package application;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Stack;

public class Solver {
	public Solution DFS,BFS,A1,A2;
	long parcouru=0,cree=0;
	public Solver(int NombreReines) {
		//DFS CALCULATION
			long start = System. currentTimeMillis();
			ChessTable  sol = dfs(NombreReines);
			long end = System. currentTimeMillis();
			System.out.println("DFS done");
		this.DFS = new Solution(sol,end-start,parcouru,cree);
		parcouru = 0;cree = 0;
		//BFS CALCULATION
			start = System. currentTimeMillis();
			sol = bfs(NombreReines);
			end = System. currentTimeMillis();
			System.out.println("BFS done");
		this.BFS = new Solution(sol,end-start,parcouru,cree);
		parcouru = 0;cree = 0;
		//A1 CALCULATION
			start  = System. currentTimeMillis();
			sol = A1(NombreReines);
			end = System.currentTimeMillis();
			System.out.println("A1 done");
		this.A1 = new Solution(sol,end-start,parcouru,cree);
		//A2 CALCULATION
			start  = System. currentTimeMillis();
			sol = A2(NombreReines);
			end = System.currentTimeMillis();
			System.out.println("A2 done");
		this.A2 = new Solution(sol,end-start,parcouru,cree);
		
	}
	
	public ChessTable dfs(int NombreReines) {
	    	int min = Integer.MAX_VALUE;
	    	Stack<ChessTable> StateStack = new Stack<ChessTable>();
	    	ChessTable sol = new ChessTable(NombreReines);
	    	ChessTable current;
	    	ChessTable  next;
	    	StateStack.push(sol);
	    	while(!StateStack.empty()) {
	    		current = StateStack.pop();
	    		parcouru++;
	    		if(!current.finished) {
	        		for(int i = 0; i < NombreReines;i++) {
	        			next = new  ChessTable(current);
	        			next.addElement(i);
	        			StateStack.push(next);
	        			cree++;
	        		}
	    		}else {
	    			int CurrentDanger = current.verifyQueens();
	    			if(CurrentDanger < min) {
	    				sol  = current;
	    				min  = CurrentDanger;
	    			}
	    			if(CurrentDanger == 0) {sol  = new ChessTable(current);StateStack.clear();}
	    		}
	    	}
	    	return sol;
	    }

	public ChessTable bfs(int NombreReines) {
	    	int min = Integer.MAX_VALUE;
	    	LinkedList<ChessTable> StateList = new LinkedList<ChessTable>();
	    	ChessTable sol = new ChessTable(NombreReines);
	    	ChessTable current;
	    	ChessTable  next;

	    	StateList.add(sol);
	    	while(!StateList.isEmpty()) {
	    		current = StateList.poll();
	    		parcouru++;
	    		if(!current.finished) {
	        		for(int i = 0; i < NombreReines;i++) {
	        			next = new  ChessTable(current);
	        			next.addElement(i);
	        			StateList.add(next);
	        			cree++;
	        		}
	    		}else {
	    			int CurrentDanger = current.verifyQueens();
	    			if(CurrentDanger < min) {
	    				sol  = current;
	    				min  = CurrentDanger;
	    			}
	    			if(CurrentDanger == 0) {sol  = new ChessTable(current);StateList.clear();}
	    		}
	    	}
	    	return sol;
	    }

	
	public ChessTable A1(int NombreReines) {
  
    	PriorityQueue<ChessTable> StateList = new PriorityQueue<ChessTable>(new Comparator<ChessTable>() {

			@Override
			public int compare(ChessTable o1, ChessTable o2) {
				int x = o1.verifyQueensWithWeight();
				int y = o2.verifyQueensWithWeight();
				if(x > y)  return 1;
				if(x < y)  return -1;
				if( o1.index > o2.index ) return -1;
				if (o1.index < o2.index ) return 1;
				return 1;
			}});
    	
    	
    	ChessTable sol = new ChessTable(NombreReines);
    	ChessTable current;
    	ChessTable  next;

    	StateList.add(sol);
    	while(!StateList.isEmpty()) {
    		current = StateList.poll();
    		parcouru++;
    		if(!current.finished) {
        		for(int i = 0; i < NombreReines;i++) {
        			next = new  ChessTable(current);
        			next.addElement(i);
        			StateList.add(next);
        			cree++;
        		}
    		}else {
    			if(current.verifyQueens() == 0) {sol  = new ChessTable(current);StateList.clear();}
    		}
    	}
    	return sol;
	}
	public ChessTable A2(int NombreReines) {
		PriorityQueue<ChessTable> StateList = new PriorityQueue<ChessTable>(new Comparator<ChessTable>() {

			@Override
			public int compare(ChessTable o1, ChessTable o2) {
				int x = o1.verifyQueensWithWeight();
				int y = o2.verifyQueensWithWeight();
				if(x > y)  return 1;
				if(x < y)  return -1;
				if( o1.index > o2.index ) return -1;
				if (o1.index < o2.index ) return 1;
				return 1;
			}});
    	
    	
    	ChessTable sol = new ChessTable(NombreReines);
    	ChessTable current;
    	ChessTable  next;

    	StateList.add(sol);
    	while(!StateList.isEmpty()) {
    		current = StateList.poll();
    		parcouru++;
    		if(!current.finished) {
        		for(int i = 0; i < NombreReines;i++) {
        			next = new  ChessTable(current);
        			next.addElement(i);
        			StateList.add(next);
        			cree++;
        		}
    		}else {
    			if(current.verifyQueens() == 0) {sol  = new ChessTable(current);StateList.clear();}
    		}
    	}
    	return sol;
	}
}
