package controller.states;

import controller.Game;
import model.Board;

public class Respawn extends GameState {

	@Override
	public void reorganize() {
		// TODO Auto-generated method stub

	}

	@Override
	public void run() {
		Board.respawnCreatures();
		Board.pacman.setPacmanState(model.creatures.Pacman.PacmanState.MOVE);

		Game.setState(new Normal());
	}

}
