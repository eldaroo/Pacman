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
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.text.DefaultCaret;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;

public class BeginMenu extends JInternalFrame implements ActionListener {

	private boolean pressRecovery = false;
	private boolean pressBegin = false;
	JLayeredPane layers = new JLayeredPane();
	JButton btnRecovery;
	JButton btnBegin;
	JLabel lblLoading;
	
	//SE DIBUJA EL MENU DE ARRANQUE CARGANDO EL JUEGO
	public BeginMenu() {
		//TITULO
		setBorder(new TitledBorder(null, "Menu de Arranque", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().setLayout(null);
		//LOGO DEL JUEGO
		JLabel lblPacMan = new JLabel();
		lblPacMan.setIcon(ResourceBinding.getImageIcon(this));
		lblPacMan.setBounds(10, -268, 800,900);
		//ANIMACI�N DE CARGA DE JUEGO
		lblLoading = new JLabel("Loading");
		lblLoading.setIcon(new ImageIcon("resources/62157.gif"));
		lblLoading.setBounds(212, 380, 114, 94);
		//BOTON COMENZAR PARTIDA
		btnBegin = new JButton("Comenzar");
		btnBegin.setBounds(200, 400, 137, 29);
		btnBegin.addActionListener(this );
		//BOTON CARGAR PARTIDA
		btnRecovery = new JButton("Recuperar partida");
		btnRecovery.setBounds(200, 440, 137, 29);
		btnRecovery.addActionListener(this);
		//SE A�ADEN COMO CAPAS SOLO EL LOGO Y LA ANIMACI�N DE CARGA DE JUEGO
		layers.add(lblPacMan);
		layers.add(lblLoading);
		//SE A�ADEN LAS CAPAS A LA VENTANA Y SE DIBUJA
		setContentPane(layers);
		setVisible(true);
		setBounds(200, 200, 600, 650);
	}
	
	//REGISTRA SE SE PRESIONA ALG�N BOT�N (INICIAR/CARGAR)
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
	}
	
	//DEVUELVE SI SE PRESION� (O NO) RL BOTON DE INICIAR PARTIDA
	public boolean wasPressbtnBegin() {
		return pressBegin;
	}
	//DEVUELVE SI SE PRESION� (O NO) RL BOTON DE CARGAR PARTIDA
	public boolean wasPressBtnRecovery() {
		return pressRecovery;
	}
}
