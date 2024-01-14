package fr.pantheonsorbonne.ufr27.miage.exception;

public class ContractNotSignedException extends Exception {

    public ContractNotSignedException(int siretFond) {
        super("Le fond " +
                siretFond +
                " n'a pas encore signé le Contrat Juridique; vous ne pouvez pas lui envoyer le Business Plan.");
    }
}
