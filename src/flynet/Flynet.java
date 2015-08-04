/*
 * Niet te gebruiken bij vdab-testen! Die moet je zelf maken.
 */
package flynet;

import flynet.exceptions.ToestelBehoortNietTotVlootException;
import flynet.exceptions.personeel.FouteGraadException;
import flynet.personeel.Adres;
import flynet.personeel.CabineCrew;
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
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Johan
 */
public class Flynet {

    /**
     * @param args the command line arguments, hier dus geen.
     */
    public static void main(String[] args) throws ClassNotFoundException {
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
        VliegMaatschappij brusselAirlines = new VliegMaatschappij(1, "BrusselsAirlines", Arrays.<LuchtVaartuig>asList(
                toestellen.get("airbusA319"), toestellen.get("boeing737"),
                toestellen.get("boeing787"), toestellen.get("britishAerospace146")));

        VliegMaatschappij TNTAirways = new VliegMaatschappij(2, "TNTAirways", Arrays.<LuchtVaartuig>asList(
                toestellen.get("antonovAn30"), toestellen.get("antonovAn225"),
                toestellen.get("antonovAn30")));

        VliegMaatschappij jetairfly = new VliegMaatschappij(1, "JetairFly", Arrays.<LuchtVaartuig>asList(
                toestellen.get("boeing737"), toestellen.get("boeing787")));

        VliegMaatschappij thomasCook = new VliegMaatschappij(1, "Thomascook", Arrays.<LuchtVaartuig>asList(
                toestellen.get("airbusA319"), toestellen.get("boeing787")));

        //*** Vluchten      
        List<Vlucht> vluchten = new ArrayList<>();
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
                    .setVliegMaatschappij(brusselAirlines)
                    .setVluchtID(1)
                    .createVlucht();
        } catch (ToestelBehoortNietTotVlootException ex) {
            System.out.println(ex.getMessage());
        }
        vluchten.add(newYork);

        //* Beijing
        Vlucht beijing = null;
        Set<VliegendPersoneelslid> personeelBeijing = new HashSet<>();
        personeelBeijing.add((VliegendPersoneelslid) personeelsleden.get("Captain Kirk"));
        personeelBeijing.add((VliegendPersoneelslid) personeelsleden.get("Skywalker"));

        try {
            beijing = new VluchtBuilder().setBestemming("Beijing")
                    .setDuurtijd(1)
                    .setPersoneelsleden(personeelBeijing)
                    .setToestel(toestellen.get("antonovAn225"))
                    .setVliegMaatschappij(TNTAirways)
                    .setVluchtID(2)
                    .createVlucht();
        } catch (ToestelBehoortNietTotVlootException ex) {
            System.out.println(ex.getMessage());
        }
        vluchten.add(beijing);

        //* Sydney
        Vlucht sydney = null;
        Set<VliegendPersoneelslid> personeelSydney = new HashSet<>();
        personeelSydney.add((VliegendPersoneelslid) personeelsleden.get("Captain Kirk"));
        personeelSydney.add((VliegendPersoneelslid) personeelsleden.get("Spock"));
        personeelSydney.add((VliegendPersoneelslid) personeelsleden.get("Pavel Checkov"));
        personeelSydney.add((VliegendPersoneelslid) personeelsleden.get("Hikaru Sulu"));

        try {
            sydney = new VluchtBuilder().setBestemming("Sydney")
                    .setDuurtijd(3)
                    .setPersoneelsleden(personeelSydney)
                    .setToestel(toestellen.get("airbusA319"))
                    .setVliegMaatschappij(thomasCook)
                    .setVluchtID(3)
                    .createVlucht();
        } catch (ToestelBehoortNietTotVlootException ex) {
            System.out.println(ex.getMessage());
        }
        vluchten.add(sydney);

