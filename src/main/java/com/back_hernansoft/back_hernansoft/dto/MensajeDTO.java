package com.back_hernansoft.back_hernansoft.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MensajeDTO {
    
    private Integer idMensaje;
    private String mensaje;
    private String fecha;
    private String estado;
    private String nombreEmisor;
    private String nombreDestinatario;



}
