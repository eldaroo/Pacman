package model;

import java.util.ArrayList;
import java.util.Observable;

import controller.Game;
import model.Ghost.GhostState;
import sounds.Sounds;

public abstract class Eating{
	private static ArrayList<Dot> dots;

	//SE COMEN LOS DOTS Y SUPERDOTS EN FUNCION DE LA UBICACIÓN DEL PACMAN EN EL TABLERO (AUMENTAN LOS PUNTOS Y SE ACTIVA EL SUPERMODE)
		public static void eatingDot(Board board) {
			Board.setPacmanEatNewDot(false);
			dots=Board.getDots();

			for (Dot dot : dots) {

				if (((Dot) dot).getPosition().getBoardPosition().equals(Pacman.getPosition().getBoardPosition()))
				{
					Sounds.reproduceEatDot();
					Board.upScore(10,0);
					Board.setDotRemoved((Dot) dot);
					
					if (Board.getDotRemoved().getSuper() == true) {
						Game.setGameState (GameState.SUPERMODE);
						Board.upScore(20, 0);
					}
					Board.setPacmanEatNewDot(true);

				}
			}
			
			dots.remove(Board.getDotRemoved());
			Board.setDots(dots);
			board.update();
			//CHEQUEA SI TERMINO EL LEVEL
			if (dots.size()==0)
			{
				Game.setGameState(GameState.NEXTLEVEL);
				Game.setFirstTime(true);
			}
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
		public static void eatingGhosts( Pacman pacman) {
			int ghostEated = 0;
			for (Ghost ghost : Game.getGhostsArray()) {
				//eatingGhost = false;
				
				//SOLO LOS COMERA SI ESTAN VIVOS
				if (!ghost.getGhostState().equals(GhostState.DEATH))
				{
					if (pacman.getBoardPosition().equals(ghost.getBoardPosition())) 
					{
						ghostEated++;
						Sounds.reproduceEatGhost();
						ghost.setGhostState(Ghost.GhostState.EATED);
					//	eatingGhost = true;
						Board.upScore(50, ghostEated);
					}
				}
			}
			//setEatingGhost(false);

		}

}
