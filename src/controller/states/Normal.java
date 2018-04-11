package controller.states;

import controller.Game;
import model.Board;
import model.creatures.ghostStates.Courageous;
import sounds.Sounds;

public class Normal extends GameState {


	@Override
	public void reorganize(Game game) throws InterruptedException {
		Game.initVisual();
		Sounds.reproduceBeginning();
		Game.setFirstTime (false);
	}


	@Override
	public void run() throws InterruptedException {
		Board.pacman.resetGhostEated();


		while (Game.getState().toString()=="Normal") {
			Game.upTime();
			Thread.sleep(Game.getRetard());
			Game.runCreatures();
			Game.getBoard().update();

		}
	}
	
	@Override
	public String toString() {
		return "Normal";
	}

}
