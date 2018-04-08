package controller.states;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;

import controller.Game;

public abstract class Recovery extends GameState {

	@Override
	public void reorganize(Game game) {
		// TODO Auto-generated method stub

	}

	@Override
	public void run() throws FileNotFoundException, IOException, ParseException  {
		Game.recovery();
	}

}
