package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MemberPanel extends BasicPanel implements ActionListener {
	
	MemberInsert proc = null;
	MemberUpdate proc2 = null;
	
	JFrame      jFrame;
	JButton     btnInsert, btnUpdate;
	JScrollPane pane;
	JTable      jTable;
	JLabel      lbl;


	
	public MemberPanel() {
		initComponent();
		
	}

	private void initComponent() {
			
		subTitle.setText("회원관리");
		
		side.setLayout(null);
		// 버튼 등록
		btnInsert = new JButton("회원등록");
		btnUpdate = new JButton("회원수정");

		btnInsert.setBounds( 100, 450, 100, 30);
		btnUpdate.setBounds( 100, 550, 100, 30);
		
		side.add( btnInsert );
		side.add( btnUpdate);
		
		btnInsert.addActionListener( this );
		btnUpdate.addActionListener( this );
		
		// 라벨 추가
		
		top_bottom.setLayout(null);
		
		lbl = new JLabel("회원 목록");
		lbl.setBounds( 10, -10, 150, 100);
		lbl.setFont(new Font("Serif", Font.BOLD, 10));
		lbl.setFont(lbl.getFont().deriveFont(25.0f));
		top_bottom.add(lbl);
		
		// JTable 추가
		
		jTable   =   new JTable();
		
		jTable.setModel(
				new DefaultTableModel( getDataList(), getColumnList() ) {
					public boolean isCellEditable( int row, int column) {
						return false;
						
					}
				}
				);
		pane = new JScrollPane( jTable );
		bottom.setLayout(new BorderLayout());
		bottom.add( pane,BorderLayout.CENTER );
		
	}
	
	
	private Vector<? extends Vector> getDataList() {
		
		return null;
	}

	private Vector<?> getColumnList() {
		Vector cols = new Vector();
		cols.add("이름");
		cols.add("휴대폰 번호");
		cols.add("부유 포인트");
		return cols;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton) e.getSource();
		if( e.getSource() == btnInsert ) {
			System.out.println("회원등록");
			if( proc != null )
				proc.dispose();
			proc = new MemberInsert( this );	
		
		}
		if(e.getSource() == btnUpdate) {
			System.out.println("회원수정");
			if( proc2 != null )
				proc2.dispose();
			proc2 = new MemberUpdate( this );
		}
		
	}


}