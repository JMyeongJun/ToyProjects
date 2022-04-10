package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import model.CategoryDao;
import model.MenuDao;

public class MenuPanel extends BasicPanel implements ActionListener{
	
	JButton btnAddMenu, btnAddCat,
			btnCat1, btnCat2, btnCat3,
			btnMenu1, btnMenu2, btnMenu3;
	
	AddMenu     adm  = null;
	AddCat      adc  = null;
	ClickedMenu ckm  = null; 
	
	public MenuPanel() {
		initComponent();
	}
	
	private void initComponent() {
		
		//top_bottom Layout 설정 
		top_bottom.setLayout( new FlowLayout(FlowLayout.LEFT,5,5) );
		top_bottom.setBorder( BorderFactory.createEmptyBorder(4, 4, 4, 4) );
		//side 패널
		side.setLayout( new BorderLayout() );
		//bottom 패널
		bottom.setLayout( new FlowLayout( FlowLayout.LEFT, 5, 5) );
		bottom.setBorder( BorderFactory.createEmptyBorder(4, 4, 4, 4) );
		
		
		//button
		btnCat1 = new JButton("카테고리1");
		btnCat2 = new JButton("카테고리2");
		btnCat3 = new JButton("카테고리3");
		
		btnAddCat  = new JButton("+카테고리");
		btnAddMenu = new JButton("메뉴 등록");
		
		btnMenu1 = new JButton("메뉴1");
		btnMenu2 = new JButton("메뉴2");
		btnMenu3 = new JButton("메뉴3");
		
		btnCat1.setPreferredSize( new Dimension( 100, 50 ) );
		btnCat2.setPreferredSize( new Dimension( 100, 50 ) );
		btnCat3.setPreferredSize( new Dimension( 100, 50 ) );
		
		btnAddCat.setPreferredSize( new Dimension( 100, 50 ) );
		btnAddMenu.setPreferredSize( new Dimension( 100, 50 ) );
		
		side.setBorder( BorderFactory.createEmptyBorder( 20, 50, 50, 50 ) );
		
		btnMenu1.setPreferredSize( new Dimension( 100, 100) );
		btnMenu2.setPreferredSize( new Dimension( 100, 100) );
		btnMenu3.setPreferredSize( new Dimension( 100, 100) );
		
		bottom.setBorder( BorderFactory.createEmptyBorder( 20, 20, 20, 20) );
		
		top_bottom.add( btnCat1 );
		top_bottom.add( btnCat2 );
		top_bottom.add( btnCat3 );
		top_bottom.add( btnAddCat );
		
		side.add( btnAddMenu, BorderLayout.SOUTH);
		
		bottom.add( btnMenu1 );
		bottom.add( btnMenu2 );
		bottom.add( btnMenu3 );
		
		this.btnAddMenu.addActionListener( this );
		this.btnAddCat.addActionListener( this );
		this.btnMenu1.addActionListener( this );
		this.btnCat1.addActionListener( this );
		//카테고리 버튼 클릭시 이벤트 연결
		//this.
	}

	public static void main(String[] args) {
		new MenuPanel();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton) e.getSource();
		System.out.println( btn.getText() );
		System.out.println( e.getSource() );
		//메뉴 등록 버튼 열리도록
		if ( e.getSource() == btnAddMenu) {
			if( adm != null) {
				adm.dispose();
			}
			adm = new AddMenu( this );
		}
		
		//카테고리 등록 버튼 열리도록
		if ( e.getSource() == btnAddCat) {
			if( adc != null) {
				adc.dispose();
			}
			adc = new AddCat( this );
		}
		
		// 메뉴 클릭시
		if ( btn.getText().equals("메뉴1") ) {
			System.out.println(e.getSource());									// <내가 누른 것이 Menu인 것을 어떻게 알려주나? 
		    if( ckm != null) {                // 아메리카노 카라멜마키아또 카페모카 등 이름이 전부 달라 
		    	ckm.dispose();                // getText() 사용 불가능. 
		    }
		   ckm = new ClickedMenu();
		}
		
		//카테고리 클릭시
		if ( e.getSource() == btnCat1 ) {
			String catName = this.btnCat1.getText();
			int choice = JOptionPane.showConfirmDialog(null,
					"해당 카테고리를 삭제하시겠습니까?",
					"카테고리 삭제",
					JOptionPane.YES_NO_OPTION);
			//System.out.println( choice ); 예0 아니오1 x:-1
			// 예 또는 아니오 또는 x버튼을 눌렀을 때
			
			String msg = "";
			if( choice == 0) {
				CategoryDao dao = new CategoryDao();
				dao.deleteCategory( catName );
				msg = catName + "(이)가 삭제되었습니다";
				
				}else if (choice == 1) {
					msg = catName + "(이)가 삭제되지 않았습니다.";
				}else {
					msg = "취소를 클릭하였습니다.";
				}
				JOptionPane.showMessageDialog(null,
						msg, "삭제확인",JOptionPane.OK_OPTION);
				
			}
		}
		
		// 카테고리 버튼[카테고리1],[카테고리2],[카테고리3],... 클릭시 
		// 삭제하시겠습니까? 창이 떠야한다. 
		// -> 카테고리1,2,3 그리고 추가하며 4567이 생길텐데
		// 어떤 기준으로 클릭한 것이 [카테고리@] 인 것을 알게 할 수 있나 ?
	}


