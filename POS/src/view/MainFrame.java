package view;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame{
	
	JPanel panel1, panel2;
	
	public void init(MainFrame mf) {
		setTitle("MainFrame");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		panel1 = new OrderPanel(mf);
		panel2 = new SalesPanel(mf);
		
		add(panel1);
		
		setSize(1000, 700);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		System.out.println("main");
		MainFrame mf = new MainFrame();
		mf.init(mf);
	}
	
//	public void change(String name) {
//		switch (name) {
//		case "OrderPanel":
//			getContentPane().removeAll();
//			add(panel1);
//			revalidate();
//			repaint();
//			break;
//		case "SalesPanel":
//			getContentPane().removeAll();
//			add(panel2);
//			revalidate();
//			repaint();
//			break;
//		default:
//			break;
//		}
//	}
}
