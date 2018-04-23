package visual;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import controller.Game;
import model.Board;
import model.Serializator;
import model.squares.Square;
import model.squares.Square.Corner;

public class ViewManager {

	private static Window window;
	private static BeginMenu beginMenu;
	private static BoardView boardView;
	private static PostGameView postGameView;
	private static ScoreView scoreView;

	public ViewManager() {
		window = new Window();

	}

	public static void startBeginMenu() {
		beginMenu = new BeginMenu();
		window.setContentPane(beginMenu);
	}

	public static void startGameView() {

		boardView = new BoardView();
		boardView.createDotsView(Board.getDots());
		boardView.createGhostsView();
		boardView.createPacmanView();
		boardView.createBackground();
		boardView.createBarView();
		boardView.draw();
		window.setContentPane(boardView.getLayers());

	}

	public static void startPostGameView() {
		scoreView = new ScoreView();
		postGameView = new PostGameView(window, scoreView);
		window.setContentPane(postGameView);
	}

	
	
	public static void removeWindowContent(Container contentPane) {
		window.remove(contentPane);
	}

	public static Window getWindow() {
		return window;
	}

	public static void setWindow(Window window) {
		ViewManager.window = window;
	}

	public static BeginMenu getBeginMenu() {
		return beginMenu;
	}

	public static void setBeginMenu(BeginMenu beginMenu) {
		ViewManager.beginMenu = beginMenu;
	}

	public static PostGameView getPostGameView() {
		return postGameView;
	}

	public static void setPostGameView(PostGameView postGameView) {
		ViewManager.postGameView = postGameView;
	}

	public static BoardView getBoardView() {
		return boardView;
	}

	public static void setBoardView(BoardView boardView) {
		ViewManager.boardView = boardView;
	}

	public static ScoreView getScoreView() {
		return scoreView;
	}

	public static void setScoreView(ScoreView scoreView) {
		ViewManager.scoreView = scoreView;
	}

}
