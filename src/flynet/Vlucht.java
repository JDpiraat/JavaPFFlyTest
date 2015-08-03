/*
 * Niet te gebruiken bij vdab-testen! Die moet je zelf maken.
 */
package flynet;

import flynet.exceptions.ToestelBehoortNietTotVlootException;
import flynet.personeel.VliegendPersoneelslid;
import flynet.vloot.LuchtVaartuig;
import flynet.vloot.VliegMaatschappij;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author Johan
 */
public class Vlucht implements Serializable {

    private final int vluchtID;
    private final String bestemming;
    private final int duurtijd;
    // om het snel te kunnen wegschrijven ...
    private final transient VliegMaatschappij vliegMaatschappij;
    private final transient LuchtVaartuig toestel;
    private final transient Set<VliegendPersoneelslid> personeelsleden;

    Vlucht(int vluchtID, String bestemming, int duurtijd, VliegMaatschappij vliegMaatschappij, LuchtVaartuig toestel, Set<VliegendPersoneelslid> personeelsleden)
            throws ToestelBehoortNietTotVlootException {
        this.vluchtID = vluchtID;
        this.bestemming = bestemming;
        this.duurtijd = duurtijd;
        this.vliegMaatschappij = vliegMaatschappij;
        if (vliegMaatschappij.getVloot().contains(toestel)) {
            this.toestel = toestel;
        } else {
            throw new ToestelBehoortNietTotVlootException(" De vliegmaatschappij "
                    + vliegMaatschappij.getVliegMaatschappijNaam()
                    + "  heeft geen toestel van dit type (" + toestel.getType() + "). ");
        }
        this.personeelsleden = personeelsleden;
    }

    public int getVluchtID() {
        return vluchtID;
    }

    public String getBestemming() {
        return bestemming;
    }

    public int getDuurtijd() {
        return duurtijd;
    }

    public VliegMaatschappij getVliegMaatschappij() {
        return vliegMaatschappij;
    }

    public LuchtVaartuig getToestel() {
        return toestel;
    }

    public Set<VliegendPersoneelslid> getPersoneelsleden() {
        return personeelsleden;
    }

    public BigDecimal berekenVluchtKost() {
        BigDecimal vluchtKostPerDag = toestel.BerekenTotaleKostprijsPerDag();
        for (VliegendPersoneelslid personeelslid : personeelsleden) {
            vluchtKostPerDag = vluchtKostPerDag.add(((Kost)personeelslid).BerekenTotaleKostprijsPerDag());            
        }
        return vluchtKostPerDag.multiply(new BigDecimal(duurtijd));
    }    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.vluchtID;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Vlucht other = (Vlucht) obj;
        if (this.vluchtID != other.vluchtID) {
            return false;
        }
        if (!Objects.equals(this.bestemming, other.bestemming)) {
            return false;
        }
        if (this.duurtijd != other.duurtijd) {
            return false;
        }
        if (!Objects.equals(this.vliegMaatschappij, other.vliegMaatschappij)) {
            return false;
        }
        if (!Objects.equals(this.toestel, other.toestel)) {
            return false;
        }
        return Objects.equals(this.personeelsleden, other.personeelsleden);
    }

    @Override
    public String toString() {
        return "Vlucht{" + "vluchtID=" + vluchtID + ", bestemming="
                + bestemming + ", duurtijd=" + duurtijd + ", vliegMaatschappij="
                + vliegMaatschappij + ", toestel=" + toestel + ", personeelsleden="
                + personeelsleden + '}';
    }

}
