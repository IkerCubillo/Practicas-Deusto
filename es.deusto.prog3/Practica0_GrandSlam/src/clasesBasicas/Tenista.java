package clasesBasicas;

import java.util.ArrayList;

public class Tenista {
	
	private String name;
	private String nationality;
	private int numWins;
	
	public Tenista(String name, String nationality, int numWins) {
		this.name = name;
		this.nationality = nationality;
		this.numWins = numWins;
	}
	//

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public int getNumWins() {
		return numWins;
	}

	public void setNumWins(int numWins) {
		this.numWins = numWins;
	}

	@Override
	public String toString() {
		return "Tenista [name=" + name + ", nationality=" + nationality + ", numWins=" + numWins + "]";
	}

}
