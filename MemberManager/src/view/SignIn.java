package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class SignIn extends JFrame implements ActionListener{

	JLabel labelId, labelPw, labelName, labelJob, labelGender, labelIntro, labelIndate;
	JPanel panel;
	JTextField txtId, txtName, txtIndate;
	JPasswordField txtPw;
	JComboBox<String> cbJob;
	JRadioButton rbtnMan, rbtnWoman;
	JTextArea txtIntro;
	JButton btnInsert, btnDelete, btnUpdate, btnCancel, btnSearch;
	
	GridBagLayout gbl;
	GridBagConstraints gbc;
	
	public SignIn() {
		setTitle("회원 관리 프로그램 v1.0 - 회원 가입");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(gbl = new GridBagLayout());

		initComponents();

		setSize(400, 600);
		setVisible(true);
	}
	
	private void initComponents(){
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1; gbc.weighty = 1;
		
		// 아이디
		labelId = new JLabel("아이디");
		txtId = new JTextField(20);
		setGbc(labelId, 0, 0, 1, 1);
		setGbc(txtId, 1, 0, 3, 1);
		
		// 암호
		labelPw = new JLabel("암호");
		txtPw = new JPasswordField(20);
		setGbc(labelPw, 0, 1, 1, 1);
		setGbc(txtPw, 1, 1, 3, 1);
		
		// 이름
		labelName = new JLabel("이름");
		txtName = new JTextField(20);
		setGbc(labelName, 0, 2, 1, 1);
		setGbc(txtName, 1, 2, 3, 1);
		
		// 직업
		labelJob = new JLabel("직업");
		cbJob = new JComboBox<String>(new String[] {
				"회사원", "학생", "군인", "없음"
		});
		setGbc(labelJob, 0, 3, 1, 1);
		setGbc(cbJob, 1, 3, 3, 1);
		
		// 성별
		labelGender = new JLabel("성별");
		rbtnMan = new JRadioButton("남자");
		rbtnWoman = new JRadioButton("여자");
		
		ButtonGroup btnGroup = new ButtonGroup();
		btnGroup.add(rbtnMan);
		btnGroup.add(rbtnWoman);
		
		JPanel pGender = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pGender.add(rbtnMan);
		pGender.add(rbtnWoman);
		
		setGbc(labelGender, 0, 4, 1, 1);
		setGbc(pGender, 1, 4, 3, 1);
		
		// 자기소개
		labelIntro = new JLabel("자기 소개");
		txtIntro = new JTextArea();
		txtIntro.setLineWrap(true);
		JScrollPane sp = new JScrollPane(txtIntro);
		gbc.weighty = 3;
		setGbc(labelIntro, 0, 5, 1, 1);
		setGbc(sp, 1, 5, 3, 1);
		
		// 가입일
		labelIndate = new JLabel("가입일");
		txtIndate = new JTextField(LocalDate.now().toString());
		txtIndate.setEditable(false);
		gbc.weighty = 1;
		setGbc(labelIndate, 0, 6, 1, 1);
		setGbc(txtIndate, 1, 6, 3, 1);
		
		// 하단 버튼 btnInsert, btnDelete, btnUpdate, btnCancel, btnSearch
		btnInsert = new JButton("가입");
		btnInsert.addActionListener(this);
		btnDelete = new JButton("삭제");
		btnDelete.addActionListener(this);
		btnUpdate = new JButton("수정");
		btnUpdate.addActionListener(this);
		btnCancel = new JButton("취소");
		btnCancel.setForeground(Color.red);
		btnCancel.addActionListener(this);
		btnSearch = new JButton("조회");
		btnSearch.addActionListener(this);
		
		JPanel pBtns = new JPanel(new FlowLayout());
		pBtns.add(btnInsert);
		pBtns.add(btnDelete);
		pBtns.add(btnUpdate);
		pBtns.add(btnCancel);
		pBtns.add(btnSearch);
		
		setGbc(pBtns, 0, 7, 4, 1);
	}
	
	private void setGbc(JComponent c, int x, int y, int w, int h) {
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = w;
		gbc.gridheight = h;
		gbc.insets = new Insets(2, 2, 2, 2); // 격자와 격자 간의 거리
		gbl.setConstraints(c, gbc);
		add(c);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch (e.getActionCommand()) {
		case "가입":
			
			break;
		case "삭제":
			
			break;
		case "수정":
			
			break;
		case "취소":
			
			break;
		case "조회":
			
			break;
		}
	}
	
}
