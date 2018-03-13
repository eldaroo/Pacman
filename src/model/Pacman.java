package model;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.json.simple.JSONValue;

import sounds.Sounds;

public class Pacman extends Creature {

	boolean eatingGhost = false;

	public boolean isEatingGhost() {
		return eatingGhost;
	}

	public void setEatingGhost(boolean eatingGhost) {
		this.eatingGhost = eatingGhost;
	}

	public Pacman(String name, Square position) {
		super(name);
		this.position = position;
	}

	public void eatingGhosts(ArrayList<Ghost> ghostsArray, Pacman pacman, Board board, ArrayList<Square> hellZone) {
		eatingGhost = false;
		for (Ghost ghost : ghostsArray) {
			eatingGhost = false;
			if (pacman.getBoardPosition().equals(ghost.getBoardPosition())) {
				
				sounds.reproduceEatGhost();
				board.score += 50;
				ghost.isDead(hellZone);
				System.out.println("muere ghost");
				eatingGhost = true;
			}
	
		}
		
	}
	
}
