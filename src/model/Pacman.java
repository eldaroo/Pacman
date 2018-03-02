package model;

import java.io.IOException;
import java.io.Writer;
import java.util.LinkedHashMap;

import org.json.simple.JSONValue;

public class Pacman extends Creature {



	public Pacman(String name, Square position) {
		super(name);
		this.position = position;
	}

	public void eatingGhosts(Ghost ghost, Pacman pacman, Board board) {
		if (pacman.getBoardPosition() == ghost.getBoardPosition()) {
			board.score += 50;
			ghost.isDead();
		}
		// creatures.remove(indexPacman); <<<CUANDO SEAN VARIAS CRIATURAS>>>

	}
	
}
