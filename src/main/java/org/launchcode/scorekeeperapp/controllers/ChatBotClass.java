package org.launchcode.scorekeeperapp.controllers;
import javax.websocket.*;
import java.net.URI;

@ClientEndpoint
public class ChatBotClass {

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("WebSocket connection opened");

    }


    @OnMessage
    public void onMessage(String message) {
        System.out.println("Received message: " + message);

    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        System.out.println("WebSocket connection closed: " + closeReason);
    }

    public static void main(String[] args) {
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        String uri = "ws://localhost:8080";

        try {
            Session session = container.connectToServer(ChatBotClass.class, URI.create(uri));

            session.getBasicRemote().sendText("Hello, server!");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
