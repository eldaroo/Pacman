package controller.states;

import controller.Game;
import model.Board;

public class NextLevel extends GameState {

	@Override
	public void reorganize(Game game) {
		Board.upLevel();
		Board.setDots(Game.getDotStartMatrix());
		Game.initVisual();
		Game.setState(new Respawn());
		Game.setRetard((Game.getRetard() *5) / 6);

	}

	@Override
	public void run() {


	}

}
