package controller.states;

import controller.Game;
import visual.BeginMenu;

public class Load extends GameState {

	@Override
	public void reorganize(Game game) {
		BeginMenu beginMenu = Game.getBeginMenu();
		Game.getGameView().setContentPane(beginMenu);
		Game.setFirstTime(false);
	}

	@Override
	public void run() {

		if (BeginMenu.wasPressbtnBegin()) {
			Game.setState(new Normal());
			Game.setFirstTime(true);
			Game.getBeginMenu().dispose();
		} else if (BeginMenu.wasPressBtnRecovery()) {
			Game.setState(new Recovery());
			Game.getBeginMenu().dispose();
			Game.setFirstTime(true);
		} else if (BeginMenu.wasPressBtnExit()) {
			System.exit(0);
		}
	}

}
