package application;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Stack;

public class Solver {
	public Solution Sol;
	long parcouru=0,cree=0;
	double CR=0.5,MR=0.5,POPU=1000;
	int C1=1,C2=1,R1=1,R2=1,teta=1,taillePopu=10,maxIter=10000;

	
	public Solver(int NombreReines,int algo) {
		//DFS CALCULATION
			ChessTable  sol = null;
			long start = System. currentTimeMillis();
			switch(algo) {
				/*case  0:
					 sol = dfs(NombreReines);
					break;
				case  1:
					sol = bfs(NombreReines);
					break;
				case  2:
					sol = A1(NombreReines);
					break;
				case  3:
					sol = A2(NombreReines);
					break;*/
				case 0:
					sol = ga(POPU,NombreReines,CR,MR,4000);
					break;
				case 1:
					sol = pso(NombreReines,C1,C2,R1,R2,teta,taillePopu,maxIter);
					break;
			}
			long end = System. currentTimeMillis();
		this.Sol = new Solution(sol,end-start,parcouru,cree);
		parcouru = 0;cree=0;
	}
	
	public ChessTable dfs(int NombreReines) {
	    	int min = Integer.MAX_VALUE;
	    	Stack<ChessTable> StateStack = new Stack<ChessTable>();
	    	ChessTable sol = new ChessTable(NombreReines);
	    	ChessTable current;
	    	ChessTable  next;
	    	StateStack.push(sol);
	    	while(!StateStack.empty()) {
	    		current = StateStack.pop();
	    		parcouru++;
	    		if(!current.finished) {
	        		for(int i = 0; i < NombreReines;i++) {
	        			next = new  ChessTable(current);
	        			next.addElement(i);
	        			StateStack.push(next);
	        			cree++;
	        		}
	    		}else {
	    			int CurrentDanger = current.verifyQueens();
	    			if(CurrentDanger < min) {
	    				sol  = current;
	    				min  = CurrentDanger;
	    			}
	    			if(CurrentDanger == 0) {sol  = new ChessTable(current);StateStack.clear();}
	    		}
	    	}
	    	return sol;
	    }

	public ChessTable bfs(int NombreReines) {
	    	int min = Integer.MAX_VALUE;
	    	LinkedList<ChessTable> StateList = new LinkedList<ChessTable>();
	    	ChessTable sol = new ChessTable(NombreReines);
	    	ChessTable current;
	    	ChessTable  next;

	    	StateList.add(sol);
	    	while(!StateList.isEmpty()) {
	    		current = StateList.poll();
	    		parcouru++;
	    		if(!current.finished) {
	        		for(int i = 0; i < NombreReines;i++) {
	        			next = new  ChessTable(current);
	        			next.addElement(i);
	        			StateList.add(next);
	        			cree++;
	        		}
	    		}else {
	    			int CurrentDanger = current.verifyQueens();
	    			if(CurrentDanger < min) {
	    				sol  = current;
	    				min  = CurrentDanger;
	    			}
	    			if(CurrentDanger == 0) {sol  = new ChessTable(current);StateList.clear();}
	    		}
	    	}
	    	return sol;
	    }

