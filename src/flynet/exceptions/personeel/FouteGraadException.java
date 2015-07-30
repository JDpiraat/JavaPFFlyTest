/*
 * Niet te gebruiken bij vdab-testen! Die moet je zelf maken.
 */
package flynet.exceptions.personeel;

/**
 *
 * @author Johan
 */
public class FouteGraadException extends Exception {

    public FouteGraadException() {
    }

    public FouteGraadException(String message) {
        super(message);
    }

    public FouteGraadException(String message, Throwable cause) {
        super(message, cause);
    }

    public FouteGraadException(Throwable cause) {
        super(cause);
    }
}
