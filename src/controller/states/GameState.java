package controller.states;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;

import controller.Game;
import model.Board;
import model.Pacman.PacmanState;

public abstract class GameState {

	public abstract void reorganize(Game game);
	public abstract void run() throws InterruptedException, FileNotFoundException, NullPointerException, IOException, ParseException;
	
	protected void runCreatures () throws InterruptedException {

		if (Board.pacman.getPacmanState()==PacmanState.EATGHOST) {
		    Thread.sleep(500);
			Board.pacman.setPacmanState(PacmanState.MOVE);
		}
		
			Board.lookingForCreatures();
			Board.moveGhosts();
			Board.lookingForCreatures();

		Board.movePacman();
		Board.lookingForDot();
		Board.lookingForFruit();

		Board.updateFruit();

	}
	
}
