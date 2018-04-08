package controller.states;

import controller.Game;
import model.Board;
import model.Ghost;
import model.Pacman;
import sounds.Sounds;
import visual.DotsView;
import visual.FruitView;
import visual.PacmanView;

public class Normal extends GameState {


	@Override
	public void reorganize(Game game) {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("unlikely-arg-type")
	@Override
	public void run() throws InterruptedException {
		Board.pacman.resetGhostEated();
		Board.setGhostState(Ghost.GhostState.COURAGEOUS);

		if (Game.isFirstTime()) {
			Game.initVisual();
			Sounds.reproduceBeginning();
			Game.setFirstTime (false);
		}

		//while (Game.getState().equals(Normal.class)) {
			while (!Game.isPaused()) {
			Game.setTime(Game.getTime() + 1);
			Thread.sleep(Game.getRetard());
			System.out.println("HOLA");
			runCreatures();
			Game.getBoard().update();

		}
	}

}
