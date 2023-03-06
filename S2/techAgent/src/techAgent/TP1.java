package techAgent;
import java.util.Scanner;


public class TP1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("bienvenue to ze Expert Système!");

        // Ask the user a series of questions
        System.out.println("As-tu de la fever? (oui/non)");
        String hasFever = scanner.nextLine();

        System.out.println("Avez-vous mal à la head? (oui/non)");
        String hasHeadache = scanner.nextLine();

        System.out.println("Avez-vous une toux? (oui/non)");
        String hasCough = scanner.nextLine();

        // Provide a diagnonsis based on the user's responses
        if (hasFever.equals("oui") && hasHeadache.equals("oui") && hasCough.equals("oui")) {
            System.out.println("Vous avez peut-être la grippe.");
        } else if (hasFever.equals("n") && hasHeadache.equals("oui") && hasCough.equals("oui")) {
            System.out.println("Vous pouvez avoir un rhume.");
        } else {
            System.out.println("Désolé, aucune idée.");
        }

    }

}