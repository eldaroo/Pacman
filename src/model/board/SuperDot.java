package model.board;

import controller.Game;
import controller.states.GameState;
import controller.states.SuperState;
import model.creatures.Pacman;

public class SuperDot extends Dot {
	
	public void eatenInState(GameState gameState) {
		gameState.superDotEaten();
	}
	
	public void eaten(Pacman pacman) {
		super.eaten(pacman);		
		Game.setState (new SuperState());
		Game.setFirstTime(true);
		Game.getBoard().upScore(20, 0);
		pacman.resetGhostEated();
	}
}
