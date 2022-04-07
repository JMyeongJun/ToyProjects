package view;

import javax.swing.JButton;
import javax.swing.JLabel;

public class SalesPanel extends BasicPanel {
	MainFrame mf;
	JButton btn;

	public SalesPanel(MainFrame main) {
		mf = main;

		top_top.add(new JLabel("SalesPanel"));

		btn = new JButton("btn2");
//		btn.addActionListener(e -> {
//			mf.change("OrderPanel");
//		});

		side.add(btn);
	}
}
