package visual;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JInternalFrame;

import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.text.DefaultCaret;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.awt.FlowLayout;

public class BeginMenu extends JInternalFrame implements ActionListener {

	private boolean pressRecovery = false;
	private boolean pressBegin = false;
	private boolean pressExit = false;
	JLayeredPane layers = new JLayeredPane();
	JButton btnRecovery;
	JButton btnBegin;
	JButton btnExit;
	JLabel lblLoading;
	
	//SE DIBUJA EL MENU DE ARRANQUE CARGANDO EL JUEGO
	public BeginMenu()  {
		setAutoscrolls(true);
		setBackground(new Color(0, 204, 51));
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		//TITULO
		//setBorder(new TitledBorder(null, "Menu de Arranque", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().setLayout(new FlowLayout());
		//LOGO DEL JUEGO
		JLabel lblPacMan = new JLabel();
		lblPacMan.setForeground(Color.BLACK);
		lblPacMan.setBackground(new Color(102, 255, 204));
		lblPacMan.setIcon(ResourceBinding.getImageIcon(this));
		lblPacMan.setBounds(100, 0, 352,400);
		//ANIMACIÓN DE CARGA DE JUEGO
		lblLoading = new JLabel("Loading");
		lblLoading.setIcon(new ImageIcon("resources/loading.gif"));
		lblLoading.setBounds(212, 380, 114, 94);
		//BOTON COMENZAR PARTIDA
		btnBegin = new JButton("Comenzar");
		btnBegin.setBounds(200, 400, 137, 29);
		btnBegin.addActionListener(this );
		//BOTON CARGAR PARTIDA
		btnRecovery = new JButton("Recuperar partida");
		btnRecovery.setBounds(200, 440, 137, 29);
		btnRecovery.addActionListener(this);
		//BOTON SALIR
		btnExit = new JButton("Salir");
		btnExit.setBounds(200, 480, 137, 29);
		btnExit.addActionListener(this);
		//SE AÑADEN COMO CAPAS SOLO EL LOGO Y LA ANIMACIÓN DE CARGA DE JUEGO
		layers.add(lblPacMan);
		layers.add(lblLoading);
		//SE AÑADEN LAS CAPAS A LA VENTANA Y SE DIBUJA
		setContentPane(layers);
		
		setVisible(true);
		setBounds(200, 200, 600, 650);
	}
	
	//REGISTRA SE SE PRESIONA ALGÚN BOTÓN (INICIAR/CARGAR)
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource()==btnBegin)
		{
			pressBegin=true;
		}
		else if  (e.getSource()==btnRecovery)
		{
			pressRecovery = true;
		}
		else if  (e.getSource()==btnExit)
		{
			pressExit = true;
		}
	}
	
	//DEVUELVE SI SE PRESIONÓ (O NO) RL BOTON DE INICIAR PARTIDA
	public boolean wasPressbtnBegin() {
		return pressBegin;
	}
	//DEVUELVE SI SE PRESIONÓ (O NO) RL BOTON DE CARGAR PARTIDA
	public boolean wasPressBtnRecovery() {
		return pressRecovery;
	}
	//DEVUELVE SI SE PRESIONÓ (O NO) EL BOTON DE SALIR
	public boolean wasPressBtnExit() {
		return pressExit;
	}
}
