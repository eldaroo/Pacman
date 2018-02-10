package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLayeredPane;

import model.Board;
import model.BoardConfiguration;
import model.Direction;
import model.Dot;
import model.Ghost;
import model.Pacman;
import model.Square;
import visual.BoardView;
import visual.CreaturesView;
import visual.DotsView;

public class Game implements KeyListener {

	static Board board;
	static Square[][] boardMatrix;
	static BoardView boardView;
	static Dot[][] dotMatrix;
	static DotsView dotsView;
	static Game game = new Game();
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

	static int superTime = 0;

	static JLayeredPane layers;

	static boolean run = true;

	public static void main(String[] args) {

		game.initGame();
		game.initVisual();
		game.Play();

	}

	private void initGame() {

		BoardConfiguration boardconfiguration = new BoardConfiguration();
		board = new Board(boardconfiguration.level1BoardRecharged, boardconfiguration.level1BoardRecharged);
		boardMatrix = board.getBoard();
		dotMatrix = board.getDots();
		ghost1 = new Ghost(boardMatrix[23][22]);
		ghost2 = new Ghost(boardMatrix[23][22]);
		ghost3 = new Ghost(boardMatrix[23][22]);
		ghost4 = new Ghost(boardMatrix[23][22]);
		ghost5 = new Ghost(boardMatrix[23][22]);
		pacman = new Pacman(boardMatrix[27][43]);
		
		//ghost = new Ghost(boardMatrix[23][23]);

	}

	private void initVisual() {

		layers = new JLayeredPane();

		dotsView = new DotsView(dotMatrix, layers);
		boardView = new BoardView(boardMatrix, layers);

		pacmanView = new CreaturesView(pacman, layers);
		ghostView1 = new CreaturesView(ghost1, layers);
		ghostView2 = new CreaturesView(ghost2, layers);
		ghostView3 = new CreaturesView(ghost3, layers);
		ghostView4 = new CreaturesView(ghost4, layers);
		ghostView5 = new CreaturesView(ghost5, layers);


		pacman.addObserver(pacmanView);
		ghost1.addObserver(ghostView1);
		ghost2.addObserver(ghostView2);
		ghost3.addObserver(ghostView3);
		ghost4.addObserver(ghostView4);
		ghost5.addObserver(ghostView5);
		board.addObserver(dotsView);

		boardView.addKeyListener(game);
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
		}

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	private void Play() {
		while (run) {
			try {
				Thread.sleep(80);

			} catch (InterruptedException time) {

			}
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
				SUPERMODE(ghost1, pacman);
		}
	}

	public void  SUPERMODE(Ghost ghost, Pacman pacman) {
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
				
			ghost.move();
			pacman.move();
			pacman.eatingGhosts(ghost, pacman);
			board.eatingDot(pacman);

			superTime++;
			System.out.println("caca");

			//32 segundos??
			if (superTime/12==30) {
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
}
