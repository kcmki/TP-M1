import java.util.Random;

public class PartitionProblemInstanceGenerator {

    public static void main(String[] args) {
        int n = 10;  // Taille de l'ensemble S
        int maxVal = 100;  // Valeur maximale des éléments de S
        int[] S = generateInstance(n, maxVal);
        System.out.println("Instance :");
        printArray(S);
    }

    // Génère une instance du problème de partitionnement
    public static int[] generateInstance(int n, int maxVal) {
        int[] S = new int[n];
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            S[i] = rand.nextInt(maxVal) + 1;
        }
        return S;
    }

    // Affiche un tableau d'entiers
    public static void printArray(int[] arr) {
        System.out.print("[ ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println("]");
    }


    // Evaluer la solution
    public static int evaluate(int[] S, int[] S1Indices) {
    int sumS1 = 0;
    int sumS2 = 0;
    for (int i = 0; i < S.length; i++) {
        if (contains(S1Indices, i)) { // si S[i] appartient à S1
            sumS1 += S[i];
        } else { // sinon S[i] appartient à S2
            sumS2 += S[i];
        }
    }
    return Math.abs(sumS1 - sumS2);
    }

    public static boolean contains(int[] arr, int target) {
        for (int i : arr) {
            if (i == target) {
                return true;
            }
        }
        return false;
    }

    // Verifier la solution 

    public static int calculateDifference(int[] S, int[] Sprime) {
    int sumS1 = 0;
    
    for (int i = 0; i < Sprime.length; i++) {
        sumS1 += S[Sprime[i]];
    }
    
    int sumS2 = 0;
    
    for (int i = 0; i < S.length; i++) {
        if (!containsIndex(Sprime, i)) {
            sumS2 += S[i];
        }
    }
    
    return Math.abs(sumS1 - sumS2);
        }

        private static boolean containsIndex(int[] array, int index) {
            for (int i = 0; i < array.length; i++) {
                if (array[i] == index) {
                    return true;
                }
            }
            
            return false;
}
    //Solution 
    public static int[] generateSolution(int[] S) {
    int n = S.length;
    int[] SPrime = new int[n / 2]; // solution

    // tri des indices de S dans l'ordre décroissant selon les valeurs de S
    Integer[] indices = new Integer[n];
    for (int i = 0; i < n; i++) {
        indices[i] = i;
    }
    Arrays.sort(indices, (a, b) -> Integer.compare(S[b], S[a]));

    int sumS1 = 0;
    int sumS2 = 0;

    // placement des indices dans les sous-ensembles S1 et S2
    for (int i = 0; i < n; i++) {
        int index = indices[i];
        if (sumS1 <= sumS2) {
            SPrime[index] = 1; // ajout de l'index à S1
            sumS1 += S[index];
        } else {
            SPrime[index] = 2; // ajout de l'index à S2
            sumS2 += S[index];
        }
    }

    return SPrime;
}


}
