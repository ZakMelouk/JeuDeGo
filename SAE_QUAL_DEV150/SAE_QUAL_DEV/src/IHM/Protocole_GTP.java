package IHM;

import Jeu.*;

import java.util.HashMap;
import java.util.Scanner;

public class Protocole_GTP {
    private HashMap<String,Integer> passe;

    private HashMap<Boolean,String> tour;
    private HashMap<String, IPlayer> Joueurs;

    private static final int MAX_PASSE = 2;

    public Protocole_GTP(String[] args){
        Joueurs = new HashMap<>();
        tour = new HashMap<>();
        passe = new HashMap<>();
        tour.put(true,"BLACK");
        tour.put(false,"WHITE");
        passe.put("BLACK",0);
        passe.put("WHITE",0);
        Joueurs.put("BLACK", FabriqueJoueur.CreerJoueur("BLACK","CONSOLE"));
        Joueurs.put("WHITE",FabriqueJoueur.CreerJoueur("WHITE","CONSOLE"));

    }

    public void jeu(){

        Tableau MonTab = new Tableau();
        Scanner scanner = new Scanner(System.in);

        do {
            if(Joueurs.get("BLACK").getClass().getSimpleName().compareTo("RandomPlayer")==0 && Joueurs.get("WHITE").getClass().getSimpleName().compareTo("RandomPlayer")==0){
                RandomPlay(MonTab);
                return;
            }
            else{
                String commande = scanner.nextLine();
                commande = commande.trim();
                int numero_donné = 0;
                String instruction_donnee = "";
                String arg [] = new String[0];
                boolean commande_valide = false;
                String commande_passe[];
                if(commande.length() != 0){
                    commande_passe = commande.split(" ");
                    if(!Main.estNombre(commande_passe[0])){
                        commande_valide = true;
                        instruction_donnee = commande_passe[0];
                        arg = new String [commande_passe.length-1];
                    }
                    else {
                        if (commande_passe.length > 1) {
                            instruction_donnee = commande_passe[1];
                            commande_valide = true;
                            numero_donné = Integer.parseInt(commande_passe[0]);
                            arg = new String [commande_passe.length-2];
                        }
                    }
                    for (int i = 0; i < arg.length ; ++i){
                        if(!Main.estNombre(commande_passe[0]))
                            arg[i] = commande_passe[i+1];
                        else
                            arg[i] = commande_passe[i+2];
                    }
                }

                if(commande_valide) {
                    instruction_donnee = instruction_donnee.toUpperCase();
                    if (instruction_donnee.compareTo("BOARDSIZE") == 0) {
                        if(arg.length == 0||!(Main.estNombre(arg[0]))){
                            System.out.println("?"+ (numero_donné == 0 ? "" : numero_donné)+" boardsize not an integer");
                            continue;
                        }
                        int taille = Integer.parseInt(arg[0]);
                        try {
                            MonTab = new Tableau(taille);
                            System.out.println("=" + (numero_donné == 0 ? "" : numero_donné));
                        }
                        catch(IllegalArgumentException e){
                            System.out.println("?"+ (numero_donné == 0 ? "" : numero_donné)+" unacceptable size");
                        }
                    }
                    else if(instruction_donnee.compareTo("LIBERTIES")==0){
                        try{
                            int nbLiberté = MonTab.getLiberté(arg[0]);
                            System.out.println("=" + (numero_donné == 0 ? "" : numero_donné)+"\n"+arg[0]+" a "+nbLiberté+" liberté");

                        }catch(Exception e){
                            System.out.println("?"+ (numero_donné == 0 ? "" : numero_donné)+e.getLocalizedMessage());
                        }
                    }
                    else if(instruction_donnee.compareTo("PLAYER") == 0){
                        if (arg.length!=2){
                            System.out.println("?"+ (numero_donné == 0 ? "" : numero_donné)+" unknown command");
                        }
                         try {
                             IPlayer p = FabriqueJoueur.CreerJoueur(arg[0].toUpperCase(), arg[1].toUpperCase());
                             Joueurs.put(arg[0].toUpperCase(), p);
                             System.out.println("=" + (numero_donné == 0 ? "" : numero_donné));
                         }
                         catch(Exception e){
                             System.out.println("?"+ (numero_donné == 0 ? "" : numero_donné)+" "+e.getLocalizedMessage());
                         }
                    }
                    else if (instruction_donnee.compareTo("CLEAR_BOARD") == 0) {
                        MonTab.ClearTheBoard();
                        System.out.println("=" + (numero_donné == 0 ? "" : numero_donné));
                    }
                    else if (instruction_donnee.compareTo("PLAY") == 0) {
                        if(arg.length != 2){
                            System.out.println("?"+ (numero_donné == 0 ? "" : numero_donné)+ " invalid color or coordinate");
                            continue;
                        }
                        if(Joueurs.get(tour.get(true)).getClass().getSimpleName().compareTo("RandomPlayer")==0){
                            try{
                                MonTab.play(Joueurs.get(tour.get(true)).play(MonTab,tour.get(true),"00"));
                                ChangeTour();
                            }catch(Exception e){
                                if(MonTab.estPlein())
                                    return;
                            }
                        }
                        if(arg[0].toUpperCase().compareTo(tour.get(true))==0){
                            try{
                                MonTab.play(Joueurs.get(arg[0].toUpperCase()).play(MonTab,arg[0].toUpperCase(),arg[1]));
                                System.out.println("=" + (numero_donné == 0 ? "" : numero_donné));
                                ChangeTour();
                                if(passe.get(arg[0].toUpperCase())>0){
                                    passe.put(arg[0].toUpperCase(),0);
                                }
                                if(Joueurs.get(tour.get(true)).getClass().getSimpleName().compareTo("RandomPlayer")==0 ){
                                    try{
                                        MonTab.play(Joueurs.get(tour.get(true)).play(MonTab,tour.get(true),"00"));
                                        ChangeTour();
                                    }catch(Exception e){
                                        if(MonTab.estPlein())
                                            return;
                                    }
                                }
                            }catch(Exception e){
                                System.out.println("?"+ (numero_donné == 0 ? "" : numero_donné)+" "+e.getLocalizedMessage());
                            }
                        }
                        else{
                            System.out.println("C'est au tour des "+tour.get(true)+"S de jouer");
                        }
                    }
                    else if(instruction_donnee.compareTo("SHOWBOARD") == 0){
                        System.out.println("=" + (numero_donné == 0 ? "" : numero_donné)+"\n"+MonTab.Show_board());
                    }
                    else if((instruction_donnee.compareTo("BLACK") == 0 )||(instruction_donnee.compareTo("WHITE") == 0 )){
                        if(arg[0].toUpperCase().compareTo("PASS")==0){
                            if(tour.get(true).compareTo(instruction_donnee)==0){
                                System.out.println("=" + (numero_donné == 0 ? "" : numero_donné));
                                ChangeTour();
                                passe.put(instruction_donnee,passe.get(instruction_donnee)+1);
                                if(passe.get("BLACK")>0 && passe.get("WHITE")>0){
                                    return;
                                }
                            }
                            else
                                System.out.println("Ce n'est pas à votre tour de jouer");
                        }
                        else{
                            System.out.println("?"+ (numero_donné == 0 ? "" : numero_donné)+" unknown command");
                        }
                    }
                    else if(instruction_donnee.compareTo("QUIT") == 0){
                        System.out.println("=" + (numero_donné == 0 ? "" : numero_donné));
                        return;
                    }
                    else{
                        System.out.println("?"+ (numero_donné == 0 ? "" : numero_donné)+" unknown command");
                    }
                }
            }
        } while (true);
    }
    private void RandomPlay(Tableau MonTab){
        do{
            try{
                Pierre p = Joueurs.get(tour.get(true)).play(MonTab,tour.get(true),"00");
                MonTab.play(p);
                System.out.println("\n"+tour.get(true)+" joue en "+p.coord.toString()+"\n");
                System.out.println(MonTab.Show_board());
                if(passe.get(tour.get(true))>0)
                    passe.put("BLACK",0);
            }catch(Exception e){
                passe.put(tour.get(true),1);
                System.out.println("\n"+tour.get(true)+" passe\n");
            }
            if((passe.get("BLACK")>0 && passe.get("WHITE")>0))
                return;
            ChangeTour();
        }while(!(MonTab.estPlein()));
    }
    private void ChangeTour(){
        String a = tour.get(true);
        String b = tour.get(false);
        tour.put(true,b);
        tour.put(false,a);
    }
}
