package view;

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
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MemberUpdate extends JFrame implements ActionListener {
	
	// Field
	MemberPanel memberPanel = null;
	
	String      id          = null;
	
	JFrame     jFrame;
	JPanel     pan1;
	JButton    btnUpdate, btnCancel;
	JLabel     lblName, lblTel, lblPoint;
	JTextField txtName, txtTel, txtPoint;
	
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
	
	public MemberUpdate(String id, MemberPanel memberPanel) {
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
		txtName.setText("이름");
		gbAdd( lblName, 0, 0, 1, 1);
		gbAdd( txtName, 0, 1, 1, 1 );

//		pan1.add(lblName);
//		pan1.add(txtName);
		
		
		lblTel = new JLabel("전화번호 입력");
		txtTel  = new JTextField(10);
		txtTel.setText("전화번호");
		gbAdd(lblTel,  0, 2, 1, 1);
		gbAdd(txtTel, 0, 3, 1, 1 );
		
//		pan1.add(lblTel);
//		pan1.add(txtTel);

		lblPoint = new JLabel("포인트");
		txtPoint  = new JTextField(10);
		txtPoint.setText("보유 포인트 출력");
		gbAdd(lblPoint, 0, 4, 1, 1);
		gbAdd(txtPoint, 0, 5, 1, 1 );
		
//		pan1.add(lblPoint);
//		pan1.add(txtPoint);

		// 버튼
		JPanel pButton = new JPanel();
		btnUpdate = new JButton("취소");
		btnCancel = new JButton("수정");
		pan1.add( btnUpdate );
		pan1.add( btnCancel );
		gbAdd( pan1, 0,6,1,1);
		
		btnUpdate.addActionListener( this );
		btnCancel.addActionListener( this );

		
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
