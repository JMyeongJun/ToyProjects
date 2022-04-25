package view;

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

	String id = null;
	String number = null;
	String point = null;

	JFrame jFrame;
	JButton btnUpdate, btnCancel, btnDelete, btnFind;
	JLabel lblName, lblTel, lblCurrent, lblPoint;
	JTextField txtName, txtTel, txtCurrent, txtPoint;

	GridBagLayout gbl;
	GridBagConstraints gbc;

	// 생성자
	public MemberUpdate() {
		initComponent();
	}

	public MemberUpdate(MemberPanel memberPanel) {
		this();
		this.memberPanel = memberPanel;
	}

	public MemberUpdate(String id, String number, String point, MemberPanel memberPanel) {
		this(memberPanel);
		this.id = id;
		this.number = number;
		this.point = point;

		txtName.setText(id);
		txtCurrent.setText(number);
		txtPoint.setText(point);
	}

	private void initComponent() {
		gbl = new GridBagLayout();
		this.setLayout(gbl);

		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		// 여백을 분배하는 변수, 모두 0이면 가운데로 모임

		// 패널
		this.getContentPane().setBackground(Color.WHITE);

		setSize(350, 500);
		setLocation(300, 100);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// 라벨
		this.setLayout(new GridBagLayout());

		lblName = new JLabel("이름");
		txtName = new JTextField(10);
		txtName.setText("");
		gbAdd(lblName, 0, 0, 1, 1);
		gbAdd(txtName, 0, 1, 3, 1);

		lblCurrent = new JLabel("전화번호");
		txtCurrent = new JTextField(10);
		txtCurrent.setText("");
		gbAdd(lblCurrent, 0, 2, 1, 1);
		gbAdd(txtCurrent, 0, 3, 3, 1);

		lblPoint = new JLabel("보유 포인트");
		txtPoint = new JTextField(10);
		txtPoint.setText("");
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
		MemberVo vo = getViewData();

		switch (e.getActionCommand()) {
		case "수정":
			vo = getViewData();
			dao.updateMember(number, txtName.getText(), txtCurrent.getText());
			clearViewData();
			MemberPanel.jTableRefresh();
			this.dispose();
			break;
		case "삭제":
			dao.deleteMember(txtCurrent.getText());
			vo.setPhoneNumber(txtCurrent.getText());
			if (txtCurrent.getText() == null)
				System.out.println("삭제 안됨");
			clearViewData();
			MemberPanel.jTableRefresh();
			this.dispose();
			break;
		case "취소":
			clearViewData();
			break;
		}
	}

	// txt -> vo
	private MemberVo getViewData() {
		String memberName = txtName.getText();
		String phoneNumber = txtCurrent.getText();
		int Point = 0;
		MemberVo vo = new MemberVo(memberName, phoneNumber, Point);
		return vo;
	}

	// vo -> txt
	private void setViewData(MemberVo vo) {
		txtName.setText(vo.getMemberName());
		txtCurrent.setText(vo.getPhoneNumber());
		txtPoint.setText(Integer.toString(vo.getPoint()));
	}

	// 화면 지움
	private void clearViewData() {
		txtName.setText("");
		txtCurrent.setText("");
		txtPoint.setText("");
		txtCurrent.setFocusable(true);
	}

}