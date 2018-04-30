package controller.states;

import controller.Game;
import model.Board;

public class NextLevelState extends GameState {

	@Override
	public void reorganize() {
		Game.getBoard().upLevel();
		Game.getBoard().makeDots();
		System.out.println("Level " + Game.getBoard().getLevel());
		Game.setState(new RespawnState());
		Game.setRetard((Game.getRetard() *5) / 6);

	}

	@Override
	public void run() {


	}

}
