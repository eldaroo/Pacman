package visual;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import model.Board;
import model.Dot;

public class DotView extends JLabel {

	private static final long serialVersionUID = 1L;

	public DotView(Dot dot) {
		setIcon(ImageBinding.getImageIcon(dot));
		setBounds((dot.getBoardPosition().getX() * 10) - 10, (dot.getBoardPosition().getY() * 10) - 10 + 25, 30, 30);

	}

	
}
