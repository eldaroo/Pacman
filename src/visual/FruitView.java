package visual;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import model.Fruit;

public class FruitView extends JLabel implements Observer {

	
	public FruitView(Fruit obj, JLayeredPane layers) {
		this.setBounds((obj.getFruitPosition().getX() * 10) - 10, (obj.getFruitPosition().getY() * 10) - 10+25, 30, 30);
		layers.add(this, 8);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		Fruit fruit = (Fruit) o;
		repaint();
		setIcon(ResourceBinding.getFruitIcon(fruit));
		System.out.println("Nueva fruta");
	}

}
