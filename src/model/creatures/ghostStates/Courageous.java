package model.creatures.ghostStates;

import model.creatures.Ghost;
import model.creatures.IA;
import model.creatures.Pacman;

public class Courageous extends GhostState {


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

	}

	@Override
	public void meetPacman(Ghost ghost, Pacman pacman) throws InterruptedException {
	ghost.eatPacman(pacman);
		
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Courageous";
	}

	@Override
	public void changeState(Ghost ghost) {
		ghost.setState(new Pussy());
	}



}
