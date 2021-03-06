package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import org.json.simple.parser.ParseException;
import controller.states.GameState;
import controller.states.LoadState;
import controller.states.NextLevelState;
import controller.states.PauseState;
import controller.states.PostGameState;
import model.Board;
import model.Direction;
import model.board.Dot;
import model.squares.Square;
import sounds.Sounds;

import visual.ViewManager;

public class Game implements KeyListener {

	// MODELO
	private static GameState state;
	private static Board board;
	private static Square[][] boardMatrix;
	private static ArrayList<Dot> dotStartMatrix;

	// ESTRUCTURA
	private static boolean changeState = true;
	private static boolean firstTime = true;
	private static Sounds sound = new Sounds();
	private static boolean win = false;
	private static Game game;

	// DATOS
	private static int time = 0;
	private static int retard = 66;
	private static int superTime = 0;
	private static int ghostQuantity = 5;

	public static void main(String[] args) throws IOException, ParseException, InterruptedException,
			LineUnavailableException, UnsupportedAudioFileException {
		initGame();
		play();
	}

	// INICIALIZAMOS ALGUNAS DE LAS VARIABLES
	public static void initGame() {

		game = new Game();
		state = new LoadState();
		board = new Board();
		boardMatrix = board.getBoard();
		board.makeDots();
		board.createGhosts(ghostQuantity);
		board.createPacman("pacman", boardMatrix[27][43]);
		ViewManager.getWindow().addKeyListener(game);

	}

	private static void play() throws IOException, ParseException, InterruptedException, LineUnavailableException,
			UnsupportedAudioFileException {

		boolean ever = true;
		time = 0;

		while (ever) {

			if (state.toString() != "PostGame")
				ViewManager.getWindow().requestFocus();

			if (isFirstTime()) {
				state.reorganize();
			}
			playMusic();

			state.run();

			if (board.getLifes() <= 0) {
				state = new PostGameState();
				firstTime = true;
				board.setLifes(3);
			}

			if (board.getLevel() > 3) {
				win = true;
				state = new PostGameState();
				firstTime = true;	
				board.setLevel(1);
				}
		}
	}

	private static void playMusic() throws FileNotFoundException, InterruptedException {
		if (changeState) {
			sound.reproduceMusic(state.toString());
			changeState = false;
		}
	}

	public static void runCreatures() throws InterruptedException {

		board.lookingForCreatures();
		board.moveGhosts();
		board.lookingForCreatures();
		board.movePacman();

	}

	public static void upSuperTime() {
		Game.superTime++;
	}

	// ESCUCHA LAS TECLAS: DIRECCIONES Y PAUSA (P)
	@Override
	public void keyPressed(KeyEvent arg0) {

		switch (arg0.getKeyCode()) {
		case KeyEvent.VK_LEFT: {
			board.pacman.setPotentialDirection(Direction.LEFT);
			break;
		}
		case KeyEvent.VK_UP: {
			board.pacman.setPotentialDirection(Direction.UP);
			break;
		}
		case KeyEvent.VK_RIGHT: {
			board.pacman.setPotentialDirection(Direction.RIGHT);
			break;
		}
		case KeyEvent.VK_DOWN: {
			board.pacman.setPotentialDirection(Direction.DOWN);
			break;
		}
		case KeyEvent.VK_P: {
			setState(new PauseState());
			break;
		}
		}
	}

	public static void checkIfCompleteLevel(){
		if (board.getDots().size() == 0) {
			sound.reproduceLevelUp();
			setState(new NextLevelState());
			board.setPacmanEatNewDot(false);
			setFirstTime(true);
		}
	}

	// METODOS OBLIGADOS PARA EL KEYLISTENER
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}
	// ---------------------------------------

	public static boolean isFirstTime() {
		return firstTime;
	}

	public static void setFirstTime(boolean firstTime) {
		Game.firstTime = firstTime;
	}

	public static void setState(GameState newState) {
		state = newState;
		changeState = true;
	}

	public static int getTime() {
		return time;
	}
	public static boolean getWin() {
		return win;
	}
	public static void setWin(boolean win) {
		Game.win = win;
	}

	public static void upTime() {
		Game.time++;
	}

	public static int getRetard() {
		return retard;
	}

	public static void setRetard(int retard) {
		Game.retard = retard;
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

	public static Board getBoard() {
		return board;
	}

	public static Sounds getSound() {
		return sound;
	}

}