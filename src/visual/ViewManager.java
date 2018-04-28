package visual;

import java.awt.Container;
import model.Board;


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

	public static void startPostGameView(boolean win) {
		scoreView = new ScoreView();
		if (win) {
			postGameView = new WinGameView(window, scoreView);
		} else {
			postGameView = new GameOverView(window, scoreView);
		}
		window.remove(boardView.getLayers());
		window.setContentPane(postGameView);
		window.repaint();
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
