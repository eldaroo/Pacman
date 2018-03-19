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
import model.GameState;

public class PostGameView extends JPanel {
	private JTextField textField;

	public PostGameView(GameView gameView) {

		setVisible(true);
		setSize(200, 200);
		setLocation(200, 200);
		
		JLabel lblGameOver = new JLabel(" G A M E   O V E R ");
		lblGameOver.setFont(new Font("Tekton Pro Cond", Font.PLAIN, 36));
		lblGameOver.setSize(131, 33);
		add(lblGameOver, BorderLayout.NORTH);

		JLabel lblNombre = new JLabel("Nombre: ");
		lblNombre.setSize(54, 87);
		add(lblNombre,BorderLayout.CENTER);

		textField = new JTextField();
		textField.setBorder(new EmptyBorder(0, 0, 0, 0));
		textField.setSize(112, 84);
		add(textField,BorderLayout.CENTER);
		textField.setColumns(10);
		
		JButton btnSalvarPartida = new JButton("SAVE SCORE");
		btnSalvarPartida.setSize(124, 23);
		add(btnSalvarPartida, BorderLayout.CENTER);

		JButton btnVolverAJugar = new JButton("TRY AGAIN");
		btnVolverAJugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Game.setGameState(GameState.NORMALMODE);
				Game.setFirstTime(true);
				//Game.restartGameVisual();
				Game.respawn();
			}
		});
		btnVolverAJugar.setSize(121, 23);
		add(btnVolverAJugar,BorderLayout.SOUTH);

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