	public ChessTable A1(int NombreReines) {
  
    	PriorityQueue<ChessTable> StateList = new PriorityQueue<ChessTable>(new Comparator<ChessTable>() {

			@Override
			public int compare(ChessTable o1, ChessTable o2) {
				int x = o1.verifyQueensWithWeight();
				int y = o2.verifyQueensWithWeight();
				if(x > y)  return 1;
				if(x < y)  return -1;
				if( o1.index > o2.index ) return -1;
				if (o1.index < o2.index ) return 1;
				return 1;
			}});
    	
    	
    	ChessTable sol = new ChessTable(NombreReines);
    	ChessTable current;
    	ChessTable  next;

    	StateList.add(sol);
    	while(!StateList.isEmpty()) {
    		current = StateList.poll();
    		parcouru++;
    		if(!current.finished) {
        		for(int i = 0; i < NombreReines;i++) {
        			next = new  ChessTable(current);
        			next.addElement(i);
        			StateList.add(next);
        			cree++;
        		}
    		}else {
    			if(current.verifyQueens() == 0) {sol  = new ChessTable(current);StateList.clear();}
    		}
    	}
    	return sol;
	}
	public ChessTable A2(int NombreReines) {
		
		// modifier pour que la case choisie soit celle ou la distance est de N+1/2
		
		PriorityQueue<ChessTable> StateList = new PriorityQueue<ChessTable>(new Comparator<ChessTable>() {

			@Override
			public int compare(ChessTable o1, ChessTable o2) {
				int dist1 = o1.distance();
				
				int dist2 = o2.distance();
				
				if(dist1 > dist2)  return -1;
				if(dist1 < dist2)  return 1;
				if( o1.index > o2.index ) return -1;
				if (o1.index < o2.index ) return 1;
				return 1;
			}});
    	
    	ChessTable sol = new ChessTable(NombreReines);
    	ChessTable current;
    	ChessTable  next;

    	StateList.add(sol);
    	while(!StateList.isEmpty()) {
    		current = StateList.poll();
    		parcouru++;
    		if(!current.finished) {
        		for(int i = 0; i < NombreReines;i++) {
        			next = new  ChessTable(current);
        			next.addElement(i);
        			StateList.add(next);
        			cree++;
        		}
    		}else {
    			if(current.verifyQueens() == 0) {sol  = new ChessTable(current);StateList.clear();}
    		}
    	}
    	return sol;
	}
	public ChessTable ga(double popu,int NombreReines,double CR,double MR,int maxiter) {
		GA g = new GA((int)popu, NombreReines, CR, MR);
		return new ChessTable(NombreReines,g.run((int)maxiter));
	}
	public ChessTable pso(int NombreReines,int C1,int C2,int R1,int R2,int teta,int taillePopu,int maxIt) {
		
		PSO p = new PSO((int)teta,C1,C2,R1,R2,maxIt,NombreReines,taillePopu);
		return new ChessTable(NombreReines,p.run());
	}
	
	public class Population {
	    int TaillePopu; // taille de la population
	    PriorityQueue<Individue> Individus;
	    int TailleEchec; // taille de l'échiquier
	     int cpt_add = 0;
	     int cpt_del = 0;

	    // Constructeur
	    public Population(int TaillePopu, int TailleEchec) {
	        Comparator<Individue> scoreComparator = new Comparator<Individue>() {
	            @Override
	            public int compare(Individue i1, Individue i2) {
	                return Integer.compare(i2.getScore(),i1.getScore());
	            }
	        };
	        Individus = new PriorityQueue<Individue>(scoreComparator);
	        this.TaillePopu = TaillePopu;
	        this.TailleEchec = TailleEchec;
	        // Initialise une population
	        for (int i = 0; i < TaillePopu; i++) {
	            Individus.add(new Individue(TailleEchec));
	        }
	    }

	    public void afficherPopulation() {
	        Individue[] elems = Individus.toArray(new Individue[0]);
	        Arrays.sort(elems, new Comparator<Individue>() {
	            @Override
	            public int compare(Individue i1, Individue i2) {
	                return Integer.compare(i1.getScore(), i2.getScore());
	            }
	        });
	        for (int i = 0; i < elems.length; i++) {
	            System.out.println(elems[i]);
	        }
	        System.out.println("Nombre d'individu : " + Individus.size());
	    }
	    

	    // selection de la population

	    public Individue getIndividuNum(int num) {
	        // Check if the given index is within the bounds of the population size
	        if (num < 0 || num >= Individus.size()) {
	            throw new IndexOutOfBoundsException("Index " + num + " is out of bounds for the population");
	        }
	        // Convert PriorityQueue to array and sort it based on the score of each individual
	        Individue[] elems = Individus.toArray(new Individue[0]);
	        Arrays.sort(elems, new Comparator<Individue>() {
	            @Override
	            public int compare(Individue i1, Individue i2) {
	                return Integer.compare(i2.getScore(), i1.getScore());
	            }
	        });
	        // Return the individual at the given index
	        return elems[num];
	    }
	    

	    public Individue getIndividuAleatoire() {
	        // Récupérer un indice aléatoire dans la taille de la population
	        int randIndex = (int) (Math.random() * TaillePopu);
	        // Récupérer l'individu correspondant à cet indice dans la TreeMap
	        Object[] elems = Individus.toArray();
	        return (Individue) elems[randIndex];
	    }

	    public void addIndividue(Individue individu) {

	        Individus.add(individu);

	        // Maintenir la taille de la population en supprimant le pire individu
	        Individus.poll(); // supp le pire element
	    }

