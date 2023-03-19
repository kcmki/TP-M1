import java.util.ArrayList;

public class Regle {
	public int name;
	public ArrayList<String> premisses;
	public ArrayList<String> resultants;
	
	public Regle(int name, ArrayList<String> premisses, ArrayList<String> resultants){
		this.name = name;
		this.premisses = premisses;
		this.resultants = resultants;
	}
}
