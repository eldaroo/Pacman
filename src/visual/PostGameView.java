package visual;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.EmptyBorder;

import controller.Game;
import controller.states.NormalState;
import controller.states.RespawnState;
import model.persistence.DataManager;

public abstract class PostGameView extends JPanel {

	private static final long serialVersionUID = 34706378010734016L;
	private JTextField textField;

	public PostGameView(Window window, ScoreView scoreView) {

		setVisible(true);

		generateParticularComponents();
		JLabel lblName = new JLabel("Nombre: ");
		lblName.setSize(54, 87);
		add(lblName,BorderLayout.CENTER);

		textField = new JTextField();
		textField.setBorder(new EmptyBorder(0, 0, 0, 0));
		textField.setSize(112, 84);
		textField.setEnabled(true);
		add(textField,BorderLayout.CENTER);
		textField.setColumns(10);
		
		JButton btnSave = new JButton("SAVE SCORE");
		

		btnSave.setSize(124, 23);
		add(btnSave, BorderLayout.CENTER);
		btnSave.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				//SALVAMOS EL SCORE AL TOCAR EL BOTON
				DataManager.saveScore(textField.getText());
				try {
					//NOS DESHACEMOS DEL POSTGAMEVIEW
					this.finalize();
				} catch (Throwable e) {
					e.printStackTrace();
				}
				//AGREGAMOS AL SCOREVIEW A lA PANTALLA
				window.setContentPane(scoreView);
				
				//RECUPERAMOS EL LISTADO DE SCORE DE LA BASE DE DATOS
				scoreView.getScore();

				window.repaint();
			}
			
		});
		
		
	}
	
	public abstract void generateParticularComponents() ;
}
