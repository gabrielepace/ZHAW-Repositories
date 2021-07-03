

// Using express: http://expressjs.com/
var express = require('express');
var fs = require('fs');
// Create the app
var app = express();

var io = require('socket.io')(server);

// Set up the server
// process.env.PORT is related to deploying on heroku
var server = app.listen(process.env.PORT || 3001, listen);

// This call back just tells us that the server has started
function listen() {
    var host = server.address().address;
    var port = server.address().port;
    console.log('Example app listening at http://' + host + ':' + port);
}

app.use(express.static('public'));

var board = "";


io.sockets.on('connection',
    function (socket) {


        console.log("We have a new client: " + socket.id);


        io.sockets.emit('board', { board: board });
		socket.emit('board', { board: board });


        socket.on('message',
            function (data) {
                console.log(data)
				board = data.board;
				io.sockets.emit('board', { board: board });
				socket.emit('board', { board: board });
            }
        );

        socket.on('disconnect', function () {
            console.log("Client has disconnected");
        });
    }
);


