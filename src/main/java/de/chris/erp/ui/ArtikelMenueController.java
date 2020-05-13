package de.chris.erp.ui;

import de.chris.erp.geschaeftslogik.ArtikelService;
import de.chris.erp.persistence.Artikel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/artikelmenue")
public class ArtikelMenueController
{
    @Autowired
    ArtikelService artikelService;

    List<Artikel> artikelEntities = new ArrayList<>();

    Artikel artikel = new Artikel();

    @RequestMapping("/artikel")
    public String oeffneArtikelFenster(@ModelAttribute Artikel artikel)
    {
        return "artikel";
    }

    @PostMapping("/suchen")
    public String sucheArtikel(Artikel artikel, BindingResult bindingResult, Model model)
    {
        this.artikel = artikel;
        artikelEntities = artikelService.sucheArtikelNachEigenschaften(artikel);
        return "redirect:/artikelmenue";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String sendeArtikel(Model model) {
        model.addAttribute("artikelEntities", artikelEntities);
        model.addAttribute("artikel",artikel);
        return "artikelmenue";
    }
}
