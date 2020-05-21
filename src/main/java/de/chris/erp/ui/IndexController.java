package de.chris.erp.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController
{
    @RequestMapping("/artikelmenue")
    public String oeffneArtikelMenue()
    {
        return "redirect:/artikelmenue/oeffnen";
    }
}
