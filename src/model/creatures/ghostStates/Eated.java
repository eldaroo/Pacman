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
	public void singularityAction(Ghost ghost) {
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
	public void changeState(Ghost ghost) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void move(Ghost ghost) {
		ghost.move();
		
	}
}
