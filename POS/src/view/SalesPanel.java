package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;


public class SalesPanel extends BasicPanel  {
	
	JPanel   topPane, underPane, rightPane;
	JButton  btnPrev, btnLookup, btnToExcel;	
	JLabel  lbl;
	
	GridBagLayout  gbl;
	GridBagConstraints gbc;
	private JTable  table;
	private Frame mainFrame;
	private Label  StatusLabel;
	
	
	
	public SalesPanel() {
	   initcomponents();
	   
		mainFrame = new Frame("매출 관리");
		
		StatusLabel = new Label();
		
		
	   
	    bottom.setForeground(Color.PINK);
	    bottom.setLayout(new BorderLayout(0, 0));
	      
		bottom.setForeground(Color.PINK);
		GridBagLayout gbl1 = new GridBagLayout();
		gbl1.columnWidths = new int[]{313, 0};
		gbl1.rowHeights = new int[]{237, 0};
		gbl1.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl1.rowWeights = new double[]{0.0, Double.MIN_VALUE};
	//	bottom.setLayout(gridBagLayout);
	      

	      
	      table = new JTable();
	      table.setForeground(Color.WHITE);
	      table.setBackground(Color.LIGHT_GRAY);
	      table.setFont(new Font("굴림", Font.PLAIN, 12));
	    //Scroll 나중에
	      JScrollPane scrollPane = new JScrollPane(table);
	      bottom.add(scrollPane);
	     
	      table.setModel(new DefaultTableModel(
	     new Object[][] {
   			  {null, null, null, null, null, null},
   			  {null, null, null, null, null, null},
   			  {null, null, null, null, null, null},
			  {null, null, null, null, null, null},
   			  {null, null, null, null, null, null},   },
	     new String[] {  "주문번호", "주문일자", "결제수단", "결제금액", "포인트사용", "매출" } ));
	      setVisible(true);
	      table.getColumnModel().getColumn(2).setMinWidth(13);
	      btnPrev.setHorizontalAlignment(SwingConstants.TRAILING);
	      subTitle.setFont(new Font("굴림", Font.PLAIN, 24));
	      
	      GridBagLayout gbl2 = new GridBagLayout();
	      gbl2.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
	      gbl2.rowHeights = new int[]{0, 0};
	      gbl2.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
	      gbl2.rowWeights = new double[]{0.0, Double.MIN_VALUE};
			top_bottom.setLayout(gbl2);
			
		    LocalDate date = LocalDate.now();  //확인용
		    
		    JTextField tf1 = new JTextField(10);
		    tf1.setText(" "+date);
		    GridBagConstraints gbc_tf1 = new GridBagConstraints();  
			gbc_tf1.insets = new Insets(0, 0, 0, 7);
			gbc_tf1.gridx = 1;
			gbc_tf1.gridy = 1;
			top_bottom.add(tf1, gbc_tf1);
			
			
		    JTextField tf2 = new JTextField(10);
		    tf2.setText(" "+date);
		    GridBagConstraints gbc_tf2 = new GridBagConstraints();  
			gbc_tf2.insets = new Insets(0, 0, 0, 7);
			gbc_tf2.gridx = 2;
			gbc_tf2.gridy = 1;
			top_bottom.add(tf2, gbc_tf2);
		    
		    
    
			
			
			JButton btn1 = new JButton("조회");
			GridBagConstraints gbc_btn1 = new GridBagConstraints();
			gbc_btn1.insets = new Insets(0, 0, 0, 7);
			gbc_btn1.gridx = 5;
			gbc_btn1.gridy = 1;
			top_bottom.add(btn1, gbc_btn1);
			
			btn1.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String data = tf1.getText();
					data       += tf2.getText();
					StatusLabel.setText(data);
				}
				
			} );

			
			
			JButton btn2 = new JButton("엑셀 저장");
			GridBagConstraints gbc_btn2 = new GridBagConstraints();
			gbc_btn2.gridx = 7;
			gbc_btn2.gridy = 1;
			top_bottom.add(btn2, gbc_btn2);
			
			JLabel lbl = new JLabel("주문 내역");
			GridBagConstraints gbc_lbl = new GridBagConstraints();
			gbc_lbl.fill = GridBagConstraints.VERTICAL;
			gbc_lbl.gridx = 1;
			gbc_lbl.gridy = 1;
			side.add(lbl, gbc_lbl);
			
			
	      
	}      
	
	


	
	public void initcomponents() {
	subTitle.setText("매출조회");
	subTitle.setFont(new Font("굴림", Font.PLAIN, 24));
	subTitle.setHorizontalAlignment(SwingConstants.CENTER);
	top_top.setBackground(Color.WHITE);
	top_top.add(subTitle, BorderLayout.CENTER);
	
	
	
	
//	JPanel.add(bottom, BorderLayout,PAGE_END);
	  	
	btnPrev      =  new  JButton("이전");
	btnLookup    =  new  JButton("조회");
	btnToExcel   =  new  JButton("엑셀 저장");
	lbl          =  new  JLabel("매출 조회");
		

		
		
	}
	

	
/*
	public static void main(String[] args) {
		
	//	Dimension  dim = new Dimension(100, 100);
		
	Date  date  = new Date();
		
	SimpleDateFormat  fmt = new SimpleDateFormat("yyyy-MM-dd");
	
	System.out.println(fmt.format(date));
	

		

	}
	
*/

}

	

