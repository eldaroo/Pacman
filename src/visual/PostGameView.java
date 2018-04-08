package visual;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.EmptyBorder;

import controller.Game;
import controller.states.Normal;

public class PostGameView extends JPanel {

	private static final long serialVersionUID = 34706378010734016L;
	private JTextField textField;

	public PostGameView(GameView gameView, PostGameView postGameView, ScoreView scoreView) {

		setVisible(true);
		setSize(324, 333);
		setLocation(200, 200);
		
		JLabel lblGameOver = new JLabel(" G A M E   O V E R ");
		lblGameOver.setFont(new Font("Tekton Pro Cond", Font.PLAIN, 36));
		lblGameOver.setSize(131, 33);
		add(lblGameOver, BorderLayout.NORTH);

		JLabel lblName = new JLabel("Nombre: ");
		lblName.setSize(54, 87);
		add(lblName,BorderLayout.CENTER);

		textField = new JTextField();
		textField.setBorder(new EmptyBorder(0, 0, 0, 0));
		textField.setSize(112, 84);
		textField.setEnabled(true);
		add(textField,BorderLayout.CENTER);
		textField.setColumns(10);
		
		JButton btnSave = new JButton("SAVE SCORE");
		btnSave.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				//SALVAMOS EL SCORE AL TOCAR EL BOTON
				Game.saveScore(textField.getText());
				try {
					//NOS DESHACEMOS DEL POSTGAMEVIEW
					this.finalize();
				} catch (Throwable e) {
					e.printStackTrace();
				}
				//AGREGAMOS AL SCOREVIEW A lA PANTALLA
				gameView.setContentPane(scoreView);
				
				//RECUPERAMOS EL LISTADO DE SCORE DE LA BASE DE DATOS
				scoreView.getScore();

				gameView.repaint();
			}
		});
		btnSave.setSize(124, 23);
		add(btnSave, BorderLayout.CENTER);

		JButton btnAgain = new JButton("TRY AGAIN");
		btnAgain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Game.setState(new Normal());
				Game.setFirstTime(true);
				//Game.restartGameVisual();
				Game.respawn();
			}
		});
		btnAgain.setSize(121, 23);
		add(btnAgain,BorderLayout.SOUTH);

		JButton btnTomarseElChori = new JButton("EXIT");
		btnTomarseElChori.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnTomarseElChori.setBounds(11, 235, 132, 23);
		add(btnTomarseElChori,BorderLayout.SOUTH);
	}
}
