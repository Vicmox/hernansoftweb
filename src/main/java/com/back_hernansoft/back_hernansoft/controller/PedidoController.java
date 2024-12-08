package com.back_hernansoft.back_hernansoft.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back_hernansoft.back_hernansoft.entity.pedido;
import com.back_hernansoft.back_hernansoft.repository.pedidoRepository;

@RestController
@RequestMapping("/pedidos")
public class PedidoController{


    @Autowired
    private pedidoRepository pedidoRepository;

    @GetMapping
    public List<pedido> listar() {
        return pedidoRepository.findAll();
    }

    @GetMapping("/{id}")
    public pedido obtenerPorId(@PathVariable int id) {
        return pedidoRepository.findById(id).orElse(null);
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody pedido pedido) {
        try {
            pedido nuevoPedido = pedidoRepository.save(pedido);
            return ResponseEntity.ok(nuevoPedido);
        } catch (ObjectOptimisticLockingFailureException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflicto de actualización: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public pedido actualizar(@PathVariable int id, @RequestBody pedido pedido) {
        pedido existente = pedidoRepository.findById(id).orElse(null);
        if (existente != null) {
            existente.setEstado(pedido.getEstado());
            existente.setFecha_pedido(pedido.getFecha_pedido());
            existente.setSubtotal(pedido.getSubtotal());
            existente.setTotal(pedido.getTotal());
            return pedidoRepository.save(existente);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable int id) {
        pedidoRepository.deleteById(id);
    }
}