package model;

import java.io.IOException;
import java.io.Serializable;
import java.io.Writer;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Observable;
import java.util.Random;

import org.json.simple.JSONValue;

import sounds.Sounds;

public class Board extends Observable implements Serializable {

	private static final long serialVersionUID = -6472116531941544087L;

	private Square[][] board;
	private Dot[][] dots;
	private Dot dotRemoved;
	private boolean pacmanEatNewDot;
	private boolean superMode = false;
	private Sounds sounds = new Sounds();


	private ArrayList<Square> hellZone = new ArrayList<Square>();
	private ArrayList<Square> teleportList = new ArrayList<Square>();


	public long lifes = 3;
	public long score = 0;

	public Board(int[][] level1) {
		makeBoard(level1);
		makeDots(level1);
	}

	public GameState eatingDot(Pacman pacman, GameState gameState) {
		pacmanEatNewDot = false;
		if (dots[pacman.getBoardPosition().getX()][pacman.getBoardPosition().getY()] != null) {
			sounds.reproduceEatDot();
			score += 10;
			dotRemoved = dots[pacman.getBoardPosition().getX()][pacman.getBoardPosition().getY()];
			if (dotRemoved.superDot == true) {
				System.out.println("se comio un superDot!");
				gameState = GameState.SUPERMODE;
				score += 20;
			}
			dots[dotRemoved.getBoardPosition().getX()][dotRemoved.getBoardPosition().getY()] = null;
			pacmanEatNewDot = true;

		}
		setChanged();
		notifyObservers();
		return gameState;
	}

	//EXPORTAR DE DATOS
	public ArrayList<Square> getHellZone() {
		return hellZone;
	}
	public Square[][] getBoard() {
		return board;
	}
	public Dot getDotRemoved() {
		return dotRemoved;
	}
	public Dot[][] getDots() {
		return dots;
	}
	public boolean getSuperMode() {
		return superMode;
	}
	public boolean getPacmanEatNewDot() {
		return pacmanEatNewDot;
	}
	
	//ESTABLECE LOS CASILLEROS SIGUIENTES A LOS TELEPORT
	private void linkTeleports() {
		teleportList.get(0).setLeft(teleportList.get(5));
		teleportList.get(1).setUp(teleportList.get(2));
		teleportList.get(2).setDown(teleportList.get(1));
		teleportList.get(3).setUp(teleportList.get(4));
		teleportList.get(4).setDown(teleportList.get(3));
		teleportList.get(5).setRight(teleportList.get(0));
	}
	//CONSTRUYE EL TABLERO A PARTIR DE LA MATRIZ DE DATOS BASE
	private void makeBoard(int[][] levelBoard) {
		board = new Square[levelBoard.length][levelBoard.length];
		for (int i = 0; i < levelBoard.length; i++) {
			for (int j = 0; j < levelBoard.length; j++) {
				//SE ASIGNAN LOS TIPOS DE CASILLEROS
				switch (levelBoard[i][j]) {
				case 0:
					//WALL
					board[i][j] = new Wall();
					break;
				case 1:
					//PATH
					board[i][j] = new Path();
					break;
				case 2:
					//PATH NOT NAVEGABLE
					board[i][j] = new FalsePath();
					break;
				case 4:
					//PATH WITH DOT
					board[i][j] = new Path();
					break;
				case 5:
					//PATH WITH SUPER DOT
					board[i][j] = new Path();
					break;
				case 6:
					//HELL
					board[i][j] = new Hell();
					hellZone.add(board[i][j]);
					break;
				case 7:
					//HELL ENTRANCE
					board[i][j] = new HellGate();
					break;

				case 8:
					//HELL NOT NAVEGABLE
					board[i][j] = new FalseHell();
					break;
				case 9:
					//PATH WITH TELEPORT
					board[i][j] = new Path();
					teleportList.add(board[i][j]);
				}
				//ASIGNA AL CASILLERO EN UNA POSICION DEL TABLERO
				board[i][j].setBoardPosition(new Position(i, j));
			}
		}
		//ENLAZA CADA CASILLERO CON SU ADYACENTE
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {

				if (j + 1 < board.length) {
					board[i][j].setDown(board[i][j + 1]);
				}
				if (j - 1 >= 0) {
					board[i][j].setUp(board[i][j - 1]);
				}
				if (i - 1 >= 0) {
					board[i][j].setLeft(board[i - 1][j]);
				}
				if (i + 1 < board.length) {
					board[i][j].setRight(board[i + 1][j]);
				}
			}
		}
		//EJECUTA EL ENLACE DE CASILLEROS TELEPORT
		linkTeleports();
	}
	//CREA LOS DOTS Y SUPER DOTS Y LES ASIGNA UNA POSICIÓN EN EL TABLERO
	private void makeDots(int[][] levelDots) {
		dots = new Dot[levelDots.length][levelDots.length];
		for (int i = 0; i < levelDots.length; i++) {
			for (int j = 0; j < levelDots.length; j++) {
				switch (levelDots[i][j]) {
				case 4:
					dots[i][j] = new Dot();
					break;
				case 5:
					dots[i][j] = new SuperDot();
					break;
				}
				if (dots[i][j] != null)dots[i][j].position = board[i][j];
			}
		}

	}

	//SE COMEN LOS DOTS Y SUPERDOTS EN FUNCION DE LA UBICACIÓN DEL PACMAN EN EL TABLERO (AUMENTAN LOS PUNTOS Y SE ACTIVA EL SUPERMODE)
	public void eatingDot(Pacman pacman) {
		pacmanEatNewDot = false;
		if (dots[pacman.getBoardPosition().getX()][pacman.getBoardPosition().getY()] != null) {
			score += 10;
			dotRemoved = dots[pacman.getBoardPosition().getX()][pacman.getBoardPosition().getY()];
			if (dotRemoved.getSuper() == true) {
				superMode = true;
				score += 20;
			}
			dots[dotRemoved.getBoardPosition().getX()][dotRemoved.getBoardPosition().getY()] = null;
			pacmanEatNewDot = true;
		}
		//AVISA AL VISUAL SI HUBO MODIFICACIÓN DE DOTS EN EL TABLERO
		setChanged();
		notifyObservers();
	}
	
	//IMPORTAR DATOS
	public void setBoard(Square[][] board) {
		this.board = board;
	}
	public void setDotRemoved(Dot dotRemoved) {
		this.dotRemoved = dotRemoved;
	}
	public void setDots(Dot[][] dots) {
		this.dots = dots;
	}
	public void setSuperMode(boolean superMode) {
		this.superMode=superMode;
	}

	//LOS DATOS DEL BOARD QUE SE GUARDAN CUANDO SE EJECUTA toPersist
	public void writeJSONString(Writer out) throws IOException {
		LinkedHashMap obj = new LinkedHashMap<>();
		obj.put("score", score);
		obj.put("lifes", lifes);
		JSONValue.writeJSONString(obj, out);
	}
}
