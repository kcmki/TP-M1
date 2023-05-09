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
        


        double tetaV    = 0.4;
        double c1       = 1.49445f;
        double c2       = 1.49445f;
        int maxIteration = 10000;
        int taillePopu = 100;
        double r1  = 0.2;//Math.random();
        double r2 = 0.2;//Math.random();

        int maxEchec = 15;
        int nbIter = 5;

        try ( FileWriter myWriter = new FileWriter("GA-n.txt");) {
                for (int i = 5 ; i <= maxEchec ; i++) {
                            Individue a = null;
                            float temp = 0;
                            float score = 0;
                            for (int w = 0; w < nbIter; w++) {
                                GenetiqueAlgo algo = new GenetiqueAlgo(taillePopu,i,0.4,0.4);
                                long startTime = System.currentTimeMillis();
                                a = algo.run(maxIteration);
                                long endTime = System.currentTimeMillis();
                                temp += (endTime - startTime);
                                score += a.score;
                            }  
                            String line = i+","+temp/nbIter+","+score/nbIter;
                            System.out.println(line);
                            myWriter.write(line+"\n");  
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
    

    public static void printArray(int[] tab) {
        for (int i = 0; i < tab.length; i++) {
            System.out.print(tab[i] + " ");
        }
        System.out.println();
    }
}
