package mes_class;
import java.util.Random;

import java.util.LinkedList;

public class Board {
    private int rows;
    private int columns;
    public char[][] grid;
    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        grid = new char[rows][columns];

        // Initialisation du plateau vide
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                grid[i][j] = ' ';
            }
        }

        // Ajout des murs sur les bords
        for (int i = 0; i < rows; i++) {
            grid[i][0] = '*';                 // mur gauche
            grid[i][columns - 1] = '*';       // mur droite
        }
        for (int j = 0; j < columns; j++) {
            grid[0][j] = '*';                 // mur haut
            grid[rows - 1][j] = '*';          // mur bas
        }
    }

    // Affiche la grille (avec titre et score fictif)
    public void draw(int score) {
        clearScreen();
        System.out.println("Snake         Score: " + score);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(grid[i][j]);
            }
            System.out.println();
        }
    }

    // Retourne le contenu de la case (x, y)
    public char get(int x, int y) {
        return grid[x][y];
    }

    // Ajoute un fruit dans une case vide aléatoire (hors murs)
    public void addFruit() {
        Random rand = new Random();
        int x, y;
        do {
            x = rand.nextInt(rows - 2) + 1;    // évite les murs
            y = rand.nextInt(columns - 2) + 1; // évite les murs
        } while (grid[x][y] != ' ');
        grid[x][y] = 'x';
    }
    //NEttoyace de l'ecran
    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    // Dessine le serpent sur la grille (remise à zéro sauf murs/fruits) format liste chainée 
    /// Autre methode
    public void addSnake(LinkedList<Coordinates> snakeCoords) {
        // Vider le centre de la grille (hors murs/fruits)
        for (int i = 1; i < rows - 1; i++) {
            for (int j = 1; j < columns - 1; j++) {
                if (grid[i][j] != 'x') grid[i][j] = ' ';
            }
        }
        // Dessiner le serpent
        for (Coordinates c : snakeCoords) {
            if (c.equals(snakeCoords.getLast())) {
                grid[c.getRow()][c.getCol()] = '@'; // tête représentée par '@' (selon visuel fourni)
            } else {
                grid[c.getRow()][c.getCol()] = 'o';
            }
        }
    }
}
