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
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//DIBUJA LA MATRIZ CON TODOS LOS DOTS
	JLabel[][] dotMatrix;
	//SE DIBUJAN DOTS
	public DotsView(ArrayList<Dot> dotBoardMatrix, JLayeredPane layers) {
		 dotMatrix = new JLabel[1000][1000];
		for (Dot dot : dotBoardMatrix) {
				
			dotMatrix[dot.getPosition().getBoardPosition().getX()][dot.getPosition().getBoardPosition().getY()]= new JLabel();
				dotMatrix[dot.getPosition().getBoardPosition().getX()][dot.getPosition().getBoardPosition().getY()].setIcon(ResourceBinding.getImageIcon(dot));
				dotMatrix[dot.getPosition().getBoardPosition().getX()][dot.getPosition().getBoardPosition().getY()].setBounds((dot.getPosition().getBoardPosition().getX() * 10) - 10, (dot.getPosition().getBoardPosition().getY() * 10) - 10+25, 30, 30);
				
				layers.add(dotMatrix[dot.getPosition().getBoardPosition().getX()][dot.getPosition().getBoardPosition().getY()], 5);
			}

		}
	
	//RECIBE AVISO DEL DOT COMIDO Y LO DESAPARECE
	@Override
	public void update(Observable observable, Object arg) {
		Board board = (Board) observable;
		Dot dotRemoved = board.getDotRemoved();

		if (Board.getPacmanEatNewDot()) 
		{
			dotMatrix[dotRemoved.getPosition().getBoardPosition().getX()][dotRemoved.getPosition().getBoardPosition().getY()].setVisible(false);

			
	}
}
}
