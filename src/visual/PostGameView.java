package visual;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.EmptyBorder;

public class PostGameView extends JPanel {
	private JTextField textField;
	public PostGameView(GameView gameView) {
		setLayout(null);
		setVisible(true);
		setBounds(100, 100, 600, 650);
		JLabel lblGameOver = new JLabel("Game Over");
		lblGameOver.setFont(new Font("Tekton Pro Cond", Font.PLAIN, 21));
		lblGameOver.setBounds(131, 33, 89, 20);
		add(lblGameOver);
		
		JLabel lblNombre = new JLabel("Nombre: ");
		lblNombre.setBounds(54, 87, 46, 14);
		add(lblNombre);
		
		textField = new JTextField();
		textField.setBorder(new EmptyBorder(0, 0, 0, 0));
		textField.setBounds(112, 84, 86, 20);
		add(textField);
		textField.setColumns(10);
		
		JButton btnSalvarPartida = new JButton("Salvar Partida");
		btnSalvarPartida.setBounds(190, 152, 124, 23);
		add(btnSalvarPartida);
		
		JButton btnVolverAJugar = new JButton("Volver a jugar");
		btnVolverAJugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.print("salio del juego");
				
			}
		});
		btnVolverAJugar.setBounds(219, 235, 121, 23);
		add(btnVolverAJugar);
		
		JButton btnTomarseElChori = new JButton("Tomarse el Chori");
		btnTomarseElChori.setBounds(11, 235, 132, 23);
		add(btnTomarseElChori);
	}
}
