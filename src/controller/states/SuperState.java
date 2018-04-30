package controller.states;

import controller.Game;
import model.Board;
import model.creatures.ghostStates.Hurry;
import model.creatures.ghostStates.Pussy;
import sounds.Sounds;

public class SuperState extends GameState {

	@Override
	public void reorganize() throws InterruptedException {
		superDotEaten();
		Game.setFirstTime(false);

	}

	@Override
	public void run() throws InterruptedException {


			Thread.sleep(Game.getRetard());
			Game.upTime();
			Game.upSuperTime();
			Game.runCreatures();

			Game.getBoard().getDotRemoved().eatenInState(this);
			
			if (Game.getSuperTime() == 150) {
				Game.setState(new NormalState());
				Game.getBoard().pacman.resetGhostEated();
			}

			Game.getBoard().update();

	}
	
	public void dotEaten() {}

	public void superDotEaten() {
		Game.setSuperTime(0);
		Game.getBoard().setGhostStates(new Pussy());
	}

	@Override
	public String toString() {
		return "Super";
	}

}
