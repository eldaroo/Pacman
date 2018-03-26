package visual;

import java.awt.HeadlessException;
import javax.swing.JFrame;
import java.awt.Font;
import java.awt.Color;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

public class GameView extends JFrame {

	private static final long serialVersionUID = 275143433150485185L;
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
