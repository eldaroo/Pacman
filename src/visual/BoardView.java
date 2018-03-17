package visual;

import java.awt.Font;
import java.awt.GridBagLayout;
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
import model.Square.Corner;

public class BoardView extends Thread implements ActionListener, Observer{

	JLabel lblScore;
	JLabel lblLifes;
	JButton  btnSave ;
	JButton  btnExit ;
	Square[][] squareArray;
	JLayeredPane layers;
	BeginMenu beginMenu;
	
	public BoardView(BeginMenu beginMenu, Square[][] squareArray, JLayeredPane layers)
	{
		this.squareArray= squareArray;
		this.layers = layers;
		this.beginMenu = beginMenu;
	}
	//SE CARGRA EN EL HILO EL DIBUJO DEL TABLERO
	@Override
	public void run() {
		System.out.print("Charging BoardView Thread");
		//ETIQUETA DE PUNTAJE
		lblScore = new JLabel("Score: ");
		lblScore.setFont(new Font("Tekton Pro", Font.PLAIN, 14));
		lblScore.setBounds(130, 3, 80, 21);
		layers.add(lblScore);
		//ETIQUETA DE VIDAS
		lblLifes = new JLabel("Vidas:");
		lblLifes.setFont(new Font("Tekton Pro", Font.PLAIN, 14));
		lblLifes.setBounds(30, 3, 80, 21);
		layers.add(lblLifes);
		//BOTON PARA GUARDAR PARTIDA
		btnSave = new JButton("Save");
		btnSave.setBounds(450, 0, 89, 23);
		layers.add(btnSave, 0);
		//BOTON SALIR AL MENU DE INICIO
		btnExit = new JButton("Exit");
		btnExit.setBounds(350, 0, 89, 23);
		layers.add(btnExit, 2);
		btnExit.addActionListener((ActionListener) this);
		//TABLERO DE JUEGO
		int capa = 10000;
		JLabel[][] Fondo = new JLabel[60][60];
		for (int i = 0; i < squareArray.length; i++) {
			capa++;
			for (int j = 0; j < squareArray.length; j++) {
				capa++;
				Fondo[i][j] = new JLabel();
				if(squareArray[i][j].corner==Corner.CENTER)
				Fondo[i][j].setIcon(ResourceBinding.getImageIcon(squareArray[i][j]));
				else
				Fondo[i][j].setIcon(ResourceBinding.getCornerIcon(squareArray[i][j]));
				Fondo[i][j].setBounds(i * 10, (j * 10)+25, 10, 10);
				layers.add(Fondo[i][j], capa);
			}
			if(i%11==3)System.out.print('.');
		}
		System.out.println(' ');
		//QUITA LA ANIMACIÓN DE CARGA DE JUEGO EN EL MENU DE INICIO
		beginMenu.layers.remove(beginMenu.lblLoading);
		System.out.println("BoardView Thread Succefully Charged.");
		//SE ACTUALIZA EL MENU DE INICIO Y SE AGREGAN LAS CAPAS DE INICIO Y CARGA DE PARTIDA
		beginMenu.repaint();
		beginMenu.layers.add(beginMenu.btnBegin);
		beginMenu.layers.add(beginMenu.btnRecovery);
		beginMenu.layers.add(beginMenu.btnExit);
	}
	//EJECUTAR SALVADO AL PRESIONAR BOTON PARA SALVAR JUEGO
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource()==btnSave)
		{
			Game.save();
		}
		if(arg0.getSource()==btnExit)
		{
			System.exit(0);
		}
		
	}
	//OBSERVA Y DIBUJA CUANDO SE ALTERAN EL PUNTAJE Y LAS VIDAS
	@Override
	public void update(Observable arg0, Object arg1) {
		Board board = (Board) arg0;
		lblScore.setText("Score: "+ board.score);
		lblLifes.setText("Lifes: "+ board.lifes);
	}
}
