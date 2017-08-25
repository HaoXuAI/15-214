package edu.cmu.cs.cs214.rec07.gui;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;


import edu.cmu.cs.cs214.rec07.core.TicTacToe;

public class GamePanel extends JPanel {

	private TicTacToe ticTacToe;

	JButton [][] buttons= new JButton[3][3];
	ActionListener listener = e -> {
	};
	int turn;
	int count;


	public GamePanel(TicTacToe ticTacToe) {
		this.ticTacToe = ticTacToe;
		setLayout(new GridLayout(3,3));
		turn = 1;
		count= 0;
		for(int i = 0; i < 3; i++) {
			for(int j = 0;j < 3; j++) {
				buttons[i][j] = new JButton();
				buttons[i][j].putClientProperty("INDEX", new Integer[]{i,j});
				buttons[i][j].putClientProperty("OWNER", null);
				buttons[i][j].addActionListener(listener);
				add(buttons[i][j]);
			}
		}
	}

}
