/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.noticiero.controladores;

import com.example.noticiero.excepciones.MiException;
import com.example.noticiero.servicios.UsuarioServicio;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Admin
 */
@Controller
@RequestMapping("/")
public class PortalController {
    //Se instancia el objeto usuario servicio para poder acceder a los metodos
    //y registrar al usuario
    @Autowired
    private UsuarioServicio us;
    @GetMapping("/")
    public String index(){
        return "index.html";
    }
    
@GetMapping("/registrar")
public String registrar(){
    return "registro.html";
}
@PostMapping("/registro")
public String registro(@RequestParam String nombre, @RequestParam String email, 
        @RequestParam String password, @RequestParam String password2, ModelMap map){
        try {
            //Este metodo recibe los parametros del formulario registrar usuario
            //con la ayuda del usuario servicio podemos registrar al usuario
            us.registrarUsuario(nombre, email, password, password2);
            map.put("Exito", "Usuario registrado correctamente!");
            return "index.html";
        } catch (MiException ex) {
            map.put("Error", ex.getMessage());
            return "registro.html";
           
        }
    
}
@GetMapping("/login")
public String login(){
    return "login.html";
}
    
}
