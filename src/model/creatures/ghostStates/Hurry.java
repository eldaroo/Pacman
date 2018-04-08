package model.creatures.ghostStates;

import controller.Game;
import model.Board;
import model.GameState;
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
		ghost.setPotentialDirection(IA.pathFinder(ghost.getIntelligence()));
		
	}
	@Override
	public void particularAction(Ghost ghost) {
		if(Game.getGameState().equals(GameState.NORMALMODE))
		{
			ghost.setState(new Courageous());
		}
		
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
	public void setGhostState(Ghost ghost, GhostState ghostState) {
		
	}
}
