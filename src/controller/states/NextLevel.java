package controller.states;

import controller.Game;
import model.Board;

public class NextLevel extends GameState {

	@Override
	public void reorganize(Game game) {
		// TODO Auto-generated method stub

	}

	@Override
	public void run() {
		Board.upLevel();
		Board.setDots(Game.getDotStartMatrix());
		Game.setGameState(new Respawn());
		Game.setRetard((Game.getRetard() *5) / 6);
	}

}
