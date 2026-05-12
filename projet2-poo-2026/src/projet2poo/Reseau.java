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
public class Reseau {
     private String nom;
    private double freqUplink, freqDownlink;
    private ArrayList<BTS> lesBTS = new ArrayList<>();

    public Reseau(String nom, double fUp, double fDown) {
        this.nom = nom; this.freqUplink = fUp; this.freqDownlink = fDown;
    }

    public void ajouterBTS(BTS b) { lesBTS.add(b); }

    public int calculerTotalAbonnes() {
        int total = 0;
        for (BTS b : lesBTS) { total += b.getNombreAbonnes(); }
        return total;
    }

    public void afficherPerformances() {
        System.out.println("\n--- Performances Réseau " + nom + " ---");
        System.out.println("Total Abonnés : " + calculerTotalAbonnes());
        System.out.println("Nombre de BTS : " + lesBTS.size());
    }
}
   