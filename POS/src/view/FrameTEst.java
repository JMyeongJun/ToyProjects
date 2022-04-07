package view;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class FrameTEst extends JFrame{
	
	public FrameTEst() {
		setTitle("FrameTEst");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		BasicPanel bp = new BasicPanel();
		
		bp.side.setLayout(new GridLayout(2,1,3,3));
		bp.side.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		
		bp.top_top.setLayout(new GridLayout());
		bp.top_top.add(new JLabel("bp패널12312"));
		bp.bottom.add(new JLabel("aoeijbaldkfbjadfb"));
		JButton side1 = new JButton("side1");
		bp.side.add(side1);
		bp.side.add(new JButton("side2"));
		
		BasicPanel bp2 = new BasicPanel();
		
		bp2.side.setLayout(new GridLayout(2,1,3,3));
		bp2.side.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		
		bp2.top_top.setLayout(new GridLayout());
		bp2.top_top.add(new JLabel("bp2패널"));
		bp2.bottom.add(new JLabel("oai"));
		JButton side3 = new JButton("side3");
		bp2.side.add(side3);
		bp2.side.add(new JButton("side4123124"));
		
		side1.addActionListener(e->{
			remove(bp);
			add(bp2);
			revalidate();
			repaint();
		});
		side3.addActionListener(e ->{
			remove(bp2);
			add(bp);
			revalidate();
			repaint();
		});
		
		add(bp);
		
		setSize(1000, 700);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new FrameTEst();
	}
}
