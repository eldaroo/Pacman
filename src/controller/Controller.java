package controller;

import java.io.IOException;
import java.util.Observer;

import javax.swing.JLayeredPane;

import org.json.simple.parser.ParseException;

import model.Board;
import model.BoardConfiguration;
import visual.BoardView;

public class Controller {
	
	static Thread game ;
	static Thread boardView;
	static JLayeredPane layers;
	static Board board;
	static BoardConfiguration boardConfiguration;
	
	public static void main(String[] args) throws IOException, ParseException, InterruptedException {
		boardConfiguration = new BoardConfiguration();
		board= new Board(boardConfiguration.level1BoardRecharged);
		layers = new JLayeredPane();
		
		game = new Thread( new Game(board, layers, boardConfiguration),"game");
		boardView= new Thread ( new BoardView(board.getBoard(), layers), "boardView");
	
		game.start();
		boardView.start();
		boardView.join();
		board.addObserver((Observer) boardView);
	}
}
