package calculator;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Calc extends JFrame{

	JLabel label;
	JPanel panel;
	JButton[] num;
	JButton sum, sub, mul, div, back, erase, equal, pm, dot;
	
	Double a = null, b = null;
	char calc;

	public Calc() {
		setTitle("Calculator");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(400, 600);
		setLayout(new GridBagLayout());
		setLocationRelativeTo(null);

		initComponents();

		setVisible(true);
		System.out.println("½ÇÇà");
	}

	private void initComponents() {
		label = new JLabel(" ");
		label.setBackground(Color.gray);
		label.setOpaque(true);
		label.setHorizontalAlignment(JLabel.RIGHT);
		label.setBorder(BorderFactory.createEmptyBorder(0,10,0,10));

		panel = new JPanel();
		panel.setLayout(new GridBagLayout());

		num = new JButton[10];
		for (int i = 0; i < num.length; i++) {
			num[i] = new JButton(Integer.toString(i));
		}

		sum = new JButton("+");
		sub = new JButton("-");
		mul = new JButton("*");
		div = new JButton("/");
		back = new JButton("<-");
		erase = new JButton("C");
		equal = new JButton("=");
		pm = new JButton("+/-");
		dot = new JButton(".");

		initLayout();
		initFont();
		initActions();
		
	}

	public void initLayout() {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		// frame
		gbc.weightx = 1;
		gbc.weighty = 1;
		setGbc(gbc, 0, 0, 1, 1);
		add(label, gbc);
		gbc.weighty = 1.5;
		setGbc(gbc, 0, 1, 1, 1);
		add(panel, gbc);

		// panel
		gbc.weighty = 1;
		setGbc(gbc, 0, 0, 1, 1);
		panel.add(sum, gbc); // +
		setGbc(gbc, 1, 0, 1, 1);
		panel.add(sub, gbc); // -
		setGbc(gbc, 2, 0, 1, 1);
		panel.add(mul, gbc); // *
		setGbc(gbc, 3, 0, 1, 1);
		panel.add(div, gbc); // /
		setGbc(gbc, 3, 1, 1, 1);
		panel.add(back, gbc); // <-
		setGbc(gbc, 3, 2, 1, 1);
		panel.add(erase, gbc); // C
		setGbc(gbc, 3, 3, 1, 2);
		panel.add(equal, gbc); // =
		setGbc(gbc, 0, 4, 1, 1);
		panel.add(pm, gbc); // +/-
		setGbc(gbc, 2, 4, 1, 1);
		panel.add(dot, gbc); // .

		// num(1~9)
		for (int i = 3; i >= 1; i--) {
			for (int j = 0; j <= 2; j++) {
				setGbc(gbc, j, i, 1, 1);
				panel.add(num[3 * (3 - i) + j + 1], gbc);
			}
		}
		// num(0)
		setGbc(gbc, 1, 4, 1, 1);
		panel.add(num[0], gbc);
	}
	
	private void initFont() {
		changeFont(this, new Font("San Serif", Font.BOLD, 30));
		label.setFont(new Font("San Serif", Font.BOLD, 45));
	}

	private void initActions() {
		for (int i = 0; i < num.length; i++) {
			num[i].addActionListener(new NumBtnAction());
		}
		sum.addActionListener(new CalcBtnAction());
		sub.addActionListener(new CalcBtnAction());
		mul.addActionListener(new CalcBtnAction());
		div.addActionListener(new CalcBtnAction());
	}
	
	private void setGbc(GridBagConstraints gbc, int gridx, int gridy, int gridwidth, int gridheight) {
		gbc.gridx = gridx;
		gbc.gridy = gridy;
		gbc.gridwidth = gridwidth;
		gbc.gridheight = gridheight;
	}

	private void changeFont(Component component, Font font) {
		component.setFont(font);
		if (component instanceof Container) {
			for (Component child : ((Container) component).getComponents()) {
				changeFont(child, font);
			}
		}
	}

	class NumBtnAction implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			System.out.println(e.getActionCommand().trim());
			label.setText(label.getText() + e.getActionCommand().trim());
		}
	}
	
	class CalcBtnAction implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			System.out.println(e.getActionCommand().trim());
			if(a == null) {
				a = Double.parseDouble(label.getText());
				System.out.println("a : " + a);
			}
			calc = e.getActionCommand().charAt(0);
			System.out.println("calc : " + calc);
		}
	}
	
	public static void main(String[] args) {
		new Calc();
	}

}
