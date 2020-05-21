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

    /** Prüft, ob ein gegebener String leer oder null ist.
     * @param string ein beliebiger String
     * @return true, falls der String null oder leer ist und ansonsten false.
     */
    public static boolean isEmptyOrNull(String string)
    {
        return string == null || string.isEmpty();
    }
}
