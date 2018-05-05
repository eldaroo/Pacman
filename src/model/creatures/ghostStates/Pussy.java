package model.creatures.ghostStates;

import controller.Game;
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
		ghost.setPotentialDirection(IA.pathFinder(2));
		
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
		return "Pussy";
	}

	@Override
	public void changeState(Ghost ghost) {
		
	}
	@Override

	public void move(Ghost ghost) {
		ghost.setAuxForRetarded(ghost.getAuxForRetarded() + 1);
		if (ghost.getAuxForRetarded() == 2) {
			ghost.move();
			ghost.setAuxForRetarded(0);
		}
	}


}
