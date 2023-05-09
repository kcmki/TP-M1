import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        /*
        //Algo genetique 
        int maxIteration = 10000;
        int tailleEchec = 50;
        int taillePopu = 300;
        double TauxCroisement = 0.4;
        double TauxMutation = 0.4;

        System.out.println("Size "+ tailleEchec);
        // Affichage de la population
        GenetiqueAlgo algo = new GenetiqueAlgo(taillePopu,tailleEchec,TauxCroisement,TauxMutation);

        algo.run(maxIteration);
         */

        // Algo PSO
        


        //double tetaV    = 0.4;
        //double c1       = 1.49445f;
        //double c2       = 1.49445f;
        //int maxIteration = 10000;
        //int taillePopu = 100;
        //double r1  = 0.2;//Math.random();
        //double r2 = 0.2;//Math.random();

        float nbBench = 5;
        float nbIter = 3;
        int tailleEchec = 10;

        int minmaxIteration = 1000;
        int maxmaxIteration = 5000;

        int minTaillepopu = 50;
        int maxTaillepopu = 100;

        try ( FileWriter myWriter = new FileWriter("GA-10-5-3.txt");) {
            for (int i = 0 ; i <= nbBench ; i++) {
                for (int j = 0; j <= nbBench; j++) {
                    for (int maxIteration = minmaxIteration; maxIteration <= maxmaxIteration; maxIteration+=((maxmaxIteration-minmaxIteration)/nbBench)) {
                        for (int taillePopu = minTaillepopu; taillePopu <= maxTaillepopu; taillePopu+=((maxTaillepopu-minTaillepopu)/nbBench)) {
                            Individue a = null;
                            float temp = 0;
                            float score = 0;
                            for (int w = 0; w < nbIter; w++) {
                                GenetiqueAlgo algo = new GenetiqueAlgo(taillePopu,tailleEchec,(float)(i/nbBench),(float)(j/nbBench));
                                long startTime = System.currentTimeMillis();
                                a = algo.run(maxIteration);
                                long endTime = System.currentTimeMillis();
                                temp += (endTime - startTime);
                                score += a.score;
                            }  
                            String line = tailleEchec+","+taillePopu+","+maxIteration+","+(float)(i/nbBench)+","+(float)(j/nbBench)+","+temp/nbIter+","+score/nbIter;
                            myWriter.write(line+"\n");  
                        }
                    }
                }
            }
        }
    
        

    
/*                 Individue a = null;
                float temp = 0;
                float score = 0;
                for (int i = 0; i < nbIter; i++) {
                    PSO algo = new PSO(tetaV,c1,c2,r1,r2,maxIteration,tailleEchec,taillePopu);
                    long startTime = System.currentTimeMillis();
                    int[] tab = algo.run();
                    long endTime = System.currentTimeMillis();
                    a = new Individue(tab);
                    temp += (endTime - startTime);
                    score += a.score;
                }
                String line = tailleEchec+","+taillePopu+","+maxIteration+","+tetaV+","+c1+","+c2+","+temp/nbIter+","+score/nbIter;
                System.out.println(tailleEchec+","+taillePopu+","+maxIteration+","+tetaV+","+c1+","+c2+","+temp/nbIter+","+score/nbIter);
                myWriter.write(line+"\n");  */
    }

    public static void printArray(int[] tab) {
        for (int i = 0; i < tab.length; i++) {
            System.out.print(tab[i] + " ");
        }
        System.out.println();
    }
}
