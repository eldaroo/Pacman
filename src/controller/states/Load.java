package controller.states;

import controller.Game;
import visual.BeginMenu;
import visual.GameView;

public class Load extends GameState {

	@Override
	public void reorganize(Game game) {
		// TODO Auto-generated method stub

	}

	@Override
	public void run() {
		
		if (Game.isFirstTime()) {
			BeginMenu beginMenu= Game.getBeginMenu();
			Game.getGameView().setContentPane(beginMenu);
			Game.setFirstTime(false);
		}
		if (BeginMenu.wasPressbtnBegin()) {
			Game.setState(new Normal());
			Game.setFirstTime(true);
			Game.getBeginMenu().dispose();
		} else if (BeginMenu.wasPressBtnRecovery()) {
			//Game.setState(new Recovery());
			Game.getBeginMenu().dispose();
			Game.setFirstTime (true);
		} else if (BeginMenu.wasPressBtnExit()) {
			System.exit(0);
		}
	}

}
