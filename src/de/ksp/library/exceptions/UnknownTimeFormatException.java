package de.ksp.library.exceptions;

/**
 * User: paul
 * Date: 28.03.13
 * Time: 04:01
 */
public class UnknownTimeFormatException extends Exception {

    public UnknownTimeFormatException(String input) {
        super("Unknown time format input: " + input);
    }

}
