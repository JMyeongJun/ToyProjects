package view;

import javax.swing.JButton;
import javax.swing.JLabel;

public class OrderPanel extends BasicPanel{
	JButton btn;
	
	public OrderPanel() {
		top_top.add(new JLabel("top_top 패널입니다"));
		top_bottom.add(new JLabel("top_bottom 패널입니다"));
		bottom.add(new JLabel("bottom 패널입니다"));
		side.add(new JLabel("side 패널입니다"));
		
		btn = new JButton("btn");
		side.add(btn);
	}
}
