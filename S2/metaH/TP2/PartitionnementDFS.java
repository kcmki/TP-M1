import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class PartitionnementDFS {

    public static void dfs(int[] S, int index, int sum, int target, ArrayList<Integer> solution, ArrayList<ArrayList<Integer>> solutions) {
        if (sum == target) {
            solutions.add(new ArrayList<>(solution));
        } else if (sum < target && index < S.length) {
            // inclure l'élément à l'indice index
            solution.add(S[index]);
            dfs(S, index+1, sum+S[index], target, solution, solutions);
            solution.remove(solution.size()-1);
            // exclure l'élément à l'indice index
            dfs(S, index+1, sum, target, solution, solutions);
        }
    }
    
    public static void main(String[] args) {
        int choiceOfData = Integer.parseInt(args[0]);
        System.out.println(choiceOfData);
        ArrayList<Integer> S = new ArrayList<>();
        try {
            File myObj = new File("dataset.txt");
            Scanner myReader = new Scanner(myObj);
            String data = "";
            for (int i = 0; i < choiceOfData; i++) {
                data = myReader.nextLine();
            }
            
            for (String string : data.split(",")) {
                S.add(Integer.parseInt(string.trim()));
            }
            System.out.println(S);
        myReader.close();
        } catch (FileNotFoundException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
        }


        int[] S2 =  S.toArray();
        int target = 5;
        ArrayList<ArrayList<Integer>> solutions = new ArrayList<>();
        dfs(S2, 0, 0, target, new ArrayList<>(), solutions);
        System.out.println(solutions); */
    }
}
