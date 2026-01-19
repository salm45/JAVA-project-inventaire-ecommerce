package service;

import model.Produit;
import model.MouvementStock;
import model.StockInsuffisantException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe de service gérant le catalogue de produits et l'historique des mouvements de stock.
 * 
 * Objectifs Pédagogiques: Utilisation des Collections (Map et ArrayList) et Traçabilité.
 */
public class Inventaire {
    // Catalogue des produits: Map<Référence, Produit>
    private Map<String, Produit> catalogue;
    // Historique des mouvements: ArrayList<MouvementStock>
    private List<MouvementStock> historique;

    /**
     * Constructeur de la classe Inventaire.
     */
    public Inventaire() {
        this.catalogue = new HashMap<>();
        this.historique = new ArrayList<>();
    }

    // --- 6.3.1 Gestion des Produits ---

    /**
     * Ajoute un produit au catalogue.
     * @param produit Le produit à ajouter (ProduitStandard ou ProduitFragile).
     */
    public void ajouterProduit(Produit produit) {
        if (catalogue.containsKey(produit.getRef())) {
            System.out.println("Erreur: Le produit avec la référence " + produit.getRef() + " existe déjà.");
        } else {
            catalogue.put(produit.getRef(), produit);
            System.out.println("Produit ajouté au catalogue: " + produit.getNom() + " (Réf: " + produit.getRef() + ")");
        }
    }

    /**
     * Supprime un produit du catalogue par sa référence.
     * @param ref La référence du produit à supprimer.
     * @return true si le produit a été supprimé, false sinon.
     */
    public boolean supprimerProduit(String ref) {
        if (catalogue.containsKey(ref)) {
            Produit produit = catalogue.remove(ref);
            System.out.println("Produit supprimé du catalogue: " + produit.getNom() + " (Réf: " + ref + ")");
            return true;
        } else {
            System.out.println("Erreur: Produit avec la référence " + ref + " non trouvé.");
            return false;
        }
    }

    /**
     * Recherche un produit par sa référence.
     * @param ref La référence du produit à rechercher.
     * @return Le produit trouvé ou null s'il n'existe pas.
     */
    public Produit rechercherProduit(String ref) {
        return catalogue.get(ref);
    }

    // --- 6.3.2 Gestion de l'Inventaire ---

    /**
     * Effectue une entrée en stock pour un produit.
     * @param ref La référence du produit.
     * @param quantite La quantité à ajouter.
     */
    public void entreeStock(String ref, int quantite) {
        Produit produit = rechercherProduit(ref);
        if (produit != null) {
            produit.ajouterStock(quantite);
            
            // Traçabilité: Création et enregistrement du mouvement
            MouvementStock mouvement = new MouvementStock(ref, "ENTREE", quantite);
            historique.add(mouvement);
            
            // Polymorphisme: Appel de la méthode manipuler()
            produit.manipuler();
            System.out.println("ENTREE de " + quantite + " unités pour " + produit.getNom() + ". Nouveau stock: " + produit.getQuantiteStock());
        } else {
            System.out.println("Erreur: Entrée en stock impossible. Produit avec la référence " + ref + " non trouvé.");
        }
    }

    /**
     * Effectue une sortie de stock pour un produit.
     * @param ref La référence du produit.
     * @param quantite La quantité à retirer.
     * @throws StockInsuffisantException Si le stock est insuffisant.
     */
    public void sortieStock(String ref, int quantite) throws StockInsuffisantException {
        Produit produit = rechercherProduit(ref);
        if (produit != null) {
            // Validation: La méthode retirerStock() lève l'exception si nécessaire
            produit.retirerStock(quantite);
            
            // Traçabilité: Création et enregistrement du mouvement
            MouvementStock mouvement = new MouvementStock(ref, "SORTIE", quantite);
            historique.add(mouvement);
            
            // Polymorphisme: Appel de la méthode manipuler()
            produit.manipuler();
            System.out.println("SORTIE de " + quantite + " unités pour " + produit.getNom() + ". Nouveau stock: " + produit.getQuantiteStock());
        } else {
            System.out.println("Erreur: Sortie de stock impossible. Produit avec la référence " + ref + " non trouvé.");
        }
    }

    /**
     * Affiche l'historique complet des mouvements de stock.
     */
    public void afficherHistorique() {
        System.out.println("\n--- Historique des Mouvements de Stock ---");
        if (historique.isEmpty()) {
            System.out.println("Aucun mouvement enregistré.");
        } else {
            for (MouvementStock mouvement : historique) {
                System.out.println(mouvement);
            }
        }
        System.out.println("------------------------------------------");
    }

    /**
     * Affiche l'état actuel du catalogue.
     */
    public void afficherCatalogue() {
        System.out.println("\n--- État Actuel du Catalogue ---");
        if (catalogue.isEmpty()) {
            System.out.println("Le catalogue est vide.");
        } else {
            for (Produit produit : catalogue.values()) {
                // Affichage du type de produit pour plus de clarté
                String type = produit instanceof model.ProduitFragile ? "Fragile" : "Standard";
                System.out.printf("Réf: %s | Nom: %s | Type: %s | Stock: %d\n", 
                                  produit.getRef(), produit.getNom(), type, produit.getQuantiteStock());
            }
        }
        System.out.println("----------------------------------");
    }
}
