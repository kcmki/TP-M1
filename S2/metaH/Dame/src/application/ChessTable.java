package application;

public class ChessTable {
	
	public int Queens[];
	public int max;
	public int index;
	public boolean finished = false;
	
	public ChessTable(int NbQueens){
		this.Queens = new int[NbQueens];
			for(int i = 0 ; i < NbQueens;i++) this.Queens[i] = -1;
		this.max = NbQueens;
		this.index=0;
	}
	public ChessTable(ChessTable T){
		this.Queens = new int[T.max];
		for(int i = 0 ; i < T.index;i++) this.Queens[i] = T.Queens[i];
		this.max = T.max;
		this.finished = T.finished;
		this.index=T.index;
	}
	
	public void addElement(int x) {
		if(!finished) {
			this.Queens[index] = x;
			index++;
			if(index == max) finished = true;
		}
	}
	
	public  int verifyQueens() {
		int inDanger = 0;
		for(int i = 0;i < max;i++) {
			
			//on vérifie les colonne vu que les lignes sont surement juste
			for(int j= 0; j<max;j++) {
				if(Queens[i] == Queens[j] && i != j) inDanger++;
			}
			
			//vérification des diagonale
			inDanger = inDanger + verifyDiag(i,this.Queens[i]);
			
		}
		return inDanger;
	}
	
	private int verifyDiag(int i, int j) {
		int danger = 0;
		//on vérifie les quatre diagonale de l'element situé a [i,j]
		//Nord ouest
		int tempI = i,tempJ = j;
		while(tempI >= 0 && tempJ >= 0) {
			if(this.Queens[tempI] == tempJ && tempJ  !=j && tempI != i) danger++;
			tempI--;
			tempJ--;
		}
		//Sud est
		tempI = i;
		tempJ = j;
		while(tempI < max && tempJ < max) {
			if(this.Queens[tempI] == tempJ && tempJ  !=j && tempI != i) danger++;
			tempI++;
			tempJ++;
		}
		// nord est
		tempI = i;
		tempJ = j;
		while(tempI >= 0 && tempJ < max) {
			if(this.Queens[tempI] == tempJ && tempJ  !=j && tempI != i) danger++;
			tempI--;
			tempJ++;
		}
		//sud ouest
		tempI = i;
		tempJ = j;
		while(tempI < max && tempJ >= 0) {
			if(this.Queens[tempI] == tempJ && tempJ  !=j && tempI != i) danger++;
			tempI++;
			tempJ--;
		}
		return danger;
	}
}
