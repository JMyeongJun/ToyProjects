package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.LocalDate;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.MemberDao;
import model.MemberVo;

public class MemberProc extends JFrame implements KeyListener {

	private JLabel labelId, labelPw, labelName, labelJob, labelGender, labelIntro, labelIndate;
	private JTextField txtId, txtName, txtIndate;
	private JPasswordField txtPw;
	private JComboBox<String> cbJob;
	private JRadioButton rbtnMan, rbtnWoman;
	private JTextArea txtIntro;
	private JButton btnInsert, btnDelete, btnUpdate, btnCancel, btnSearch;
	private ButtonGroup btnGroup;

	private GridBagLayout gbl;
	private GridBagConstraints gbc;

	private String currentUserId;

	public MemberProc() {
		setTitle("회원 가입");

		initComponents();
	}

	public MemberProc(String userid) {
		currentUserId = userid;
		setTitle("회원 수정 - " + currentUserId);

		initComponents();
		initUpdate(currentUserId);
	}

	private void initComponents() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(gbl = new GridBagLayout());

		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				System.out.println(e.getKeyCode());
			}

			@Override
			public void keyReleased(KeyEvent e) {
				System.out.println(e.getKeyCode());

			}

			@Override
			public void keyPressed(KeyEvent e) {
				System.out.println(e.getKeyCode());
			}
		});

		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;

		// 아이디
		labelId = new JLabel("아이디");
		txtId = new JTextField(20);
		setGbc(new JLabel("아이디"), 0, 0, 1, 1);
		setGbc(txtId, 1, 0, 3, 1);

		// 암호
		labelPw = new JLabel("암호");
		txtPw = new JPasswordField(20);
		setGbc(new JLabel("암호"), 0, 1, 1, 1);
		setGbc(txtPw, 1, 1, 3, 1);

		// 이름
		labelName = new JLabel("이름");
		txtName = new JTextField(20);
		setGbc(new JLabel("이름"), 0, 2, 1, 1);
		setGbc(txtName, 1, 2, 3, 1);

		// 직업
		labelJob = new JLabel("직업");
		cbJob = new JComboBox<String>(new String[] { "회사원", "학생", "군인", "없음" });
		cbJob.setSelectedItem("없음");
		cbJob.setBackground(Color.white);
		setGbc(new JLabel("직업"), 0, 3, 1, 1);
		setGbc(cbJob, 1, 3, 3, 1);

		// 성별
		labelGender = new JLabel("성별");
		rbtnMan = new JRadioButton("남자");
		rbtnWoman = new JRadioButton("여자");

		btnGroup = new ButtonGroup();
		btnGroup.add(rbtnMan);
		btnGroup.add(rbtnWoman);

		JPanel pGender = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pGender.add(rbtnMan);
		pGender.add(rbtnWoman);

		setGbc(new JLabel("성별"), 0, 4, 1, 1);
		setGbc(pGender, 1, 4, 3, 1);

		// 자기소개
		labelIntro = new JLabel("자기 소개");
		txtIntro = new JTextArea();
		txtIntro.setLineWrap(true);
		JScrollPane sp = new JScrollPane(txtIntro);
		gbc.weighty = 3;
		setGbc(new JLabel("자기 소개"), 0, 5, 1, 1);
		setGbc(sp, 1, 5, 3, 1);

		// 가입일
		labelIndate = new JLabel("가입일");
		txtIndate = new JTextField(LocalDate.now().toString());
		txtIndate.setEditable(false);
		gbc.weighty = 1;
		setGbc(new JLabel("가입일"), 0, 6, 1, 1);
		setGbc(txtIndate, 1, 6, 3, 1);

		// 하단 버튼 btnInsert, btnDelete, btnUpdate, btnCancel, btnSearch
		btnInsert = new JButton("가입");
		btnDelete = new JButton("삭제");
		btnUpdate = new JButton("수정");
		btnCancel = new JButton("취소");
		btnCancel.setForeground(Color.red);
		btnSearch = new JButton("조회");

		// 가입 버튼 액션
		btnInsert.addActionListener(e -> {
			MemberDao dao = new MemberDao();

			String userid = txtId.getText();
			String passwd = txtPw.getText();
			String username = txtName.getText();
			String job = ((String) cbJob.getSelectedItem() == "없음") ? null : (String) cbJob.getSelectedItem();
			String gender = "";
			if (rbtnMan.isSelected()) {
				gender = "남";
			} else if (rbtnWoman.isSelected()) {
				gender = "여";
			}
			String intro = txtIntro.getText();

			if (dao.insertMember(userid, passwd, username, job, gender, intro)) {
				System.out.println("회원 가입 성공");
				JOptionPane.showMessageDialog(null, "회원 가입 성공");
				dispose();
			} else {
				System.out.println("회원 가입 실패");
				JOptionPane.showMessageDialog(null, "회원 가입 실패");
			}
		});
		// 삭제 버튼 액션
		btnDelete.addActionListener(e -> {
			MemberDao dao = new MemberDao();

			int res = JOptionPane.showConfirmDialog(null, currentUserId + "를 삭제하시겠습니까?", "회원 삭제",
					JOptionPane.YES_NO_OPTION);

			if (res == 0) {
				if (dao.deleteMember(currentUserId)) {
					System.out.println(currentUserId + "가 삭제되었습니다");
					JOptionPane.showMessageDialog(null, currentUserId + "가 삭제되었습니다");
					dispose();
				} else {
					System.out.println("삭제 오류!");
					JOptionPane.showMessageDialog(null, currentUserId + "삭제 오류!!");
				}
			}
		});
		// 수정 버튼 액션
		btnUpdate.addActionListener(e -> {
			MemberDao dao = new MemberDao();

			dao.updateMember(currentUserId, "PASSWD", txtPw.getText());
			dao.updateMember(currentUserId, "USERNAME", txtName.getText());
			dao.updateMember(currentUserId, "JOB", (String) cbJob.getSelectedItem());
			if (rbtnMan.isSelected()) {
				dao.updateMember(currentUserId, "GENDER", "남");
			} else if (rbtnWoman.isSelected()) {
				dao.updateMember(currentUserId, "GENDER", "여");
			}
			dao.updateMember(currentUserId, "INTRO", txtIntro.getText());

			JOptionPane.showMessageDialog(null, "수정 되었습니다.", "아이디 - " + currentUserId, JOptionPane.DEFAULT_OPTION);
			dispose();
		});
		// 취소 버튼 액션
		btnCancel.addActionListener(e -> {
			dispose();
		});
		// 조회 버튼 액션
		btnSearch.addActionListener(e -> {
			checkId();
		});

		JPanel pBtns = new JPanel(new FlowLayout());
		pBtns.add(btnInsert);
		pBtns.add(btnDelete);
		pBtns.add(btnUpdate);
		pBtns.add(btnCancel);
		pBtns.add(btnSearch);
		setGbc(pBtns, 0, 7, 4, 1);

		txtId.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					checkId();
				}
			}
		});

		// 버튼 비활성화
		btnDelete.setEnabled(false);
		btnUpdate.setEnabled(false);

		setSize(400, 600);
		setVisible(true);
	}

	// 조회 버튼 클릭시 id validation 조회
	private void checkId() {
		MemberDao dao = new MemberDao();
		if (dao.getMember(txtId.getText()) != null) {
			System.out.println("중복된 아이디입니다.");
		} else if (txtId.getText().length() != 0 && txtId.getText().length() <= 12) {
			System.out.println("사용 가능한 아이디입니다.");
		} else {
			System.out.println("사용 불가능한 아이디입니다");
		}
	}

	// GridBagConstraints 설정 함수
	private void setGbc(JComponent c, int x, int y, int w, int h) {
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = w;
		gbc.gridheight = h;
		gbc.insets = new Insets(2, 2, 2, 2); // 격자와 격자 간의 거리
		gbl.setConstraints(c, gbc);
		add(c);
	}

	// 수정시 요소 초기화
	private void initUpdate(String userid) {
		MemberDao dao = new MemberDao();

		// MemberVo 가지고 오기
		MemberVo vo = dao.getMember(userid);

		// vo로 각 텍스트필드 초기화
		txtId.setText(vo.getUserid());
		txtPw.setText(vo.getPasswd());
		txtName.setText(vo.getUsername());
		cbJob.setSelectedItem(vo.getJob());
		if (vo.getGender() != null) {
			if (vo.getGender().equals("남")) {
				rbtnMan.setSelected(true);
			} else if (vo.getGender().equals("여")) {
				rbtnWoman.setSelected(true);
			}
		}
		txtIntro.setText(vo.getIntro());
		txtIndate.setText(vo.getIndate());

		// 가입 버튼 비활성화
		btnInsert.setEnabled(false);
		// 아이디 필드 비활성화
		txtId.setEditable(false);
		// 삭제, 수정 버튼 활성화
		btnDelete.setEnabled(true);
		btnUpdate.setEnabled(true);
	}

	// 창 닫을 시 동작
	@Override
	public void dispose() {
		super.dispose();
		System.out.println("MemberProc disposed");
		MemberList.tableRefresh(MemberList.getTable());
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
