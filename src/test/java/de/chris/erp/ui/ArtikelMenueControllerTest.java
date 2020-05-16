package de.chris.erp.ui;

import de.chris.erp.geschaeftslogik.ArtikelService;
import de.chris.erp.persistence.Artikel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ArtikelMenueController.class)
class ArtikelMenueControllerTest
{
    @Autowired
    MockMvc mockMvc;

    @MockBean
    ArtikelService artikelService;


    @Test
    void oeffneArtikelFenster() throws Exception
    {
        mockMvc
        .perform(get("/artikelmenue/artikel").accept(MediaType.TEXT_HTML))
                .andExpect(MockMvcResultMatchers.view().name("artikel"))
                .andExpect(status().isOk());
    }

    @Test
    void sucheArtikel() throws Exception
    {
        //todo: übergabeparameter testen
        mockMvc.perform(post("/artikelmenue/suchen")
                .accept(MediaType.TEXT_HTML))
                .andExpect(MockMvcResultMatchers.view().name("artikelmenue"))
                .andExpect(status().isOk());
    }

    @Test
    void zeigeLeereFelder() throws Exception
    {
        mockMvc
                .perform(get("/artikelmenue/leeren").accept(MediaType.TEXT_HTML))
                .andExpect(MockMvcResultMatchers.view().name("artikelmenue"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("artikelEntities",hasSize(0)))
                .andExpect(model().attribute("artikel",allOf(
                        //Es wird getestet, ob der Artikel leer ist
                        hasProperty("id",is(0L)),
                        hasProperty("bezeichnung",nullValue()),
                        hasProperty("nummer",nullValue())
                )));
    }

    @Test
    void zeigeArtikelTabelleOhneGeloeschtenArtikel() throws Exception
    {
        Artikel artikel1 = new Artikel();
        artikel1.setId(1);
        artikel1.setBezeichnung("Artikel1");

        Artikel artikel2 = new Artikel();
        artikel2.setId(2);
        artikel2.setBezeichnung("Artikel2");

        List<Artikel> artikelListe = new ArrayList<>();
        artikelListe.add(artikel1);
        artikelListe.add(artikel2);

        ArtikelMenueController artikelMenueController = new ArtikelMenueController();

        Field artikelListeAttribut = artikelMenueController.getClass().getDeclaredField("artikelEntities");
        artikelListeAttribut.setAccessible(true);
        artikelListeAttribut.set(artikelMenueController, artikelListe);

        mockMvc = MockMvcBuilders.standaloneSetup(artikelMenueController)
                .build();

        mockMvc
                .perform(get("/artikelmenue/ohneGeloeschtenArtikel/{id}",1)
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("artikelmenue"))
                .andExpect(model().attribute("artikelEntities",hasItem(
                        allOf(
                                hasProperty("id",is(2L)),
                                hasProperty("bezeichnung",is("Artikel2"))
                        )
                )));
    }
}