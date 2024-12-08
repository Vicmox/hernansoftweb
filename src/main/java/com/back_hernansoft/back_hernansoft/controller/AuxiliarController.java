package com.back_hernansoft.back_hernansoft.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuxiliarController {
    
    @GetMapping("/home_Auxiliar")
    public String home_Auxiliar() {
        return "Auxiliar/home_Auxiliar"; // Nombre de la plantilla (archivo .html en templates)
    }

    @GetMapping("/pedidos_Auxiliar")
    public String pedidos_Auxiliar() {
        return "Auxiliar/pedidos_Auxiliar"; // Nombre de la plantilla (archivo .html en templates)
    }
}
