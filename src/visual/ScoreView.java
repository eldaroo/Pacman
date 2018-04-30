package visual;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import controller.Game;
import model.persistence.DataManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

public class ScoreView extends JPanel {

	private static final long serialVersionUID = 1725601919587341513L;
	public JTextArea scoreTextArea;
	public JButton btnExit;
	public ScoreView() {
		setLayout(null);
		setVisible(true);
		setSize(600, 650);
		setLocation(250, 10);
		scoreTextArea = new JTextArea();
		scoreTextArea.setBounds(200, 200, 172, 238);

		add(scoreTextArea);
		JLabel lblMaximosPuntajes = new JLabel("Maximos puntajes:");
		lblMaximosPuntajes.setBounds(200, 500, 95, 21);
		add(lblMaximosPuntajes);
		
		btnExit = new JButton("EXIT");
		add(btnExit);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
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
}
