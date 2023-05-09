import java.util.Arrays;

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

    
    public int fitnessFct() {
        int nb = 0;
        for (int i = 0; i < n-1; i++) {
            for (int j = i+1; j < n; j++) {
                if (Position[i] == Position[j] || Math.abs(Position[i] - Position[j]) == j-i) 
                {
                    nb++;
                }
            }
        }
        return nb;
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


