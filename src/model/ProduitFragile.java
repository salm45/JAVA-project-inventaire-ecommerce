package model;

/**
 * Classe concrète représentant un produit fragile.
 * 
 * Objectifs Pédagogiques: Héritage et Polymorphisme Strict.
 */
public class ProduitFragile extends Produit {

    /**
     * Constructeur de la classe ProduitFragile.
     * @param ref La référence unique du produit.
     * @param nom Le nom du produit.
     */
    public ProduitFragile(String ref, String nom) {
        super(ref, nom);
    }

    /**
     * Implémentation de la méthode manipuler() pour un produit fragile.
     * Un avertissement est affiché pour refléter la nature du produit.
     */
    @Override
    public void manipuler() {
        System.out.println("!!! ATTENTION FRAGILE !!! Manipulation délicate du produit : " + getNom() + " (Réf: " + getRef() + ").");
    }
}
