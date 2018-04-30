package controller.states;

import controller.Game;
import model.Board;

public class RespawnState extends GameState {

	@Override
	public void reorganize() {
		// TODO Auto-generated method stub

	}

	@Override
	public void run() {
		Game.getBoard().respawnCreatures();
		Game.getBoard().pacman.setPacmanState(model.creatures.Pacman.PacmanState.MOVE);

		Game.setState(new NormalState());
	}

}
