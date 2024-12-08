package com.back_hernansoft.back_hernansoft.controller;

import com.back_hernansoft.back_hernansoft.entity.usuario;
import com.back_hernansoft.back_hernansoft.repository.usuarioRepository;
import com.back_hernansoft.back_hernansoft.service.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/auth")
public class UsuarioController {

    // private static final Logger logger =
    // LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    private usuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public String register(@RequestParam String identificacion,
            @RequestParam String correo,
            @RequestParam String contrasena,
            @RequestParam(value = "rol", required = false, defaultValue = "cliente") String rol,
            @RequestParam String nombre,
            @RequestParam String apellido,
            @RequestParam String tipoIdentificacion,
            @RequestParam String telefono,
            RedirectAttributes redirectAttributes) {
        if (usuarioRepository.existsByIdentificacion(identificacion)) {
            redirectAttributes.addFlashAttribute("error", "El nombre de usuario ya existe");
            return "redirect:/auth/register"; // Redirige al formulario de registro en caso de error
        }
        if (usuarioRepository.existsByCorreo(correo)) {
            redirectAttributes.addFlashAttribute("error", "El correo electrónico ya está registrado");
            return "redirect:/auth/register";
        }

        usuario nuevoUsuario = new usuario();
        nuevoUsuario.setIdentificacion(identificacion);
        System.out.println("identificacion: "+identificacion);
        nuevoUsuario.setCorreo(correo);
        nuevoUsuario.setContrasena(passwordEncoder.encode(contrasena));
        nuevoUsuario.setRol(rol);
        System.out.println("Rol: " + rol);
        nuevoUsuario.setNombre(nombre);
        nuevoUsuario.setApellido(apellido);
        nuevoUsuario.setTipoIdentificacion(tipoIdentificacion);
        nuevoUsuario.setTelefono(telefono);
        nuevoUsuario.setDireccion("");
        // Verificar el usuario antes de guardar
        System.out.println("Nuevo usuario: " + nuevoUsuario);

        // Guardar el usuario
        usuarioRepository.save(nuevoUsuario);

        redirectAttributes.addFlashAttribute("success", "Usuario registrado con éxito");
        return "pagPrincipal"; // Redirige a adminusuarios después del registro
    }

    // @PostMapping("/login")
    // System.err.println("ENTRO AL ENDPOINT login");
    // usuarioRepository.findByIdentificacion(identificacion);
    // if (user.isPresent()) {
    // usuario usuarioEncontrado = user.get();
    // // Loguear los datos del usuario
    // logger.info("Usuario encontrado: ");
    // logger.info("Identificación: {}", usuarioEncontrado.getIdentificacion());
    // System.err.println("Identificación:"+usuarioEncontrado.getIdentificacion());
    // logger.info("Correo: {}", usuarioEncontrado.getCorreo());
    // System.err.println("Correo:"+ usuarioEncontrado.getCorreo());
    // System.err.println("Contrasena:"+ usuarioEncontrado.getContrasena());
    //
    // if (passwordEncoder.matches(contrasena, usuarioEncontrado.getContrasena())) {
    // return "redirect:/homes"; // Redirige a homes después del login exitoso
    // } else {
    // redirectAttributes.addFlashAttribute("error", "Contraseña incorrecta");
    // }
    // } else {
    // redirectAttributes.addFlashAttribute("error", "Usuario no encontrado");
    // }
    //
    // return "pagPrincipal"; // Redirige a login en caso de error
    // }
}
