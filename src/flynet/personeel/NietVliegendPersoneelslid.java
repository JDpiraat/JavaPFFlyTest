/*
 * Niet te gebruiken bij vdab-testen! Die moet je zelf maken.
 */
package flynet.personeel;

import flynet.Kost;
import java.math.BigDecimal;

/**
 *
 * @author Johan
 */
public class NietVliegendPersoneelslid extends Personeelslid implements Kost {

    private int urenPerWeek;
    private Afdeling afdeling;
    private BigDecimal basisKostprijsPerDag;

    public NietVliegendPersoneelslid(int urenPerWeek, Afdeling afdeling, BigDecimal basisKostprijsPerDag, String personeelsID, String naam, Adres adres) {
        super(personeelsID, naam, adres);
        setUrenPerWeek(urenPerWeek);
        this.afdeling = afdeling;
        setBasisKostprijsPerDag(basisKostprijsPerDag);
    }

    public int getUrenPerWeek() {
        return urenPerWeek;
    }

    /**
     *
     * @param urenPerWeek minstens 1 en maximum 60 werkuren per week.
     */
    public final void setUrenPerWeek(int urenPerWeek) {
        if (urenPerWeek > 0 && urenPerWeek < 60) {
            this.urenPerWeek = urenPerWeek;
        } else {
            // veel beter is een eigen checked exception te schrijven ipv IllegalArgumentException te gebruiken
            throw new IllegalArgumentException("Wij willen personeel dat werkt en zich niet overwerkt (uren per week tussen 0 en 60).");
        }
    }

    public Afdeling getAfdeling() {
        return afdeling;
    }

    public void setAfdeling(Afdeling afdeling) {
        this.afdeling = afdeling;
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
    public BigDecimal getBasisKostprijsPerDag() {
        return basisKostprijsPerDag;
    }

    @Override
    public BigDecimal BerekenTotaleKostprijsPerDag() {
        return basisKostprijsPerDag;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(super.toString().replace("Personeelslid", "Niet vliegend personeelslid"));
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.append(", urenPerWeek=");
        stringBuilder.append(urenPerWeek);
        stringBuilder.append(", afdeling=");
        stringBuilder.append(afdeling);
        stringBuilder.append('}');
        return stringBuilder.toString();
    }

    public enum Afdeling {

        PERSONEELSADMINISTRATIE,
        BOEKHOUDING,
        INCHECKBALIE,
        LOGISTIEK;
    }

}
