package visual;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import model.Creature;
import model.Direction;
import model.Position;

public class CreaturesView extends JLabel implements Observer {

	private static ResourceBinding imagesResources;

	public CreaturesView(Creature obj, JLayeredPane layers) {

		this.setBounds(obj.getBoardPosition().getX() * 40, obj.getBoardPosition().getY() * 40, 40, 40);
		setIcon(imagesResources.getImageIcon(obj));
		layers.add(this, 6);

	}

	@Override
	public void update(Observable observable, Object object) {

		Creature creature = (Creature) observable;
		Position boardPosition = creature.getBoardPosition();
		Direction direction =creature.getDirection();
		this.setBounds(boardPosition.getX() * 40, boardPosition.getY() * 40, 40, 40);


		/*
		 * switch (direction) { case RIGHT: this.setBounds(this.getX()+40,
		 * this.getY(), 40, 40); if (creature.getPosition().getClass().getName()
		 * == "model.Teleport") this.setBounds(this.getX()-760, this.getY(), 40,
		 * 40); break; case LEFT: this.setBounds(this.getX()-40, this.getY(),
		 * 40, 40); if (creature.getPosition().getClass().getName() ==
		 * "model.Teleport") this.setBounds(this.getX()+760, this.getY(), 40,
		 * 40); break; case UP: this.setBounds(this.getX(), this.getY()-40, 40,
		 * 40); if (creature.getPosition().getClass().getName() ==
		 * "model.Teleport") this.setBounds(this.getX(), this.getY()+760, 40,
		 * 40); break; case DOWN: this.setBounds(this.getX(), this.getY()+40,
		 * 40, 40); if (creature.getPosition().getClass().getName() ==
		 * "model.Teleport") this.setBounds(this.getX(), this.getY()-760, 40,
		 * 40);
		 *
		 * break; }
		 */

	}
}
