package controller.states;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;

import controller.Game;
import sounds.Sounds;

public abstract class GameState {

	public abstract void reorganize() throws InterruptedException, FileNotFoundException;
	public abstract void run() throws InterruptedException, FileNotFoundException, NullPointerException, IOException, ParseException;
	public void dotEaten() {}
	public void superDotEaten() {}
	
}
