/*
 * Niet te gebruiken bij vdab-testen! Die moet je zelf maken.
 */
package flynet;

import flynet.personeel.Adres;
import flynet.personeel.Certificaat;
import static flynet.personeel.Certificaat.ATPL;
import static flynet.personeel.Certificaat.CPL;
import flynet.personeel.CockpitCrew;
import flynet.personeel.NietVliegendPersoneelslid;
import flynet.personeel.Personeelslid;
import flynet.personeel.VliegendPersoneelslid;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;

/**
 *
 * @author Johan
 */
public class Flynet {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        testPersoneel();
    }

    private static void testPersoneel() {
        Adres europalaan = new Adres("Europalaan 37", "3600", "Genk");
        Adres keizerslaan = new Adres("Keizerslaan 11", "1000", "Brussel");
        
        Personeelslid nietVliegendPersoneelslid = new NietVliegendPersoneelslid(40, NietVliegendPersoneelslid.Afdeling.LOGISTIEK, new BigDecimal(200), "666", "Kip", keizerslaan);
        System.out.println(nietVliegendPersoneelslid);
        Personeelslid captainKirk = new CockpitCrew(5000, VliegendPersoneelslid.Graad.CAPTAIN, new BigDecimal(500), new HashSet<>(Arrays.asList(Certificaat.PPL, Certificaat.ATPL, Certificaat.CPL)), "001", "Captain Kirk", europalaan);
        System.out.println(captainKirk);
    }
    
}
