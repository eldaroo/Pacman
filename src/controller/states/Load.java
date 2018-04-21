package controller.states;

import javax.swing.text.View;

import controller.Game;
import visual.BeginMenu;
import visual.ViewManager;

public class Load extends GameState {

	@Override
	public void reorganize() {
		ViewManager.startBeginMenu();
		Game.setFirstTime(false);

	}

	@Override
	public void run() {

		if (BeginMenu.wasPressbtnBegin()) {
			Game.setState(new Normal());
			Game.setFirstTime(true);
			ViewManager.getBeginMenu().dispose();
		} else if (BeginMenu.wasPressBtnRecovery()) {
			Game.setState(new Recovery());
			ViewManager.getBeginMenu().dispose();
			Game.setFirstTime(true);
		} else if (BeginMenu.wasPressBtnExit()) {
			System.exit(0);
		}
	}

}
