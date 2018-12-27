package battleship;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class main {

	public static void main(String[] args) throws Exception {
		
		Scanner reader = new Scanner(System.in);
		String name, in, space = "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n";
		int strike1 = 0, strike2 = 0, end_score = 3;
		String[] sp;
		boolean gate = false;
		player p1, p2;
		
		
		System.out.println("Welcome to Battleship. Please input user information below.");
		System.out.println("What kind of game do you want to play? type in 1 for bot VS player, type in 2 for player VS player and type in 3 for bot VS bot");
		System.out.println("Player 1 first. What is you name?");
		name = reader.nextLine();
		p1 = new player(name);
		p1.b.print_map();
		System.out.println("\nWelcome " + name + ", Now you must plot your ships.");
		init_board(p1, reader);
		System.out.println("Board created. Please review your board and turn the computer over to player 2.");
//		TimeUnit.SECONDS.sleep(3);
		System.out.println(space + "Player 2's turn now. What is you name?");
		name = reader.nextLine();
		p2 = new player(name);
		p2.b.print_map();
//		System.out.println("\nWelcome " + name + ", Now you must plot your ships. (Don't peak earlier lines!)");
		init_board(p2, reader);
		System.out.println("Board created. Please review your board and turn the computer over to player 1.");
//		TimeUnit.SECONDS.sleep(3);
		
		System.out.println(space);
		
	
		while (p1.score < end_score && p2.score < end_score) {
			System.out.println("\nPlayer 1. Your turn. ");
			System.out.println("As a reminder, here is your strike board");
			p1.enemy_b.print_map();
			while (gate == false) {
				System.out.println("Where do you want to strike? Please enter in 2 numbers in order and in the following format: 1 1 (this represents (1,1)). It will only take in the first two numbers you type.");
				System.out.println("alternatively, you can type in -1 to see your own board.");
				in = reader.nextLine();
				if ( in.equals("-1")) {
					p1.b.print_map();
//					TimeUnit.SECONDS.sleep(4);
					System.out.println(space);
				}
				else {
					//"\\s+" = all white space
					in = in.replaceFirst("\\s++$","");
					try {
						sp = in.split("\\s+");
						strike1 = Integer.parseInt(sp[0]); strike2 = Integer.parseInt(sp[1]);
						if (strike_valid(strike1, strike2, p1) == true) { gate = true;}
					}
					catch (Exception e) {
						System.out.println("make sure you enter the numbers correctly! no leading white space.\n");
					}
					
				}
			}
			gate = false;
			if (p2.b.get_value(strike1-1, strike2-1) != " " ) {
				System.out.println("hit!");
				p1.score++;
				p1.b.set_value(strike1-1, strike2-1, 1);
				p2.b.set_value(strike1-1, strike2-1, 2);
				p1.enemy_b.set_value(strike1-1, strike2-1, 1);
			}
			else {
				System.out.println("Miss!");
				p1.enemy_b.set_value(strike1-1, strike2-1, 0);
			}
			p1.enemy_b.print_map();
//			TimeUnit.SECONDS.sleep(3);
			if (p1.score >= end_score) break;
			else {
				System.out.println("\nPlayer 2. Your turn.");
				System.out.println("As a reminder, here is your strike board");
				p2.enemy_b.print_map();
				while (gate == false) {
					System.out.println("Where do you want to strike? Please enter in 2 numbers in order and in the following format: 1 1 (this represents (1,1)). It will only take in the first two numbers you type.");
					System.out.println("alternatively, you can type in -1 to see your own board.");
					in = reader.nextLine();
					if (in.equals("-1")) {
						p2.b.print_map();
//						TimeUnit.SECONDS.sleep(4);
						System.out.println(space);
					}
					else {
						in = in.replaceFirst("\\s++$","");
						try {
							sp = in.split("\\s+");
							strike1 = Integer.parseInt(sp[0]); strike2 = Integer.parseInt(sp[1]);
							if (strike_valid(strike1, strike2, p2) == true) { gate = true;}
						}
						catch (Exception e) {
							System.out.println("make sure you enter the numbers correctly! no leading white space.\n");
						}
					}
			
				}
				gate = false;
				
					if (p1.b.get_value(strike1-1, strike2-1) != " " ) {
						System.out.println("hit!");
						p2.score++;
						p2.b.set_value(strike1-1, strike2-1, 1);
						p1.b.set_value(strike1-1, strike2-1, 2);
						p2.enemy_b.set_value(strike1-1, strike2-1, 1);
					}
					else {
						System.out.println("Miss!");
						p2.enemy_b.set_value(strike1-1, strike2-1, 0);
					}
					p2.enemy_b.print_map();
//					TimeUnit.SECONDS.sleep(3);
			}
				
		}
		
		if ((p1.score > p2.score) && p1.score == end_score) {
			System.out.println(p1.getName() + " WON!");
		}
		else if ((p2.score > p1.score) && p2.score == end_score) {
			System.out.println(p2.getName() + " WON!");
		}
		
	}
	
	public static void init_board (player p, Scanner reader) {
		boolean gate = false;
		while (gate == false) {
			System.out.println("Where do you want your aircraft carrier (5 squares)?"
					+ "\nPlease enter in order and in the following format: 1 1 1 2 1 3 (this represents (1,1),(1,2),(1,3).");
			gate = add(reader,p, "a", 10);
		}
		gate = false;
		while (gate == false) {
			System.out.println("\nWhere do you want your battleship (4 squares)?"
					+ "\nPlease enter in order and in the following format: 1 1 1 2 1 3 (this represents (1,1),(1,2),(1,3).");
			gate = add(reader,p, "b", 8);
		}
		gate = false;
		while (gate == false) {
			System.out.println("\nWhere do you want your cruiser (3 squares)?"
					+ "\nPlease enter in order and in the following format: 1 1 1 2 1 3 (this represents (1,1),(1,2),(1,3).");
			gate = add(reader,p, "c", 6);
		}
		gate = false;
		while (gate == false) {
			System.out.println("\nWhere do you want your first destroyer (2 squares)?"
					+ "\nPlease enter in order and in the following format: 1 1 1 2 1 3 (this represents (1,1),(1,2),(1,3).");
			gate = add(reader,p, "d", 4);
		}
		gate = false;
		while (gate == false) {
			System.out.println("\nWhere do you want your second destroyer (2 squares)?"
					+ "\nPlease enter in order and in the following format: 1 1 1 2 1 3 (this represents (1,1),(1,2),(1,3).");
			gate = add(reader,p, "d", 4);
		}
		gate = false;
		while (gate == false) {
			System.out.println("\nWhere do you want your first submarine (1 square)?"
					+ "\nPlease enter in order and in the following format: 1 1 1 2 1 3 (this represents (1,1),(1,2),(1,3).");
			gate = add(reader,p, "s", 2);
		}
		gate = false;
		while (gate == false) {
			System.out.println("\nWhere do you want your second submarine (1 square)?"
					+ "\nPlease enter in order and in the following format: 1 1 1 2 1 3 (this represents (1,1),(1,2),(1,3).");
			gate = add(reader,p, "s", 2);
		}
	}
	
	//check values are in range, if so add to board
	public static boolean rest_add (String list[], int[] num_list, player p, String type) {
		for (int n = 0; n < list.length; n++) {
			int temp = Integer.parseInt(list[n]);
			num_list[n] = temp;
			if (temp < 1 || temp > 10) { System.out.println("Make sure you enter values within range!");return false; }
		}
		String[][]board = p.b.getBs_map();
		int row = -1, column = -1;
		for (int n = 0; n < list.length; n++) {
			int temp = Integer.parseInt(list[n]); 
			if (row == -1) row = temp;
			else if (column == -1) column = temp;
			if (row != -1 && column != -1) {
				if (board[row-1][column-1] == " ") {
					p.b.add_ship(row-1, column-1, type);
					row = column = -1;
				}
				else {
					System.out.println("no repeat!");
					return false;
				}
			}
		}
		p.b.print_map();
		return true;
	}
	
	//check if users enter the right number of coordinates 
	public static boolean rest_num (int num, String[] list) {
		if (num == list.length) return true;
		else {
			System.out.println("Enter the right number of coordinates! No leading white spaces");
			return false;
		}
	}
	
	//driver function to add to the board
	public static boolean add (Scanner reader, player p, String type, int num) {
		String in = reader.nextLine();
		String[]list = in.split("\\s+");
		
		if (rest_num(num, list) == false) return false;
		if (num == 2) {
			int[] num_list = new int[list.length];
			
			if (list.length < 2) {
				System.out.println("Enter a set of cordinates.");
				return false;
			}
			if (rest_add(list,num_list,p,type) == true) return true;
			else return false;
		}
		
		else if (check_valid(in) == 1) {
			int[] num_list = new int[list.length];
			if (rest_num(num, list) == false) return false;
			if (rest_add(list,num_list,p,type) == true) return true;
			else return false;
		}
		
		else return false;
	}
	
	//check if values entered are valid (horizontal and vertical)
	public static int check_valid (String in) {

		String[]list = in.split("\\s+");
		int[] num_list = new int[list.length];
		for (int n = 0; n < list.length; n++) {
			num_list[n] = Integer.parseInt(list[n]);
		}
		
		//whether vertical or horizontal
		int difference = 2;
		if (Math.abs(num_list[0] - num_list[2]) == 0) difference = 0;
		else if (Math.abs(num_list[1] - num_list[3]) == 0) difference = 1;
		if (difference == 2) {System.out.println("Make sure you enter valid ship locations!");return 0; }
		int d = num_list[difference] - num_list[difference+2];
		for (int n = difference; n < num_list.length-2; n=n+2) {
			if ((num_list[n] - num_list[n+2]) != 0) { 
				System.out.println("Make sure you enter valid ship locations!");return 0; 
			}
			else {
				if (difference == 1) {
					if (Math.abs(num_list[n-1] - num_list[n+1]) != 1) {
						System.out.println("Make sure you enter valid ship locations!");return 0; 
					}
				}
				else {
					if (Math.abs(num_list[n+1] - num_list[n+3]) != 1) {
						System.out.println("Make sure you enter valid ship locations!");return 0; 
					}	
				}
			}
		}
		return 1;
	}

	public static boolean strike_valid (int row, int column, player p) {
		if ((row < 1 || row > 10) || (column < 1 || column > 10)) {
			System.out.println("Enter a valid target within range!\n");
			return false;
		}
		if (p.enemy_b.get_value(row-1, column-1) != " ") {
			System.out.println("You have already striked!\n");
			return false;
		}
		return true;
	}
}
