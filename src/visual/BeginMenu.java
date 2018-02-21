package visual;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JInternalFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;

public class BeginMenu extends JInternalFrame {

	private boolean press = false;
	
	public BeginMenu() {
		setBorder(new TitledBorder(null, "Menu de Arranque", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JButton btnBegin = new JButton("Comenzar");
		btnBegin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				press=true;
			}
		});
		
		JLabel lblPacMan = new JLabel("PAC - MAN");
		lblPacMan.setHorizontalAlignment(SwingConstants.CENTER);
		lblPacMan.setFont(new Font("Tahoma", Font.BOLD, 24));
		getContentPane().add(lblPacMan);
		getContentPane().add(btnBegin);
		
		getContentPane().add(lblPacMan, BorderLayout.NORTH);
		
		JLabel label_1 = new JLabel("                           ");
		getContentPane().add(label_1, BorderLayout.WEST);
		
		JLabel label_2 = new JLabel("                             ");
		getContentPane().add(label_2, BorderLayout.SOUTH);
		
		JLabel label_3 = new JLabel("                              ");
		getContentPane().add(label_3, BorderLayout.EAST);
		setVisible(true);
		setBounds(200, 200, 400, 400);
	}

	public boolean wasPress() {
		return press;
	}

}
