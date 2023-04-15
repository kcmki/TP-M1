import java.util.Comparator;
import java.util.PriorityQueue;

public class TP4 {
    static int[] Tab = {4,2,3,4,1};
    public static void main(String[] args) {
        
        int TaillePop = 10;

        PriorityQueue<int[]> sol = new PriorityQueue<int[]>(new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2) {
                return fitFunc(o2,Tab) - fitFunc(o1,Tab);
        }
        });

        initPop(sol,Tab.length,TaillePop);

        System.out.println(sol.size());
        int maxGen = 100;
        int[][] Parents;
        int[] Enfant1,Enfant2;
        for (int i = 0; i < maxGen; i++) {
            Parents = Selection(sol);

            Enfant1 = Crossover(Parents[0],Parents[1]);
            Enfant2 = mutation(Enfant1);
            System.out.println("------------------");
            printArray(Enfant1);
            printArray(Enfant2);
            System.out.println("------------------");
            Remplacement(sol,Enfant1,fitFunc(Enfant1,Tab));
            Remplacement(sol,Enfant2,fitFunc(Enfant2,Tab));
            System.out.println(sol.size());
        }

        printArray(lastElem(sol));

    }
    public static int[] lastElem(PriorityQueue<int[]> sol) {
        int[] last = sol.poll();
        while (sol.size() != 0) {
            last = sol.poll();
        }
        return last;
    }
    public static int[][] Selection(PriorityQueue<int[]> sol) {
        int[][] Parents = new int[2][];
        Parents[0] = sol.poll();
        Parents[1] = sol.poll();
        sol.add(Parents[0]);
        sol.add(Parents[1]);
        return Parents;
    }

    public static int[] Crossover(int[] sol1, int[] sol2) {
        int[] newSol = new int[sol1.length];
        int index = (int) (Math.random() * sol1.length);
        for (int i = 0; i < index; i++) {
            newSol[i] = sol1[i];
        }
        for (int i = index; i < sol1.length; i++) {
            newSol[i] = sol2[i];
        }
        return newSol;
    }

    public static void Remplacement(PriorityQueue<int[]> sol, int[] Enfant, int eVal) {
        int[] pire = sol.poll();
        int pVal = fitFunc(pire,Tab);
        if (eVal < pVal) {
            sol.add(Enfant);
        }else{
            sol.add(pire);
        }
    }


    public static void initPop(PriorityQueue<int[]> sol,int Size, int PopSize) {
        for (int i = 0; i < PopSize; i++) {
            sol.add(randomSol(Size));
        }
    }

    public static  int[] randomSol(int Size) {
        int[] sol = new int[Size];
        for (int i = 0; i < Size; i++) {
            sol[i] = (int) (Math.random() * 2);
        }
        return sol;
    }

    public static int fitFunc(int[] sol,int[] Tab) {
        int s1 = 0;
        int s2 = 0;
        for (int i = 0; i < Tab.length; i++) {
            if (sol[i] == 1)  s1+=Tab[i];
            else s2+=Tab[i];
        }
        return Math.abs(s1-s2);
    }

    public static int[] mutation(int[] sol) {
        int[] newSol = new int[sol.length];
        for (int i = 0; i < sol.length; i++) {
            newSol[i] = sol[i];
        }
        int index = (int) (Math.random() * sol.length);
        newSol[index] = (newSol[index] == 1) ? 0 : 1;
        return newSol;
    }

    public static int[] crossover(int[] sol1, int[] sol2) {
        int[] newSol = new int[sol1.length];
        int index = (int) (Math.random() * sol1.length);
        for (int i = 0; i < index; i++) {
            newSol[i] = sol1[i];
        }
        for (int i = index; i < sol1.length; i++) {
            newSol[i] = sol2[i];
        }
        return newSol;
    }

    public static int sum(int[] Tab) {
        int sum = 0;
        for (int i = 0; i < Tab.length; i++) {
            sum += Tab[i];
        }
        return sum;
    }
    public static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
