import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;



public class  TP{
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

            ArrayList<Integer> A = Astar(S);
            System.out.println(A);
        }

        public static ArrayList<Integer> Astar(ArrayList<Integer> S){
            int Sum = sum(S);
            PriorityQueue<Noeud> Ferme = new PriorityQueue<Noeud>(new Comparator<Noeud>(){
                @Override
                public int compare(Noeud o1, Noeud o2) {
                    return o1.compareTo(o2);
            }
            });
            PriorityQueue<Noeud> Ouvert = new PriorityQueue<Noeud>(new Comparator<Noeud>(){
                @Override
                public int compare(Noeud o1, Noeud o2) {
                    return o1.compareTo(o2);
            }
            });
            
            ArrayList<Integer> A = new ArrayList<Integer>();
            Noeud Elem  = new Noeud(f(A,Sum), A);
            
            Ouvert.add(Elem);
            while (Ouvert.size() != 0) {
                Elem = Ouvert.poll();
                System.out.println(Elem.children);
                Ferme.add(Elem);
                ArrayList<Integer> T = new ArrayList<Integer>(S);
                remove(T,Elem.children);

                for (Integer integer : T) {
                    ArrayList <Integer> newL = new ArrayList<Integer>(Elem.children);
                    newL.add(integer);

                    Noeud newElem  = new Noeud(f(newL,Sum),newL);
                    if(sum(newElem.children) >= Sum/2)
                        return newElem.children;
                    if(sum(newElem.children) < Sum/2)
                        Ouvert.add(newElem);  
                }

            }
            return Ferme.poll().children;
        }

        private static void remove(ArrayList<Integer> t, ArrayList<Integer> children) {
            for (Integer integer : children) {
                t.remove(integer);
            }
        }

        private static int sum(ArrayList<Integer> s) {
            int sum = 0;
            for (Integer object : s) {
                sum+=object;
            }
            return sum;
        }

        private static int f(ArrayList<Integer> a, int s) {
            int sum = sum(a);
            return sum - (s/2);
        }

        
}