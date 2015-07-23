/*
 * Niet te gebruiken bij vdab-testen! Die moet je zelf maken.
 */
package flynet.vloot;

import java.math.BigDecimal;

/**
 *
 * @author Johan
 */
public class PassagiersVliegtuig extends LuchtVaartuig {
    
    private static final BigDecimal TOESLAG_PER_ZETEL = new BigDecimal(10);
    
    private int aantalPassagiers; // het aantal zetels

    public PassagiersVliegtuig(int aantalPassagiers, BigDecimal basisKostprijsPerDag, String type, int kruissnelheid, int vliegbereik) {
        super(basisKostprijsPerDag, type, kruissnelheid, vliegbereik);
        this.aantalPassagiers = aantalPassagiers;
    }

    /**
     * 
     * @return het aantal zetels (aantal mogelijke passagiers)
     */
    public int getAantalPassagiers() {
        return aantalPassagiers;
    }

    /**
     * 
     * @param aantalPassagiers het aantal zetels (aantal mogelijke passagiers)
     */
    public void setAantalPassagiers(int aantalPassagiers) {
        this.aantalPassagiers = aantalPassagiers;
    }

    @Override
    public BigDecimal BerekenTotaleKostprijsPerDag() {
        return getBasisKostprijsPerDag().add(new BigDecimal(aantalPassagiers).multiply(TOESLAG_PER_ZETEL));
    }
    
}
