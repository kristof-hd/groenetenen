package be.vdab.groenetenen.services;

import java.util.Optional;

import be.vdab.groenetenen.entities.Offerte;

public interface OfferteService {
	//void create(Offerte offerte);
	void create(Offerte offerte, String offertesURL);
	void aantalOffertesMail(); 	
	Optional<Offerte> read(long id);
	
}
