/*
 * Niet te gebruiken bij vdab-testen! Die moet je zelf maken.
 */
package flynet.exceptions;

/**
 *
 * @author Johan
 */
public class ToestelBehoortNietTotVlootException extends Exception {

    public ToestelBehoortNietTotVlootException() {
    }

    public ToestelBehoortNietTotVlootException(String string) {
    }

    public ToestelBehoortNietTotVlootException(String message, Throwable cause) {
        super(message, cause);
    }

    public ToestelBehoortNietTotVlootException(Throwable cause) {
        super(cause);
    }
}
