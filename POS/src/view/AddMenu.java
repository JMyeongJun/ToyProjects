package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddMenu extends JFrame{
   
   
   //Fields
   BasicPanel bp = null;
   JButton    btnOk, btnCancel;
   JComboBox  cbCat;
   JLabel     lblMenuName, lblMenuPrice, lblCat;
   JTextField tfMenuName, tfMenuPrice;
   JPanel     btnPanel;
   
   //레이아웃 설정
   GridBagLayout gb;
   GridBagConstraints gbc;
   
   public AddMenu() {
      initComponent();
   }
   

   public AddMenu(BasicPanel basicPanel) {
      initComponent();
      this.bp = basicPanel;
      
      
   }
   
   private void initComponent() {
      setTitle("메뉴 등록");
      gb       = new GridBagLayout();
      this.setLayout( gb );
      
      gbc      = new GridBagConstraints();
      gbc.fill = GridBagConstraints.BOTH;
      gbc.weightx = 1.0;
      gbc.weighty = 1.0;
      
      //부품배치
      JLabel lblMenuName = new JLabel("메뉴 이름");
             tfMenuName  = new JTextField(20);
      tfMenuName.setBorder( BorderFactory.createEmptyBorder(20,20,20,20) );
       gbAdd( lblMenuName, 0, 0, 1, 1);
       gbAdd( tfMenuName,  0, 1, 1, 1);
             
      JLabel lblMenuPrice = new JLabel("가격");
             tfMenuPrice  = new JTextField(20);
       gbAdd( lblMenuPrice, 0, 2, 1, 1);
        gbAdd( tfMenuPrice,  0, 3, 1, 1);  
             
      JLabel lblCat      = new JLabel("카테고리");
      String[] Cat = {"카테고리1", "카테고리2","카테고리3"};
      cbCat              = new JComboBox( Cat );
       gbAdd( lblCat, 0, 4, 1, 1);
       gbAdd( cbCat , 0, 5, 1, 1);
       
       JPanel btnPanel = new JPanel();
       btnCancel = new JButton("취소");
       btnOk     = new JButton("등록");
       
       btnPanel.add(btnCancel);
       btnPanel.add(btnOk);
       gbAdd( btnPanel, 0, 6, 5, 1);
       
       setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
       setSize( 500,500);
       setVisible( true );
   }

   private void gbAdd(JComponent c, int x, int y, int w, int h) {
      gbc.gridx = x;
      gbc.gridy = y;
      gbc.gridwidth = w;
      gbc.gridheight = h;
      gb.setConstraints(c, gbc);
      gbc.insets = new Insets(2, 2, 2, 2);
      this.add( c, gbc );
   }


   public static void main(String[] args) {
      new AddMenu();

   }

}