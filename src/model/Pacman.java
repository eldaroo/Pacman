package model;



import java.util.ArrayList;

import controller.Game;
import model.Ghost.GhostState;
import sounds.Sounds;

public class Pacman extends Creature {

	private boolean eatingGhost = false;
	public static enum PacmanState {MOVE,EATGHOST,DEATH};
	private static PacmanState pacmanState = PacmanState.MOVE;
	private ArrayList<Dot> dots;
	
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
	
	public void eatingDots()
	{
		//if (((Dot) dot).getPosition().getBoardPosition().equals(position.getBoardPosition()))
			
	}
	

	//SE COMEN LOS DOTS Y SUPERDOTS EN FUNCION DE LA UBICACIÓN DEL PACMAN EN EL TABLERO (AUMENTAN LOS PUNTOS Y SE ACTIVA EL SUPERMODE)
	public void eatingDot(Board board) {
		board.setPacmanEatNewDot(false);
		dots=board.getDots();

		for (Dot dot : dots) {

			if (((Dot) dot).getPosition().getBoardPosition().equals(position.getBoardPosition()))
			{
				sounds.reproduceEatDot();
				board.setScore(board.getScore()+ 10);
				board.setDotRemoved((Dot) dot);
				
				if (board.getDotRemoved().getSuper() == true) {
					Game.setGameState (GameState.SUPERMODE);
					board.setScore(board.getScore()+20);
				}
				board.setPacmanEatNewDot(true);

			}
		}
		
		dots.remove(board.getDotRemoved());
		board.setDots(dots);
		
		//CHEQUEA SI TERMINO EL JUEGO
		if (dots.size()==0)
		{
			Game.setGameState(GameState.POSTGAME);
			Game.setFirstTime(true);
		}
		//AVISA AL VISUAL SI HUBO MODIFICACIÓN DE DOTS EN EL TABLERO
		board.Update();
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
				//pacman.setPacmanState(Pacman.PacmanState.EATGHOST);
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
