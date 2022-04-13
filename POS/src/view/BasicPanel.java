package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

// 기본 패널(건들지 마세요)
// - 본인 패널 클래스 만들고 extends BasicPanel 하고난 후 사용
public class BasicPanel extends JPanel {
	public JPanel top, bottom, side;
	public JPanel top_top, top_bottom;

	public JButton btnPrev;
	public JLabel subTitle;

	private GridBagLayout gbl;
	private GridBagConstraints gbc;
	
	public static final Color colorBottom = new Color(224, 224, 224);
	public static final Color colorSide = new Color(237, 254, 188);
	public static final Color colorMenu = new Color(100, 100, 100);
	public static final Color colorCategory = new Color(52, 152, 219);

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
		bottom.setBackground(colorBottom);
		side.setBackground(colorSide);

		// 탑 패널 내부 설정(top_top, top_bottom 패널)
		top.setLayout(new GridLayout(2, 1));
		top_top = new JPanel();
		top_bottom = new JPanel();

		// vgap 10 설정
		top_top.setLayout(new BorderLayout(10, 0));
		// top_top 패널 마진값 5,5,5,5 설정(위, 왼, 아, 오)
		top_top.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		
		top_top.setBackground(Color.white); // top 패널 구분하기 위해 잠시 색상 꺼놓음
		top_bottom.setBackground(Color.white);
		
		// 이전 버튼, 부제 라벨 초기화
		btnPrev = new JButton("<");
		btnPrev.setFont(basicFont(20, Font.BOLD));
		btnPrev.setBackground(Color.white);
		btnPrev.setBorderPainted(false);
		subTitle = new JLabel("subTitle");
		subTitle.setFont(new Font("Source Han Sans", Font.BOLD, 50));
		
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
	
	public Font basicFont(int size, int bold) {
		return new Font("Source Han Sans", bold, size);
	}
	
	public Font basicFont(int size) {
		return basicFont(size, Font.BOLD);
	}
}