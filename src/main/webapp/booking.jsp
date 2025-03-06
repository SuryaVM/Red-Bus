<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "s" uri = "/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bus Ticket Booking</title>
    <link rel="stylesheet" href="css/styles.css">
	
</head>
<body>
    <header>
        <div class="logo"><img src="images/Redbuslogo.png">  RedBus Booking Platform</div>
        <nav>
            <ul class="nav-links">
                <li><a href="#">Home</a></li>
                <li><a href="#">About Us</a></li>
                <li><a href="#contact-details">Contact</a></li>
            </ul>
        </nav>
    </header>

    <main style="background-image: url(images/Background.png)">
    
        <h2>India's No. 1 Online Bus Ticket Booking Site</h2>
        
        <section class="search-section">
            <h1>Book Your Bus Ticket</h1>
            
            <div id="search-form">
                <input name="from" id='From' placeholder="From" required> 
                <input name="to" id='To' placeholder="To" required>
                <input name="date" type="date" id='Date' required>
                <!-- <input type="number" id='Passengers'placeholder="Passengers" min="1" required> -->
                <br></br>
                <button type="submit" id="searchBtn">Search Buses</button>
            </div>
            
        </section>
 

        <section id="results-section">
        
            <div class="bus-card">
                <br><br><br><br>
                <h3 style="color: red;" align="center" ><b>Search for Buses</b></h3>
            </div>
				<%-- <s:property value="busJson"/> --%>
        </section>
        
    </main>

    <section>
    
        <div id="contact-details">
            <h3>Contact Information</h3>
            <p>Email: <a href="mailto:vmsuryaram@gmail.com">support@redbus.com</a></p>
            <p>Contact: 0462 202509</p>
        </div>

    </section>
    
    <footer>
        <p>&copy; 2025 Bus Ticketing. All Rights Reserved.</p>
        <script src="js/script.js"></script>
    </footer>

    
</body>
</html>

