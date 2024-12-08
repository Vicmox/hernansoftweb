package com.back_hernansoft.back_hernansoft.service.interfac;

import com.back_hernansoft.back_hernansoft.entity.pedido;

import java.util.List;
import java.util.Optional;

public interface IPedidoService {

    public List<pedido> obtenerTodosPedidos();

    Optional<pedido> buscarPedidoPorId(Integer id); // Buscar un pedido por ID
    void actualizarPedido(pedido pedido); // Actualizar un pedido


}
