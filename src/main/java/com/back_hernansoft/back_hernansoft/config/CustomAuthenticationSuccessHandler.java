package com.back_hernansoft.back_hernansoft.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private static final Map<String, String> ROLE_URL_MAP = new HashMap<>();

    static {
        // Mapear roles a las URLs correspondientes
        ROLE_URL_MAP.put("ROLE_administrador", "/adminUsuarios");
        ROLE_URL_MAP.put("ROLE_gerente", "/pedidos_Gerente");
        ROLE_URL_MAP.put("ROLE_vendedor", "/ventas_Vendedor");
        ROLE_URL_MAP.put("ROLE_cajero", "/cajero_Datos");
        ROLE_URL_MAP.put("ROLE_auxiliar", "/home_Auxiliar");
        ROLE_URL_MAP.put("ROLE_cliente", "/Cliente/cliente_Datos");
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        authentication.getAuthorities().forEach(authority -> {
            System.out.println("Rol autenticado: " + authority.getAuthority());
        });
        // Obtener el usuario autenticado
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        // Guardar el ID del usuario en un header de respuesta o cookie
        response.setHeader("User-Id", userDetails.getIdentificacion());
        // 
        response.addCookie(new Cookie("User-Id", userDetails.getIdentificacion()));
        // Obtener el rol del usuario y redirigir con un query param con la identificación
        String identificacion = userDetails.getIdentificacion();
        String redirectUrl = "/pagPrincipal" + "identificacion=" + identificacion;
        for (var authority : authentication.getAuthorities()) {
            String role = authority.getAuthority();
            if (ROLE_URL_MAP.containsKey(role)) {
                redirectUrl = ROLE_URL_MAP.get(role);
                break;
            }
        }

        // Redirigir al usuario
        response.sendRedirect(redirectUrl);
    }
}
