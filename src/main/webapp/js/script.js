function searchAndLoadBuses() {
	const from = document.getElementById("From").value.trim();
	const to = document.getElementById("To").value.trim();
	const date = document.getElementById("Date").value.trim();
	console.log(date);
	const busContainer = document.getElementById('results-section');

	if (from === "" || to === "") {
		alert("Please fill in both 'From' and 'To' fields.");
	} else if (from.toLowerCase() === to.toLowerCase()) {
		alert("The 'From' and 'To' locations cannot be the same.");
	} else if (date === "") {
		alert("Date need to be filled.")
	} else {
		const xhr = new XMLHttpRequest();
		const params = `from=${encodeURIComponent(from)}&to=${encodeURIComponent(to)}&date=${encodeURIComponent(date)}`;
		xhr.open("GET", `search.action?${params}`, true);
		xhr.onreadystatechange = function() {
			if (this.readyState === 4 && this.status === 200) {
				console.log("Response received!");
				busContainer.innerHTML = "";

				const buses = JSON.parse(xhr.responseText);
				buses.forEach(bus => {
					const busCard = `
								<div class="bus-card" data-bus='${JSON.stringify(bus)}'>
									<div style="color:rgb(252, 13, 29);" class="bus-title">${bus.bus_name}</div>
									<div class="bus-info" id="busId"><strong>Bus ID:</strong> ${bus.id}</div>
									<div class="bus-info"><strong>Departure:</strong> ${bus.departure_time}</div>
									<div class="bus-info"><strong>Arrival:</strong> ${bus.arrival_time}</div>
									
									<div class="bus-type">
										<span class="${bus.ac ? 'ac' : 'unavailable'}">AC</span>
										<span class="${bus.ac === false ? 'non-ac' : 'unavailable'}">Non-AC</span>
										<span class="${bus.sleeper ? 'sleeper' : 'unavailable'}">Sleeper</span>
										<span class="${bus.semi_sleeper ? 'semi-sleeper' : 'unavailable'}">Semi-Sleeper</span>
									</div>

									<div class="availability">
									                ${bus.sleeper ? `&#x1F6CF; Sleepers Available: <strong>${bus.berths_available}</strong><br>` : ""}
									                ${bus.semi_sleeper ? `&#x1F4BA; Semi-Sleepers Available: <strong>${bus.seats_available}</strong><br>` : ""}
									                <p style="font-weight:bold; font-size:16px;">Price: Rs.${bus.price}</p>
									</div>
									
									<button id="book-btn" class="button">Book Now</button>
								</div>
							`;
					busContainer.innerHTML += busCard;
				});
			}else if(xhr.status === 302) {
			            // Manually redirect the browser
			            window.location.href = xhr.getResponseHeader("Location");
			} 
			else {
				const busContainer = document.getElementById('results-section');
				busContainer.innerHTML = `<div id="search-message" class="searching-progress">&#x1F50E; &#x1F50D; Searching in progress...</div>
	`
			}
		};
		xhr.send();
	}
}

function myBookings() {
	const bookingsSection = document.getElementById('my-bookings-section');
	//const username = document.body.getAttribute('data-username');
	const xhr = new XMLHttpRequest();
	//const params = `username=${encodeURIComponent(username)}`;
	xhr.open("POST", "mybookings.action", true);
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr.onreadystatechange = function() {
		if (xhr.readyState === 4 && xhr.status === 200) {
			const bookings = JSON.parse(xhr.responseText);
			console.log(bookings);
			if (bookings.length === 0) {
				bookingsSection.innerHTML = '<p style="text-align: center;">No bookings found.</p>';
				return;
			}
			const today = new Date().toISOString().split('T')[0];
			bookingsSection.innerHTML = '';
			bookings.forEach(booking => {
				const bookingCard = document.createElement('div');
				bookingCard.className = 'booking-card';
				bookingCard.dataset.seatType = `${booking.seatType}`;
				bookingCard.dataset.bookingId = `${booking.bookingId}`;
				bookingCard.dataset.busId = `${booking.busId}`;

				bookingCard.innerHTML = `
								    ${booking.status !== "cancelled"
						? (booking.travelDate >= today
							? '<p style="font-style: italic; color: green;">Upcoming Journey</p>'
							: '<p style="font-style: italic; color: red;">Completed Journey</p>')
						: '<p style="font-style: italic; color: red;">Cancelled</p>'}
								    
								    <h3 style="color: red">Booking ID: ${booking.bookingId}</h3>
								    <p>Travel Date &#128467;: ${booking.travelDate}</p>
								    <p>Seat Type &#x1F6CF;/&#x1F4BA;: ${booking.seatType}</p>

								    ${booking.bus_name
						? `
								            <h4 style="color: red">Bus Details:</h4>
								            <p>Bus Name &#128652;: ${booking.bus_name}</p>
								            <p>From: ${booking.from_location} &#x2192; To: ${booking.to_location}</p>
								            <p>Departure &#x23F1;: ${booking.departure_time}</p>
								            <p>Arrival &#x23F1;: ${booking.arrival_time}</p>
								        `
						: '<p>Bus details not available.</p>'}
								    
									${booking.status !== "cancelled" && booking.travelDate >= today
						? '<button id="cancel-btn" class="cancel-btn button">Cancel</button>'
						: ''}
								`;

				bookingsSection.appendChild(bookingCard);
			});
		}
		
	};
	xhr.send();
	//xhr.send(params);
}

document.addEventListener("DOMContentLoaded", function() {
	const dateInput = document.getElementById("Date");
	const today = new Date().toISOString().split('T')[0]; // Get today's date in YYYY-MM-DD format
	dateInput.setAttribute("min", today); // Set the minimum date to today
});

document.addEventListener("DOMContentLoaded", function() {
	document.getElementById("searchBtn").addEventListener("click", function() {
		searchAndLoadBuses();
	});
});



document.getElementById('results-section').addEventListener('click', function(event) {
	if (event.target.classList.contains('button')) {
		const busCard = event.target.closest('.bus-card');
		alert(`Pressed book now, Booking initiated!`);
		const book = busCard.querySelector("#book-btn");
		const bus = JSON.parse(busCard.dataset.bus); // Parse the string back to an object
		book.innerHTML = '';
		if (bus.sleeper == true) {
			book.innerHTML += `<button class="inner-button slpr-btn">Sleeper</button>`;

		}
		if (bus.semi_sleeper == true) {
			book.innerHTML += `<button class="inner-button semislpr-btn">Semi-Sleeper</button> `;
		}
	}
});
document.getElementById('my-booking').addEventListener('click', function() {
	myBookings();
});

document.getElementById('my-bookings-section').addEventListener('click', function(event) {
	if (event.target.classList.contains('cancel-btn')) {
		const bookingCard = event.target.closest('.booking-card');
		const bookingId = bookingCard.dataset.bookingId;
		const seatType = bookingCard.dataset.seatType;
		const busId = bookingCard.dataset.busId;
		//const username = document.body.getAttribute('data-username');
		const xhr = new XMLHttpRequest();
		const params = `bookingId=${encodeURIComponent(bookingId)}&seatType=${encodeURIComponent(seatType)}&busId=${encodeURIComponent(busId)}`;
		xhr.open("POST", "cancel.action", true);
		xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		xhr.onreadystatechange = function() {
			if (xhr.readyState === 4 && xhr.status === 200) {
				alert("Bus ticket was Canceled");
				const busContainer = document.getElementById('results-section');
				// Only refresh search results if results section has actual bus cards
				if (busContainer.querySelector('.bus-card')) {
					searchAndLoadBuses();
				}
				myBookings();
			}
			else if(xhr.status === 302) {
						// Manually redirect the browser
						window.location.href = xhr.getResponseHeader("Location");
					}
		};
		xhr.send(params);
	}
});


document.addEventListener('click', (event) => {
	const section = document.getElementById('my-bookings-section');
	const myBookingBtn = document.getElementById('my-booking');

	// Toggle visibility when the "My Bookings" button is clicked
	if (event.target === myBookingBtn) {
		section.style.display = section.style.display === 'block' ? 'none' : 'block';
		return; // Stop further execution so it doesn't close immediately
	}

	// Close the section if it's open and click happens outside the section and button
	if (section.style.display === 'block' && !section.contains(event.target)) {
		section.style.display = 'none';
	}
});

document.getElementById('results-section').addEventListener('click', function(event) {

	if (event.target.classList.contains('slpr-btn') || event.target.classList.contains('semislpr-btn')) {
		const from = document.getElementById("From").value.trim();
		const to = document.getElementById("To").value.trim();
		const date = document.getElementById("Date").value.trim();
		const busCard = event.target.closest('.bus-card');
		const bus = JSON.parse(busCard.dataset.bus); // Parse the string back to an object
		const busId = bus.id;
		const seatType = event.target.classList.contains('slpr-btn') ? "sleeper" : "semi-sleeper";
		if ((bus.berths_available < 1 && seatType === "sleeper") || (bus.seats_available < 1 && seatType === "semi-sleeper")) {
			alert(`No ${seatType} seats available for the current bus`);
		}
		else {
			//const username = document.body.getAttribute('data-username');
			// Fire AJAX request to book the specific seat type
			const xhr = new XMLHttpRequest();
			const params = `from=${encodeURIComponent(from)}&to=${encodeURIComponent(to)}&date=${encodeURIComponent(date)}&busId=${encodeURIComponent(busId)}&seatType=${encodeURIComponent(seatType)}`;
			xhr.open("POST", "book.action", true);
			xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

			xhr.onreadystatechange = function() {
				if (xhr.readyState === 4) {
					if (xhr.status === 200) {
						alert(`Seat booked successfully for ${seatType}!`);
						searchAndLoadBuses();
					} else if(xhr.status === 302) {
						// Manually redirect the browser
						window.location.href = xhr.getResponseHeader("Location");
					}
				}
			};

			xhr.send(params);
		}
	}
});
