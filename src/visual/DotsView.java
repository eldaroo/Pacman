package visual;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import model.Board;
import model.Dot;

public class DotsView extends JPanel implements Observer {
	
	//DIBUJA LA MATRIZ CON TODOS LOS DOTS
	JLabel[][] dotMatrix = new JLabel[60][60];
	//SE DIBUJAN DOTS
	public DotsView(Dot[][] dots, JLayeredPane layers) {
		for (int i = 0; i < dots.length; i++) {
			for (int j = 0; j < dots.length; j++) {
				if (dots[i][j] != null) {
					dotMatrix[i][j] = new JLabel();

					dotMatrix[i][j].setIcon(ResourceBinding.getImageIcon(dots[i][j]));
					dotMatrix[i][j].setBounds((i * 10) - 10, (j * 10) - 10+25, 30, 30);

					layers.add(dotMatrix[i][j], 5);
				}
			}
		}
	}
	
	//RECIBE AVISO DEL DOT COMIDO Y LO DESAPARECE
	@Override
	public void update(Observable observable, Object arg) {
		Board board = (Board) observable;
		Dot dotRemoved = board.getDotRemoved();

		if (board.pacmanEatNewDot())
			dotMatrix[dotRemoved.getBoardPosition().getX()][dotRemoved.getBoardPosition().getY()].setVisible(false);
	}
}
