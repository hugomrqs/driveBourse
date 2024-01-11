package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.dto.OnePagerInteret;
import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;

import java.util.ArrayList;
import java.util.List;

public class InteretAgregationStrategy implements AggregationStrategy {
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        OnePagerInteret newResponse = newExchange.getIn().getBody(OnePagerInteret.class);
        if (oldExchange == null) {
            List<OnePagerInteret> responses = new ArrayList<>();
            responses.add(newResponse);
            newExchange.getIn().setBody(responses);
            return newExchange;
        } else {
            List<OnePagerInteret> responses = oldExchange.getIn().getBody(List.class);
            responses.add(newResponse);
            return oldExchange;
        }
    }
}

