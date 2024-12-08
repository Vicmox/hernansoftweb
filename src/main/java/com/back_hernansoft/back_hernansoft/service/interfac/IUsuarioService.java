package com.back_hernansoft.back_hernansoft.service.interfac;

import com.back_hernansoft.back_hernansoft.entity.usuario;
import java.util.List;
import java.util.Optional;


public interface IUsuarioService {
    public List<usuario> obtenerTodosUsuarios();
    public usuario findUsuario(Integer id_usuario);
    public Optional<usuario> findByIdentificacion(String identificacion);
    public void insertar(usuario usuario);

    public void delete(Integer id);

    public void update(usuario usuario);
}
