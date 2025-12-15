package mes_class;
import java.util.Collections;

public class GameAI {
    private Board plateau;
    private Snake serpent;
    private int score;

    public GameAI() {
        this.plateau = new Board(16, 46);
        this.serpent = new Snake();
        this.score = 0;
        plateau.addFruit();
    }

    // Stratégie 1 : Toujours aller vers l'ouest (gauche)
    public void jouerStrategie1() {
        System.out.println("=== Stratégie 1 : Toujours vers l'ouest ===\n");
        Collections.reverse(serpent.getCoordinates());
        
        while (true) {
            plateau.addSnake(serpent.getCoordinates());
            plateau.draw(score);

            int decalageLigne = 0;
            int decalageColonne = -1;

            int prochaineLigne = serpent.getHeadX() + decalageLigne;
            int prochaineColonne = serpent.getHeadY() + decalageColonne;
            char caseCible = plateau.get(prochaineLigne, prochaineColonne);
            boolean estQueue = serpent.getTail().getRow() == prochaineLigne && serpent.getTail().getCol() == prochaineColonne;
            
            if (caseCible == '*'
                || (caseCible == 'o' && !estQueue)) {
                System.out.println("Game Over! Score: " + score);
                break;
            } else if (caseCible == 'x') {
                serpent.move(decalageLigne, decalageColonne);
                score++;
                plateau.addFruit();
            } else {
                serpent.move(decalageLigne, decalageColonne);
                serpent.removeQueue();
            }

            pause(300);
        }
    }

    // Stratégie 2 : Aller vers le fruit
    public void jouerStrategie2() {
        System.out.println("=== Stratégie 2 : Vers la nourriture ===\n");
        
        while (true) {
            // 1) Rendu du jeu pour ce tick: place le serpent dans la grille puis affiche
            plateau.addSnake(serpent.getCoordinates());
            plateau.draw(score);

            // 2) Recherche du fruit: on scanne la grille publique pour trouver 'x'
            int fruitLigne = -1, fruitColonne = -1;
            for (int i = 0; i < plateau.grid.length; i++) {
                for (int j = 0; j < plateau.grid[0].length; j++) {
                    if (plateau.grid[i][j] == 'x') {
                        fruitLigne = i;
                        fruitColonne = j;
                    }
                }
            }

            // 3) Direction initiale vers le fruit (vecteur unitaire):
            //    priorité à l'axe vertical (lignes), sinon horizontal (colonnes)
            int teteLigne = serpent.getHeadX();
            int teteColonne = serpent.getHeadY();
            int decalageLigne = 0, decalageColonne = 0;

            if (teteLigne < fruitLigne) {
                decalageLigne = 1;
            } else if (teteLigne > fruitLigne) {
                decalageLigne = -1;
            } else if (teteColonne < fruitColonne) {
                decalageColonne = 1;
            } else if (teteColonne > fruitColonne) {
                decalageColonne = -1;
            }

            // 4) Prépare la case cible et applique l'exception "queue":
            //    entrer sur la queue est autorisé si elle sera retirée ensuite
            int prochaineLigne = teteLigne + decalageLigne;
            int prochaineColonne = teteColonne + decalageColonne;
            char caseCible = plateau.get(prochaineLigne, prochaineColonne);
            boolean estQueue = serpent.getTail().getRow() == prochaineLigne && serpent.getTail().getCol() == prochaineColonne;
            // 5) Si la case directe est dangereuse (mur ou corps hors-queue),
            //    on tente un pas alternatif sûr parmi {haut, bas, droite, gauche}
            if (caseCible == '*'
                || (caseCible == 'o' && !estQueue)) {
                int[][] directions = new int[][] { {-1,0}, {1,0}, {0,1}, {0,-1} };
                boolean found = false;
                for (int[] d : directions) {
                    int nx = teteLigne + d[0];
                    int ny = teteColonne + d[1];
                    char c = plateau.get(nx, ny);
                    boolean queueTouchee = serpent.getTail().getRow() == nx && serpent.getTail().getCol() == ny;
                    if (c != '*' && !(c == 'o' && !queueTouchee)) {
                        decalageLigne = d[0]; decalageColonne = d[1];
                        prochaineLigne = nx; prochaineColonne = ny;
                        caseCible = c;
                        estQueue = queueTouchee;
                        found = true;
                        break;
                    }
                }
                // Aucun pas sûr → fin de partie
                if (!found) {
                    System.out.println("Game Over! Score: " + score);
                    break;
                }
            }
            
            // 6) Application du mouvement selon la case cible:
            //    - mur/corps hors-queue → fin
            //    - fruit → croissance (pas de suppression de queue) et nouveau fruit
            //    - vide → déplacement standard (suppression de queue)
            if (caseCible == '*'
                || (caseCible == 'o' && !estQueue)) {
                System.out.println("Game Over! Score: " + score);
                break;
            } else if (caseCible == 'x') {
                serpent.move(decalageLigne, decalageColonne);
                score++;
                plateau.addFruit();
            } else {
                serpent.move(decalageLigne, decalageColonne);
                serpent.removeQueue();
            }

            // 7) Petite pause pour lisibilité de l'animation
            pause(300);
        }
    }

    // Méthode utilitaire pour faire une pause
    private void pause(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        GameAI jeu = new GameAI();
        jeu.jouerStrategie2();
    }
}
