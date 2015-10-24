package de.hsmannheim.gameoflife.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.Border;

import de.hsmannheim.gameoflife.controller.GameController;
import de.hsmannheim.gameoflife.model.GridField;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class MainView implements Observer {
	private JFrame mainFrame;
	private JButton[][] fieldArray;
	private JButton startStopButton;
	private static final int SIZEOFCELL = 10;
	private GameController gameController;
	private boolean isRunning = false;

	public MainView(GameController gameController) {
		this.gameController = gameController;
		gameController.generateGameField();
		int widthOfField = gameController.getField().getFieldData().length;
		int heightOfField = gameController.getField().getFieldData()[0].length;
		setFieldSize(widthOfField, heightOfField);
		prepareGUI(widthOfField, heightOfField);
		createMenuBar();
	}

	private void setFieldSize(int widthOfField, int heightOfField) {
		fieldArray = new JButton[widthOfField][heightOfField];
		for (int y = 0; y < widthOfField; y++) {
			for (int x = 0; x < heightOfField; x++) {
				JButton tempButton = new JButton();
				tempButton.setSize(SIZEOFCELL, SIZEOFCELL);
				tempButton.addActionListener(new CellActionListener(x, y));
				tempButton.setBackground(Color.WHITE);
				tempButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
				tempButton.setOpaque(true);
				fieldArray[x][y] = tempButton;
			}
		}
	}

	private class CellActionListener implements ActionListener {
		private int x;
		private int y;

		public CellActionListener(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			cellClicked(x, y);
		}
	}

	private void prepareGUI(int widthOfField, int heightOfField) {
		JPanel buttonPanel = new JPanel(new FlowLayout());
		JPanel contentPanel = new JPanel(new BorderLayout());
		startStopButton = new JButton("Start");
		startStopButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				startStopClicked();
			}
		});
		buttonPanel.add(startStopButton);
		mainFrame = new JFrame("Game of Life");
		mainFrame.setSize(600, 600);// verhältniss von width/heigth
		JPanel gridPanel = new JPanel(new GridLayout(widthOfField, heightOfField));
		for (int i = 0; i < fieldArray.length; i++) {
			for (int j = 0; j < fieldArray[i].length; j++) {
				gridPanel.add(fieldArray[i][j]);
			}
		}

		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});
		contentPanel.add(gridPanel, BorderLayout.CENTER);
		contentPanel.add(buttonPanel, BorderLayout.PAGE_END);
		mainFrame.setContentPane(contentPanel);
		mainFrame.setVisible(true);
	}

	private void cellClicked(int x, int y) {
		JButton cellButton = fieldArray[x][y];
		if (cellButton.getBackground() == Color.GREEN) {
			cellButton.setBackground(Color.WHITE);
			gameController.changeField(x, y, 0);
		} else {
			cellButton.setBackground(Color.GREEN);
			gameController.changeField(x, y, 1);
		}
		cellButton.setOpaque(true);
	}
	
	private void startStopClicked() {
		isRunning = !isRunning;
		if (isRunning) {
			gameController.startGame();
			startStopButton.setText("Stopp");
		} else {
			gameController.stopGame();
			startStopButton.setText("Start");
		}
	}
	
	private void startNewGameClicked() {
		gameController.generateGameField();
	}

	private void createMenuBar() {

		JMenuBar menubar = new JMenuBar();
		ImageIcon icon = new ImageIcon("exit.png");

		JMenu menu = new JMenu("File");
		menu.setMnemonic(KeyEvent.VK_F);

		menu.add(genMenuItem("Start", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		}));

		menu.add(genMenuItem("Neu", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		}));

		menu.add(genMenuItem("Laden", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		}));

		menu.add(genMenuItem("Speichern", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		}));

		menu.add(genMenuItem("Exit", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		}));
		menubar.add(menu);
		mainFrame.setJMenuBar(menubar);
	}

	private JMenuItem genMenuItem(String title, ActionListener action) {
		JMenuItem eMenuItem = new JMenuItem(title);
		eMenuItem.addActionListener(action);
		return eMenuItem;
	}

	@Override
	public void update(Observable o, Object arg) {
		GridField gridField = (GridField)arg;
		for (int x = 0; x < gridField.getFieldData().length; x++) {
			for (int y = 0; y < fieldArray.length; y++) {
				
			}
		}
	}
}
