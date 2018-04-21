package controller.states;

import controller.Game;
import model.Board;

public class NextLevel extends GameState {

	@Override
	public void reorganize() {
		Board.upLevel();
		Board.setDots(Game.getDotStartMatrix());
		System.out.println("Level 2");
		Game.setState(new Respawn());
		Game.setFirstTime(true);
		Game.setRetard((Game.getRetard() *5) / 6);

	}

	@Override
	public void run() {


	}

}
