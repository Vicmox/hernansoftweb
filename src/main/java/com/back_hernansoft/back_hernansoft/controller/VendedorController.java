package com.back_hernansoft.back_hernansoft.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.back_hernansoft.back_hernansoft.entity.cotizacion;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
public class VendedorController {
    @GetMapping("/ventas_Vendedor")
    public String pedidosGerente() {
        return "Vendedor/ventas_Vendedor"; // Nombre de la plantilla (archivo .html en templates)
    }

    @GetMapping("/ventas_Clientes_Vendedor")
    public String ventas_Clientes_Vendedor(){
        return "Vendedor/ventas_Clientes_Vendedor";
    }
    
    @GetMapping("/ventas_Cotizaciones_Vendedor")
    public String ventas_Cotizaciones_Vendedor(){
        return "Vendedor/ventas_Cotizaciones_Vendedor";
    }

    @GetMapping("ventas_Pedidos_Vendedor")
    public String ventas_Pedidos_Vendedor(){
        return "Vendedor/ventas_Pedidos_Vendedor";

    }
    @GetMapping("ventas_Productos_Vendedor")
    public String ventas_Productos_Vendedor(){
        return "Vendedor/ventas_Productos_Vendedor";
    }

    @GetMapping("ventas_Clientes_Cotizaciones_Vendedor")
    public String ventas_Clientes_Cotizaciones_Vendedor() {
        return "Vendedor/ventas_Clientes_Cotizaciones_Vendedor";
    }
    
    @GetMapping("ventas_home")
    public String ventas_home() {
        return "Vendedor/ventas_home";
    }
    
}
