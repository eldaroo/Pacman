package visual;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import controller.Game;
import model.DataManager;

import javax.swing.JLabel;

public class ScoreView extends JPanel {

	private static final long serialVersionUID = 1725601919587341513L;
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
		lblMaximosPuntajes.setBounds(48, 25, 95, 21);
		add(lblMaximosPuntajes);
		
			}
	public void getScore()
	{
				DataManager.getScore(this);

	}
	public JTextArea getScoreTextArea() {
		return scoreTextArea;
	}
	public void setScoreTextArea(JTextArea scoreTextArea) {
		this.scoreTextArea = scoreTextArea;
	}
}
