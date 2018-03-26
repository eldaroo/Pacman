package visual;

import java.util.Observable;
import javax.swing.JLayeredPane;

import model.Board;
import model.Pacman;
import model.Position;

public class PacmanView extends CreaturesView{

	private static final long serialVersionUID = 8740845001484130644L;


	public PacmanView(Pacman pacman, JLayeredPane layers) {
		super(pacman,layers);
		this.setBounds((pacman.getBoardPosition().getX() * 10) - 10, (pacman.getBoardPosition().getY() * 10) - 10+25, 30, 30);
		setIcon(ResourceBinding.getPacmanIcon(pacman));
		//PARA OBTENER INFO DE DOTS
		//setIcon(ResourceBinding.getPacmanIcon(obj,board));
		layers.add(this, 6);

     }
	

	//@Override
	public void update(Observable observable, Object object) {

		Pacman pacman = (Pacman) observable;
		setIcon(ResourceBinding.getPacmanIcon(pacman));
		Position boardPosition = pacman.getBoardPosition();
		this.setBounds((boardPosition.getX() * 10) - 10, (boardPosition.getY() * 10) - 10+25, 30, 30);
	}
}