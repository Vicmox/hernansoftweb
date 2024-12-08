package com.back_hernansoft.back_hernansoft.controller;

import com.back_hernansoft.back_hernansoft.config.CustomUserDetails;
import com.back_hernansoft.back_hernansoft.entity.usuario;
import com.back_hernansoft.back_hernansoft.service.interfac.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping
public class VistasController {

    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping({"/inicio", "/"})
    public String inicio(Model model) {
        // Obtener la autenticación actual
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

            model.addAttribute("nombre", userDetails.getNombre());
            model.addAttribute("apellido", userDetails.getApellido());
            model.addAttribute("rol", userDetails.getRol());
        } else {
            model.addAttribute("error", "No se pudo obtener los detalles del usuario.");
        }

        return "index";  // Nombre del archivo HTML
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @GetMapping("/homes")
    public String home(Model model) {
        // Obtener la autenticación actual
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

            model.addAttribute("nombre", userDetails.getNombre());
            model.addAttribute("apellido", userDetails.getApellido());
            model.addAttribute("rol", userDetails.getRol());
        } else {
            model.addAttribute("error", "No se pudo obtener los detalles del usuario.");
        }

        return "home";  // Nombre del archivo HTML
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @GetMapping("/adminusuarios")
    public String adminusuarios(Model model) {
        String numeroDocumento = null;  // Inicializamos la variable antes del bloque if


        // Obtener lista de usuarios
        List<usuario> usuarios= usuarioService.obtenerTodosUsuarios();
        usuario usuario= new usuario();
        model.addAttribute("usuarios",usuarios);
        model.addAttribute("usuario",usuario);

        // Obtener la autenticación actual
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

            // Obtener el usuario completo basado en el nombre o identificador del usuario
            Optional<usuario> usuarioActual = usuarioService.findByIdentificacion(userDetails.getIdentificacion());

            // Pasar detalles del usuario al modelo
            model.addAttribute("usuarioActual", usuarioActual);
            model.addAttribute("nombre", userDetails.getNombre());
            model.addAttribute("apellido", userDetails.getApellido());
            model.addAttribute("rol", userDetails.getRol());
            model.addAttribute("identificacion", userDetails.getIdentificacion());
            model.addAttribute("correo", userDetails.getCorreo());
            model.addAttribute("direccion", userDetails.getDireccion());

            numeroDocumento = userDetails.getIdentificacion();
        } else {
            model.addAttribute("error", "No se pudo obtener los detalles del usuario.");
        }

        System.err.println("EL USUARIO TIENE EL NUMERO DE IDENTIFICACION "+numeroDocumento);
        if (numeroDocumento != null) {
            Optional<usuario> usuarioBuscado = usuarioService.findByIdentificacion(numeroDocumento);

            if (usuarioBuscado.isPresent()) {
                model.addAttribute("usuario", usuarioBuscado.get()); // Cargar los datos del usuario encontrado en el formulario
            } else {
                model.addAttribute("error", "Usuario no encontrado");
            }
        } else {
            model.addAttribute("error", "No se pudo obtener el documento del usuario.");
        }


        return "Admin/adminPerfil";  // Nombre del archivo HTML
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @GetMapping("/paginaLogin")
    public String paginaLogin(Model model) {


        // Obtener lista de usuarios
        List<usuario> usuarios= usuarioService.obtenerTodosUsuarios();
        usuario usuario= new usuario();
        model.addAttribute("usuarios",usuarios);
        model.addAttribute("usuario",usuario);

        // Obtener la autenticación actual
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

            model.addAttribute("nombre", userDetails.getNombre());
            model.addAttribute("apellido", userDetails.getApellido());
            model.addAttribute("rol", userDetails.getRol());
        } else {
            model.addAttribute("error", "No se pudo obtener los detalles del usuario.");
        }

        return "pagPrincipal";  // Nombre del archivo HTML
    }
}
