package com.back_hernansoft.back_hernansoft.config;

import com.back_hernansoft.back_hernansoft.entity.usuario;
import com.back_hernansoft.back_hernansoft.service.interfac.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private IUsuarioService usuarioService;  // Usa tu servicio existente

    @Override
    public UserDetails loadUserByUsername(String identificacion) throws UsernameNotFoundException {
        System.err.println("ENTRO A CUSTOMUSERDETAILSSERVICE");

        // Busca el usuario en la base de datos por identificación
        usuario user = usuarioService.findByIdentificacion(identificacion)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        // Debugging para mostrar la información del usuario
        System.err.println("Usuario enviado: " + identificacion);
        System.err.println("Usuario encontrado: " + user.getIdentificacion());
        System.err.println("Contraseña del usuario: " + user.getContrasena());
        System.err.println("Rol del usuario: " + user.getRol());

        // Retorna una instancia de CustomUserDetails en lugar de User.builder()
        return new CustomUserDetails(user);
    }
}


//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Autowired
//    private IUsuarioService usuarioService;  // Usa tu servicio existente
//
//    @Override
//    public UserDetails loadUserByUsername(String identificacion) throws UsernameNotFoundException {
//        // Busca el usuario en la base de datos por identificación
//        usuario user = usuarioService.findByIdentificacion(identificacion)
//                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
//
//        System.err.println("Usuario encontrado: " + user.getIdentificacion());
//        System.err.println("Contraseña del usuario: " + user.getContrasena());
//
//        if (passwordEncoder.matches(user.getContrasena(), user.getContrasena())) {
//            System.err.println("Contraseña correcta");
//        } else {
//            System.err.println("Contraseña incorrecta");
//        }
//
//        // Crea y retorna un UserDetails con el rol del usuario
//        return User.builder()
//                .username(user.getIdentificacion())  // Nombre de usuario
//                .password(user.getContrasena())      // Contraseña hasheada
//                .roles(user.getRol())                // Rol del usuario
//                .build();
//    }



