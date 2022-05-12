package view.member;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.MemberDao;

public class MemberInsert extends JFrame implements ActionListener {
	// Field
	JButton btnInsert, btnCancel;
	JLabel lblName, lblPhoneNumber;
	JTextField txtName, txtPhoneNumber;

	GridBagLayout gbl;
	GridBagConstraints gbc;

	// 생성자
	public MemberInsert() {
		initComponent();
	}

	private void initComponent() {

		gbl = new GridBagLayout();
		this.setLayout(gbl);

		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;

		this.getContentPane().setBackground(Color.white);

		setSize(350, 500);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		// 라벨
		lblName = new JLabel("이름 입력");
		txtName = new JTextField(10);
		gbAdd(lblName, 0, 0, 1, 1);
		gbAdd(txtName, 0, 1, 3, 1);

		lblPhoneNumber = new JLabel("전화번호 입력");
		txtPhoneNumber = new JTextField(10);
		gbAdd(lblPhoneNumber, 0, 2, 1, 1);
		gbAdd(txtPhoneNumber, 0, 3, 3, 1);

		// 버튼
		btnCancel = new JButton("취소");
		btnInsert = new JButton("등록");

		gbAdd(btnCancel, 0, 4, 1, 1);
		gbAdd(btnInsert, 1, 4, 1, 1);

		btnCancel.addActionListener(this);
		btnInsert.addActionListener(this);

		setVisible(true);
	}

	// GridBagConstraints 설정 함수
	private void gbAdd(JComponent c, int x, int y, int w, int h) {
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = w;
		gbc.gridheight = h;
		gbl.setConstraints(c, gbc);
		gbc.insets = new Insets(1, 1, 1, 1);
		this.add(c, gbc);
	}

//----------------------------------------------------------------------------
	// 버튼 기능
	@Override
	public void actionPerformed(ActionEvent e) {
		MemberDao dao = new MemberDao();

		switch (e.getActionCommand()) {
		case "등록":
			if(txtPhoneNumber.getText().length() == 0 || txtName.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "이름과 전화번호를 입력해주세요.", "확인", JOptionPane.WARNING_MESSAGE);
				return;
			}
			dao.insertMember(txtName.getText(), txtPhoneNumber.getText());
			MemberPanel.tableRefresh(MemberPanel.table);
			dispose();
			break;

		case "취소":
			dispose();
			break;
		}
	}
}
