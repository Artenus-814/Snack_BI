package mes_class;

import java.util.LinkedList;

public class Snake {
    private LinkedList<Coordinates> coordinates = new LinkedList<>();

    public Snake() {
        // Place le serpent horizontalement au centre
        coordinates.add(new Coordinates(2, 5));
        coordinates.add(new Coordinates(2, 6));
        coordinates.add(new Coordinates(2, 7));
        coordinates.add(new Coordinates(2, 8));
        coordinates.add(new Coordinates(2, 9));
        coordinates.add(new Coordinates(2, 10));
        coordinates.add(new Coordinates(2, 11));
        coordinates.add(new Coordinates(2, 12));
    }
    
    public LinkedList<Coordinates> getCoordinates() { 
    	return coordinates; 
    	}
    //Methode getHead
    public Coordinates getHead() { 
    	return coordinates.getLast(); 
    	}
    public Coordinates getTail() { 
        return coordinates.getFirst(); 
        }
  //Methode getHead(Son abscisse)
    public int getHeadX() {
    	return getHead().getRow(); 
    	}
  //Methode getHead(Son Ordonne)
    public int getHeadY() {
    	return getHead().getCol(); 
    	}
    //MEthode deplacement
    public void move(int dx, int dy) {
        Coordinates head = getHead();
        coordinates.addLast(new Coordinates(head.getRow() + dx, head.getCol() + dy));
    }
    //Methode qui supprime queue
    public void removeQueue() { 
    	coordinates.removeFirst(); 
    	}
}
