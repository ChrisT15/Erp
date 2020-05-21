package de.chris.erp.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller für die Willkommensseite
 */
@Controller
@RequestMapping("/")
public class IndexController
{
    /** Öffnet das Artikelmenü.
     * @return der Pfad für die Methode, die das Artikelmenü öffnet.
     */
    @RequestMapping("/artikelmenue")
    public String oeffneArtikelMenue()
    {
        return "redirect:/artikelmenue/oeffnen";
    }
}
