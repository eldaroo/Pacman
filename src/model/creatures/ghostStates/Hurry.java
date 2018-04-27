package model.creatures.ghostStates;

import controller.Game;
import model.Board;
import model.creatures.Ghost;
import model.creatures.IA;
import model.creatures.Pacman;

public class Hurry extends GhostState {

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

		if (Game.getSuperTime() == 150)
			ghost.setState(new Courageous());

	}

	@Override
	public void meetPacman(Ghost ghost, Pacman pacman) throws InterruptedException {
		pacman.eatGhost(ghost);

	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Hurry";
	}

	@Override
	public void changeState(Ghost ghost) {
		ghost.setState(new Courageous());
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
