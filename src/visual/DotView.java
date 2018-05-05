package visual;


import javax.swing.JLabel;
import model.board.Dot;

public class DotView extends JLabel {

	private static final long serialVersionUID = 1L;

	public DotView(Dot dot) {
		setIcon(ImageBinding.getImageIcon(dot));
		setBounds((dot.getBoardPosition().getX() * 10) - 10, (dot.getBoardPosition().getY() * 10) - 10 + 25, 30, 30);

	}

	
}
