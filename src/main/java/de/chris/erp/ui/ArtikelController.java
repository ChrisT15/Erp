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

/**
 * Controller für das Lesen und Eingeben von Daten für {@link Artikel}
 */
@Controller
@RequestMapping("/artikel")
public class ArtikelController
{
    @Autowired
    private ArtikelService artikelService;

    /** Ein {@link Artikel} wird gespeichert.
     * @param id Id des Artikels. Wird benötigt, da ansonsten ein Artikel mit Id 0 eingegengenommen wird.
     * @param artikel Artikel, der gespeichert wird
     * @param result
     * @param model Model der View
     * @return Pfad
     */
    @PostMapping("/speichern/{id}")
    public String speichern(@PathVariable("id") long id, @Valid Artikel artikel, BindingResult result, Model model)
    {
        //Tritt ein Fehler auf, so wird die ursprüngliche Seite wieder geöffnet.
        if (result.hasErrors())
        {
            return "artikel";
        }
        artikelService.speicherArtikel(artikel);
        //Ist das Speichern erfolgreich, so wird das leere Artikelmenü aufgerufen.
        return "redirect:/artikelmenue/leeren";
    }

    /** Ein Artikel mit einer vorgegebenen Id soll editiert werden. Es wird das Artikel-Fenster geöffnet.
     * @param id Id des zu editierenden Artikels
     * @param model Model der View
     * @return Name der Artikel-View.
     */
    @GetMapping("bearbeiten/{id}")
    public String bearbeiten(@PathVariable("id") long id, Model model)
    {
        Artikel artikel = artikelService.findById(id);
        model.addAttribute("artikel", artikel);
        return "artikel";
    }

    /** Ein {@link Artikel} mit einer vorgegebenen Id wird gelöscht
     * @param id Id eines Artikels
     * @param model Model der View
     * @return Pfad für die Anzeige des Artikelmenüs ohne den gelöschten Artikel
     */
    @GetMapping("loeschen/{id}")
    public String loeschen(@PathVariable("id") long id, Model model) {
        Artikel artikel = artikelService.findById(id);
        artikelService.loescheArtikel(artikel);
        model.addAttribute("id",id);
        return "redirect:/artikelmenue/ohneGeloeschtenArtikel/{id}";
    }
}