	    public String toString() {
	        String s = "";
	        Object[] elems = Individus.toArray();
	        for (int i = 0; i < elems.length; i++) {
	            s += elems[i] + "";
	        }
	        return s;
	    }

	    public void addElement(Individue i) {
	        //System.out.println("Ajout de l'individu " + i + " à la population");
	        Individus.add(i);
	    }

	    public void removeLastElement() {
	        //System.out.println("Suppression de l'individu " + Individus.peek() + " de la population");
	        Individus.poll();
	    }

	}
public class Individue {
    int n ; // taille de lechequier
    int[] Position;
    int score;
    
    
    public Individue(int[] n) {
        this.Position = n.clone();
        this.n = n.length;
        score = fitnessFct();

    }
    public Individue(int n) 
    {   this.Position = new int[n];
        this.n = n;
            // Initialise une population
            for (int i = 0; i < n; i++) 
            {   
                Position[i] = (int) (Math.random() * n);  
            }
            score = fitnessFct();
    }

    public void initIndividue ()
    {
        for (int i = 0; i < n; i++) 
        {   
                Position[i] = -1;  
        }
        System.out.println("Solution initialisé : "+Arrays.toString(Position));
    }

    
    public  int fitnessFct() {
    	int fit = 0;
		for(int i = 0;i < this.n;i++) {
			
			//on vérifie les colonne vu que les lignes sont surement juste
			for(int j= 0; j<this.n;j++) {
				if(Position[i] == Position[j] && i != j && Position[i] != -1) fit++;
			}

			
			//vérification des diagonale
			fit +=verifyDiag(i,this.Position[i]);
		}
		return fit;
	}
	
	private int verifyDiag(int i, int j) {

		//on vérifie les quatre diagonale de l'element situé a [i,j]
		//Nord ouest
		int fit = 0;
		int tempI = i,tempJ = j;
		while(tempI >= 0 && tempJ >= 0) {
			if(this.Position[tempI] == tempJ && tempJ  !=j && tempI != i && tempJ != -1) fit++;
			tempI--;
			tempJ--;
		}
		//Sud est
		tempI = i;
		tempJ = j;
		while(tempI < this.n && tempJ < this.n) {
			if(this.Position[tempI] == tempJ && tempJ  !=j && tempI != i && tempJ != -1) fit++;
			tempI++;
			tempJ++;
		}
		// nord est
		tempI = i;
		tempJ = j;
		while(tempI >= 0 && tempJ < this.n) {
			if(this.Position[tempI] == tempJ && tempJ  !=j && tempI != i && tempJ != -1) fit++;
			tempI--;
			tempJ++;
		}
		//sud ouest
		tempI = i;
		tempJ = j;
		while(tempI < this.n && tempJ >= 0) {
			if(this.Position[tempI] == tempJ && tempJ  !=j && tempI != i && tempJ != -1) fit++;
			tempI++;
			tempJ--;
		}
		return fit;
	}

    
    // Verification

    public boolean isValide ()
    {   
        System.out.println("Verification de la solution : "+Arrays.toString(Position));
        if ( verifierHorizontal()==0 && verifierVertical()==0 && verifierDiagonale()==0)
            return true;
        else
            return false;
    }
    
   

    public int verifierHorizontal ()
    {   int cpt = 0;
        for (int i = 0; i < n; i++) 
        {
            for (int j = 0; j < n; j++) 
            {
                if (i != j && Position[i] == Position[j])
                    cpt++;
            }
        }
        return cpt;
    }

    public int verifierVertical () 
    {   int cpt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (Position[i] == Position[j])
                    cpt++;
            }
        }
        return cpt;
    }
    
    public int verifierDiagonale ()     
    {    // vérifier la diagonale en haut à gauche
        int cpt = 0;
    for (int i = 0; i < n; i++) 
    {
        for (int j = i + 1; j < n; j++) 
        {
            if (Position[i] == Position[j] - (j - i) || Position[i] == Position[j] + (j - i))
                cpt++;
        }
    }
    
    // vérifier la diagonale en bas à gauche
    for (int i = 0; i < n; i++) 
    {
        for (int j = i + 1; j < n; j++) 
        {
            if (Position[i] == Position[j] - (i - j) || Position[i] == Position[j] + (i - j))
                cpt++;
        }
    }
    
    return cpt;
    }
    

    
    
        

        // Constructeur

    public int[] getPosition() {
        return Position;
    }

    public void setPosition(int[] position) {
        Position = position;
    }
    
    public String toString() {
        String s = Arrays.toString(Position) + "Fitness fct :  " + fitnessFct();
        s+=                            "\tScore :  " + score;

        return s;

    }

    public void mutation (int numPosition, int NouvelleValeur)
    {
        Position[numPosition] = NouvelleValeur;
        this.score = fitnessFct();
    }

    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
}

