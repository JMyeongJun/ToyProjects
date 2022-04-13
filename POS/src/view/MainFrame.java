package view;

import javax.swing.JFrame;
import javax.swing.JPanel;

// 메인 실행창
public class MainFrame extends JFrame {

	static JPanel mainPanel;

	public MainFrame() {
		init();
	}

	public void init() {
		setTitle("카페 POS");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	public static void main(String[] args) {
		MainFrame mf = new MainFrame();

		mainPanel = new HomePanel(mf);

		mf.add(mainPanel);

		mf.setSize(1100, 700);
		mf.setVisible(true);
	}

	public void changePanel(JPanel panel) {
		mainPanel = panel;
		
		getContentPane().removeAll();
		getContentPane().add(mainPanel);
		revalidate();
		repaint();
	}

}
