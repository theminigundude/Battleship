package battleship;

public class player {
	
	private String name;
	public int score;
	public board b, enemy_b;
	
	//create a new player
	public player (String name_in) {
		this.name = name_in;
		this.score = 0;
		System.out.println("Score set to 0");
		System.out.println("board printed\n");
		b = new board(name, false);
		enemy_b = new board(name, true);	
	}
	
	public String getName () {
		return name;
	}
	
	public int getScore () {
		return score;
	}
	
	
}
