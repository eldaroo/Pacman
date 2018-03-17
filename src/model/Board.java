package model;

import java.io.IOException;
import java.io.Serializable;
import java.io.Writer;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Observable;
import java.util.Random;

import org.json.simple.JSONValue;

import model.Square.Corner;
import sounds.Sounds;

public class Board extends Observable implements Serializable {

	private static final long serialVersionUID = -6472116531941544087L;

	private Square[][] board;
	private ArrayList <Dot> dots;
	private Dot dotRemoved;
	private boolean pacmanEatNewDot;
	private boolean superMode = false;
	private Sounds sounds = new Sounds();
	private HellGate hellGate = new HellGate();

	private ArrayList<Square> hellZone = new ArrayList<Square>();
	private ArrayList<Square> teleportList = new ArrayList<Square>();

	public long lifes = 3;
	public long score = 0;

	public Board(char[][] level1) {
		makeBoard(level1);
		makeDots(level1);
	}
	public void Update() {

		setChanged();
		notifyObservers();

	}

	// EXPORTAR DE DATOS
	public ArrayList<Square> getHellZone() {
		return hellZone;
	}
	public long getScore() {
		return score;
	}
	public void setScore(long score) {
		this.score = score;
	}

	public Square[][] getBoard() {
		return board;
	}

	public Dot getDotRemoved() {
		return dotRemoved;
	}
	public ArrayList<Dot> getDots() {

		return dots;
	}

	public boolean getSuperMode() {
		return superMode;
	}

	public boolean getPacmanEatNewDot() {
		return isPacmanEatNewDot();
	}

	// ESTABLECE LOS CASILLEROS SIGUIENTES A LOS TELEPORT
	private void linkTeleports() {
		teleportList.get(0).setLeft(teleportList.get(5));
		teleportList.get(1).setUp(teleportList.get(2));
		teleportList.get(2).setDown(teleportList.get(1));
		teleportList.get(3).setUp(teleportList.get(4));
		teleportList.get(4).setDown(teleportList.get(3));
		teleportList.get(5).setRight(teleportList.get(0));
	}

