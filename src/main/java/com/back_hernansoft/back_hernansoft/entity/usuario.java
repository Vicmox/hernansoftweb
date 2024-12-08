package com.back_hernansoft.back_hernansoft.entity;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "usuario")
public class usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    public Integer getIdUsuario() {
    	return this.idUsuario;
    }
    public void setIdUsuario(Integer idUsuario) {
    	this.idUsuario = idUsuario;
    }


    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    public String getNombre() {
    	return this.nombre;
    }
    public void setNombre(String nombre) {
    	this.nombre = nombre;
    }




    @Column(name = "apellido", nullable = false, length = 50)
    private String apellido;

    public String getApellido() {
    	return this.apellido;
    }
    public void setApellido(String apellido) {
    	this.apellido = apellido;
    }



    @Column(name = "tipo_identificacion", length = 30)
    private String tipoIdentificacion;

    public String getTipoIdentificacion() {
    	return this.tipoIdentificacion;
    }
    public void setTipoIdentificacion(String tipoIdentificacion) {
    	this.tipoIdentificacion = tipoIdentificacion;
    }


    @Column(name = "identificacion",nullable = false ,length = 50)
    private String identificacion;

    public String getIdentificacion() {
    	return this.identificacion;
    }
    public void setIdentificacion(String identificacion) {
    	this.identificacion = identificacion;
    }


    @Column(name = "direccion",nullable = true ,length = 50)
    private String direccion;

    public String getDireccion() {
        return this.direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Column(name = "telefono", length = 10)
    private String telefono;

    public String getTelefono() {
    	return this.telefono;
    }
    public void setTelefono(String telefono) {
    	this.telefono = telefono;
    }



    @Column(name = "correo", nullable = false, length = 70)
    private String correo;


    public String getCorreo() {
    	return this.correo;
    }
    public void setCorreo(String correo) {
    	this.correo = correo;
    }


    @Column(name = "contrasena", nullable = false, length = 70)
    private String contrasena;

    public String getContrasena() {
    	return this.contrasena;
    }
    public void setContrasena(String contrasena) {
    	this.contrasena = contrasena;
    }


    @Column(name = "rol", nullable = false, length = 15)
    private String rol;

    public String getRol() {
    	return this.rol;
    }
    public void setRol(String rol) {
    	this.rol = rol;
    }


    // Relación con mensajes como emisor
    @OneToMany(mappedBy = "emisor", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<mensaje> mensajesEnviados;

    // Relación con mensajes como destinatario
    @OneToMany(mappedBy = "destinatario", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<mensaje> mensajesRecibidos;


    @Override
    public String toString() {
        return "usuario{" +
                "idUsuario=" + idUsuario +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", tipoIdentificacion='" + tipoIdentificacion + '\'' +
                ", identificacion='" + identificacion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", correo='" + correo + '\'' +
                ", contrasena='" + contrasena + '\'' +
                ", rol='" + rol + '\'' +
                '}';
    }

}
