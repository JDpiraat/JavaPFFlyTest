/*
 * Niet te gebruiken bij vdab-testen! Die moet je zelf maken.
 */
package flynet.vloot;

import java.util.List;

/**
 *
 * @author Johan
 */
public class VliegMaatschappij {
    
    private final int vliegMaatschappijID;
    private String vliegMaatschappijNaam;
    private final List<LuchtVaartuig> vloot;

    public VliegMaatschappij(int vliegMaatschappijID, String vliegMaatschappijNaam, List<LuchtVaartuig> vloot) {
        this.vliegMaatschappijID = vliegMaatschappijID;
        this.vliegMaatschappijNaam = vliegMaatschappijNaam;
        this.vloot = vloot;
    }   

    public int getVliegMaatschappijID() {
        return vliegMaatschappijID;
    }
    
    public String getVliegMaatschappijNaam() {
        return vliegMaatschappijNaam;
    }

    public void setVliegMaatschappijNaam(String vliegMaatschappijNaam) {
        this.vliegMaatschappijNaam = vliegMaatschappijNaam;
    }

    public List<LuchtVaartuig> getVloot() {
        return vloot;
    }

    public void addLuchtvaartuig(LuchtVaartuig luchtVaartuig){
        vloot.add(luchtVaartuig);
    }

    public void removeLuchtvaartuig(LuchtVaartuig luchtVaartuig){
        vloot.remove(luchtVaartuig);
    }
   
}
