package visual;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import model.Square;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JTextField;

import controller.Game;

public class GameView extends JFrame implements ActionListener {

	JButton  btnSave ;
	public GameView() throws HeadlessException {
		super("JUEGO");
		setResizable(false);
		//setExtendedState(JFrame.MAXIMIZED_BOTH); 
		//setUndecorated(true);
		//PANTALLA COMPLETA!
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 650);
		getContentPane().setLayout(null);
		
		setVisible(true);
	}
	public void createBoardView(Square[][] squareArray, JLayeredPane layers)
	{
		btnSave = new JButton("Save");
		btnSave.setBounds(195, 0, 89, 23);
		layers.add(btnSave, 0);
		btnSave.addActionListener((ActionListener) this);
		
		int capa = 1000;
		JLabel[][] Fondo = new JLabel[60][60];
		for (int i = 0; i < squareArray.length; i++) {
			capa++;
			for (int j = 0; j < squareArray.length; j++) {
				capa++;
				Fondo[i][j] = new JLabel();
				Fondo[i][j].setIcon(ResourceBinding.getImageIcon(squareArray[i][j]));
				Fondo[i][j].setBounds(i * 10, (j * 10)+25, 10, 10);
				layers.add(Fondo[i][j], capa);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource()==btnSave)
		{
			Game.save();
		}
		
	}
}
