package com;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.exception.ConstraintViolationException;

import com.Data.Booking;
import com.Data.User;
import com.Data.UserSession;
import com.blazebit.security.BCrypt;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.servlet.http.Cookie;

public class Crud {
	private static SessionFactory factoryBus, factoryUser, factoryBooking, factoryUserSession;

	// Common method to get session
	private static Session getSession(Class<?> entityClass) {
		SessionFactory factory = null;

		if (entityClass == com.Data.Bus.class) {
			if (factoryBus == null) {
				factoryBus = buildSessionFactory(entityClass);
			}
			factory = factoryBus;
		} else if (entityClass == com.Data.User.class) {
			if (factoryUser == null) {
				factoryUser = buildSessionFactory(entityClass);
			}
			factory = factoryUser;
		} else if (entityClass == com.Data.Booking.class) {
			if (factoryBooking == null) {
				factoryBooking = buildSessionFactory(entityClass);
			}
			factory = factoryBooking;
		} else if (entityClass == com.Data.UserSession.class) {
			if (factoryUserSession == null) {
				factoryUserSession = buildSessionFactory(entityClass);
			}
			factory = factoryUserSession;
		}

		return factory != null ? factory.openSession() : null;
	}

	// Common method to build SessionFactory
	private static SessionFactory buildSessionFactory(Class<?> entityClass) {
		Configuration config = new Configuration();
		config.addAnnotatedClass(entityClass);
		config.configure();
		return config.buildSessionFactory();
	}

	public static List<com.Data.Bus> fetchBuses(String From, String To) {

		Session session = getSession(com.Data.Bus.class);

		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<com.Data.Bus> query = builder.createQuery(com.Data.Bus.class);

		Root<com.Data.Bus> root = query.from(com.Data.Bus.class);

		Predicate from_cond = builder.equal(root.get("from_location"), From);
		Predicate to_cond = builder.equal(root.get("to_location"), To);
		query.select(root).where(builder.and(from_cond, to_cond));

		List<com.Data.Bus> buses = session.createQuery(query).getResultList();

		session.close();
		return buses;
	}

	public static List<com.Data.Bus> fetchBusesWithId(int id) {

		Session session = getSession(com.Data.Bus.class);

		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<com.Data.Bus> query = builder.createQuery(com.Data.Bus.class);

		Root<com.Data.Bus> root = query.from(com.Data.Bus.class);

		Predicate id_cond = builder.equal(root.get("id"), id);
		query.select(root).where(id_cond);

		List<com.Data.Bus> buses = session.createQuery(query).getResultList();

		session.close();
		return buses;
	}

	public static void updateSeatCount(int id, boolean sleeper, boolean semi_sleeper, boolean add) {
		Session session = getSession(com.Data.Bus.class);
		Transaction transaction = session.beginTransaction();
		String updateQuery = null;
		if (!add) {
			updateQuery = "UPDATE Bus b SET " + (sleeper ? "b.berths_available = b.berths_available - 1" : "")
					+ (semi_sleeper ? "b.seats_available = b.seats_available - 1" : "") + " WHERE b.id = :id";
		} else {
			updateQuery = "UPDATE Bus b SET " + (sleeper ? "b.berths_available = b.berths_available + 1" : "")
					+ (semi_sleeper ? "b.seats_available = b.seats_available + 1" : "") + " WHERE b.id = :id";
		}

		int updated = session.createMutationQuery(updateQuery).setParameter("id", id).executeUpdate();

		transaction.commit();
		System.out.println("HQL Updated rows: " + updated + " after updated seatCount");
		session.close();
	}

	public static String fetchSession(String username) {
		Session session = getSession(com.Data.UserSession.class);
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<com.Data.UserSession> query = builder.createQuery(com.Data.UserSession.class);

		Root<com.Data.UserSession> root = query.from(com.Data.UserSession.class);

		Predicate username_cond = builder.equal(root.get("username"), username);
		query.select(root).where(username_cond);

		List<com.Data.UserSession> userSessions = session.createQuery(query).getResultList();
		session.close();

		if(!userSessions.isEmpty()) {
			for( UserSession u : userSessions) {
				if( ( u.getExpires_at() ).after( Timestamp.from(Instant.now() ) ) ) {
					return u.getSession_id();
				}
			}
		}
		return "";
	}


