package application;


import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;


public class Controller {
	
	//GUI elements
	public TextField numberOfQueens;
	public GridPane chessTable;
	public TextField Timer,cree,parcouru;
	public Text TextBOX;
	public RadioButton buttonDFS,buttonBFS,buttonA1,buttonA2;
	
	//globals
	public int NBQ = 8;
	public Solver S;
	boolean finished = false;
	
	
	public void SetQueens(ActionEvent e) {
		int nbQueens = Utils.toNumeric(numberOfQueens.getText());
		
		if(nbQueens > 100) nbQueens = 100;
		if(nbQueens < 4) {TextBOX.setText("le minimum est 4");numberOfQueens.setText("4");}
		NBQ = nbQueens;
		int algo  = 0;
		Timer.setText("");
		parcouru.setText("");
		cree.setText("");
		
		buttonDFS.setDisable(true);
		buttonBFS.setDisable(true);
		buttonA1.setDisable(true);
		buttonA2.setDisable(true);
		numberOfQueens.setDisable(true);
		
		if(buttonDFS.isSelected()) algo = 0;
		if(buttonBFS.isSelected()) algo = 1;
		if(buttonA1.isSelected()) algo = 2;
		if(buttonA2.isSelected()) algo = 3;
		
		resetTable();

		Tasker task = new Tasker(NBQ,algo);     
		TextBOX.setText("Inprogress");
		try {new Thread(task).start();}
		catch(Exception ex){TextBOX.setText("Error :"+ex.getMessage());}
		
		
		task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
		    @Override
		    public void handle(WorkerStateEvent t) {
		    	Solver S1 = null;
		    	
		    	S1 = task.getValue();
		    	S = S1;

				buttonDFS.setDisable(false);
				buttonBFS.setDisable(false);
				buttonA1.setDisable(false);
				buttonA2.setDisable(false);
				numberOfQueens.setDisable(false);
				
				resetTable();
				drawQueens(chessTable,S.Sol.Solution.Queens);
				Timer.setText(""+S.Sol.elapsedTime/1000.0);
				parcouru.setText(""+S.Sol.parcouru);
				cree.setText(""+S.Sol.cree);
				TextBOX.setText("Done");
		    }
		});
	}
	
	
	private void drawQueens(GridPane chessTable, int[] queens) {
		for(int x = 0; x< queens.length;x++) {
			int i = x;
			int j = queens[x];
		
			Image Queen = new Image("crown-gold.png");
			Image QueenBlack = new Image("crown-gold-bg.png");
			
			Rectangle r = (Rectangle) chessTable.getChildren().get(i*NBQ+j);
			
			if(r.getFill() == Color.WHITE) r.setFill(new ImagePattern(Queen));
			else r.setFill(new ImagePattern(QueenBlack));
		}

	}
	
	private void resetTable() {
		chessTable.getChildren().clear();
		//draw the chess table
		    double s = 500/NBQ; // side of rectangle
		    boolean white = true;	
		    for (int i = 0; i < NBQ; i++) {
		      for (int j = 0; j < NBQ; j++) {
		        Rectangle r = new Rectangle(s, s, s, s);
		        if (white)
		          {r.setFill(Color.WHITE);white = !white;}
		        else white = !white;
		        chessTable.add(r, j, i);
		      }
		      if((NBQ % 2) == 0) white = !white;
		    }
		
	}

}
