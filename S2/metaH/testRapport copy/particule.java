import java.util.Random;

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
    
    public static int[] multiplTableau(double n, int[] arr) {
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i] = (int) (n * arr[i]);
            result[i] = result[i] % arr.length;
        }
        return result;
    }
    
    
    
    public static int[] addTableau(int[] t1, int[] t2) {
        int[] result = new int[t1.length];
        for (int i = 0; i < t1.length; i++) {
            result[i] = (t1[i] + t2[i]) % t1.length;
        }
        return result;
    }
    
    
    
    
}