public class particule extends Individue{

   
    int x[];
    int v[];
    int pBest[];

    public particule (int[] n )
    {
        super(n);
        this.x=Position.clone();
        this.pBest=Position.clone();
        this.v = new int[n.length];
        // definir v0
        double vMin = 0.1;
        double vMax = 1;
        Random rand = new Random();
        for (int i=0;i<n.length;i++)
        {
            double randomDouble = vMin + (vMax - vMin) * rand.nextDouble();
            v[i] =  (int) Math.round(randomDouble);
        }

    }

    
    public particule(int n) {
        super(n);
        this.x=Position.clone();
        this.pBest=Position.clone();
        this.v = new int[n];
        // definir v0
        double vMin = 0.1;
        double vMax = 1;
        Random rand = new Random();
        for (int i=0;i<n;i++)
        {
            double randomDouble = vMin + (vMax - vMin) * rand.nextDouble();
            v[i] =  (int) Math.round(randomDouble);
        }
     
    }

    

    public void updateVitesse(double tetaV, double c1, double c2, double r1, double r2, int[] gBest) {
        int[] temp_tab = addTableau(multiplTableau(tetaV, v), multiplTableau(c1 * r1, MinusTableau(pBest, x)));
        v = addTableau(temp_tab, multiplTableau(c2 * r2, MinusTableau(gBest, x)));
    }
    
    public void updatePosition() {
        x = addTableau(x, v);
        Position = x.clone();
        score = fitnessFct();
        
        Individue theBest = new Individue(pBest);

        if (score > theBest.fitnessFct())
        {
            pBest = x.clone();
        }
    }

    public int[] MinusTableau(int[] tab1, int[] tab2) {
        int[] result = new int[tab1.length];
        for (int i = 0; i < tab1.length; i++) {
            result[i] = (tab1[i] - tab2[i] + n) % n;
        }
        return result;
    }
    
    public  int[] multiplTableau(double n, int[] arr) {
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i] = (int) (n * arr[i]);
            result[i] = result[i] % arr.length;
        }
        return result;
    }
    
    
    
    public  int[] addTableau(int[] t1, int[] t2) {
        int[] result = new int[t1.length];
        for (int i = 0; i < t1.length; i++) {
            result[i] = (t1[i] + t2[i]) % t1.length;
        }
        return result;
    }
    
    
    
    
}

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

                System.out.print("La meilleure initial est : ");
                System.out.print(population[i]);
                System.out.println(" avec un score de : " + population[i].score);
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
                        System.out.println("Meilleur score de la "+j+" etiration: "+population[j].score);
                        System.out.println("La meilleure solution  de literation est : "+population[j]);
                        MaxScore = population[j].score;
                        MaxIndexScore = j;
                        gBest = population[j].Position.clone();
                        System.out.println("Gbest : "+Arrays.toString(gBest));
                    }
                }
                
                Individue temp = new Individue(gBest);
                if (temp.score == 0 )
                {
                    System.out.println("Meilleur solution trouvé");
                    break;

                }
                    
            }

            System.out.print("La meilleure solution est : ");
            System.out.print(Arrays.toString(gBest));
            System.out.println(" avec un score de : " + MaxScore);
            return gBest;

    }

    public int[] localSearch(int[] position, int delta) {
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
	
public class GA{   
	    int tailleEchec;
	    int taillePopu;
	    Population pop;
	    double TauxCroisement;
	    double TauxMutation;
	    public GA(int taillePopu, int tailleEchec,double TauxCroisement,double TauxMutation)
	    {
	        // Initialisation de la population
	        this.tailleEchec = tailleEchec;
	        this.taillePopu = taillePopu;
	        this.TauxCroisement = TauxCroisement;
	        this.TauxMutation = TauxMutation;
	        pop = new Population(taillePopu,tailleEchec);
	    }

	    public int[] run (int maxIteration)
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
	        System.out.println("Meilleure individue :"+pop.Individus.peek());
	        return pop.Individus.poll().Position;
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

}