	// CONSTRUYE EL TABLERO A PARTIR DE LA MATRIZ DE DATOS BASE
	private void makeBoard(char[][] level1) {
		board = new Square[level1.length][level1.length];
		for (int i = 0; i < level1.length; i++) {
			for (int j = 0; j < level1.length; j++) {
				System.out.print(".");
				// SE ASIGNAN LOS TIPOS DE CASILLEROS
				switch (level1[i][j]) {
				case '\u0000':
					// WALL
					board[i][j] = new Wall();
					board[i][j].setCorner(Corner.CENTER);
					break;
				case '\u0001':
					// PATH
					board[i][j] = new Path();
					board[i][j].setCorner(Corner.CENTER);
					break;
				case '\u0002':
					// PATH NOT NAVEGABLE
					board[i][j] = new FalsePath();
					board[i][j].setCorner(Corner.CENTER);
					break;
				case '\u0003':
					// TELEPORT NOT NAVEGABLE
					board[i][j] = new FalseTeleport();
					board[i][j].setCorner(Corner.CENTER);
					break;
				case '\u0004':
					// PATH WITH DOT
					board[i][j] = new Path();
					board[i][j].setCorner(Corner.CENTER);
					break;
				case '\u0005':
					// PATH WITH SUPER DOT
					board[i][j] = new Path();
					board[i][j].setCorner(Corner.CENTER);
					break;
				case '\u0006':
					// HELL
					board[i][j] = new Hell();
					board[i][j].setCorner(Corner.CENTER);
					hellZone.add(board[i][j]);
					break;
				case '\u0007':
					// HELL ENTRANCE
					hellGate.setBoardPosition(new Position(i, j));
					board[i][j] = hellGate;
					board[i][j].setCorner(Corner.CENTER);
					break;
				case '\u0008':
					// HELL NOT NAVEGABLE
					board[i][j] = new FalseHell();
					board[i][j].setCorner(Corner.CENTER);
					break;
				case '\u0009':
					// PATH WITH TELEPORT
					board[i][j] = new Teleport();
					teleportList.add(board[i][j]);
					board[i][j].setCorner(Corner.CENTER);
					break;
					// CORNERS
				case 'a':
					// a PATHCORNER_NW
					board[i][j] = new FalsePath();
					board[i][j].setCorner(Corner.NE);
					break;
				case 'w':
					// w PATHCORNER_NE
					board[i][j] = new FalsePath();
					board[i][j].setCorner(Corner.NW);
					break;
				case 'x':
					// x PATHCORNER_SW
					board[i][j] = new FalsePath();
					board[i][j].setCorner(Corner.SE);
					break;
				case 'd':
					// d PATHCORNER_SE
					board[i][j] = new FalsePath();
					board[i][j].setCorner(Corner.SW);
					break;
				case 'q':
					// q WALLCORNER_SE
					board[i][j] = new Wall();
					board[i][j].setCorner(Corner.NE);
					break;
				case 'e':
					// e WALLCORNER_SW
					board[i][j] = new Wall();
					board[i][j].setCorner(Corner.NW);
					break;
				case 'z':
					// z WALLCORNER_NE
					board[i][j] = new Wall();
					board[i][j].setCorner(Corner.SE);
					break;
				case 'c':
					// c WALLCORNER_NW
					board[i][j] = new Wall();
					board[i][j].setCorner(Corner.SW);
					break;
				}

				// ASIGNA AL CASILLERO EN UNA POSICION DEL TABLERO
				if (!board.getClass().equals(HellGate.class))
					board[i][j].setBoardPosition(new Position(i, j));
			}
		}
		// ENLAZA CADA CASILLERO CON SU ADYACENTE
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
		// EJECUTA EL ENLACE DE CASILLEROS TELEPORT
		linkTeleports();
	}

	public HellGate getHellGate() {
		return hellGate;
	}

	public void setHellGate(HellGate hellGate) {
		this.hellGate = hellGate;
	}

	//CREA LOS DOTS Y SUPER DOTS Y LES ASIGNA UNA POSICIÓN EN EL TABLERO
	private void makeDots(char[][] level1) {
		Dot dot;
		SuperDot superDot;
		dots = new ArrayList <Dot>();
		for (int i = 0; i < level1.length; i++) {
			for (int j = 0; j < level1.length; j++) {
				switch (level1[i][j]) {
				case 4:
					dot=new Dot();
					dot.setPosition(board[i][j]);
					dots.add(dot);
					break;
				case 5:
					superDot=new SuperDot();
					superDot.setPosition(board[i][j]);
					dots.add( superDot);
					break;
				}
			}
		}

	}

public void update() {
		// AVISA AL VISUAL SI HUBO MODIFICACIÓN DE DOTS EN EL TABLERO
		setChanged();
		notifyObservers();
	}

	
	public void setBoard(Square[][] board) {
		this.board = board;
	}

	public void setDotRemoved(Dot dotRemoved) {
		this.dotRemoved = dotRemoved;
	}
	public void setDots(ArrayList<Dot> dots) {

		this.dots = dots;
	}

	public void setSuperMode(boolean superMode) {
		this.superMode = superMode;
	}

	// LOS DATOS DEL BOARD QUE SE GUARDAN CUANDO SE EJECUTA toPersist
	public void writeJSONString(Writer out) throws IOException {
		LinkedHashMap obj = new LinkedHashMap<>();
		obj.put("score", score);
		obj.put("lifes", lifes);
		JSONValue.writeJSONString(obj, out);
	}

	public boolean isPacmanEatNewDot() {
		return pacmanEatNewDot;
	}

	public void setPacmanEatNewDot(boolean pacmanEatNewDot) {
		this.pacmanEatNewDot = pacmanEatNewDot;
	}
}
