package com;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class Crud{

	public static List<com.Data.Bus> fetch(String From,String To){
		Configuration config = new Configuration();
	    config.addAnnotatedClass(com.Data.Bus.class);
	    config.configure();
	    SessionFactory factory = config.buildSessionFactory();
	    Session session = factory.openSession();
	    Transaction transaction = session.beginTransaction();

	    CriteriaBuilder builder = session.getCriteriaBuilder();
	    CriteriaQuery<com.Data.Bus> query = builder.createQuery(com.Data.Bus.class);

	    Root<com.Data.Bus> root = query.from(com.Data.Bus.class);

	    Predicate from_cond = builder.equal(root.get("from_location"), From);
	    Predicate to_cond = builder.equal(root.get("to_location"), To);
	    query.select(root).where(builder.and(from_cond,to_cond));

	    List<com.Data.Bus> buses = session.createQuery(query).getResultList();

	    transaction.commit();
	    session.close();
	    return buses;
	}
}

