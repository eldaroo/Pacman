package visual;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class WinGameView extends PostGameView{


	public WinGameView(Window window, ScoreView scoreView) {
		super(window, scoreView);
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 1L;

	@Override
	public void generateParticularLabel() {
		JLabel gif = new JLabel("New label");
		gif.setIcon(new ImageIcon(WinGameView.class.getResource("/images/win3.gif")));
		gif.setBounds(110, 67, 172, 172);
		add(gif);		
	}
}
