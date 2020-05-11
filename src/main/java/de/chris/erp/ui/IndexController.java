package de.chris.erp.ui;

import de.chris.erp.persistence.Artikel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController
{
    @RequestMapping("/artikelmenue")
    public String oeffneArtikelMenue(Model model)
    {
        model.addAttribute("artikel",new Artikel());
        return "artikelmenue";
    }
}
