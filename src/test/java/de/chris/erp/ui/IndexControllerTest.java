package de.chris.erp.ui;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(controllers = IndexController.class)
class IndexControllerTest
{
    @Autowired
    MockMvc artikelMenueControllerMock;

    /** Test, ob das Artikelmenü aufgerufen wird.
     * @throws Exception
     */
    @Test
    void oeffneArtikelMenue() throws Exception
    {
        artikelMenueControllerMock
                .perform(MockMvcRequestBuilders.get("/artikelmenue")
                .accept(MediaType.TEXT_HTML))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("artikelmenue"));
    }
}