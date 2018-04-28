package model.creatures.ghostStates;

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
		if (ghost.getBoardPosition().equals(Board.getHellGatePosition())) {
			ghost.setKeyOfHell(!ghost.haveKeyOfHell());

			if (ghost.getState().toString()=="Death") {
				ghost.setState(new InHell());
			}else if (ghost.getState().toString()=="InHell")
			{
				ghost.setState(new Courageous());
			}
		}
	}
	
	public abstract String toString();


	
}
