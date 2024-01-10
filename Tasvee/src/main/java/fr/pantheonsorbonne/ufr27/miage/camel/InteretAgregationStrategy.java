package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.dto.Interet;
import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;

import java.util.ArrayList;
import java.util.List;

public class InteretAgregationStrategy implements AggregationStrategy {
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        Interet newResponse = newExchange.getIn().getBody(Interet.class);
        if (oldExchange == null) {
            List<Interet> responses = new ArrayList<>();
            responses.add(newResponse);
            newExchange.getIn().setBody(responses);
            return newExchange;
        } else {
            List<Interet> responses = oldExchange.getIn().getBody(List.class);
            responses.add(newResponse);
            return oldExchange;
        }
    }
}

