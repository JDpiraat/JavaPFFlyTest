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
public class CockpitCrew extends VliegendPersoneelslid implements Kost {

    private static final Set<Graad> toegelatenGraden = new HashSet<>();

    static {
        toegelatenGraden.add(Graad.CAPTAIN);
        toegelatenGraden.add(Graad.JUNIORFLIGTOFFICER);
        toegelatenGraden.add(Graad.SECONDOFFICER);
        toegelatenGraden.add(Graad.SENIORFLIGHTOFFICER);
    }
    
    private static final BigDecimal TOESLAG_CAPTAIN = BigDecimal.valueOf(1.3);// toeslag 30%
    private static final BigDecimal TOESLAG_SENIORFLIGHTOFFICER = new BigDecimal("1.2");// toeslag 20%
    // meest 'archaïsche' manier om hier de floating point representation-problemen uit de weg te helpen:
    private static final BigDecimal TOESLAG_SECONDOFFICER = new BigDecimal(11).divide(BigDecimal.TEN);// toeslag 10%  
    
    private static final BigDecimal TOESLAG_CPL_CERTIFICAAT = new BigDecimal(50); // toeslag 50€/dag
    
    private int vlieguren;
    private Graad graad;
    private BigDecimal basisKostprijsPerDag;

    public CockpitCrew(int vlieguren, Graad graad, BigDecimal basisKostprijsPerDag, Set<Certificaat> certificaten, String personeelsID, String naam, Adres adres) {
        super(certificaten, personeelsID, naam, adres);
        this.vlieguren = vlieguren;
        try {
            setGraad(graad);
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException(naam + " kan niet gecreeert worden: " + ex.getMessage());
        }
        setBasisKostprijsPerDag(basisKostprijsPerDag);
    }

    public int getVlieguren() {
        return vlieguren;
    }

    public void setVlieguren(int vlieguren) {
        this.vlieguren = vlieguren;
    }

    @Override
    public Graad getGraad() {
        return graad;
    }

    /**
     * 
     * @param graad toegelaten waarden: CAPTAIN, JUNIORFLIGTOFFICER, SECONDOFFICER, SENIORFLIGHTOFFICER.
     */
    @Override
    public final void setGraad(Graad graad) {
        if (toegelatenGraden.contains(graad)) {
            this.graad = graad;
        } else throw new IllegalArgumentException("Foutieve graad!");
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
        switch (graad) {
            case CAPTAIN:
                totaleKostprijsPerDag = totaleKostprijsPerDag.multiply(TOESLAG_CAPTAIN);
                break;
            case SENIORFLIGHTOFFICER:
                totaleKostprijsPerDag = totaleKostprijsPerDag.multiply(TOESLAG_SENIORFLIGHTOFFICER);
                break;
            case SECONDOFFICER:
                totaleKostprijsPerDag = totaleKostprijsPerDag.multiply(TOESLAG_SECONDOFFICER);
                break;            
        }
        if (super.getCertificaten().contains(Certificaat.CPL)){
            totaleKostprijsPerDag = totaleKostprijsPerDag.add(TOESLAG_CPL_CERTIFICAAT);
        }
        return totaleKostprijsPerDag;
    }

    @Override
    public String toString() {
        return "CockpitCrew{" + super.toString() + " vlieguren=" + vlieguren + ", graad=" + graad 
                + ", basisKostprijsPerDag=" + basisKostprijsPerDag 
                + ", totaleKostprijsPerDag=" + BerekenTotaleKostprijsPerDag() + '}';
    }
    
}
