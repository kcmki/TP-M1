package TechAgent;
import java.util.*;

    /* C'est la classe qui represente la base de regles. Elle comporte deux variables representent respectivement la 
     * liste des premisses de la regle et sa conclusion. Les premisses sont une liste de chaînes de caracteres 
     * et la conclusion est une chaîne de caracteres.  */
	class Regle {
        
        private List<String> premisses;
        private String conclusion;
        private boolean active;

        public Regle(List<String> premises, String conclusion) {
            this.premisses = premises;
            this.conclusion = conclusion;
            this.active = true;
        }
        
        public List<String> getPremises() {
            return premisses;
        }
        
        public String getConclusion() {
            return conclusion;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Si ");
            for (int i = 0; i < premisses.size(); i++) {
                sb.append(premisses.get(i));
                if (i < premisses.size() - 1) {
                    sb.append(" ET ");
                }
            }
            sb.append(", alors ");
            sb.append(conclusion);
            sb.append(".");
            return sb.toString();
        }

        public boolean isActive() {
            return active;
        }
        
        public void desactiver() {
            this.active = false;
        }
    }

/* "MoteurInference" est la classe principale contenant la methode de chainage avant */
public class MoteurInference {
    
    public Map<String, Boolean> baseFait;
    private List<Regle> regles;
    
    /* Le constructeur de la classe "MoteurInference". Il prend en entree deux arguments à savoir "baseFait" qui contient les faits 
     * initiaux et "regles" qui contient l'ensemble des listes     */
    
    public MoteurInference(Map<String, Boolean> baseFait, List<Regle> regles) {
        this.baseFait = new HashMap<>(baseFait);
        this.regles = regles;
    }
    /*  La methode principale "chainage avant"   */
    public void chainageAvant() {
        boolean nouveauxFaits = true;
        while (nouveauxFaits) {
            nouveauxFaits = false;
            for (Regle regle : regles) {
                if (regle.isActive()) {
                    if (toutesLesPremissesSontVraies(regle)) {
                        String conclusion = regle.getConclusion();
                        if (!baseFait.containsKey(conclusion)) {
                            baseFait.put(conclusion, true);
                            nouveauxFaits = true;
                        }
                        regle.desactiver(); // On désactive la règle après son utilisation
                        System.out.println("On utilise la règle : " + regle);
                    }
                }
            }
        }
    }
    
    
    




    public boolean chainageArriere(String but) {
    System.out.println("Recherche de " + but + " en utilisant le chaînage arrière...");
    if (this.baseFait.containsKey(but)) {
        System.out.println(but + " est déjà un fait !");
        return true;
    } else {
        for (Regle regle : this.regles) {
            if (regle.getConclusion().equals(but)) {
                System.out.println("On utilise la règle : " + regle);
                boolean estSatisfait = true;
                for (String premisses : regle.getPremises()) {
                    estSatisfait = estSatisfait && chainageArriere(premisses);
                }
                if (estSatisfait) {
                    System.out.println("On peut ajouter le fait " + but);
                    this.baseFait.put(but, false);
                    return true;
                }
            }
        }
    }
    System.out.println("Impossible de prouver " + but);
    return false;
}

    /* Cette methode permet de verifier si toutes les premisses d'une regle sont vraies en parcourant la liste des premisses, 
     * en les comparant avec les faits dans baseFait et en renvoyant false 
     * si une premisses est fausse et true si toutes les premisses sont vraies.  */
    private boolean toutesLesPremissesSontVraies(Regle regle) {
        for (String premisses : regle.getPremises()) {
            if (!baseFait.containsKey(premisses) || !baseFait.get(premisses)) {
                return false;
            }
        }
        return true;
    }
    
    public static void main(String[] args) {
        

        // initialiser la base de faits avec des faits initiaux
        Map<String, Boolean> baseFait = new HashMap<>();
        baseFait.put("fievre elevee", true);
        baseFait.put("douleur de tete", true);
        baseFait.put("douleur musculaire", true);
        // Definir but
        String but = "grippe";
        
        // definir quelques regles
        List<Regle> regles = new ArrayList<>();

        regles.add(new Regle(Arrays.asList("fievre elevee", "douleur dans la poitrine"), "pneumonie"));
        regles.add(new Regle(Arrays.asList("fievre elevee", "douleur de tete", "douleur musculaire"), "grippe"));
        regles.add(new Regle(Arrays.asList("douleur dans la poitrine", "essoufflement", "fatigue"), "suspicion d'insuffisance cardiaque"));
        regles.add(new Regle(Arrays.asList("douleur dans l'abdomen", "nausees", "vomissements"), "gastro-enterite"));
        regles.add(new Regle(Arrays.asList("eruption cutanee", "demangeaisons", "fievre"), "varicelle"));
        regles.add(new Regle(Arrays.asList("perte de poids", "fatigue", "douleur dans l'abdomen"), "cancer du pancreas"));
        regles.add(new Regle(Arrays.asList("douleur dans le bas du dos", "fievre", "mictions frequentes"), "suspicion d'infection urinaire"));
        regles.add(new Regle(Arrays.asList("douleur dans la poitrine", "transpiration excessive", "essoufflement"), "suspicion d'infarctus du myocarde"));
        regles.add(new Regle(Arrays.asList("douleur dans les articulations", "gonflement", "rougeur"), "suspicion d'arthrite"));
        regles.add(new Regle(Arrays.asList("douleur abdominale", "ballonnements", "constipation"), "syndrome du colon irritable"));
        regles.add(new Regle(Arrays.asList("eruption cutanee", "fievre", "douleur abdominale"), "zona"));
        regles.add(new Regle(Arrays.asList("douleur au cou", "maux de tete", "fatigue"), "migraine"));
        regles.add(new Regle(Arrays.asList("douleur dans la poitrine", "toux", "essoufflement"), "bronchite"));
        regles.add(new Regle(Arrays.asList("douleur dans la poitrine", "essoufflement", "palpitations"), "trouble du rythme cardiaque"));
        regles.add(new Regle(Arrays.asList("douleur de tete", "nausees", "vomissements"), "migraine avec aura"));       
                        
        // creer le moteur d'inference et l'executer sur la base de faits
        MoteurInference moteur = new MoteurInference(baseFait, regles);
        /* 
        System.out.println("Chainage arriere");
        if (moteur.chainageArriere(but)) 
            {
                System.out.println("But prouvé par chainage arriere");
            } else {
                System.out.println("But impossible à prouver par chainage arriere");
            }
        
        System.out.println("**************************************");
        */
        
        moteur.chainageAvant();
        System.out.println("Chainage Avant");
        System.out.println(moteur.baseFait);
        boolean estVrai = moteur.baseFait.containsKey(but) ? moteur.baseFait.get(but) : false;
        System.out.println(but + " est " + estVrai);
        
        
        
    }
}

