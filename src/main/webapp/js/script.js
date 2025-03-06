

document.addEventListener("DOMContentLoaded", function() {
	const dateInput = document.getElementById("Date");
	const today = new Date().toISOString().split('T')[0]; // Get today's date in YYYY-MM-DD format
	dateInput.setAttribute("min", today); // Set the minimum date to today
});

// Validate the "From" and "To" fields to ensure they are not the same
document.addEventListener("DOMContentLoaded", function() {
	alert("refreshed");
	document.getElementById("searchBtn").addEventListener("click", function() {
		const from = document.getElementById("From").value.trim();
		const to = document.getElementById("To").value.trim();
		const date = document.getElementById("Date").value.trim();

		if (from.toLowerCase() === to.toLowerCase()) {
			alert("The 'From' and 'To' locations cannot be the same.");
		} else if (from === "" || to === "") {
			alert("Please fill in both 'From' and 'To' fields.");
		} else {
			alert("Searching buses...");

			const xhr = new XMLHttpRequest();
			const params = `from=${encodeURIComponent(from)}&to=${encodeURIComponent(to)}&date=${encodeURIComponent(date)}`;
			xhr.open("GET", `search.action?${params}`, true);
			xhr.onreadystatechange = function() {
				if (this.readyState === 4 && this.status === 200) {
					console.log("Response received!");
					const busContainer = document.getElementById('results-section');
					busContainer.innerHTML = ""; // Clear previous results

					// Corrected parsing and response handling
					const buses = JSON.parse(xhr.responseText);
					buses.forEach(bus => {
						const busCard = `
							<div class="bus-card">
								<div style="color:rgb(252, 13, 29);" class="bus-title">${bus.bus_name}</div>
								<div class="bus-info"><strong>Bus ID:</strong> ${bus.id}</div>
								<div class="bus-info"><strong>Departure:</strong> ${bus.departure_time}</div>
								<div class="bus-info"><strong>Arrival:</strong> ${bus.arrival_time}</div>
								
								<div class="bus-type">
									<span class="${bus.ac ? 'ac' : 'unavailable'}">AC</span>
									<span class="${bus.ac === false ? 'non-ac' : 'unavailable'}">Non-AC</span>
									<span class="${bus.sleeper ? 'sleeper' : 'unavailable'}">Sleeper</span>
									<span class="${bus.semi_sleeper ? 'semi-sleeper' : 'unavailable'}">Semi-Sleeper</span>
								</div>

								<div class="availability">
									&#x1F6CF; Berths Available: <strong>${bus.berths_available}</strong> | &#x1F4BA; Seats Available: <strong>${bus.seats_available}</strong>
								</div>
								<br>
								<button class="button">Book Now</button>
							</div>
						`;
						busContainer.innerHTML += busCard;
					});
				}
			};
			xhr.send();
		}
	});
});

/*buses.forEach(bus => {
											const busCard =
											 `<div class="bus-card">
																		<div style="color:rgb(252, 13, 29);" class="bus-title">${bus.bus_name}</div>
																		<div class="bus-info"><strong>Bus ID:</strong> ${bus.id}</div>
																		<div class="bus-info"><strong>Departure:</strong> ${bus.departure_time}</div>
																		<div class="bus-info"><strong>Arrival:</strong> ${bus.arrival_time}</div>
																	    
																		<div class="bus-type">
																		  <span class="${bus.ac ? 'ac' : 'unavailable'}">AC</span>
																		  <span class="${bus.ac == false ? 'non-ac' : 'unavailable'}">Non-AC</span>
																		  <span class="${bus.sleeper ? 'sleeper' : 'unavailable'}">Sleeper</span>
																		  <span class="${bus.semi_sleeper ? 'semi-sleeper' : 'unavailable'}">Semi-Sleeper</span>
																		</div>
															  
																		<div class="availability">
																		  🛏️ Berths Available: <strong>${bus.berths_available}</strong> | 💺 Seats Available: <strong>${bus.seats_available}</strong>
																		</div>
																		<br>
																		<button class="button">Book Now</button>
																	  </div>
														`;
						busContainer.innerHTML += busCard; 
					});*/
/*
const xhr = new XMLHttpRequest();
			xhr.open("GET", `http://localhost:8080/bus/fetch?form=${from}&to=${to}`, true);
			xhr.setRequestHeader("Content-Type", "application/json");
			xmlhttp.onreadystatechange = function() {
				  if (this.readyState == 4 && this.status == 200) {
						const buses = JSON.parse(xhr.responseText);
						console.log("hi");
						console.log(buses);
*/
