package model;

import java.io.*;
import java.util.*;
import org.json.simple.JSONValue;
import controller.Game;
import model.board.Dot;
import model.board.Fruit;
import model.creatures.*;
import model.creatures.ghostStates.*;
import model.squares.*;
import visual.CreaturesView;

public class Board extends Observable implements Serializable {

	private static final long serialVersionUID = -6472116531941544087L;

	// SQUARES
	private Square[][] board;
	private HellGate hellGate = new HellGate();
	private Square originalPacmanPosition;
	// ELEMENTOS VARIOS
	private ArrayList<Dot> dots;
	private Dot dotRemoved;
	private Fruit fruit;
	private Position fruitPosition;
	// CRIATURAS
	public Pacman pacman;
	private ArrayList<Ghost> ghostsArray;
	// VARIABLES
	int hellIndex = 0;
	Random randomHellZoneSquare = new Random();
	private boolean pacmanEatNewDot = false;
	private long lifes = 0;
	private long score = 0;
	private Long level = (long) 1;
	private int aux = 0;
	private ArrayList<Square> hellZone = new ArrayList<Square>();

	public Board() {
		BoardFactory.createGameMatrix(this);
		setOriginalPacmanPosition(board[27][43]);
		fruit = new Fruit(getFruitPosition());
	}

	public void setMatrix(Square[][] matrix) {
		this.board=matrix;		
	}

	public void makeDots() {
		dots = BoardFactory.createDots(board);
	}

	public void createPacman(String name, Square position) {
		pacman = new Pacman(name, position);
	}

	public void movePacman() {
		pacman.move();
	}

	// ******** METODOS DE FANTASMAS

	public void createGhosts(int value) {
		ghostsArray = new ArrayList<Ghost>();

		int intelligence = 5;
		for (int i = 0; i < value; i++) {

			hellIndex = IA.random(getHellZone().size());
			ghostsArray.add(new Ghost("ghost" + i, getHellZone().get(hellIndex), intelligence));
			intelligence += 1;
		}

	}

	public void respawnCreatures() {
		pacman.setPosition(originalPacmanPosition);

		for (Ghost ghost : ghostsArray) {
			ghost.setState(new InHell());
			ghost.setKeyOfHell(false);
			ghost.setHellTime(0);
			// UBICA A LOS GHOST EN POSICION AZAROZA DENTRO DEL HELL
			hellIndex = IA.random(getHellZone().size());
			ghost.setPosition(getHellZone().get(hellIndex));
		}
	}

	public void moveGhosts() throws InterruptedException {

		for (Ghost ghost : ghostsArray) {
			ghost.run(pacman);
		}
	}

	public void lookingForCreatures() throws InterruptedException {
		for (Ghost ghost : ghostsArray) {
			if (pacman.getBoardPosition().equals(ghost.getBoardPosition())) {
				ghost.getState().meetPacman(ghost, pacman);
			}
		}
	}

	public void setGhostStates(GhostState state) {

		for (Ghost ghost : ghostsArray) {
			ghost.state.changeState(ghost);
		}
	}

	// ----------- METODOS VARIOS ---------------

	public void upScore(int quantity, int multiplication){
		if (multiplication > 0) {
			score += (quantity * multiplication);
		} else {
			score += quantity;
		}
		checkUpLife();
	}

	public void upLevel() {
		level++;
	}

	public void checkUpLife(){
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

	public void subtractLife() {
		lifes--;
	}

	public void observePacman(CreaturesView pacmanView) {
		pacman.addObserver(pacmanView);
	}

	public Position getHellGatePosition() {
		return hellGate.getBoardPosition();
	}

	// EXPORTAR DE DATOS
	public ArrayList<Square> getHellZone() {
		return hellZone;
	}

	public long getScore() {
		return score;
	}

	public Pacman getPacman() {
		return pacman;
	}

	public void setScore(long score) {
		this.score = score;
	}

	public void setLevel(long level) {
		this.level = level;
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

	public void setDots(ArrayList<Dot> dots) {
		this.dots = dots;
	}

	public Position getFruitPosition() {
		return fruitPosition;
	}

	public void setFruitPosition(Position fruitPosition) {
		this.fruitPosition = fruitPosition;
	}

	public HellGate getHellGate() {
		return hellGate;
	}

	public void setHellGate(HellGate hellGate) {
		this.hellGate = hellGate;
	}

	public long getLifes() {
		return lifes;
	}

	public void setLifes(long lifes) {
		this.lifes = lifes;
	}

	public Fruit getFruit() {
		return fruit;
	}

	public long getLevel() {
		return level;
	}

	public void setOriginalPacmanPosition(Square board2) {
		this.originalPacmanPosition = board2;
	}

	public void setDotRemoved(Dot dotRemoved) {
		this.dotRemoved = dotRemoved;
	}

	public boolean isPacmanEatNewDot() {

		return pacmanEatNewDot;
	}

	public ArrayList<Ghost> getGhostsArray() {
		return ghostsArray;
	}

	public void setPacmanEatNewDot(boolean pacmanEatNewDot) {
		this.pacmanEatNewDot = pacmanEatNewDot;
	}

	public void update() {
		setChanged();
		notifyObservers();
	}

	public void writeJSONString(Writer out) throws IOException {
		LinkedHashMap<String, Long> obj = new LinkedHashMap<>();
		obj.put("score", score);
		obj.put("lifes", lifes);
		obj.put("level", level);

		JSONValue.writeJSONString(obj, out);
	}
}
