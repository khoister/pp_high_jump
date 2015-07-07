var express = require('express');
var router = express.Router();
var wunderground_key = "ff8e69b1f2ae3ab8";


function get_data(out_response, category, state, city)
{
	var http        = require("http");
	var host        = 'api.wunderground.com';
	var path_prefix = '/api/';
	var path        = '/' + category + '/q/';
	var path_suffix = '.json';

	// Construct the URL to Weather Underground
	var url_path = path_prefix + wunderground_key + path + state + '/' + city + path_suffix; 
	console.log("URL to wunderground: " + url_path);
	var options =
	{
		host: host,
		path: url_path
	};
	var data = '';
	var request= http.request(options, function(response)
	{
		response.on('data', function(chunk)
		{
			data += chunk;
		});
		response.on('end', function()
		{
			console.log("Dataset retrieved successfully");
			out_response.json(JSON.parse(data));
		});
	});

	request.on('error', function(e)
	{
		console.log("An error occurred retrieving from endpoint: " + e.message);
	});
	request.end();
}


/*
 * Routers
 */
router.get('/current/:state/:city', function(req, res)
{
	var state = req.params.state;
	var city = req.params.city;
	console.log('Current conditions for City='+city+', State='+state);
	get_data(res, 'conditions', state, city);
});

router.get('/10day/:state/:city', function(req, res)
{
	var state = req.params.state;
	var city = req.params.city;
	console.log('10 day forecast for City='+city+', State='+state);
	get_data(res, 'forecast10day', state, city);
});

module.exports = router;
