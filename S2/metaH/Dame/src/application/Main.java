package application;
	
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;



public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			
			Parent root = FXMLLoader.load(getClass().getResource("/Main.fxml"));
			primaryStage.setScene(new Scene(root));
			
			Image icon = new Image("crown.png");
			primaryStage.setResizable(false);
			primaryStage.getIcons().add(icon);
			primaryStage.setTitle("Dame");
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		//launch(args);
		int max = 8;
		Solver S = new Solver(max);
		Utils.printArray(S.BFS.Solution.Queens);
		System.out.println(S.BFS.elapsedTime);
		Utils.printArray(S.DFS.Solution.Queens);
		System.out.println(S.DFS.elapsedTime);
	}
}
