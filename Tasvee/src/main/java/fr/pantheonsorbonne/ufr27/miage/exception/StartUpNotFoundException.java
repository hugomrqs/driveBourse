package fr.pantheonsorbonne.ufr27.miage.exception;

public class StartUpNotFoundException extends Throwable {

            public StartUpNotFoundException(int siret) {
                super("No Siret " + siret + " tracked in database");
            }
    }

