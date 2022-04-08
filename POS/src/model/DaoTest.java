package model;

import java.util.Scanner;

public class DaoTest {
	public static void main(String[] args) {

		MenuDao mdao = new MenuDao();
		CategoryDao cdao = new CategoryDao();
		MemberDao bdao = new MemberDao();

		Scanner sc = new Scanner(System.in);

		boolean bo = true;

		while (bo) {
			System.out.println("------------------------");
			System.out.println("선택 1 : 메뉴");
			System.out.println("선택 2 : 카테고리");
			System.out.println("선택 3 : 멤버");

			switch (sc.nextInt()) {
			case 1:
				System.out.println("메뉴----------");
				System.out.println("1: 추가, 2: 수정, 3: 삭제");
				switch (sc.nextInt()) {
				case 1:
					// 메뉴이름, 가격, 카테고리id 입력
					mdao.insertMenu(sc.next(), sc.nextInt(), sc.nextInt());
					break;
				case 2:
					// 수정할 현재 메뉴, 메뉴이름, 가격 카테고리id 입력 
					mdao.updateMenu(sc.next(), sc.next(), sc.nextInt(), sc.nextInt());
					break;
				case 3:
					// 메뉴이름 입력
					mdao.deleteMenu(sc.next());
					break;
				}
				for(MenuVo vo : mdao.getMenuList()) {
					System.out.println(vo);
				}
				break;
			case 2:
				System.out.println("카테고리----------");
				System.out.println("1: 추가, 2: 수정, 3: 삭제");
				switch (sc.nextInt()) {
				case 1:
					// 카테고리이름 입력
					cdao.insertCategory(sc.next());
					break;
				case 2:
					// 수정할 카테고리이름, 카테고리이름 입력 
					cdao.updateCategory(sc.next(), sc.next());
					break;
				case 3:
					// 카테고리이름 입력
					cdao.deleteCategory(sc.next());
					break;
				}
				for(CategoryVo vo : cdao.getCategoryList()) {
					System.out.println(vo);
				}
				break;
			case 3:
				System.out.println("멤버----------");
				System.out.println("1: 추가, 2: 수정, 3: 삭제");
				switch (sc.nextInt()) {
				case 1:
					// 멤버이름, 번호 입력
					bdao.insertMember(sc.next(), sc.next());
					break;
				case 2:
					// 수정할 멤버의 번호, 멤버이름, 번호 입력
					bdao.updateMember(sc.next(), sc.next(), sc.next());
					break;
				case 3:
					// 번호 입력
					bdao.deleteMember(sc.next());
					break;
				}
				for(MemberVo vo : bdao.getMemberList()) {
					System.out.println(vo);
				}
				break;
			default:
				bo = false;
			}
		}
		
		sc.close();
	}
}
