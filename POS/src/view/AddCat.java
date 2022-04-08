package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AddCat extends JFrame implements ActionListener{
   
   BasicPanel   bp = null;
   JButton      btnOk, btnCancel;
   JLabel       lbl;
   JTextField   tfCatName;
   
   //Layout
   GridBagLayout       gb;
   GridBagConstraints  gbc;
   
   //Constructor
   public AddCat() {
      initComponent();
   }
   
   public AddCat(BasicPanel basicPanel) {
      initComponent();
      this.bp = basicPanel;
   }
   
   private void initComponent() {
      setTitle("카테고리등록");
      
      //레이아웃 설정
      gb = new GridBagLayout();
      this.setLayout( gb );
      
      gbc = new GridBagConstraints();
      gbc.fill = GridBagConstraints.BOTH;
      gbc.weightx = 1.0;
      gbc.weighty = 1.0;
      
      //부품배치
      JLabel     lbl       = new JLabel("카테고리 등록");
      JTextField tfCatName = new JTextField( 20 );
      JButton    btnOk     = new JButton("등록");
      JButton    btnCancel = new JButton("취소");
      gbAdd( lbl, 0, 0, 1, 1 );
      gbAdd( tfCatName, 0, 1, 1, 1);
      gbAdd( btnOk, 1, 2, 1, 1);
      gbAdd( btnCancel,0, 2, 1, 1 );
      
      btnOk.addActionListener( this );
      btnCancel.addActionListener( this );
      
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      setSize( 380, 400 );
      setVisible( true );
   }
   
   private void gbAdd(JComponent c, int x, int y, int w, int h) {
      gbc.gridx    =   x;
      gbc.gridy    =   y;
      gbc.gridwidth  =   w;
      gbc.gridheight  =   h;
      gb.setConstraints(c, gbc);
      gbc.insets   =    new Insets(2, 2, 2, 2); //설정
      this.add( c, gbc );
      
   }

   public static void main(String[] args) {
      new AddCat();

   }

   @Override
   public void actionPerformed(ActionEvent e) {
      // TODO Auto-generated method stub
      
   }

}