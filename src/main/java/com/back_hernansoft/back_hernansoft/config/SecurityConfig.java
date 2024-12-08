package com.back_hernansoft.back_hernansoft.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;
    private final CustomAuthenticationSuccessHandler successHandler;

    // Constructor modificado para inyectar tanto CustomUserDetailsService como
    // CustomAuthenticationSuccessHandler
    public SecurityConfig(CustomUserDetailsService customUserDetailsService,
            CustomAuthenticationSuccessHandler successHandler) {
        this.customUserDetailsService = customUserDetailsService;
        this.successHandler = successHandler; // Aquí se inyecta el successHandler
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Deshabilita CSRF solo para desarrollo
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/inicio", "/auth**", "/paginaLogin", "/login", "/auth/register").permitAll()
                        .requestMatchers("/adminusuarios").hasRole("administrador")
                        .requestMatchers("/homes").hasRole("vendedor")
                        .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/paginaLogin") // Página de login personalizada
                        .loginProcessingUrl("/login") // El endpoint donde se procesan las credenciales
                        .successHandler(successHandler) // Usa el CustomAuthenticationSuccessHandler
                        .failureUrl("/paginaLogin?error=true") // Redirige a /paginaLogin si el login falla
                )
                .logout(logout -> logout
                        .logoutUrl("/logout") // URL para cerrar sesión
                        .logoutSuccessUrl("/login?logout") // Redirigir después del logout
                        .invalidateHttpSession(true) // Invalida la sesión HTTP
                        .deleteCookies("JSESSIONID") // Elimina la cookie de sesión
                        .deleteCookies("User-Id") // Elimina la cookie remember-me
                        .clearAuthentication(true) // Limpia la autenticación
                        .permitAll() // Permitir acceso a todos
                );

        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customUserDetailsService); // Usa tu servicio personalizado
        authProvider.setPasswordEncoder(passwordEncoder()); // Aquí se debe usar el PasswordEncoder
        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return NoOpPasswordEncoder.getInstance();
        // return new BCryptPasswordEncoder();
    }

}
