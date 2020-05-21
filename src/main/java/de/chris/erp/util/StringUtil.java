package de.chris.erp.util;

/**
 * Hilfsklasse für {@link String}
 */
public class StringUtil
{
    private StringUtil()
    {
        //Von dieser Hilfsklasse soll kein Objekt erzeugt werden.
    }

    public static boolean isEmptyOrNull(String string)
    {
        return string == null || string.isEmpty();
    }
}
