package com.back_hernansoft.back_hernansoft.entity;

import java.sql.Date;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="mensajes")
public class mensaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
    	return this.id;
    }
    public void setId(Long id) {
    	this.id = id;
    }


    @Column(name="mensaje")
    private String mensaje;

    public String getMensaje() {
    	return this.mensaje;
    }
    public void setMensaje(String mensaje) {
    	this.mensaje = mensaje;
    }


    @Column(name="estado")
    private String estado;

    @Column(name="fecha")
    private Date fecha;

    @Column(name = "id_emisor")
    private Integer idEmisor;

    @Column(name = "id_destinatario")
    private Integer idDestinatario;

    @ManyToOne
    @JoinColumn(name = "id_emisor", insertable = false, updatable = false)
    @JsonIgnore
    private usuario emisor;

    public usuario getEmisor() {
    	return this.emisor;
    }
    public void setEmisor(usuario emisor) {
    	this.emisor = emisor;
    }


    @ManyToOne
    @JoinColumn(name = "id_destinatario", insertable = false, updatable = false)
    @JsonIgnore
    private usuario destinatario;

    public usuario getDestinatario() {
    	return this.destinatario;
    }
    public void setDestinatario(usuario destinatario) {
    	this.destinatario = destinatario;
    }



    public Date getFecha() {
    	return this.fecha;
    }
    public void setFecha(Date fecha) {
    	this.fecha = fecha;
    }

    public String getEstado() {
    	return this.estado;
    }
    public void setEstado(String estado) {
    	this.estado = estado;
    }

    public Long getIdMensaje( ) {
    	return this.id;
    }
    public void setIdMensaje(Long id) {
    	this.id = id;
    }

    public void setContenido(String mensaje) {
    	this.mensaje = mensaje;
    }

}
