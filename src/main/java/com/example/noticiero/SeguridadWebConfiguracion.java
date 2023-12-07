/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.noticiero;

import com.example.noticiero.excepciones.MiException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.web.SecurityFilterChain;

/**
 *
 * @author Admin
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SeguridadWebConfiguracion {
   /**
     * Configuración de la seguridad para las solicitudes HTTP.
     *
     * @param http Configuración de seguridad HTTP.
     * @return Configuración del filtro de seguridad.
     * @throws MiException Excepción personalizada.
     * @throws Exception Excepción general.
     */
    /*http
             .authorizeHttpRequests((authz) -> authz
                                 // Configuración de la autenticación básica de HTTP.

                    .anyRequest().authenticated()
                
                )
                .httpBasic(withDefaults());
                // Devuelve la configuración del filtro de seguridad construido.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws MiException, Exception{
       http.authorizeHttpRequests((autorizaciones) -> autorizaciones
               .requestMatchers("/css/**", "/js/**", "/img/**", "/**").permitAll().anyRequest().authenticated())
               .httpBasic(withDefaults());
       return http.build();
               
               
}
}