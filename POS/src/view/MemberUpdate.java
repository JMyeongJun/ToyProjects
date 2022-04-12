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
	
	String      id          = null;
	String      number      = null;
	String      point       = null;
	
	JFrame     jFrame;
	JPanel     pan1;
	JButton    btnUpdate, btnCancel, btnDelete, btnFind;
	JLabel     lblName, lblTel, lblCurrent, lblPoint;
	JTextField txtName, txtTel, txtCurrent, txtPoint;
	
	GridBagLayout       gbl;
	GridBagConstraints  gbc;
	
	//생성자
	public MemberUpdate() {
		initComponent();
	}
	
	public MemberUpdate( MemberPanel memberPanel ) {
		this();
		this.memberPanel = memberPanel;
	}
	
	public MemberUpdate(String id, String number, String point, MemberPanel memberPanel) {
		this(memberPanel);
		this.id = id;
		this.number = number;
		this.point  = point;
//		btnFind_click();
		
		txtName.setText( id );
		txtCurrent.setText(number);
		txtPoint.setText(point);

	}



	private void initComponent() {
		
		gbl = new GridBagLayout();
		this.setLayout( gbl );
		
		gbc         = new GridBagConstraints();
		gbc.fill    = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		// 여백을 분배하는 변수, 모두 0이면 가운데로 모임
		
		// 패널
		
		pan1   = new JPanel();
		gbAdd( pan1, 0,0,1,1);
		pan1.setBackground(Color.WHITE);
		
		setSize( 350, 500 );
		setLocation(300, 100);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		// 라벨
		pan1.setLayout(new GridBagLayout());
		
		lblName = new JLabel("이름");
		txtName = new JTextField(10);
		txtName.setText("");
		gbAdd( lblName, 0, 0, 1, 1);
		gbAdd( txtName, 0, 1, 1, 1 );

		lblCurrent = new JLabel("전화번호");
		txtCurrent  = new JTextField(10);
		txtCurrent.setText("");
		gbAdd(lblCurrent,  0, 2, 1, 1);
		gbAdd(txtCurrent,  0, 3, 1, 1 );

		lblPoint = new JLabel("보유 포인트");
		txtPoint  = new JTextField(10);
		txtPoint.setText("");
		txtPoint.setEnabled(false);
		gbAdd(lblPoint, 0, 4, 1, 1);
		gbAdd(txtPoint, 0, 5, 1, 1 );
		
		// 버튼
		JPanel pButton = new JPanel();
		btnCancel = new JButton("취소");
		btnUpdate = new JButton("수정");
		btnDelete = new JButton("삭제");
		pan1.add( btnCancel );
		pan1.add( btnUpdate );
		pan1.add( btnDelete );
		gbAdd( pan1, 0,6,1,1);
		
		btnCancel.addActionListener(this);
		btnUpdate.addActionListener(this);
		btnDelete.addActionListener(this);
	
		setVisible(true);
	}

	private void gbAdd(JComponent c, int x, int y, int w, int h) {
		gbc.gridx      = x;
		gbc.gridy      = y;
		gbc.gridwidth  = w;
		gbc.gridheight = h;
		gbl.setConstraints( c, gbc );
		gbc.insets     = new Insets(2, 2, 2, 2 );
		this.add( c, gbc );
		
		
	}

	public static void main(String[] args) {
		new MemberUpdate();

	}
	
	// 버튼 event 연결
	@Override
	public void actionPerformed(ActionEvent e) {
		
		MemberDao dao = new MemberDao();
		MemberVo  vo  = getViewData();
		
		switch( e.getActionCommand() ) {
		
		case "수정":
			vo = getViewData();
			dao.updateMember( number, txtName.getText(), txtCurrent.getText() );
			clearViewData();
			MemberPanel.jTableRefresh();
			this.dispose();
			break;
		
		case "삭제":
			dao.deleteMember( txtCurrent.getText());
			vo.setPhoneNumber( txtCurrent.getText() );
			if( txtCurrent.getText() == null)
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
		String memberName  = txtName.getText();
		String phoneNumber = txtCurrent.getText();
		int    Point       = 0;
		MemberVo vo = new MemberVo(memberName, phoneNumber, Point);
		return vo;
	}
	
	// vo -> txt
	private void setViewData( MemberVo vo ) {
		
		txtName.setText( vo.getMemberName() );
		txtCurrent.setText( vo.getPhoneNumber() );
		txtPoint.setText( Integer.toString(vo.getPoint()));
		
	}
	// 화면 지움
	private void clearViewData() {
		txtName.setText("");
		txtCurrent.setText("");
		txtPoint.setText("");
		txtCurrent.setFocusable(true);
	}
	
//	private void btnFind_click() {
//		
//		String phonenumber = txtCurrent.getText().trim();
//		MemberDao dao = new MemberDao();
//		Vector<MemberVo> vo = dao.getMemberList();
//		
//		
//	}
	
}