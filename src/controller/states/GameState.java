package controller.states;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;

import controller.Game;

public abstract class GameState {

	public abstract void reorganize(Game game) throws InterruptedException;
	public abstract void run() throws InterruptedException, FileNotFoundException, NullPointerException, IOException, ParseException;
	
}
