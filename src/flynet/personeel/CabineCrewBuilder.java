/*
 * Niet te gebruiken bij vdab-testen! Die moet je zelf maken.
 */
package flynet.personeel;

import flynet.exceptions.personeel.FouteGraadException;
import flynet.personeel.VliegendPersoneelslid.Graad;
import java.math.BigDecimal;
import java.util.Set;

// vb-tje: dit wordt niet verwacht, maar zou wel mogen (constructor met veel parameters, Builder is ook auto-generated code).
/**
 * 
 * @author Johan
 */
public class CabineCrewBuilder {

    private String werkpositie;
    private Graad graad;
    private BigDecimal basisKostprijsPerDag;
    private Set<Certificaat> certificaten;
    private String personeelsID;
    private String naam;
    private Adres adres;

    public CabineCrewBuilder() {
    }

    public CabineCrewBuilder setWerkpositie(String werkpositie) {
        this.werkpositie = werkpositie;
        return this;
    }

    public CabineCrewBuilder setGraad(Graad graad) {
        this.graad = graad;
        return this;
    }

    public CabineCrewBuilder setBasisKostprijsPerDag(BigDecimal basisKostprijsPerDag) {
        this.basisKostprijsPerDag = basisKostprijsPerDag;
        return this;
    }

    public CabineCrewBuilder setCertificaten(Set<Certificaat> certificaten) {
        this.certificaten = certificaten;
        return this;
    }

    public CabineCrewBuilder setPersoneelsID(String personeelsID) {
        this.personeelsID = personeelsID;
        return this;
    }

    public CabineCrewBuilder setNaam(String naam) {
        this.naam = naam;
        return this;
    }

    public CabineCrewBuilder setAdres(Adres adres) {
        this.adres = adres;
        return this;
    }

    public CabineCrew createCabineCrew() throws FouteGraadException {
        return new CabineCrew(werkpositie, graad, basisKostprijsPerDag, certificaten, personeelsID, naam, adres);
    }

}
