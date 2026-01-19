package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Classe représentant un enregistrement de mouvement de stock pour la traçabilité.
 * 
 * Objectifs Pédagogiques: Traçabilité.
 */
public class MouvementStock {
    private LocalDateTime date;
    private String type; // "ENTREE" ou "SORTIE"
    private int quantite;
    private String refProduit;

    /**
     * Constructeur de la classe MouvementStock.
     * @param refProduit La référence du produit concerné.
     * @param type Le type de mouvement ("ENTREE" ou "SORTIE").
     * @param quantite La quantité concernée par le mouvement.
     */
    public MouvementStock(String refProduit, String type, int quantite) {
        this.date = LocalDateTime.now();
        this.refProduit = refProduit;
        this.type = type;
        this.quantite = quantite;
    }

    /**
     * Retourne une représentation textuelle du mouvement.
     * @return Une chaîne de caractères décrivant le mouvement.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return String.format("[%s] %s de %d unités pour le produit %s (Réf: %s)",
            date.format(formatter), type, quantite, refProduit, refProduit);
    }
}
