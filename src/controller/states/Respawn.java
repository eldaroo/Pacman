package controller.states;

import controller.Game;
import model.Board;
import model.Pacman.PacmanState;

public class Respawn extends GameState {

	@Override
	public void reorganize(Game game) {
		// TODO Auto-generated method stub

	}

	@Override
	public void run() {
		Board.respawnCreatures();
		Board.pacman.setPacmanState(PacmanState.MOVE);
		Game.setFirstTime(true);
		Game.setState(new Normal());
	}

}
