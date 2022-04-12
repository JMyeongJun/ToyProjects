package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import model.MemberDao;
import model.MemberVo;

public class MemberInsert extends JFrame implements ActionListener {
	
	// Field
	MemberPanel memberPanel = null;
	String      id          = null;
	
	JFrame     jFrame;
	JPanel     pan1;
	JButton    btnInsert, btnCancel;
	JLabel     lblName, lblTel;
	JTextField txtName, txtTel;
	JTable     jTable;
	
	GridBagLayout        gbl;
	GridBagConstraints   gbc;
	
	
	//생성자
	public MemberInsert() {
		initComponent();
	}
	
	public MemberInsert( MemberPanel memberPanel ) {
		this();
		this.memberPanel = memberPanel;
	}
	
	public MemberInsert(String id, MemberPanel memberPanel) {
		this(memberPanel);
		this.id = id;
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
		
		lblName = new JLabel("이름 입력");
		txtName = new JTextField(10);
		txtName.getText();
		gbAdd( lblName, 0, 0, 1, 1);
		gbAdd( txtName, 0, 1, 1, 1 );

		lblTel = new JLabel("전화번호 입력");
		txtTel  = new JTextField(10);
		txtTel.getText();
		gbAdd(lblTel,  0, 2, 1, 1);
		gbAdd(txtTel, 0, 3, 1, 1 );

		// 버튼
		btnCancel = new JButton("취소");
		btnInsert = new JButton("등록");
		pan1.add( btnCancel );
		pan1.add( btnInsert );
		gbAdd( pan1, 0,4,4,1);
		
		btnCancel.addActionListener(this);
		btnInsert.addActionListener(this);

		setVisible(true);
	}
	
	

	// GridBagConstraints 설정 함수
	private void gbAdd(JComponent c, int x, int y, int w, int h) {
		gbc.gridx      = x;
		gbc.gridy      = y;
		gbc.gridwidth  = w;
		gbc.gridheight = h;
		gbl.setConstraints( c, gbc );
		gbc.insets     = new Insets(2, 2, 2, 2 );
		this.add( c, gbc );
	}
//----------------------------------------------------------------------------

	public static void main(String[] args) {
		new MemberInsert();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		MemberDao dao = new MemberDao();
		MemberVo  vo  = getViewData();
		
		switch( e.getActionCommand()) {
		case "등록":
			dao.insertMember( txtName.getText(), txtTel.getText() );
			MemberPanel.jTableRefresh();
			this.dispose();
			break;
			
		case "취소":
			clearviewData();
			break;
		}
		
	}

	private void clearviewData() {
		txtName.setText("");
		txtTel.setText("");
		txtName.setFocusable( true );	
	}

	private MemberVo getViewData() {
		
		return null;
	}

}
