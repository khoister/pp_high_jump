var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res) {
  res.render('index', { title: 'Nothing to see here...' });
});

router.get('/weather', function(req, res) {
  res.render('weather', { title: 'Simple Weather Display' });
});

module.exports = router;
