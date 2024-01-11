package fr.pantheonsorbonne.ufr27.miage.exception;

public class OnePagerNotFoundException extends Throwable {
    public OnePagerNotFoundException(int siretStartup) {
        super("No OnePager for StartUp number " + siretStartup + " tracked in database");
    }
}
