package application;

import java.util.LinkedList;
import java.util.Stack;

public class Solver {
	public Solution DFS,BFS,A1,A2;
	public Solver(int NombreReines) {
		//DFS CALCULATION
			long start = System. currentTimeMillis();
			ChessTable  sol = dfs(NombreReines);
			long end = System. currentTimeMillis();
		this.DFS = new Solution(sol,end-start);
		//BFS CALCULATION
			start = System. currentTimeMillis();
			//sol = bfs(NombreReines);
			end = System. currentTimeMillis();
		this.BFS = new Solution(sol,end-start);
	}
	
	public static ChessTable dfs(int NombreReines) {
	    	int min = Integer.MAX_VALUE;
	    	Stack<ChessTable> StateStack = new Stack<ChessTable>();
	    	ChessTable sol = new ChessTable(NombreReines);
	    	ChessTable current;
	    	ChessTable  next;
	    	StateStack.push(sol);
	    	while(!StateStack.empty()) {
	    		current = StateStack.pop();
	    		if(!current.finished) {
	        		for(int i = 0; i < NombreReines;i++) {
	        			next = new  ChessTable(current);
	        			next.addElement(i);
	        			StateStack.push(next);
	        		}
	    		}else {
	    			int CurrentDanger = current.verifyQueens();
	    			if(CurrentDanger < min) {
	    				sol  = current;
	    				min  = CurrentDanger;
	    			}
	    		}
	    	}
	    	return sol;
	    }

	 public static ChessTable bfs(int NombreReines) {
	    	int min = Integer.MAX_VALUE;
	    	LinkedList<ChessTable> StateList = new LinkedList<ChessTable>();
	    	ChessTable sol = new ChessTable(NombreReines);
	    	ChessTable current;
	    	ChessTable  next;
	    	StateList.add(sol);
	    	while(!StateList.isEmpty()) {
	    		current = StateList.pop();
	    		if(!current.finished) {
	        		for(int i = 0; i < NombreReines;i++) {
	        			next = new  ChessTable(current);
	        			next.addElement(i);
	        			StateList.add(next);
	        		}
	    		}else {
	    			int CurrentDanger = current.verifyQueens();
	    			if(CurrentDanger < min) {
	    				sol  = current;
	    				min  = CurrentDanger;
	    			}
	    		}
	    	}
	    	return sol;
	    }
}
