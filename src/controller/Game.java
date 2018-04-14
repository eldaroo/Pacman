package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JLayeredPane;
import org.json.simple.parser.ParseException;
import controller.states.GameState;
import controller.states.Load;
import controller.states.Pause;
import controller.states.PostGame;
import model.Board;
import model.Direction;
import model.Dot;
import model.squares.Square;
import sounds.Sounds;
import visual.BeginMenu;
import visual.CreaturesView;
import visual.DotsView;
import visual.FruitView;
import visual.GameView;
import visual.PacmanView;

public class Game implements KeyListener, Runnable {
	// MODELO
	private static GameState state;
	private static Board board;
	private static Square[][] boardMatrix;
	// private static ArrayList<Dot> dotMatrix;
	private static ArrayList<Dot> dotStartMatrix;

	// VISUAL
	private static DotsView dotsView;
	private static GameView gameView; // JFRAME
	private static CreaturesView pacmanView;
	private static ArrayList<CreaturesView> ghostViewsArray;
	private static BeginMenu beginMenu;
	private static JLayeredPane layers;
	private static FruitView fruitView;

	// ESTRUCTURA
	private static boolean run = true;
	private static boolean firstTime = true;
	private static Sounds sound = new Sounds();

	// DATOS
	private static int time = 0;
	private static int retard = 66;
	private static int superTime = 0;
	private static int ghostQuantity = 5;

	public Game(BeginMenu beginMenu, JLayeredPane layers, Board board) {
		Game.beginMenu = beginMenu;
		Game.layers = layers;
		Game.board = board;
	}

	// EL METODO PRINCIPAL (ESTAMOS EN UN THREAD)
	public void run() {
		initGame();
		try {
			play();
		} catch (IOException | ParseException | InterruptedException | LineUnavailableException
				| UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
	}

	// INICIALIZAMOS ALGUNAS DE LAS VARIABLES
	public void initGame() {

		state = new Load();
		gameView = new GameView();
		boardMatrix = Board.getBoard();

		Board.makeDots();
		dotStartMatrix = Board.getDots();
		Board.createGhosts(ghostQuantity);
		Board.createPacman("pacman", boardMatrix[27][43]);
		gameView.addKeyListener(this);

	}

	public static void initVisual() {

		gameView.setContentPane(layers);
		fruitView = new FruitView(layers);
		dotsView = new DotsView(Board.getDots(), layers);
		pacmanView = new PacmanView(Board.pacman, layers);
		createGhostViews(ghostQuantity);
		gameView.setVisible(true);

		Board.observeFruit(fruitView);
		Board.observePacman(pacmanView);

		board.addObserver(dotsView);

	}

	// ARRANCA EL JUEGO
	private void play() throws IOException, ParseException, InterruptedException, LineUnavailableException,
			UnsupportedAudioFileException {

		boolean ever = true;
		time = 0;

		while (ever) {

			// if (state.toString() != "PostGame")
			gameView.requestFocus();

			if (isFirstTime())
				state.reorganize(this);

			state.run();

			if (Board.getLifes() <= 0) {
				state = new PostGame();
				firstTime = true;
				Board.setLifes(3);
			}
		}
	}

	public static void createGhostViews(int value) {
		ghostViewsArray = new ArrayList<CreaturesView>();
		Board.observeGhost(ghostViewsArray, value, layers);
	}

	public static void runCreatures() throws InterruptedException {

		Board.lookingForCreatures();
		Board.moveGhosts();
		Board.lookingForCreatures();
		Board.movePacman();
		Board.lookingForDot();
		Board.lookingForFruit();
		Board.updateFruit();

	}

	public static void upSuperTime() {
		Game.superTime++;
	}

	// ESCUCHA LAS TECLAS: DIRECCIONES Y PAUSA (P)
	@Override
	public void keyPressed(KeyEvent arg0) {

		switch (arg0.getKeyCode()) {
		case KeyEvent.VK_LEFT: {
			Board.pacman.setPotentialDirection(Direction.LEFT);
			break;
		}
		case KeyEvent.VK_UP: {
			Board.pacman.setPotentialDirection(Direction.UP);
			break;
		}
		case KeyEvent.VK_RIGHT: {
			Board.pacman.setPotentialDirection(Direction.RIGHT);
			break;
		}
		case KeyEvent.VK_DOWN: {
			Board.pacman.setPotentialDirection(Direction.DOWN);
			break;
		}
		case KeyEvent.VK_P: {
			setState(new Pause());
			break;
		}
		}
	}

	// METODOS OBLIGADOS PARA EL KEYLISTENER
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}

	public static boolean isFirstTime() {
		return firstTime;
	}

	public static void setFirstTime(boolean firstTime) {
		Game.firstTime = firstTime;
	}

	public static void setState(GameState newState) {
		state = newState;
	}

	public static boolean isPaused() {
		return !run;
	}

	public static int getTime() {
		return time;
	}

	public static void setTime(int time) {
		Game.time = time;
	}

	public static void upTime() {
		Game.time++;
	}

	public void pause(boolean run) {
		Game.run = !run;
	}

	public static int getRetard() {
		return retard;
	}

	public static void setRetard(int retard) {
		Game.retard = retard;
	}

	public static GameState getState() {
		// TODO Auto-generated method stub
		return state;
	}

	public static int getSuperTime() {
		return superTime;
	}

	public static void setSuperTime(int superTime) {
		Game.superTime = superTime;
	}

	public static ArrayList<Dot> getDotStartMatrix() {
		return dotStartMatrix;
	}

	public static GameView getGameView() {
		return gameView;
	}

	public static void setGameView(GameView gameView) {
		Game.gameView = gameView;
	}

	public static FruitView getFruitView() {
		return fruitView;
	}

	public static void setFruitView(FruitView fruitView) {
		Game.fruitView = fruitView;
	}

	public static int getGhostQuantity() {
		return ghostQuantity;
	}

	public static void setGhostQuantity(int ghostQuantity) {
		Game.ghostQuantity = ghostQuantity;
	}

	public static CreaturesView getPacmanView() {
		return pacmanView;
	}

	public static void setPacmanView(CreaturesView pacmanView) {
		Game.pacmanView = pacmanView;
	}

	public static DotsView getDotsView() {
		return dotsView;
	}

	public static void setDotsView(DotsView dotsView) {
		Game.dotsView = dotsView;
	}

	public static BeginMenu getBeginMenu() {
		// TODO Auto-generated method stub
		return beginMenu;
	}

	public static Board getBoard() {
		// TODO Auto-generated method stub
		return board;
	}

	public static Sounds getSound() {
		// TODO Auto-generated method stub
		return sound;
	}

}