	public static void addSession(String session_id, String username) {
		Session session = getSession(com.Data.UserSession.class);

		Transaction transaction = session.beginTransaction();
		UserSession userSession = new UserSession(session_id, username);
		session.persist(userSession);

		transaction.commit();
		session.close();
	}

	public static String validateCookie(Cookie cookies[]) {
		Session session = getSession(com.Data.UserSession.class);
		String sessionId;
		System.out.println(cookies);
		for (Cookie cookie : cookies) {
			if ("SESSION_ID".equals(cookie.getName())) {
				sessionId = cookie.getValue();
				CriteriaBuilder builder = session.getCriteriaBuilder();
				CriteriaQuery<com.Data.UserSession> query = builder.createQuery(com.Data.UserSession.class);

				Root<com.Data.UserSession> root = query.from(com.Data.UserSession.class);

				Predicate sessionId_cond = builder.equal(root.get("session_id"), sessionId);
				query.select(root).where(sessionId_cond);

				List<com.Data.UserSession> userSessions = session.createQuery(query).getResultList();
				System.out.println(userSessions);
				session.close();

				return userSessions.isEmpty() ? "" : userSessions.get(0).getUsername();
			}
		}
		session.close();
		return "";
	}

	public static boolean addUser(String username, String password, String email, String full_name) {
		Session session = getSession(com.Data.User.class);

		String salt = BCrypt.gensalt();
		String hashedpw = BCrypt.hashpw(password, salt);
		Transaction transaction = session.beginTransaction();

		User user = new User(username, hashedpw, email, full_name, salt);
		try {
			session.persist(user);
			transaction.commit();
			return true;
		} catch (ConstraintViolationException e) {
			System.out.println("Username already exists. Please choose a different one.");
			transaction.rollback();
			return false;
		} finally {
			session.close();
		}
	}

	public static boolean validateUser(String username, String password) {
		Session session = getSession(com.Data.User.class);

		// Transaction transaction = session.beginTransaction();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<com.Data.User> query = builder.createQuery(com.Data.User.class);
		Root<com.Data.User> root = query.from(com.Data.User.class);

		Predicate usernameCondition = builder.equal(root.get("username"), username);

		query.select(root).where(usernameCondition);

		List<User> users = session.createQuery(query).getResultList();
		// transaction.commit();
		session.close();
		if (!users.isEmpty()) {
			String salt = users.get(0).getSalt();
			String dbpassword = users.get(0).getPassword();
			String hashedpw = BCrypt.hashpw(password, salt);
			return hashedpw.equals(dbpassword);
		}
		return false;
	}

	public static void bookBus(String Date, String busId, String seatType, String username) throws ParseException {
		Session session = getSession(com.Data.Booking.class);
		Transaction transaction = session.beginTransaction();

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date utilDate = dateFormat.parse(Date);

		// Convert java.util.Date to java.sql.Date
		Date sqlDate = new Date(utilDate.getTime());

		Booking book = new Booking(username, seatType, sqlDate, Integer.parseInt(busId));

		session.persist(book);
		transaction.commit();
		session.close();

	}

	public static void cancelBus(String username, int bookingId) throws ParseException {
		Session session = getSession(com.Data.Booking.class);
		Transaction transaction = session.beginTransaction();

		String updateQuery = "UPDATE Booking b SET b.status = 'cancelled' WHERE b.id = :id AND b.username = :username";

		int updated = session.createMutationQuery(updateQuery).setParameter("id", bookingId)
				.setParameter("username", username).executeUpdate();

		transaction.commit();
		System.out.println("HQL Updated rows: " + updated + " after cancellation");
		session.close();

	}

	public static List<com.Data.Booking> myBookings(String username) {
		Session session = getSession(com.Data.Booking.class);
		Transaction transaction = session.beginTransaction();

		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<com.Data.Booking> query = builder.createQuery(com.Data.Booking.class);

		Root<com.Data.Booking> root = query.from(com.Data.Booking.class);

		Predicate username_cond = builder.equal(root.get("username"), username);

		query.select(root).where(builder.and(username_cond));

		List<com.Data.Booking> myBookings = session.createQuery(query).getResultList();

		transaction.commit();
		session.close();
		return myBookings;
	}

}
