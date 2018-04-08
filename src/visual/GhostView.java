package visual;

import java.util.Observable;

import javax.swing.JLayeredPane;

import model.Position;
import model.creatures.Ghost;

public class GhostView extends CreaturesView{


	private static final long serialVersionUID = 6357805430570453814L;


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
		this.setBounds((boardPosition.getX() * 10) - 10, (boardPosition.getY() * 10) - 10+25, 30, 30);
		
	}
}
