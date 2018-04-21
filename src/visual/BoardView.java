package visual;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import model.Board;
import model.Serializator;
import model.creatures.Pacman;
import model.squares.Square;

public class BoardView extends JLabel implements ActionListener, Observer{
	
	JLayeredPane layers ;
	private JLabel lblScore;
	private JLabel lblLevel;
	private JLabel lblLifes;
	private JButton  btnSave ;
	private JButton  btnExit ;
	
	public BoardView(JLayeredPane layers)
	{
		this.layers = layers;

		drawBoard();
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		
		lblScore.setText("Score: "+ Board.getScore());
		lblLifes.setText("Lifes: "+ Board.getLifes());
		lblLevel.setText("Level: "+ Board.getLevel());		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource()==btnSave)
		{
			try {
				Serializator.toPersist();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			}
		if(arg0.getSource()==btnExit)
		{
			System.exit(0);
		}		
	}
	private void drawBoard() {

		setIcon(ResourceBinding.getBoard());
		setBounds(0, ViewManager.getWindow().getY()+5,600, 600);
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
				
				layers.add(this);
				setVisible(true);		
	}
}
