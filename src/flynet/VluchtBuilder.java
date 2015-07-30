/*
 * Niet te gebruiken bij vdab-testen! Die moet je zelf maken.
 */
package flynet;

import flynet.exceptions.ToestelBehoortNietTotVlootException;
import flynet.personeel.VliegendPersoneelslid;
import flynet.vloot.LuchtVaartuig;
import flynet.vloot.VliegMaatschappij;
import java.util.Set;

public class VluchtBuilder {

    private int vluchtID;
    private String bestemming;
    private int duurtijd;
    private VliegMaatschappij vliegMaatschappij;
    private LuchtVaartuig toestel;
    private Set<VliegendPersoneelslid> personeelsleden;

    public VluchtBuilder() {
    }

    public VluchtBuilder setVluchtID(int vluchtID) {
        this.vluchtID = vluchtID;
        return this;
    }

    public VluchtBuilder setBestemming(String bestemming) {
        this.bestemming = bestemming;
        return this;
    }

    public VluchtBuilder setDuurtijd(int duurtijd) {
        this.duurtijd = duurtijd;
        return this;
    }

    public VluchtBuilder setVliegMaatschappij(VliegMaatschappij vliegMaatschappij) {
        this.vliegMaatschappij = vliegMaatschappij;
        return this;
    }

    public VluchtBuilder setToestel(LuchtVaartuig toestel) {
        this.toestel = toestel;
        return this;
    }

    public VluchtBuilder setPersoneelsleden(Set<VliegendPersoneelslid> personeelsleden) {
        this.personeelsleden = personeelsleden;
        return this;
    }

    public Vlucht createVlucht() throws ToestelBehoortNietTotVlootException {
        return new Vlucht(vluchtID, bestemming, duurtijd, vliegMaatschappij, toestel, personeelsleden);
    }

}