        //* Signapore
        Vlucht signapore = null;
        Set<VliegendPersoneelslid> personeelSignapore = new HashSet<>();
        personeelSignapore.add((VliegendPersoneelslid) personeelsleden.get("McCoy"));
        personeelSignapore.add((VliegendPersoneelslid) personeelsleden.get("Hikaru Sulu"));
        personeelSignapore.add((VliegendPersoneelslid) personeelsleden.get("Skywalker"));
        try {
            signapore = new VluchtBuilder().setBestemming("Signapore")
                    .setDuurtijd(2)
                    .setPersoneelsleden(personeelSignapore)
                    .setToestel(toestellen.get("britishAerospace146"))
                    .setVliegMaatschappij(brusselAirlines)
                    .setVluchtID(4)
                    .createVlucht();
        } catch (ToestelBehoortNietTotVlootException ex) {
            System.out.println(ex.getMessage());
        }
        vluchten.add(signapore);

        //* Malta
        Vlucht malta = null;
        Set<VliegendPersoneelslid> personeelMalta = new HashSet<>();
        personeelMalta.add((VliegendPersoneelslid) personeelsleden.get("Captain Kirk"));
        personeelMalta.add((VliegendPersoneelslid) personeelsleden.get("Spock"));
        personeelMalta.add((VliegendPersoneelslid) personeelsleden.get("Skywalker"));

        try {
            malta = new VluchtBuilder().setBestemming("Malta")
                    .setDuurtijd(1)
                    .setPersoneelsleden(personeelMalta)
                    .setToestel(toestellen.get("boeing737"))
                    .setVliegMaatschappij(jetairfly)
                    .setVluchtID(5)
                    .createVlucht();
        } catch (ToestelBehoortNietTotVlootException ex) {
            System.out.println(ex.getMessage());
        }
        vluchten.add(malta);

        //*** output
        // opdeling vrachtvluchten vs passagiersvluchten 
        //(veel makkelijker met streams maar dat volgt na programming fundamentals 
        //(zie ook de fork 'java8 features'))
        List<Vlucht> vrachtvluchten = new ArrayList<>();
        List<Vlucht> passagiersvluchten = new ArrayList<>();
        for (Vlucht vlucht : vluchten) {
            if (vlucht.getToestel() instanceof VrachtVliegtuig) {
                vrachtvluchten.add(vlucht);
            } else if (vlucht.getToestel() instanceof PassagiersVliegtuig) {
                passagiersvluchten.add(vlucht);
            } else {
                // doe dit zelf nooit, zomaar een runtimeException werpen ...
                throw new RuntimeException("Een nieuwe vliegtuigsoort: UFO?!");
            }
        }
        // en printen ...
        if (!vrachtvluchten.isEmpty()) {
            System.out.println(DOUBLEDASH80);
            System.out.println("VRACHTVLUCHTEN:");
            System.out.println(DOUBLEDASH80);
            for (Vlucht vrachtvlucht : vrachtvluchten) {
                printVluchtGegevens(vrachtvlucht);
                System.out.println("\n");
            }
        }
        if (!passagiersvluchten.isEmpty()) {
            System.out.println(DOUBLEDASH80);
            System.out.println("PASSAGIERSVLUCHTEN:");
            System.out.println(DOUBLEDASH80);
            for (Vlucht passagiersvlucht : passagiersvluchten) {
                printVluchtGegevens(passagiersvlucht);
                System.out.println("\n");
            }
        }
        System.out.println("\n" + DOUBLEDASH80);

        //*** wegschrijven naar file en terug inlezen
        // zeer basis: vraag is of dat dit zo (ook als 'alles' serializable is) een meerwaarde is... aan de andere kant: weinig werk...
        final String FLYNET_DAT_FILE = "flynet.dat";
        
        try (FileOutputStream fos = new FileOutputStream(FLYNET_DAT_FILE); ObjectOutputStream oos = new ObjectOutputStream(fos);) {
            oos.writeObject(vluchten);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        List<Vlucht> ingelezenVluchten = null;
        try (FileInputStream fos = new FileInputStream(FLYNET_DAT_FILE); ObjectInputStream oos = new ObjectInputStream(fos);) {
            ingelezenVluchten = (List<Vlucht>) oos.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        for (Vlucht vlucht : ingelezenVluchten) {
            System.out.println(vlucht);
        }

    } 

    // omkeren keys en values ... normaal zijn de keys de unieke objecten (en de values een waarde die niet uniek hoeft te zijn)
    // dit is puur voor mijn gemak (ipv allemaal globale var's bv, vond ik dit, wel ... eens iets anders ...)
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
        } catch (FouteGraadException ex) {
            System.out.println(ex.getMessage());
        }

