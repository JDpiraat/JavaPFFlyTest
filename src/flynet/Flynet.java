/*
 * Niet te gebruiken bij vdab-testen! Die moet je zelf maken.
 */
package flynet;

import flynet.personeel.Adres;
import flynet.personeel.CabineCrewBuilder;
// 3 verschillende manieren om hetzelfde te bereiken
import flynet.personeel.Certificaat;
import static flynet.personeel.Certificaat.*;
import static flynet.personeel.Certificaat.EVAC;

import flynet.personeel.CockpitCrew;
import flynet.personeel.NietVliegendPersoneelslid;
import flynet.personeel.Personeelslid;
import flynet.personeel.VliegendPersoneelslid.Graad;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 *
 * @author Johan
 */
public class Flynet {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Map<String, Personeelslid> personeelsleden = maakPersoneel();
        System.out.println("Overzicht personeelsleden:");
        System.out.println(personeelsleden);
    }

    private static Map<String, Personeelslid> maakPersoneel() {
        Adres europalaan = new Adres("Europalaan 37", "3600", "Genk");
        Adres keizerslaan = new Adres("Keizerslaan 11", "1000", "Brussel");

        Personeelslid nietVliegendPersoneelslid = null;
        try {
            nietVliegendPersoneelslid = new NietVliegendPersoneelslid(40,
                    NietVliegendPersoneelslid.Afdeling.LOGISTIEK, new BigDecimal(200),
                    "666", "Kip", keizerslaan);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }

        Personeelslid captainKirk = null;
        try {
            captainKirk = new CockpitCrew(5000, Graad.STEWARD, new BigDecimal(500),
                    new HashSet<>(Arrays.asList(Certificaat.PPL, Certificaat.ATPL, Certificaat.CPL)),
                    "001", "Captain Kirk", europalaan);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }

        Personeelslid spock = null;
        try {
            spock = new CockpitCrew(4500, Graad.SECONDOFFICER, new BigDecimal(400),
                    new HashSet<>(Arrays.asList(Certificaat.PPL, Certificaat.ATPL, Certificaat.CPL)),
                    "002", "Spock", keizerslaan);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }

        Personeelslid mcCoy = null;
        try {
            mcCoy = new CockpitCrew(4500, Graad.SENIORFLIGHTOFFICER, new BigDecimal(400),
                    new HashSet<>(Arrays.asList(Certificaat.PPL, Certificaat.EHBO)),
                    "003", "McCoy", europalaan);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
        Personeelslid pavelCheckov = null;
        try {
            pavelCheckov = new CabineCrewBuilder()
                    .setAdres(europalaan)
                    .setBasisKostprijsPerDag(new BigDecimal(300))
                    .setCertificaten(new HashSet<>(Arrays.asList(EHBO, EVAC)))
                    .setGraad(Graad.PURSER)
                    .setNaam("Pavel Chekov")
                    .setPersoneelsID("004")
                    .setWerkpositie("deur1")
                    .createCabineCrew();
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }

        Personeelslid hikaruSulu = null;
        try {
            hikaruSulu = new CabineCrewBuilder()
                    .setAdres(europalaan)
                    .setBasisKostprijsPerDag(new BigDecimal(300))
                    .setCertificaten(new HashSet<>(Arrays.asList(FIRE, EVAC)))
                    .setGraad(Graad.STEWARD)
                    .setNaam("Hikaru Sulu")
                    .setPersoneelsID("005")
                    .setWerkpositie("deur2")
                    .createCabineCrew();
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }

        Personeelslid skyWalker = null;
        try {
            skyWalker = new CabineCrewBuilder()
                    .setAdres(keizerslaan)
                    .setBasisKostprijsPerDag(new BigDecimal(300))
                    .setCertificaten(new HashSet<>(Arrays.asList(FIRE, EHBO)))
                    .setGraad(Graad.STEWARD)
                    .setNaam("Sky Walker")
                    .setPersoneelsID("006")
                    .setWerkpositie("nooduitgang")
                    .createCabineCrew();
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }

        Map<String, Personeelslid> personeelsleden = new HashMap<>();

        personeelsleden.put(
                "niet vliegend personeelslid", nietVliegendPersoneelslid);
        personeelsleden.put(
                "Captain Kirk", captainKirk);
        personeelsleden.put(
                "Spock", spock);
        personeelsleden.put(
                "McCoy", mcCoy);
        personeelsleden.put(
                "Pavel Checkov", pavelCheckov);
        personeelsleden.put(
                "Hikaru Sulu", hikaruSulu);
        personeelsleden.put(
                "Sky Walker", skyWalker);

        return personeelsleden;
    }

}
