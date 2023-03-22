import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class PartitionnementDFS {
    public static int min;
    public static long nodes = 0;
    public static void dfs(Integer[] S, int index, int sum, int total, ArrayList<Integer> solution, ArrayList<ArrayList<Integer>> solutions) {
        if (abs(sum - (total - sum)) < min) {
            solutions.clear();
            solutions.add(new ArrayList<>(solution));
            min = abs(sum - (total - sum));
        }else if(abs(sum - (total - sum)) == min){
            solutions.add(new ArrayList<>(solution));
        }
        nodes++;
        
        if (index < S.length && sum < total/2) {
            // inclure l'élément à l'indice index
            solution.add(S[index]);
            
            dfs(S, index+1, sum+S[index], total, solution, solutions);
            solution.remove(solution.size()-1);
            // exclure l'élément à l'indice index
            dfs(S, index+1, sum, total, solution, solutions);
        }
    }
    
    private static int abs(int a) {
        if(a < 0) {
            return -a;
        }
        return a;
    }

    public static void main(String[] args) {
        int choiceOfData = Integer.parseInt(args[0]);
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

            System.out.println();

        myReader.close();
        } catch (FileNotFoundException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
        } 



        ArrayList<ArrayList<Integer>> solutions = new ArrayList<>();
        min = sum(S);
        System.out.println(S);
        long start = System.currentTimeMillis();
        dfs(S.toArray(Integer[]::new), 0, 0, min, new ArrayList<>(), solutions);
        long end = System.currentTimeMillis();
        System.out.println("Les solutions : \n");
        System.out.println(solutions);
        System.out.println("Nb solutions :"+solutions.size()/2);
        System.out.println("La difference entre les sous tableaux : "+min);
        System.out.println("Temps d'execution : "+(end-start)+" ms");
        System.out.println("Nombre de noeuds : "+nodes);
    }

    private static void randomArray(ArrayList<Integer> s, String string) {
        int n = Integer.parseInt(string);
        s.clear();
        for (int i = 0; i < n; i++) {
            s.add((int)(Math.random()*100));
        }
    }

    private static int sum(ArrayList<Integer> s) {
        int sum = 0;
        for (Integer integer : s) {
            sum += integer;
        }
        return sum;
    }
}
