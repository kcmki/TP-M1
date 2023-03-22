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
		
		if(nbQueens > 500) nbQueens = 500;
		if(nbQueens < 4) {TextBOX.setText("le minimum est 4");numberOfQueens.setText("4");}
		NBQ = nbQueens;
		Timer.setText("");
		parcouru.setText("");
		cree.setText("");
		buttonDFS.setDisable(true);
		buttonBFS.setDisable(true);
		buttonA1.setDisable(true);
		buttonA2.setDisable(true);
		buttonDFS.setSelected(false);
		buttonBFS.setSelected(false);
		buttonA1.setSelected(false);
		buttonA2.setSelected(false);
		numberOfQueens.setDisable(true);
		
		resetTable();

		Tasker task = new Tasker(NBQ);     
		TextBOX.setText("Inprogress");
		try {new Thread(task).start();}
		catch(Exception ex){TextBOX.setText("Error :"+ex.getMessage());}
		
		
		task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
		    @Override
		    public void handle(WorkerStateEvent t) {
		    	Solver S1 = null;
		    	
		    	S1 = task.getValue();
		    	S = S1;

				TextBOX.setText("Select a solution to draw");
				buttonDFS.setDisable(false);
				buttonBFS.setDisable(false);
				buttonA1.setDisable(false);
				buttonA2.setDisable(false);
				numberOfQueens.setDisable(false);
		    }
		});
	}
	
	public void RadioSelection(ActionEvent e) {
		if(buttonDFS.isSelected()) {
			resetTable();
			drawQueens(chessTable,S.DFS.Solution.Queens);
			Timer.setText(""+S.DFS.elapsedTime/1000.0);
			parcouru.setText(""+S.DFS.parcouru);
			cree.setText(""+S.DFS.cree);
			
		}
		if(buttonBFS.isSelected()) {
			resetTable();
			drawQueens(chessTable,S.BFS.Solution.Queens);
			Timer.setText(""+S.BFS.elapsedTime/1000.0);
			parcouru.setText(""+S.BFS.parcouru);
			cree.setText(""+S.BFS.cree);
		}
		if(buttonA1.isSelected()) {
			resetTable();
			drawQueens(chessTable,S.A1.Solution.Queens);
			Timer.setText(""+S.A1.elapsedTime/1000.0);
			parcouru.setText(""+S.A1.parcouru);
			cree.setText(""+S.A1.cree);
		}
		if(buttonA2.isSelected()) {
			resetTable();
			drawQueens(chessTable,S.A2.Solution.Queens);
			Timer.setText(""+S.A2.elapsedTime/1000.0);
			parcouru.setText(""+S.A2.parcouru);
			cree.setText(""+S.A2.cree);
		}
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
