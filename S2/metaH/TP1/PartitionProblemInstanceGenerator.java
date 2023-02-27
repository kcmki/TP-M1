
import java.util.Arrays;
import java.util.Random;

public class PartitionProblemInstanceGenerator {

    public static void main(String[] args) {


        try {
            if(args.length < 2) {
                throw new Exception("Entrez les arguments comme suit : java <Main> <n> <maxval>");
            }
            //  initialisation des variables
            int n = Integer.parseInt(args[0]);
            int maxVal = Integer.parseInt(args[1]);
            int[] S = generateInstance(n, maxVal);

            // affichage du tableau initial
            System.out.println("Instance :");
            printArray(S);
            int[] SPrime = generateSolution(S);

            // affichage de la solution
            System.out.println("Solution :");
            printArray(SPrime);
            System.out.println("Vérification : " + Verification(S, SPrime));
            System.out.println("Evaluation : " + evaluate(S, SPrime));

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

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
    public static int evaluate(int[] S, int[] Sprime) {
        int sumS1 = 0;
        int sumS2 = 0;
        for (int i = 0; i < S.length; i++) {
            if (Sprime[i] == 1) { // si S[i] appartient à S1
                sumS1 += S[i];
            } else { // sinon S[i] appartient à S2
                sumS2 += S[i];
            }
        }
        return Math.abs(sumS1 - sumS2);
    }

    // Verifier la solution 
    public static boolean Verification(int[] S, int[] Sprime) {
        if(S.length != Sprime.length) {
            return false;
        }
        for (int i = 0; i < Sprime.length; i++) {
            if (Sprime[i] != 1 && Sprime[i] != 2) {
                return false;
            }
            }
        return true;
        }
    

    //Solution 
    public static int[] generateSolution(int[] S) {
        int n = S.length;
        int[] SPrime = new int[n]; // solution

        // tri des indices de S dans l'ordre décroissant selon les valeurs de S
        Integer[] indices = new Integer[n];
        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }
        //tri les indices du tableau indices selon les valeurs dans S
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
