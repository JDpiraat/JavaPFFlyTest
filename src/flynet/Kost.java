/*
 * Niet te gebruiken bij vdab-testen! Die moet je zelf maken.
 */
package flynet;

import java.math.BigDecimal;

/**
 *
 * @author Johan
 */
public interface Kost {
    
    void setBasisKostprijsPerDag(BigDecimal basisKostprijsPerDag);
    BigDecimal getBasisKostprijsPerDag();
    
    BigDecimal BerekenTotaleKostprijsPerDag();
    
}
