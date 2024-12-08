package com.back_hernansoft.back_hernansoft.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CajeroController {
    
    @GetMapping("/pedidos_Cajero")
    public String pedidosCajero() {
        return "Cajero/pedidos_Cajero"; // Nombre de la plantilla (archivo .html en templates)
    }
    @GetMapping("/clientes_Cajero")
    public String clientesCajero() {
        return "Cajero/clientes_Cajero"; // Nombre de la plantilla (archivo .html en templates)
    }   

    @GetMapping("/cajero_Datos")
    public String cajero_Datos() {
        return "Cajero/cajero_Datos"; // Nombre de la plantilla (archivo .html en templates)
    }

}
