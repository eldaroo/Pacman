package visual;

import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import model.Square;

public class BoardView extends JFrame {

	public BoardView(Square[][] squareArray, JLayeredPane layers) throws HeadlessException {
		super("JUEGO");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 840);

		int capa = 2000;
		JLabel[][] Fondo = new JLabel[60][60];
		for (int i = 0; i < squareArray.length; i++) {
			capa++;
			for (int j = 0; j < squareArray.length; j++) {
				capa++;
				Fondo[i][j] = new JLabel();
				Fondo[i][j].setIcon(ResourceBinding.getImageIcon(squareArray[i][j]));
				Fondo[i][j].setBounds(i * 10, j * 10, 10, 10);
				layers.add(Fondo[i][j], capa);
			}
		}
		setContentPane(layers);
		setVisible(true);
	}
}
