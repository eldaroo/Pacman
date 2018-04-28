package visual;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JLabel;

public class GameOverView extends PostGameView {

	public GameOverView(Window window, ScoreView scoreView) {
		super(window, scoreView);
	}

	@Override
	public void generateParticularLabel() {
		JLabel lblGameOver = new JLabel(" G A M E   O V E R ");
		lblGameOver.setFont(new Font("Tekton Pro Cond", Font.PLAIN, 36));
		lblGameOver.setSize(131, 33);
		add(lblGameOver, BorderLayout.NORTH);		
	}
	

}
