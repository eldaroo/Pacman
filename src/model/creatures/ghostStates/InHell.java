package model.creatures.ghostStates;

import controller.Game;
import model.creatures.Ghost;
import model.creatures.IA;
import model.creatures.Pacman;

public class InHell extends GhostState{

	@Override
	public void determinateTarget(Ghost ghost, Pacman pacman) {
		
	}

	@Override
	public void determinatePotentialDirection(Ghost ghost) {
		ghost.setPotentialDirection(IA.randomDirection());
		
	}
	@Override
	public void singularityAction(Ghost ghost) {
		determinatePermanenceInHell(ghost);
		
	}
	private void determinatePermanenceInHell(Ghost ghost) {
		if (ghost.timeForOutOfHell()) {
			ghost.setTarget(Game.getBoard().getHellGatePosition());
			ghost.setPotentialDirection(IA.pathFinder(ghost.getIntelligence()));
			ghost.setKeyOfHell(true);
			
		} else
			ghost.upHellTime();		
	}

	@Override
	public void meetPacman(Ghost ghost, Pacman pacman) throws InterruptedException {
		
	}	
	
	protected GhostState nextState() {
		return new Courageous();		
	}
	

	@Override
	public String toString() {
		return "InHell";
	}

	@Override
	public void changeState(Ghost ghost) {
		
	}

	@Override
	public void move(Ghost ghost) {
		ghost.move();
		
	}


}
