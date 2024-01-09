package fr.pantheonsorbonne.ufr27.miage.java.camel;

import jakarta.enterprise.context.ApplicationScoped;
import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class CamelRoutes extends RouteBuilder {

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.smtp.user")
    String smtpUser;

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.smtp.password")
    String smtpPassword;

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.smtp.host")
    String smtpHost;

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.smtp.port")
    String smtpPort;
    @Override
    public void configure() throws Exception {
        System.out.println("test");
        from("imaps://imap.gmail.com:993?username=hugo.albert.marques@gmail.com&password=LMR444//opera&consumer.delay=30000&searchTerm.subject=VotreSujet")
                .log("yeyeye")
                .split().body()
                //processor pour signer le contrat
                .to("smtps:" + smtpHost + ":" + smtpPort + "?username=" + smtpUser + "&password=" + smtpPassword + "&contentType=");
    }
}