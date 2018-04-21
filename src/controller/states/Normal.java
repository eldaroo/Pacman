package controller.states;

import controller.Game;
import model.Board;
import model.creatures.ghostStates.Courageous;
import sounds.Sounds;
import visual.ViewManager;

public class Normal extends GameState {


	@Override
	public void reorganize() throws InterruptedException {
		ViewManager.startGameView();
		Game.getBoard().addObserver(ViewManager.getBoardView());
		Board.observePacman(ViewManager.getPacmanView());
		Game.getBoard().addObserver(ViewManager.getDotsView());
		Game.getBoard().update();
		Sounds.reproduceBeginning();

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
