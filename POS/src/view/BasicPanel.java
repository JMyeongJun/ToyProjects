package view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class BasicPanel extends JPanel {
	public JPanel top, bottom, side;
	public JPanel top_top, top_bottom;

	private GridBagLayout gbl;
	private GridBagConstraints gbc;

	public BasicPanel() {
		initcomponents();
	}

	private void initcomponents() {
		setLayout(gbl = new GridBagLayout());

		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;

		top = new JPanel();
		bottom = new JPanel();
		side = new JPanel();

		top.setPreferredSize(getPreferredSize());
		bottom.setPreferredSize(getPreferredSize());
		side.setPreferredSize(getPreferredSize());
		
		gbc.weightx = 2.2;
		setGbc(top, 0, 0, 1, 1);

		gbc.weighty = 4;
		setGbc(bottom, 0, 1, 1, 1);

		gbc.weightx = 1;
		gbc.weighty = 1;
		setGbc(side, 3, 0, 1, 2);
		
		top.setBackground(Color.white);
		bottom.setBackground(new Color(224, 224, 224));
		side.setBackground(new Color(237, 254, 188));

		// 탑 패널 내부 설정(top_top, top_bottom 패널)
		top.setLayout(new GridLayout(2, 1));
		top_top = new JPanel();
		top_bottom = new JPanel();

//		top_top.setBackground(Color.white);
		top_bottom.setBackground(Color.white);
		

		top.add(top_top);
		top.add(top_bottom);
		
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