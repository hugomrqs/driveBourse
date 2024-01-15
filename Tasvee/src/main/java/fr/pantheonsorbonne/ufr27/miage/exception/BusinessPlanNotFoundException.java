package fr.pantheonsorbonne.ufr27.miage.exception;

public class BusinessPlanNotFoundException extends Throwable {
    public BusinessPlanNotFoundException(int siretStartup) {
        super("No BusinessPlan for StartUp number " + siretStartup + " tracked in database");
    }
}
