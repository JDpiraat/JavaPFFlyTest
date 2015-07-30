/*
 * Niet te gebruiken bij vdab-testen! Die moet je zelf maken.
 */
package flynet.personeel;

import flynet.Kost;
import flynet.exceptions.personeel.FouteGraadException;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Johan
 */
public class CabineCrew extends VliegendPersoneelslid implements Kost {

    private static final Set<Graad> toegelatenGraden = new HashSet<>();
    static {
        toegelatenGraden.add(Graad.PURSER);
        toegelatenGraden.add(Graad.STEWARD);
    }

    private static final BigDecimal TOESLAG_PURSER = BigDecimal.valueOf(1.2); // toeslag 20%
    private static final BigDecimal TOESLAG_EHBO_CERTIFICAAT = new BigDecimal(5); // toeslag 5€/dag

    private String werkpositie;
    private Graad graad;
    private BigDecimal basisKostprijsPerDag;

    /**
     * use CabineCrewBuilder to instanciate CabineCrew object
     * 
     * @param werkpositie
     * @param graad                 toegelaten waarden: PURSER, STEWARD.
     * @param basisKostprijsPerDag  > 0
     * @param certificaten
     * @param personeelsID
     * @param naam
     * @param adres 
     */
    CabineCrew(String werkpositie, Graad graad, BigDecimal basisKostprijsPerDag, Set<Certificaat> certificaten, String personeelsID, String naam, Adres adres) throws FouteGraadException{
        super(certificaten, personeelsID, naam, adres);
        this.werkpositie = werkpositie;
        try {
            setGraad(graad);
        } catch (FouteGraadException ex) {
            throw new FouteGraadException(naam + " kan niet gecreëerd worden: " + ex.toString());
        }
        setBasisKostprijsPerDag(basisKostprijsPerDag);
    }

    public String getWerkpositie() {
        return werkpositie;
    }

    public void setWerkpositie(String werkpositie) {
        this.werkpositie = werkpositie;
    }

    @Override
    public Graad getGraad() {
        return graad;
    }

    /**
     *
     * @param graad toegelaten waarden: PURSER, STEWARD.
     * @throws flynet.exceptions.personeel.FouteGraadException
     */
    @Override
    public final void setGraad(Graad graad) throws FouteGraadException {
        if (toegelatenGraden.contains(graad)) {
            this.graad = graad;
        } else {
            throw new FouteGraadException();
        }
    }

    @Override
    public BigDecimal getBasisKostprijsPerDag() {
        return basisKostprijsPerDag;
    }

    /**
     *
     * @param basisKostprijsPerDag moet groter dan 0 zijn.
     */
    @Override
    public final void setBasisKostprijsPerDag(BigDecimal basisKostprijsPerDag) {
        if (basisKostprijsPerDag.signum() == 1) {
            this.basisKostprijsPerDag = basisKostprijsPerDag;
        } else {
            throw new IllegalArgumentException("De kost moet groter dan 0 zijn.");
        }
    }

    @Override
    public BigDecimal BerekenTotaleKostprijsPerDag() {
        BigDecimal totaleKostprijsPerDag = basisKostprijsPerDag;
        if (graad == Graad.PURSER) {
            totaleKostprijsPerDag = totaleKostprijsPerDag.multiply(TOESLAG_PURSER);
        }
        if (getCertificaten().contains(Certificaat.EHBO)) {
            totaleKostprijsPerDag = totaleKostprijsPerDag.add(TOESLAG_EHBO_CERTIFICAAT);
        }
        return totaleKostprijsPerDag;
    }

    @Override
    public String toString() {
        return "CabineCrew{" + super.toString() + " werkpositie=" + werkpositie
                + ", graad=" + graad + ", basisKostprijsPerDag=" + basisKostprijsPerDag
                + ", totaleKostprijsPerDag=" + BerekenTotaleKostprijsPerDag() + '}';
    }

}
