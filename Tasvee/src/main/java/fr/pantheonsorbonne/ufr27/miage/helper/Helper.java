package fr.pantheonsorbonne.ufr27.miage.helper;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Helper {
    public final int siret;
    public Helper() {
        this.siret = 1234567121;
    }

}
