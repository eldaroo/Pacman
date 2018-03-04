package visual ;

import javax.swing.JPanel;

import org.json.simple.parser.ParseException;

import controller.Game;

import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import model.Direction;
import model.GameState;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JButton;

public class RecoveryMenu extends JInternalFrame{
	Game game = new Game();
	public RecoveryMenu(Game game, GameView gameView) {
		this.game= game;
		setBounds(200, 200, 600, 650);

		getContentPane().setLayout(null);
		setVisible(true);
		System.out.println("hola");
		
		JLabel lblSeleccioneUnaPartida = new JLabel("Tiene una partida guardada del dia ");
		lblSeleccioneUnaPartida.setBounds(144, 243, 233, 23);
		getContentPane().add(lblSeleccioneUnaPartida);
		
		JList list = new JList();
		list.setBounds(93, 229, 1, 1);
		getContentPane().add(list);
		
		JButton btnBack = new JButton("Atras");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					Game.setGameState(GameState.LOAD);
					Game.setFirstTime(true);
					dispose();
			}
		});
		
		btnBack.setBounds(112, 324, 89, 23);
		getContentPane().add(btnBack);
		
		JButton btnBegin = new JButton("Comenzar");
		btnBegin.setBounds(269, 324, 89, 23);
		getContentPane().add(btnBegin);
		btnBegin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Game.recovery();
				} catch (IOException | ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
}
