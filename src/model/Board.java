package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;

<<<<<<< HEAD
public class Board implements Serializable {
=======
public class Board extends Observable implements Serializable {
>>>>>>> 23109994925dd78db28c484068f24dbf2c728aa5

	/**
	 *
	 */
	private static final long serialVersionUID = -6472116531941544087L;

	Square[][] board;
	Dot[][] dots;
<<<<<<< HEAD
	ArrayList<Square> teleportList = new ArrayList<Square>();
=======
	ArrayList <Square> teleportList = new ArrayList <Square>();
	Dot dotRemoved;

	public Dot getDotRemoved() {
		return dotRemoved;
	}

	public void setDotRemoved(Dot dotRemoved) {
		this.dotRemoved = dotRemoved;
	}
>>>>>>> 23109994925dd78db28c484068f24dbf2c728aa5

	public Board(int[][] levelBoard, int[][] levelDots) {
		makeBoard(levelBoard);
		makeDots(levelDots);
	}

	public Square[][] getBoard() {
		return board;
	}

	public Dot[][] getDots() {
		return dots;
	}

<<<<<<< HEAD
	private void linkTeleports() {
		teleportList.get(0).setLeft(teleportList.get(5));
		teleportList.get(1).setUp(teleportList.get(2));
		teleportList.get(2).setDown(teleportList.get(1));
		teleportList.get(3).setUp(teleportList.get(4));
		teleportList.get(4).setDown(teleportList.get(3));
		teleportList.get(5).setRight(teleportList.get(0));

	}
	/*
	 * private void teleportCreature(Creature creature) { if (teleportList.get(0) ==
	 * creature.position) { creature.setPosition(teleportList.get(5).getLeft()); }
	 * if (teleportList.get(1).equals(creature.position)) {
	 * creature.setPosition(teleportList.get(2).getUp()); } if
	 * (teleportList.get(2).equals(creature.position)) {
	 * creature.setPosition(teleportList.get(1).getDown()); } if
	 * (teleportList.get(3).equals(creature.position)) {
	 * creature.setPosition(teleportList.get(4).getUp()); } if
	 * (teleportList.get(4).equals(creature.position)) {
	 * creature.setPosition(teleportList.get(3).getDown()); } if
	 * (teleportList.get(5).equals(creature.position)) {
	 * creature.setPosition(teleportList.get(0).getRight()); } }
	 */

	private void lookingForDot() {

	}
=======
>>>>>>> 23109994925dd78db28c484068f24dbf2c728aa5

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
				case 1:

					dots[i][j] = new Dot();
					break;
				case 2:
					dots[i][j] = new SuperDot();
					break;

				}
				if (dots[i][j] != null)
					dots[i][j].position = board[i][j];
			}
		}

	}

<<<<<<< HEAD
	/*
	 * public void move(Creature creature) {
	 *
	 * if(creature.position.getClass().getName()== "model.Teleport") {
	 * teleportCreature(creature); } Direction potentialDirection =
	 * creature.getPotentialDirection(); Direction direction =
	 * creature.getDirection(); Square position = creature.getPosition(); Square
	 * nextPotentialPosition = position.get(potentialDirection); Square nextPosition
	 * = position.get(direction);
	 *
	 * if (nextPotentialPosition.isNavegable(creature)) {
	 * creature.direction=potentialDirection;
	 * creature.setPosition(nextPotentialPosition);
	 *
	 * } else if((potentialDirection !=
	 * direction)&&(nextPosition.isNavegable(creature))) {
	 *
	 * creature.setPosition(nextPosition);
	 *
	 * }
	 *
	 * if(creature.identy.equals("Pacman")){ if
	 * (creature.position.getClass().getName()=="model.Dot"){ creature.eatDot();
	 * }else if (creature.position.getClass().getName()=="model.SuperDot"){
	 *
	 * } } }
	 */
=======
	public void move(Creature creature) {

		if(creature.position.getClass().getName()== "model.Teleport")
		{
			teleportCreature(creature);
		}
		Direction potentialDirection = creature.getPotentialDirection();
		Direction direction = creature.getDirection();
		Square position = creature.getPosition();
		Square nextPotentialPosition = position.get(potentialDirection);
		Square nextPosition = position.get(direction);

		if (nextPotentialPosition.isNavegable(creature)) {
			creature.direction=potentialDirection;
			creature.setPosition(nextPotentialPosition);

		} else if((potentialDirection != direction)&&(nextPosition.isNavegable(creature)))
		{

			creature.setPosition(nextPosition);

		}


		if (creature.identy.equals("Pacman")
				&& (dots[creature.getBoardPosition().getX()][creature.getBoardPosition().getY()] != null)) {

			removeDot(dots[creature.getBoardPosition().getX()][creature.getBoardPosition().getY()]);
			// creature.eatDot();

		}

	}

>>>>>>> 23109994925dd78db28c484068f24dbf2c728aa5
	public void setBoard(Square[][] board) {
		this.board = board;
	}

	public void setDots(Dot[][] dots) {
		this.dots = dots;
	}
<<<<<<< HEAD
=======

	public void removeDot(Dot dot) {
		dotRemoved = dot;
		if (dots[dot.getBoardPosition().getX()][dot.getBoardPosition().getY()].superDot) {
			dots[dot.getBoardPosition().getX()][dot.getBoardPosition().getY()] = null;
			// modeSuperDotOn();
		}else
		{
			dots[dot.getBoardPosition().getX()][dot.getBoardPosition().getY()] = null;

		}
		System.out.println(dotRemoved);

		setChanged();
		notifyObservers();
	}

	private void teleportCreature(Creature creature) {
		if(teleportList.get(0)==creature.position)
		{
			creature.setPosition(teleportList.get(5).getLeft());
		}
		if(teleportList.get(1).equals(creature.position))
		{
			creature.setPosition(teleportList.get(2).getUp());
		}
		if(teleportList.get(2).equals(creature.position))
		{
			creature.setPosition(teleportList.get(1).getDown());
		}
		if(teleportList.get(3).equals(creature.position))
		{
			creature.setPosition(teleportList.get(4).getUp());
		}
		if(teleportList.get(4).equals(creature.position))
		{
			creature.setPosition(teleportList.get(3).getDown());
		}
		if(teleportList.get(5).equals(creature.position))
		{
			creature.setPosition(teleportList.get(0).getRight());
		}
	}
>>>>>>> 23109994925dd78db28c484068f24dbf2c728aa5
}
