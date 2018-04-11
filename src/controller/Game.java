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
import javax.swing.JOptionPane;

import org.json.simple.parser.ParseException;

import controller.states.GameState;
import controller.states.Load;
import controller.states.Normal;
import controller.states.Pause;
import controller.states.PostGame;
import model.Board;
import model.Direction;
import model.Dot;
import model.MyDataAcces;
import model.Serializator;
import model.Square;
import model.creatures.Pacman.PacmanState;
import model.creatures.ghostStates.Courageous;
import model.creatures.ghostStates.Pussy;
import sounds.Sounds;
import visual.BeginMenu;
import visual.CreaturesView;
import visual.DotsView;
import visual.FruitView;
import visual.GameView;
import visual.PacmanView;
import visual.PostGameView;
import visual.ScoreView;

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
		dotStartMatrix =Board.getDots();
		Board.createGhosts(ghostQuantity);
		Board.createPacman("pacman", boardMatrix[27][43]);
		scoreView = new ScoreView();
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

			//if (state.toString() != "PostGame")
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

		if (Board.pacman.getPacmanState() == PacmanState.EATGHOST) {
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
		Game.time ++;
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
	public static void upSuperTime() {
		Game.superTime++;
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

	public static BeginMenu getBeginMenu() {
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


}