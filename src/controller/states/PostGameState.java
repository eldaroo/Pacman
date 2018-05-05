package controller.states;

import javax.swing.JOptionPane;

import controller.Game;
import visual.ScoreView;
import visual.ViewManager;

public class PostGameState extends GameState {

	@Override
	public void reorganize() {
		JOptionPane.showMessageDialog(null, "la partida termino. Puntos: " + Game.getBoard().getScore());
		//ViewManager.removeWindowContent(ViewManager.getLayers());
		ViewManager.startPostGameView(Game.getWin());
		Game.setState(new ScoreState());
		
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
