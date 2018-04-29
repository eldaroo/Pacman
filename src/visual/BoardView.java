package visual;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import model.Board;
import model.Position;
import model.Serializator;
import model.board.Dot;
import model.board.Fruit;
import model.creatures.Ghost;
import model.creatures.Pacman;
import model.creatures.Pacman.PacmanState;
import model.squares.Square;

public class BoardView extends JPanel implements ActionListener, Observer {

	private static final long serialVersionUID = 1L;
	
	private JLayeredPane layers;

	public JLayeredPane getLayers() {
		return layers;
	}
	
	private JLabel background;
	private JLabel lblScore;
	private JLabel lblLevel;
	private JLabel lblLifes;
	private JButton btnSave;
	private JButton btnExit;
	
	private DotView[][] dotMatrix;
	private static FruitView fruitView;
	private static CreaturesView pacmanView;
	private static ArrayList<CreaturesView> ghostViewsArray;
	
	public BoardView() {
		layers = new JLayeredPane();
		dotMatrix = new DotView[1000][1000];
		ghostViewsArray = new ArrayList<CreaturesView>();
		//fruitView = new FruitView(layers);
	}
	
	public void createDotsView(ArrayList<Dot> dotBoardMatrix) {
		int x, y;
		for (Dot dot : dotBoardMatrix) {
			x = dot.getBoardPosition().getX();
			y = dot.getBoardPosition().getY();
			dotMatrix[x][y] = new DotView(dot);

			layers.add(dotMatrix[dot.getBoardPosition().getX()][dot.getBoardPosition().getY()], 5);
		}
	}
	public void createPacmanView() {

		pacmanView = new PacmanView(Board.pacman, layers);
	}
	public void createGhostsView() {
		int aux = 0;
		for (Ghost ghost : Board.getGhostsArray()) { 
			ghostViewsArray.add(new GhostView(ghost, layers));
			ghost.addObserver(ghostViewsArray.get(aux));
			aux++;
		}
	}
	public void createBackground() {
		background = new JLabel();
		background.setIcon(ImageBinding.getBoard(Board.getLevel()));
		background.setBounds(0, ViewManager.getWindow().getY() + 5, 600, 600);
		layers.add(background);
	}
	
	public void createBarView() {

		// ETIQUETA DE PUNTAJE
		lblScore = new JLabel("Score: ");
		lblScore.setFont(new Font("Tekton Pro", Font.PLAIN, 14));
		lblScore.setBounds(130, 3, 120, 21);
		lblScore.setForeground(Color.WHITE);
		layers.add(lblScore);
		// ETIQUETA DE VIDAS
		lblLifes = new JLabel("Vidas:");
		lblLifes.setFont(new Font("Tekton Pro", Font.PLAIN, 14));
		lblLifes.setBounds(30, 3, 80, 21);
		lblLifes.setForeground(Color.WHITE);
		layers.add(lblLifes);
		// ETIQUETA DE LEVEL
		lblLevel = new JLabel("Level: ");
		lblLevel.setFont(new Font("Tekton Pro", Font.PLAIN, 14));
		lblLevel.setBounds(230, 3, 80, 21);
		lblLevel.setForeground(Color.WHITE);
		layers.add(lblLevel);
		// BOTON PARA GUARDAR PARTIDA
		btnSave = new JButton("Save");
		btnSave.setBounds(450, 0, 89, 23);
		layers.add(btnSave, 0);
		btnSave.addActionListener((ActionListener) this);
		// BOTON SALIR AL MENU DE INICIO
		btnExit = new JButton("Exit");
		btnExit.setBounds(350, 0, 89, 23);
		layers.add(btnExit, 2);
		btnExit.addActionListener((ActionListener) this);
	}

	public void draw() {
		//layers.add(this);
		setVisible(true);
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		Board board = (Board) arg0;
		repaintBar(board);
		repaintDots(board);
		repaintFruit(board);
		repaintPacman(board);
		//repaintGhosts(board);
	}

	private void repaintPacman(Board board) {
		Pacman pacman = board.getPacman();
		if (pacman.getPacmanState()==PacmanState.EATGHOST) {
			pacmanView.setIcon(ImageBinding.getEatIcon(pacman));
		} else
			pacmanView.setIcon(ImageBinding.getPacmanIcon(pacman));
		Position boardPosition = pacman.getBoardPosition();
		this.setBounds((boardPosition.getX() * 10) - 10, (boardPosition.getY() * 10) - 10 + 25, 30, 30);
	}
	private void repaintFruit(Board board) {
		Fruit fruit = board.getFruit();
		if (fruit.isEnableToEat()==true)
		{
			setVisible(true);
			fruitView.setIcon(ImageBinding.getFruitIcon(fruit));
		}else{
			setVisible(false);
		}
	}
	private void repaintDots(Board board) {
		Dot dotRemoved = board.getDotRemoved();
		if (board.isPacmanEatNewDot()) {
			// System.out.println(dotMatrix[dotRemoved.getBoardPosition().getX()][dotRemoved.getBoardPosition().getY()]);
			// System.out.println(dotRemoved.getBoardPosition().getX());
			dotMatrix[dotRemoved.getBoardPosition().getX()][dotRemoved.getBoardPosition().getY()].setVisible(false);
		}
	}
	private void repaintBar(Board board) {

		lblScore.setText("Score: " + board.getScore());
		lblLifes.setText("Lifes: " + board.getLifes());
		lblLevel.setText("Level: " + board.getLevel());
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnSave) {
			try {
				Serializator.toPersist();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (arg0.getSource() == btnExit) {
			System.exit(0);
		}
	}

	public static CreaturesView getPacmanView() {
		return pacmanView;
	}
}
