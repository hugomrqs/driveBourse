package fr.pantheonsorbonne.ufr27.miage.dto;

public abstract class NDADTO<T> {
    int numeroContrat;
    private T sujet;
    private int siretTasvee;
    private boolean signatureTasvee;

    public NDADTO(int numeroContrat, T sujet, int siretTasvee, boolean signatureTasvee) {
        this.numeroContrat=numeroContrat;
        this.sujet = sujet;
        this.siretTasvee = siretTasvee;
        this.signatureTasvee = signatureTasvee;
    }

    // Getters et setters

    public T getSujet() {
        return sujet;
    }
    public void setSujet(T sujet) {
        this.sujet = sujet;
    }


    public int getSiretTasvee() {
        return siretTasvee;
    }
    public void setSiretTasvee(int siretTasvee){this.siretTasvee=siretTasvee;}

    public boolean isSignatureTasvee() {
        return signatureTasvee;
    }

    public void setSignatureTasvee(boolean signatureTasvee) {
        this.signatureTasvee = signatureTasvee;
    }
    public int getNumeroContrat(){return numeroContrat;}
    public void setNumeroContrat(int numeroContrat){this.numeroContrat=numeroContrat;}

}