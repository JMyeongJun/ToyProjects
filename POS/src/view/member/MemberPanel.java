package view.member;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.MemberDao;
import model.MemberVo;
import view.BasicPanel;
import view.HomePanel;
import view.MainFrame;

public class MemberPanel extends BasicPanel implements ActionListener, MouseListener {
	MainFrame mf;
	
	JButton btnInsert, btnUpdate;
	JScrollPane pane;
	static JTable table;
	JLabel lblMemberList;

	MemberInsert memberInsert = null;
	MemberUpdate memberUpdate = null;

	public MemberPanel() {
		initComponent();
	}
	
	public MemberPanel(MainFrame mf) {
		this();
		this.mf = mf;
		btnPrev.addActionListener(e -> {
			mf.changePanel(new HomePanel(mf));
		});
	}

	private void initComponent() {
		subTitle.setText("회원관리");

		// side 버튼 등록
		btnInsert = new JButton("회원등록");
		btnInsert.setFont(basicFont(20, Font.BOLD));
		btnUpdate = new JButton("회원수정");
		btnUpdate.setFont(basicFont(20, Font.BOLD));
		side.setLayout(new GridLayout(0, 1, 30, 30));
		side.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		for(int i = 0; i < 5; i++) {
			JPanel emptyPanel = new JPanel();
			emptyPanel.setBackground(colorSide);
			side.add(emptyPanel);
		}
		side.add(btnInsert);
		side.add(btnUpdate);

		// top_bottom 라벨 추가
		top_bottom.setLayout(null);

		lblMemberList = new JLabel("회원 목록");
		lblMemberList.setBounds(10, -10, 150, 100);
		lblMemberList.setFont(basicFont(30, Font.BOLD));
		lblMemberList.setFont(lblMemberList.getFont().deriveFont(25.0f));
		top_bottom.add(lblMemberList);

		// bottom JTable 추가
		table = new JTable();
		table.setRowHeight(30);
		table.setFont(basicFont(20, Font.PLAIN));
		tableRefresh(table);
		
		pane = new JScrollPane(table);
		bottom.setLayout(new BorderLayout());
		bottom.add(pane, BorderLayout.CENTER);

		// action 추가
		btnInsert.addActionListener(this);
		btnUpdate.addActionListener(this);
		table.addMouseListener(this);

		// 출력
		setVisible(true);
	}


	// button event 연결 click
	@Override
	public void actionPerformed(ActionEvent e) {

		switch (e.getActionCommand()) {
		case "회원등록":
			if (memberInsert != null) {
				memberInsert.dispose();
			}
			memberInsert = new MemberInsert();

			tableRefresh(table);
			break;
		case "회원수정":
			int r = table.getSelectedRow();

			if(r < 0) {
				JOptionPane.showMessageDialog(null, "수정할 회원을 클릭해주세요.", "확인", JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			String id = (String) table.getValueAt(r, 0);
			String number = (String) table.getValueAt(r, 1);
			String point = (String) table.getValueAt(r, 2);

			if (memberUpdate != null) {
				memberUpdate.dispose();
			}
			memberUpdate = new MemberUpdate(id, number, point, this);
			break;
		}
	}

	public static void tableRefresh(JTable table) {
		table.setModel(new DefaultTableModel(getDataList(), getColumnList()) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		table.repaint();
	}
	
	// Table - 데이터(rows) 반환
	private static Vector<? extends Vector<String>> getDataList() {
		MemberDao dao = new MemberDao();
		Vector<MemberVo> voList = dao.getMemberList();
		Vector<Vector<String>> list = new Vector<Vector<String>>();
		
		for (MemberVo vo : voList) {
			Vector<String> row = new Vector<String>();
			row.add(vo.getMemberName());
			row.add(vo.getPhoneNumber());
			row.add(Integer.toString(vo.getPoint()));
			
			list.add(row);
		}
		return list;
	}
	
	private static Vector<?> getColumnList() {
		Vector<String> cols = new Vector<String>();
		cols.add("이름");
		cols.add("전화번호");
		cols.add("포인트");
		return cols;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// 마우스 더블 클릭
		if (e.getClickCount() == 2) {
			int r = table.getSelectedRow();

			String id = (String) table.getValueAt(r, 0);
			String number = (String) table.getValueAt(r, 1);
			String point = (String) table.getValueAt(r, 2);

			if (memberUpdate != null) {
				memberUpdate.dispose();
			}
			memberUpdate = new MemberUpdate(id, number, point, this);
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