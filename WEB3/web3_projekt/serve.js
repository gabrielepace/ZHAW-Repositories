"use strict";

const path = require('path');
const express = require('express');
const morgan = require('morgan');
const apiRoutes = require('./api/routing');

const port = process.env.PORT || 8080;

const app = express();
app.use(morgan('dev'));
app.use(express.json());
app.use(express.static(path.resolve(__dirname, 'src')));
app.use('/node_modules', express.static(path.resolve(__dirname, 'node_modules')));

apiRoutes(app).then(() => {
    app.listen(port, () => {
        console.log(`Ready. Use URL: localhost:${port}`);
    });
});

// test API calls
app.get('/api/tests/plus', (req, res) => {
    // tested with `curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET "localhost:8080/api/tests/plus?x=15&y=22" `
    let x = Number(req.query.x) || 0;
    let y = Number(req.query.y) || 1;
    res.json({'result': x + y});
});
app.post('/api/tests/minus', (req,res) => {
    // tested with `curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X POST "localhost:8080/api/tests/minus" -d '{ "x": 10, "y": 2.5}' `
    let x = Number(req.body.x) || 0;
    let y = Number(req.body.y) || 0;
    res.json({'result': x-y});
});
app.delete('/api/tests/echo', (req, res) => {
    res.json({'test': "1234"});
});
app.put('/api/tests/echo', (req, res) => {
    res.json({'test': "5678"});
});