package visual;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.GridLayout;

public class PlayerView extends JPanel implements Observer{

	private static final long serialVersionUID = 5833621458256587347L;


	public PlayerView(JLayeredPane layers) {
		setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lblScore = new JLabel("SCORE: ");
		lblScore.setVerticalAlignment(SwingConstants.TOP);
		add(lblScore);
		
		JLabel lblLifes = new JLabel("LIFES: ");
		lblLifes.setVerticalAlignment(SwingConstants.TOP);
		add(lblLifes);
		setVisible(true);
		layers.add(this,30);
	}

	
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

}
