package de.chris.erp.ui;

import de.chris.erp.geschaeftslogik.ArtikelService;
import de.chris.erp.persistence.Artikel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.ArrayList;

@Controller
@RequestMapping("/artikel")
public class ArtikelController
{
    @Autowired
    ArtikelService artikelService;

    @PostMapping("/speichern/{id}")
    public String speichern(@PathVariable("id") long id, @Valid Artikel artikel, BindingResult result, Model model)
    {
        if (result.hasErrors())
        {
            return "artikel";
        }
        artikelService.speicherArtikel(artikel);
        return "artikelmenue";
    }

    @GetMapping("bearbeiten/{id}")
    public String bearbeiten(@PathVariable("id") long id, Model model)
    {
        Artikel artikel = artikelService.findById(id);
        model.addAttribute("artikel", artikel);
        return "artikel";
    }

    @GetMapping("loeschen/{id}")
    public String loeschen(@PathVariable("id") long id, Model model) {
        Artikel artikel = artikelService.findById(id);
        artikelService.loescheArtikel(artikel);
        model.addAttribute("artikel",new Artikel());
        model.addAttribute("artikelEntities",new ArrayList<>());
        return "artikelmenue";
    }
}
