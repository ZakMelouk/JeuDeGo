package Jeu;
import java.util.Objects;

public class Coord {
    private int x;
    private int y;

    public Coord(int x_, int y_){

        x=x_;
        y=y_;
    }
    public String toString() {

        char a = (char) ('A'+x);
        int b = y+1;
        return a+""+b;
    }

    public boolean compareTo(Coord a){
        if(a.x == this.x && a.y == this.y)
            return true;
        else
            return false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Coord other = (Coord) obj;
        return x == other.x && y == other.y;
    }

}
