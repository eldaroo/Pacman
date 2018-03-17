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

import java.sql.Date;
import java.time.LocalDateTime;

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
import model.Ghost.GhostState;
import model.Pacman;
import model.Pacman.PacmanState;
import model.Position;
import model.Serializator;
import model.Square;
import sounds.Beginning;
import sounds.Death;
import sounds.EatDot;
import sounds.EatGhost;
import sounds.Sounds;
import visual.BeginMenu;
import visual.BoardView;
import visual.GameView;
import visual.GhostView;
import visual.PacmanView;
import visual.PlayerView;
import visual.PostGameView;
import visual.RecoveryMenu;
import visual.CreaturesView;
import visual.DotsView;

public class Game implements KeyListener, Runnable {

	//HILOS
	private static Game game;
	private static Thread boardView;

	//MODELO
	private static Board board;
	private static Square[][] boardMatrix;
	private static ArrayList<Dot> dotMatrix;
	private static BoardConfiguration boardConfiguration ;

	//VISUAL
	private static DotsView dotsView;
	private static PostGameView postGameView;
	private static GameView gameView; //JFRAME
	private static CreaturesView pacmanView;
	private static ArrayList<CreaturesView> ghostViewsArray;
	private static BeginMenu beginMenu;
	private static JLayeredPane layers;
	private static PlayerView playerView;
	private static RecoveryMenu recoveryMenu;

	private static LocalDateTime date = LocalDateTime.now();

	//CRIATURAS
	private static Pacman pacman;
	private static ArrayList<Ghost> ghostsArray;

	//SERIALIZADOR
	private static Serializator serializator = new Serializator();


	//ESTRUCTURA
	private static GameState gameState;
	private static boolean run = true;
	private static boolean firstTime = true;
	private static Square originalPositionPacman ; 
	private static int hellIndex=0;
	private static Random randomHellZoneSquare = new Random();
	private static Sounds sound = new Sounds();
	
	//DATOS
	private static int velocity = 66;
	private static int distance = 0;
	private static int superTime = 0;
	private static int hellTime = 0;
	private static int ghostQuantity = 5;
	
