package model;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.json.simple.JSONValue;

import controller.Game;
import model.Ghost.GhostState;
import sounds.Sounds;

public class Pacman extends Creature {

	boolean eatingGhost = false;
	public void run(Board board)
	{
		move();
		eatingDot(board);
	}
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

	//SE COMEN LOS DOTS Y SUPERDOTS EN FUNCION DE LA UBICACIÓN DEL PACMAN EN EL TABLERO (AUMENTAN LOS PUNTOS Y SE ACTIVA EL SUPERMODE)
	public void eatingDot(Board board) {
		board.setPacmanEatNewDot(false);
		for (Dot dot : board.getDots()) {
		if (dot.getPosition().getBoardPosition().equals(position.getBoardPosition()))
			{
				sounds.reproduceEatDot();
				board.setScore(board.getScore()+ 10);

				board.setDotRemoved(dot);
				if (board.getDotRemoved().getSuper() == true) {
					Game.setGameState (GameState.SUPERMODE);
					board.setScore(board.getScore()+20);
				}
				dot = null;
				board.setPacmanEatNewDot(true);
				//AVISA AL VISUAL SI HUBO MODIFICACIÓN DE DOTS EN EL TABLERO
				board.Update();
		}
		

		}

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
				eatingGhost = true;
			}
			}
		}
		
	}
	
}
