package visual;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;


import model.creatures.Creature;

public class CreaturesView extends JLabel implements Observer {

	private static final long serialVersionUID = -7241581276315197591L;

	public CreaturesView(Creature creature, JLayeredPane layers) {


		this.setBounds((creature.getBoardPosition().getX() * 10) - 10, (creature.getBoardPosition().getY() * 10) - 10+25, 30, 30);
		
	}

	@Override
	public void update(Observable observable, Object object) {
		
	}
	
	
}
