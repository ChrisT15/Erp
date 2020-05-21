package de.chris.erp.persistence;

import java.util.List;

/**
 * Interface für selbst erstellte Methode mit Persistenzlogik für {@link Artikel}
 */
public interface ArtikelRepositoryCustom
{
    /** Sucht Artikel anhand von Eingaben in einem Suchformular.
     * @param artikelSuchFormular Enthält verschiedene Eigenschaften, die auf die gesuchten Artikel zutreffen
     *                            sollen
     * @return Liste von Artikel, die die Suchkriterien aus dem Suchformular erfüllen
     */
    public List<Artikel> sucheArtikelNachEigenschaften(ArtikelSuchFormular artikelSuchFormular);

}
