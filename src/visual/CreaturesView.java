package visual;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import model.Creature;
import model.Direction;
import model.Position;

public class CreaturesView extends JLabel implements Observer {

	public CreaturesView(Creature obj, JLayeredPane layers) {

		this.setBounds((obj.getBoardPosition().getX() * 10) - 10, (obj.getBoardPosition().getY() * 10) - 10, 30, 30);
		setIcon(ResourceBinding.getImageIcon(obj));
		layers.add(this, 6);

	}

	@Override
	public void update(Observable observable, Object object) {

		Creature creature = (Creature) observable;
		Position boardPosition = creature.getBoardPosition();
		Direction direction = creature.getDirection();
		this.setBounds((boardPosition.getX() * 10) - 10, (boardPosition.getY() * 10) - 10, 30, 30);

	}
}
