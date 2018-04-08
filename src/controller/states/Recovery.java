package controller.states;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.parser.ParseException;

import controller.Game;
import model.Board;
import model.Dot;

public abstract class Recovery extends GameState {

	@Override
	public void reorganize(Game game) {
		// TODO Auto-generated method stub

	}

	@Override
	public void run() throws FileNotFoundException, NullPointerException, IOException, ParseException {
		ArrayList<Dot> dotsArraySaved = Game.getSerializator().recover();
		Board.setDots(dotsArraySaved);
		//dotMatrix = Board.getDots();
		Game.setState(new Normal());
		Game.setFirstTime(true);
	}

}
