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
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import de.hsmannheim.gameoflife.controller.GameController;
import de.hsmannheim.gameoflife.model.GridField;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;

public class MainView implements Observer {
	private JFrame mainFrame;
	private JButton[][] fieldArray;
	private JButton startStopButton;
	private static final int SIZEOFCELL = 10;
	private GameController gameController;
	private boolean isRunning = false;
	private JPanel gridPanel;

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
//				tempButton.setSize(SIZEOFCELL, SIZEOFCELL);
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
		JButton nextButton = new JButton("Next");
		nextButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!isRunning)
					gameController.doNextSingleStep();
			}
		});
		buttonPanel.add(nextButton);
		
		mainFrame = new JFrame("Game of Life");
		mainFrame.setSize(600, 600);// verhältniss von width/heigth
		gridPanel = new JPanel();
		initGrid(widthOfField, heightOfField);

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

	private void initGrid(int widthOfField, int heightOfField) {
		gridPanel.setLayout(new GridLayout(widthOfField, heightOfField));
		gridPanel.removeAll();
		for (int i = 0; i < fieldArray.length; i++) {
			for (int j = 0; j < fieldArray[i].length; j++) {
				gridPanel.add(fieldArray[i][j]);
			}
		}
		gridPanel.repaint();

		mainFrame.revalidate();
	}

	private void cellClicked(int x, int y) {
		if (!isRunning) {
			JButton cellButton = fieldArray[x][y];
			if (cellButton.getBackground() == Color.GREEN) {
				updateCellColor(x, y, 0);
				gameController.changeField(x, y, 0);
			} else {
				updateCellColor(x, y, 1);
				gameController.changeField(x, y, 1);
			}
			cellButton.setOpaque(true);
		}
	}

	private void updateCellColor(int x, int y, int value) {
		if (value == 0) {
			fieldArray[x][y].setBackground(Color.WHITE);
		} else {
			fieldArray[x][y].setBackground(Color.GREEN);
		}
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
		createDialog();
		gameController.stopGame();
		isRunning = false;
	}

	private void createDialog() {
		final JDialog newGameDialog = new JDialog(mainFrame, "Neues Spiel", true);
		JPanel centerPanel = new JPanel(new GridLayout(3, 2));
		centerPanel.add(new JLabel("Reihen"));
		final JTextField rowTF = new JTextField();
		centerPanel.add(rowTF);
		centerPanel.add(new JLabel("Spalten"));
		final JTextField collTF = new JTextField();
		centerPanel.add(collTF);
		centerPanel.add(new JLabel("Random"));
		final JCheckBox randomeCB = new JCheckBox();
		centerPanel.add(randomeCB);

		newGameDialog.setSize(200, 200);
		newGameDialog.setLayout(new BorderLayout());
		JButton okButton = new JButton("Ok");
		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int x = Integer.valueOf(rowTF.getText());
				int y = Integer.valueOf(collTF.getText());
				setFieldSize(x, y);
				initGrid(x, y);
				if (randomeCB.isSelected()) {
					gameController.generateRandomGameField(x, y);
				}else{
					gameController.generateGameField(x, y);
				}
				newGameDialog.setVisible(false);
			}
		});
		newGameDialog.add(centerPanel, BorderLayout.CENTER);
		newGameDialog.add(okButton, BorderLayout.PAGE_END);
		newGameDialog.setVisible(true);
	}

	private void createMenuBar() {

		JMenuBar menubar = new JMenuBar();
		ImageIcon icon = new ImageIcon("exit.png");

		JMenu menu = new JMenu("File");
		menu.setMnemonic(KeyEvent.VK_F);

		menu.add(genMenuItem("Neu", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				startNewGameClicked();
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
		GridField gridField = (GridField) arg;
		for (int y = 0; y < gridField.getFieldData().length; y++) {
			for (int x = 0; x < gridField.getFieldData()[0].length; x++) {
				int cellValue = gridField.getFieldData()[x][y];
				updateCellColor(x, y, cellValue);
			}
		}
	}
}
