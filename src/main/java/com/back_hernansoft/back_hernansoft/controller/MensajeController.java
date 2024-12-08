package com.back_hernansoft.back_hernansoft.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
import com.back_hernansoft.back_hernansoft.dto.MensajeDTO;
import com.back_hernansoft.back_hernansoft.entity.mensaje;
import com.back_hernansoft.back_hernansoft.entity.usuario;
import com.back_hernansoft.back_hernansoft.repository.mensajeRepository;
import com.back_hernansoft.back_hernansoft.repository.usuarioRepository;


@RestController
@RequestMapping("/mensajes")
public class MensajeController {
    
@Autowired
  private mensajeRepository mensajeRepository;
    
  @Autowired
  private usuarioRepository usuarioRepository;

  @GetMapping
  public List<mensaje> listar() {
    return mensajeRepository.findAll();
  }

  @GetMapping("/usuarios/vendedores")
  public List<usuario> listarVendedores() {
    return usuarioRepository.findByRol("vendedor");
  }
/* 
  @GetMapping("/usuario/mensajes/{identificacion}")
  public List<MensajeDTO> listarPorUsuario(@PathVariable Integer identificacion) {
    Integer idUsuario = usuarioRepository.findIdByIdentificacion(identificacion.toString())
        .orElseThrow(() -> new RuntimeException("Usuario no encontrado")).getIdUsuario();
        
        List<mensaje> mensajeRespuesta= mensajeRepository.findAll().stream()
        .filter(
            m -> m.getEmisor().getIdUsuario().equals(idUsuario) || m.getDestinatario().getIdUsuario().equals(idUsuario))
        .toList();
        List<MensajeDTO> mensajeDTOs = new ArrayList<>();
        for (mensaje mensaje : mensajeRespuesta) {
            MensajeDTO mensajeDTO = MensajeDTO.builder()
                    .idMensaje(mensaje.getId(idUsuario));
                    .mensaje(mensaje.getContenido())
                    .fecha(mensaje.getFecha().toString())
                    .estado(mensaje.getEstado())
                    .nombreEmisor(mensaje.getEmisor().getNombre())
                    .nombreDestinatario(mensaje.getDestinatario().getNombre())
                    .build();
            mensajeDTOs.add(mensajeDTO);
        }
        return mensajeDTOs;
  }*/

  @PostMapping
  public ResponseEntity<?> crear(@RequestBody Map<String, Object> mensajeDTO) {
    try {
      // Obtener el ID del emisor a partir de la identificación

      Integer idEmisor = usuarioRepository.findIdByIdentificacion(mensajeDTO.get("identificacion").toString())
          .orElseThrow(() -> new RuntimeException("Usuario emisor no encontrado")).getIdUsuario();

      // Obtener el ID del destinatario desde el DTO
      Integer idDestinatario = Integer.parseInt(mensajeDTO.get("destinatarioId").toString());

      // Verificar que el destinatario exista
      usuario destinatario = usuarioRepository.findById(idDestinatario)
          .orElseThrow(() -> new RuntimeException("Usuario destinatario no encontrado"));

      // Crear el nuevo mensaje
      mensaje nuevoMensaje = new mensaje();
      nuevoMensaje.setMensaje((String) mensajeDTO.get("mensaje"));
      nuevoMensaje.setFecha(Date.valueOf((String) mensajeDTO.get("fecha")));
      nuevoMensaje.setEstado((String) mensajeDTO.get("estado"));

      // Asignar el emisor y destinatario
      usuario emisor = new usuario();
      emisor.setIdUsuario(idEmisor);
      nuevoMensaje.setEmisor(emisor);
      nuevoMensaje.setDestinatario(destinatario);

      // Guardar el mensaje
      mensaje mensajeGuardado = mensajeRepository.save(nuevoMensaje);
      return ResponseEntity.status(HttpStatus.CREATED).body(mensajeGuardado);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
    }
  }

  @GetMapping("/usuarios/vendedor/{nombre}")
  public ResponseEntity<usuario> obtenerVendedorPorNombre(@PathVariable String nombre) {
    usuario vendedor = usuarioRepository.findByNombre(nombre).orElseThrow(); // Asumiendo que tienes un método en el
                                                                             // repositorio para buscar id por nombre
    return ResponseEntity.ok(vendedor);
  }

  @PutMapping("/{id}")
  public mensaje actualizar(@PathVariable int id, @RequestBody mensaje mensaje) {
    mensaje existente = mensajeRepository.findById(id).orElse(null);
    if (existente != null) {
      existente.setEstado(mensaje.getEstado());
      existente.setMensaje(mensaje.getMensaje());
      existente.setEstado(mensaje.getEstado());
      existente.setFecha(mensaje.getFecha());
      return mensajeRepository.save(existente);
    }
    return null;
  }

  @DeleteMapping("/{id}")
  public void eliminar(@PathVariable int id) {
    mensajeRepository.deleteById(id);
  }

}

