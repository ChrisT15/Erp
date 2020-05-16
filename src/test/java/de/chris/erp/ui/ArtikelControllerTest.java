package de.chris.erp.ui;

import de.chris.erp.geschaeftslogik.ArtikelService;
import de.chris.erp.persistence.Artikel;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = ArtikelController.class)
class ArtikelControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ArtikelService artikelService;

    @Test
    void speichern() throws Exception
    {
        mockMvc.perform(post("/artikel/speichern/{id}",1)
                .accept(MediaType.TEXT_HTML))
                .andExpect(redirectedUrl("/artikelmenue/leeren"))
                .andExpect(status().isFound());
    }

    @Test
    void bearbeiten() throws Exception
    {
        Artikel artikel = new Artikel();
        artikel.setId(1);
        artikel.setNummer("1");
        artikel.setBezeichnung("TestArtikel");
        artikel.setBruttoEinkaufsPreis(new BigDecimal("10.0"));
        artikel.setVerkaufsPreis(new BigDecimal("20.0"));

        Mockito.when(artikelService.findById(1)).thenReturn(artikel);

        mockMvc
                .perform(MockMvcRequestBuilders.get("/artikel/bearbeiten/{id}",1)
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("artikel"))
                .andExpect(model().attribute("artikel",allOf(
                                hasProperty("id",is(1L)),
                                hasProperty("nummer",is("1")),
                                hasProperty("bezeichnung",is("TestArtikel")),
                                hasProperty("bruttoEinkaufsPreis",is(new BigDecimal("10.0"))),
                                hasProperty("verkaufsPreis",is(new BigDecimal("20.0")))
                        )
                ));
    }

    @Test
    void loeschen() throws Exception
    {
        mockMvc
                .perform(get("/artikel/loeschen/{id}",1)
                        .accept(MediaType.TEXT_HTML))
                .andExpect(redirectedUrl("/artikelmenue/ohneGeloeschtenArtikel/1"))
                .andExpect(status().isFound());
    }
}