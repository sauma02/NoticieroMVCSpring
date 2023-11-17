/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.noticiero.controladores;

import com.example.noticiero.excepciones.MiException;
import com.example.noticiero.servicios.NoticiaServicio;
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
@RequestMapping("/noticia")
public class NoticiaControlador {
    @Autowired
    private NoticiaServicio ns;
    @GetMapping("/registrar")
    public String registrar(){
        return "noticia_form.html";
    }
    @PostMapping("/registro")
    //Despues del mapeo viaja la informacion desde el form hasta el metodo para conectar con la base de datos
    public String registro(@RequestParam(required=false) String id, @RequestParam String titulo,@RequestParam  String cuerpo, ModelMap modelo){
        try {
            ns.crearNoticia(id, titulo, cuerpo);
            modelo.put("Exito", "La noticia cargo exitosamente");
        } catch (MiException ex) {
           modelo.put("Error", ex.getMessage());
           return "noticia_form.html";
        }
        
        return "index.html";
    }
    @PostMapping("/modificar")
    //Modificar
    public String modificar(@RequestParam(required=false)String id, @RequestParam String titulo, @RequestParam String cuerpo, ModelMap modelo){
        try {
            ns.modificaNoticia(id, titulo, cuerpo);
            modelo.put("Exito","La noticia se modifico exitosamente");
            
            
        } catch (MiException ex) {
            modelo.put("Error", ex.getMessage());
            return "noticia_modificar_form.html";
        }
        return "index.html";
    }
}
    
