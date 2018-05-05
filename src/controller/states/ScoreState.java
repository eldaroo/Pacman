package controller.states;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;

import controller.Game;
import visual.ScoreView;
import visual.ViewManager;

public class ScoreState extends GameState {

	@Override
	public void reorganize() throws InterruptedException, FileNotFoundException {
		// TODO Auto-generated method stub

	}

	@Override
	public void run()
			throws InterruptedException, FileNotFoundException, NullPointerException, IOException, ParseException {

		if (ScoreView.isPressBeginMenu()) {
			Game.setState(new LoadState());
			Game.setFirstTime(true);
			ViewManager.startBeginMenu();
		}
		if (ScoreView.isPressExitBtn()) {
			System.exit(0);
		}

	}

}
