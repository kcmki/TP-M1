import java.util.ArrayList;

public class TP2 {
	public ArrayList<Regle> BDR = new ArrayList<Regle>();
	public ArrayList<String> BDF = new ArrayList<String>();
	public String but;
	
	public TP2(ArrayList<Regle> BDR, ArrayList<String> BDF, String but) {
		this.BDR = BDR;
		this.BDF = BDF;
		this.but = but;
	}
	
	@SuppressWarnings("unlikely-arg-type")
	public ArrayList<Regle> ChainageAvant() {
		ArrayList<Regle> regle_appliquables = new ArrayList<Regle>();
		int cpt = 0;
		while(true) {
			for(int i = 0; i < BDR.size(); i++) {
				if(BDF.contains(BDR.get(i).premisses) && !regle_appliquables.contains(BDR.get(i))) {
					regle_appliquables.add(BDR.get(i));
				}
			}
			BDF.addAll(regle_appliquables.get(cpt).resultants);
			BDR.remove(regle_appliquables.get(cpt));
			cpt += 1;
			if(BDF.contains(but) || cpt >= regle_appliquables.size()) {
				break;
			}
		}
		return regle_appliquables;
	}
	
	public boolean but_atteint() {
		if(BDF.contains(but))
			return true;
		return false;
	}
}
