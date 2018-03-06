package controller;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

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
import visual.BeginMenu;
import visual.BoardView;
import visual.GameView;
import visual.PlayerView;
import visual.RecoveryMenu;
import visual.CreaturesView;
import visual.DotsView;

public class Game implements KeyListener, Runnable {

	
	static Board board;
	static Square[][] boardMatrix;
	static GameView gameView;
	static Dot[][] dotMatrix;
	static DotsView dotsView;
	static Game game;
	static Thread boardView;
	static Pacman pacman;

	static Ghost ghost1;
	static Ghost ghost2;
	static Ghost ghost3;
	static Ghost ghost4;
	static Ghost ghost5;

	static CreaturesView ghostView1;
	static CreaturesView ghostView2;
	static CreaturesView ghostView3;
	static CreaturesView ghostView4;
	static CreaturesView ghostView5;
	static CreaturesView pacmanView;
	BoardConfiguration boardConfiguration ;

	static Serializator serializator = new Serializator();
	static GameState gameState;


	static BeginMenu beginMenu;
	static int superTime = 0;
	static int hellTime = 0;
	static JLayeredPane layers;
	static boolean run = true;
	static boolean firstTime = true;
	static PlayerView playerView;
	static RecoveryMenu recoveryMenu;
	static Square originalPositionPacman ; 
	static Square originalPositionGhost ; 

	static BufferedInputStream bis = null;

	public Game(BeginMenu beginMenu, Thread boardView, Board board, JLayeredPane layers, BoardConfiguration boardConfiguration)
	{
		this.beginMenu = beginMenu;
		this.layers = layers;
		this.board= board;
		this.boardConfiguration = boardConfiguration;
		this.boardView= boardView;

	}
	public void run () {
		
		
		initGame();
		try {
			play();
		} catch (IOException | ParseException | InterruptedException | LineUnavailableException | UnsupportedAudioFileException e) {
			e.printStackTrace();
		}


	}


	private void initGame() {
		gameState = GameState.LOAD;
		gameView = new GameView();
	    boardMatrix = board.getBoard();
	    originalPositionGhost =boardMatrix[23][22];
	    originalPositionPacman =boardMatrix[27][43];
		ghost1 = new Ghost("ghost1", originalPositionGhost);
		ghost2 = new Ghost("ghost2", originalPositionGhost);
		ghost3 = new Ghost("ghost3", originalPositionGhost);
		ghost4 = new Ghost("ghost4", originalPositionGhost);
		ghost5 = new Ghost("ghost5", originalPositionGhost);
		pacman = new Pacman("pacman", originalPositionPacman);

	}

	private void initVisual() {

		gameView.setContentPane(layers);

		gameView.addKeyListener(this);
		dotMatrix = board.getDots();
		dotsView = new DotsView(dotMatrix, layers);
		playerView = new PlayerView(layers);
		pacmanView = new CreaturesView(pacman, layers);
		ghostView1 = new CreaturesView(ghost1, layers);
		ghostView2 = new CreaturesView(ghost2, layers);
		ghostView3 = new CreaturesView(ghost3, layers);
		ghostView4 = new CreaturesView(ghost4, layers);
		ghostView5 = new CreaturesView(ghost5, layers);
		gameView.setVisible(true);
		pacman.addObserver(pacmanView);
		ghost1.addObserver(ghostView1);
		ghost2.addObserver(ghostView2);
		ghost3.addObserver(ghostView3);
		ghost4.addObserver(ghostView4);
		ghost5.addObserver(ghostView5);
		board.addObserver(dotsView);
		//board.addObserver(boardView);

	}

