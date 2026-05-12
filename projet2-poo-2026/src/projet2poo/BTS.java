/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projet2poo;
import java.util.ArrayList;

/**
 *
 * @author ANGELA
 */
public class BTS {
    private int numero;
    private String emplacement, typeMilieu;
    private double hauteur, rayonCouverture;
    private int maxMS;
    private ArrayList<MS> listeMS = new ArrayList<>();

    public BTS(int num, String lieu, double h, String milieu, double rayon, int max) {
        this.numero = num;
        this.emplacement = lieu;
        this.hauteur = h;
        this.typeMilieu = milieu;
        this.rayonCouverture = rayon;
        this.maxMS = max;
    }

    // Vérifie bien que le nom de l'exception est celui de ton fichier !
    public void ajouterMS(MS mobile) throws CelluleStatureeException {
        if (listeMS.size() >= maxMS) {
            throw new CelluleStatureeException("ALERTE : La BTS n°" + numero + " est saturée !");
        }
        listeMS.add(mobile);
        System.out.println("Mobile " + mobile.getMsisdn() + " connecté à la BTS " + numero);
    }

    public void verifierEtat() {
        String etat = (listeMS.size() >= maxMS) ? "SATUREE" : "DISPONIBLE";
        System.out.println("Etat BTS " + numero + " : " + etat + " (" + listeMS.size() + "/" + maxMS + ")");
    }

    public int getNombreAbonnes() {
        return listeMS.size();
    }
}
