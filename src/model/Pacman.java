package model;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.json.simple.JSONValue;

import model.Ghost.GhostState;
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
		setKeyOfHell(false);
	}



	public void eatingGhosts(ArrayList<Ghost> ghostsArray, Pacman pacman, Board board, ArrayList<Square> hellZone) {
		for (Ghost ghost : ghostsArray) {
			//SOLO LOS COMERA SI ESTAN VIVOS
			if (!ghost.getGhostState().equals(GhostState.DEATH))
			{
			eatingGhost = false;
			//SI ESTAN PARADOS EN EL MISMO LUGAR LO COME
			if (pacman.getBoardPosition().equals(ghost.getBoardPosition())) {
				
				sounds.reproduceEatGhost();
				board.score += 50;
				ghost.setGhostState(Ghost.GhostState.EATED);
				System.out.println("muere ghost");
				eatingGhost = true;
			}
			}
		}
		
	}
	
}
