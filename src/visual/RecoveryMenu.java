package visual ;

import javax.swing.JPanel;

import org.json.simple.parser.ParseException;

import controller.Game;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import model.Direction;
import model.GameState;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JButton;

public class RecoveryMenu extends JPanel{
	public RecoveryMenu() {
		setBounds(200, 200, 600, 650);

		setLayout(null);
		setVisible(true);
		System.out.println("hola");
		
		JLabel lblSeleccioneUnaPartida = new JLabel("Seleccione una partida:");
		lblSeleccioneUnaPartida.setBounds(127, 54, 134, 19);
		add(lblSeleccioneUnaPartida);
		
		JList list = new JList();
		list.setBounds(93, 229, 1, 1);
		add(list);
		
		JList list_1 = new JList();
		list_1.setBounds(207, 98, 127, 191);
		add(list_1);
		
		JButton btnBack = new JButton("Atras");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					Game.setGameState(GameState.LOAD);
					
			}
		});
		
		btnBack.setBounds(112, 324, 89, 23);
		add(btnBack);
		
		JButton btnBegin = new JButton("Comenzar");
		btnBegin.setBounds(269, 324, 89, 23);
		add(btnBegin);
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
