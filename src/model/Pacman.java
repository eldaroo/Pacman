package model;



import java.util.ArrayList;

import controller.Game;
import model.Fruit.FruitType;
import model.Ghost.GhostState;
import sounds.Sounds;

public class Pacman extends Creature {

	private boolean eatingGhost = false;
	public static enum PacmanState {MOVE,EATGHOST,DEATH};
	private static PacmanState pacmanState = PacmanState.MOVE;
	private ArrayList<Dot> dots;
	

	public Pacman(String name, Square position) {
		super(name);
		this.position = position;
		setKeyOfHell(false);
	}
	
	public void run(Board board)
	{
		move();
		eatingDot(board);
		eatingFruit(Fruit.enableToEat, board);
	}

	//SE COMEN LOS DOTS Y SUPERDOTS EN FUNCION DE LA UBICACIÓN DEL PACMAN EN EL TABLERO (AUMENTAN LOS PUNTOS Y SE ACTIVA EL SUPERMODE)
	public void eatingDot(Board board) {
		board.setPacmanEatNewDot(false);
		dots=board.getDots();

		for (Dot dot : dots) {

			if (((Dot) dot).getPosition().getBoardPosition().equals(position.getBoardPosition()))
			{
				sounds.reproduceEatDot();
				board.upScore(10);
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
		
		//CHEQUEA SI TERMINO EL LEVEL
		if (dots.size()==0)
		{
			Game.setGameState(GameState.NEXTLEVEL);
			Game.setFirstTime(true);
		}
		//AVISA AL VISUAL SI HUBO MODIFICACIÓN DE DOTS EN EL TABLERO
		board.update();
		}
	
	public void eatingFruit(boolean enableToEat, Board board)
	{
		if (enableToEat) {
			board.upScore(200);

		}
	}
	public void eatingGhosts(ArrayList<Ghost> ghostsArray, Pacman pacman, Board board, ArrayList<Square> hellZone) {
		int ghostEated = 0;
		for (Ghost ghost : ghostsArray) {
			eatingGhost = false;
			
			//SOLO LOS COMERA SI ESTAN VIVOS
			if (!ghost.getGhostState().equals(GhostState.DEATH))
			{
				if (pacman.getBoardPosition().equals(ghost.getBoardPosition())) 
				{
					ghostEated++;
					sounds.reproduceEatGhost();
				//pacman.setPacmanState(Pacman.PacmanState.EATGHOST);
					ghost.setGhostState(Ghost.GhostState.EATED);
					eatingGhost = true;
				/*case 0:
					board.upScore(50);
					break;
*/
					
				}
			}
		}
		setEatingGhost(false);
		
	}
	
	public void death()
	{
		Board.lifes--;
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
	public PacmanState getPacmanState () {
		return pacmanState;
	}
	
}