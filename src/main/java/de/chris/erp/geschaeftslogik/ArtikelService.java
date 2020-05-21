package de.chris.erp.geschaeftslogik;

import de.chris.erp.persistence.Artikel;
import de.chris.erp.persistence.ArtikelRepository;
import de.chris.erp.persistence.ArtikelSuchFormular;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service-Klasse für {@link Artikel}
 */
@Service
public class ArtikelService
{
    @Autowired
    private ArtikelRepository<Artikel,Long> artikelRepository;

    public Artikel speicherArtikel(Artikel artikel)
    {
        return artikelRepository.save(artikel);
    }

    public void loescheArtikel(Artikel artikel)
    {
        artikelRepository.delete(artikel);
    }

    /** Sucht einen Artikel anhand einer vorgegebenen Id. Gibt es keinen Artikel mit der vorgegebenen
     * Id, dann wird ein Fehler geworfen.
     * @param artikelId Id des gesuchten Artikels
     * @return Artikel mit der vorgegebenen Id
     */
    public Artikel findById(long artikelId)
    {
        return artikelRepository.findById(artikelId)
                .orElseThrow(()-> new IllegalStateException("Keine gültige Artikel-Id: " + artikelId));
    }

    /** Sucht Artikel anhand von Eingaben in einem Suchformular.
     * @param artikelSuchFormular Enthält verschiedene Eigenschaften, die auf die gesuchten Artikel zutreffen
     *                            sollen
     * @return Liste von Artikel, die die Suchkriterien aus dem Suchformular erfüllen
     */
    public List<Artikel> sucheArtikelNachEigenschaften(ArtikelSuchFormular artikelSuchFormular)
    {
        return artikelRepository.sucheArtikelNachEigenschaften(artikelSuchFormular);
    }
}
