package visual;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

import controller.Game;

import javax.swing.JLabel;

public class ScoreView extends JPanel {
	public JTextArea scoreTextArea;

	public ScoreView() {
		setLayout(null);
		setVisible(true);
		setSize(324, 333);
		setLocation(200, 200);
		scoreTextArea = new JTextArea();
		scoreTextArea.setBounds(149, 39, 172, 238);

		add(scoreTextArea);
		JLabel lblMaximosPuntajes = new JLabel("Maximos puntajes:");
		lblMaximosPuntajes.setBounds(48, 11, 95, 21);
		add(lblMaximosPuntajes);
		
			}
	public void getScore()
	{
		//LLAMAMOS AL METODO QUE MUESTRA EL SCORE
				Game.getScore();

	}
	public JTextArea getScoreTextArea() {
		return scoreTextArea;
	}
	public void setScoreTextArea(JTextArea scoreTextArea) {
		this.scoreTextArea = scoreTextArea;
	}
}