package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;

public class Board extends Observable implements Serializable {

	private static final long serialVersionUID = -6472116531941544087L;

	Square[][] board;
	Dot dotRemoved;
	boolean pacmanEatNewDot;
	public boolean superMode = false;
	Dot[][] dots;
	
	ArrayList<Square> teleportList = new ArrayList<Square>();

	public Board(int[][] levelBoard, int[][] levelDots) {
		makeBoard(levelBoard);
		makeDots(levelDots);
	}

	public void eatingDot(Pacman pacman) {

		pacmanEatNewDot = false;
		if (dots[pacman.getBoardPosition().getX()][pacman.getBoardPosition().getY()] != null) {

			dotRemoved = dots[pacman.getBoardPosition().getX()][pacman.getBoardPosition().getY()];
			if (dotRemoved.superDot == true)
				superMode=true;
			dots[dotRemoved.getBoardPosition().getX()][dotRemoved.getBoardPosition().getY()] = null;
			pacmanEatNewDot = true;
		}

		setChanged();
		notifyObservers();
	}

	public Square[][] getBoard() {
		return board;
	}

	public Dot getDotRemoved() {
		return dotRemoved;
	}

	public Dot[][] getDots() {
		return dots;
	}

	private void linkTeleports() {
		teleportList.get(0).setLeft(teleportList.get(5));
		teleportList.get(1).setUp(teleportList.get(2));
		teleportList.get(2).setDown(teleportList.get(1));
		teleportList.get(3).setUp(teleportList.get(4));
		teleportList.get(4).setDown(teleportList.get(3));
		teleportList.get(5).setRight(teleportList.get(0));

	}

	private void makeBoard(int[][] levelBoard) {
		board = new Square[levelBoard.length][levelBoard.length];
		for (int i = 0; i < levelBoard.length; i++) {
			for (int j = 0; j < levelBoard.length; j++) {
				switch (levelBoard[i][j]) {
				case 1:

					board[i][j] = new Path();
					break;
				case 0:
					board[i][j] = new Wall();
					break;
				case 2:
					board[i][j] = new FalsePath();
					break;
				case 4:
					board[i][j] = new Path();
					break;
				case 5:
					board[i][j] = new Path();
					break;
				case 6:
					board[i][j] = new Hell();
					break;
				case 9:
					board[i][j] = new Path();
					teleportList.add(board[i][j]);

				}
				board[i][j].setBoardPosition(new Position(i, j));
			}
		}

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {

				if (j + 1 < board.length) {
					board[i][j].setDown(board[i][j + 1]);

				}
				if (j - 1 >= 0) {
					board[i][j].setUp(board[i][j - 1]);
				}
				if (i - 1 >= 0) {
					board[i][j].setLeft(board[i - 1][j]);
				}
				if (i + 1 < board.length) {
					board[i][j].setRight(board[i + 1][j]);
				}
			}
		}
		linkTeleports();

	}

	private void makeDots(int[][] levelDots) {
		dots = new Dot[levelDots.length][levelDots.length];
		for (int i = 0; i < levelDots.length; i++) {
			for (int j = 0; j < levelDots.length; j++) {
				switch (levelDots[i][j]) {
				case 4:

					dots[i][j] = new Dot();
					break;
				case 5:
					dots[i][j] = new SuperDot();
					break;

				}
				if (dots[i][j] != null)
					dots[i][j].position = board[i][j];
			}
		}

	}

	public void setBoard(Square[][] board) {
		this.board = board;
	}

	public void setDotRemoved(Dot dotRemoved) {
		this.dotRemoved = dotRemoved;
	}

	public void setDots(Dot[][] dots) {
		this.dots = dots;
	}

	public boolean pacmanEatNewDot() {
		// TODO Auto-generated method stub
		return pacmanEatNewDot;
	}
}
