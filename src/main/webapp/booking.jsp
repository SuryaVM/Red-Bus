<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Bus Ticket Booking</title>
<link rel="stylesheet" href="css/styles.css">

</head>
<body data-username="<s:property value='username' />">
	<header>
		<div class="logo">
			<img src="images/Redbuslogo.png"> RedBus Booking Platform
		</div>
		<nav>
			<ul class="nav-links">
				<li><a href="#">Home</a></li>
				<li><a href="#about-us">About Us</a></li>
				<li><a href="#contact-details">Contact</a></li>
			</ul>
		</nav>
	</header>

	<main style="background-image: url(images/Background.png)">
		<button class="my-booking" id="my-booking">My Bookings</button>
		<div id="my-bookings-section" style="display: none;"></div>
		<h2>India's No. 1 Online Bus Ticket Booking Site</h2>

		<section class="search-section">
			<h1>
				Welcome
				<s:property value="username" />
				!, Book Your Bus Ticket
			</h1>
<br>
			<div id="search-form">
				<input name="from" id='From' placeholder="From" required> <input
					name="to" id='To' placeholder="To" required> <input
					name="date" type="date" id='Date' placeholder="Date" required>
				<!-- <input type="number" id='Passengers'placeholder="Passengers" min="1" required> -->
				<br></br>
				<button type="submit" id="searchBtn">Search Buses</button>
			</div>

		</section>


		<section id="results-section">

			<div class="initial-card">
			<br><br><br>
				<h3 style="color: red;" align="center">
					<b>Search for Buses</b>
				</h3>
			</div>

		</section>

	</main>

	<section>

		<img src="images/Qr.png" alt="QrCode ad"
			style="max-width: 100%; height: 500px; padding: 5%">
		<section id="about-us">
			<h2 style="text-align: center;">About Us</h2>
			<div class="about-container">
				<img src="images/Redbuslogo.png" alt="redBus Logo"
					class="about-logo">
				<p class="about-text">redBus is the world's largest online bus
					ticket booking service trusted by over 25 million happy customers
					globally. redBus offers bus ticket booking through its website, iOS
					and Android mobile apps for all major routes.</p>
			</div>
		</section>
		<div id="contact-details">
			<h3>Contact Information</h3>
			<p>
				Email: <a href="mailto:vmsuryaram@gmail.com">support@redbus.com</a>
			</p>
			<p>Contact: 0462 202509</p>
		</div>

	</section>

	<footer>
		<p>&copy; 2025 RedBus India. All Rights Reserved.</p>
		<script src="js/script.js"></script>
	</footer>


</body>
</html>

