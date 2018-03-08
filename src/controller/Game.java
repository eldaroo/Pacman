package controller;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.text.html.HTMLEditorKit.Parser;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import model.Board;
import model.BoardConfiguration;
import model.Direction;
import model.Dot;
import model.GameState;
import model.Ghost;
import model.Pacman;
import model.Serializator;
import model.Square;
import sounds.beginning;
import visual.BeginMenu;
import visual.BoardView;
import visual.GameView;
import visual.PlayerView;
import visual.RecoveryMenu;
import visual.CreaturesView;
import visual.DotsView;

public class Game implements KeyListener, Runnable {
	// HILOS
	static Game game;
	static Thread boardView;

	// MODELOS
	static Board board;
	static Square[][] boardMatrix;
	static Dot[][] dotMatrix;
	static Square target;
	BoardConfiguration boardConfiguration;

	// VISUALES
	static JLayeredPane layers;
	static GameView gameView;
	static DotsView dotsView;
	static CreaturesView pacmanView;
	static ArrayList<CreaturesView> ghostViewsArray;
	static PlayerView playerView;

	// CRIATURAS
	static Pacman pacman;
	static ArrayList<Ghost> ghostsArray;
	static int pacmanState = 1;
	static int ghostQuantity = 5;
	static Square originalPositionPacman;

	// SERIALIZADOR
	static Serializator serializator = new Serializator();

	// ESTRUCTURA
	static GameState gameState;
	static boolean firstTime = true;
	static BeginMenu beginMenu;
	static RecoveryMenu recoveryMenu;

	// DATOS
	static int velocity = 66;
	static int distance;
	// static int superTime;
	static int hellTime;
	static int hellIndex;
	static Random randomHellZoneSquare = new Random();
	static boolean run = true;

	public Game(BeginMenu beginMenu, Thread boardView, Board board, JLayeredPane layers,
			BoardConfiguration boardConfiguration) {
		this.beginMenu = beginMenu;
		this.layers = layers;
		this.board = board;
		this.boardConfiguration = boardConfiguration;
		this.boardView = boardView;

	}

