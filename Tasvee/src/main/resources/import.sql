-- Inserting into BilanComptable
INSERT INTO BilanComptable (coutDeMarchandise, emplois, ressources, venteDeMarchandise)
VALUES (10000, 5000, 15000, 12000);

-- Inserting into CVDirigeant
INSERT INTO CVDirigeant (engagement, ecole, lienLinkedin, mainExperience)
VALUES (1, 'Ecole de Commerce', 'https://www.linkedin.com/in/dirigeant', 'CEO at CompanyX');

-- Inserting into Statut
INSERT INTO Statut (idStatut, nombrePart, prixPartActuel, strategieEntrepreneur)
VALUES (1, 100, 50, 1);

-- Inserting into PrestataireFinancier
INSERT INTO PrestataireFinancier (SiretPrestataireFinancier, mail)
VALUES (1234567, 'finance@example.com');

-- Inserting into PrestataireJuridique
INSERT INTO PrestataireJuridique (SiretPrestataireJuridique, email)
VALUES (98765, 'legal@example.com');

-- Inserting into ExpertiseFinanciere
INSERT INTO ExpertiseFinanciere (BFRExpert, margeBrutExpert, SiretPrestataireFinancier)
VALUES (5000, 7000, 1234567);

-- Inserting into ExpertiseJuridique
INSERT INTO ExpertiseJuridique (nombrePartExpertise, SiretPrestataireJuridique)
VALUES (20, 98765);

-- Inserting into StartUp (assuming IDBilanComptable, IDCVDirigeant, IDStatut are 1)
INSERT INTO StartUp (IDBilanComptable, IDCVDirigeant, IDStatut, argentLevee, nombreDePersonne, numeroDeLevee, partCede, dateOfferForm, IBAN, SiretStartUP, lienSiteWeb, mail, secteur)
VALUES (1, 1, 1, 200000, 15, 1, 30, NOW(), 'FR7630001007941234567890185', 1234567890, 'https://www.startupexample.com', 'contact@startupexample.com', 'Tech');

-- Inserting into BusinessModel
INSERT INTO BusinessModel (argentLeveeXpTasvee, partCedeeXpTasvee, SiretStartUP)
VALUES (100000, 10, 1234567890);

-- Inserting into OnePager
INSERT INTO OnePager (IDExpertiseFinanciere, IDExpertiseJuridique, SiretStartUP)
VALUES (1, 1, 1234567890);

-- Inserting into BusinessPlan
INSERT INTO BusinessPlan (IDOnePager, SiretStartUP)
VALUES (1, 1234567890);

-- Inserting into Fonds
INSERT INTO Fonds (IsInterested, IBAN, SiretFonds, mail)
VALUES (1, 'FR7630003000011234567890186', 234567890, 'funds@example.com');

-- Inserting into PropositionFinale
INSERT INTO PropositionFinale (leveedeFondsFinale, pourcentagePartFinale, SiretFonds)
VALUES (300000, 25, 234567890);

-- Inserting into ContratJuridiqueBM
INSERT INTO ContratJuridiqueBM (IDBusinessModel, PourcentageComissionTasvee, StartUP, TASVEE, SiretStartUP, SiretTasvee)
VALUES (1, 5, 1, 1, 1234567890, 1122334455);

-- Inserting into ContratJuridiqueOnePagerPourBP
INSERT INTO ContratJuridiqueOnePagerPourBP (IDOnePager, fonds, tasvee, SiretFonds, SiretTasvee)
VALUES (1, 1, 1, 234567890, 1122334455);

-- Inserting into ContratTripartiteFinal
INSERT INTO ContratTripartiteFinal (PropositionFinaleId, fonds, startUp, tasvee)
VALUES (1, 1, 1, 1);