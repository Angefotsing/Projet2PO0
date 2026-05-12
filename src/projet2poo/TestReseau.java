/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
   