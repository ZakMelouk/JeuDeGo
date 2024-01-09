package Jeu;

import java.util.Objects;

public class Couleur {
    private enum TypeCouleur {
        WHITE,
        BLACK
    }
    TypeCouleur couleur;

    public static boolean isCouleur(String couleur){
        if(couleur.toUpperCase().compareTo(TypeCouleur.BLACK.toString())!=0 && couleur.toUpperCase().compareTo(TypeCouleur.WHITE.toString())!=0)
            return false;
        else return true;
    }

    public Couleur(String c){
        if(!(isCouleur(c)))
            throw new IllegalArgumentException("invalid color or coordinate");
        if(c.compareTo(TypeCouleur.WHITE.toString())==0) {
            this.couleur = TypeCouleur.WHITE;
        }
        else
            this.couleur = TypeCouleur.BLACK;
    }
    @Override
    public String toString(){
        return couleur.toString();
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Couleur other = (Couleur) obj;
        if(((Couleur) obj).couleur.toString().compareTo(couleur.toString()) == 0)
            return true;
        else
            return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(couleur);
    }
}


