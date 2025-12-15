package mes_class;
import java.util.LinkedList;

public class TestBoard {
	 public static void main(String[] args) {
	        // La je me declare mes dimensions du plateau
	        final int rows = 16;      // Modifier selon visuel exact
	        final int columns = 46;   // Modifier selon visuel exact

	        // Initialisation du plateau
	        Board board = new Board(rows, columns);

	        // Initialisation en bruteun serpent  : ligne 2, placements en colonne 5 à 12
	        LinkedList<Coordinates> snake = new LinkedList<>();
	        for (int j = 5; j <= 12; j++) {
	            snake.add(new Coordinates(2, j)); // Ligne 2, colonnes 5 à 12
	        }

	        // Placement du serpent
	        board.addSnake(snake);

	        // Placement du fruit (exemple à ligne 5, colonne 10)
	        board.addFruit(); // Ou: board.grid[5][10] = 'x'; (à adapter selon la classe)

	        // Affichage avec score 0
	        board.draw(0);
	    }
	}


