package de.chris.erp.ui;

import de.chris.erp.geschaeftslogik.ArtikelService;
import de.chris.erp.persistence.Artikel;
import de.chris.erp.persistence.ArtikelSuchFormular;
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
    private ArtikelService artikelService;

    private List<Artikel> artikelEntities = new ArrayList<>();

    private ArtikelSuchFormular artikelSuchFormular = new ArtikelSuchFormular();

    @RequestMapping("/artikel")
    public String oeffneArtikelFenster(@ModelAttribute Artikel artikel)
    {
        return "artikel";
    }

    @PostMapping("/suchen")
    public String sucheArtikel(ArtikelSuchFormular artikelSuchFormular, BindingResult bindingResult, Model model)
    {
        this.artikelSuchFormular = artikelSuchFormular;
        artikelEntities = artikelService.sucheArtikelNachEigenschaften(artikelSuchFormular);
        model.addAttribute("artikelEntities", artikelEntities);
        model.addAttribute("artikelSuchFormular",artikelSuchFormular);
        return "artikelmenue";
    }

    @GetMapping("/leeren")
    public String zeigeLeereFelder(Model model)
    {
        model.addAttribute("artikelEntities",new ArrayList<>());
        model.addAttribute("artikelSuchFormular",new ArtikelSuchFormular());
        return "artikelmenue";
    }

    @GetMapping("/ohneGeloeschtenArtikel/{id}")
    public String zeigeArtikelTabelleOhneGeloeschtenArtikel(Model model,@PathVariable("id") Long id)
    {
        model.addAttribute("artikelSuchFormular",new ArtikelSuchFormular());
        artikelEntities.removeIf(artikel -> artikel.getId() == id);
        model.addAttribute("artikelEntities",artikelEntities);
        return "artikelmenue";
    }

    @GetMapping("/oeffnen")
    public String oeffneArtikelMenue(Model model)
    {
        model.addAttribute("artikelSuchFormular",artikelSuchFormular);
        return "artikelmenue";
    }

}
