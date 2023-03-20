import java.util.ArrayList;

public class Noeud implements Comparable<Noeud>{
    int value;
    ArrayList<Integer> children;
    public Noeud(int value, ArrayList<Integer> children){
        this.value = value;
        this.children = children;
    }
    @Override
    public int compareTo(Noeud o) {
        if(this.value < o.value) return -1;
        if(this.value > o.value) return 1;
        return 0;
    }
}
