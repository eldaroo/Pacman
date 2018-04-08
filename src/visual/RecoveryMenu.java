package visual;

import org.json.simple.parser.ParseException;

import controller.Game;
import controller.states.Load;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JButton;

public class RecoveryMenu extends JInternalFrame {

	private static final long serialVersionUID = 3323633746761751043L;

	public RecoveryMenu(GameView gameView) {


		getContentPane().setLayout(null);
		setVisible(true);

		JLabel lblSelect = new JLabel("Tiene una partida ");
		lblSelect.setBounds(144, 243, 233, 23);
		getContentPane().add(lblSelect);

		JList<?> list = new JList<Object>();
		list.setBounds(93, 229, 1, 1);
		getContentPane().add(list);

		JButton btnBack = new JButton("Atras");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Game.setState(new Load());
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
