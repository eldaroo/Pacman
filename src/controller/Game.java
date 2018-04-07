package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.io.FileNotFoundException;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Random;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import org.json.simple.parser.ParseException;
import org.w3c.dom.css.DocumentCSS;

import model.Board;
import model.Direction;
import model.Dot;
import model.Fruit;
import model.GameState;
import model.Ghost;
import model.Ghost.GhostState;
import model.IA;
import model.MyDataAcces;
import model.Pacman;
import model.Pacman.PacmanState;
import model.Serializator;
import model.Square;

import sounds.Sounds;
import visual.BeginMenu;

import visual.GameView;
import visual.GhostView;
import visual.PacmanView;
import visual.PostGameView;
import visual.ScoreView;
import visual.CreaturesView;
import visual.DotsView;
import visual.FruitView;

public class Game implements KeyListener, Runnable {

	// MODELO
	private static Board board;
	private static Square[][] boardMatrix;
	//private static ArrayList<Dot> dotMatrix;
	private static ArrayList<Dot> dotStartMatrix;

	// VISUAL
	private static DotsView dotsView;
	private static PostGameView postGameView;
	private static GameView gameView; // JFRAME
	private static CreaturesView pacmanView;
	private static ArrayList<CreaturesView> ghostViewsArray;
	private static BeginMenu beginMenu;
	private static JLayeredPane layers;
	private static FruitView fruitView;
	private static ScoreView scoreView;

	// SERIALIZADOR y SQL
	private static Serializator serializator = new Serializator();
	private static MyDataAcces connection;
	private static ResultSet result;

	// ESTRUCTURA
	private static GameState gameState;
	private static boolean run = true;
	private static boolean firstTime = true;
	// private static Square originalPositionPacman ;
	private static Sounds sound = new Sounds();

	// DATOS
	private static int time = 0;
	private static int retard = 66;
	private static int superTime = 0;
	private static int ghostQuantity = 5;

	// CON ESTO LAS VARIABLES IMPORTADAS DE CONTROLLER SE PUEDEN MANEJAR LOCALMENTE
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
	public static void initGame() {

		gameState = GameState.LOAD;
		gameView = new GameView();
		boardMatrix = Board.getBoard();

		Board.makeDots();
		dotStartMatrix = Board.getDots();
		Board.createGhosts(ghostQuantity);
		Board.createPacman("pacman", boardMatrix[27][43]);
		scoreView = new ScoreView();
	}

