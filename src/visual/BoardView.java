package visual;

import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import model.Square;

public class BoardView extends JFrame {


	public BoardView(Square[][] squareArray, JLayeredPane layers) throws HeadlessException {
		super("jUEGO");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 840);

		int capa=1000;
		JLabel[][] Fondo = new JLabel[20][20];
		for (int i = 0; i < squareArray.length; i++) {
			capa++;
			for (int j = 0; j < squareArray.length; j++) {
				capa++;
				Fondo[i][j] = new JLabel();
				Fondo[i][j].setIcon(ResourceBinding.getImageIcon(squareArray[i][j]));
				Fondo[i][j].setBounds(i * 40, j * 40, 40, 40);
				layers.add(Fondo[i][j], capa);
			}
		}
		setContentPane(layers);
		setVisible(true);
	}
}
