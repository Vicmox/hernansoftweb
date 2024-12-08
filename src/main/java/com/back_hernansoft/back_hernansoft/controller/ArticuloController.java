package com.back_hernansoft.back_hernansoft.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.back_hernansoft.back_hernansoft.entity.ArticuloPedido;
import com.back_hernansoft.back_hernansoft.entity.articulo;
import com.back_hernansoft.back_hernansoft.service.ArticuloService;

@RestController
@RequestMapping("/articulos")
public class ArticuloController {

    @Autowired
    private ArticuloService articuloService;

    @GetMapping
    public ResponseEntity<List<articulo>> obtenerTodosLosArticulos() {
        List<articulo> articulos = articuloService.obtenerTodosLosArticulos();
        return ResponseEntity.ok(articulos);
    }

    @GetMapping("/pedidos")
    public ResponseEntity<List<ArticuloPedido>> consultarPedidos(
            @RequestParam(required = false) String estado,
            @RequestParam(required = false) String cliente,
            @RequestParam(required = false) String marca,
            @RequestParam(required = false) String tipo) {
        List<ArticuloPedido> pedidos = articuloService.consultarPedidos(estado, cliente, marca, tipo);
        return ResponseEntity.ok(pedidos);
    }


    @PostMapping
    public ResponseEntity<articulo> agregarArticulo(@RequestBody articulo articulo) {
        articulo nuevoArticulo = articuloService.agregarArticulo(articulo);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoArticulo);
    }


    @PutMapping("/{id}")
    public ResponseEntity<articulo> editarArticulo(
            @PathVariable Integer id,
            @RequestBody articulo articuloDetalles) {
        articulo articuloActualizado = articuloService.editarArticulo(id, articuloDetalles);
        return ResponseEntity.ok(articuloActualizado);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deshabilitarArticulo(@PathVariable Integer id) {
        articuloService.deshabilitarArticulo(id);
        return ResponseEntity.noContent().build();
    }
}
