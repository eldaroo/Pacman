package controller.states;

import controller.Game;
import model.Board;
import model.creatures.ghostStates.Hurry;
import model.creatures.ghostStates.Pussy;
import sounds.Sounds;

public class Super extends GameState {

	@Override
	public void reorganize() throws InterruptedException {
		Game.setSuperTime(0);
		Board.setGhostStates(new Pussy());
		Game.setFirstTime(false);

	}

	@Override
	public void run() throws InterruptedException {


			Thread.sleep(Game.getRetard());
			Game.upTime();
			Game.upSuperTime();
			Game.runCreatures();

			if (Board.getDotRemoved().getSuper())
			{
				Game.setSuperTime(0);
				Board.setGhostStates(new Pussy());
			}
			if (Game.getSuperTime() == 150) {
				Game.setState(new Normal());
				Board.pacman.resetGhostEated();
			}

			Game.getBoard().update();

	}

	@Override
	public String toString() {
		return "Super";
	}

}
