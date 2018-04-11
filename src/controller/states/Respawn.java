package controller.states;

import controller.Game;
import model.Board;

public class Respawn extends GameState {

	@Override
	public void reorganize(Game game) {
		// TODO Auto-generated method stub

	}

	@Override
	public void run() {
		Board.respawnCreatures();
		Board.pacman.setPacmanState(model.creatures.Pacman.PacmanState.MOVE);
		Game.setFirstTime(true);
		Game.setState(new Normal());
	}

}
