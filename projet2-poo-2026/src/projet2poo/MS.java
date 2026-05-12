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
public class MS implements EmissionRadio {
    private String nom, prenom, motDePasse, msisdn, imsi, typeAppareil;
    private ArrayList<String> appelsRecus = new ArrayList<>();

    public MS(String nom, String prenom, String mdp, String msisdn, String imsi, String type) {
        this.nom = nom; this.prenom = prenom; this.motDePasse = mdp;
        this.msisdn = msisdn; this.imsi = imsi; this.typeAppareil = type;
    }

    public void afficherInfos() {
        System.out.println("[MS] " + prenom + " " + nom + " | Num: " + msisdn + " | Type: " + typeAppareil);
    }

    public void appeler(MS destinataire) {
        System.out.println(this.prenom + " appelle " + destinataire.prenom + "...");
        destinataire.recevoirAppel(this.msisdn);
    }

    public void recevoirAppel(String numero) {
        this.appelsRecus.add(numero);
        System.out.println("Appel reçu de " + numero + " sur le mobile de " + this.prenom);
    }

    @Override
    public void emettreSignal() {
        System.out.println("Le mobile " + msisdn + " emet sur la liaison montante (Uplink).");
    }

    public String getMsisdn() { return msisdn; }
}

    