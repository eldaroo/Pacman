package controller.states;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.parser.ParseException;

import controller.Game;
import model.Board;
import model.board.Dot;
import model.persistence.Serializator;
import sounds.Sounds;

public class RecoveryState extends GameState {

	@Override
	public void reorganize() {
		// TODO Auto-generated method stub

	}

	@Override
	public void run() throws FileNotFoundException, IOException, ParseException  {

		ArrayList<Dot> dotsArraySaved = Serializator.recover();
		Game.getBoard().setDots(dotsArraySaved);
		Game.setState(new NormalState());
		Game.setFirstTime(true);
	}

}
