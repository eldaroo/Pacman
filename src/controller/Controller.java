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
		boardConfiguration = new BoardConfiguration();
		board= new Board(boardConfiguration.getLevel1BoardRecharged());
		layers = new JLayeredPane();
		
		game = new Thread( new Game(beginMenu, boardView, board, layers, boardConfiguration),"game");
		boardView= new BoardView(beginMenu, board.getBoard(), layers);
	
		game.start();
		boardView.start();
		board.addObserver((Observer) boardView);
	}
}