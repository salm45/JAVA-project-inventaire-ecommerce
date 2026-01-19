package model;

/**
 * Exception personnalisée levée lorsque la quantité en stock est insuffisante
 * pour effectuer une sortie.
 */
public class StockInsuffisantException extends Exception {
    /**
     * Constructeur pour l'exception.
     * @param message Le message d'erreur.
     */
    public StockInsuffisantException(String message) {
        super(message);
    }
}
