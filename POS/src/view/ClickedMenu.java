package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.MenuDao;
import model.MenuVo;

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
		
		//이벤트 연결
		btnDelete.addActionListener( ( e ) -> {
			System.out.println("삭제 버튼 클릭");
			deleteMenu();
			btnOnOff(true);
		});
		btnUpdate.addActionListener( ( e ) -> {
			System.out.println("수정 버튼 클릭");
			updateMenu();
			btnOnOff(true);
		});
		
		
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
	
	private void deleteMenu() {
		String menuName = this.tfMenuName.getText();
		int choice = JOptionPane.showConfirmDialog(null,
				menuName + "을(를) 삭제하시겠습니까?",
				"삭제확인",
				JOptionPane.YES_NO_OPTION);
		System.out.println( choice ); // 예0 아니오1 x-1
		String msg = "";
		if( choice == 0 ) {
			MenuDao dao = new MenuDao();
			dao.deleteMenu(  menuName  );
			msg = menuName + "(이)가 삭제되었습니다";
		}else if (choice == 1) {
			msg = menuName + "(이)가 삭제되지 않았습니다.";
		}else {
			msg = "취소를 클릭하였습니다.";
		}
		JOptionPane.showMessageDialog(null,
				msg, "삭제확인",JOptionPane.OK_OPTION);
		this.dispose();
	}
	private void updateMenu() {
		MenuDao dao = new MenuDao();
		String currentMenuName = "";
		String menuName   = tfMenuName.getText();
		int    price      = Integer.parseInt( tfMenuPrice.getText() );
		int    categoryId = cbCat.getSelectedIndex();
		
		dao.updateMenu(currentMenuName, menuName, price, categoryId);
		//dao.updateMenu( , , , );
		//String currentMenuName, String menuName,
		//int price, int categoryId)
		this.dispose();
	}
	private void btnOnOff(boolean sw) {
		
		
	}

}
