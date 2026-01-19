package model;

/**
 * Classe abstraite représentant un produit dans le système d'inventaire.
 * Elle sert de base pour les différents types de produits (Standard, Fragile).
 * 
 * Objectifs Pédagogiques: Maîtrise de la Classe Abstraite et de l'Encapsulation.
 */
public abstract class Produit {
    private String ref;
    private String nom;
    private int quantiteStock;

    /**
     * Constructeur de la classe Produit.
     * @param ref La référence unique du produit.
     * @param nom Le nom du produit.
     */
    public Produit(String ref, String nom) {
        this.ref = ref;
        this.nom = nom;
        this.quantiteStock = 0; // Initialisation à 0
    }

    /**
     * Méthode abstraite pour la manipulation physique du produit.
     * Doit être implémentée par les classes filles pour démontrer le Polymorphisme.
     */
    public abstract void manipuler();

    // Getters
    public String getRef() {
        return ref;
    }

    public String getNom() {
        return nom;
    }

    public int getQuantiteStock() {
        return quantiteStock;
    }

    /**
     * Ajoute une quantité au stock du produit.
     * @param quantite La quantité à ajouter (doit être > 0).
     */
    public void ajouterStock(int quantite) {
        if (quantite > 0) {
            this.quantiteStock += quantite;
        }
    }

    /**
     * Retire une quantité du stock du produit.
     * @param quantite La quantité à retirer (doit être > 0).
     * @throws StockInsuffisantException Si le stock est insuffisant.
     */
    public void retirerStock(int quantite) throws StockInsuffisantException {
        if (quantite > 0) {
            if (this.quantiteStock >= quantite) {
                this.quantiteStock -= quantite;
            } else {
                throw new StockInsuffisantException("Stock insuffisant pour le produit " + nom + " (Référence: " + ref + "). Stock actuel: " + quantiteStock + ", Quantité demandée: " + quantite);
            }
        }
    }

    @Override
    public String toString() {
        return "Produit [ref=" + ref + ", nom=" + nom + ", quantiteStock=" + quantiteStock + "]";
    }
}
