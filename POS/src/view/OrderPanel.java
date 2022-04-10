package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/* 남은것

 - 테이블에서 항목선택후 삭제 - + 누르면 이벤트 발생
 - 결제버튼 누르면 결제창으로 넘어감
 - DB연동
 - 전체적인 디자인  
 - 중복되는 코드들 정리, 줄정리 
 - 주문내역 라벨 이나 밑의 버튼들처럼 항성 보여지는 것들은 왠만하면 전역변수로 지정한다.  -> order말고 다른거에서도 써야함*/

public class OrderPanel extends BasicPanel 
	implements  ActionListener, MouseListener{

// Field
	private GridBagLayout      gb;
	private GridBagConstraints gbc;
	
	public JLabel lblOrder;
	public JButton btn_DelMenu;
	public JButton btn_MnsMenu;
	public JButton btn_PlsMenu;
	public JButton btn_Payment;
	public JTable  tblOrder;
	
// 생성자
	public OrderPanel() {
		
		initComponent();
	}
	
	private void initComponent() {
		
//레이아웃 설정-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------		
//top_bottom 설정
		gb = new GridBagLayout();
		gbc = new GridBagConstraints();
		
		top_bottom.setLayout(gb);
		gbc.fill = GridBagConstraints.BOTH;
	    gbc.weightx = 1.0;
		gbc.weighty = 1.0; 
		
		JButton btn_Cate1 = new JButton("카테고리1");
		JButton btn_Cate2 = new JButton("카테고리2");
		JButton btn_Cate3 = new JButton("카테고리3");
		
		gbc.insets = new Insets(5, 5, 5, 10);
		gbAdd(btn_Cate1,0,0,1,1);
		gbAdd(btn_Cate2,1,0,1,1);
		gbAdd(btn_Cate3,2,0,1,1);
		
		top_bottom.add(btn_Cate1);
		top_bottom.add(btn_Cate2);
		top_bottom.add(btn_Cate3);
		
// bottom 설정 - 카테고리 1
		
		JPanel bottom_Cate1 = new JPanel();
		bottom_Cate1.setLayout(gb);
		
		JButton btn_Cate1_Menu1 = new JButton("카테1 메뉴1");
		JButton btn_Cate1_Menu2 = new JButton("카테1 메뉴2");
		JButton btn_Cate1_Menu3 = new JButton("카테1 메뉴3");
		JButton btn_Cate1_Menu4 = new JButton("카테1 메뉴4");
		JButton btn_Cate1_Menu5 = new JButton("카테1 메뉴5");
		JButton btn_Cate1_Menu6 = new JButton("카테1 메뉴6");
		JButton btn_Cate1_Menu7 = new JButton("카테1 메뉴7");
		JButton btn_Cate1_Menu8 = new JButton("카테1 메뉴8");
		JButton btn_Cate1_Menu9 = new JButton("<html><body><center>카테1 메뉴9<br> <br>2500</center></body></html>");   // 자바 코드로는 줄바꿈이 안된다고 한다

		gbAdd(btn_Cate1_Menu1,0,0,1,1);
		gbAdd(btn_Cate1_Menu2,1,0,1,1);
		gbAdd(btn_Cate1_Menu3,2,0,1,1);
		gbAdd(btn_Cate1_Menu4,3,0,1,1);
		gbAdd(btn_Cate1_Menu5,0,1,1,1);
		gbAdd(btn_Cate1_Menu6,1,1,1,1);
		gbAdd(btn_Cate1_Menu7,2,1,1,1);
		gbAdd(btn_Cate1_Menu8,3,1,1,1);
		gbAdd(btn_Cate1_Menu9,0,2,1,1);

		bottom_Cate1.add(btn_Cate1_Menu1);
		bottom_Cate1.add(btn_Cate1_Menu2);
		bottom_Cate1.add(btn_Cate1_Menu3);
		bottom_Cate1.add(btn_Cate1_Menu4);
		bottom_Cate1.add(btn_Cate1_Menu5);
		bottom_Cate1.add(btn_Cate1_Menu6);
		bottom_Cate1.add(btn_Cate1_Menu7);
		bottom_Cate1.add(btn_Cate1_Menu8);
		bottom_Cate1.add(btn_Cate1_Menu9); 

		bottom.setLayout(new GridLayout());																		// 이걸 그리드백으로 할거였으면 gbAdd(bottom_cate1,0,0,1,1)도 해줬어야 했다.
		bottom.add(bottom_Cate1);
		
// bottom 설정 - 카테고리 2		
		
		JPanel bottom_Cate2 = new JPanel();
		bottom_Cate2.setLayout(gb);

		JButton btn_Cate2_Menu1 = new JButton("카테2 메뉴1");  
		JButton btn_Cate2_Menu2 = new JButton("카테2 메뉴2");
		JButton btn_Cate2_Menu3 = new JButton("카테2 메뉴3");
		JButton btn_Cate2_Menu4 = new JButton("카테2 메뉴4");
		JButton btn_Cate2_Menu5 = new JButton("카테2 메뉴5");
		JButton btn_Cate2_Menu6 = new JButton("카테2 메뉴6");
		JButton btn_Cate2_Menu7 = new JButton("카테2 메뉴7");
		JButton btn_Cate2_Menu8 = new JButton("카테2 메뉴8");
		JButton btn_Cate2_Menu9 = new JButton("<html><body><center>카테2 메뉴9<br> <br>2500</center></body></html>");  

		gbAdd(btn_Cate2_Menu1,0,0,1,1);
		gbAdd(btn_Cate2_Menu2,1,0,1,1);
		gbAdd(btn_Cate2_Menu3,2,0,1,1);
		gbAdd(btn_Cate2_Menu4,3,0,1,1);
		gbAdd(btn_Cate2_Menu5,0,1,1,1);
		gbAdd(btn_Cate2_Menu6,1,1,1,1);
		gbAdd(btn_Cate2_Menu7,2,1,1,1);
		gbAdd(btn_Cate2_Menu8,3,1,1,1);
		gbAdd(btn_Cate2_Menu9,0,2,1,1);

		bottom_Cate2.add(btn_Cate2_Menu1);
		bottom_Cate2.add(btn_Cate2_Menu2);
		bottom_Cate2.add(btn_Cate2_Menu3);
		bottom_Cate2.add(btn_Cate2_Menu4);
		bottom_Cate2.add(btn_Cate2_Menu5);
		bottom_Cate2.add(btn_Cate2_Menu6);
		bottom_Cate2.add(btn_Cate2_Menu7);
		bottom_Cate2.add(btn_Cate2_Menu8);
		bottom_Cate2.add(btn_Cate2_Menu9); 
		
// bottom 설정 - 카테고리 3		
		
		JPanel bottom_Cate3 = new JPanel();
		bottom_Cate3.setLayout(gb);

		JButton btn_Cate3_Menu1 = new JButton("카테3 메뉴1");  
		JButton btn_Cate3_Menu2 = new JButton("카테3 메뉴2");
		JButton btn_Cate3_Menu3 = new JButton("카테3 메뉴3");
		JButton btn_Cate3_Menu4 = new JButton("카테3 메뉴4");
		JButton btn_Cate3_Menu5 = new JButton("카테3 메뉴5");
		JButton btn_Cate3_Menu6 = new JButton("카테3 메뉴6");
		JButton btn_Cate3_Menu7 = new JButton("카테3 메뉴7");
		JButton btn_Cate3_Menu8 = new JButton("카테3 메뉴8");
		JButton btn_Cate3_Menu9 = new JButton("<html><body><center>카테2 메뉴9<br> <br>2500</center></body></html>");  

		gbAdd(btn_Cate3_Menu1,0,0,1,1);
		gbAdd(btn_Cate3_Menu2,1,0,1,1);
		gbAdd(btn_Cate3_Menu3,2,0,1,1);
		gbAdd(btn_Cate3_Menu4,3,0,1,1);
		gbAdd(btn_Cate3_Menu5,0,1,1,1);
		gbAdd(btn_Cate3_Menu6,1,1,1,1);
		gbAdd(btn_Cate3_Menu7,2,1,1,1);
		gbAdd(btn_Cate3_Menu8,3,1,1,1);
		gbAdd(btn_Cate3_Menu9,0,2,1,1);

		bottom_Cate3.add(btn_Cate3_Menu1);
		bottom_Cate3.add(btn_Cate3_Menu2);
		bottom_Cate3.add(btn_Cate3_Menu3);
		bottom_Cate3.add(btn_Cate3_Menu4);
		bottom_Cate3.add(btn_Cate3_Menu5);
		bottom_Cate3.add(btn_Cate3_Menu6);
		bottom_Cate3.add(btn_Cate3_Menu7);
		bottom_Cate3.add(btn_Cate3_Menu8);
		bottom_Cate3.add(btn_Cate3_Menu9); 
		
		
// side 설정
		
		side.setLayout(gb);
		
		//side 테이블 설정
		
		String [] tbl_Column = {"메뉴","수량","가격"};
		tblOrder = new JTable(
				new DefaultTableModel(null, tbl_Column));							 

		DefaultTableModel model  = (DefaultTableModel) tblOrder.getModel();			 // 테이블 모델을 얻어온다
		JScrollPane       scroll = new JScrollPane(tblOrder);                        // 스크롤도 컨테이너다 (스크롤 안에 테이블을 넣은거임) 

		DefaultTableCellRenderer center = new DefaultTableCellRenderer();			 // 가운데정렬
		center.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel center2 = tblOrder.getColumnModel();
		for (int i = 0; i < center2.getColumnCount(); i++) {
			center2.getColumn(i).setCellRenderer(center);
		}

		tblOrder.getParent().setBackground(Color.WHITE);							 // 테이블 색
		tblOrder.setShowVerticalLines(false);										 // 테이블 선 지우기
		tblOrder.setShowHorizontalLines(false);
		
		// side 나머지 설정 
		lblOrder    = new JLabel("주문내역");
		btn_DelMenu = new JButton("삭제");
		btn_MnsMenu = new JButton("-");
		btn_PlsMenu = new JButton("+");
		btn_Payment = new JButton("결제");
		
		gbc.weighty = 1.1;
		gbAdd(lblOrder   ,0,0,3,1);
		gbc.weighty = 6;
		gbAdd(scroll,0,1,3,1);
		gbc.weighty = 0.5;
		gbAdd(btn_DelMenu,0,2,1,1);
		gbAdd(btn_MnsMenu,1,2,1,1);
		gbAdd(btn_PlsMenu,2,2,1,1);
		gbc.insets = new Insets(0,0,0,0);
		gbAdd(btn_Payment,0,3,3,1);
		
		side.add(lblOrder);
		side.add(scroll);
		side.add(btn_DelMenu);
		side.add(btn_MnsMenu);
		side.add(btn_PlsMenu);
		side.add(btn_Payment);
		
// 색깔, 폰트 설정
		
		subTitle.setFont(new Font("본고딕", Font.BOLD, 50 ));
		lblOrder.setFont(new Font("본고딕", Font.BOLD, 40 ));
		
		btnPrev.setBackground(new Color(230,230,230));
		Color btnCate_col  = new Color(52,152,219);
		btn_Cate1.setBackground(btnCate_col);
		btn_Cate2.setBackground(btnCate_col);
		btn_Cate3.setBackground(btnCate_col);
		btn_Cate1.setForeground(Color.white);
		btn_Cate2.setForeground(Color.white);
		btn_Cate3.setForeground(Color.white);
		
		Color btn_menu_col = new Color(94,94,94);
		btn_Cate1_Menu1.setBackground(btn_menu_col);
		btn_Cate1_Menu2.setBackground(btn_menu_col);
		btn_Cate1_Menu3.setBackground(btn_menu_col);
		btn_Cate1_Menu4.setBackground(btn_menu_col);
		btn_Cate1_Menu5.setBackground(btn_menu_col);
		btn_Cate1_Menu6.setBackground(btn_menu_col);
		btn_Cate1_Menu7.setBackground(btn_menu_col);
		btn_Cate1_Menu8.setBackground(btn_menu_col);
		btn_Cate1_Menu9.setBackground(btn_menu_col);

		
		Color btn_side_col = new Color(229,239,204);
		btn_DelMenu.setBackground(btn_side_col);
		btn_MnsMenu.setBackground(btn_side_col);
		btn_PlsMenu.setBackground(btn_side_col);
		btn_Payment.setBackground(btn_side_col);
		
// 패널전환

		btn_Cate1.addActionListener( ( e ) -> {

			bottom.removeAll();
			bottom.add(bottom_Cate1);
			bottom.revalidate();													// 자바에서 동적 변화 후에는 revalidate(재확인) 와 repaint가 항상 따라다닌다 (잔상이 보여서 새로그려야함)
			bottom.repaint();
		});

		btn_Cate2.addActionListener( ( e ) -> {
			bottom.removeAll();
			bottom.add(bottom_Cate2);
			bottom.revalidate();
			bottom.repaint();
		});

		btn_Cate3.addActionListener( ( e ) -> {
			bottom.removeAll();
			bottom.add(bottom_Cate3);
			bottom.revalidate();
			bottom.repaint();
		});

//기능연결 -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		
		tblOrder.addMouseListener(this);
		btn_DelMenu.addActionListener(this);
		btn_MnsMenu.addActionListener(this);
		btn_PlsMenu.addActionListener(this);
		btn_Payment.addActionListener(this);

		
		
		
//임시용======================================================================================================================================================================================
		
		int menu1_Price = 2500;
		int menu2_Price = 3000;
		int menu3_Price = 2000;
		int menu4_Price = 2200;
		
//임시용======================================================================================================================================================================================	
// 기능 연결 -> 메뉴버튼 -> 비슷한 코드를 묶는 방법?
		
		btn_Cate1_Menu1.addActionListener( ( e ) -> {											

			String btn_MenuName = btn_Cate1_Menu1.getActionCommand();								// 메뉴1 버튼이름 가져오기
			
			if (model.getRowCount() == 0 ) {												// 테이블이 비어있으면 무조건 메뉴1을 추가함
				
				model.addRow(new Object[]{btn_MenuName, 1, menu1_Price});
			}else {

				int dupl = 0;
				for (int i = 0; i < model.getRowCount(); i++) {			 					
					if (tblOrder.getValueAt(i, 0).equals(btn_MenuName) ) {       			// 이미 메뉴1이 들어가있다면
						
						int pre_Quantity = (int) tblOrder.getValueAt(i, 1);					// 메뉴1의 수량을 가져와서
						tblOrder.setValueAt(pre_Quantity+1, i, 1);							// 기존 수량에 1을 더한다.
						int pre_Price = (int) tblOrder.getValueAt(i, 2);					// 가격도 같은방법
						tblOrder.setValueAt(pre_Price + menu1_Price, i, 2);
					} else  dupl++; 					 									// 메뉴 1이 없다면 dupl라는 변수에 1을 더한다
				}

				if (dupl == model.getRowCount()) {											// 메뉴1이 없다면 (열을 쭉 검색해서 한번이라도 메뉴1이 보였다면 dupl은 행개수 - 1이 되었을것임)
					
					model.addRow(new Object[]{btn_MenuName, 1, menu1_Price});						// 메뉴1을 추가한다
				}
			} 
		});
		
		btn_Cate1_Menu2.addActionListener( ( e ) -> {											

			String btn_MenuName = btn_Cate1_Menu2.getActionCommand();								// 메뉴1 버튼이름 가져오기
			
			if (model.getRowCount() == 0 ) {												// 테이블이 비어있으면 무조건 메뉴1을 추가함
				
				model.addRow(new Object[]{btn_MenuName, 1, menu2_Price});
			}else {

				int dupl = 0;
				for (int i = 0; i < model.getRowCount(); i++) {			 					
					if (tblOrder.getValueAt(i, 0).equals(btn_MenuName) ) {       			
						
						int pre_Quantity = (int) tblOrder.getValueAt(i, 1);							
						tblOrder.setValueAt(pre_Quantity+1, i, 1);			
						int pre_Price = (int) tblOrder.getValueAt(i, 2);					
						tblOrder.setValueAt(pre_Price + menu2_Price, i, 2);
					} else  dupl++; 					 									
				}

				if (dupl == model.getRowCount()) {											
					
					model.addRow(new Object[]{btn_MenuName, 1, menu2_Price});						
				}
			} 
		});
		
		btn_Cate1_Menu3.addActionListener( ( e ) -> {											

			String btn_MenuName = btn_Cate1_Menu3.getActionCommand();								
			
			if (model.getRowCount() == 0 ) {												
				
				model.addRow(new Object[]{btn_MenuName, 1, menu3_Price});
			}else {

				int dupl = 0;
				for (int i = 0; i < model.getRowCount(); i++) {			 					
					if (tblOrder.getValueAt(i, 0).equals(btn_MenuName) ) {       			
						
						int pre_Quantity = (int) tblOrder.getValueAt(i, 1);							
						tblOrder.setValueAt(pre_Quantity+1, i, 1);	
						int pre_Price = (int) tblOrder.getValueAt(i, 2);					
						tblOrder.setValueAt(pre_Price + menu3_Price, i, 2);
					} else  dupl++; 					 									
				}

				if (dupl == model.getRowCount()) {											
					
					model.addRow(new Object[]{btn_MenuName, 1, menu3_Price});						
				}
			} 
		});
		
		btn_Cate1_Menu4.addActionListener( ( e ) -> {											

			String btn_MenuName = btn_Cate1_Menu4.getActionCommand();								
			
			if (model.getRowCount() == 0 ) {												
				
				model.addRow(new Object[]{btn_MenuName, 1, menu4_Price});
			}else {

				int dupl = 0;
				for (int i = 0; i < model.getRowCount(); i++) {			 					
					if (tblOrder.getValueAt(i, 0).equals(btn_MenuName) ) {       			
						
						int pre_Quantity = (int) tblOrder.getValueAt(i, 1);							
						tblOrder.setValueAt(pre_Quantity+1, i, 1);			
						int pre_Price = (int) tblOrder.getValueAt(i, 2);					
						tblOrder.setValueAt(pre_Price + menu4_Price, i, 2);
					} else  dupl++; 					 									
				}

				if (dupl == model.getRowCount()) {											
					
					model.addRow(new Object[]{btn_MenuName, 1, menu4_Price});						
				}
			} 
		});
		


		
		

	}
	
	
	private void gbAdd(JComponent c, int x, int y, int w, int h) { 

		gbc.gridx = x; 
		gbc.gridy = y; 
		gbc.gridwidth = w;
		gbc.gridheight = h; 
		gb.setConstraints(c, gbc);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {

		switch (e.getActionCommand()) {
		
		case "삭제" : System.out.println("삭제누름");
					  click_DelMenu();
					  break;
					  
		case "+"    : System.out.println("+누름");	  break;
		case "-"    : System.out.println("-누름");	  break;	
		case "결제" : System.out.println("결제누름"); break;
		
		}
	} 

	private void click_DelMenu() {
		
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		int row = tblOrder.getSelectedRow();
		System.out.println(tblOrder.getModel().getValueAt(row, 1) + "개");
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
