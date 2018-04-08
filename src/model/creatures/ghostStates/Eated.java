package model.creatures.ghostStates;

import model.creatures.Ghost;
import model.creatures.IA;
import model.creatures.Pacman;

public class Eated extends GhostState {

	@Override
	public void determinateTarget(Ghost ghost, Pacman pacman) {
	}

	@Override
	public void determinatePotentialDirection(Ghost ghost) {
		
	}
	@Override
	public void particularAction(Ghost ghost) {
		ghost.setState(new Death());
		
	}

	@Override
	public void meetPacman(Ghost ghost, Pacman pacman) throws InterruptedException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Eated";
	}

	@Override
	public void setGhostState(Ghost ghost, GhostState ghostState) {
		// TODO Auto-generated method stub
		
	}
}
