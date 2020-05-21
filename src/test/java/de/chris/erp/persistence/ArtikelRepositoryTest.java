package de.chris.erp.persistence;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test-Klasse für {@link ArtikelRepository}
 */
@SpringBootTest
@ActiveProfiles("testdb")
class ArtikelRepositoryTest
{
    @Autowired
    private ArtikelRepository<Artikel,Long> artikelRepository;

    @Test
    public void sucheArtikelNachEigenschaften()
    {
        //Zuerst wird die Tabelle für Artikel geleert, damit der Test nicht verfälscht wird.
        artikelRepository.deleteAll();

        //Für den Test werden Artikel erzeugt.
        Artikel artikel1 = new Artikel();
        artikel1.setBezeichnung("Artikel1");
        artikel1.setNummer("1");

        Artikel artikel2 = new Artikel();
        artikel2.setBezeichnung("Artikel2");
        artikel2.setNummer("2");

        artikelRepository.save(artikel1);
        artikelRepository.save(artikel2);

        Artikel artikel = new Artikel();
        artikel.setBezeichnung("Artikel1");

        List<Artikel> artikelList = artikelRepository.sucheArtikelNachEigenschaften(artikel);
        assertEquals(1,artikelList.size());
        assertEquals("Artikel1",artikelList.get(0).getBezeichnung());

        //Damit andere Tests nicht verfälscht werden, werden die Test-Daten wieder gelöscht.
        artikelRepository.deleteAll();
    }
}