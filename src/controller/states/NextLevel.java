package controller.states;

import controller.Game;
import model.Board;

public class NextLevel extends GameState {

	@Override
	public void reorganize() {
		Board.upLevel();
		Board.makeDots();
		System.out.println("Level " + Board.getLevel());
		Game.setState(new Respawn());
		Game.setRetard((Game.getRetard() *5) / 6);

	}

	@Override
	public void run() {


	}

}
