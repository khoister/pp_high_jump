// Document ready
$(document).ready(initialize);

// Data ==================================================================

// Simply add/update or remove cities from this table
var locations = new Array
(
	{ city: "austin",        state: "tx" },
	{ city: "san_jose",      state: "ca" },
	{ city: "campbell",      state: "ca" },
	{ city: "omaha",         state: "ne" },
	{ city: "timonium",      state: "md" },
	{ city: "san_francisco", state: "ca" }
);

var CURRENT_CONDITIONS_ROWS = 2;
var FORECAST_ROWS = 10; // Rows for the 10-day forecast


// Functions =============================================================

function initialize()
{
	// Create a table
	var table = document.createElement("TABLE");
	table.setAttribute("id", "WeatherTable");
	table.style.backgroundColor = 'white';
	table.style.borderStyle = 'solid';
	table.style.borderWidth = '1px';
	var div = document.getElementById("weather");
	div.appendChild(table);

	// Create rows to display information
	for (var i = 0; i < CURRENT_CONDITIONS_ROWS + FORECAST_ROWS; ++i) {
		var row = table.insertRow(i);
		// Create the columns ahead of time, one for each city
		for (var j = 0; j < locations.length; ++j) {
			row.insertCell(j);
		}
	}

	for (var i = 0; i < locations.length; ++i)
	{
		// Load the data from remote weather server
		load_current_conditions(i, '/weather/current/' + locations[i].state + '/' + locations[i].city);
		load_10day_forecast(i, '/weather/10day/' + locations[i].state + '/' + locations[i].city);
	}
}   

function load_current_conditions(column, data_url)
{
	$.getJSON( data_url, function( data )
	{
		var table = document.getElementById("WeatherTable");
		var cell;
		var step = 0;

		// Time
		cell = table.rows[step++].cells[column];
		cell.style.borderStyle = 'solid';
		cell.style.borderWidth = '1px';
		cell.setAttribute("align", "center");
		cell.innerHTML = '<b>Currently</b><br>' + data.current_observation.local_time_rfc822;

		// Location
		cell = table.rows[step++].cells[column];
		cell.setAttribute("align", "center");
		cell.innerHTML =
			'<b>' + data.current_observation.display_location.full + '</b><br>' + // Location
			'<img src="' + data.current_observation.icon_url + '"><br>' +         // Weather icon
			data.current_observation.temp_f + ' F<br>' +                          // Temperature in F
			data.current_observation.weather;                                     // Conditions

	});
}

function load_10day_forecast(column, data_url)
{
	$.getJSON( data_url, function( data )
	{
		var table = document.getElementById("WeatherTable");
		var step = CURRENT_CONDITIONS_ROWS;
		var cell;
		$.each(data.forecast.simpleforecast.forecastday, function()
		{
			var date = this.date.weekday_short + ', ' + this.date.monthname + ' ' + this.date.day + ', ' + this.date.year;
			// Aggregate weather information
			cell = table.rows[step++].cells[column];
			cell.style.borderStyle = 'solid';
			cell.style.borderWidth = '1px';
			cell.setAttribute("align", "center");
			cell.innerHTML =
				'<b>' + date + '</b>' +                                          // Date
				'<br><img src="' + this.icon_url + '"><br>' +                    // Weather icon
				this.low.fahrenheit + ' - ' + this.high.fahrenheit + ' F<br>' +  // Low - High Temperature in F
				this.conditions;                                                 // Conditions
		});
	});
}

