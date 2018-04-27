package model;

import java.io.*;
import java.util.*;
import org.json.simple.JSONValue;
import controller.Game;
import controller.states.NextLevel;
import model.creatures.*;
import model.creatures.ghostStates.*;
import model.squares.*;
import visual.CreaturesView;

public class Board extends Observable implements Serializable {

	private static final long serialVersionUID = -6472116531941544087L;

	// SQUARES
	static private Square[][] board;
	static private HellGate hellGate = new HellGate();
	static private Square originalPacmanPosition;

	// ELEMENTOS VARIOS
	static private ArrayList<Dot> dots;
	static private Dot dotRemoved;
	private static Fruit fruit;
	static private Position fruitPosition;

	// CRIATURAS
	public static Pacman pacman;
	public static Pacman getPacman() {
		return pacman;
	}

	private static ArrayList<Ghost> ghostsArray;

	// VARIABLES
	static int hellIndex = 0;
	static Random randomHellZoneSquare = new Random();
	static private boolean pacmanEatNewDot = false;
	static private boolean superMode = false;
	private static long lifes = 3;
	private static long score = 0;
	private static Long level = (long) 1;
	private static int aux = 0;

	public Board() {
		makeBoard();
		fruit = new Fruit(getFruitPosition());

	}

	public static void makeBoard() {
		board = BoardConfiguration.makeBoard();
	}
	public static void makeDots() {
		dots = BoardConfiguration.makeDots();
	}

	public static void createPacman(String name, Square position) {
		pacman = new Pacman(name, position);
	}

	public static void movePacman() {
		pacman.move();
	}

	public static void lookingForDotAndFruit() throws InterruptedException {
		pacman.lookingForDots();
		pacman.lookingForFruit();
		}
	

	// ******** METODOS DE FANTASMAS

	public static void createGhosts(int value) {
		ghostsArray = new ArrayList<Ghost>();

		int intelligence = 5;
		for (int i = 0; i < value; i++) {

			hellIndex = IA.random(Board.getHellZone().size());
			ghostsArray.add(new Ghost("ghost" + i, Board.getHellZone().get(hellIndex), intelligence));
			intelligence += 1;
		}

	}

	public static void respawnCreatures() {
		pacman.setPosition(Board.getOriginalPacmanPosition());

		for (Ghost ghost : ghostsArray) {
			ghost.setState(new InHell());
			ghost.setKeyOfHell(false);
			ghost.setHellTime(0);
			// UBICA A LOS GHOST EN POSICION AZAROZA DENTRO DEL HELL
			hellIndex = IA.random(Board.getHellZone().size());
			ghost.setPosition(Board.getHellZone().get(hellIndex));
		}
	}

	public static void moveGhosts() throws InterruptedException {

		for (Ghost ghost : ghostsArray) {

				ghost.run(pacman);
		}
	}

	public static void lookingForCreatures() throws InterruptedException {
		for (Ghost ghost : ghostsArray) {
			if (pacman.getBoardPosition().equals(ghost.getBoardPosition())) {

				ghost.getState().meetPacman(ghost, pacman);

			}
		}
	}

	public static void setGhostStates(GhostState state) {

		for (Ghost ghost : ghostsArray) {
			ghost.state.changeState(ghost);
		}
	}

	

	// ----------- METODOS VARIOS ---------------

	public static void upScore(int quantity, int multiplication) throws InterruptedException {
		if (multiplication > 0) {
			score += (quantity * multiplication);
		} else {
			score += quantity;
		}
		checkUpLife();
	}

	public static void upLevel() {
		level++;
	}

	public static void checkUpLife() throws InterruptedException {
		switch (aux) {
		case 0:
			if (score > 1000) {
				lifes++;
				aux++;
				Game.getSound().reproduceLifeUp();
			}
			break;
		case 1:
			if (score > 5000) {
				lifes++;
				aux++;
				Game.getSound().reproduceLifeUp();
			}
			break;
		case 2:
			if (score > 10000) {
				lifes++;
				Game.getSound().reproduceLifeUp();
			}
			break;
		default:
			break;
		}
	}

	public static void subtractLife() {
		lifes--;
	}

	public static void observePacman(CreaturesView pacmanView) {
		pacman.addObserver(pacmanView);
	}



	public static Position getHellGatePosition() {
		return BoardConfiguration.getHellGatePosition();
	}

	// EXPORTAR DE DATOS
	public static ArrayList<Square> getHellZone() {
		return BoardConfiguration.getHellZone();
	}

	public static long getScore() {
		return score;
	}

	public static void setScore(long score) {
		Board.score = score;
	}
	public static void setLevel(long level) {
		Board.level = level;
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

	public static void setDots(ArrayList<Dot> dots)
	{
		Board.dots = dots;
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
	public static Fruit getFruit() {
		return fruit;
	}

	public static long getLevel() {
		return level;
	}

	public static Square getOriginalPacmanPosition() {
		return originalPacmanPosition;
	}

	public static void setOriginalPacmanPosition(Square board2) {
		Board.originalPacmanPosition = board2;
	}

	public static void setBoard(Square[][] board) {
		Board.board = board;

	}

	public static void setDotRemoved(Dot dotRemoved) {
		Board.dotRemoved = dotRemoved;

	}
	public static void setSuperMode(boolean superMode) {
		Board.superMode = superMode;
	}

	public static boolean isPacmanEatNewDot() {

		return pacmanEatNewDot;
	}

	public static ArrayList<Ghost> getGhostsArray() {
		return ghostsArray;
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
