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
import flynet.personeel.VliegendPersoneelslid;
import flynet.personeel.VliegendPersoneelslid.Graad;
import flynet.vloot.LuchtVaartuig;
import flynet.vloot.PassagiersVliegtuig;
import flynet.vloot.VliegMaatschappij;
import flynet.vloot.VrachtVliegtuig;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Johan
 */
public class Flynet {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //*** Personeel
        Map<String, Personeelslid> personeelsleden = maakPersoneel();
        System.out.println("Overzicht personeelsleden:");
        System.out.println(personeelsleden);
        
        //*** Luchtvaartuigen
        Map<String, LuchtVaartuig> toestellen = maakLuchtvaartuigen();
        System.out.println("Overzicht toestellen:");
        for (LuchtVaartuig toestel : toestellen.values()) {
            System.out.println(toestel);
        }
        
        //*** VliegMaatschapijen
        VliegMaatschappij brusselAirlines = new VliegMaatschappij(1, "BrusselsAirlines"
                , Arrays.<LuchtVaartuig>asList(
                        toestellen.get("airbusA319"), toestellen.get("boeing737"), 
                        toestellen.get("boeing787"), toestellen.get("britishAerospace146")));
        
        VliegMaatschappij TNTAirways = new VliegMaatschappij(2, "TNTAirways"
                , Arrays.<LuchtVaartuig>asList(
                        toestellen.get("antonovAn30"), toestellen.get("antonovAn225"), 
                        toestellen.get("antonovAn30")));
        
        VliegMaatschappij jetairfly = new VliegMaatschappij(1, "JetairFly"
                , Arrays.<LuchtVaartuig>asList(
                        toestellen.get("boeing737"), toestellen.get("boeing787")));
        
        VliegMaatschappij thomasCook = new VliegMaatschappij(1, "Thomascook"
                , Arrays.<LuchtVaartuig>asList(
                        toestellen.get("airbusA319"), toestellen.get("boeing787")));
        
        //*** Vluchten
        //* New York
        Vlucht newYork = null;
        Set<VliegendPersoneelslid> personeelNewYork = new HashSet<>();
        personeelNewYork.add((VliegendPersoneelslid) personeelsleden.get("Captain Kirk"));
        personeelNewYork.add((VliegendPersoneelslid) personeelsleden.get("Spock"));
        personeelNewYork.add((VliegendPersoneelslid) personeelsleden.get("Pavel Checkov"));
        personeelNewYork.add((VliegendPersoneelslid) personeelsleden.get("Hikaru Sulu"));
        
        try {
        newYork = new VluchtBuilder().setBestemming("New York")
                                            .setDuurtijd(2)
                                            .setPersoneelsleden(personeelNewYork)
                                            .setToestel(toestellen.get("boeing787"))
                                            .setVliegMaatschappij(thomasCook)
                                            .setVluchtID(1)
                                            .createVlucht();
        } catch (Vlucht.ToestelBehoortNietTotVlootException ex) {
            System.out.println(ex.getMessage());
        }
        
        //*** output
        System.out.println(newYork);
        System.out.println("Totale kostprijs: " + ((newYork != null)? newYork.berekenVluchtKost() : "XXXXXX"));
        // System.out.println("toestel: " + newYork.getToestel().BerekenTotaleKostprijsPerDag().multiply(new BigDecimal(newYork.getDuurtijd())));
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
            captainKirk = new CockpitCrew(5000, Graad.CAPTAIN, new BigDecimal(500),
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
    
    private static Map<String, LuchtVaartuig> maakLuchtvaartuigen() {
        LuchtVaartuig airbusA319 = new PassagiersVliegtuig(141, new BigDecimal(1000), "AirbusA319", 880, 6850);
        LuchtVaartuig boeing737 = new PassagiersVliegtuig(190, new BigDecimal(1500), "Boeing737", 850, 12500);
        LuchtVaartuig boeing787 = new PassagiersVliegtuig(330, new BigDecimal(2000), "Boeing787", 913, 15700);
        LuchtVaartuig antonovAn30 = new VrachtVliegtuig(8, new BigDecimal(300), "AntonovAn-30", 430, 1600);
        LuchtVaartuig britishAerospace146 = new VrachtVliegtuig(42, new BigDecimal(600), "BritishAerospace146", 750, 1600);
        LuchtVaartuig antonovAn225 = new VrachtVliegtuig(425, new BigDecimal(1500), "AntonovAn-225", 800, 15400);
        
        Map<String, LuchtVaartuig> toestellen = new HashMap<>();
        toestellen.put("airbusA319", airbusA319);
        toestellen.put("boeing737", boeing737);
        toestellen.put("boeing787", boeing787);
        toestellen.put("antonovAn30", antonovAn30);
        toestellen.put("britishAerospace146", britishAerospace146);
        toestellen.put("antonovAn225", antonovAn225);
        
        return toestellen;
    }
    
}
