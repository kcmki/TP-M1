package application;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Controller {
	
	public TextField numberOfQueens;
	public GridPane chessTable;
	public void SetQueens(ActionEvent e) {
	
		int nbQueens = Utils.toNumeric(numberOfQueens.getText());
		if(nbQueens > 500) nbQueens = 500;
		chessTable.getChildren().clear();
	    // Create 64 rectangles and add to pane
	    int count = 0;
	    double s = 500/nbQueens; // side of rectangle
	    	
	    for (int i = 0; i < nbQueens; i++) {
	      count++;
	      for (int j = 0; j < nbQueens; j++) {
	        Rectangle r = new Rectangle(s, s, s, s);
	        if (count % 2 == 0)
	          r.setFill(Color.WHITE);
	        chessTable.add(r, j, i);
	        count++;
	      }
	    }
	}
	

}
