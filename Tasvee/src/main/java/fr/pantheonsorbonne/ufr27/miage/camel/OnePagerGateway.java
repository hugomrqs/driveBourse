package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.dto.OnePagerDTO;

public interface OnePagerGateway {
    void sendOnePager(OnePagerDTO onePagerDTO);
}
