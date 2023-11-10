## Objectifs du système à modéliser A MODIFIER


On propose de modéliser un système de réservation (master) de tickets pouvant supporter plusieurs vendeurs (vendor). Le système master gère les salles, les concerts, les différents artistes se produisant dans les concerts et la réservation des tickets alors que les vendeurs assurent la vente de billets. Chaque vendeur a un quota pour un concert donné, qui peut évoluer avec le temps.
En cas d'annulation de concert, le système de réservation informe les vendors qui doivent contacter les clients (customers). Le master propose des services de validation de l'authenticité des tickets à l'entrée des concerts.

Lors de la réservation de ticket, on a 2 phases:
- le booking (réservation des places)
- le ticketing (émission de billets sécurisés avec clé.)

Le vendor va demander au master via une API rest les concerts pour lesquels il possède un quota. Seuls ces concerts seront proposés à la vente au client.
Le client spécifie ensuite le nombre de places assises et le nombre de places debout qu'il souhaite acheter. Le vendor interroge le master sur la disponibilité. Celui-ci va lui renvoyer des tickets transitionnels valables 10 minutes en cas de disponibilité de places.
Le vendeur va ensuite renseigner les informations du client et les transmettre au master pour l'émission finale des tickets avec clé sécurisée qui sera transmise au client pour qu'il puisse entrer dans la salle.
En cas d'annulation du concert, le master prévient les vendors (avec les informations des tickets à annuler et les emails des clients) le vendeur doit envoyer un email au client pour chaque ticket annulé.

## Interfaces

```
artist->master: POST venue
vendor->master: GET Gigs
master->vendor: Collection<Gigs>

Customer->vendor: cli:gig selection

vendor->master: jms:booking
alt booking successfull
    master->vendor: transitional tickets
    vendor->Customer: ticket purshase ok
    Customer->vendor: cli:customer informations
    
    vendor->master: jms:ticketing
    master->vendor: tickets

else booking unsuccessfull
    master->vendor: no quota for gigs
end

opt venue cancellation
    artist->master: DELETE venue
    master->vendor: jms:topic:cancellation
    vendor->Customer: smtp:cancellation email
end
```
![](seqDiagram.png)

## Schéma relationnel

![](EER.png)

## Exigences fonctionnelles

* l'intermédiaire DOIT recevoir la demande de l'entrepeneur 
* l'intermédiaire DOIT diffuser le deck aux pool d'investisseurs
* l'investisseur intéressé DOIT contacter l'intermédiaire
* l'intermediaire DOIT rendre compte de son intéret
* l'intermediaire DOIT mettre en relation l'entreprise et l'investisseur
* L'entrepeneur et l'investisseur DOIVENT trouver un accord (signer NDA, autrement back to 4. )
* l'investisseur DOIT effectuer la transaction
* ll'investisseur DOIT enregistrer les documents (data, transac, parts )
* l'entreprise et l'investisseur NE DOIVENTPAs entrer en contact sauf lorsque l'intermediaire le permet

## Exigences non fonctionnelles

*les communiations et échanges de données DOIVENT être fiable et utiliser le messaging
*lors d'une annulation d'un accord, l'intermediaire  DOIT informer chaques acteurs du deal de facon fiable
*Aucune données ne DOIT passer par un destinataire non souhaités  / externes
