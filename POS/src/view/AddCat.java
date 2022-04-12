package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import model.CategoryDao;

public class AddCat extends JFrame {
	MenuPanel mp;

	JButton btnOk, btnCancel;
	JLabel lbl;
	JTextField tfCatName;

	// Layout
	GridBagLayout gb;
	GridBagConstraints gbc;

	// Constructor
	public AddCat() {
		initComponent();
	}

	public AddCat(MenuPanel mp) {
		this();
		this.mp = mp;
	}

	private void initComponent() {
		setTitle("카테고리등록");

		// 레이아웃 설정
		gb = new GridBagLayout();
		this.setLayout(gb);

		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;

		// 부품배치
		JLabel lbl = new JLabel("카테고리 등록");
		tfCatName = new JTextField(20);
		JButton btnOk = new JButton("등록");
		JButton btnCancel = new JButton("취소");
		gbAdd(lbl, 0, 0, 1, 1);
		gbAdd(tfCatName, 0, 1, 1, 1);
		gbAdd(btnOk, 1, 2, 1, 1);
		gbAdd(btnCancel, 0, 2, 1, 1);

		btnOk.addActionListener((e) -> {
			System.out.println("등록 버튼 클릭");
			insertCat();
		});

		btnCancel.addActionListener((e) -> {
			this.dispose();
		});

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(380, 400);
		setVisible(true);
	}

	private void gbAdd(JComponent c, int x, int y, int w, int h) {
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = w;
		gbc.gridheight = h;
		gb.setConstraints(c, gbc);
		gbc.insets = new Insets(2, 2, 2, 2); // 설정
		this.add(c, gbc);

	}

	private void insertCat() {
		CategoryDao dao = new CategoryDao();
		String categoryName = this.tfCatName.getText();
		dao.insertCategory(categoryName);
		dispose();
		mp.viewCategory();
	}

}
