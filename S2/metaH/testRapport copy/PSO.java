import java.util.Arrays;

public class PSO {
    double c1, c2;
    double r1, r2;
    int maxIteration;
    int tailleEchec;
    int taillePopu;
    particule population[];
    double tetaV;
    int gBest[];

    public PSO(double tetaV,double c1, double c2,double r1, double r2, int maxIteration, int tailleEchec, int taillePopu) {
        this.r1 = r1;
        this.r2 = r2;
        this.c1 = c1;
        this.c2 = c2;
        this.maxIteration = maxIteration;
        this.tailleEchec = tailleEchec;
        this.taillePopu = taillePopu;
        this.tetaV = tetaV;
        population = new particule[taillePopu];
    }

    public int [] run() {

        int MaxIndexScore = 0;
        int MaxScore = 100;
        // Initialisation de la population
        for (int i = 0; i < taillePopu; i++) {
            population[i] = new particule(tailleEchec);
            if (population[i].score < MaxScore)
            {
                MaxScore = population[i].score;
                MaxIndexScore = i;
            }
        }
        
        gBest = population[MaxIndexScore].Position.clone();
        
        // Boucle principal
            for (int i = 0; i < maxIteration; i++) // Tant que (Cdt d'arret non verifie)
            {
                for (int j = 0; j < taillePopu ; j++) // pour chaque particule xi
                {
                    population[j].updateVitesse(tetaV, c1, c2, r1, r2, gBest);
                    population[j].updatePosition(); // mise a jour de la position et Pbest
                    population[j] = new particule(localSearch(population[j].Position, 1));
                }
                // Mise a jour de Gbest
                for (int j = 0; j < taillePopu ; j++) {
                    if (population[j].score < MaxScore)
                    {   
                        MaxScore = population[j].score;
                        MaxIndexScore = j;
                        gBest = population[j].Position.clone();
                    }
                }
                
                Individue temp = new Individue(gBest);
                if (temp.score == 0 )
                {
                    break;
                }
                    
            }

            return gBest;

    }

    
public static int[] localSearch(int[] position, int delta) {
    int[] bestPosition = position.clone();
    Individue a = new Individue(position);
    int bestScore = a.score;

    boolean improved = true;
    while (improved) {
        improved = false;
        for (int i = 0; i < position.length; i++) {
            int[] neighbor = position.clone();
            neighbor[i] += delta % position.length;
            a = new Individue(neighbor);
            int neighborScore = a.score;
            if (neighborScore < bestScore) {
                bestPosition = neighbor.clone();
                bestScore = neighborScore;
                improved = true;
            }
        }
        position = bestPosition.clone();
    }

    return bestPosition;
}
}