	private void play() throws IOException, ParseException, InterruptedException, LineUnavailableException, UnsupportedAudioFileException {
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
				superMode(ghost1, pacman);
				break;
			case POSTGAME:
				postGame();
				break;
			}

		}

	}

	private static void load() {
		if (firstTime) {

			gameView.setContentPane(beginMenu);
			firstTime = false;
		}

		if (beginMenu.wasPressbtnBegin()) {
			gameState = GameState.NORMALMODE;
			firstTime = true;
			beginMenu.dispose();
		} else if (beginMenu.wasPressBtnRecovery()) {
			gameState = GameState.RECOVERY;
			beginMenu.dispose();
			firstTime = true;

		}
	}

	private static void pausa() {
		JOptionPane.showMessageDialog(null, "la partida esta en pausa");
		gameState = GameState.NORMALMODE;

	}

	private static void postGame() {
		JOptionPane.showMessageDialog(null, "la partida termino. Puntos: "+ board.score);
		firstTime = true;
		board.lifes = 3;
		board.score = 0;
		gameState = GameState.LOAD;
		//gameView.dispose(boardView); Hay q buscar el equivalente a esto
	}

	private static void superMode(Ghost ghost, Pacman pacman) {

		while (board.superMode) {
			try {
				Thread.sleep(80);

			} catch (InterruptedException time) {

			}

			/*
			 * creatures.get(indexPacman).eateable = false; for (Creature creature :
			 * creatures) { if ((creature.identy=="Ghost")&&(creature.alive)) {
			 * creature.eateable = true; } } <<<CUANDO SEAN VARIAS CRIATURAS>>>
			 */
			ghost.pathFinder(pacman, 10);
			ghost.move();
			pacman.move();
			pacman.eatingGhosts(ghost, pacman, board);
			board.eatingDot(pacman);

			superTime++;
			/*
			 * //32 segundos?? if (superTime/12==30) { System.out.println("caca");
			 */
			if (board.dotRemoved.superDot) {
				superTime = 0;
			}
			if (superTime == 100) {
				superTime = 0;
				/*
				 * for (Creature creature : creatures) { if (creature.identy!="Pacman") {
				 * creature.eateable = false; }else creature.eateable = true; } <<<CUANDO SEAN
				 * VARIAS CRIATURAS>>>
				 */
				board.superMode = false;
			}

			// creatures.get(indexPacman).eatingGhosts(creatures, indexPacman);
			// <<<CUANDO SEAN VARIAS CRIATURAS>>>

		}

	}

	private void normalMode() throws InterruptedException, LineUnavailableException, IOException, UnsupportedAudioFileException {
		hellTime = 0;
		audioBeginning();
		Thread.sleep(1000);
		while (gameState.equals(GameState.NORMALMODE)) {

			Thread.sleep(80);

			if (hellTime == 100) {
				ghost1.pathFinder(pacman, 1);
				ghost2.pathFinder(pacman, 3);
				ghost3.pathFinder(pacman, 5);
				ghost4.pathFinder(pacman, 7);
				ghost5.pathFinder(pacman, 9);

				//EL HELLTIME LO DEBE TENER CADA GHOST EN FUNCION DE LA INTELIGENCIA
				ghost1.move();
				ghost2.move();
				ghost3.move();
				ghost4.move();
				ghost5.move();
			} else {
				hellTime++;
			}

			ghost1.eatPacman(pacman,board);
			ghost2.eatPacman(pacman,board);
			ghost3.eatPacman(pacman,board);
			ghost4.eatPacman(pacman,board);
			ghost5.eatPacman(pacman,board);

			if (!pacman.alive) {
				gameState = GameState.RESPAWN;
				if (board.lifes <= 0) {
					gameState = GameState.POSTGAME;
				}
			} else {
				pacman.move();
				board.eatingDot(pacman);
			}

			if (board.superMode) {
				gameState = GameState.SUPERMODE;
			}
			// SUPERMODE(ghost1, pacman);

		}

	}

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
	
	public static void save() {
		try {

			serializator.toPersist(board, pacman, ghost1, ghost2, ghost3, ghost4, ghost5);
			gameView.requestFocus();

		} catch (IOException e) {
			System.out.println("error " + e);
			e.printStackTrace();
		}
	}

	public static void recovery() throws FileNotFoundException, IOException, ParseException {
		Dot[][] dotsArraySaved = serializator.recover(board, pacman, ghost1, ghost2, ghost3, ghost4, ghost5);
		board.setDots(dotsArraySaved);
		recoveryMenu.dispose();
		setGameState(GameState.NORMALMODE);
		setFirstTime(true);
	}

	public static void respawn() {
		pacman.setPosition(originalPositionPacman);
		ghost1.setPosition(originalPositionGhost);
		ghost2.setPosition(originalPositionGhost);
		ghost3.setPosition(originalPositionGhost);
		ghost4.setPosition(originalPositionGhost);
		ghost5.setPosition(originalPositionGhost);
		
		pacman.alive=true;
		firstTime = true;
		gameState = GameState.NORMALMODE;
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

	public void audioBeginning() throws LineUnavailableException, UnsupportedAudioFileException, FileNotFoundException, InterruptedException
	{
		//Sonidos
		//bis = new BufferedInputStream(getClass().getResourceAsStream("pacman_beginning.wav"));
		try {
		AudioInputStream ais = AudioSystem.getAudioInputStream(new BufferedInputStream(getClass().getResourceAsStream("pacman_beginning.wav")));


		//AudioInputStream ais = AudioSystem.getAudioInputStream(bis);
		
		Clip audioBeginning = AudioSystem.getClip();
		audioBeginning.open(ais);
		audioBeginning.start();
		
		}catch(IOException e){
			System.err.println("CAGASTE LA WASKA");
		}

		
	}
	
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
	

	public static Square[][] getBoardMatrix() {
		return boardMatrix;
	}

	public static JLayeredPane getLayers() {
		return layers;
	}


}
