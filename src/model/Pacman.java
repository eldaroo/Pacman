package model;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.json.simple.JSONValue;

import sounds.Sounds;

public class Pacman extends Creature {



	public Pacman(String name, Square position) {
		super(name);
		this.position = position;
		setKeyOfHell(false);
	}



	public void eatingGhosts(ArrayList<Ghost> ghostsArray, Pacman pacman, Board board, ArrayList<Square> hellZone) {
		for (Ghost ghost : ghostsArray) {
			if (pacman.getBoardPosition().equals(ghost.getBoardPosition())) {
				
				sounds.reproduceEatGhost();
				
				board.score += 50;
				ghost.setGhostState(Ghost.GhostState.DEATH);
				System.out.println("muere ghost");
			}
	
		}
		
	}
	
}
