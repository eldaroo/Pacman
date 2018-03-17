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

	private boolean eatingGhost = false;
	public static enum PacmanState {MOVE,EATDOT,EATSUPER,EATGHOST,DEATH};
	private static PacmanState pacmanState = PacmanState.MOVE;
	
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

	public void setPacmanState(PacmanState pacmanState) {
		Pacman.pacmanState = pacmanState;
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
				setPacmanState(PacmanState.EATDOT);
				board.setDotRemoved(dot);
				if (board.getDotRemoved().getSuper() == true) {
					Game.setGameState (GameState.SUPERMODE);
					board.setScore(board.getScore()+20);
					setPacmanState(PacmanState.EATSUPER);
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
				pacman.setPacmanState(Pacman.PacmanState.EATGHOST);
				ghost.setGhostState(Ghost.GhostState.EATED);
				eatingGhost = true;
			}
			}
		}
		setEatingGhost(false);
		
	}
	
	public PacmanState getPacmanState () {
		return pacmanState;
	}
	
}
