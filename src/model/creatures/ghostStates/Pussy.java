package model.creatures.ghostStates;

import controller.Game;
import model.Board;
import model.creatures.Ghost;
import model.creatures.IA;
import model.creatures.Pacman;

public class Pussy extends GhostState{

	@Override
	public void determinateTarget(Ghost ghost, Pacman pacman) {
		ghost.setTarget(pacman.getBoardPosition());
		
	}

	@Override
	public void determinatePotentialDirection(Ghost ghost) {
		ghost.setPotentialDirection(IA.pathFinder(ghost.getIntelligence()));
		
	}
	@Override
	public void singularityAction(Ghost ghost) {
		if (Game.getSuperTime() >= 125)
			ghost.setState(new Hurry());			
	}

	@Override
	public void meetPacman(Ghost ghost, Pacman pacman) throws InterruptedException {
		pacman.eatGhost(ghost);
		
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Pussy";
	}

	@Override
	public void changeState(Ghost ghost) {
		// TODO Auto-generated method stub
		
	}


}
