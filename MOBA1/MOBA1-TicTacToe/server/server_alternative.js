const SocketServer = require('websocket').server
const http = require('http')

const server = http.createServer((req, res) => {})

server.listen(3001, ()=>{
    console.log("Listening on port 3001...")
})

wsServer = new SocketServer({httpServer:server})

var board = "";
var connections = []

wsServer.on('request', function(request) {
    var connection = request.accept();
    connections.push(connection);
    console.log((new Date()) + ' Connection accepted.');

    connection.on('message', function(message) {
        connections.forEach(element => {
            if (element != connection)
            element.sendUTF(message.utf8Data);
          })
    })

    connection.on('close', (resCode, des) => {
        console.log('connection closed')
        connections.splice(connections.indexOf(connection), 1)
    })

})
