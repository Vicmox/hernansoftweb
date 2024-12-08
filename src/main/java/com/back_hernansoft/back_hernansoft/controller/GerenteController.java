package com.back_hernansoft.back_hernansoft.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GerenteController {

    @GetMapping("/pedidos_Gerente")
    public String pedidosGerente() {
        return "Gerente/pedidos_Gerente"; // Nombre de la plantilla (archivo .html en templates)
    }
    @GetMapping("/creditos_Gerente")
    public String creditos_Gerente() {
        return "Gerente/creditos_Gerente"; // Nombre de la plantilla (archivo .html en templates)
    }
    @GetMapping("/cotizaciones_Gerente")
    public String cotizacionesGerente() {
        return "Gerente/cotizaciones_Gerente"; // Nombre de la plantilla (archivo .html en templates)
    }
    @GetMapping("/gerente_Datos")
    public String gerente_Datos() {
        return "Gerente/gerente_Datos"; // Nombre de la plantilla (archivo .html en templates)
    }
}

