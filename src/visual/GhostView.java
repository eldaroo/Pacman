package visual;

import java.util.Observable;

import javax.swing.JLayeredPane;

import model.Creature;
import model.Direction;
import model.Ghost;
import model.Pacman;
import model.Position;

public class GhostView extends CreaturesView{

	public GhostView(Ghost obj, JLayeredPane layers) {
		super(obj, layers);
		this.setBounds((obj.getBoardPosition().getX() * 10) - 10, (obj.getBoardPosition().getY() * 10) - 10+25, 30, 30);
		setIcon(ResourceBinding.getGhostIcon(obj));
		layers.add(this, 6);

     }
	

	//@Override
	public void update(Observable observable, Object object) {

		Ghost creature = (Ghost) observable;
		setIcon(ResourceBinding.getGhostIcon(creature));

		Position boardPosition = creature.getBoardPosition();
		Direction direction = creature.getDirection();
		this.setBounds((boardPosition.getX() * 10) - 10, (boardPosition.getY() * 10) - 10+25, 30, 30);
		
	}
}
