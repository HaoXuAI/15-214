package edu.cmu.cs.cs214.rec09.chat.server;

/**
 * Callback for ChatServer, called each time a message arrives.
 */
public interface ChatSubscriber {
    /**
     * Handles a message received from chat server, which includes sender's name
     * and the message text.
     * 
     * @param name
     *            sender's name
     * @param message
     *            message text
     */
    void handleMessage(String name, String message);
}
