package visual;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import controller.Game;
import model.Board;
import model.Square;

public class BoardView extends Thread implements ActionListener, Observer{

	JLabel lblScore;
	JLabel lblLifes;
	JButton  btnSave ;
	Square[][] squareArray;
	JLayeredPane layers;
	BeginMenu beginMenu;
	public BoardView(BeginMenu beginMenu, Square[][] squareArray, JLayeredPane layers)
	{
		this.squareArray= squareArray;
		this.layers = layers;
		this.beginMenu = beginMenu;
	}
	
	@Override
	public void run() {
		System.out.println("thread boardView");
		lblScore = new JLabel("Score: ");
		lblScore.setFont(new Font("Tekton Pro", Font.PLAIN, 14));
		lblScore.setBounds(130, 3, 80, 21);
		layers.add(lblScore);
		
		lblLifes = new JLabel("Vidas:");
		lblLifes.setFont(new Font("Tekton Pro", Font.PLAIN, 14));
		lblLifes.setBounds(30, 3, 80, 21);
		layers.add(lblLifes);
		
		btnSave = new JButton("Save");
		btnSave.setBounds(450, 0, 89, 23);
		layers.add(btnSave, 0);
		btnSave.addActionListener((ActionListener) this);
		
		int capa = 10000;
		JLabel[][] Fondo = new JLabel[60][60];
		for (int i = 0; i < squareArray.length; i++) {
			capa++;
			for (int j = 0; j < squareArray.length; j++) {
				capa++;
				Fondo[i][j] = new JLabel();
				Fondo[i][j].setIcon(ResourceBinding.getImageIcon(squareArray[i][j]));
				Fondo[i][j].setBounds(i * 10, (j * 10)+25, 10, 10);
				layers.add(Fondo[i][j], capa);
			}
		}
		
		//Cuando se termina de cargar muestra los botones para comenzar
		beginMenu.layers.remove(beginMenu.lblLoading);
		beginMenu.repaint();
		beginMenu.layers.add(beginMenu.btnBegin);
		beginMenu.layers.add(beginMenu.btnRecovery);

	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource()==btnSave)
		{
			Game.save();
		}
		
	}
	@Override
	public void update(Observable arg0, Object arg1) {
		Board board = (Board) arg0;
		lblScore.setText("Score: "+ board.score);
		lblLifes.setText("Lifes: "+ board.lifes);
	}
}
