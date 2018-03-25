package model;

import java.io.IOException;
import java.io.Serializable;
import java.io.Writer;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Observable;

import org.json.simple.JSONValue;

import model.Square.Corner;

public class Board  extends Observable  implements Serializable {

	private static final long serialVersionUID = -6472116531941544087L;

	static private Square[][] board;
	static private ArrayList <Dot> dots;
	static private Dot dotRemoved;
	static private boolean pacmanEatNewDot;
	static private boolean superMode = false;
	static private HellGate hellGate = new HellGate();
	static private char[][]levelMatrix;
	static private ArrayList<Square> hellZone = new ArrayList<Square>();
	static private ArrayList<Square> teleportList = new ArrayList<Square>();


	private static long lifes = 3;
	private static long score = 0;
	private static Long level =(long) 1;

	static private Position fruitPosition;
    static private Square originalPacmanPosition;

	public static Square getOriginalPacmanPosition() {
		return originalPacmanPosition;
	}


	public static void setOriginalPacmanPosition(Square board2) {
		Board.originalPacmanPosition = board2;
	}


	public Board(char[][] level1) {
		levelMatrix=level1;
		makeBoard();
	}

	// CONSTRUYE EL TABLERO A PARTIR DE LA MATRIZ DE DATOS BASE
	private static void makeBoard() {
		board = new Square[levelMatrix.length][levelMatrix.length];
		for (int i = 0; i < levelMatrix.length; i++) {
			for (int j = 0; j < levelMatrix.length; j++) {
				// SE ASIGNAN LOS TIPOS DE CASILLEROS
				switch (levelMatrix[i][j]) {
				case '\u0000':
					// WALL
					board[i][j] = new Wall();
					break;
				case '\u0001':
					// PATH
					board[i][j] = new Path();
					break;
				case '\u0002':
					// PATH NOT NAVEGABLE
					board[i][j] = new FalsePath();
					break;
				case '\u0003':
					// TELEPORT NOT NAVEGABLE
					board[i][j] = new FalseTeleport();
					break;
				case '\u0004':
					// PATH WITH DOT
					board[i][j] = new Path();
					break;
				case '\u0005':
					// PATH WITH SUPER DOT
					board[i][j] = new Path();
					break;
				case '\u0006':
					// HELL
					board[i][j] = new Hell();
					hellZone.add(board[i][j]);
					break;
				case '\u0007':
					// HELL ENTRANCE
					hellGate.setBoardPosition(new Position(i, j));
					board[i][j] = hellGate;
					break;
				case '\u0008':
					// HELL NOT NAVEGABLE
					board[i][j] = new FalseHell();
					break;
				case '\u0009':
					// PATH WITH TELEPORT
					board[i][j] = new Teleport();
					teleportList.add(board[i][j]);
					break;
				case 'f':
					// PATH WITH FRUIT
					board[i][j] = new Path();
					fruitPosition = new Position(i,j);
					break;
					// CORNERS
				case 'a':
					// a PATHCORNER_NW
					board[i][j] = new FalsePath(Corner.NE);
					break;
				case 'w':
					// w PATHCORNER_NE
					board[i][j] = new FalsePath(Corner.NW);
					break;
				case 'x':
					// x PATHCORNER_SW
					board[i][j] = new FalsePath(Corner.SE);
					break;
				case 'd':
					// d PATHCORNER_SW
					board[i][j] = new FalsePath(Corner.SW);
					break;
				case 'q':
					// q WALLCORNER_SE
					board[i][j] = new Wall(Corner.NE);
					break;
				case 'e':
					// e WALLCORNER_NW
					board[i][j] = new Wall(Corner.NW);
					break;
				case 'z':
					// z WALLCORNER_SE
					board[i][j] = new Wall(Corner.SE);
					break;
				case 'c':
					// c WALLCORNER_NW
					board[i][j] = new Wall(Corner.SW);
					break;
				}

				// ASIGNA AL CASILLERO EN UNA POSICION DEL TABLERO
					board[i][j].setBoardPosition(new Position(i, j));
					setOriginalPacmanPosition(board[27][43]);
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
	//CREA LOS DOTS Y SUPER DOTS Y LES ASIGNA UNA POSICIÓN EN EL TABLERO
	public void makeDots() {
		Dot dot;
		SuperDot superDot;
		dots = new ArrayList <Dot>();
		for (int i = 0; i < levelMatrix.length; i++) {
			for (int j = 0; j < levelMatrix.length; j++) {
				switch (levelMatrix[i][j]) {
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

	// ESTABLECE LOS CASILLEROS SIGUIENTES A LOS TELEPORT
	private static void linkTeleports() {
		teleportList.get(0).setLeft(teleportList.get(5));
		teleportList.get(1).setUp(teleportList.get(2));
		teleportList.get(2).setDown(teleportList.get(1));
		teleportList.get(3).setUp(teleportList.get(4));
		teleportList.get(4).setDown(teleportList.get(3));
		teleportList.get(5).setRight(teleportList.get(0));
	}
	public static void upScore(int quantity, int multiplication)
	{
		if (multiplication>0) {
			score+=(quantity*multiplication);			
		} else {
			score+=quantity;
		}		
	}
	public static void upLevel()
	{
		level++;
	}
	public static void lostLife()
	{
		lifes--;
	}
	// EXPORTAR DE DATOS
	public static ArrayList<Square> getHellZone() {
		return hellZone;
	}
	public static long getScore() {
		return score;
	}
	public static void setScore(long score) {
		Board.score = score;
	}
	public static Square[][] getBoard() {
		return board;
	}
	public static Dot getDotRemoved() {
		return dotRemoved;
	}
	public static ArrayList<Dot> getDots() {
		return dots;
	}
	public static boolean getSuperMode() {
		return superMode;
	}
	public static boolean getPacmanEatNewDot() {
		return isPacmanEatNewDot();
	}
	public static Position getFruitPosition() {
		return fruitPosition;
	}
	public static void setFruitPosition(Position fruitPosition) {
		Board.fruitPosition = fruitPosition;
	}
	public static HellGate getHellGate() {
		return hellGate;
	}
	public static void setHellGate(HellGate hellGate) {
		Board.hellGate = hellGate;
	}
	public static long getLifes() {
		return lifes;
	}
	public static void setLifes(long lifes) {
		Board.lifes = lifes;
	}
	public long getLevel() {
		return level;
	}


	public static void setBoard(Square[][] board) {
		Board.board = board;

	}
	public static void setDotRemoved(Dot dotRemoved) {
		Board.dotRemoved = dotRemoved;
	}
	public static void setDots(ArrayList<Dot> dots) {
		Board.dots = dots;
	}
	public static void setSuperMode(boolean superMode) {
		Board.superMode = superMode;
	}
	public static boolean isPacmanEatNewDot() {
		return pacmanEatNewDot;
	}
	public static void setPacmanEatNewDot(boolean pacmanEatNewDot) {
		Board.pacmanEatNewDot = pacmanEatNewDot;
	}
	// AVISA AL VISUAL SI HUBO MODIFICACIÓN DE DOTS EN EL TABLERO
	public void update() {
		setChanged();
		notifyObservers();
	}
	
	// LOS DATOS DEL BOARD QUE SE GUARDAN CUANDO SE EJECUTA toPersist
	public void writeJSONString(Writer out) throws IOException {
		LinkedHashMap<String, Long> obj = new LinkedHashMap<>();
		obj.put("score", score);
		obj.put("lifes", lifes);
		obj.put("level", level);

		JSONValue.writeJSONString(obj, out);
	}
}

