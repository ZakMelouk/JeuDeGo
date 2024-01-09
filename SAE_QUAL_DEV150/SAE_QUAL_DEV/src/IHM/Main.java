package IHM;
public class Main {

    public static void main(String[] args) {
        Protocole_GTP protocole = new Protocole_GTP(args);
        protocole.jeu();
    }


    public static boolean estNombre(String chaine) {
        try {
            // Tenter de convertir la chaîne en int
            Integer.parseInt(chaine);
            return true; // Si la conversion réussit, alors la chaîne est un nombre
        } catch (NumberFormatException e) {
            return false; // Si la conversion échoue, la chaîne n'est pas un nombre
        }
    }

}
