package visual;

import java.awt.Dimension;
import java.util.Observable;
import javax.swing.JLayeredPane;

import model.Pacman;
import model.Position;
import model.Pacman.PacmanState;

public class PacmanView extends CreaturesView {

	private static final long serialVersionUID = 8740845001484130644L;
	private static Dimension dimension;
	
	public PacmanView(Pacman pacman, JLayeredPane layers) {
		super(pacman, layers);
		this.setBounds((pacman.getBoardPosition().getX() * 10) - 10, (pacman.getBoardPosition().getY() * 10) - 10 + 25,
				30, 30);
		setIcon(ResourceBinding.getPacmanIcon(pacman));
		// PARA OBTENER INFO DE DOTS
		// setIcon(ResourceBinding.getPacmanIcon(obj,board));
		layers.add(this, 6);

	}

	// @Override
	public void update(Observable observable, Object object) {

		Pacman pacman = (Pacman) observable;
		if (pacman.getPacmanState()==PacmanState.EATGHOST) {
			setIcon(ResourceBinding.getEatIcon(pacman));
			comboScream();
		} else
			setIcon(ResourceBinding.getPacmanIcon(pacman));
		Position boardPosition = pacman.getBoardPosition();
		this.setBounds((boardPosition.getX() * 10) - 10, (boardPosition.getY() * 10) - 10 + 25, 30, 30);
	}

	@SuppressWarnings("deprecation")
	private void comboScream() {
		dimension=this.size();
		
		while (dimension.getHeight()<500) {
			System.out.println(this.size());

			dimension.height++;
			dimension.width++;
			this.resize(dimension);
			this.repaint();
		}
	}
}