package view.member;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.MemberDao;
import model.MemberVo;

public class MemberUpdate extends JFrame implements ActionListener {
	// Field
	MemberPanel memberPanel = null;

	String currentName = null;
	String currentPhoneNumber = null;
	String currentPoint = null;

	JLabel lblName, lblTel, lblPhoneNumber, lblPoint;
	JTextField txtName, txtPhoneNumber, txtPoint;
	JButton btnUpdate, btnCancel, btnDelete;

	GridBagLayout gbl;
	GridBagConstraints gbc;

	// 생성자
	public MemberUpdate() {
		initComponent();
	}

	public MemberUpdate(String name, String number, String point, MemberPanel memberPanel) {
		this();
		this.memberPanel = memberPanel;
		this.currentName = name;
		this.currentPhoneNumber = number;
		this.currentPoint = point;
	}

	private void initComponent() {
		gbl = new GridBagLayout();
		this.setLayout(gbl);

		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;

		// 패널
		this.setLayout(new GridBagLayout());
		this.getContentPane().setBackground(Color.WHITE);

		setSize(350, 500);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		lblName = new JLabel("이름");
		txtName = new JTextField(10);
		txtName.setText(currentName);
		gbAdd(lblName, 0, 0, 1, 1);
		gbAdd(txtName, 0, 1, 3, 1);

		lblPhoneNumber = new JLabel("전화번호");
		txtPhoneNumber = new JTextField(10);
		txtPhoneNumber.setText(currentPhoneNumber);
		gbAdd(lblPhoneNumber, 0, 2, 1, 1);
		gbAdd(txtPhoneNumber, 0, 3, 3, 1);

		lblPoint = new JLabel("보유 포인트");
		txtPoint = new JTextField(10);
		txtPoint.setText(currentPoint);
		txtPoint.setEditable(false);
		gbAdd(lblPoint, 0, 4, 1, 1);
		gbAdd(txtPoint, 0, 5, 3, 1);

		// 버튼
		btnCancel = new JButton("취소");
		btnUpdate = new JButton("수정");
		btnDelete = new JButton("삭제");
		gbAdd(btnCancel, 0, 6, 1, 1);
		gbAdd(btnDelete, 1, 6, 1, 1);
		gbAdd(btnUpdate, 2, 6, 1, 1);

		btnCancel.addActionListener(this);
		btnUpdate.addActionListener(this);
		btnDelete.addActionListener(this);

		setVisible(true);
	}

	private void gbAdd(JComponent c, int x, int y, int w, int h) {
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = w;
		gbc.gridheight = h;
		gbl.setConstraints(c, gbc);
		gbc.insets = new Insets(2, 2, 2, 2);
		this.add(c, gbc);
	}

	// 버튼 event 연결
	@Override
	public void actionPerformed(ActionEvent e) {
		MemberDao dao = new MemberDao();

		switch (e.getActionCommand()) {
		case "수정":
			dao.updateMember(currentPhoneNumber, txtName.getText(), txtPhoneNumber.getText());
			MemberPanel.tableRefresh(MemberPanel.table);
			dispose();
			break;
		case "삭제":
			dao.deleteMember(currentPhoneNumber);
			MemberPanel.tableRefresh(MemberPanel.table);
			dispose();
			break;
		case "취소":
			dispose();
			break;
		}
	}

}