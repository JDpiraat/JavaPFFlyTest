/*
 * Niet te gebruiken bij vdab-testen! Die moet je zelf maken.
 */
package flynet.personeel;

import java.util.Set;

/**
 *
 * @author Johan
 */
public abstract class VliegendPersoneelslid extends Personeelslid {

    private final Set<Certificaat> certificaten;

    protected VliegendPersoneelslid(Set<Certificaat> certificaten, String personeelsID, String naam, Adres adres) {
        super(personeelsID, naam, adres);
        this.certificaten = certificaten;
    }

    public Set<Certificaat> getCertificaten() {
        return certificaten;
    }

    public void addCertificaten(Certificaat certificaat) {
        if (!certificaten.contains(certificaat)) {
            certificaten.add(certificaat);
        }
    }

    public void removeCertificaten(Certificaat certificaat) {
        certificaten.remove(certificaat);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(super.toString().replace("Personeelslid", "Vliegend personeelslid"));
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.append("certificaten=");
        stringBuilder.append(certificaten);
        stringBuilder.append('}');
        return stringBuilder.toString();
    }

    public abstract Graad getGraad();

    public abstract void setGraad(Graad graad);

    public enum Graad {

        CAPTAIN,
        SENIORFLIGHTOFFICER,
        SECONDOFFICER,
        JUNIORFLIGTOFFICER,
        STEWARD,
        PURSER;        
    }
}
