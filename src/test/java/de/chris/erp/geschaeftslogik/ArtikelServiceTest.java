package de.chris.erp.geschaeftslogik;

import de.chris.erp.persistence.Artikel;
import de.chris.erp.persistence.ArtikelRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

/**
 * Test für {@link ArtikelService}
 */
@SpringBootTest
class ArtikelServiceTest
{
    @Autowired
    private ArtikelService artikelService;

    @MockBean
    private ArtikelRepository<Artikel,Long> artikelRepository;

    /**
     * Test, ob die Methode {@link ArtikelService#findById(long)} den gefunden {@link Artikel} zurückgibt.
     */
    @Test
    void findByIdFuntioniert()
    {
        Artikel artikel = new Artikel();
        artikel.setId(1L);
        artikel.setBezeichnung("Artikel1");
        Optional<Artikel> artikel1Optional = Optional.of(artikel);

        when(artikelRepository.findById(1L)).thenReturn(artikel1Optional);

        Artikel artikelFromService = artikelService.findById(1L);
        assertEquals(artikel,artikelFromService);
    }

    /**
     * Test, ob eine {@link Exception} geworfen wird, wenn kein {@link Artikel} gefunden wurde.
     */
    @Test
    void findByIdSchlaegtFehl()
    {
        when(artikelRepository.findById(2L)).thenReturn(Optional.empty());
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> artikelService.findById(2L));
        assertEquals("Keine gültige Artikel-Id: 2",exception.getMessage());
    }
}