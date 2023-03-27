package application;

import javafx.concurrent.Task;
import javafx.scene.control.TextField;

public class Tasker extends Task<Solver> {
	int NbQueens;
	int algo;
	public Tasker(int NbQueens, int algo) {
		this.NbQueens = NbQueens;
		this.algo = algo;
	}
	
	@Override
	protected Solver call() throws Exception {
		Solver S = new Solver(this.NbQueens,this.algo);
		return S;
	  }
	
	@Override
	protected void succeeded() {
	 super.succeeded();
	}

}