package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Observer;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JLayeredPane;
import javax.swing.text.View;

import org.json.simple.parser.ParseException;
import controller.states.GameState;
import controller.states.Load;
import controller.states.Pause;
import controller.states.PostGame;
import model.Board;
import model.BoardConfiguration;
import model.Direction;
import model.Dot;
import model.squares.Square;
import sounds.Sounds;
import visual.ViewManager;


public class Game implements KeyListener {
	
	public static Game game;
	// MODELO
	private static GameState state;
	private static Board board;
	private static Square[][] boardMatrix;
	private static ArrayList<Dot> dotStartMatrix;
	private static ViewManager viewManager;

	// ESTRUCTURA
	private static boolean run = true;
	private static boolean changeState = true;
	private static boolean firstTime = true;
	private static Sounds sound = new Sounds();

	// DATOS
	private static int time = 0;
	private static int retard = 66;
	private static int superTime = 0;
	private static int ghostQuantity = 5;

	public static void main(String[] args) throws IOException, ParseException, InterruptedException, LineUnavailableException, UnsupportedAudioFileException {
		game = new Game();
		viewManager = new ViewManager();
		initGame();
		initVisual();
		play();
	}

	// INICIALIZAMOS ALGUNAS DE LAS VARIABLES
	public static void initGame() {

		state = new Load();
		board = new Board(BoardConfiguration.getLevelBoard());
		boardMatrix = Board.getBoard();

		Board.makeDots();
		dotStartMatrix = Board.getDots();
		Board.createGhosts(ghostQuantity);
		Board.createPacman("pacman", boardMatrix[27][43]);
		ViewManager.getWindow().addKeyListener(game);


	}

	public static void initVisual() {



	}

	// ARRANCA EL JUEGO
	private static void play() throws IOException, ParseException, InterruptedException, LineUnavailableException,
			UnsupportedAudioFileException {

		boolean ever = true;
		time = 0;

		while (ever) {

			// if (state.toString() != "PostGame")
			ViewManager.getWindow().requestFocus();

			if (isFirstTime())
			{
				state.reorganize();

			}
			playMusic();

			state.run();

			if (Board.getLifes() <= 0) {
				state = new PostGame();
				firstTime = true;
				Board.setLifes(3);
			}
		}
	}


	private static void playMusic() throws FileNotFoundException, InterruptedException {
		if(changeState)
		{
			sound.reproduceMusic(state.toString());
			changeState = false;
		}
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
		changeState = true;
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

	public static Board getBoard() {
		// TODO Auto-generated method stub
		return board;
	}

	public static Sounds getSound() {
		// TODO Auto-generated method stub
		return sound;
	}



}