package model;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.json.simple.JSONValue;

public class Pacman extends Creature {



	public Pacman(String name, Square position) {
		super(name);
		this.position = position;
	}

	public void eatingGhosts(ArrayList<Ghost> ghostsArray, Pacman pacman, Board board, ArrayList<Square> hellZone, Square target) {
		for (Ghost ghost : ghostsArray) {
			if (pacman.getBoardPosition().equals(ghost.getBoardPosition())) {
				board.score += 50;
				ghost.isDead(target, hellZone);
			}	
		}		
	}
	
}
