package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ClickedMenu extends JFrame {
   
   BasicPanel bp = null;
   
   JButton    btnCancel, btnDelete, btnUpdate;
   JLabel     lblMenuName, lblMenuPrice, lblCat;
   JTextField tfMenuName, tfMenuPrice;
   JComboBox  cbCat;
   
   GridBagLayout gb;
   GridBagConstraints gbc;
   
   public ClickedMenu() {
      initComponent();
   }
   public ClickedMenu(BasicPanel basicPanel) {
      initComponent();
      this.bp = basicPanel;
   }
   
   private void initComponent() {
      setTitle("메뉴 관리");
      
      gb       = new GridBagLayout();
      this.setLayout( gb );
      
      gbc          = new GridBagConstraints();
      gbc.fill     = GridBagConstraints.BOTH;
      gbc.weightx  = 1.0;
      gbc.weighty  = 1.0;
      
      //메뉴이름
      lblMenuName = new JLabel("메뉴 이름");
      tfMenuName  = new JTextField();
      gbAdd( lblMenuName, 0, 0, 1, 1);
      gbAdd(  tfMenuName, 0, 1, 1, 1);
      
      //가격
      lblMenuPrice = new JLabel("가격");
      tfMenuPrice  = new JTextField();
      gbAdd( lblMenuPrice, 0, 2, 1, 1);
      gbAdd(  tfMenuPrice, 0, 3, 1, 1);
      
      //카테고리
      lblCat        = new JLabel("카테고리 ");
      String[] cats = {"카테고리1","카테고리2","카테고리3"};
      cbCat         = new JComboBox( cats );
      gbAdd( lblCat, 0, 4, 1, 1);
      gbAdd( cbCat , 0, 5, 1, 1);
      
      //최하단 버튼 3개 취소 삭제 수정
      JPanel btnPanel = new JPanel();
      btnCancel = new JButton("취소");
      btnDelete = new JButton("삭제");
      btnUpdate = new JButton("수정");
      
      btnPanel.add(btnCancel);
      btnPanel.add(btnDelete);
      btnPanel.add(btnUpdate);
      gbAdd( btnPanel, 0, 7, 1, 1);
      
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      setSize(350,600);
      setVisible(true);
   }
   
   private void gbAdd(JComponent  c, int x, int y, int w, int h) {
      gbc.gridx         =   x;
      gbc.gridy         =   y;
      gbc.gridwidth     =   w;
      gbc.gridheight    =   h;
      gb.setConstraints( c, gbc );
      gbc.insets        =   new Insets(2, 2, 2, 2); 
      this.add(  c, gbc );
      
   }
   
   public static void main(String[] args) {
      new ClickedMenu();

   }

}