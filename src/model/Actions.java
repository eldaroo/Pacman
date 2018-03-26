package model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

import controller.Game;
import model.Fruit.FruitType;
import model.Ghost.GhostState;
import sounds.Sounds;

public abstract class Actions{
	static ArrayList<Dot> dots;
	static int ghostSpeed=0;
	static int hellIndex = 0;
	static Random randomHellZoneSquare = new Random();
	

	//SE COMEN LOS DOTS Y SUPERDOTS EN FUNCION DE LA UBICACIÓN DEL PACMAN EN EL TABLERO (AUMENTAN LOS PUNTOS Y SE ACTIVA EL SUPERMODE)
		public static void eatingDot(Pacman pacman) {
			Board.setPacmanEatNewDot(false);
			dots=Board.getDots();

			for (Dot dot : dots) {

				if (((Dot) dot).getPosition().getBoardPosition().equals(pacman.getPosition().getBoardPosition()))
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
			
			//CHEQUEA SI TERMINO EL LEVEL
			if (dots.size()==0)
			{
				Game.setGameState(GameState.NEXTLEVEL);
				Game.setFirstTime(true);
			}
			}
		
		public static void eatingFruit(Pacman pacman)
		{
			if (Fruit.isEnableToEat()) {
				if(Fruit.getFruitPosition().equals(pacman.getPosition()))
				{
					Board.upScore(200,0);
				}
			}
		}
		public static void eatingGhosts(Pacman pacman) {
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
						ghost.setGhostState(GhostState.EATED);
					//	eatingGhost = true;
						Board.upScore(50, ghostEated);
					}
				}
			}
			//setEatingGhost(false);

		}
		
		
		//METODOS DE CREACION Y MOVILIZACION DE FANTASMAS
		
		public static void createGhosts(int value) {
			Game.setGhostsArray(new ArrayList <Ghost>());

			int intelligence = 1;
				for (int i = 0; i < value; i++) {
					
					hellIndex= IA.random(Board.getHellZone().size());
					Game.addGhost(new Ghost("ghost"+i, Board.getHellZone().get(hellIndex), intelligence));
					intelligence +=2;
				}
				
		}
		public static void moveGhosts() throws InterruptedException {
			
			for (Ghost ghost : Game.getGhostsArray()) {
					ghost.run(Game.getPacman());
			}
		}
		public static void respawnCreatures(Pacman pacman)
		{
			pacman.setPosition(Board.getOriginalPacmanPosition());

			for (Ghost ghost : Game.getGhostsArray()) {
				// UBICA A LOS GHOST EN POSICION AZAROZA DENTRO DEL HELL
				ghost.setKeyOfHell(true);
				hellIndex= IA.random(Board.getHellZone().size());
				ghost.setPosition( Board.getHellZone().get(hellIndex));
			}
		}
		public static void moveGhostsSlowed(int value) throws InterruptedException {
			//SUPERMODE: LOS GHOST SE MUEVEN MAS LENTOS
			ghostSpeed++;
			if(ghostSpeed==value) {
				moveGhosts();
				ghostSpeed=0;
			}		
		}
		
		//METODOS DE PACMAN

		public static void eatingPacman(Pacman pacman)
		{
			for (Ghost ghost : Game.getGhostsArray()) {
				ghost.eatingPacman(pacman);

			}
		}
		
		//FRUIT 	
		private static int fruitTime = 0;


		public static void updateFruit() {
			fruitTime++;
			if (Game.getTime() % 50 == 0) {
				determinateType();
				Fruit.setEnableToEat(true);
				fruitTime = 0;
			}
			if (fruitTime == 20) {
				Fruit.setEnableToEat(false);
			}
		}
			public static void determinateType() {


				Random random = new Random();
				int aux = random.nextInt(4);

				switch (aux) {
				case 0:
					Fruit.setFruitType ( FruitType.APPLE);
					break;
				case 1:
					Fruit.setFruitType ( FruitType.BANANNA);
					break;
				case 2:
					Fruit.setFruitType ( FruitType.ORANGE);
					break;
				case 3:
					Fruit.setFruitType ( FruitType.CHERRY);
					break;
				}

			}
		

}
