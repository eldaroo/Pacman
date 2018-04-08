package controller.states;

import controller.Game;
import model.Board;
import model.Ghost;
import model.Ghost.GhostState;

public class Super extends GameState {

	@Override
	public void reorganize(Game game) {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("unlikely-arg-type")
	@Override
	public void run() throws InterruptedException {
		Game.setSuperTime(0);
		Board.setGhostState(Ghost.GhostState.PUSSY);

		while (Game.getState().equals(Super.class)) {

			Thread.sleep(Game.getRetard());
			Game.setTime(Game.getTime()+1);

			runCreatures();

			Game.setSuperTime(Game.getSuperTime() + 1);
			if (Board.getDotRemoved().getSuper()) {
				Game.setSuperTime(0);
			}
			if (Game.getSuperTime() == 150) {
				Game.setState (new Normal());
			}
			if (Game.getSuperTime() >= 125)
				Board.setGhostState(GhostState.HURRY);

			Game.getBoard().update();

		}
	}

}
