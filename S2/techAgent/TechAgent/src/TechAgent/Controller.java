package TechAgent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

public class Controller {
	@FXML
	private AnchorPane CheckBoxes;
	@FXML
	private RadioButton RadioPart1,ChAvant,ChArriere,RadioPart2;
	@FXML
	private Button startButton;
	@FXML
	private TextArea Result;
	@FXML
	private ToggleGroup But;
	
	
	public void start(ActionEvent e) {
			Result.setText("");
			ObservableList<Node> nodes = CheckBoxes.getChildrenUnmodifiable();
	        Map<String, Boolean> baseFait = new HashMap<>();

			for (Node node : nodes) {
			    if (node instanceof CheckBox) {
			        CheckBox checkbox = (CheckBox) node;
			        // Do something with the checkbox
			        // For example, you can set its selected property to true:
			        if(checkbox.isSelected())
			        	 baseFait.put(checkbox.getText(), true);
			    }
			}
			MonMoteur M = new MonMoteur();
			M.Moteur = new MoteurInference(baseFait,M.regles);
			System.out.println(baseFait);
			if(ChAvant.isSelected()) {

				M.Moteur.chainageAvant();
		        Map<String, Boolean> result = M.getMapDifference(baseFait);
		        
		        String Afficher = "";
		        for (Entry<String, Boolean> entry : result.entrySet()) {
		        	String key = (String) entry.getKey();
		        	Afficher += key+"\n";
		        }
		        
		        if(Afficher != "") Result.setText("Chainage avant : \n"+Afficher);
		        else Result.setText("Aucun resultat");
	
				
			}else {
				
		        Toggle selectedToggle = But.getSelectedToggle();
		        String but = "";
		        if (selectedToggle instanceof RadioButton) {
		            RadioButton selectedRadioButton = (RadioButton) selectedToggle;
		            but = selectedRadioButton.getText();
		        }
		        System.out.println("Chainage arriere");
		        if (M.Moteur.chainageArriere(but)) 
		            {
		        		Result.setText("But prouvé par chainage arriere");
		            } else {
		            	Result.setText("But impossible à prouver par chainage arriere");
		            }
			}


	}
	public class MonMoteur{
		public MoteurInference Moteur;
		public List<Regle> regles = new ArrayList<>();
		public MonMoteur() {
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

		}
		
	    public Map<String,Boolean> getMapDifference(Map<String, Boolean> map2) {
	        Map<String, Boolean> difference = new HashMap<>();

	        for (Entry<String, Boolean> entry : Moteur.baseFait.entrySet()) {
	        	String key = (String) entry.getKey();
	        	Boolean value1 = (Boolean) entry.getValue();
	        	Boolean value2 = (Boolean) map2.get(key);

	            if (value2 == null || !value2.equals(value1)) {
	                difference.put(key, value1);
	            }
	        }

	        return difference;
	    }
	}

}

