package visual;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import model.Board;
import model.Dot;

public class DotsView extends JPanel implements Observer {

	private static final long serialVersionUID = 1L;
	JLabel[][] dotMatrix;

	public DotsView(ArrayList<Dot> dotBoardMatrix, JLayeredPane layers) {
		dotMatrix = new JLabel[1000][1000];
		for (Dot dot : dotBoardMatrix) {

			dotMatrix[dot.getBoardPosition().getX()][dot.getBoardPosition().getY()] = new JLabel();
			dotMatrix[dot.getBoardPosition().getX()][dot.getBoardPosition().getY()]
					.setIcon(ImageBinding.getImageIcon(dot));
			dotMatrix[dot.getBoardPosition().getX()][dot.getBoardPosition().getY()].setBounds(
					(dot.getBoardPosition().getX() * 10) - 10, (dot.getBoardPosition().getY() * 10) - 10 + 25, 30, 30);

			layers.add(dotMatrix[dot.getBoardPosition().getX()][dot.getBoardPosition().getY()], 5);
		}

	}

	@Override
	public void update(Observable observable, Object arg) {
		Dot dotRemoved = Board.getDotRemoved();

		if (Board.isPacmanEatNewDot()) {
			//System.out.println(dotMatrix[dotRemoved.getBoardPosition().getX()][dotRemoved.getBoardPosition().getY()]);
			//System.out.println(dotRemoved.getBoardPosition().getX());
			dotMatrix[dotRemoved.getBoardPosition().getX()][dotRemoved.getBoardPosition().getY()].setVisible(false);

		}
	}
}
