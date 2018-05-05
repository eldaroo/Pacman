package visual;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import model.persistence.DataManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

public class ScoreView extends JPanel {

	private static final long serialVersionUID = 1725601919587341513L;
	public JTextArea scoreTextArea;
	public JButton btnBeginMenu;
	public JButton btnExitMenu;
	private static boolean pressBeginMenu = false;
	private static boolean pressExitBtn = false;

	public static boolean isPressExitBtn() {
		return pressExitBtn;
	}


	public static void setPressExitBtn(boolean pressExitBtn) {
		ScoreView.pressExitBtn = pressExitBtn;
	}


	public ScoreView() {
		setLayout(null);
		setVisible(true);
		setSize(600, 650);
		setLocation(250, 10);
		scoreTextArea = new JTextArea();
		scoreTextArea.setBounds(200, 100, 172, 238);

		add(scoreTextArea);
		JLabel lblMaximosPuntajes = new JLabel("Maximos puntajes:");
		lblMaximosPuntajes.setBounds(200, 50, 200, 21);
		add(lblMaximosPuntajes);

		btnBeginMenu = new JButton("MENU INICIO");
		btnBeginMenu.setBounds(200, 450, 200, 30);
		add(btnBeginMenu);
		btnBeginMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setPressBeginMenu(true);
			if (arg0.getSource() == btnExitMenu)
				setPressExitBtn(true);
		}
	});

		btnExitMenu = new JButton("SALIR");
		btnExitMenu.setBounds(200, 500, 200, 30);
		add(btnExitMenu);
		btnExitMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setPressExitBtn(true);
		}
	});
		
	}


	public void getScore() {
		DataManager.getScore(this);

	}

	public JTextArea getScoreTextArea() {
		return scoreTextArea;
	}

	public void setScoreTextArea(JTextArea scoreTextArea) {
		this.scoreTextArea = scoreTextArea;
	}

	public static boolean isPressBeginMenu() {
		return pressBeginMenu;
	}

	public static void setPressBeginMenu(boolean pressBeginMenu) {
		ScoreView.pressBeginMenu = pressBeginMenu;
	}
}
