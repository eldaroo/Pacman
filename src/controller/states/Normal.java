package controller.states;

import javax.swing.JOptionPane;

import controller.Game;
import model.Board;
import model.creatures.ghostStates.Courageous;
import sounds.Sounds;
import visual.BoardView;
import visual.ViewManager;

public class Normal extends GameState {


	@Override
	public void reorganize() throws InterruptedException {
		ViewManager.startGameView();
		Game.getBoard().addObserver(ViewManager.getBoardView());
		Board.observePacman(BoardView.getPacmanView());
		Game.getBoard().update();
		JOptionPane.showMessageDialog(null, "Move a Paco con las flechitas.\nCon la tecla 'P', hace una pausa.");
		Game.getSound().reproduceBeginning();

		Game.setFirstTime (false);
	}


	@Override
	public void run() throws InterruptedException {

			Thread.sleep(Game.getRetard());
			Game.upTime();
			Game.runCreatures();
			Game.getBoard().update();

	}
	
	@Override
	public String toString() {
		return "Normal";
	}

}
