package model.creatures.ghostStates;

import model.Board;
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
			ghost.setTarget(Board.getHellGatePosition());
			ghost.setPotentialDirection(IA.pathFinder(ghost.getIntelligence()));
			ghost.setKeyOfHell(true);
			
		} else
			ghost.upHellTime();		
	}

	@Override
	public void meetPacman(Ghost ghost, Pacman pacman) throws InterruptedException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "InHell";
	}

	@Override
	public void changeState(Ghost ghost) {
		// TODO Auto-generated method stub
		
	}



}
