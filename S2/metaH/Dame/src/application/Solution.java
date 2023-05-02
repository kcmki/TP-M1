package application;



public class Solution{
	long elapsedTime;
	ChessTable Solution;
	long parcouru;
	long cree;
	int fit;
	public Solution(ChessTable sol,long time,long parcouru,long cree) {
		this.Solution = sol;
		this.elapsedTime = time;
		this.parcouru=parcouru;
		this.cree=cree;
		
		this.fit = fitnessFct(this.Solution.Queens);
		System.out.println(this.fit);
	}
	
    public int fitnessFct(int[] Position) {
        int nb = 0;
        int n= Position.length;
        
        for (int i = 0; i < n-1; i++) {
            for (int j = i+1; j < n; j++) {
                if (Position[i] == Position[j] || Math.abs(Position[i] - Position[j]) == j-i) {
                    nb++;
                }
            }
        }
        return nb;
    }
}
