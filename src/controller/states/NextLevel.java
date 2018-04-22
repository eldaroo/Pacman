package controller.states;

import controller.Game;
import model.Board;
import sounds.Sounds;

public class NextLevel extends GameState {

	@Override
	public void reorganize() {
		Board.upLevel();
		Board.setDots(Game.getDotStartMatrix());
		System.out.println("Level " + Board.getLevel());
		Game.setState(new Respawn());
		Game.setRetard((Game.getRetard() *5) / 6);

	}

	@Override
	public void run() {


	}

}
