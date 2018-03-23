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
	
	static Thread game ;
	static Thread boardView;
	static JLayeredPane layers;
	static Board board;
	static BoardConfiguration boardConfiguration;
	static BeginMenu beginMenu = new BeginMenu();
	
	public static void main(String[] args) throws IOException, ParseException, InterruptedException {
		//INSTANCIO VARIABLES
		boardConfiguration = new BoardConfiguration();
		board= new Board(boardConfiguration.getLevel1BoardRecharged());
		layers = new JLayeredPane();
		
		//INSTANCIO LOS THREADS
		game = new Thread( new Game(beginMenu, board, layers, boardConfiguration),"game");
		boardView= new BoardView(beginMenu, board.getBoard(), layers);
	
		//ARRANCAN LOS THREADS
		game.start();
		boardView.start();

		//UNA VEZ CARGADO INCORPORO EL THREAD AL BOARD
		board.addObserver((Observer) boardView);

	}
}