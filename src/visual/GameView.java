package visual;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import model.Board;
import model.Square;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JTextField;

import controller.Game;
import java.awt.Font;

public class GameView extends JFrame {
	
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
	

	
}
