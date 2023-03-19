package application;

import javafx.concurrent.Task;
import javafx.scene.control.TextField;

public class Tasker extends Task<Solver> {
	int NbQueens;
	public Tasker(int NbQueens) {
		this.NbQueens = NbQueens;
	}
	
	@Override
	protected Solver call() throws Exception {
		Solver S = new Solver(this.NbQueens);
		return S;
	  }
	
	@Override
	protected void succeeded() {
	 super.succeeded();
	}

}