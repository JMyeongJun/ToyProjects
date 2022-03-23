package chess_main;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		char[][] chessMap = new char[8][8];
		initMap(chessMap);
		printMap(chessMap);
		
		Scanner sc = new Scanner(System.in);
		String input;
		String[] li;
		
		System.out.print("말 선택 : ");
		while(!(input = sc.nextLine()).equals("exit")) {
			li = input.split(",");
			System.out.println(chessMap[Integer.parseInt(li[0])][Integer.parseInt(li[1])] + "선택 " + Arrays.toString(li));
			char temp = chessMap[Integer.parseInt(li[0])][Integer.parseInt(li[1])];
			chessMap[Integer.parseInt(li[0])][Integer.parseInt(li[1])] = ' ';
			
			System.out.print("이동할 곳 선택 : ");
			input = sc.nextLine();
			li = input.split(",");
			System.out.println(Arrays.toString(li) + " 이동");
			chessMap[Integer.parseInt(li[0])][Integer.parseInt(li[1])] = temp;
			
			printMap(chessMap);
			
			System.out.print("말 선택");
		}
		
		sc.close();
	}

	public static void initMap(char[][] map) {
		char[] set = { 'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R' };
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (i == 0 || i == 7) {
					map[i][j] = set[j];
				}else if(i == 1 || i == 6) {
					map[i][j] = 'P';
				}
			}
		}
	}
	
	public static void printMap(char[][] map) {
		System.out.println("-----------------------------");
		System.out.println("   0  1  2  3  4  5  6  7");
		for (int i = 0; i < 8; i++) {
			System.out.print(i + " ");
			for (int j = 0; j < 8; j++) {
				System.out.print("[");
				System.out.print(map[i][j]);
				System.out.print("]");
			}
			System.out.println();
		}
		System.out.println("-----------------------------");
	}
	
	public static void select() {
		
	}
	
	public static void move(char[][] map, int ind1, int ind2) {
		
	}
}
