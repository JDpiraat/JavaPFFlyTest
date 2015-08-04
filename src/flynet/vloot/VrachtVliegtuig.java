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
public class VrachtVliegtuig extends LuchtVaartuig {
    
    private static final BigDecimal TOESLAG_PER_TON_LAADVERMOGEN = new BigDecimal(100);
    
    private int laadvermogen;

    public VrachtVliegtuig(int laadvermogen, BigDecimal basisKostprijsPerDag, String type, int kruissnelheid, int vliegbereik) {
        super(basisKostprijsPerDag, type, kruissnelheid, vliegbereik);
        this.laadvermogen = laadvermogen;
    }    

    /**
     * 
     * @return  laadvermogen in ton.
     */
    public int getLaadvermogen() {
        return laadvermogen;
    }

    /**
     * 
     * @param laadvermogen (ton)
     */
    public void setLaadvermogen(int laadvermogen) {
        this.laadvermogen = laadvermogen;
    }
    
    @Override
    public BigDecimal BerekenTotaleKostprijsPerDag() {
        return getBasisKostprijsPerDag().add(new BigDecimal(laadvermogen).multiply(TOESLAG_PER_TON_LAADVERMOGEN));
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