	public Game(BeginMenu beginMenu, Thread boardView, Board board, JLayeredPane layers, BoardConfiguration boardConfiguration)
	{
		//CON ESTO LAS VARIABLES IMPORTADAS DE CONTROLLER SE PUEDEN MANEJAR LOCALMENTE
		this.beginMenu = beginMenu;
		this.layers = layers;
		this.board= board;
		this.boardConfiguration = boardConfiguration;
		this.boardView= boardView;
		

	}
	public void run () {
		//EL METODO PRINCIPAL (ESTAMOS EN UN THREAD)
		initGame();
		try {
			play();
		} catch (IOException | ParseException | InterruptedException | LineUnavailableException | UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
	}

	public static void initGame() {
		//INICIALIZAMOS ALGUNAS DE LAS VARIABLES
		
		gameState = GameState.LOAD;
		gameView = new GameView();
	    boardMatrix = board.getBoard();
	    originalPositionPacman =boardMatrix[27][43];
	    createGhosts(ghostQuantity);
		pacman = new Pacman("pacman", originalPositionPacman);

	}

	private void initVisual() {
		//INICIALIZAMOS MAS VARIABLES, ESTA VEZ ORIENTADO A LO VISUAL
		
		gameView.setContentPane(layers);
		gameView.addKeyListener(this);
		dotMatrix = board.getDots();
		dotsView = new DotsView(dotMatrix, layers);
		playerView = new PlayerView(layers);
		pacmanView = new PacmanView(pacman, layers);
		createGhostViews(ghostQuantity);
		gameView.setVisible(true);
		
		pacman.addObserver(pacmanView);
		board.addObserver(dotsView);

	}

	// ARRANCA EL JUEGO
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
					sound.reproduceBeginning();
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
		}

	}
	
	private static void load() {
		//LA PANTALLA PRE-JUEGO

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

	private static void pausa() {
		JOptionPane.showMessageDialog(null, "la partida esta en pausa");
		gameState = GameState.NORMALMODE;

	}
	//TERMINO LA PARTIDA
	private static void postGame() { 
		if (firstTime) {
			JOptionPane.showMessageDialog(null, "la partida termino. Puntos: "+ board.score);
			gameView.remove(layers);
			postGameView= new PostGameView(gameView);
			layers.add(postGameView);
			gameView.setContentPane(postGameView);
			gameView.repaint();
			firstTime=false;
		}
	}

	private void superMode(Pacman pacman) throws InterruptedException {
		int slowGhosts=0; 
		superTime = 0;
		setGhostState(Ghost.GhostState.PUSSY);
		setPacmanState(Pacman.PacmanState.MOVE);
		while (gameState.equals(GameState.SUPERMODE)) {

			Thread.sleep(velocity);	
			
			//SUPERMODE: LOS GHOST SE MUEVEN MAS LENTOS
			slowGhosts++;
			if(slowGhosts==2) {
				moveGhosts();
				slowGhosts=0;
			}
			

			pacman.run(board);
			pacman.eatingGhosts(ghostsArray, pacman, board, board.getHellZone());

			superTime++;
			if (board.getDotRemoved().getSuper()) {
				superTime = 0;
			}
			if (superTime == 150) {
				gameState = GameState.NORMALMODE;
			}
			if (superTime >= 125)
				setGhostState(GhostState.HURRY);
			
		}

	}

	private void normalMode() throws InterruptedException, LineUnavailableException, IOException, UnsupportedAudioFileException {
		hellTime = 0;

		setGhostState(Ghost.GhostState.COURAGEOUS);
		setPacmanState(Pacman.PacmanState.MOVE);

		while (gameState.equals(GameState.NORMALMODE)) {

			Thread.sleep(velocity);

			moveGhosts();
			eatingPacman();
			pacman.run(board);
			
			//END GAME
			if (board.lifes <= 0) {
					gameState = GameState.POSTGAME;
					firstTime = true;
			}
			
		}
				
		}

	private void setPacmanState(PacmanState pacmanState) {
		pacman.setPacmanState(pacmanState);
		
	}
	private static void setGhostState(Ghost.GhostState ghostState) {
		//el objetivo cambia en funcion al estado del juego
		
		for (Ghost ghost : ghostsArray) {
			//SI LOS FANTASMAS ESTAN MUERTOS O EN EL INFIERNO NO CAMBIAN SU ESTADO, RESPETAN UN PROCESO DE CAMBIO INTERNO QUE TIENEN
			if (!ghost.getGhostState().equals(GhostState.DEATH)&& (!ghost.getGhostState().equals(GhostState.INHELL)))
					ghost.setGhostState(ghostState);
		}
		
	}
	private static void moveGhosts() {
		
		for (Ghost ghost : ghostsArray) {
			// GHOSTS: BUSCAN EL OBJETIVO Y SE MUEVEN

			
			if (ghost.getGhostState()==GhostState.DEATH) {
				ghost.run(pacman, gameState);
				ghost.run(pacman, gameState);
				ghost.run(pacman, gameState);
				ghost.run(pacman, gameState);
			}else {
				ghost.run(pacman, gameState);				
			}
			
			
		}
		//System.out.println();
	}
	private static void eatingPacman()
	{
		for (Ghost ghost : ghostsArray) {
			// GHOSTS: SI ENCUENTRAN AL PACMAN LO COMEN
			gameState = ghost.eatingPacman(pacman, board, gameState);

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

			gameView.requestFocus();

		} catch (IOException e) {
			System.out.println("error " + e);
			e.printStackTrace();
		}
	}
	// CARGA PARTIDA

	public static void recovery() throws FileNotFoundException, IOException, ParseException {
		// RECUPER LOS DATOS GUARDADOS DE LOS DOTS Y LOS VUELCA AL TABLERO

		ArrayList<Dot> dotsArraySaved = serializator.recover(board, pacman);
		board.setDots(dotsArraySaved);
		recoveryMenu.dispose();
		setGameState(GameState.NORMALMODE);
		setFirstTime(true);
	}
	// REINICIA POSICIONES EN EL TABLERO
	public static void respawn() {	

		pacman.setPosition(originalPositionPacman);

		for (Ghost ghost : ghostsArray) {
			// UBICA A LOS GHOST EN POSICION AZAROZA DENTRO DEL HELL
			ghost.setKeyOfHell(true);
			hellIndex=randomHellZoneSquare.nextInt(board.getHellZone().size());
			ghost.setPosition( board.getHellZone().get(hellIndex));
		}
		// REINICIA PARTIDA

		pacman.setAlive(true);
		firstTime = true;
		gameState = GameState.NORMALMODE;
	}
	
	// CREA MODELO DE GHOSTS
	private static void createGhosts(int ghostQuantity) {
	    ghostsArray = new ArrayList <Ghost>();
		
		int aux=1;
		int intelligence = 1;
		while (aux<= ghostQuantity) {
			hellIndex=randomHellZoneSquare.nextInt(board.getHellZone().size());
			ghostsArray.add(new Ghost("ghost"+aux,board.getHellZone().get(hellIndex), intelligence, board));
			aux++;
			intelligence +=2;
		}
	}
	
	
	public static GameState getGameState() {
		return gameState;
	}
	
	// CREA VISUAL DE GHOSTS Y COMIENZA A OBSERVAR MODELO DE GHOSTS
	private void createGhostViews(int ghostQuantity) {
	    ghostViewsArray = new ArrayList <CreaturesView>();
		int aux=1;
		while (aux<= ghostQuantity) {
			ghostViewsArray.add(new GhostView(ghostsArray.get(aux-1), layers));
			ghostsArray.get(aux-1).addObserver(ghostViewsArray.get(aux-1));
			aux++;
		}		
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

	
	// METODOS OBLIGADOS PARA EL KEYLISTENER
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