package mes_class;

public class Coordinates {
    private int row, col;
    public Coordinates(int row, int col)
    { this.row = row;
    this.col = col; 
    }
    public int getRow() 
    
    { return row; 
    }
    public int getCol() {
    	return col; 
    	}
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Coordinates)) return false;
        Coordinates other = (Coordinates) obj;
        return row == other.row && col == other.col;
    }
}



