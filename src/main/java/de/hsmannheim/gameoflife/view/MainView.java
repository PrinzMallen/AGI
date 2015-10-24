package de.hsmannheim.gameoflife.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.JButton;

public class MainView {
	private JFrame mainFrame;
	private JButton[][] fieldArray;
	private static final int SIZEOFCELL = 10;

	public MainView(int widthOfField, int heightOfField) {
		setFieldSize(widthOfField, heightOfField);
		prepareGUI(widthOfField, heightOfField);
	}

	public static void main(String[] args) {
		MainView mainView = new MainView(9, 9);

	}

	private void setFieldSize(int widthOfField, int heightOfField) {
		fieldArray = new JButton[widthOfField][heightOfField];
		for (int i = 0; i < widthOfField; i++) {
			for (int j = 0; j < heightOfField; j++) {
				final JButton tempButton = new JButton();
				tempButton.setSize(SIZEOFCELL, SIZEOFCELL);
				tempButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if (tempButton.getBackground() == Color.GREEN) {
							tempButton.setBackground(Color.WHITE);
						} else
							tempButton.setBackground(Color.GREEN);
						tempButton.setOpaque(true);
					}
				});
				tempButton.setBackground(Color.WHITE);
				tempButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
				tempButton.setOpaque(true);
				fieldArray[i][j] = tempButton;
			}
		}
	}

	private void prepareGUI(int widthOfField, int heightOfField) {
		mainFrame = new JFrame("Game of Life");
		mainFrame.setSize(600, 600);// verhältniss von width/heigth
		mainFrame.setLayout(new GridLayout(widthOfField, heightOfField));
		for (int i = 0; i < fieldArray.length; i++) {
			for (int j = 0; j < fieldArray[i].length; j++) {
				mainFrame.add(fieldArray[i][j]);
			}
		}

		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});

		mainFrame.setVisible(true);
	}
}
