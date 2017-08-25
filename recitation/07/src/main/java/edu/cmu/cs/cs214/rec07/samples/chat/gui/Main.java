package edu.cmu.cs.cs214.rec07.samples.chat.gui;

import javax.swing.*;

/**
 * Simple chat application.
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            //add frame and set its closing operation
            JFrame frame = new JFrame("Start a SimpleChat session");
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            //add chat client, participants will be added by SimpleChatClient
            frame.add(new SimpleChatClient(frame));

            //display the JFrame
            frame.pack();
            frame.setResizable(false);
            frame.setVisible(true);
        });
    }
}
