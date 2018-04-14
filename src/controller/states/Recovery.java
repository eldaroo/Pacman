package controller.states;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.parser.ParseException;

import controller.Game;
import model.Board;
import model.Dot;
import model.Serializator;

public class Recovery extends GameState {

	@Override
	public void reorganize(Game game) {
		// TODO Auto-generated method stub

	}

	@Override
	public void run() throws FileNotFoundException, IOException, ParseException  {

		ArrayList<Dot> dotsArraySaved = Serializator.recover();
		Board.setDots(dotsArraySaved);
		Game.setState(new Normal());
		Game.setFirstTime(true);
	}

}
