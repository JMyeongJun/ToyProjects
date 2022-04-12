package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.MemberDao;
import model.MemberVo;

public class MemberPanel extends BasicPanel implements ActionListener, MouseListener {
	MainFrame mf;
	
	JButton btnInsert, btnUpdate;
	JScrollPane pane;
	static JTable jTable;
	JLabel lbl;

	MemberInsert proc = null;
	MemberUpdate proc2 = null;

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

		side.setLayout(null);
		// 버튼 등록
		btnInsert = new JButton("회원등록");
		btnUpdate = new JButton("회원수정");

		btnInsert.setBounds(100, 450, 100, 30);
		btnUpdate.setBounds(100, 550, 100, 30);

		side.add(btnInsert);
		side.add(btnUpdate);

		// 라벨 추가

		top_bottom.setLayout(null);

		lbl = new JLabel("회원 목록");
		lbl.setBounds(10, -10, 150, 100);
		lbl.setFont(new Font("Serif", Font.BOLD, 10));
		lbl.setFont(lbl.getFont().deriveFont(25.0f));
		top_bottom.add(lbl);

		// JTable 추가
		jTable = new JTable();
		jTable.setModel(new DefaultTableModel(getDataList(), getColumnList()) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		pane = new JScrollPane(jTable);
		bottom.setLayout(new BorderLayout());
		bottom.add(pane, BorderLayout.CENTER);

		// 기능 추가
		btnInsert.addActionListener(this);
		btnUpdate.addActionListener(this);

		jTable.addMouseListener(this);

		setVisible(true);
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

	// button event 연결 click
	@Override
	public void actionPerformed(ActionEvent e) {

		switch (e.getActionCommand()) {
		case "회원등록":
			if (proc != null) {
				proc.dispose();
			}
			proc = new MemberInsert();

			jTableRefresh();
			break;
		case "회원수정":
			System.out.println(e);
			int c = jTable.getSelectedColumn();
			int r = jTable.getSelectedRow();

			String id = (String) jTable.getValueAt(r, 0);
			String number = (String) jTable.getValueAt(r, 1);
			String point = (String) jTable.getValueAt(r, 2);
			System.out.println(id);

			if (proc2 != null)
				proc2.dispose();
			proc2 = new MemberUpdate(id, number, point, this);
			break;
		}
	}

	public static void jTableRefresh() {
		jTable.setModel(new DefaultTableModel(getDataList(), getColumnList()) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		jTable.repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// 마우스 더블 클릭
		if (e.getClickCount() == 2) {

			System.out.println(e);
			int c = jTable.getSelectedColumn();
			int r = jTable.getSelectedRow();

			String id = (String) jTable.getValueAt(r, 0);
			String number = (String) jTable.getValueAt(r, 1);
			String point = (String) jTable.getValueAt(r, 2);
			System.out.println(id);

			if (proc2 != null)
				proc2.dispose();
			proc2 = new MemberUpdate(id, number, point, this);
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}