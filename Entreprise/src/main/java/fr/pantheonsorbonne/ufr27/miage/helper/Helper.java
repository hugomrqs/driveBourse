package fr.pantheonsorbonne.ufr27.miage.helper;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Helper {
    public final int siret;
    public final String[] topicsToListen;
    public Helper() {
        this.siret = 127645217;
//  les topics exitants sont : {"T", "S", "I", "F", "E"}
        this.topicsToListen=new String[]{"T", "E"};

    }

}
