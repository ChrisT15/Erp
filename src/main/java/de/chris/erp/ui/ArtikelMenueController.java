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

/**
 * Controller für das Artikel-Menü.
 * In dem Artikel-Menü können Artikel nach Eigenschaften gesucht werden.
 */
@Controller
@RequestMapping("/artikelmenue")
public class ArtikelMenueController
{
    @Autowired
    private ArtikelService artikelService;

    /**
     * Repräsentiert die Suchergebnisse einer Suche nach Artikeln
     */
    private List<Artikel> artikelEntities = new ArrayList<>();

    /**
     * Enthält die Suchkritieren für die Suche nach Artikeln
     */
    private ArtikelSuchFormular artikelSuchFormular = new ArtikelSuchFormular();

    /** Öffnet das Artikel-Detail-Fenster für einen bestimmten Artikel.
     * Die Daten des Artikels werden dann angezeigt.
     * @param artikel Artikel, für den die Detail-Ansicht geöffnet werden soll
     * @return Name der Artikel-Detail-View
     */
    @RequestMapping("/artikel")
    public String oeffneArtikelFenster(@ModelAttribute Artikel artikel)
    {
        return "artikel";
    }

    /** Anhand der Eingaben im {@link ArtikelSuchFormular} werden Artikel gesucht
     *
     * @param artikelSuchFormular enthält Suchkriterien, nach denen Artikel gesucht werden
     * @param bindingResult
     * @param model Model der View
     * @return Name des Artikelmenüs.
     */
    @PostMapping("/suchen")
    public String sucheArtikel(ArtikelSuchFormular artikelSuchFormular,
                               BindingResult bindingResult,
                               Model model)
    {
        this.artikelSuchFormular = artikelSuchFormular;
        artikelEntities = artikelService.sucheArtikelNachEigenschaften(artikelSuchFormular);
        //Die Suchergebnisse werden zum Model hinzugefügt, damit diese in der View angezeigt werden.
        model.addAttribute("artikelEntities", artikelEntities);
        model.addAttribute("artikelSuchFormular",artikelSuchFormular);
        return "artikelmenue";
    }

    /** Leert alle Eingaben und die Ergebnis-Tabelle in der Artikelmenü-View.
     * @param model Model der View
     * @return Pfad des Artikelmenüs.
     */
    @GetMapping("/leeren")
    public String zeigeLeereFelder(Model model)
    {
        //Um die View zu leeren werden leere Objekte zum Model hinzugefügt.
        model.addAttribute("artikelEntities",new ArrayList<>());
        model.addAttribute("artikelSuchFormular",new ArtikelSuchFormular());
        return "artikelmenue";
    }

    /** Entfernt einen {@link Artikel} mit einer vorgegebenen Id aus der Ergebnis-Liste
     * der Artikelmenü-View
     * @param model
     * @param id
     * @return Name des Artikel-Menüs.
     */
    @GetMapping("/ohneGeloeschtenArtikel/{id}")
    public String zeigeArtikelTabelleOhneGeloeschtenArtikel(Model model,@PathVariable("id") Long id)
    {
        model.addAttribute("artikelSuchFormular",new ArtikelSuchFormular());
        artikelEntities.removeIf(artikel -> artikel.getId() == id);
        model.addAttribute("artikelEntities",artikelEntities);
        return "artikelmenue";
    }

    /** Öffnet das Artiklmenü. Wichtig ist hierbei, dass ein Artikelsuchformular zum
     * Model hinzugefügt wird.
     * @param model Model der View
     * @return Name des Artikelmenüs
     */
    @GetMapping("/oeffnen")
    public String oeffneArtikelMenue(Model model)
    {
        model.addAttribute("artikelSuchFormular",artikelSuchFormular);
        return "artikelmenue";
    }

}
