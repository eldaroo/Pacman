package model;

import java.util.ArrayList;
import java.util.Random;

import model.squares.FalseHell;
import model.squares.FalsePath;
import model.squares.FalseTeleport;
import model.squares.Hell;
import model.squares.HellGate;
import model.squares.Path;
import model.squares.Square;
import model.squares.Teleport;
import model.squares.Wall;

public class BoardConfiguration {
	int cont = 0;

	static private Square[][] board;
	static private HellGate hellGate = new HellGate();
	static private ArrayList<Square> hellZone = new ArrayList<Square>();
	static private ArrayList<Square> teleportList = new ArrayList<Square>();
	static private ArrayList<Dot> dots;
	static Random randomHellZoneSquare = new Random();


	public static Square[][] makeBoard() {
		board = new Square[levelBoard.length][levelBoard.length];
		for (int i = 0; i < levelBoard.length; i++) {
			for (int j = 0; j < levelBoard.length; j++) {
				switch (levelBoard[i][j]) {
				case '\u0000':
					board[i][j] = new Wall();
					break;
				case '\u0001':
					board[i][j] = new Path();
					break;
				case '\u0002':
					board[i][j] = new FalsePath();
					break;
				case '\u0003':
					board[i][j] = new FalseTeleport();
					break;
				case '\u0004':
					board[i][j] = new Path();
					break;
				case '\u0005':
					board[i][j] = new Path();
					break;
				case '\u0006':
					board[i][j] = new Hell();
					hellZone.add(board[i][j]);
					break;
				case '\u0007':
					hellGate.setBoardPosition(new Position(i, j));
					board[i][j] = hellGate;
					break;
				case '\u0008':
					board[i][j] = new FalseHell();
					break;
				case '\u0009':
					board[i][j] = new Teleport();
					teleportList.add(board[i][j]);
					break;

				}

				// ASIGNA AL CASILLERO EN UNA POSICION DEL TABLERO
				board[i][j].setBoardPosition(new Position(i, j));
				Board.setOriginalPacmanPosition(board[27][43]);
			}
		}
		// ENLAZA CADA CASILLERO CON SU ADYACENTE
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
		// EJECUTA EL ENLACE DE CASILLEROS TELEPORT
		linkTeleports();
		return board;
	}

	// CREA LOS DOTS Y SUPER DOTS Y LES ASIGNA UNA POSICI�N EN EL TABLERO
	public static ArrayList<Dot> makeDots() {
		Dot dot;
		SuperDot superDot;
		dots = new ArrayList<Dot>();

		for (int i = 0; i < levelBoard.length; i++) {
			for (int j = 0; j < levelBoard.length; j++) {
				switch (levelBoard[i][j]) {
				case 4:
					dot = new Dot();
					dot.setPosition(board[i][j]);
					dots.add(dot);
					break;
				case 5:
					superDot = new SuperDot();
					superDot.setPosition(board[i][j]);
					dots.add(superDot);
					break;
				}
			}
		}
		return dots;
	}

