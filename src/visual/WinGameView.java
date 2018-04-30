package visual;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.LayoutManager;

import javax.swing.ImageIcon;

public class WinGameView extends PostGameView{


	public WinGameView(Window window, ScoreView scoreView) {
		super(window, scoreView);
		setSize(500, 550);
		setLocation(50, 33);
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 1L;

	@Override
	public void generateParticularComponents() {
		JLabel gif = new JLabel();
		gif.setIcon(new ImageIcon(WinGameView.class.getResource("/images/win3.gif")));
		gif.setBounds(110, 67, 172, 172);
		add(gif);		
		
		
	}
}
