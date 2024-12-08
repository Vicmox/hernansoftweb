package com.back_hernansoft.back_hernansoft.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back_hernansoft.back_hernansoft.entity.ArticuloPedido;
import com.back_hernansoft.back_hernansoft.entity.articulo;
import com.back_hernansoft.back_hernansoft.repository.articuloRepository;

@Service
public class ArticuloService {

    @Autowired
    private articuloRepository articuloRepository;

    /**
     * Obtener todos los artículos.
     */
    public List<articulo> obtenerTodosLosArticulos() {
        return articuloRepository.findAll();
    }

    /**
     * Consultar pedidos por filtros.
     */
    public List<ArticuloPedido> consultarPedidos(String estado, String cliente, String marca, String tipo) {
        // Implementar lógica para buscar pedidos según los filtros.
        return articuloRepository.findPedidosByFilters(estado, cliente, marca, tipo);
    }

    /**
     * Agregar un nuevo artículo.
     */
    public articulo agregarArticulo(articulo articulo) {
        return articuloRepository.save(articulo);
    }

    /**
     * Editar los detalles de un artículo.
     */
    public articulo editarArticulo(Integer id, articulo articuloDetalles) {
        articulo articulo = articuloRepository.findById(id)
                .orElseThrow();
        articulo.setDescripcion(articuloDetalles.getDescripcion());
        // Actualizar otros campos si aplica.
        return articuloRepository.save(articulo);
    }

    /**
     * Deshabilitar un artículo.
     */
    public void deshabilitarArticulo(Integer id) {
        articulo articulo = articuloRepository.findById(id)
                .orElseThrow();
        articuloRepository.delete(articulo);
    }
}
