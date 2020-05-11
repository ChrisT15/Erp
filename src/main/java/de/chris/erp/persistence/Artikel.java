package de.chris.erp.persistence;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Artikel
{
    public Artikel() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "nummer")
    private String nummer;

    @Column(name = "bezeichnung")
    private String bezeichnung;

    @Column(name = "verkaufsPreis")
    private BigDecimal verkaufsPreis;

    @Column(name = "bruttoEinkaufsPreis")
    private BigDecimal bruttoEinkaufsPreis;

    @Column(name = "nettoEinkaufsPreis")
    private BigDecimal nettoEinkaufsPreis;

    @Lob
    @Column(name = "bild")
    private byte[] bild;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public BigDecimal getVerkaufsPreis() {
        return verkaufsPreis;
    }

    public void setVerkaufsPreis(BigDecimal verkaufsPreis) {
        this.verkaufsPreis = verkaufsPreis;
    }

    public BigDecimal getBruttoEinkaufsPreis() {
        return bruttoEinkaufsPreis;
    }

    public void setBruttoEinkaufsPreis(BigDecimal bruttoEinkaufsPreis) {
        this.bruttoEinkaufsPreis = bruttoEinkaufsPreis;
    }

    public BigDecimal getNettoEinkaufsPreis() {
        return nettoEinkaufsPreis;
    }

    public void setNettoEinkaufsPreis(BigDecimal nettoEinkaufsPreis) {
        this.nettoEinkaufsPreis = nettoEinkaufsPreis;
    }

    public byte[] getBild() {
        return bild;
    }

    public void setBild(byte[] bild) {
        this.bild = bild;
    }
}
