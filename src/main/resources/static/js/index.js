const socket = new WebSocket("ws://localhost:8080");

socket.addEventListener("open", event => {
    console.log("Websocket connection opened");

});

socket.addEventListener("message" event => {

    const receivedData = event.data;
});


socket.addEventListener("close", event => {
    console.log("WebSocket connection closed");
});

socket.addEventListener("error", event => {
    console.error("WebSocket error:", event);
});

socket.send("Hello, server!");

socket.close();

//
//const http = require("http");
//const websocketServer = require("websocket").server
//const httpServer = http.createServer();
//httpServer.listen(8080, () => console.log("listening.. on 8080"))
//
//const wsServer = new websocketServer({
//    "httpServer": httpServer
//
//})
//
//wsServer.on("request", request => {
//
//    const connection = request.accept(null, request.origin);
//    connection.on("open", () => console.log("opened"))
//    connection.on("close", () => console.log("closed"))
//    connection.on("message", message => {
//
//    })
//
//    const clientID = userID;
//
//})
