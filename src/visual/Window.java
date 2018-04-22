package visual;

import java.awt.HeadlessException;
import javax.swing.JFrame;
import java.awt.Font;
import java.awt.Color;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

public class Window extends JFrame {

	private static final long serialVersionUID = 275143433150485185L;
	GraphicsDevice grafica=GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	
	
	public Window() throws HeadlessException {
		super("JUEGO");
		setUndecorated(true);
		setFont(new Font("Chiller", Font.BOLD, 24));
		setTitle("PACO-MAN");
		//setBackground(new Color(32, 178, 170));
		setBackground(Color.BLUE);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300,20,600,630);
		//setSize(600, 650);
		setLayout(null);
		//grafica.setFullScreenWindow(this);
		setVisible(true);
	}
	
	
	

	
}
