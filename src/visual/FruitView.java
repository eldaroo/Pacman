package visual;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import model.Fruit;

public class FruitView extends JLabel implements Observer {

	private static final long serialVersionUID = 563167748030641838L;

	public FruitView(JLayeredPane layers) {
		this.setBounds((Fruit.getBoardPosition().getX() * 10) - 10, (Fruit.getBoardPosition().getY() * 10) - 10+25, 30, 30);
		layers.add(this, 8);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		Fruit fruit = (Fruit) o;
		repaint();
		if (Fruit.isEnableToEat()==true)
		{
			setVisible(true);
			setIcon(ResourceBinding.getFruitIcon(fruit));
			System.out.println("Nueva fruta");
		}else{
			setVisible(false);
			
		}
	}

}
