/*
 * Niet te gebruiken bij vdab-testen! Die moet je zelf maken.
 */
package flynet.personeel;

import java.util.Objects;

/**
 *
 * @author Johan
 */
public abstract class Personeelslid {
    
    private final String personeelsID;
    private final String naam;
    private Adres adres;

    protected Personeelslid(String personeelsID, String naam, Adres adres) {
        this.personeelsID = personeelsID;
        this.naam = naam;
        this.adres = adres;
    }
    
    public String getPersoneelsID() {
        return personeelsID;
    }
    
    public String getNaam() {
        return naam;
    }   

    public Adres getAdres() {
        return adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    // 't is JPF dus de ID mag hier wel nog gebruikt worden (nog niets van pesistence dus ... klein beetje dirty wel)
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.personeelsID);
        hash = 79 * hash + Objects.hashCode(this.naam);
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
        final Personeelslid other = (Personeelslid) obj;
        if (!Objects.equals(this.personeelsID, other.personeelsID)) {
            return false;
        }
        if (!Objects.equals(this.naam, other.naam)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Personeelslid{" + "personeelsID=" + personeelsID + ", naam=" + naam + ", adres=" + adres + '}';
    }
    
}
