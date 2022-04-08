package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CafeMemberProc extends JFrame implements ActionListener {
	
	// Field
	JFrame     jFrame;
	JPanel     pan1, pan2;
	JButton    btnInsert, btnCancel;
	JLabel     label1, label2;
	JTextField txtName, txtTel;
	
	
	//생성자
	public CafeMemberProc() {
		initComponent();
	}

	private void initComponent() {
		
		// 패널
		jFrame = new JFrame();
		pan1   = new JPanel();
		pan2   = new JPanel();
		
		this.setLayout(null);
		pan2.setLayout(null);
		
		pan1.setBounds(45, 45, 250, 300);
		pan2.setBounds(0, 0, 350, 500);
		
		pan1.setBackground(Color.WHITE);
		pan2.setBackground(Color.GRAY);
		
		this.add( pan2,BorderLayout.CENTER);
		pan2.add( pan1);

		
		setSize( 350, 500 );
		setLocation(100, 100);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		// 라벨
		pan1.setLayout(null);
		
		label1 = new JLabel("이름 입력");
		label1.setBounds(10, 80, 100, 30);
		label2 = new JLabel("전화번호 입력");
		label2.setBounds( 10, 180, 100, 30);
		pan1.add(label1);
		pan1.add(label2);
		
		// 버튼
		btnInsert = new JButton("취소");
		btnCancel = new JButton("등록");
		
		btnInsert.setBounds(0,280,126,20);
		btnCancel.setBounds(125,280,126,20);
		
		pan1.add( btnInsert);
		pan1.add( btnCancel);
		
		btnInsert.addActionListener( this );
		btnCancel.addActionListener( this );

		//텍스트
		txtName = new JTextField(10);
		txtName.setBounds(10, 50, 100, 30);
		txtName.setText("이름");
		txtTel  = new JTextField(20);
		txtTel.setText("전화번호");
		txtTel.setBounds(10, 150, 100, 30);
		pan1.add(txtName);
		pan1.add(txtTel);
		
		setVisible(true);
	}


	public static void main(String[] args) {
		new CafeMemberProc();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
