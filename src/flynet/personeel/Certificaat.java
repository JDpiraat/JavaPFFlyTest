/*
 * Niet te gebruiken bij vdab-testen! Die moet je zelf maken.
 */
package flynet.personeel;

/**
 *
 * @author Johan
 */
public enum Certificaat {
    PPL("Private Pilot License"),
    ATPL("Airline Transport Pilot License"),
    CPL("Commercial Pilot License"),
    EHBO("First Aid"),
    EVAC("Evacution Procedures"),
    FIRE("Fire Fighting");
    
   private final String omschrijving;

    private Certificaat(String omschrijving) {
        this.omschrijving = omschrijving;
    }   

    public String getOmschrijving() {
        return omschrijving;
    }
    
}
