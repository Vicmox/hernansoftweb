package com.back_hernansoft.back_hernansoft.service;

import com.back_hernansoft.back_hernansoft.repository.usuarioRepository;
import com.back_hernansoft.back_hernansoft.entity.usuario;
import com.back_hernansoft.back_hernansoft.service.interfac.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.back_hernansoft.back_hernansoft.entity.usuario;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    private usuarioRepository usuarioRepository;
    //@Autowired
    //private PasswordEncoder passwordEncoder;


    @Override
    public List<usuario> obtenerTodosUsuarios() {
        List<usuario> usuarios = usuarioRepository.findAll();
        //System.out.println("SOUT DE PRUEBA" + usuarios);  // Asegúrate de que los datos se están cargando
        return usuarios;
    }

    @Override
    public usuario findUsuario(Integer id_usuario){

        return usuarioRepository.findById(id_usuario).orElse(null);
    }

    @Override
    public Optional<usuario> findByIdentificacion(String identificacion){

        return usuarioRepository.findByIdentificacion(identificacion);
    }

    @Override
    public void insertar(usuario usuario){
        //System.err.println("Antes" + usuario.getContrasena());
        //CODIFICAR CONTRASEÑA
//        usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
//        System.err.println("Despues " + usuario.getContrasena());
//        System.out.println( "USUARIO EN USUARIOSERVICE "+ usuario.toString());
        usuarioRepository.saveAndFlush(usuario);
    }

    
    public usuario obtenerUsuarioPorIdentificacion(String identificacion) {
        Optional<usuario> usuarioOpt = usuarioRepository.findByIdentificacion(identificacion);
        return usuarioOpt.orElse(null);
    }

    @Override
    public void update(usuario usuario) {
        System.err.println("ENTRO A USUARIOSERVICE UPDATE");
        // Buscar el usuario en la base de datos por ID
        Optional<usuario> usuarioExistenteOpt = usuarioRepository.findById(usuario.getIdUsuario());

        if (usuarioExistenteOpt.isPresent()) {
            usuario usuarioExistente = usuarioExistenteOpt.get();

            // Imprimir los valores recibidos para depuración
            System.out.println(usuario.getIdUsuario());
            System.out.println(usuario.getNombre());
            System.out.println(usuario.getApellido());
            System.out.println(usuario.getIdentificacion() + " que es de tipo " + usuario.getTipoIdentificacion());
            System.out.println(usuario.getTelefono());
            System.out.println(usuario.getCorreo());
            System.out.println(usuario.getContrasena());
            System.out.println(usuario.getRol());
            System.out.println(usuario.getDireccion());

            // Actualizar los campos del usuario existente
            usuarioExistente.setNombre(usuario.getNombre());
            usuarioExistente.setApellido(usuario.getApellido());
            usuarioExistente.setIdentificacion(usuario.getIdentificacion());
            usuarioExistente.setTipoIdentificacion(usuario.getTipoIdentificacion());
            usuarioExistente.setTelefono(usuario.getTelefono());
            usuarioExistente.setCorreo(usuario.getCorreo());
            usuarioExistente.setRol(usuario.getRol());
            usuarioExistente.setContrasena(usuario.getContrasena());
            usuarioExistente.setDireccion(usuario.getDireccion());

            // Si se pasa una contraseña nueva, encriptarla antes de guardar
//            if (usuario.getContrasena() != null && !usuario.getContrasena().isEmpty()) {
//                usuarioExistente.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
//            }
            usuarioExistente.setContrasena(usuario.getContrasena());

            // Guardar los cambios en la base de datos
            usuarioRepository.saveAndFlush(usuarioExistente);
        } else {
            throw new IllegalArgumentException("Usuario con ID " + usuario.getIdentificacion() + " no encontrado.");
        }
    }

    
    @Override
    public void delete(Integer  id) {
        usuarioRepository.deleteById(id);

    }
}
