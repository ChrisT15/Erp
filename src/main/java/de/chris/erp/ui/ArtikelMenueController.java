package de.chris.erp.ui;

import de.chris.erp.geschaeftslogik.ArtikelService;
import de.chris.erp.persistence.Artikel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/artikelmenue")
public class ArtikelMenueController
{
    @Autowired
    ArtikelService artikelService;

    List<Artikel> artikelEntities = new ArrayList<>();

    @RequestMapping("/artikel")
    public String oeffneArtikelFenster(@ModelAttribute Artikel artikel)
    {
        return "artikel";
    }

    @PostMapping("/suchen")
    public String sucheArtikel(Artikel artikel, BindingResult bindingResult, Model model)
    {
        artikelEntities = artikelService.sucheArtikelNachEigenschaften(artikel);
        return "redirect:/suchen";
    }

    @GetMapping("/suchen")
    public String sendeArtikel(Model model) {
        model.addAttribute("artikelEntities", artikelEntities);
        return "artikelmenue";
    }
}
