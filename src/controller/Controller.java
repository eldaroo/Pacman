package controller;

import java.io.IOException;
import java.util.Observer;

import javax.swing.JLayeredPane;

import org.json.simple.parser.ParseException;

import model.Board;
import model.BoardConfiguration;
import visual.BeginMenu;
import visual.BoardView;

public class Controller {
	
	static Thread game;
	static Thread boardView;
	static JLayeredPane layers;
	static Board board;
	static BoardConfiguration boardConfiguration;
	static BeginMenu beginMenu = new BeginMenu();
	
	public static void main(String[] args) throws IOException, ParseException, InterruptedException {
		//SE CREA EL MODELO BASE DEL TABLERO
		boardConfiguration = new BoardConfiguration();
		board= new Board(boardConfiguration.level1BoardRecharged);
		//SE CREA EL CONTENEDOR DE CAPAS
		layers = new JLayeredPane();
		//SE CREAN LOS HILOS DEL CONTROLLER Y DEL VISUAL (MANERAS ALTERNATIVAS)
		game = new Thread( new Game(beginMenu, boardView, board, layers, boardConfiguration),"game");
		boardView= new BoardView(beginMenu, board.getBoard(), layers);
		//SE INICIAN LOS HILOS DEL CONTROLLER Y DEL VISUAL
		game.start();
		boardView.start();
		//EL VISUAL DEL BOARD COMIENZA A OBSERVAR AL MODELO DEL BOARD
		board.addObserver((Observer) boardView);
	}
}
