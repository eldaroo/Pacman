package visual;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;

import model.Creature;
import model.Direction;
import model.Position;

public class CreatureView extends JLabel implements Observer {

	@Override
	public void update(Observable observable, Object object) {

		Creature creature = (Creature) observable;
		Position boardPosition = creature.getBoardPosition();
		Direction direction =creature.getDirection();
		switch (direction) {
		case RIGHT:
			this.setBounds(this.getX()+40, this.getY(), 40, 40);
			if (creature.getPosition().getClass().getName() == "model.Teleport")
				this.setBounds(this.getX()-760, this.getY(), 40, 40);
			break;
		case LEFT:
			this.setBounds(this.getX()-40, this.getY(), 40, 40);
			if (creature.getPosition().getClass().getName() == "model.Teleport")
				this.setBounds(this.getX()+760, this.getY(), 40, 40);
			break;
		case UP:
			this.setBounds(this.getX(), this.getY()-40, 40, 40);
			if (creature.getPosition().getClass().getName() == "model.Teleport")
				this.setBounds(this.getX(), this.getY()+760, 40, 40);
			break;
		case DOWN:
			this.setBounds(this.getX(), this.getY()+40, 40, 40);
			if (creature.getPosition().getClass().getName() == "model.Teleport")
				this.setBounds(this.getX(), this.getY()-760, 40, 40);

			break;
		}
	}
}
