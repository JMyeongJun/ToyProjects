package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import model.MemberDao;
import model.MemberVo;

public class MemberList extends JFrame implements ActionListener {

	JPanel panelBtn;
	JButton btnSignIn, btnEdit, btnRefresh, btnExcel, btnSearch;
	static JTable table;
	JScrollPane scrollpane;

	MemberProc signIn;

	public MemberList() {
		initComponents();
	}

	// 초기화
	private void initComponents() {
		// 프레임 설정
		setTitle("회원 관리 프로그램 v1.0");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());

		// 버튼 패널 설정
		panelBtn = new JPanel();
		btnSignIn = new JButton("회원 가입");
		btnSignIn.addActionListener(this);
		btnEdit = new JButton("수정");
		btnEdit.addActionListener(this);
		btnRefresh = new JButton("새로 고침");
		btnRefresh.addActionListener(this);
		btnExcel = new JButton("엑셀로 저장");
		btnExcel.addActionListener(this);
		btnSearch = new JButton("ID 검색");
		btnSearch.addActionListener(this);

		panelBtn.setLayout(new FlowLayout());
		panelBtn.add(btnSignIn);
		panelBtn.add(btnEdit);
		panelBtn.add(btnRefresh);
		panelBtn.add(btnExcel);
		panelBtn.add(btnSearch);

		// 테이블 패널 설정
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.addMouseListener(new TableMouseListener());
		tableRefresh(table);
		
		scrollpane = new JScrollPane(table);

		// 메인 프레임에 추가
		add(panelBtn, BorderLayout.NORTH);
		add(scrollpane, BorderLayout.CENTER);

		// 프레임 설정 및 화면 출력
		setSize(600, 500);
		setVisible(true);
	}

	// 버튼 ActionListener 설정 
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "회원 가입":
			System.out.println("Sign in");
			showMemberProc();
			break;
		case "수정":
			System.out.println("Edit");
			edit();
			break;
		case "새로 고침":
			System.out.println("Refresh");
			tableRefresh(table);
			break;
		case "엑셀로 저장":
			System.out.println("Save Excel");
			saveExcel();
			break;
		case "ID 검색":
			System.out.println("Search");
			String searchId = JOptionPane.showInputDialog("조회할 ID 입력");
			if (searchId != null) {
				tableRefresh(table, searchId);
			}
			break;
		}
	}

	// 테이블 새로고침
	public static void tableRefresh(JTable table) {
		tableRefresh(table, "");
	}

	// 테이블 새로고침 - id 검색 결과
	private static void tableRefresh(JTable table, String searchId) {
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

	// Table - Column 리스트 반환
	public static Vector<?> getColumnList() {
		Vector<String> cols = new Vector<String>();
		cols.add("아이디");
		cols.add("이름");
		cols.add("직업");
		cols.add("성별");
		cols.add("가입일");
		return cols;
	}

	// Table - 데이터(rows) 반환
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

	// 테이블 getter
	public static JTable getTable() {
		return table;
	}

	// 테이블 mouseListener 설정 클래스
	class TableMouseListener implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			// 테이블 row 더블 클릭시 수정
			if (e.getClickCount() == 2) {
				edit();
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}
	}
	
	// 회원 정보 수정
	private void edit() {
		try {
			String userid = (String) table.getModel().getValueAt(table.getSelectedRow(), 0);
			showMemberProc(userid);
		} catch (Exception e) {
			System.out.println("No Selected Row!!");
		}
	}
	
	// MemberProc창 띄우기 - 회원가입
	private void showMemberProc() {
		if (signIn == null) {
			signIn = new MemberProc();
		} else if (!signIn.isVisible()) {
			signIn = null;
			signIn = new MemberProc();
		}
	}
	
	// MemberProc창 띄우기 - 수정
	private void showMemberProc(String userid) {
		if (signIn == null) {
			signIn = new MemberProc(userid);
		} else if (!signIn.isVisible()) {
			signIn = null;
			signIn = new MemberProc(userid);
		}
	}

	// 엑셀 저장 - 저장 다이얼로그
	private void saveExcel() {
		File file = null;
		JFileChooser fc = new JFileChooser(System.getProperty("user.home") + "/Desktop");
		fc.setFileFilter(new FileNameExtensionFilter("*.xlsx", "xlsx"));
		fc.addChoosableFileFilter(new FileNameExtensionFilter("*.xls", "xls"));
		fc.addChoosableFileFilter(new FileNameExtensionFilter("*.csv", "csv"));
		fc.addChoosableFileFilter(new FileNameExtensionFilter("*.txt", "txt"));
		
		if(fc.showSaveDialog(fc) == JFileChooser.CANCEL_OPTION) {
			return;
		};
		
		String filter = fc.getFileFilter().getDescription();
		
		if (filter.equals("모든 파일")) {
			file = fc.getSelectedFile();
		}else {
			file = new File(fc.getSelectedFile().toString() + filter.substring(1));
		}
		
		new SaveExcel(file);
//		writeFile(file);
	}
	
	// 엑셀 저장 - 파일 쓰기
	private void writeFile(File file) {
		int rowCount = table.getRowCount();
		try {
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			
			bw.write("아이디,이름,직업,성별,가입일\n");

			MemberDao dao = new MemberDao();
			for(int i = 0; i < rowCount; i++) {
				MemberVo vo = dao.getMember((String)table.getValueAt(i, 0));
				bw.write(vo.toString2() + "\n");
			}
			
			bw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "저장 에러", "에러", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	// main 실행
	public static void main(String[] args) {
		new MemberList();
	}

}
