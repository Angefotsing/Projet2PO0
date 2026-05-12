
# Projet 2 POO : Modélisation d'un réseau GSM


## Description du Projet

Ce projet consiste en une application Java développée dans le cadre du module de *Programmation Orientée Objet (POO). L'objectif est de modéliser la partie radio d'un réseau GSM, gérant les interactions entre les stations mobiles (MS) et les stations de base (BTS*).
Le programme simule l'attachement d'un mobile à une cellule, la gestion de la capacité des équipements et le déclenchement d'alertes en cas de saturation.


## Fonctionnalités Clés

 * *Gestion des Mobiles (MS) :* Caractérisés par un nom, numéro de téléphone (MSISDN) et numéro de carte SIM (IMSI).
 * *Stations de Base (BTS) :* Gestion de l'emplacement, de la hauteur et de la capacité maximale d'utilisateurs.
 * *Réseau :* Centralisation des équipements et gestion des bandes de fréquences (Uplink/Downlink).
 * *Gestion des Exceptions :* Implémentation d'une exception personnalisée CelluleStatureeException pour gérer les tentatives de connexion sur une BTS saturée.


## Concepts POO Appliqués

 * *Encapsulation :* Utilisation d'attributs privés et de constructeurs.
 * *Interfaces :* Utilisation de l'interface EmissionRadio.
 * *Héritage & Polymorphisme :* Structure modulaire des classes métiers.
 * *Gestion d'Exceptions :* Utilisation des blocs try-catch et throw.


## Structure du Projet

Le code est organisé dans le package projet2poo et comprend les fichiers suivants :
 * MS.java : Représente le terminal utilisateur
CODE
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

 * BTS.java : Gère les ressources radio de la cellule.
CODE
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

 * Reseau.java : Classe de gestion globale.
CODE
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
   
 * EmissionRadio.java : Interface définissant les capacités d'émission.
package projet2poo;

/**
 *
 * @author ANGELA
 */
public interface EmissionRadio {
    void emettreSignal();
    
}

 * CelluleStatureeException.java : Gestion des erreurs de capacité.
CODE 
package projet2poo;

/**
 *
 * @author ANGELA
 */
public class CelluleStatureeException extends Exception {
    public CelluleStatureeException(String message) {
        super(message);
    }
}

 * TestReseau.java : Classe principale pour exécuter les tests de simulation.
package projet2poo;

/**
 *
 * @author ANGELA
 */
public class TestReseau {
    public static void main(String[] args) {
        System.out.println("*****************");
        System.out.println("PROJET GSM - ETUDIANT : ANGELA FOTSING");
        System.out.println("*****************\n");

        try {
            // 1. Initialisation
            Reseau orange = new Reseau("Orange CM", 900.0, 1800.0);
            BTS bts1 = new BTS(101, "Bafoussam Centre", 45.0, "Urbain", 5.0, 1); // Capacité 1 pour tester l'exception
            orange.ajouterBTS(bts1);

            MS angela = new MS("Fotsing", "Angela", "pass123", "677112233", "IMSI_ANG", "Smartphone");
            MS jean = new MS("Dupont", "Jean", "pass456", "655445566", "IMSI_JEA", "Tablette");

            // 2. Tests
            angela.afficherInfos();
            bts1.ajouterMS(angela);
            bts1.verifierEtat();

            // Test de l'exception (ajouter un 2ème MS alors que max = 1)
            System.out.println("\nTentative d'ajout d'un deuxième utilisateur...");
            bts1.ajouterMS(jean); 

        } catch (CelluleStatureeException e) {
            System.err.println(e.getMessage());
        }

        System.out.println("\n--- Fin des tests de simulation ---");
    }
    
}

