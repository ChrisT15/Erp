package de.chris.erp.ui;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = IndexController.class)
class IndexControllerTest
{
    @Autowired
    private MockMvc mockMvc;

    /** Test, ob das Artikelmenü aufgerufen wird.
     * @throws Exception
     */
    @Test
    void oeffneArtikelMenue() throws Exception
    {
        mockMvc
                .perform(MockMvcRequestBuilders.get("/artikelmenue")
                .accept(MediaType.TEXT_HTML))
                .andExpect(redirectedUrl("/artikelmenue/oeffnen"))
                .andExpect(status().isFound());
    }
}