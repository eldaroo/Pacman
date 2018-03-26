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
import model.Board;
import model.Direction;
import model.Dot;
import model.Actions;
import model.Fruit;
import model.GameState;
import model.Ghost;
import model.Ghost.GhostState;
import model.IA;
import model.MyDataAcces;
import model.Pacman;
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
	//private static Square originalPositionPacman ; 
	private static Sounds sound = new Sounds();
	
	//DATOS
	private static int time=0;
	private static int retard = 66;
	private static int superTime = 0;
	private static int ghostQuantity = 5;

	
	//CON ESTO LAS VARIABLES IMPORTADAS DE CONTROLLER SE PUEDEN MANEJAR LOCALMENTE
	public Game(BeginMenu beginMenu, JLayeredPane layers, Board board)
	{
		Game.beginMenu = beginMenu;
		Game.layers = layers;
		Game.board = board;
	}

	//EL METODO PRINCIPAL (ESTAMOS EN UN THREAD)
	public void run () {
		initGame();
		try {
			play();
		} catch (IOException | ParseException | InterruptedException | LineUnavailableException | UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
	}

	//INICIALIZAMOS ALGUNAS DE LAS VARIABLES
	public static void initGame() {
		
		gameState = GameState.LOAD;
		gameView = new GameView();
	    boardMatrix = Board.getBoard();

	    Board.makeDots();
	    Actions.createGhosts(ghostQuantity);
		pacman = new Pacman("pacman", boardMatrix[27][43]);
		scoreView= new ScoreView();
	}

	//INICIALIZAMOS MAS VARIABLES, ESTA VEZ ORIENTADO A LO VISUAL
	private void initVisual() {
		
		gameView.setContentPane(layers);
		gameView.addKeyListener(this);
		dotMatrix = Board.getDots();
		fruitView = new FruitView( layers);
		dotsView = new DotsView(dotMatrix, layers);
		pacmanView = new PacmanView(pacman, layers);
		createGhostViews(ghostQuantity);
		gameView.setVisible(true);
		
		Board.observeFruit(fruitView);
		
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
				System.exit(0);
				//postGame();
				break;
			case NEXTLEVEL:
				nextLevel();
				break;
			}
			
	
			//END GAME
			if (Board.getLifes() <= 0) {
					gameState = GameState.POSTGAME;
					firstTime = true;
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
	
	private void normalMode() throws InterruptedException, LineUnavailableException, IOException, UnsupportedAudioFileException {

		setGhostState(Ghost.GhostState.COURAGEOUS);
		
		if (firstTime) {
			initVisual();
			Sounds.reproduceBeginning();
			firstTime = false;
		}
		while (gameState.equals(GameState.NORMALMODE)) {
			time++;
			Thread.sleep(retard);
			Actions.moveGhosts();
			Actions.eatingPacman(pacman);
			runPacman();
			board.update();

		}	
	}

	private void superMode() throws InterruptedException {
		superTime = 0;
		setGhostState(Ghost.GhostState.PUSSY);
		
		while (gameState.equals(GameState.SUPERMODE)) {

			Thread.sleep(retard);	
			time++;

			Actions.moveGhostsSlowed(2);
			runPacman();
			//runGhost
			Actions.eatingGhosts(pacman);

			superTime++;
			if (Board.getDotRemoved().getSuper()) {
				superTime = 0;
			}
			if (superTime == 150) {
				gameState = GameState.NORMALMODE;
			}
			if (superTime >= 125)
				setGhostState(GhostState.HURRY);	
			board.update();

		}

	}	

	// REINICIA POSICIONES EN EL TABLERO
	public static void respawn() {	

		Actions.respawnCreatures(pacman);

		// REINICIA PARTIDA

		Pacman.setPacmanState(Pacman.PacmanState.MOVE);
		
		firstTime = true;
		gameState = GameState.NORMALMODE;
	}
	
	private static void pausa() {
		JOptionPane.showMessageDialog(null, "la partida esta en pausa");
		gameState = GameState.NORMALMODE;
	}

	private void nextLevel() {
		Board.upLevel();
		board.makeDots();
		gameState=GameState.RESPAWN;
		retard= retard/2;
	}

	//TERMINO LA PARTIDA
	private static void postGame() { 
		if (firstTime) {
			//JOptionPane.showMessageDialog(null, "la partida termino. Puntos: "+ board.getScore());
			gameView.remove(layers);
			postGameView= new PostGameView(gameView, postGameView, scoreView);
			gameView.setContentPane(postGameView);
			firstTime=false;
		}
		
	}
	
	private static void setGhostState(Ghost.GhostState ghostState) {
		
		for (Ghost ghost : ghostsArray) {
			//SI LOS FANTASMAS ESTAN MUERTOS O EN EL INFIERNO NO CAMBIAN SU ESTADO, RESPETAN UN PROCESO DE CAMBIO INTERNO QUE TIENEN
			if (!ghost.getGhostState().equals(GhostState.DEATH)&& (!ghost.getGhostState().equals(GhostState.INHELL)))
					ghost.setGhostState(ghostState);
		}
	}
	private void createGhostViews(int ghostQuantity) {
	    ghostViewsArray = new ArrayList <CreaturesView>();
		int aux=1;
		while (aux<= ghostQuantity) {
			ghostViewsArray.add(new GhostView(getGhostsArray().get(aux-1), layers));
			ghostsArray.get(aux-1).addObserver(ghostViewsArray.get(aux-1));
			aux++;
		}		
	}
	public void runPacman()
	{
		pacman.move();
		Actions.eatingDot(pacman);
		Actions.eatingFruit(pacman);
		
		Actions.updateFruit();
		
	}
	
	// GUARDA PARTIDA
	public static void save() {
		try {

			serializator.toPersist(board, pacman);

			gameView.requestFocus();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// CARGA PARTIDA
	public static void recovery() throws FileNotFoundException, IOException, ParseException {
		
		ArrayList<Dot> dotsArraySaved = serializator.recover(board, pacman);
		Board.setDots(dotsArraySaved);
		//recoveryMenu.dispose();
		setGameState(GameState.NORMALMODE);
		setFirstTime(true);
	}

	//SALVAMOS EL SCORE
	public static void saveScore(String name) 
	{
		try {
			connection = new MyDataAcces();
			connection.setQuery(name, Board.getScore());
		} catch (Exception e) {
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
	public static  ArrayList<Ghost> getGhostsArray() {
		// TODO Auto-generated method stub
		return ghostsArray;
	}

	public static void setGhostsArray(ArrayList<Ghost> arrayList) {
		ghostsArray=arrayList;
	}

	public static void setPacman(Pacman pacman) {
		Game.pacman = pacman;
	}

	public static void addGhost(Ghost ghost) {
		ghostsArray.add(ghost);
	}

	public static Pacman getPacman() {
		return pacman;
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