	// INICIALIZAMOS MAS VARIABLES, ESTA VEZ ORIENTADO A LO VISUAL
	private void initVisual() {

		gameView.setContentPane(layers);
		gameView.addKeyListener(this);
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

			if (gameState != GameState.POSTGAME)
				gameView.requestFocus();
			

			switch (gameState) {
			case LOAD:
				load();
				break;
			case RECOVERY:
				recovery();
				break;
			case NORMALMODE:
				normalMode();
				break;
			case SUPERMODE:
				superMode();
				break;
			case RESPAWN:
				respawn();
				break;
			case PAUSA:
				pausa();
				break;
			case POSTGAME:
				 postGame();
				break;
			case NEXTLEVEL:
				nextLevel();
				break;
			}

			// END GAME
			if (Board.getLifes() <= 0) {
				gameState = GameState.POSTGAME;
				firstTime = true;
				Board.setLifes(3);
			}
		}
	}

	private static void load() {
		// LA PANTALLA PRE-JUEGO

		if (firstTime) {
			gameView.setContentPane(beginMenu);
			firstTime = false;
		}
		if (beginMenu.wasPressbtnBegin()) {
			// CAMBIA A MODO NORMAL (CIERRA PANTALLA DE INICIO)
			gameState = GameState.NORMALMODE;
			firstTime = true;
			beginMenu.dispose();
		} else if (beginMenu.wasPressBtnRecovery()) {
			// CAMBIA A MODO CARGAR (CIERRA PANTALLA DE INICIO)
			gameState = GameState.RECOVERY;
			beginMenu.dispose();
			firstTime = true;
		} else if (beginMenu.wasPressBtnExit()) {
			// CIERRA EL JUEGO
			System.exit(0);
		}
	}

	private void normalMode()
			throws InterruptedException, LineUnavailableException, IOException, UnsupportedAudioFileException {
		
		Board.pacman.resetGhostEated();
		Board.setGhostState(Ghost.GhostState.COURAGEOUS);

		if (firstTime) {
			initVisual();
			Sounds.reproduceBeginning();
			firstTime = false;
		}
		while (gameState.equals(GameState.NORMALMODE)) {
			time++;
			Thread.sleep(retard);

			runCreatures();
			board.update();

		}
	}

	private void superMode() throws InterruptedException {
		superTime = 0;
		Board.setGhostState(Ghost.GhostState.PUSSY);

		while (gameState.equals(GameState.SUPERMODE)) {

			Thread.sleep(retard);
			time++;

			runCreatures();

			superTime++;
			if (Board.getDotRemoved().getSuper()) {
				superTime = 0;
			}
			if (superTime == 150) {
				gameState = GameState.NORMALMODE;
			}
			if (superTime >= 125)
				Board.setGhostState(GhostState.HURRY);
			
			board.update();

		}
		
	}

	// REINICIA POSICIONES EN EL TABLERO
	public static void respawn() {

		Board.respawnCreatures();

		// REINICIA PARTIDA

		Board.pacman.setPacmanState(PacmanState.MOVE);

		firstTime = true;
		gameState = GameState.NORMALMODE;
	}

	private static void pausa() {
		JOptionPane.showMessageDialog(null, "la partida esta en pausa");
		gameState = GameState.NORMALMODE;
	}

	private void nextLevel() {
		Board.upLevel();
		Board.setDots(dotStartMatrix);
		gameState = GameState.RESPAWN;
		retard = (retard *5) / 6;
	}

	// TERMINO LA PARTIDA
	private static void postGame() {
		if (firstTime) {
			 JOptionPane.showMessageDialog(null, "la partida termino. Puntos: "+ board.getScore());
			gameView.remove(layers);
			postGameView = new PostGameView(gameView, postGameView, scoreView);
			gameView.setContentPane(postGameView);
			firstTime = false;
			sound.reproducePostGame();

		}

	}

	private void createGhostViews(int value) {
		ghostViewsArray = new ArrayList<CreaturesView>();
		Board.observeGhost(ghostViewsArray, value,layers);
		
	}

	public void runCreatures() throws InterruptedException {

		if (Board.pacman.getPacmanState()==PacmanState.EATGHOST) {
		    Thread.sleep(500);
			Board.pacman.setPacmanState(PacmanState.MOVE);
		}
		
			Board.lookingForCreatures();
			Board.moveGhosts();
			Board.lookingForCreatures();

		Board.movePacman();
		Board.lookingForDot();
		Board.lookingForFruit();

		Board.updateFruit();

	}

	// GUARDA PARTIDA
	public static void save() {
		try {

			serializator.toPersist();

			gameView.requestFocus();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// CARGA PARTIDA
	public static void recovery() throws FileNotFoundException, IOException, ParseException {

		ArrayList<Dot> dotsArraySaved = serializator.recover();
		Board.setDots(dotsArraySaved);
		//dotMatrix = Board.getDots();
		setGameState(GameState.NORMALMODE);
		setFirstTime(true);
	}

	// SALVAMOS EL SCORE
	public static void saveScore(String name) {
		try {
			connection = new MyDataAcces();
			connection.setQuery(name, Board.getScore());
		} catch (Exception e) {
		}
	}

	// CARGAMOS EL SCORE Y LO VOLCAMOS AL JTEXTAREA DEL SCOREVIEW
	public static void getScore() {
		int aux = 0;
		try {
			connection = new MyDataAcces();
			result = connection.getQuery();
			while (result.next()) {
				aux++;
				scoreView.getScoreTextArea()
						.append(aux + ". " + result.getString("name") + " " + result.getInt("score") + "\n");
			}

		} catch (Exception e) {
		}

		gameView.repaint();
	}

	public static Square[][] getBoardMatrix() {
		return boardMatrix;
	}

	public static JLayeredPane getLayers() {
		return layers;
	}

	public static boolean isFirstTime() {
		return firstTime;
	}

	public static void setFirstTime(boolean firstTime) {
		Game.firstTime = firstTime;
	}

	public static GameState getGameState() {
		return gameState;
	}

	public static void setGameState(GameState gameState) {
		Game.gameState = gameState;
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

	public void pause(boolean run) {
		Game.run = !run;
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
			gameState = GameState.PAUSA;
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

}