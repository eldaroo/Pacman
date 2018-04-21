package controller.states;

import javax.swing.JOptionPane;

import controller.Game;
import model.Board;
import visual.ViewManager;

public class PostGame extends GameState {

	@Override
	public void reorganize() {
		JOptionPane.showMessageDialog(null, "la partida termino. Puntos: " + Board.getScore());
		//ViewManager.removeWindowContent(ViewManager.getLayers());
		ViewManager.startPostGameView();
		Game.setFirstTime(false);
		Game.getSound().reproducePostGame();
	}

	@Override
	public void run() {
		


	}

}
