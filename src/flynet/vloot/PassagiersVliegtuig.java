/*
 * Niet te gebruiken bij vdab-testen! Die moet je zelf maken.
 */
package flynet.vloot;

import java.math.BigDecimal;
import java.util.Objects;

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
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.getType());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof LuchtVaartuig) {
            final LuchtVaartuig other = (LuchtVaartuig) obj;
            return Objects.equals(this.getType(), other.getType());
        }
        return false;
    }
    
}
