package mes_class;
import java.io.*;

public class ScoreManager {
    private static final String FICHIER = "scores.txt";

    public static void sauvegarderScore(String nom, int score) {
        try (FileWriter fw = new FileWriter(FICHIER, true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(nom + " : " + score + "\n");
        } catch (IOException e) {
            System.out.println("Erreur: " + e.getMessage());
        }
    }

    public static void afficherScores() {
        try (BufferedReader br = new BufferedReader(new FileReader(FICHIER))) {
            String ligne;
            System.out.println("\n=== SCORES ===");
            while ((ligne = br.readLine()) != null) {
                System.out.println(ligne);
            }
        } catch (IOException e) {
            System.out.println("Aucun score sauvegardé");
        }
    }
}
