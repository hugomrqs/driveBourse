package fr.pantheonsorbonne.ufr27.miage.service;
import fr.pantheonsorbonne.ufr27.miage.dto.OnePagerDTO;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TraitementOnePagerServiceImpl implements TraitementOnePagerService {

    // Hypothetical maximum values for normalization
    private static final int MAX_PART_EXPERTISE = 1000;
    private static final int MAX_PRIX_PAR_PART = 50000;
    private static final double MAX_BFR = 200;//M;
    private static final double MAX_MARGE_BRUT = 100;//M;
    private static final double WEIGHT_PART_EXPERTISE = 0.25;
    private static final double WEIGHT_PRIX_PAR_PART = 0.25;
    private static final double WEIGHT_BFR = 0.25;
    private static final double WEIGHT_MARGE_BRUT = 0.25;

    public static double calculateInterestLevel(OnePagerDTO onePagerDTO) {
        double normalizedPartExpertise = (double) onePagerDTO.expertiseJuridique().nombrePartExpertise() / MAX_PART_EXPERTISE;
        double normalizedPrixPart = (double) onePagerDTO.expertiseJuridique().prixActuelPartExpertise() / MAX_PRIX_PAR_PART;
        double normalizedBFR = 1.0 - (onePagerDTO.expertiseFinanciere().bfrExpert() / MAX_BFR);
        double normalizedMargeBrut = onePagerDTO.expertiseFinanciere().margeBrutExpert() / MAX_MARGE_BRUT;

        return (normalizedPartExpertise * WEIGHT_PART_EXPERTISE +
                normalizedPrixPart * WEIGHT_PRIX_PAR_PART +
                normalizedBFR * WEIGHT_BFR +
                normalizedMargeBrut * WEIGHT_MARGE_BRUT);
    }

    public static boolean isOnePagerInteresting(OnePagerDTO onePagerDTO) {
        return calculateInterestLevel(onePagerDTO) > 0.60;
    }

    @Override
    public boolean OnePagerResponse(OnePagerDTO onePagerDTO) {
        return isOnePagerInteresting(onePagerDTO);
    }

}

