package controller;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Iterator;

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
import model.Ghost;
import model.Pacman;
import model.Serializator;
import model.Square;
import visual.BeginMenu;
import visual.GameView;
import visual.PlayerView;
import visual.CreaturesView;
import visual.DotsView;

public class Game implements KeyListener {

	static Board board;
	static Square[][] boardMatrix;
	static GameView gameView;
	static Dot[][] dotMatrix;
	static DotsView dotsView;
	static Game game;
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
	static Serializator serializator = new Serializator();
	static enum GameState {LOAD, NORMALMODE, SUPERMODE, POSTGAME, PAUSA}
	static GameState gameState;
	static BeginMenu beginMenu ;
	static int superTime = 0;
	static JLayeredPane layers;
	static boolean run = true;
	static boolean firstTime= true;
	static PlayerView playerView;
	
	public static void main(String[] args) throws IOException, ParseException, InterruptedException {	
		initGame();
		play();
	}

	private static void initGame() {

		gameView = new GameView();
		BoardConfiguration boardconfiguration = new BoardConfiguration();
		board = new Board(boardconfiguration.level1BoardRecharged, boardconfiguration.level1BoardRecharged);
		boardMatrix = board.getBoard();
		dotMatrix = board.getDots();
		ghost1 = new Ghost("ghost1", boardMatrix[23][22]);
		ghost2 = new Ghost("ghost2", boardMatrix[23][22]);
		ghost3 = new Ghost("ghost3",boardMatrix[23][22]);
		ghost4 = new Ghost("ghost4",boardMatrix[23][22]);
		ghost5 = new Ghost("ghost5",boardMatrix[23][22]);
		pacman = new Pacman("pacman",boardMatrix[27][43]);
		gameState = GameState.LOAD;
		

	}

	private static void initVisual() {

		game = new Game();
		gameView.addKeyListener(game);
		layers = new JLayeredPane();
		dotsView = new DotsView(dotMatrix, layers);
		gameView.createBoardView(boardMatrix, layers);
		playerView = new PlayerView(layers);
		pacmanView = new CreaturesView(pacman, layers);
		ghostView1 = new CreaturesView(ghost1, layers);
		ghostView2 = new CreaturesView(ghost2, layers);
		ghostView3 = new CreaturesView(ghost3, layers);
		ghostView4 = new CreaturesView(ghost4, layers);
		ghostView5 = new CreaturesView(ghost5, layers);
		gameView.setContentPane(layers);		
		pacman.addObserver(pacmanView);
		ghost1.addObserver(ghostView1);
		ghost2.addObserver(ghostView2);
		ghost3.addObserver(ghostView3);
		ghost4.addObserver(ghostView4);
		ghost5.addObserver(ghostView5);
		board.addObserver(dotsView);

	}

	private static void play() throws IOException, ParseException, InterruptedException {
		boolean ever=true;
		while(ever)
		{
			gameView.requestFocus();

			System.out.println(gameState);

			switch (gameState) {
			case LOAD:
				if (firstTime)
				{
					beginMenu = new BeginMenu();
					gameView.setContentPane(beginMenu);
					firstTime = false;
				}
				if(beginMenu.wasPress())
				{
					gameState = GameState.NORMALMODE;
					firstTime = true;
					beginMenu.dispose();
				}
				break;
			case NORMALMODE:
				if(firstTime)
				{
					initVisual();
					firstTime = false;
				}
				normalMode();
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

	private static void pausa() {
			JOptionPane.showMessageDialog(null, "la partida esta en pausa");
			gameState = GameState.NORMALMODE;
		
	}

	private static void postGame() {
		// TODO Auto-generated method stub
		
	}

	private static void superMode(Ghost ghost, Pacman pacman) {
		pacman.eateable = false;
		ghost.eateable = true;
			
		while (board.superMode) {
				try {
					Thread.sleep(80);

			} catch (InterruptedException time) {

			}
				
			/*	
			creatures.get(indexPacman).eateable = false;
			for (Creature creature : creatures) {
				if ((creature.identy=="Ghost")&&(creature.alive)) {
					creature.eateable = true;
				}
			}
			 <<<CUANDO SEAN VARIAS CRIATURAS>>>	
			*/
			ghost.pathFinder(pacman, 10);	
			ghost.move();
			pacman.move();
			pacman.eatingGhosts(ghost, pacman);
			board.eatingDot(pacman);

			superTime++;
/*
			//32 segundos??
			if (superTime/12==30) {
			System.out.println("caca");
*/
			
			if (superTime==100) {
				superTime = 0;
				/*for (Creature creature : creatures) {
					if (creature.identy!="Pacman") {
						creature.eateable = false;
					}else creature.eateable = true;
				} <<<CUANDO SEAN VARIAS CRIATURAS>>> */
				pacman.eateable = true;
				ghost.eateable = false;
				board.superMode = false;
			}
			
			//creatures.get(indexPacman).eatingGhosts(creatures, indexPacman);
			//  <<<CUANDO SEAN VARIAS CRIATURAS>>>	
				
			
		}
		
	}

	private static void normalMode() throws InterruptedException {
		
		
		while (gameState.equals(GameState.NORMALMODE)) {
			gameView.requestFocus();

			Thread.sleep(80);
			
		ghost1.pathFinder(pacman, 1);
		ghost2.pathFinder(pacman, 3);
		ghost3.pathFinder(pacman, 5);
		ghost4.pathFinder(pacman, 7);
		ghost5.pathFinder(pacman, 9);
		
		ghost1.move();
		ghost2.move();
		ghost3.move();
		ghost4.move();
		ghost5.move();
		pacman.move();
		board.eatingDot(pacman);
		if (board.superMode) 
		System.exit(0);
			//SUPERMODE(ghost1, pacman);

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
			gameState =GameState.PAUSA;
			//Pause(!isPaused());
			break;
		}

		}
	}
	public static void save()
	{
		try {
			
			serializator.toPersist(pacman, ghost1, ghost2, ghost3, ghost4, ghost5);
		} catch (IOException e) {
			System.out.println("error " +e);
			e.printStackTrace();
		}
	}
	private void recover() throws FileNotFoundException, IOException, ParseException {

		JSONArray Data = serializator.recover();
		JSONObject jObj;
		for (int i = 0; i < Data.size(); i++) {
			
			jObj = (JSONObject) Data.get(i);
			String name = jObj.get("name").toString();
			String direction = jObj.get("direction").toString();
			String x = jObj.get("getX").toString();
			String y = jObj.get("getY").toString();
			
			System.out.println(name + " va para la "+ direction+ " y esta en la posición:"+ x +" , "+y);
		} //Agarra cada objeto JSON y le extrae sus variables
	}
	
	public static boolean isPaused() {
		return !run;
	}

	public void Pause(boolean run) {
		Game.run = !run;
	}

	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
