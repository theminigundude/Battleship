package battleship;

public class board {
	
	private String[][] bs_map;
	private String name;
	private boolean which = false;
	
	
	public board(String name, boolean which) {
		//Initialize board and create empty slots
		//which determines whether it is your board or enemy's board
		bs_map = new String[10][10];
		for (int n = 0; n < bs_map.length; n++) {
			for (int a = 0; a < bs_map[n].length; a++) { bs_map[n][a] = " "; }		
		}
		this.name = name;
		this.which = which;
	}
	
	//add ship to board
	public void add_ship (int row, int column, String type) {
		bs_map[row][column] = type;
	}
	
	//print board
	public void print_map () {
		if (which == true) System.out.println("\n" + name + "'s strike board: \n");
		else System.out.println("\n" + name + "'s own board: \n");
		System.out.print("   ");
		for (int n = 0; n < bs_map.length; n++) {
			System.out.print(" " + (n+1) + "  ");
		}
		System.out.println("");
		for (int n = 0; n < bs_map.length; n++) {
			if (n > 8) System.out.print("" + (n+1) + "  ");
			else System.out.print(" " + (n+1) + "  ");
			for (int a = 0; a < bs_map[n].length; a++) { 
				if (a == bs_map[n].length-1) System.out.print(bs_map[n][a]);
				else System.out.print(bs_map[n][a] + " | ");		 
				}
			if (n < bs_map[n].length-1) System.out.println("\n------------------------------------------");
		}
		System.out.println("");
	}

	//getBoard
	public String[][] getBs_map() {
		return bs_map;
	}

	//get value from a set of coordinates
	public String get_value (int row, int column) {
		return bs_map[row][column];
	}
	
	//set value from a set of coordinates
	public void set_value (int row, int column, int result) {
		//miss
		if (result == 0) bs_map[row][column] = "O";
		//hit
		else if (result == 1) bs_map[row][column] = "X";
		//own ship sunk
		else if (result == 2) bs_map[row][column] = "=";
		
	}
	
	

}
