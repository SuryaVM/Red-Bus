
 
 document.addEventListener("DOMContentLoaded", function () {
    const dateInput = document.getElementById("Date");
    const today = new Date().toISOString().split('T')[0]; // Get today's date in YYYY-MM-DD format
    dateInput.setAttribute("min", today); // Set the minimum date to today
});

// Validate the "From" and "To" fields to ensure they are not the same
document.getElementById("search-form").addEventListener("submit", function (event) {
    const from = document.getElementById("From").value.trim();
    const to = document.getElementById("To").value.trim();

    if (from.toLowerCase() === to.toLowerCase()) {
        alert("The 'From' and 'To' locations cannot be the same.");
        event.preventDefault(); // Prevent form submission
    }
});

function searchBuses (event) {
    // const fromLocation = document.getElementById("From").value;
    // const toLocation =  document.getElementById("To").value;
    // const date = document.getElementById("Date").value; 
    // const passengers =  document.getElementById("Passengers").value;
    event.preventDefault();
    const buses = [
        {
          bus_id: "TN01AB1234",
          bus_name: "Chennai - Madurai Express",
          from: "Chennai",
          to: "Madurai",
          departure_time: "2025-02-27T22:00:00",
          arrival_time: "2025-02-28T06:00:00",
          type: {
            ac: true,
            non_ac: false,
            sleeper: true,
            semi_sleeper: false
          },
          availability: {
            berths_available: 5,
            seats_available: 20
          }
        },
        {
            bus_id: "TN02CD5678",
            bus_name: "Coimbatore - Trichy Service",
            from: "Coimbatore",
            to: "Trichy",
            departure_time: "2025-02-27T15:00:00",
            arrival_time: "2025-02-27T19:00:00",
            type: {
              ac: false,
              non_ac: true,
              sleeper: false,
              semi_sleeper: true
            },
            availability: {
              berths_available: 0,
              seats_available: 15
            }
          },
          {
            bus_id: "TN02CD5678",
            bus_name: "Coimbatore - Trichy Service",
            from: "Coimbatore",
            to: "Trichy",
            departure_time: "2025-02-27T15:00:00",
            arrival_time: "2025-02-27T19:00:00",
            type: {
              ac: false,
              non_ac: true,
              sleeper: false,
              semi_sleeper: true
            },
            availability: {
              berths_available: 0,
              seats_available: 15
            }
          },
          {
            bus_id: "TN02CD5678",
            bus_name: "Coimbatore - Trichy Service",
            from: "Coimbatore",
            to: "Trichy",
            departure_time: "2025-02-27T15:00:00",
            arrival_time: "2025-02-27T19:00:00",
            type: {
              ac: false,
              non_ac: true,
              sleeper: false,
              semi_sleeper: true
            },
            availability: {
              berths_available: 0,
              seats_available: 15
            }
          },
          {
            bus_id: "TN02CD5678",
            bus_name: "Coimbatore - Trichy Service",
            from: "Coimbatore",
            to: "Trichy",
            departure_time: "2025-02-27T15:00:00",
            arrival_time: "2025-02-27T19:00:00",
            type: {
              ac: false,
              non_ac: true,
              sleeper: false,
              semi_sleeper: true
            },
            availability: {
              berths_available: 0,
              seats_available: 15
            }
          },
          {
            bus_id: "TN02CD5678",
            bus_name: "Coimbatore - Trichy Service",
            from: "Coimbatore",
            to: "Trichy",
            departure_time: "2025-02-27T15:00:00",
            arrival_time: "2025-02-27T19:00:00",
            type: {
              ac: false,
              non_ac: true,
              sleeper: false,
              semi_sleeper: true
            },
            availability: {
              berths_available: 0,
              seats_available: 15
            }
          },
          {
            bus_id: "TN02CD5678",
            bus_name: "Coimbatore - Trichy Service",
            from: "Coimbatore",
            to: "Trichy",
            departure_time: "2025-02-27T15:00:00",
            arrival_time: "2025-02-27T19:00:00",
            type: {
              ac: false,
              non_ac: true,
              sleeper: false,
              semi_sleeper: true
            },
            availability: {
              berths_available: 0,
              seats_available: 15
            }
          },
        {
          bus_id: "TN02CD5678",
          bus_name: "Coimbatore - Trichy Service",
          from: "Coimbatore",
          to: "Trichy",
          departure_time: "2025-02-27T15:00:00",
          arrival_time: "2025-02-27T19:00:00",
          type: {
            ac: false,
            non_ac: true,
            sleeper: false,
            semi_sleeper: true
          },
          availability: {
            berths_available: 0,
            seats_available: 15
          }
        },
        {
          bus_id: "TN03EF9012",
          bus_name: "Salem - Tirunelveli Night Service",
          from: "Salem",
          to: "Tirunelveli",
          departure_time: "2025-02-27T20:30:00",
          arrival_time: "2025-02-28T05:00:00",
          type: {
            ac: true,
            non_ac: false,
            sleeper: true,
            semi_sleeper: false
          },
          availability: {
            berths_available: 8,
            seats_available: 10
          }
        }
      ];
  
      const busContainer = document.getElementById('results-section');
      busContainer.innerHTML = '';

      buses.forEach(bus => {
        const busCard = `
          <div class="bus-card">
            <div style="color:rgb(252, 13, 29);" class="bus-title">${bus.bus_name}</div>
            <div class="bus-info"><strong>Bus ID:</strong> ${bus.bus_id}</div>
            <div class="bus-info"><strong>Departure:</strong> ${bus.departure_time}</div>
            <div class="bus-info"><strong>Arrival:</strong> ${bus.arrival_time}</div>
            
            <div class="bus-type">
              <span class="${bus.type.ac ? 'ac' : 'unavailable'}">AC</span>
              <span class="${bus.type.non_ac ? 'non-ac' : 'unavailable'}">Non-AC</span>
              <span class="${bus.type.sleeper ? 'sleeper' : 'unavailable'}">Sleeper</span>
              <span class="${bus.type.semi_sleeper ? 'semi-sleeper' : 'unavailable'}">Semi-Sleeper</span>
            </div>
  
            <div class="availability">
              🛏️ Berths Available: <strong>${bus.availability.berths_available}</strong> | 💺 Seats Available: <strong>${bus.availability.seats_available}</strong>
            </div>
            <br>
            <button class="button">Book Now</button>
          </div>
        `;
        busContainer.innerHTML += busCard;
      });
};