	public void run() {

		initGame();
		try {
			play();
		} catch (IOException | ParseException | InterruptedException | LineUnavailableException
				| UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
	}

	private void initGame() {
		gameState = GameState.LOAD;
		gameView = new GameView();
		boardMatrix = board.getBoard();
		// originalPositionGhost =boardMatrix[23][22];
		originalPositionPacman = boardMatrix[27][43];
		createGhosts(ghostQuantity);
		pacman = new Pacman("pacman", originalPositionPacman);
		target = pacman.getPosition();

	}

	private void initVisual() {

		gameView.setContentPane(layers);

		gameView.addKeyListener(this);
		dotMatrix = board.getDots();
		dotsView = new DotsView(dotMatrix, layers);
		playerView = new PlayerView(layers);
		pacmanView = new CreaturesView(pacman, layers);
		createGhostViews(ghostQuantity);
		gameView.setVisible(true);

		pacman.addObserver(pacmanView);
		board.addObserver(dotsView);

	}

	private void play() throws IOException, ParseException, InterruptedException, LineUnavailableException,
			UnsupportedAudioFileException {
		boolean ever = true;
		while (ever) {
			gameView.requestFocus();

			switch (gameState) {
			case LOAD:
				load();

				break;
			case RECOVERY:
				if (firstTime) {

					recoveryMenu = new RecoveryMenu(gameView);
					gameView.setContentPane(recoveryMenu);
					firstTime = false;
				}

				break;
			case NORMALMODE:
				if (firstTime) {
					initVisual();
					firstTime = false;
				}
				normalMode();
				break;
			case RESPAWN:
				respawn();
				break;
			case PAUSA:
				pausa();
				break;
			case SUPERMODE:
				superMode(pacman);
				break;
			case POSTGAME:
				postGame();
				break;
			}
			// CHIMPAAAAAA
			distance++;
			switch (distance) {
			case 22: {
				pacmanState = 1;
			}
			case 44: {
				pacmanState = 2;
			}
			case 66: {
				pacmanState = 3;
				distance = 0;
			}
			}
		}

	}

	// PANTALLA DE INICIO
	private static void load() {
		// SE CARGA
		if (firstTime) {

			gameView.setContentPane(beginMenu);
			firstTime = false;
		}
		// CAMBIA A MODO NORMAL (CIERRA PANTALLA DE INICIO)
		if (beginMenu.wasPressbtnBegin()) {
			gameState = GameState.NORMALMODE;
			firstTime = true;
			beginMenu.dispose();
		}
		// CAMBIA A MODO CARGAR (CIERRA PANTALLA DE INICIO)
		else if (beginMenu.wasPressBtnRecovery()) {
			gameState = GameState.RECOVERY;
			beginMenu.dispose();
			firstTime = true;

		}
	}

	// PAUSA PARTIDA
	private static void pausa() {
		JOptionPane.showMessageDialog(null, "la partida esta en pausa");
		gameState = GameState.NORMALMODE;

	}

	// FIN DEL JUEGO
	private static void postGame() {
		JOptionPane.showMessageDialog(null, "la partida termino. Puntos: " + board.score);
		firstTime = true;
		board.lifes = 3;
		board.score = 0;
		gameState = GameState.LOAD;
		// gameView.dispose(boardView); Hay q buscar el equivalente a esto
	}

	// EJECUTA MODO SUPER
	private static void superMode(Pacman pacman) {
		int superTime = 0;
		while (board.getSuperMode()) {
			try {
				Thread.sleep(velocity);

			} catch (InterruptedException time) {
			}
			pacman.move();
			pacman.eatingGhosts(ghostsArray, pacman, board, board.hellZone, target);
			board.eatingDot(pacman);
			superTime++;
			if (board.dotRemoved.getSuper()) {
				superTime = 0;
			}
			if (superTime == 111) {
				board.setSuperMode(false);
			}
		}
	}

	// EJECUTA MODO NORMAL
	private void normalMode()
			throws InterruptedException, LineUnavailableException, IOException, UnsupportedAudioFileException {
		hellTime = 0;
		audioBeginning();
		Thread.sleep(3500);

		while (gameState.equals(GameState.NORMALMODE)) {

			Thread.sleep(velocity);

			if (hellTime == 20) {
				// moveGhosts(target);
				// EL HELLTIME LO DEBE TENER CADA GHOST EN FUNCION DE LA INTELIGENCIA
			} else {
				hellTime++;
			}

			moveGhosts(target, pacman);
			pacman.move();
			board.eatingDot(pacman);

			// ACTIVE SUEPERMODE
			if (board.getSuperMode()) {
				gameState = GameState.SUPERMODE;
			}
			// RESPAWN GAME
			if (!pacman.alive) {
				gameState = GameState.RESPAWN;
				// END GAME
				if (board.lifes <= 0) {
					gameState = GameState.POSTGAME;
				}
			}
		}

	}

	// MOVER TODOS LOS GHOST
	private void moveGhosts(Square target, Pacman pacman) {
		int intelligence = 1;
		// GHOSTS: BUSCAN, MUEVEN Y COMEN.
		for (Ghost ghost : ghostsArray) {
			ghost.pathFinder(target, intelligence);
			ghost.move();
			ghost.eatingPacman(pacman, board);
			intelligence += 2;
		}
	}

	// ESCUCHA LAS TECLAS: DIRECCIONES Y PAUSA (P)
	@Override
	public void keyPressed(KeyEvent arg0) {

		switch (arg0.getKeyCode()) {
		case KeyEvent.VK_LEFT: {
			pacman.setPotentialDirection(Direction.LEFT);
			break;
		}
		case KeyEvent.VK_UP: {
			pacman.setPotentialDirection(Direction.UP);
			break;
		}
		case KeyEvent.VK_RIGHT: {
			pacman.setPotentialDirection(Direction.RIGHT);
			break;
		}
		case KeyEvent.VK_DOWN: {
			pacman.setPotentialDirection(Direction.DOWN);
			break;
		}
		case KeyEvent.VK_P: {
			gameState = GameState.PAUSA;
			// Pause(!isPaused());
			break;
		}

		}
	}

	public static boolean isFirstTime() {
		return firstTime;
	}

	public static void setFirstTime(boolean firstTime) {
		Game.firstTime = firstTime;
	}

	// GUARDA PARTIDA
	public static void save() {
		try {

			serializator.toPersist(board, pacman);
			//
			gameView.requestFocus();

		} catch (IOException e) {
			System.out.println("error " + e);
			e.printStackTrace();
		}
	}

	// CARGA PARTIDA
	public static void recovery() throws FileNotFoundException, IOException, ParseException {
		// RECUPER LOS DATOS GUARDADOS DE LOS DOTS Y LOS VUELCA AL TABLERO
		Dot[][] dotsArraySaved = serializator.recover(board, pacman);
		board.setDots(dotsArraySaved);
		recoveryMenu.dispose();
		setGameState(GameState.NORMALMODE);
		setFirstTime(true);
	}

	// REINICIA POSICIONES EN EL TABLERO
	public static void respawn() {
		pacman.setPosition(originalPositionPacman);
		// UBICA A LOS GHOST EN POSICION AZAROZA DENTRO DEL HELL
		for (Ghost ghost : ghostsArray) {
			hellIndex = randomHellZoneSquare.nextInt(board.hellZone.size());
			ghost.setPosition(board.hellZone.get(hellIndex));
		}
		// REINICIA PARTIDA
		pacman.alive = true;
		firstTime = true;
		gameState = GameState.NORMALMODE;
	}

	// CREA MODELO DE GHOSTS
	private void createGhosts(int ghostQuantity) {
		ghostsArray = new ArrayList<Ghost>();
		for (int i = 1; i <= ghostQuantity; i++) {
			hellIndex = randomHellZoneSquare.nextInt(board.hellZone.size());
			ghostsArray.add(new Ghost("ghost" + i, board.hellZone.get(hellIndex)));
		}
	}

	// CREA VISUAL DE GHOSTS Y COMIENZA A OBSERVAR MODELO DE GHOSTS
	private void createGhostViews(int ghostQuantity) {
		ghostViewsArray = new ArrayList<CreaturesView>();
		for (int i = 1; i <= ghostQuantity; i++) {
			ghostViewsArray.add(new CreaturesView(ghostsArray.get(i - 1), layers));
			ghostsArray.get(i - 1).addObserver(ghostViewsArray.get(i - 1));
		}
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

	public void Pause(boolean run) {
		Game.run = !run;
	}

	// MUSICA DE INICIO DE PARTIDA
	public void audioBeginning() throws LineUnavailableException, UnsupportedAudioFileException, FileNotFoundException,
			InterruptedException {
		beginning sound = new beginning();
		sound.play();
		Thread.sleep(3795);

	}

	public static Square[][] getBoardMatrix() {
		return boardMatrix;
	}

	public static JLayeredPane getLayers() {
		return layers;
	}

	// METODOS OBLIGADOS PARA EL KEYLISTENER
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}
}
