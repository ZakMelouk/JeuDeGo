package IHM;

import Jeu.Couleur;
import Jeu.IPlayer;
import player.ConsolePlayer;
import player.RandomPlayer;

import java.util.Random;

public class FabriqueJoueur {

    public static IPlayer CreerJoueur(String couleur, String type){

        if(!(Couleur.isCouleur(couleur)))
            throw new IllegalArgumentException("invalid color");
        if(type.toUpperCase().compareTo("CONSOLE")==0){
            IPlayer p = new ConsolePlayer();
            return p;
        }
        else if(type.toUpperCase().compareTo("RANDOM")==0){
            IPlayer p = new RandomPlayer();
            return p;
        }
        else{
            throw new IllegalArgumentException("you have to say console or random");
        }
    }
}
