import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Population {
    int TaillePopu; // taille de la population
    PriorityQueue<Individue> Individus;
    int TailleEchec; // taille de l'échiquier
    static int cpt_add = 0;
    static int cpt_del = 0;

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
