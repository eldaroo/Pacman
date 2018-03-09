package visual;

import javax.swing.JLayeredPane;

import model.Creature;
import model.Ghost;
import model.Pacman;

public class GhostView extends CreaturesView{

	public GhostView(Ghost obj, JLayeredPane layers) {
		super(obj, layers);
		this.setBounds((obj.getBoardPosition().getX() * 10) - 10, (obj.getBoardPosition().getY() * 10) - 10+25, 30, 30);
		setIcon(ResourceBinding.getGhostIcon(obj));
		layers.add(this, 6);

     }
}