	// ESTABLECE LOS CASILLEROS SIGUIENTES A LOS TELEPORT
	private static void linkTeleports() {
		teleportList.get(0).setLeft(teleportList.get(5));
		teleportList.get(1).setUp(teleportList.get(2));
		teleportList.get(2).setDown(teleportList.get(1));
		teleportList.get(3).setUp(teleportList.get(4));
		teleportList.get(4).setDown(teleportList.get(3));
		teleportList.get(5).setRight(teleportList.get(0));
	}

	
	private static char[][] levelBoard = {



			{ 0,0,0, 0,0,0, 0,0,0, 0,0,0, 0,0,0, 0,0,0, 0,0,0, 0,0,0, 0,0,0, 2,9,2, 0,0,0, 0,0,0, 0,0,0, 0,0,0, 0,0,0, 0,0,0, 0,0,0, 0,0,0, 0,0,0, 0,0,0, },
			{ 0,0,0, 0,0,0, 0,0,0, 0,0,0, 0,0,0, 0,0,0, 0,0,0, 0,0,0, 0,0,0, 2,1,2, 0,0,0, 0,0,0, 0,0,0, 0,0,0, 0,0,0, 0,0,0, 0,0,0, 0,0,0, 0,0,0, 0,0,0, },
			{ 0,0,0, 0,0,0, 0,0,0, 0,0,0, 0,0,0, 0,0,0, 0,0,0, 0,0,0, 0,0,0, 2,1,2, 0,0,0, 0,0,0, 0,0,0, 0,0,0, 0,0,0, 0,0,0, 0,0,0, 0,0,0, 0,0,0, 0,0,0, },

			{ 0,0,0, 2,2,2, 2,2,2, 2,2,2, 2,2,2, 2,2,2, 2,2,2, 0,0,0, 0,0,0, 2,1,2, 0,0,0, 0,0,0, 2,2,2 ,2,2,2 ,2,2,2 ,0,0,0 ,2,2,2 ,2,2,2 ,2,2,2 ,0,0,0 },
			{ 0,0,0, 2,4,1, 1,4,1, 1,4,1, 1,5,1, 1,4,1, 1,4,2, 0,0,0, 0,0,0, 2,4,2, 0,0,0, 0,0,0, 2,4,1 ,1,4,1 ,1,5,2 ,0,0,0 ,2,4,1 ,1,4,1 ,1,4,2 ,0,0,0 },
			{ 0,0,0, 2,1,2, 2,2,2, 2,2,2, 2,1,2, 2,2,2, 2,1,2, 0,0,0, 0,0,0, 2,1,2, 0,0,0, 0,0,0, 2,1,2 ,2,2,2 ,2,1,2 ,0,0,0 ,2,1,2 ,2,2,2 ,2,1,2 ,0,0,0 },
			
			{ 0,0,0, 2,1,2, 0,0,0, 0,0,0, 2,1,2, 0,0,0, 2,1,2, 0,0,0, 0,0,0, 2,1,2, 0,0,0, 0,0,0, 2,1,2 ,0,0,0 ,2,1,2 ,2,2,2 ,2,1,2 ,0,0,0 ,2,1,2 ,0,0,0 },
			{ 0,0,0, 2,4,2, 0,0,0, 0,0,0, 2,4,2, 0,0,0, 2,4,2, 0,0,0, 0,0,0, 2,4,2, 0,0,0, 0,0,0, 2,4,2 ,0,0,0 ,2,4,1 ,1,4,1 ,1,4,2 ,0,0,0 ,2,4,2 ,0,0,0 },
			{ 0,0,0, 2,1,2, 0,0,0, 0,0,0, 2,1,2, 0,0,0, 2,1,2, 0,0,0, 0,0,0, 2,1,2, 0,0,0, 0,0,0, 2,1,2 ,0,0,0 ,2,2,2 ,2,2,2 ,2,1,2 ,0,0,0 ,2,1,2 ,0,0,0 },
			
			{ 0,0,0, 2,1,2, 0,0,0, 0,0,0, 2,1,2, 0,0,0, 2,1,2, 0,0,0, 0,0,0, 2,1,2, 0,0,0, 0,0,0, 2,1,2 ,0,0,0 ,0,0,0 ,0,0,0 ,2,1,2 ,0,0,0 ,2,1,2 ,0,0,0 },
			{ 0,0,0, 2,4,2, 0,0,0, 0,0,0, 2,4,2, 0,0,0, 2,4,2, 0,0,0, 0,0,0, 2,4,2, 0,0,0, 0,0,0, 2,4,2 ,0,0,0 ,0,0,0 ,0,0,0 ,2,4,2 ,0,0,0 ,2,4,2 ,0,0,0 },
			{ 0,0,0, 2,1,2, 0,0,0, 0,0,0, 2,1,2, 0,0,0, 2,1,2, 0,0,0, 0,0,0, 2,1,2, 0,0,0, 0,0,0, 2,1,2 ,0,0,0 ,0,0,0 ,0,0,0 ,2,1,2 ,0,0,0 ,2,1,2 ,0,0,0 },
			
			{ 0,0,0, 2,1,2, 2,2,2, 2,2,2, 2,1,2, 2,2,2, 2,1,2, 2,2,2, 2,2,2, 2,1,2, 2,2,2, 2,2,2, 2,1,2 ,2,2,2 ,2,2,2 ,2,2,2 ,2,1,2 ,0,0,0 ,2,1,2 ,0,0,0 },
			{ 0,0,0, 2,4,1, 1,4,1, 1,4,1, 1,4,1, 1,4,1, 1,4,1, 1,4,1, 1,4,1, 1,4,1, 1,4,1, 1,4,1, 1,4,1 ,1,4,1 ,1,4,1 ,1,4,1 ,1,4,2 ,0,0,0 ,2,4,2 ,0,0,0 },
			{ 0,0,0, 2,1,2, 2,2,2, 2,2,2, 2,1,2, 2,2,2, 2,2,2, 2,2,2, 2,2,2, 2,1,2, 2,2,2, 2,2,2, 2,1,2 ,2,2,2 ,2,1,2 ,2,2,2 ,2,2,2 ,0,0,0 ,2,1,2 ,0,0,0 },
			
			{ 0,0,0, 2,1,2, 0,0,0, 0,0,0, 2,1,2, 0,0,0, 0,0,0, 0,0,0, 0,0,0, 2,1,2, 0,0,0, 0,0,0, 2,1,2 ,0,0,0 ,2,1,2 ,0,0,0 ,0,0,0 ,0,0,0 ,2,1,2 ,0,0,0 },
			{ 0,0,0, 2,4,2, 0,0,0, 0,0,0, 2,4,2, 0,0,0, 0,0,0, 0,0,0, 0,0,0, 2,4,2, 0,0,0, 0,0,0, 2,4,2 ,0,0,0 ,2,4,2 ,0,0,0 ,0,0,0 ,0,0,0 ,2,4,2 ,0,0,0 },
			{ 0,0,0, 2,1,2, 0,0,0, 0,0,0, 2,1,2, 0,0,0, 0,0,0, 0,0,0, 0,0,0, 2,1,2, 0,0,0, 0,0,0, 2,1,2 ,0,0,0 ,2,1,2 ,0,0,0 ,0,0,0 ,0,0,0 ,2,1,2 ,0,0,0 },
			
			{ 0,0,0, 2,1,2, 0,0,0, 0,0,0, 2,1,2, 2,2,2, 2,2,2, 2,2,2, 2,2,2, 2,1,2, 2,2,2, 2,2,2, 2,1,2 ,0,0,0 ,2,1,2 ,2,2,2 ,2,2,2 ,0,0,0 ,2,1,2 ,0,0,0 },
			{ 0,0,0, 2,4,2, 0,0,0, 0,0,0, 2,4,1, 1,4,1, 1,4,1, 1,4,1, 1,4,1, 1,4,1, 1,4,1, 1,4,1, 1,4,2 ,0,0,0 ,2,4,1 ,1,4,1 ,1,4,2 ,0,0,0 ,2,4,2 ,0,0,0 },
			{ 0,0,0, 2,1,2, 0,0,0, 0,0,0, 2,1,2, 2,2,2, 2,2,2, 2,1,2, 2,2,2, 2,2,2, 2,1,2, 2,2,2, 2,1,2 ,0,0,0 ,2,1,2 ,2,2,2 ,2,1,2 ,0,0,0 ,2,1,2 ,0,0,0 },
			
			{ 0,0,0, 2,1,2, 0,0,0, 0,0,0, 2,1,2, 0,0,0, 0,0,0, 2,1,2, 0,0,0, 0,0,0, 2,1,2, 0,0,0, 2,1,2 ,0,0,0 ,2,1,2 ,0,0,0 ,2,1,2 ,0,0,0 ,2,1,2 ,0,0,0 },
			{ 0,0,0, 2,4,2, 0,0,0, 0,0,0, 2,4,2, 0,0,0, 0,0,0, 2,4,2, 0,0,0, 0,0,0, 2,4,2, 0,0,0, 2,4,2 ,0,0,0 ,2,4,2 ,0,0,0 ,2,4,2 ,0,0,0 ,2,4,2 ,0,0,0 },
			{ 0,0,0, 2,1,2, 0,0,0, 0,0,0, 2,1,2, 0,0,0, 0,0,0, 2,1,2, 8,8,8, 8,0,0, 2,1,2, 0,0,0, 2,1,2 ,0,0,0 ,2,1,2 ,0,0,0 ,2,1,2 ,0,0,0 ,2,1,2 ,0,0,0 },
			
			{ 2,2,2, 2,1,2, 2,2,2, 2,2,2, 2,1,2, 0,0,0, 0,0,0, 2,1,2, 8,6,6, 8,0,0, 2,1,2, 0,0,0, 2,1,2 ,2,2,2 ,2,1,2 ,0,0,0 ,2,1,2 ,2,2,2 ,2,1,2 ,2,2,2 },
			{ 9,1,1, 1,4,1, 1,4,1, 1,4,1, 1,4,2, 0,0,0, 0,0,0, 2,4,2, 8,6,6, 8,0,0, 2,4,2, 0,0,0, 2,4,1 ,1,4,1 ,1,4,2 ,0,0,0 ,2,4,1 ,1,4,1 ,1,4,1 ,1,1,9 },
			{ 2,2,2, 2,2,2, 2,2,2, 2,2,2, 2,1,2, 0,0,0, 0,0,0, 2,1,2, 8,6,6, 8,0,0, 2,1,2, 0,0,0, 2,2,2 ,2,2,2 ,2,1,2 ,0,0,0 ,2,2,2 ,2,2,2 ,2,1,2 ,2,2,2 },
			
			{ 0,0,0, 0,0,0, 0,0,0, 0,0,0, 2,1,2, 0,0,0, 0,0,0, 2,1,1, 8,6,6, 8,0,0, 2,1,2, 0,0,0, 0,0,0 ,0,0,0 ,2,1,2 ,0,0,0 ,0,0,0 ,0,0,0 ,2,1,2 ,0,0,0 },
			{ 0,0,0, 0,0,0, 0,0,0, 0,0,0, 2,4,2, 0,0,0, 0,0,0, 2,4,1, 7,6,6, 8,0,0, 2,4,2, 0,0,0, 0,0,0 ,0,0,0 ,2,4,2 ,0,0,0 ,0,0,0 ,0,0,0 ,2,4,2 ,0,0,0 },
			{ 0,0,0, 0,0,0, 0,0,0, 0,0,0, 2,1,2, 0,0,0, 0,0,0, 2,1,1, 8,6,6, 8,0,0, 2,1,2, 0,0,0, 0,0,0 ,0,0,0 ,2,1,2 ,0,0,0 ,0,0,0 ,0,0,0 ,2,1,2 ,0,0,0 },
			
			{ 0,0,0, 2,2,2, 2,2,2, 2,2,2, 2,1,2, 0,0,0, 0,0,0, 2,1,2, 8,6,6, 8,0,0, 2,1,2, 0,0,0, 2,2,2 ,2,2,2 ,2,1,2 ,0,0,0 ,2,2,2 ,2,2,2 ,2,1,2 ,0,0,0 },
			{ 0,0,0, 2,4,1, 1,4,1, 1,4,1, 1,4,2, 0,0,0, 0,0,0, 2,4,2, 8,6,6, 8,0,0, 2,4,2, 0,0,0, 2,4,1 ,1,4,1 ,1,4,2 ,0,0,0 ,2,4,1 ,1,4,1 ,1,4,2 ,0,0,0 },
			{ 0,0,0, 2,1,2, 2,2,2, 2,2,2, 2,1,2, 0,0,0, 0,0,0, 2,1,2, 8,6,6, 8,0,0, 2,1,2, 0,0,0, 2,1,2 ,2,2,2 ,2,1,2 ,0,0,0 ,2,1,2 ,2,2,2 ,2,1,2 ,0,0,0 },
			
			{ 0,0,0, 2,1,2, 0,0,0, 0,0,0, 2,1,2, 0,0,0, 0,0,0, 2,1,2, 8,8,8, 8,0,0, 2,1,2, 0,0,0, 2,1,2 ,0,0,0 ,2,1,2 ,0,0,0 ,2,1,2 ,0,0,0 ,2,1,2 ,0,0,0 },
			{ 0,0,0, 2,4,2, 0,0,0, 0,0,0, 2,4,2, 0,0,0, 0,0,0, 2,4,2, 0,0,0, 0,0,0, 2,4,2, 0,0,0, 2,4,2 ,0,0,0 ,2,4,2 ,0,0,0 ,2,4,2 ,0,0,0 ,2,4,2 ,0,0,0 },
			{ 0,0,0, 2,1,2, 0,0,0, 0,0,0, 2,1,2, 0,0,0, 0,0,0, 2,1,2, 0,0,0, 0,0,0, 2,1,2, 0,0,0, 2,1,2 ,0,0,0 ,2,1,2 ,0,0,0 ,2,1,2 ,0,0,0 ,2,1,2 ,0,0,0 },
			
			{ 0,0,0, 2,1,2, 0,0,0, 0,0,0, 2,1,2, 2,2,2, 2,2,2, 2,1,2, 2,2,2, 2,2,2, 2,1,2, 2,2,2, 2,1,2 ,0,0,0 ,2,1,2 ,2,2,2 ,2,1,2 ,0,0,0 ,2,1,2 ,0,0,0 },
			{ 0,0,0, 2,4,2, 0,0,0, 0,0,0, 2,4,1, 1,4,1, 1,4,1, 1,4,1, 1,4,1, 1,4,1, 1,4,1, 1,4,1, 1,4,2 ,0,0,0 ,2,4,1 ,1,4,1 ,1,4,2 ,0,0,0 ,2,4,2 ,0,0,0 },
			{ 0,0,0, 2,1,2, 0,0,0, 0,0,0, 2,1,2, 2,2,2, 2,2,2, 2,2,2, 2,2,2, 2,1,2, 2,2,2, 2,2,2, 2,1,2 ,0,0,0 ,2,1,2 ,2,2,2 ,2,2,2 ,0,0,0 ,2,1,2 ,0,0,0 },
			
			{ 0,0,0, 2,1,2, 0,0,0, 0,0,0, 2,1,2, 0,0,0, 0,0,0, 0,0,0, 0,0,0, 2,1,2, 0,0,0, 0,0,0, 2,1,2 ,0,0,0 ,2,1,2 ,0,0,0 ,0,0,0 ,0,0,0 ,2,1,2 ,0,0,0 },
			{ 0,0,0, 2,4,2, 0,0,0, 0,0,0, 2,4,2, 0,0,0, 0,0,0, 0,0,0, 0,0,0, 2,4,2, 0,0,0, 0,0,0, 2,4,2 ,0,0,0 ,2,4,2 ,0,0,0 ,0,0,0 ,0,0,0 ,2,4,2 ,0,0,0 },
			{ 0,0,0, 2,1,2, 0,0,0, 0,0,0, 2,1,2, 0,0,0, 0,0,0, 0,0,0, 0,0,0, 2,1,2, 0,0,0, 0,0,0, 2,1,2 ,0,0,0 ,2,1,2 ,0,0,0 ,0,0,0 ,0,0,0 ,2,1,2 ,0,0,0 },
			
			{ 2,2,2, 2,1,2, 2,2,2, 2,2,2, 2,1,2, 2,2,2, 2,2,2, 2,2,2, 2,2,2, 2,1,2, 2,2,2, 2,2,2, 2,1,2 ,2,2,2 ,2,1,2 ,2,2,2 ,2,2,2 ,0,0,0 ,2,1,2 ,2,2,2 },
			{ 9,1,1, 1,4,1, 1,4,1, 1,4,1, 1,4,1, 1,4,1, 1,4,1, 1,4,1, 1,4,1, 1,4,1, 1,4,1, 1,4,1, 1,4,1 ,1,4,1 ,1,4,1 ,1,4,1 ,1,4,2 ,0,0,0 ,2,4,1 ,1,1,9 },
			{ 2,2,2, 2,1,2, 2,2,2, 2,2,2, 2,2,2, 2,2,2, 2,1,2, 2,2,2, 2,2,2, 2,1,2, 2,2,2, 2,2,2, 2,1,2 ,2,2,2 ,2,2,2 ,2,2,2 ,2,1,2 ,0,0,0 ,2,1,2 ,2,2,2 },
			
			{ 0,0,0, 2,1,2, 0,0,0, 0,0,0, 0,0,0, 0,0,0, 2,1,2, 0,0,0, 0,0,0, 2,1,2, 0,0,0, 0,0,0, 2,1,2 ,0,0,0 ,0,0,0, 0,0,0 ,2,1,2 ,0,0,0 ,2,1,2 ,0,0,0 },
			{ 0,0,0, 2,4,2, 0,0,0, 0,0,0, 0,0,0, 0,0,0, 2,4,2, 0,0,0, 0,0,0, 2,4,2, 0,0,0, 0,0,0, 2,4,2 ,0,0,0 ,0,0,0 ,0,0,0 ,2,4,2 ,0,0,0 ,2,4,2 ,0,0,0 },
			{ 0,0,0, 2,1,2, 0,0,0, 0,0,0, 0,0,0, 0,0,0, 2,1,2, 0,0,0, 0,0,0, 2,1,2, 0,0,0, 0,0,0, 2,1,2 ,0,0,0 ,0,0,0 ,0,0,0 ,2,1,2 ,0,0,0 ,2,1,2 ,0,0,0 },
			
			{ 0,0,0, 2,1,2, 0,0,0, 2,2,2, 2,2,2, 2,2,2, 2,1,2, 0,0,0, 0,0,0, 2,1,2, 0,0,0, 0,0,0, 2,1,2 ,0,0,0 ,2,2,2 ,2,2,2 ,2,1,2 ,0,0,0 ,2,1,2 ,0,0,0 },
			{ 0,0,0, 2,4,2, 0,0,0, 2,4,1, 1,4,1, 1,4,1, 1,4,2, 0,0,0, 0,0,0, 2,4,2, 0,0,0, 0,0,0, 2,4,2 ,0,0,0 ,2,4,1 ,1,4,1 ,1,4,2 ,0,0,0 ,2,4,2 ,0,0,0 },
			{ 0,0,0, 2,1,2, 0,0,0, 2,1,2, 2,2,2, 2,2,2, 2,1,2, 0,0,0, 0,0,0, 2,1,2, 0,0,0, 0,0,0, 2,1,2 ,0,0,0 ,2,1,2 ,2,2,2 ,2,1,2 ,0,0,0 ,2,1,2 ,0,0,0 },
			
			{ 0,0,0, 2,1,2, 0,0,0, 2,1,2, 0,0,0, 0,0,0, 2,1,2, 0,0,0, 0,0,0, 2,1,2, 0,0,0, 0,0,0, 2,1,2 ,0,0,0 ,2,1,2 ,0,0,0 ,2,1,2 ,0,0,0 ,2,1,2 ,0,0,0 },
			{ 0,0,0, 2,4,2, 0,0,0, 2,4,2, 0,0,0, 0,0,0, 2,4,2, 0,0,0, 0,0,0, 2,4,2, 0,0,0, 0,0,0, 2,4,2 ,0,0,0 ,2,4,2 ,0,0,0 ,2,4,2 ,0,0,0 ,2,4,2 ,0,0,0 },
			{ 0,0,0, 2,1,2, 0,0,0, 2,1,2, 0,0,0, 0,0,0, 2,1,2, 0,0,0, 0,0,0, 2,1,2, 0,0,0, 0,0,0, 2,1,2 ,0,0,0 ,2,1,2 ,0,0,0 ,2,1,2 ,0,0,0 ,2,1,2 ,0,0,0 },
			
			{ 0,0,0, 2,1,2, 2,2,2, 2,1,2, 2,2,2, 2,2,2, 2,1,2, 0,0,0, 0,0,0, 2,1,2, 0,0,0, 0,0,0, 2,1,2 ,2,2,2 ,2,1,2 ,0,0,0 ,2,1,2 ,2,2,2 ,2,1,2 ,0,0,0 },
			{ 0,0,0, 2,4,1, 1,4,1, 1,5,1, 1,4,1, 1,4,1, 1,4,2, 0,0,0, 0,0,0, 2,4,2, 0,0,0, 0,0,0, 2,4,1 ,1,4,1 ,1,5,2 ,0,0,0 ,2,4,1 ,1,4,1 ,1,4,2 ,0,0,0 },
			{ 0,0,0, 2,2,2, 2,2,2, 2,2,2, 2,2,2, 2,2,2, 2,2,2, 0,0,0, 0,0,0, 2,1,2, 0,0,0, 0,0,0, 2,2,2 ,2,2,2 ,2,2,2 ,0,0,0 ,2,2,2 ,2,2,2 ,2,2,2 ,0,0,0 },
			
			{ 0,0,0, 0,0,0, 0,0,0, 0,0,0, 0,0,0, 0,0,0, 0,0,0, 0,0,0, 0,0,0, 2,1,2, 0,0,0, 0,0,0, 0,0,0, 0,0,0, 0,0,0, 0,0,0, 0,0,0, 0,0,0, 0,0,0, 0,0,0, },
			{ 0,0,0, 0,0,0, 0,0,0, 0,0,0, 0,0,0, 0,0,0, 0,0,0, 0,0,0, 0,0,0, 2,1,2, 0,0,0, 0,0,0, 0,0,0, 0,0,0, 0,0,0, 0,0,0, 0,0,0, 0,0,0, 0,0,0, 0,0,0, },
			{ 0,0,0, 0,0,0, 0,0,0, 0,0,0, 0,0,0, 0,0,0, 0,0,0, 0,0,0, 0,0,0, 2,9,2, 0,0,0, 0,0,0, 0,0,0, 0,0,0, 0,0,0, 0,0,0, 0,0,0, 0,0,0, 0,0,0, 0,0,0, },
			
			};
	//0 WALL
	//1 ONLY PATH
	//2 FALSE PATH
	//4 PATH WITH DOT
	//3 FALSETELEPORT
	//5 PATH WITH SUPER DOT
	//6 HELL
	//7 HELL GATE
	//8 FALSE HELL
	//9 PATH WITH TELEPORT
	
	public static Position getHellGatePosition() {
		return hellGate.getBoardPosition();
	}

	static public char[][] getLevelBoard() {
		return levelBoard;
	}

	public static ArrayList<Square> getHellZone() {
		// TODO Auto-generated method stub
		return hellZone;
	}

}
