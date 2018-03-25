package model;

import java.util.ArrayList;

import controller.Game;
import model.Ghost.GhostState;
import sounds.Sounds;

public abstract class Eating {
	private static ArrayList<Dot> dots;

	//SE COMEN LOS DOTS Y SUPERDOTS EN FUNCION DE LA UBICACIÓN DEL PACMAN EN EL TABLERO (AUMENTAN LOS PUNTOS Y SE ACTIVA EL SUPERMODE)
		public static void eatingDot(Board board) {
			board.setPacmanEatNewDot(false);
			dots=board.getDots();

			for (Dot dot : dots) {

				if (((Dot) dot).getPosition().getBoardPosition().equals(Pacman.getPosition().getBoardPosition()))
				{
					Sounds.reproduceEatDot();
					Board.upScore(10,0);
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
		
		public static void eatingFruit()
		{
			if (Fruit.isEnableToEat()) {
				if(Fruit.getFruitPosition().equals(Pacman.getPosition()))
				{
					Board.upScore(200,0);
				}
			}
		}
		public static void eatingGhosts(ArrayList<Ghost> ghostsArray, Pacman pacman, Board board, ArrayList<Square> hellZone) {
			int ghostEated = 0;
			for (Ghost ghost : ghostsArray) {
				//eatingGhost = false;
				
				//SOLO LOS COMERA SI ESTAN VIVOS
				if (!ghost.getGhostState().equals(GhostState.DEATH))
				{
					if (pacman.getBoardPosition().equals(ghost.getBoardPosition())) 
					{
						ghostEated++;
						Sounds.reproduceEatGhost();
						//pacman.setPacmanState(Pacman.PacmanState.EATGHOST);
						ghost.setGhostState(Ghost.GhostState.EATED);
					//	eatingGhost = true;
						Board.upScore(50, ghostEated);
					}
				}
			}
			//setEatingGhost(false);
			
		}
}
