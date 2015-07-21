/*
 * Niet te gebruiken bij vdab-testen! Die moet je zelf maken.
 */
package flynet.personeel;

import flynet.Kost;
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

    private static final BigDecimal TOESLAG_PURSER = new BigDecimal(1.2);

    private String werkpositie;
    private Graad graad;
    private BigDecimal basisKostprijsPerDag;

    public CabineCrew(String werkpositie, Graad graad, BigDecimal basisKostprijsPerDag, Set<Certificaat> certificaten, String personeelsID, String naam, Adres adres) {
        super(certificaten, personeelsID, naam, adres);
        this.werkpositie = werkpositie;
        setGraad(graad);
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
     */
    @Override
    public final void setGraad(Graad graad) {
        if (toegelatenGraden.contains(graad)) {
            this.graad = graad;
        } else {
            throw new IllegalArgumentException("Foutieve graad!");
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
        return totaleKostprijsPerDag;
    }

    @Override
    public String toString() {
        return "CabineCrew{" + super.toString() + " werkpositie=" + werkpositie
                + ", graad=" + graad + ", basisKostprijsPerDag=" + basisKostprijsPerDag
                + ", totaleKostprijsPerDag=" + BerekenTotaleKostprijsPerDag() + '}';
    }

}
