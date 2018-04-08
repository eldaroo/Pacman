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
import controller.states.*;
public class Game implements KeyListener, Runnable {

	// MODELO
	private static GameState state;
	private static Board board;
	private static Square[][] boardMatrix;
	//private static ArrayList<Dot> dotMatrix;
	private static ArrayList<Dot> dotStartMatrix;

	// VISUAL
	private static DotsView dotsView;
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
		Game.setBeginMenu(beginMenu);
		Game.layers = layers;
		Game.setBoard(board);
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

		state = new Load();
		setGameView(new GameView());
		boardMatrix = Board.getBoard();

		Board.makeDots();
		setDotStartMatrix(Board.getDots());
		Board.createGhosts(ghostQuantity);
		Board.createPacman("pacman", boardMatrix[27][43]);
		scoreView = new ScoreView();
	}

	// INICIALIZAMOS MAS VARIABLES, ESTA VEZ ORIENTADO A LO VISUAL
	public void initVisual() {

		getGameView().setContentPane(layers);
		getGameView().addKeyListener(this);
		fruitView = new FruitView(layers);
		dotsView = new DotsView(Board.getDots(), layers);
		pacmanView = new PacmanView(Board.pacman, layers);
		createGhostViews(ghostQuantity);
		getGameView().setVisible(true);

		Board.observeFruit(fruitView);
		Board.observePacman(pacmanView);

		getBoard().addObserver(dotsView);

	}

	// ARRANCA EL JUEGO
	@SuppressWarnings("unlikely-arg-type")
	private void play() throws IOException, ParseException, InterruptedException, LineUnavailableException,
			UnsupportedAudioFileException {
		boolean ever = true;
		time = 0;
		while (ever) {

			if (!state.equals(PostGame.class))
				getGameView().requestFocus();
			state.run();
			if (Board.getLifes() <= 0) {
				state = new PostGame();
				firstTime = true;
				Board.setLifes(3);
			}
		}
	}

	private static void load() {
		// LA PANTALLA PRE-JUEGO

		if (firstTime) {
			getGameView().setContentPane(getBeginMenu());
			firstTime = false;
		}
		getBeginMenu();
		if (BeginMenu.wasPressbtnBegin()) {
			// CAMBIA A MODO NORMAL (CIERRA PANTALLA DE INICIO)
			state = new Normal();
			firstTime = true;
			getBeginMenu().dispose();
		} else {
			getBeginMenu();
			if (BeginMenu.wasPressBtnRecovery()) {
				// CAMBIA A MODO CARGAR (CIERRA PANTALLA DE INICIO)
				//gameState = new Recovery();
				getBeginMenu().dispose();
				firstTime = true;
			} else {
				getBeginMenu();
				if (BeginMenu.wasPressBtnExit()) {
					// CIERRA EL JUEGO
					System.exit(0);
				}
			}
		}
	}

	// REINICIA POSICIONES EN EL TABLERO
	public static void respawn() {

		Board.respawnCreatures();

		// REINICIA PARTIDA

		Board.pacman.setPacmanState(PacmanState.MOVE);

		firstTime = true;
		state = new Normal();
	}

	private static void createGhostViews(int value) {
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

			getSerializator().toPersist();

			getGameView().requestFocus();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// CARGA PARTIDA
	public static void recovery() throws FileNotFoundException, IOException, ParseException {

		ArrayList<Dot> dotsArraySaved = getSerializator().recover();
		Board.setDots(dotsArraySaved);
		//dotMatrix = Board.getDots();
		setState(new Normal());
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

		getGameView().repaint();
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

	public static Serializator getSerializator() {
		return serializator;
	}

	public static void setSerializator(Serializator serializator) {
		Game.serializator = serializator;
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

	public static Board getBoard() {
		return board;
	}

	public static void setBoard(Board board) {
		Game.board = board;
	}

	public static int getSuperTime() {
		return superTime;
	}

	public static void setSuperTime(int superTime) {
		Game.superTime = superTime;
	}

	public static Sounds getSound() {
		return sound;
	}

	public static void setSound(Sounds sound) {
		Game.sound = sound;
	}

	public static ArrayList<Dot> getDotStartMatrix() {
		return dotStartMatrix;
	}

	public static void setDotStartMatrix(ArrayList<Dot> dotStartMatrix) {
		Game.dotStartMatrix = dotStartMatrix;
	}

	public static  BeginMenu getBeginMenu() {
		return beginMenu;
	}

	public static void setBeginMenu(BeginMenu beginMenu) {
		Game.beginMenu = beginMenu;
	}

	public static GameView getGameView() {
		return gameView;
	}

	public static void setGameView(GameView gameView) {
		Game.gameView = gameView;
	}

}