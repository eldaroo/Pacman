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

public class BeginMenu extends JInternalFrame implements ActionListener {

	private boolean recovery = false;
	private boolean press = false;
	JLayeredPane layers = new JLayeredPane();
	JButton btnRecovery;
	JButton btnBegin;
	public BeginMenu() {
		setBorder(new TitledBorder(null, "Menu de Arranque", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().setLayout(null);
		
		btnBegin = new JButton("Comenzar");
		btnBegin.setBounds(200, 400, 137, 29);
		btnBegin.addActionListener(this);
		

		
		JLabel lblPacMan = new JLabel();
		lblPacMan.setIcon(ResourceBinding.getImageIcon(this));
		lblPacMan.setBounds(10, -268, 800,900);
		
		layers.add(btnBegin);
		layers.add(lblPacMan);
		
		
		setContentPane(layers);
		
		btnRecovery = new JButton("Recuperar partida");
		btnRecovery.setBounds(200, 440, 137, 29);
		layers.add(btnRecovery);
		
		
		btnRecovery.addActionListener(this);
		
		setVisible(true);
		setBounds(200, 200, 600, 650);
	}

	public boolean wasPress() {
		return press;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource()==btnBegin)
		{
			press=true;
		}
		else if  (e.getSource()==btnRecovery)
		{
			recovery = true;
		}

			
	}

	public JButton getBtnRecovery() {
		return btnRecovery;
	}

}
