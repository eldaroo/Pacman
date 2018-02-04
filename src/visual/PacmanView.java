package visual;

import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JLayeredPane;

public class PacmanView extends CreatureView implements Observer{
	public PacmanView(JLayeredPane layers)
	{
		this.setBounds(360, 560, 40, 40);
		setIcon(new ImageIcon("resources/CRIATURA.png"));
		layers.add(this,6);


	}
}
