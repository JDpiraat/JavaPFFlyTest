/*
 * Niet te gebruiken bij vdab-testen! Die moet je zelf maken.
 */
package flynet.vloot;

import java.util.List;
import java.util.Objects;

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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.vliegMaatschappijID;
        hash = 67 * hash + Objects.hashCode(this.vliegMaatschappijNaam);
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
        final VliegMaatschappij other = (VliegMaatschappij) obj;
        if (this.vliegMaatschappijID != other.vliegMaatschappijID) {
            return false;
        }
        return Objects.equals(this.vliegMaatschappijNaam, other.vliegMaatschappijNaam);
    }

    @Override
    public String toString() {
        return "VliegMaatschappij{" + "vliegMaatschappijID=" + vliegMaatschappijID + ", vliegMaatschappijNaam=" + vliegMaatschappijNaam + '}';
    }
    
}
