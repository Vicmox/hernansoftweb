package com.back_hernansoft.back_hernansoft.service;

import com.back_hernansoft.back_hernansoft.entity.usuario;
import com.back_hernansoft.back_hernansoft.repository.pedidoRepository;
import com.back_hernansoft.back_hernansoft.entity.pedido;
import com.back_hernansoft.back_hernansoft.service.interfac.IPedidoService;
import com.back_hernansoft.back_hernansoft.service.interfac.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService implements IPedidoService {

    @Autowired
    private pedidoRepository pedidoRepository;


    @Override
    public List<pedido> obtenerTodosPedidos() {
        List<pedido> pedidos = pedidoRepository.findAll();
        //System.out.println("SOUT DE PRUEBA" + pedidos);  // Asegúrate de que los datos se están cargando
        return pedidos;
    }

    @Override
    public Optional<pedido> buscarPedidoPorId(Integer id) {
        // Busca un pedido por su ID
        return pedidoRepository.findById(id);
    }

    @Override
    public void actualizarPedido(pedido pedido) {
        // Guarda el pedido con los cambios realizados
        pedidoRepository.save(pedido);
    }
}
