package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BasicPanel extends JPanel {
	public JPanel top, bottom, side;
	public JPanel top_top, top_bottom;

	public JButton btnPrev;
	public JLabel subTitle;

	private GridBagLayout gbl;
	private GridBagConstraints gbc;

	public BasicPanel() {
		initcomponents(2.3);
	}

	public BasicPanel(double x) {
		initcomponents(x);
	}

	private void initcomponents(double x) {
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

		gbc.weightx = x;
		setGbc(top, 0, 0, 1, 1);

		gbc.weighty = 3.8;
		setGbc(bottom, 0, 1, 1, 1);

		gbc.weightx = 1;
		gbc.weighty = 1;
		setGbc(side, 1, 0, 1, 2);

		// 색상 설정
		top.setBackground(Color.white);
		bottom.setBackground(new Color(224, 224, 224));
		side.setBackground(new Color(237, 254, 188));

		// 탑 패널 내부 설정(top_top, top_bottom 패널)
		top.setLayout(new GridLayout(2, 1));
		top_top = new JPanel();
		top_bottom = new JPanel();

		// vgap 10 설정
		top_top.setLayout(new BorderLayout(10, 0));
		// top_top 패널 마진값 5,5,5,5 설정(위, 왼, 아, 오)
		top_top.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		
//		top_top.setBackground(Color.white); // top 패널 구분하기 위해 잠시 색상 꺼놓음
		top_bottom.setBackground(Color.white);
		
		// 이전 버튼, 부제 라벨 초기화
		btnPrev = new JButton("<PREV");
		subTitle = new JLabel("subTitle");
		
		top_top.add(btnPrev, BorderLayout.WEST);
		top_top.add(subTitle, BorderLayout.CENTER);
		
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