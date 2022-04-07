package view;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {

	JPanel OrderPanel;

	public MainFrame() {
		init();
	}

	public void init() {
		setTitle("MainFrame");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
//		setLocationRelativeTo(null);

		OrderPanel = new OrderPanel();

		add(OrderPanel);

		setSize(1000, 700);
		setVisible(true);
	}

	public static void main(String[] args) {
		new MainFrame();
	}

}
