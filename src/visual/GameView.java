package visual;

import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import model.Square;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JTextField;

public class GameView extends JFrame {

	public GameView() throws HeadlessException {
		super("JUEGO");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 650);
		setVisible(true);
	}
	public void createBoardView(Square[][] squareArray, JLayeredPane layers)
	{
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
}
