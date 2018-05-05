package visual;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

import controller.Game;
import controller.states.NormalState;
import controller.states.RespawnState;

import java.awt.Color;

public class GameOverView extends PostGameView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GameOverView(Window window, ScoreView scoreView) {
		super(window, scoreView);
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setSize(200,150);
		setLocation(190, 220);
	}

	@Override
	public void generateParticularComponents() {
		JLabel lblGameOver = new JLabel(" G A M E   O V E R ");
		lblGameOver.setFont(new Font("Tekton Pro Cond", Font.PLAIN, 36));
		lblGameOver.setSize(500, 30);
		add(lblGameOver, BorderLayout.NORTH);		
		
		

		JButton btnAgain = new JButton("TRY AGAIN");
		btnAgain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Game.setState(new NormalState());
				Game.setFirstTime(true);
				Game.setState(new RespawnState());
			}
		});
		btnAgain.setSize(121, 23);
		add(btnAgain,BorderLayout.SOUTH);

		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnExit.setBounds(11, 235, 132, 23);
		add(btnExit,BorderLayout.SOUTH);
	}
	

}
