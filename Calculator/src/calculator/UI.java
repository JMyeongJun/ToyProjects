package calculator;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class UI extends JFrame {

	JLabel label;
	JPanel panel;
	JButton[] num;
	JButton sum, sub, mul, div, back, erase, equal, pm, dot;

	Double operand1 = null, operand2 = null;
	char operator;

	public UI() {
		setTitle("Calculator");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(400, 600);
		setLayout(new GridBagLayout());
		setLocationRelativeTo(null);

		initComponents();

		setVisible(true);
		System.out.println("실행");
	}

	// 컴포넌트 초기화
	private void initComponents() {
		label = new JLabel("0");
		label.setBackground(Color.gray);
		label.setOpaque(true);
		label.setHorizontalAlignment(JLabel.RIGHT);
		label.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

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

	// 레이아웃 초기화
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

	// 레이아웃 초기화 함수
	private void setGbc(GridBagConstraints gbc, int gridx, int gridy, int gridwidth, int gridheight) {
		gbc.gridx = gridx;
		gbc.gridy = gridy;
		gbc.gridwidth = gridwidth;
		gbc.gridheight = gridheight;
	}
	
	// 폰트 초기화
	private void initFont() {
		changeFont(this, new Font("San Serif", Font.BOLD, 30));
		label.setFont(new Font("San Serif", Font.BOLD, 45));
	}
	
	// 폰트 초기화 함수
	private void changeFont(Component component, Font font) {
		component.setFont(font);
		if (component instanceof Container) {
			for (Component child : ((Container) component).getComponents()) {
				changeFont(child, font);
			}
		}
	}

	// 버튼 액션 초기화
	private void initActions() {
		for (int i = 0; i < num.length; i++) {
			num[i].addActionListener(new NumBtnAction());
		}
		sum.addActionListener(new CalcBtnAction());
		sub.addActionListener(new CalcBtnAction());
		mul.addActionListener(new CalcBtnAction());
		div.addActionListener(new CalcBtnAction());
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String labelText = label.getText();
				if (!labelText.equals("0")) {
					if (labelText.length() == 1) {
						label.setText("0");
					} else {
						label.setText(labelText.substring(0, labelText.length() - 1));
					}
				}
			}
		});
		erase.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				label.setText("0");
				operand1 = null;
				operand2 = null;
				operator = ' ';
			}
		});
		equal.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (operand1 != null && operator != ' ') {
					if(operand2 == null) {
						operand2 = Double.parseDouble(label.getText());
					}else {
						operand1 = Double.parseDouble(label.getText());
					}
					
					System.out.println("operand2 : " + operand2);
					String str = operand1.toString() + operator + operand2.toString();
					System.out.println("str : " + str);

					double ret = 0;
					switch (operator) {
					case '+':
						ret = operand1 + operand2;
						break;
					case '-':
						ret = operand1 - operand2;
						break;
					case '*':
						ret = operand1 * operand2;
						break;
					case '/':
						ret = operand1 / operand2;
						break;
					}
					
					label.setText(String.valueOf(ret));
				}
			}
		});
		pm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String labelText = label.getText();
				if(!labelText.equals("0") && labelText.charAt(0) != '-') {
					label.setText("-" + labelText);
				}else if(labelText.charAt(0) == '-'){
					label.setText(labelText.substring(1));
				}
			}
		});
		dot.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!label.getText().contains(".")) {
					label.setText(label.getText() + ".");
				}
			}
		});
	}

	// 숫자 버튼 액션 클래스
	class NumBtnAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
//			System.out.println(e.getActionCommand());
			if (label.getText().equals("0")) {
				label.setText(e.getActionCommand());
			} else {
				label.setText(label.getText() + e.getActionCommand());
			}
		}
	}
	
	// 연산자 버튼 액션 클래스
	class CalcBtnAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
//			System.out.println(e.getActionCommand());
			if (operand1 == null) {
				operand1 = Double.parseDouble(label.getText());
				System.out.println("operand1 : " + operand1);
			}
			operator = e.getActionCommand().charAt(0);
			System.out.println("calc : " + operator);
			label.setText("0");
		}
	}

}
