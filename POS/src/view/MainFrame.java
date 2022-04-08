package view;

import javax.swing.JFrame;
import javax.swing.JPanel;

// 메인 실행창
public class MainFrame extends JFrame {

	JPanel OrderPanel;

	public MainFrame() {
		init();
	}

	public void init() {
		setTitle("MainFrame");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		// new OrderPanel();부분 자기걸로 변경!!!!!!
		OrderPanel = new OrderPanel();

		add(OrderPanel);

		setSize(1000, 700);
		setVisible(true);
	}

	public static void main(String[] args) {
		new MainFrame();
	}

}
