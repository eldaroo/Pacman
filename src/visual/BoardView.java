package visual;

import java.awt.Color;
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
import model.Square.Corner;

public class BoardView extends Thread implements ActionListener, Observer{

	private JLabel lblScore;
	private JLabel lblLevel;
	private JLabel lblLifes;
	private JButton  btnSave ;
	private JButton  btnExit ;
	private Square[][] squareArray;
	private JLayeredPane layers;
	private BeginMenu beginMenu;
	
	public BoardView(BeginMenu beginMenu, JLayeredPane layers)
	{
		this.layers = layers;
		this.beginMenu = beginMenu;
		this.squareArray = Board.getBoard();
	}
	//SE CARGRA EN EL HILO EL DIBUJO DEL TABLERO
	@Override
	public void run() {
		System.out.print("Charging BoardView Thread");
		//ETIQUETA DE PUNTAJE
		lblScore = new JLabel("Score: ");
		lblScore.setFont(new Font("Tekton Pro", Font.PLAIN, 14));
		lblScore.setBounds(130, 3, 120, 21);
		lblScore.setForeground(Color.WHITE);
		layers.add(lblScore);
		//ETIQUETA DE VIDAS
		lblLifes = new JLabel("Vidas:");
		lblLifes.setFont(new Font("Tekton Pro", Font.PLAIN, 14));
		lblLifes.setBounds(30, 3, 80, 21);
		lblLifes.setForeground(Color.WHITE);
		layers.add(lblLifes);
		//ETIQUETA DE LEVEL
		lblLevel = new JLabel("Level: ");
		lblLevel.setFont(new Font("Tekton Pro", Font.PLAIN, 14));
		lblLevel.setBounds(230, 3, 80, 21);
		lblLevel.setForeground(Color.WHITE);
		layers.add(lblLevel);
		//BOTON PARA GUARDAR PARTIDA
		btnSave = new JButton("Save");
		btnSave.setBounds(450, 0, 89, 23);
		layers.add(btnSave, 0);
		btnSave.addActionListener((ActionListener) this);
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
					//Fondo[i][j].setIcon(ResourceBinding.getImageIcon(squareArray[i][j]));
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
	
		lblScore.setText("Score: "+ Board.getScore());
		lblLifes.setText("Lifes: "+ Board.getLifes());
		lblLevel.setText("Level: "+ Board.getLevel());

	}
}
