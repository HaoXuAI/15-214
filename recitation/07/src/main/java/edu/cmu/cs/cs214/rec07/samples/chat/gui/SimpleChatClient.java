package edu.cmu.cs.cs214.rec07.samples.chat.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import edu.cmu.cs.cs214.rec07.samples.chat.core.ChatServer;

/**
 * A toy chat client designed to demonstrate basic GUI programming.
 */
public class SimpleChatClient extends JPanel {
    /**
     * Horizontal and vertical distance between successive chat windows in
     * pixels.
     */
    private static final int CHAT_WINDOW_POS_OFFSET = 30;

    /** The JFrame from which this chat is established. */
    private JFrame parentFrame;

    /** The names of the participants in this chat. */
    private final List<String> names;

    public SimpleChatClient(JFrame frame) {
        this.parentFrame = frame;
        this.names = new ArrayList<>();

        // Create the components to add to the panel.
        JLabel participantLabel = new JLabel("Name: ");

        // Must be final to be accessible to the anonymous class.
        final JTextField participantText = new JTextField(20);

        JButton participantButton = new JButton("Add participant");
        JPanel participantPanel = new JPanel();
        participantPanel.setLayout(new BorderLayout());
        participantPanel.add(participantLabel, BorderLayout.WEST);
        participantPanel.add(participantText, BorderLayout.CENTER);
        participantPanel.add(participantButton, BorderLayout.EAST);

        // Defines an action listener to handle the addition of new participants
        ActionListener newParticipantListener = e -> {
            String name = participantText.getText().trim();
            if (!name.isEmpty() && !names.contains(name)) {
                names.add(name);
            }
            participantText.setText("");
            participantText.requestFocus();
        };

        // notify the action listener when participant Button is pressed
        participantButton.addActionListener(newParticipantListener);

        // notify the action listener when "Enter" key is hit
        participantText.addActionListener(newParticipantListener);

        JButton createButton = new JButton("Start chat");
        createButton.addActionListener(e -> {
            // Starts a new chat when the createButton is clicked.
            if (!names.isEmpty()) {
                startChatSession();
            }
        });

        // Adds the components we've created to the panel (and to the window).
        setLayout(new BorderLayout());
        add(participantPanel, BorderLayout.NORTH);
        add(createButton, BorderLayout.SOUTH);
        setVisible(true);
    }

    /**
     * Starts a new chat, opening one window for each participant. Invoked on
     * EDT.
     */
    private void startChatSession() {
        parentFrame.dispose();
        parentFrame = null;

        ChatServer server = new ChatServer();

        // Creates a new window for each participant.
        int pos = 0; // Distance of the next chat window from origin in pixels
        for (String name : names) {
            JFrame frame = new JFrame("Simple Chat Client -- " + name);
            ChatPanel chatPanel = new ChatPanel(server, name);

            frame.add(chatPanel);
            frame.addWindowListener(new WindowAdapter() {
                // Called to unsubscribe chat client from chat when its window
                // is closed
                @Override
                public void windowClosed(WindowEvent e) {
                    server.unsubscribe(chatPanel);
                    super.windowClosed(e);
                }
            });
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.pack();
            frame.setLocation(pos, pos);
            pos += CHAT_WINDOW_POS_OFFSET;

            frame.setResizable(true);
            frame.setVisible(true);
        }
    }
}
