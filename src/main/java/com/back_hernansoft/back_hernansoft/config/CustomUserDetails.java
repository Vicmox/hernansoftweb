package com.back_hernansoft.back_hernansoft.config;

import com.back_hernansoft.back_hernansoft.entity.usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {

    private String identificacion;
    private String contrasena;
    private String nombre;
    private String apellido;
    private String rol;
    private String correo; // Nueva variable
    private String direccion; // Nueva variable

    // Constructor que recibe un objeto `usuario`
    public CustomUserDetails(usuario user) {
        this.identificacion = user.getIdentificacion();
        this.contrasena = user.getContrasena();
        this.nombre = user.getNombre();
        this.apellido = user.getApellido();
        this.rol = user.getRol();
        this.correo = user.getCorreo(); // Inicializa el correo
        this.direccion = user.getDireccion(); // Inicializa la direccion
    }

    // Implementación de los métodos de UserDetails
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Retorna el rol del usuario como una autoridad
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_" + rol));
    }

    @Override
    public String getPassword() {
        return contrasena;
    }

    @Override
    public String getUsername() {
        return identificacion;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;  // Puedes personalizar esta lógica si lo deseas
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;  // Puedes personalizar esta lógica si lo deseas
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;  // Puedes personalizar esta lógica si lo deseas
    }

    @Override
    public boolean isEnabled() {
        return true;  // Puedes personalizar esta lógica si lo deseas
    }

    // Métodos adicionales para acceder a nombre, apellido y rol
    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getRol() {
        return rol;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    // Getter y Setter para correo
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    // Getter y Setter para direccion
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}

