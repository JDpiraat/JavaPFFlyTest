/*
 * Niet te gebruiken bij vdab-testen! Die moet je zelf maken.
 */
package flynet.personeel;

import java.util.Objects;

/**
 *
 * @author Johan
 */
public class Adres {
    
    private final String straatNr;
    private final String postcode;
    private final String gemeente;

    public Adres(String straatNr, String postcode, String gemeente) {
        this.straatNr = straatNr;
        this.postcode = postcode;
        this.gemeente = gemeente;
    }

    public String getStraatNr() {
        return straatNr;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getGemeente() {
        return gemeente;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.straatNr);
        hash = 97 * hash + Objects.hashCode(this.postcode);
        hash = 97 * hash + Objects.hashCode(this.gemeente);
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
        final Adres other = (Adres) obj;
        if (!Objects.equals(this.straatNr, other.straatNr)) {
            return false;
        }
        if (!Objects.equals(this.postcode, other.postcode)) {
            return false;
        }
        if (!Objects.equals(this.gemeente, other.gemeente)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Adres{" + "straatNr=" + straatNr + ", postcode=" + postcode + ", gemeente=" + gemeente + '}';
    }
    
}
