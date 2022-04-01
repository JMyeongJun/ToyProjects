package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.MemberDao;
import model.MemberVo;

public class MemberList extends JFrame implements ActionListener {

	JPanel panelBtn;
	JButton btnSignIn, btnRefresh, btnExcel;
	JTable table;
	JScrollPane scrollpane;

	SignIn signIn;
	
	public MemberList() {
		setTitle("회원 관리 프로그램 v1.0");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());

		initComponents();

		setSize(600, 500);
		setVisible(true);
	}

	private void initComponents() {
		// 버튼 패널 설정
		panelBtn = new JPanel();
		btnSignIn = new JButton("회원 가입");
		btnSignIn.addActionListener(this);
		btnRefresh = new JButton("새로 고침");
		btnRefresh.addActionListener(this);
		btnExcel = new JButton("엑셀로 저장");
		btnExcel.addActionListener(this);

		panelBtn.setLayout(new FlowLayout());

		panelBtn.add(btnSignIn);
		panelBtn.add(btnRefresh);
		panelBtn.add(btnExcel);

		// 테이블 패널 설정
		table = new JTable();
		tableRefresh(table);

		scrollpane = new JScrollPane(table);

		// 메인 프레임 추가
		add(panelBtn, BorderLayout.NORTH);
		add(scrollpane, BorderLayout.CENTER);
	}
	
	private void tableRefresh(JTable table) {
		table.setModel(new DefaultTableModel(getDataList(), getColumnList()) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
	}

	private Vector<?> getColumnList() {
		Vector<String> cols = new Vector<String>();
		cols.add("아이디");
		cols.add("이름");
		cols.add("직업");
		cols.add("성별");
		cols.add("가입일");
		return cols;
	}

	private Vector<? extends Vector<String>> getDataList() {
		Vector<Vector<String>> list = new Vector<Vector<String>>();

		MemberDao dao = new MemberDao();

		for (MemberVo vo : dao.getMemberList()) {
			Vector<String> row = new Vector<String>();

			row.add(vo.getUserid());
			row.add(vo.getUsername());
			row.add(vo.getJob());
			row.add(vo.getGender());
			row.add(vo.getIndate());

			list.add(row);
		}
		return list;
	}

	public static void main(String[] args) {
		new MemberList();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "회원 가입":
			System.out.println("sign in");
			if(signIn == null) {
				signIn = new SignIn();
			}else if(!signIn.isVisible()){
				signIn = null;
				signIn = new SignIn();
			}
			break;
		case "새로 고침":
			System.out.println("refresh");
			tableRefresh(table);
			table.repaint();
			break;
		case "엑셀로 저장":
			System.out.println("save excel");
			break;
		}
	}

}
