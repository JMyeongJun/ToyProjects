package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.MemberDao;
import model.MemberVo;

public class MemberList extends JFrame implements ActionListener {

	JPanel panelBtn;
	JButton btnSignIn, btnRefresh, btnExcel, btnSearch;
	static JTable table;
	JScrollPane scrollpane;

	MemberProc signIn;

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
		btnSearch = new JButton("ID 검색");
		btnSearch.addActionListener(this);

		panelBtn.setLayout(new FlowLayout());

		panelBtn.add(btnSignIn);
		panelBtn.add(btnRefresh);
		panelBtn.add(btnExcel);
		panelBtn.add(btnSearch);

		// 테이블 패널 설정
		table = new JTable();
		tableRefresh(table);
		table.addMouseListener(new TableMouseListener());

		scrollpane = new JScrollPane(table);

		// 메인 프레임 추가
		add(panelBtn, BorderLayout.NORTH);
		add(scrollpane, BorderLayout.CENTER);
	}

	public static void tableRefresh(JTable table) {
		tableRefresh(table, "");
	}

	public static void tableRefresh(JTable table, String searchId) {
		MemberDao dao = new MemberDao();
		table.setModel(new DefaultTableModel(getDataList(dao.getMemberList(searchId)), getColumnList()) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		table.repaint();
	}

	private static Vector<?> getColumnList() {
		Vector<String> cols = new Vector<String>();
		cols.add("아이디");
		cols.add("이름");
		cols.add("직업");
		cols.add("성별");
		cols.add("가입일");
		return cols;
	}

	private static Vector<? extends Vector<String>> getDataList(Vector<MemberVo> voList) {
		Vector<Vector<String>> list = new Vector<Vector<String>>();

		for (MemberVo vo : voList) {
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

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "회원 가입":
			System.out.println("Sign in");
			if (signIn == null) {
				signIn = new MemberProc();
			} else if (!signIn.isVisible()) {
				signIn = null;
				signIn = new MemberProc();
			}
			break;
		case "새로 고침":
			System.out.println("Refresh");
			tableRefresh(table);
			break;
		case "엑셀로 저장":
			System.out.println("Save Excel");
			break;
		case "ID 검색":
			System.out.println("Search");
			String searchId = JOptionPane.showInputDialog("조회할 ID 입력");
			if (searchId != null) {
				tableRefresh(table, searchId);
			}
		}
	}

	public static JTable getTable() {
		return table;
	}

	class TableMouseListener implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			if (e.getClickCount() == 2) {
				System.out.println("수정");
				String userid = (String) table.getModel().getValueAt(table.getSelectedRow(), 0);
				if (signIn == null) {
					signIn = new MemberProc(userid);
				} else if (!signIn.isVisible()) {
					signIn = null;
					signIn = new MemberProc(userid);
				}
			}
		}
		@Override
		public void mousePressed(MouseEvent e) {}
		@Override
		public void mouseReleased(MouseEvent e) {}
		@Override
		public void mouseEntered(MouseEvent e) {}
		@Override
		public void mouseExited(MouseEvent e) {}
	}

	public static void main(String[] args) {
		MemberList ml = new MemberList();
	}

}
