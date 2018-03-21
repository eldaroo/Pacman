package controller;


import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.io.FileNotFoundException;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.Random;


import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;


import org.json.simple.parser.ParseException;

import model.Board;
import model.BoardConfiguration;
import model.Direction;
import model.Dot;
import model.Fruit;
import model.GameState;
import model.Ghost;
import model.Ghost.GhostState;
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

	//MODELO
	private static Board board;
	private static Square[][] boardMatrix;
	private static ArrayList<Dot> dotMatrix;
	private static Fruit fruit;

	//VISUAL
	private static DotsView dotsView;
	private static PostGameView postGameView;
	private static GameView gameView; //JFRAME
	private static CreaturesView pacmanView;
	private static ArrayList<CreaturesView> ghostViewsArray;
	private static BeginMenu beginMenu;
	private static JLayeredPane layers;
	private static FruitView fruitView;
	private static ScoreView scoreView;
	//CRIATURAS
	private static Pacman pacman;
	private static ArrayList<Ghost> ghostsArray;

	//SERIALIZADOR y SQL
	private static Serializator serializator = new Serializator();
	private static MyDataAcces connection ;		
	private static ResultSet result;


	//ESTRUCTURA
	private static GameState gameState;
	private static boolean run = true;
	private static boolean firstTime = true;
	private static Square originalPositionPacman ; 
	private static int hellIndex=0;
	private static Random randomHellZoneSquare = new Random();
	private static Sounds sound = new Sounds();
	
	//DATOS
	private static int time;
	private static int retard = 66;
	private static int superTime = 0;
	private static int ghostQuantity = 5;
	
	public Game(BeginMenu beginMenu, Thread boardView, Board board, JLayeredPane layers, BoardConfiguration boardConfiguration)
	{
		//CON ESTO LAS VARIABLES IMPORTADAS DE CONTROLLER SE PUEDEN MANEJAR LOCALMENTE
		Game.beginMenu = beginMenu;
		Game.layers = layers;
		Game.board= board;
		//this.boardView= boardView;
		

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
	    fruit = new Fruit(board.getFruitPosition());
		pacman = new Pacman("pacman", originalPositionPacman);
		scoreView= new ScoreView();

	}

	private void initVisual() {
		//INICIALIZAMOS MAS VARIABLES, ESTA VEZ ORIENTADO A LO VISUAL
		

		gameView.setContentPane(layers);
		gameView.addKeyListener(this);
		dotMatrix = board.getDots();
		fruitView = new FruitView(fruit, layers);
		dotsView = new DotsView(dotMatrix, layers);
		pacmanView = new PacmanView(pacman, layers);
		createGhostViews(ghostQuantity);
		gameView.setVisible(true);
		
		fruit.addObserver(fruitView);
		pacman.addObserver(pacmanView);
		board.addObserver(dotsView);

	}

	// ARRANCA EL JUEGO
	private void play() throws IOException, ParseException, InterruptedException, LineUnavailableException, UnsupportedAudioFileException {
		boolean ever = true;
		time = 0;
		while (ever) {
			if (gameState!= GameState.POSTGAME)
			gameView.requestFocus();
			
			switch (gameState) {
			case LOAD:
				load();

				break;
			case RECOVERY:

				recovery();

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
			case NEXTLEVEL:
				nextLevel();
				break;
			}
		}

	}
	
	private void nextLevel() {
		int level = board.getLevel();
		level++;
		board.setLevel(level);
		board.makeDots();
		gameState=GameState.RESPAWN;
		
		
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
			//JOptionPane.showMessageDialog(null, "la partida termino. Puntos: "+ board.score);
			gameView.remove(layers);
			//gameView.repaint();
			postGameView= new PostGameView(gameView, postGameView, scoreView);
			//layers.add(postGameView);
			gameView.setContentPane(postGameView);
			firstTime=false;
		}
		
	}

	private void superMode(Pacman pacman) throws InterruptedException {
		int slowGhosts=0; 
		time++;
		superTime = 0;
		setGhostState(Ghost.GhostState.PUSSY);
		setPacmanState(Pacman.PacmanState.MOVE);
		while (gameState.equals(GameState.SUPERMODE)) {

			Thread.sleep(retard);	
			
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

	public static int getTime() {
		return time;
	}
	public static void setTime(int time) {
		Game.time = time;
	}
	private void normalMode() throws InterruptedException, LineUnavailableException, IOException, UnsupportedAudioFileException {

		setGhostState(Ghost.GhostState.COURAGEOUS);
		setPacmanState(Pacman.PacmanState.MOVE);

		while (gameState.equals(GameState.NORMALMODE)) {
			time++;
			Thread.sleep(retard);

			moveGhosts();
			eatingPacman();
			pacman.run(board);
			
			//END GAME
			if (board.getLifes() <= 0) {
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
	private static void moveGhosts() throws InterruptedException {
		
		for (Ghost ghost : ghostsArray) {
			// GHOSTS: BUSCAN EL OBJETIVO Y SE MUEVEN

				ghost.run(pacman);
		}
	}
	private static void eatingPacman()
	{
		for (Ghost ghost : ghostsArray) {
			// GHOSTS: SI ENCUENTRAN AL PACMAN LO COMEN
			ghost.eatingPacman(pacman, board);

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
		// RECUPER LOS DATOS GUARDADOS Y LOS VUELCA AL TABLERO

		ArrayList<Dot> dotsArraySaved = serializator.recover(board, pacman);
		board.setDots(dotsArraySaved);
		//recoveryMenu.dispose();
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
//SALVAMOS EL SCORE
	public static void saveScore(String name) 
	{
		try {
			connection = new MyDataAcces();
			connection.setQuery(name, board.score);
		} catch (Exception e) {
			System.out.println("error "+ e);
		}

	}
	//CARGAMOS EL SCORE Y LO VOLCAMOS AL JTEXTAREA DEL SCOREVIEW
	public static void getScore()
	{
		int aux=0;
		try {
			connection = new MyDataAcces();
			result = connection.getQuery();
			while (result.next()) {
				aux++;
				scoreView.getScoreTextArea().append(aux+". "+result.getString("name")+" "+result.getInt("score")+"\n");
			}
			
		} catch (Exception e) {
			System.out.println("error "+ e);
		}
		gameView.repaint();
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
	
	public static void restartGameVisual () {
		gameView.remove(postGameView);
		gameView.setContentPane(layers);
	}

}