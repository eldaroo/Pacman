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
		board= new Board(BoardConfiguration.getLevelBoard());
		layers = new JLayeredPane();
		
		//INSTANCIO LOS THREADS
		game = new Thread( new Game(beginMenu, layers, board),"game");
		boardView= new BoardView(beginMenu, layers);
	
		//ARRANCAN LOS THREADS
		game.start();
		boardView.start();

		//UNA VEZ CARGADO INCORPORO EL THREAD AL BOARD
		board.addObserver((Observer) boardView);

	}
}