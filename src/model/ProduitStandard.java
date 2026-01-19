package model;

/**
 * Classe concrète représentant un produit standard.
 * 
 * Objectifs Pédagogiques: Héritage et Polymorphisme.
 */
public class ProduitStandard extends Produit {

    /**
     * Constructeur de la classe ProduitStandard.
     * @param ref La référence unique du produit.
     * @param nom Le nom du produit.
     */
    public ProduitStandard(String ref, String nom) {
        super(ref, nom);
    }

    /**
     * Implémentation de la méthode manipuler() pour un produit standard.
     * La manipulation est considérée comme standard et sans risque particulier.
     */
    @Override
    public void manipuler() {
        System.out.println("--- Manipulation standard du produit : " + getNom() + " (Réf: " + getRef() + ").");
    }
}
