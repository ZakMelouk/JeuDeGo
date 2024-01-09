package Jeu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Pierre {


    public String getCouleur(){
        return couleur.toString();
    }

    //AJOUT DE LA STRUCTURE DE DONNEES COORD
    private static final Coord[] voisins = {
            new Coord(1, 0),
            new Coord(0, 1),
            new Coord(-1, 0),
            new Coord(0, -1)
    };
    public Coord coord;
    private Couleur couleur;

    public List<Pierre> findVoisins(HashMap<Coord,Pierre> tableau){
        List<Pierre> voisinsList = new ArrayList<>();
        for(Coord c : voisins){
            int newX = coord.getX() + c.getY();

            int newY = coord.getY() + c.getX();
            if(tableau.containsKey(new Coord(newX,newY)))
                voisinsList.add(tableau.get(new Coord(newX,newY)));
        }
        return voisinsList; // on retourne la liste de voisins
    }



    public Pierre(String couleur, int x, int y){
        this.couleur = new Couleur(couleur);
        coord = new Coord(x,y);
    }

    public String toString(){

        if(couleur.toString() == "BLACK"){
            return "X";
        }
        else
            return "O";

    }
}

