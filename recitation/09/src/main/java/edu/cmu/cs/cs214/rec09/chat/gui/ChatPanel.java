package edu.cmu.cs.cs214.rec09.chat.gui;

import javax.swing.*;

import edu.cmu.cs.cs214.rec09.chat.client.ChatClient;
import edu.cmu.cs.cs214.rec09.chat.client.ChatClientListener;

import java.awt.*;
import java.awt.event.ActionListener;

/**
 * The Chat Panel UI for each participant.
 */
public class ChatPanel extends JPanel implements ChatClientListener {
    private static final int MSG_FIELD_WIDTH = 60;
    private static final int CHAT_AREA_WIDTH = MSG_FIELD_WIDTH + 10;
    private static final int CHAT_AREA_HEIGHT = 20;

    /** Chat participant's name. */
    private final String name;
    
    /** Text area for displaying chat messages. */
    private final JTextArea chatArea;
        
    /**
     * Initialize a new ChatPanel with a chat server and participant's name
     */
    public ChatPanel(ChatClient client) {
    	this.name = client.getName();

    	// Register the Panel as a listener for the client
    	client.addListener(this);
        
        // Sets up a scrollable text area that line-wraps its messages, for the
        // chat messages.
        chatArea = new JTextArea(CHAT_AREA_HEIGHT, CHAT_AREA_WIDTH);
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);

        // Fun fact: the JScrollPane is a good example of the decorator pattern.
        JScrollPane scrollPane = new JScrollPane(chatArea);

        // Sets up a text field for typing chat messages, and a button to send
        // them.
        final JTextField chatField = new JTextField(MSG_FIELD_WIDTH);
        JButton sendButton = new JButton("Send");
        JPanel messagePanel = new JPanel();
        messagePanel.setLayout(new BorderLayout());
        messagePanel.add(chatField, BorderLayout.WEST);
        messagePanel.add(sendButton, BorderLayout.EAST);

        // Observer to send a message when the user presses the send button or
        // hits enter in the message field.
        ActionListener sendChatListener = e -> {
        	client.send(chatField.getText(), ChatPanel.this.name);
            chatField.setText("");
            chatField.requestFocus();
        };

        // when send button is clicked
        sendButton.addActionListener(sendChatListener);
        // when "Enter" key is pressed
        chatField.addActionListener(sendChatListener);

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.NORTH);
        add(messagePanel, BorderLayout.SOUTH);
        setVisible(true);
    }

    @Override
    public void onIncomingMsg(String name, String message) {
        String newText = String.format(" %s: %s\n", name, message);
        // add the new message to the bottom of message panel
        chatArea.append(newText);
    }
}
