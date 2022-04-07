package view;

import java.awt.BorderLayout;

import javax.swing.JButton;

public class OrderPanel extends BasicPanel{
	JButton btn, btn2;
	
	public OrderPanel() {
		initcomponents();
	}
	
	public void initcomponents() {
		btn = new JButton("버튼입니다");
		btn2 = new JButton("버튼입니다2");
		side.setLayout(new BorderLayout());
		
		side.add(btn, BorderLayout.SOUTH);
		side.add(btn2, BorderLayout.CENTER);
		
	}
}