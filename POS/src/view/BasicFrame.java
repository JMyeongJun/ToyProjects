package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class BasicFrame extends JFrame {

	public JButton up, down, side;

	private GridBagLayout gbl;
	private GridBagConstraints gbc;

	public BasicFrame() {
		initcomponents();
	}

	private void initcomponents() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(gbl = new GridBagLayout());

		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		
		up = new JButton("위");
		down = new JButton("아래");
		side = new JButton("옆");
		
		gbc.weightx = 2.5;
		
		setGbc(up, 0, 0, 3, 1);
		
		gbc.weighty = 4;
		
		setGbc(down, 0, 1, 3, 1);
		
		gbc.weightx = 1;
		gbc.weighty = 1;
		setGbc(side, 3, 0, 1, 2);
		
		setSize(1000, 700);
		setVisible(true);
	}

	// GridBagConstraints 설정 함수
	private void setGbc(JComponent c, int x, int y, int w, int h) {
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = w;
		gbc.gridheight = h;
		gbl.setConstraints(c, gbc);
		add(c);
	}
}
