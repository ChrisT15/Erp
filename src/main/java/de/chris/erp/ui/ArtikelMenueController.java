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

    private List<Artikel> artikelEntities = new ArrayList<>();

    private Artikel artikel = new Artikel();

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
        model.addAttribute("artikelEntities", artikelEntities);
        model.addAttribute("artikel",artikel);
        return "artikelmenue";
    }

    @GetMapping("/leeren")
    public String zeigeLeereFelder(Model model)
    {
        model.addAttribute("artikelEntities",new ArrayList<>());
        model.addAttribute("artikel",new Artikel());
        return "artikelmenue";
    }

    @GetMapping("/ohneGeloeschtenArtikel/{id}")
    public String zeigeArtikelTabelleOhneGeloeschtenArtikel(Model model,@PathVariable("id") Long id)
    {
        model.addAttribute("artikel",new Artikel());
        artikelEntities.removeIf(artikel -> artikel.getId() == id);
        model.addAttribute("artikelEntities",artikelEntities);
        return "artikelmenue";
    }
}
