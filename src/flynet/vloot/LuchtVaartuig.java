/*
 * Niet te gebruiken bij vdab-testen! Die moet je zelf maken.
 */
package flynet.vloot;

import flynet.Kost;
import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author Johan
 */
public abstract class LuchtVaartuig implements Kost {

    private BigDecimal basisKostprijsPerDag;
    private String type;
    private int kruissnelheid;
    private int vliegbereik;

    /**
     *
     * @param basisKostprijsPerDag
     * @param type
     * @param kruissnelheid (km/u)
     * @param vliegbereik (km)
     */
    protected LuchtVaartuig(BigDecimal basisKostprijsPerDag, String type, int kruissnelheid, int vliegbereik) {
        setBasisKostprijsPerDag(basisKostprijsPerDag);
        this.type = type;
        this.kruissnelheid = kruissnelheid;
        this.vliegbereik = vliegbereik;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     *
     * @return de kruissnelheid in km/u.
     */
    public int getKruissnelheid() {
        return kruissnelheid;
    }

    /**
     *
     * @param kruissnelheid (km/u)
     */
    public void setKruissnelheid(int kruissnelheid) {
        this.kruissnelheid = kruissnelheid;
    }

    /**
     *
     * @return het vliegbereik in km.
     */
    public int getVliegbereik() {
        return vliegbereik;
    }

    /**
     *
     * @param vliegbereik (km)
     */
    public void setVliegbereik(int vliegbereik) {
        this.vliegbereik = vliegbereik;
    }    

    @Override
    public String toString() {
        return "LuchtVaartuig{\n" + "basisKostprijsPerDag=" + basisKostprijsPerDag
                + "\n\ttype=" + type + "\n\tkruissnelheid=" + kruissnelheid
                + "\n\tvliegbereik=" + vliegbereik + '}';
    }

}
