package visual;

import javax.annotation.Resources;
import javax.swing.JLayeredPane;

import model.Creature;
import model.Pacman;

public class PacmanView extends CreaturesView{

	public PacmanView(Pacman obj, JLayeredPane layers) {
		super(obj, layers);
		this.setBounds((obj.getBoardPosition().getX() * 10) - 10, (obj.getBoardPosition().getY() * 10) - 10+25, 30, 30);
		setIcon(ResourceBinding.getPacmanIcon(obj));
		layers.add(this, 6);

     }
}
