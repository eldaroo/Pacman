package model.creatures.ghostStates;

import controller.Game;
import model.Board;
import model.creatures.Ghost;
import model.creatures.Pacman;

public abstract class GhostState {

	
	public abstract void determinatePotentialDirection(Ghost ghost);
	public abstract void determinateTarget(Ghost ghost, Pacman pacman) ;
	public abstract void singularityAction(Ghost ghost);
	public abstract void meetPacman(Ghost ghost, Pacman pacman) throws InterruptedException;
	public abstract void changeState(Ghost ghost);
	public abstract void move(Ghost ghost);
	
	public void checkGoingThroughHellGate(Ghost ghost)
	{
		if (ghost.getBoardPosition().equals(Game.getBoard().getHellGatePosition())) {
			ghost.setKeyOfHell(!ghost.haveKeyOfHell());
			ghost.setState(ghost.getState().nextState());
		}
	}
	
	protected GhostState nextState() {
		return this;		
	}
	
	public abstract String toString();


	
}
