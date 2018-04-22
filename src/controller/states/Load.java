package controller.states;

import java.io.FileNotFoundException;

import javax.swing.text.View;

import controller.Game;
import sounds.Sounds;
import visual.BeginMenu;
import visual.ViewManager;

public class Load extends GameState {

	@Override
	public void reorganize() throws FileNotFoundException, InterruptedException {
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
	@Override
	public String toString() {
		return "Load";
	}
}
