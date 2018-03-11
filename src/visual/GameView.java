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
import java.awt.Color;
import java.awt.Frame;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;

public class GameView extends JFrame {
	GraphicsDevice grafica=GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	
	
	public GameView() throws HeadlessException {
		super("JUEGO");
		setUndecorated(true);
		setFont(new Font("Chiller", Font.BOLD, 24));
		setTitle("PACO-MAN");
		setBackground(new Color(32, 178, 170));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300,20,600,650);
		setLayout(null);
		//setSize(600, 650);
		//grafica.setFullScreenWindow(this);
		this.setBackground(Color.BLUE);
		//pack();
		//show();
		setVisible(true);
	}
	

	
}
