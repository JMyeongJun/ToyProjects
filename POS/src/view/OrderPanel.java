package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

/* 남은것

 - 카테고리 클릭시 패널전환
 - 테이블에서 항목선택후 삭제 - + 누르면 이벤트 발생
 - 결제버튼 누르면 결제창으로 넘어감
 - DB연동
 - 전체적인 디자인  
 - 중복되는 코드들 정리, 줄정리 */

public class OrderPanel extends BasicPanel {

   GridBagLayout      gb;
   GridBagConstraints gbc;
   
   public OrderPanel() {
      
      initComponent();
   }
   
   private void initComponent() {
      
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
      
//실험용------------------------------------------------------------------------------------------------------------------------------------------------------------------------
      
      
      
      

      
      
//실험용------------------------------------------------------------------------------------------------------------------------------------------------------------------------
      
      JPanel bottom_Cate1 = new JPanel();
      bottom_Cate1.setBackground(new Color(255, 255, 255));
      bottom_Cate1.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 15));
      
      
      JButton btn_Menu1 = new JButton("카테1 메뉴1");  
      JButton btn_Menu2 = new JButton("카테1 메뉴2");
      JButton btn_Menu3 = new JButton("카테1 메뉴3");
      JButton btn_Menu4 = new JButton("카테1 메뉴4");
      JButton btn_Menu5 = new JButton("카테1 메뉴5");
      JButton btn_Menu6 = new JButton("카테1 메뉴6");
      JButton btn_Menu7 = new JButton("카테1 메뉴7");
      JButton btn_Menu8 = new JButton("카테1 메뉴8");
      JButton btn_Menu9 = new JButton("<html><body><center>카테1 메뉴9<br> <br>2500</center></body></html>");   // 자바 코드로는 줄바꿈이 안된다고 한다
      
      btn_Menu1.setPreferredSize(new Dimension(150,150));
      btn_Menu2.setPreferredSize(new Dimension(150,150));
      btn_Menu3.setPreferredSize(new Dimension(150,150));
      btn_Menu4.setPreferredSize(new Dimension(150,150));
      btn_Menu5.setPreferredSize(new Dimension(150,150));
      btn_Menu6.setPreferredSize(new Dimension(150,150));
      btn_Menu7.setPreferredSize(new Dimension(150,150));
      
      gbAdd(btn_Menu1,0,0,1,1);
      gbAdd(btn_Menu2,1,0,1,1);
      gbAdd(btn_Menu3,2,0,1,1);
      gbAdd(btn_Menu4,3,0,1,1);
      gbAdd(btn_Menu5,0,1,1,1);
      gbAdd(btn_Menu6,1,1,1,1);
      gbAdd(btn_Menu7,2,1,1,1);
      gbAdd(btn_Menu8,3,1,1,1);
      gbAdd(btn_Menu9,0,2,1,1);
      
      bottom_Cate1.add(btn_Menu1);
      bottom_Cate1.add(btn_Menu2);
      bottom_Cate1.add(btn_Menu3);
      bottom_Cate1.add(btn_Menu4);
      bottom_Cate1.add(btn_Menu5);
      bottom_Cate1.add(btn_Menu6);
      bottom_Cate1.add(btn_Menu7);
//      bottom_Cate1.add(btn_Menu8);
//      bottom_Cate1.add(btn_Menu9); 
      
      bottom.setLayout(new GridLayout());
      bottom.add(bottom_Cate1);
      

      
// bottom 설정 - 카테고리 2      
      


      
      
      
// side 설정
      
      side.setLayout(gb);
      
      JLabel  lblOrder    = new JLabel("주문내역");
      JButton btn_DelMenu = new JButton("삭제");
      JButton btn_MnsMenu = new JButton("-");
      JButton btn_PlsMenu = new JButton("+");
      JButton btn_Payment = new JButton("결제");
      
      String []   tbl_Column = {"메뉴","수량","가격"};
      
      JTable      tblOrder  = new JTable(
            new DefaultTableModel(null, tbl_Column));
      
      DefaultTableModel model = (DefaultTableModel) tblOrder.getModel();          // 테이블 모델을 얻어온다
      JScrollPane scroll    = new JScrollPane(tblOrder);                           // 스크롤도 컨테이너다 

      
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
      
