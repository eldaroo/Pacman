package visual;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import model.board.Fruit;

public class FruitView extends JLabel  {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FruitView(JLayeredPane layers) {
		this.setBounds((Fruit.getBoardPosition().getX() * 10) - 10, (Fruit.getBoardPosition().getY() * 10) - 10+25, 30, 30);
		layers.add(this, 8);
	}
	


}
