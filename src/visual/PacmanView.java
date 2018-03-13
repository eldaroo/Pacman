package visual;

import java.util.Observable;
import java.util.Observer;

import javax.annotation.Resources;
import javax.swing.JLayeredPane;

import model.Creature;
import model.Direction;
import model.Pacman;
import model.Position;

public class PacmanView extends CreaturesView{

	public PacmanView(Pacman obj, JLayeredPane layers) {
		super(obj, layers);
		this.setBounds((obj.getBoardPosition().getX() * 10) - 10, (obj.getBoardPosition().getY() * 10) - 10+25, 30, 30);
		setIcon(ResourceBinding.getPacmanIcon(obj));
		//PARA OBTENER INFO DE DOTS
		//setIcon(ResourceBinding.getPacmanIcon(obj,board));
		layers.add(this, 6);

     }
	

	//@Override
	public void update(Observable observable, Object object) {

		Creature creature = (Creature) observable;
		setIcon(ResourceBinding.getPacmanIcon(creature));
		
		Position boardPosition = creature.getBoardPosition();
		Direction direction = creature.getDirection();
		this.setBounds((boardPosition.getX() * 10) - 10, (boardPosition.getY() * 10) - 10+25, 30, 30);
	}
}