        Personeelslid spock = null;
        try {
            spock = new CockpitCrew(4500, Graad.SECONDOFFICER, new BigDecimal(400),
                    new HashSet<>(Arrays.asList(Certificaat.PPL, Certificaat.ATPL, Certificaat.CPL)),
                    "002", "Spock", keizerslaan);
        } catch (FouteGraadException ex) {
            System.out.println(ex.getMessage());
        }

        Personeelslid mcCoy = null;
        try {
            mcCoy = new CockpitCrew(4500, Graad.SENIORFLIGHTOFFICER, new BigDecimal(400),
                    new HashSet<>(Arrays.asList(Certificaat.PPL, Certificaat.EHBO)),
                    "003", "McCoy", europalaan);
        } catch (FouteGraadException ex) {
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
        } catch (FouteGraadException ex) {
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
        } catch (FouteGraadException ex) {
            System.out.println(ex.getMessage());
        }

        Personeelslid skywalker = null;
        try {
            skywalker = new CabineCrewBuilder()
                    .setAdres(keizerslaan)
                    .setBasisKostprijsPerDag(new BigDecimal(300))
                    .setCertificaten(new HashSet<>(Arrays.asList(FIRE, EHBO)))
                    .setGraad(Graad.STEWARD)
                    .setNaam("Luke Skywalker")
                    .setPersoneelsID("006")
                    .setWerkpositie("nooduitgang")
                    .createCabineCrew();
        } catch (FouteGraadException ex) {
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
        personeelsleden.put("Skywalker", skywalker);

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

    // ofwel moet je de toString's zo aanpassen ... niet echt gelukkig mee (either way): 
    // separation of concerns, high cohesion, mvc, & ... een object heeft geen zak te maken met hoe je het voorstelt (mss te streng/teveel ineens?)
    // dit 'print to console gedoe' lijkt me ook voor vereenvoudiging vatbaar: vrijveel werk waar je niet uit kan afleiden of iemand kan programmeren...
    private static void printVluchtGegevens(Vlucht vlucht) {
        // voorbereiding ;-)
        LuchtVaartuig luchtVaartuig = vlucht.getToestel();
        Set<VliegendPersoneelslid> vliegendPersoneel = vlucht.getPersoneelsleden();
        BigDecimal totaleKostprijsPersoneel = BigDecimal.ZERO;
        Set<CockpitCrew> cockpitCrewPersoneel = new HashSet<>();
        Set<CabineCrew> cabineCrewPersoneel = new HashSet<>();
        for (VliegendPersoneelslid vliegendPersoneelslid : vliegendPersoneel) {
            totaleKostprijsPersoneel = totaleKostprijsPersoneel.add(((Kost) vliegendPersoneelslid).BerekenTotaleKostprijsPerDag());
            if (vliegendPersoneelslid instanceof CockpitCrew) {
                cockpitCrewPersoneel.add((CockpitCrew) vliegendPersoneelslid);
            } else if (vliegendPersoneelslid instanceof CabineCrew) {
                cabineCrewPersoneel.add((CabineCrew) vliegendPersoneelslid);
            }
        }
        BigDecimal duurtijdVlucht = new BigDecimal(vlucht.getDuurtijd());
        totaleKostprijsPersoneel = totaleKostprijsPersoneel.multiply(duurtijdVlucht);

        // bouw de string op
        StringBuilder vluchtGegevens = new StringBuilder();
        vluchtGegevens.append(vlucht.getVluchtID());
        vluchtGegevens.append(": ");
        vluchtGegevens.append(vlucht.getBestemming());
        vluchtGegevens.append(" (");
        vluchtGegevens.append(vlucht.getVliegMaatschappij().getVliegMaatschappijNaam());
        vluchtGegevens.append(") - ");
        vluchtGegevens.append(vlucht.getToestel().getType());
        vluchtGegevens.append(" (");
        vluchtGegevens.append(luchtVaartuig.BerekenTotaleKostprijsPerDag().multiply(duurtijdVlucht));
        vluchtGegevens.append(") - vluchtprijs ");
        vluchtGegevens.append(vlucht.berekenVluchtKost());
        vluchtGegevens.append("\n");
        vluchtGegevens.append(DASH80);
        vluchtGegevens.append("\nTotale personeelskost voor deze vlucht: ");
        vluchtGegevens.append(totaleKostprijsPersoneel);
        vluchtGegevens.append("\n");
        vluchtGegevens.append("\n");
        vluchtGegevens.append("Cockpit personeel:");
        for (CockpitCrew cockpitCrew : cockpitCrewPersoneel) {
            vluchtGegevens.append("\n\t");
            vluchtGegevens.append(cockpitCrew.getPersoneelsID());
            vluchtGegevens.append(": ");
            vluchtGegevens.append(cockpitCrew.getNaam());
            vluchtGegevens.append("\n\t\t");
            vluchtGegevens.append(toonCertificaten(cockpitCrew));
            vluchtGegevens.append("\n\t\t");
            vluchtGegevens.append("Vlieguren: ");
            vluchtGegevens.append(cockpitCrew.getVlieguren());
            vluchtGegevens.append("\n\t\t");
            vluchtGegevens.append("Kostprijs voor deze vlucht: ");
            vluchtGegevens.append(cockpitCrew.BerekenTotaleKostprijsPerDag().multiply(duurtijdVlucht));
        }
        vluchtGegevens.append("\n");
        vluchtGegevens.append("\n");
        vluchtGegevens.append("Cabine personeel:");
        for (CabineCrew cabineCrew : cabineCrewPersoneel) {
            vluchtGegevens.append("\n\t");
            vluchtGegevens.append(cabineCrew.getPersoneelsID());
            vluchtGegevens.append(": ");
            vluchtGegevens.append(cabineCrew.getNaam());
            vluchtGegevens.append("\n\t\t");
            vluchtGegevens.append(toonCertificaten(cabineCrew));
            vluchtGegevens.append("\n\t\t");
            vluchtGegevens.append("Werkpositie: ");
            vluchtGegevens.append(cabineCrew.getWerkpositie());
            vluchtGegevens.append("\n\t\t");
            vluchtGegevens.append("Kostprijs voor deze vlucht: ");
            vluchtGegevens.append(cabineCrew.BerekenTotaleKostprijsPerDag().multiply(duurtijdVlucht));
        }

        // en print de string...
        System.out.println(vluchtGegevens);
    }

    private static StringBuilder toonCertificaten(VliegendPersoneelslid vliegendPersoneelslid) {
        StringBuilder certificatenOverzicht = new StringBuilder("Certificaten: ");
        Set<Certificaat> certificaten = vliegendPersoneelslid.getCertificaten();
        if (certificaten == null || certificaten.isEmpty()) {
            certificatenOverzicht.append("geen certificaten");
            return certificatenOverzicht;
        }
        for (Certificaat certificaat : certificaten) {
            certificatenOverzicht.append(certificaat);
            certificatenOverzicht.append(' ');
        }
        return certificatenOverzicht;
    }

    // lijntjes
    // Ockham's razor (google! dus god bestaat niet? ;-) ): een private static final voor elk soort lijntje uitgetypt,
    // of zelfs gewoon telkens uitgetypt (nou ja +/- ... + wel leesbare code) ... echt niks mis mee, maar toch ;-) : 
    private static final String repeatChar(char c, int length) {
        char[] data = new char[length];
        Arrays.fill(data, c);
        return new String(data);
    }

    private static final String DASH80 = repeatChar('-', 80);
    private static final String DOUBLEDASH80 = repeatChar('=', 80);

}
