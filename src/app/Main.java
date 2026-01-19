package app;

import model.Produit;
import model.ProduitFragile;
import model.ProduitStandard;
import model.StockInsuffisantException;
import service.Inventaire;

/**
 * Classe principale pour simuler les mouvements de stock et démontrer les fonctionnalités.
 * 
 * Objectifs Pédagogiques: Démonstration du Polymorphisme, de la Traçabilité et de la Gestion des Exceptions.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("--- Démarrage du Système de Gestion d'Inventaire E-Commerce ---");

        // 1. Initialisation de l'inventaire
        Inventaire inventaire = new Inventaire();

        // 2. Création et ajout de produits au catalogue
        ProduitStandard p1 = new ProduitStandard("REF001", "T-Shirt Coton");
        ProduitFragile p2 = new ProduitFragile("REF002", "Vase en Céramique");
        ProduitStandard p3 = new ProduitStandard("REF003", "Livre de Poche");

        inventaire.ajouterProduit(p1);
        inventaire.ajouterProduit(p2);
        inventaire.ajouterProduit(p3);
        
        inventaire.afficherCatalogue();

        // 3. Simulation des mouvements de stock (entreeStock)
        System.out.println("\n--- Simulation des Entrées en Stock ---");
        inventaire.entreeStock("REF001", 100); // Standard
        inventaire.entreeStock("REF002", 10);  // Fragile (doit afficher l'avertissement)
        inventaire.entreeStock("REF003", 50);

        inventaire.afficherCatalogue();

        // 4. Simulation des mouvements de stock (sortieStock)
        System.out.println("\n--- Simulation des Sorties de Stock ---");
        try {
            // Sortie normale pour un produit standard
            inventaire.sortieStock("REF001", 20);
            
            // Sortie normale pour un produit fragile (doit afficher l'avertissement)
            inventaire.sortieStock("REF002", 3);
            
            // Sortie qui doit échouer (stock insuffisant)
            System.out.println("\nTentative de sortie de stock insuffisante (REF003, 100 unités)...");
            inventaire.sortieStock("REF003", 100); 
            
        } catch (StockInsuffisantException e) {
            System.err.println("ERREUR DE STOCK: " + e.getMessage());
        }
        
        // 5. Nouvelle sortie normale après l'échec
        try {
            inventaire.sortieStock("REF003", 10);
        } catch (StockInsuffisantException e) {
            System.err.println("ERREUR DE STOCK: " + e.getMessage());
        }

        inventaire.afficherCatalogue();

        // 6. Affichage de l'historique des mouvements (Traçabilité)
        inventaire.afficherHistorique();
        
        // 7. Test de suppression et recherche
        System.out.println("\n--- Test de Suppression et Recherche ---");
        Produit recherche = inventaire.rechercherProduit("REF001");
        System.out.println("Recherche REF001: " + (recherche != null ? "Trouvé - " + recherche.getNom() : "Non trouvé"));
        
        inventaire.supprimerProduit("REF001");
        
        recherche = inventaire.rechercherProduit("REF001");
        System.out.println("Recherche REF001 après suppression: " + (recherche != null ? "Trouvé" : "Non trouvé"));
        
        System.out.println("\n--- Fin de la Simulation ---");
    }
}
