package mes_class;


import java.util.Scanner;

public class Games {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Board board = new Board(16, 46);
        Snake snake = new Snake();
        int score = 0;
        board.addFruit();

        while (true) { 
        	
            board.addSnake(snake.getCoordinates());
            board.draw(score);
            
            
            //J'ai adpater les touches de jeu de sorte quil soit commode pour moi lors du t
            char c = sc.next().toLowerCase().charAt(0);
            if (c == 't') break;

            int dx = 0, dy = 0;
            switch (c) {
                case 's': dx = 1; break;
                case 'z': dx = -1; break;
                case 'd': dy = 1; break;
                case 'q': dy = -1; break;
                default:
                dx = 0;
                dy = 0;
            }
            if (dx == 0 && dy == 0) {
                continue;
            }
            int nextX = snake.getHeadX() + dx;
            int nextY = snake.getHeadY() + dy;
            char cell = board.get(nextX, nextY);
            boolean isTail = snake.getTail().getRow() == nextX && snake.getTail().getCol() == nextY;
            if (cell == 'x') {
                snake.move(dx, dy);
                score++;
                board.addFruit();
            } else if (cell == '*' || (cell == 'o' && !isTail)) {
                System.out.println("Fin de partie!");
                break;
            } else {
                snake.move(dx, dy);
                snake.removeQueue();
            }
        }
        System.out.println("Score final: " + score);
        System.out.print("Votre nom: ");
        String nom = sc.next();
        ScoreManager.sauvegarderScore(nom, score);
        ScoreManager.afficherScores();
        sc.close();
    }
}
