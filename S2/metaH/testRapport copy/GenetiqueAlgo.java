import java.util.Random;

public class GenetiqueAlgo
{   
    int tailleEchec;
    int taillePopu;
    Population pop;
    double TauxCroisement;
    double TauxMutation;
    public GenetiqueAlgo (int taillePopu, int tailleEchec,double TauxCroisement,double TauxMutation)
    {
        // Initialisation de la population
        this.tailleEchec = tailleEchec;
        this.taillePopu = taillePopu;
        this.TauxCroisement = TauxCroisement;
        this.TauxMutation = TauxMutation;
        pop = new Population(taillePopu,tailleEchec);
    }

    public Individue run (int maxIteration)
    {   
        for (int i = 0; i < maxIteration; i++)
        {   
            
            // Selection
            Individue[] parents = selection();
            
            // Croisement
            
            Individue[] enfant = croisementPopulation(TauxCroisement, parents);
            
            // Mutation 
            
            mutationPopulation(TauxMutation, enfant);
            
            // Reselection
            for (int j = 0; j < enfant.length-1; j++)
            {
                Individue temp = enfant[j];
                pop.addElement(temp);
                pop.removeLastElement();
                //Maintient de la taille de la population
            }
            
            if(pop.Individus.peek().score == 0)
                break;
        }   

        return pop.Individus.peek();
    }

    public Individue[] selection ()
    {
        // Selection la moitie meilleurs individus
        Individue[] parents = new Individue[taillePopu/2];
        int j = 0;
        for (int i=taillePopu/2; i<taillePopu; i++)
        {
            parents[j] = pop.getIndividuNum(i);
            j++;
        }
        return parents;    
    }

    public Individue[] croisementPopulation(double TauxCroisement, Individue[] parents) {
        Random rand = new Random();
        Individue[] enfants = new Individue[parents.length];
    
        for (int i = 0; i < parents.length -1; i ++) {
                //System.out.println("***************************");
                //System.out.println("Parent 1 : "+parents[i].toString());
                //System.out.println("Parent 2 : "+parents[i+1].toString());
            if (rand.nextDouble() < TauxCroisement) {
                // Choisir un point de croisement aléatoire
                int pointCroisement = rand.nextInt(tailleEchec);
                // Créer les deux enfants en croisant les parents
                int[] enfant1 = new int[tailleEchec];
                int[] enfant2 = new int[tailleEchec];
                
    
                for (int j = 0; j < tailleEchec; j++) {
                    if (j < pointCroisement) {
                            enfant1[j] = parents[i].Position[j];
                            enfant2[j] = parents[i + 1].Position[j];
                    } else {
                            enfant1[j] = parents[i + 1].Position[j];
                            enfant2[j] = parents[i].Position[j];
                    }
                }
                
                // Ajouter les enfants à la liste
                enfants[i] = new Individue(enfant1);
                enfants[i + 1] = new Individue(enfant2);
                //System.out.println("Croisement");
            } else {
                // Si pas de croisement, les enfants sont les mêmes que les parents
                enfants[i] = parents[i];
                enfants[i + 1] = parents[i + 1];
                //System.out.println("Pas de croisement");
            }
            //System.out.println("iteration : "+i);
            //System.out.println("Enfant 1 : "+enfants[i].toString());
            //System.out.println("Enfant 2 : "+enfants[i+1].toString());
        }
    
        return enfants ;
    }
    



    public Individue croisementCouple(Individue parent1, Individue parent2) {
        int[] positionsEnfant = new int[tailleEchec];
        
        // Point de croisement aléatoire
        int pointCroisement = (int) (Math.random() * (tailleEchec - 1)) + 1;
        
        // Récupération des positions des parents
        int[] positionsP1 = parent1.Position;
        int[] positionsP2 = parent2.Position;
        
        // Création des positions de l'enfant
        for (int i = 0; i < pointCroisement; i++) {
            positionsEnfant[i] = positionsP1[i];
        }
        for (int i = pointCroisement; i < tailleEchec; i++) {
            positionsEnfant[i] = positionsP2[i];
        }
        
        return new Individue(positionsEnfant);
    }
    
    //mutation
    public void mutationPopulation (double TauxMutation, Individue[] enfants)
    {
        Random rand = new Random();
        
        for (int i = 0; i < enfants.length-1; i++)
        {   
            //System.out.println("Individue numero : "+i+ " Avant mutation : "+enfants[i].toString());
            if (rand.nextDouble() < TauxMutation)
            {   //System.out.println("Mutation");
                // Choisir un point de mutation aléatoire
                int pointMutation = rand.nextInt(tailleEchec);
                // Choisir une nouvelle valeur aléatoire
                int nouvelleValeur = rand.nextInt(tailleEchec);
                // Modifier la valeur
                enfants[i].mutation(pointMutation, nouvelleValeur);
            }
            /* 
            else 
            {
                System.out.println("Pas de mutation");
            }
            System.out.println("Individue numero : "+i+ " apres mutation : "+enfants[i].toString());
            System.out.println("****************************");
            */
        }
    }

}