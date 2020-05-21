package de.chris.erp.persistence;

/**
 * Enthält die Eingaben des Suchformulars für die Artikelsuche.
 */
public class ArtikelSuchFormular
{
    private String nummer;

    private String bezeichnung;

    public String getNummer() {
        return nummer;
    }

    public void setNummer(String nummer) {
        this.nummer = nummer;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }
}