// 색깔, 글자
      
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
      btn_Menu1.setBackground(btn_menu_col);
      btn_Menu2.setBackground(btn_menu_col);
      btn_Menu3.setBackground(btn_menu_col);
      btn_Menu4.setBackground(btn_menu_col);
      btn_Menu5.setBackground(btn_menu_col);
      btn_Menu6.setBackground(btn_menu_col);
      btn_Menu7.setBackground(btn_menu_col);
      btn_Menu8.setBackground(btn_menu_col);
      btn_Menu9.setBackground(btn_menu_col);

      
      Color btn_side_col = new Color(229,239,204);
      btn_DelMenu.setBackground(btn_side_col);
      btn_MnsMenu.setBackground(btn_side_col);
      btn_PlsMenu.setBackground(btn_side_col);
      btn_Payment.setBackground(btn_side_col);
      
//임시용======================================================================================================================================================================================
      
      int menu1_Price = 2500;
      int menu2_Price = 3000;
      int menu3_Price = 2000;
      int menu4_Price = 2200;
      
//임시용======================================================================================================================================================================================   
// 기능 연결 -> 메뉴버튼 -> 비슷한 코드를 묶는 방법?
      
      btn_Menu1.addActionListener( ( e ) -> {                                 

         String btn_MenuName = btn_Menu1.getActionCommand();                        // 메뉴1 버튼이름 가져오기
         
         if (model.getRowCount() == 0 ) {                                    // 테이블이 비어있으면 무조건 메뉴1을 추가함
            
            model.addRow(new Object[]{btn_MenuName, 1, menu1_Price});
         }else {

            int dupl = 0;
            for (int i = 0; i < model.getRowCount(); i++) {                         
               if (tblOrder.getValueAt(i, 0).equals(btn_MenuName) ) {                // 이미 메뉴1이 들어가있다면
                  
                  int pre_Quantity = (int) tblOrder.getValueAt(i, 1);               // 메뉴1의 수량을 가져와서
                  tblOrder.setValueAt(pre_Quantity+1, i, 1);                     // 기존 수량에 1을 더한다.
                  int pre_Price = (int) tblOrder.getValueAt(i, 2);               // 가격도 같은방법
                  tblOrder.setValueAt(pre_Price + menu1_Price, i, 2);
               } else  dupl++;                                            // 메뉴 1이 없다면 dupl라는 변수에 1을 더한다
            }

            if (dupl == model.getRowCount()) {                                 // 메뉴1이 없다면 (열을 쭉 검색해서 한번이라도 메뉴1이 보였다면 dupl은 행개수 - 1이 되었을것임)
               
               model.addRow(new Object[]{btn_MenuName, 1, menu1_Price});                  // 메뉴1을 추가한다
            }
         } 
      });
      
      btn_Menu2.addActionListener( ( e ) -> {                                 

         String btn_MenuName = btn_Menu2.getActionCommand();                        // 메뉴1 버튼이름 가져오기
         
         if (model.getRowCount() == 0 ) {                                    // 테이블이 비어있으면 무조건 메뉴1을 추가함
            
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
      
      btn_Menu3.addActionListener( ( e ) -> {                                 

         String btn_MenuName = btn_Menu3.getActionCommand();                        
         
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
      
      btn_Menu4.addActionListener( ( e ) -> {                                 

         String btn_MenuName = btn_Menu4.getActionCommand();                        
         
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
      
// 패널전환
      
      btn_Cate2.addActionListener( ( e ) -> {
         
         bottom.setVisible(false);
      });
      
      
// 기능 연결 -> +버튼 
      
                  
         
         
         
            
         
         
         
      
         
         
      

   }
   
   
   private void gbAdd(JComponent c, int x, int y, int w, int h) { 

      gbc.gridx = x; 
      gbc.gridy = y; 
      gbc.gridwidth = w;
      gbc.gridheight = h; 
      gb.setConstraints(c, gbc);
   } 
   
   public void Insert_table() {
      
   }
   
}