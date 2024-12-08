package com.back_hernansoft.back_hernansoft.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.back_hernansoft.back_hernansoft.entity.usuario;
import com.back_hernansoft.back_hernansoft.repository.usuarioRepository;
import com.back_hernansoft.back_hernansoft.service.UsuarioService;

@Controller
@RequestMapping("/Cliente")
public class ClienteController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private usuarioRepository usuarioRepository;

    @GetMapping
    public ResponseEntity<List<usuario>> obtenerTodosLosClientes() {
        List<usuario> clientes = usuarioRepository.findByRol("cliente");
        System.out.println("Clientes encontrados: " + clientes); // Esto te ayudará a verificar qué se está retornando
        if (clientes.isEmpty()) {
            return ResponseEntity.noContent().build(); // Puedes devolver un estado 204 si no hay resultados
        }
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<usuario> obtenerUsuarioPorId(@PathVariable("id") String identificacion) {
        usuario usuario = usuarioService.findByIdentificacion(identificacion)
                .orElse(null); // Devuelve null si no hay valor
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/cliente_Datos")
    public String pedidosGerente() {
        return "Cliente/cliente_Datos"; // Nombre de la plantilla (archivo .html en templates)
    }

    @GetMapping("/cliente_Cotizaciones")
    public String cliente_Cotizaciones() {
        return "Cliente/cliente_Cotizaciones"; // Nombre de la plantilla (archivo .html en templates)
    }

    @GetMapping("/cliente_mensajes")
    public String cliente_mensaje() {
        return "Cliente/cliente_mensajes"; // Nombre de la plantilla (archivo .html en templates)
    }

    @PutMapping("/{id}")
    public ResponseEntity<usuario> actualizarUsuario(@PathVariable("id") String identificacion,
            @RequestBody usuario usuarioActualizado) {
        // usuario usuarioExistente =
        // usuarioService.findByIdentificacion(identificacion).orElse(null);

        // // Actualiza los datos del usuario existente con los nuevos datos

        usuario usuarioExistente = usuarioService.findByIdentificacion(identificacion).orElseThrow(null);

        usuarioExistente.setNombre(usuarioActualizado.getNombre());
        usuarioExistente.setApellido(usuarioActualizado.getApellido());
        usuarioExistente.setCorreo(usuarioActualizado.getCorreo());
        usuarioExistente.setDireccion(usuarioActualizado.getDireccion());
        usuarioExistente.setTelefono(usuarioActualizado.getTelefono());

        System.out.println(identificacion);
        System.out.println(usuarioActualizado);
        usuarioService.update(usuarioExistente);
        // Guarda los cambios en la base de datos

        return ResponseEntity.ok(usuarioService.findByIdentificacion(identificacion).orElse(null));
    }

}
