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

import controller.Game;
import model.Board;
import model.Serializator;
import model.squares.Square;
import model.squares.Square.Corner;

public class ViewManager {


	private static JLayeredPane layers;
	private static BeginMenu beginMenu;
	private static PostGameView postGameView;
	private static BoardView boardView;
	private static Window window;
	private static CreaturesView pacmanView;
	private static ArrayList<CreaturesView> ghostViewsArray;
	private static DotsView dotsView;
	private static FruitView fruitView;
	private static ScoreView scoreView;
	private static RecoveryMenu	recoveryMenu;

	
	public ViewManager()
	{
		window = new Window();

	}
	
	public static void startBeginMenu()
	{
		beginMenu = new BeginMenu();
		window.setContentPane(beginMenu);
	}

	public static void startGameView() {
		
		layers = new JLayeredPane();
		window.setContentPane(layers);
		pacmanView = new PacmanView(Board.pacman, layers);
		createGhostViews();
		dotsView = new DotsView(Board.getDots(), layers);
		boardView = new BoardView (layers);
	
	}
	
	private static void createGhostViews() {
		ghostViewsArray = new ArrayList<CreaturesView>();
		
			int aux = 1;
			while (aux <= 5) { // CANTIDAD DE GHOST: 5
				ghostViewsArray.add(new GhostView(Board.getGhostsArray().get(aux - 1), layers));
				Board.getGhostsArray().get(aux - 1).addObserver(ghostViewsArray.get(aux - 1));
				aux++;
			}
		}

	public static void setWindowContent(Container contentPane)
	{
		window.setContentPane(contentPane);
	}
	public static void removeWindowContent(Container contentPane)
	{
		window.remove(contentPane);
	}
	
	public static JLayeredPane getLayers() {
		return layers;
	}

	public static void setLayers(JLayeredPane layers) {
		ViewManager.layers = layers;
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

	public static Window getWindow() {
		return window;
	}

	public static void setWindow(Window window) {
		ViewManager.window = window;
	}

	public static CreaturesView getPacmanView() {
		return pacmanView;
	}

	public static void setPacmanView(CreaturesView pacmanView) {
		ViewManager.pacmanView = pacmanView;
	}

	public static ArrayList<CreaturesView> getGhostViewsArray() {
		return ghostViewsArray;
	}

	public static void setGhostViewsArray(ArrayList<CreaturesView> ghostViewsArray) {
		ViewManager.ghostViewsArray = ghostViewsArray;
	}

	public static DotsView getDotsView() {
		return dotsView;
	}

	public static void setDotsView(DotsView dotsView) {
		ViewManager.dotsView = dotsView;
	}

	public static FruitView getFruitView() {
		return fruitView;
	}

	public static void setFruitView(FruitView fruitView) {
		ViewManager.fruitView = fruitView;
	}

	public static ScoreView getScoreView() {
		return scoreView;
	}

	public static void setScoreView(ScoreView scoreView) {
		ViewManager.scoreView = scoreView;
	}

	public static RecoveryMenu getRecoveryMenu() {
		return recoveryMenu;
	}

	public static void setRecoveryMenu(RecoveryMenu recoveryMenu) {
		ViewManager.recoveryMenu = recoveryMenu;
	}

	public static void startPostGameView() {
		postGameView = new PostGameView(window, scoreView);
		window.setContentPane(postGameView);
	}
	public static void startRecoveryMenu() {
		recoveryMenu = new RecoveryMenu(window);
		layers.add(recoveryMenu);
	}

	


}
