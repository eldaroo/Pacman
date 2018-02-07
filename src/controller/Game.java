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
	static Ghost ghost;
	static CreaturesView ghostView;

	static JLayeredPane layers;
	static Pacman pacman;

	static CreaturesView pacmanView;

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
		ghost = new Ghost(boardMatrix[23][23]);
		pacman = new Pacman(boardMatrix[27][43]);

	}

	private void initVisual() {

		layers = new JLayeredPane();

		dotsView = new DotsView(dotMatrix, layers);
		boardView = new BoardView(boardMatrix, layers);

		pacmanView = new CreaturesView(pacman, layers);
		ghostView = new CreaturesView(ghost, layers);

		pacman.addObserver(pacmanView);
		ghost.addObserver(ghostView);
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
			ghost.pathFinder();
			ghost.move();
			pacman.move();
			board.eatingDot(pacman);

		}
	}

}
