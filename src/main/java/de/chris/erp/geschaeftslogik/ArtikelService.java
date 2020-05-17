package de.chris.erp.geschaeftslogik;

import de.chris.erp.persistence.Artikel;
import de.chris.erp.persistence.ArtikelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Artikel findById(long artikelId)
    {
        return artikelRepository.findById(artikelId)
                .orElseThrow(()-> new IllegalStateException("Keine gültige Artikel-Id: " + artikelId));
    }

    public List<Artikel> sucheArtikelNachEigenschaften(Artikel artikel)
    {
        return artikelRepository.sucheArtikelNachEigenschaften(artikel);
    }
}
