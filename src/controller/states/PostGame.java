package controller.states;

import javax.swing.JOptionPane;

import controller.Game;
import model.Board;
import sounds.Sounds;
import visual.ViewManager;

public class PostGame extends GameState {

	@Override
	public void reorganize() {
		JOptionPane.showMessageDialog(null, "la partida termino. Puntos: " + Board.getScore());
		ViewManager.removeWindowContent(ViewManager.getLayers());
		ViewManager.startPostGameView();
		Game.setFirstTime(false);
	}

	@Override
	public void run() {
	
	}

	@Override
	public String toString() {
		return "PostGame";
	}
}
