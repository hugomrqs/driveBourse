package fr.pantheonsorbonne.ufr27.miage.dto;

public abstract class NDADTO<T> {
    private T sujet;
    private boolean signatureTasvee;

    public NDADTO(T sujet, boolean signatureTasvee) {
        this.sujet = sujet;
        this.signatureTasvee = signatureTasvee;
    }

    // Getters et setters

    public T getSujet() {
        return sujet;
    }

    public void setSujet(T sujet) {
        this.sujet = sujet;
    }

    public boolean isSignatureTasvee() {
        return signatureTasvee;
    }

    public void setSignatureTasvee(boolean signatureTasvee) {
        this.signatureTasvee = signatureTasvee;
    }

}