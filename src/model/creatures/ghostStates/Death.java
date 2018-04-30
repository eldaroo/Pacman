package model.creatures.ghostStates;

import controller.Game;
import model.Board;
import model.creatures.Ghost;
import model.creatures.IA;
import model.creatures.Pacman;

public class Death extends GhostState {


	@Override
	public void determinateTarget(Ghost ghost, Pacman pacman) {
		ghost.setTarget(Game.getBoard().getHellGatePosition());
		
	}

	@Override
	public void determinatePotentialDirection(Ghost ghost) {
		ghost.setPotentialDirection(IA.pathFinder(9));
		
		
	}
	@Override
	public void singularityAction(Ghost ghost) {
		ghost.setKeyOfHell(true);
	}

	@Override
	public void meetPacman(Ghost ghost, Pacman pacman) throws InterruptedException {
		// TODO Auto-generated method stub
		
	}

	protected GhostState nextState() {
		return new InHell();		
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Death";
	}

	@Override
	public void changeState(Ghost ghost) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void move(Ghost ghost) {
		ghost.move();
		
	}

}
