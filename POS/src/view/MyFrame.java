package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MyFrame extends JFrame {
	JLabel la = new JLabel();
	public MyFrame(String labelName) {
		super("MyFrame");
		setSize(300,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		la.setText(labelName);
		JButton nextBtn = new JButton("next");
		nextBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					setVisible(false);
					new YourFrame("You Change");
			}
		});
		c.add(la);
		c.add(nextBtn);
		setVisible(true);
	}
	public static void main(String[] args) {
		new MyFrame("My");
	}

}

class YourFrame extends JFrame{
	JLabel la = new JLabel("You");
	YourFrame(String labelName){
		super("YourFrame");
		setSize(300,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		JButton backBtn = new JButton("back");
		backBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					setVisible(false);
					new MyFrame("My Change");
			}
		});
		la.setText(labelName);
		c.add(la);
		c.add(backBtn);
		setVisible(true);
	}
	
}