/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.noticiero.controladores;

import com.example.noticiero.entidades.Noticia;
import com.example.noticiero.excepciones.MiException;
import com.example.noticiero.servicios.NoticiaServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    @GetMapping("/modificar/{id}")
    //Modificar
    public String modificar(@PathVariable String id, ModelMap modelo){
       try {
        // Obtener la noticia por el ID
        Noticia noticia = ns.buscarNoticiaPorId(id);

        // Verificar si la noticia existe
        if (noticia != null) {
            // Agregar la noticia al modelo
            modelo.addAttribute("noticia", noticia);
            return "noticia_modificar.html";
        } else {
            modelo.addAttribute("Error", "La noticia con ID " + id + " no existe");
            return "error.html";
        }
    } catch (Exception ex) {
        modelo.addAttribute("Error", ex.getMessage());
        return "error.html";
    }
       
    }
    @PostMapping("/modificar/{id}")
public String procesarModificacion(@PathVariable String id, @RequestParam String titulo, @RequestParam String cuerpo, ModelMap modelo) {
    try {
        // Lógica para modificar la noticia con los nuevos datos
        ns.modificaNoticia(id, titulo, cuerpo);

        modelo.put("Exito", "La noticia se modificó exitosamente");
        return "noticia_lista.html";  // o redirige a donde desees después de la modificación
    } catch (MiException ex) {
        modelo.put("Error", ex.getMessage());
        return "noticia_modificar.html";  // o redirige nuevamente al formulario de modificación
    }
}
    @GetMapping("/listar")
    public String listarNoticias(Model modelo) throws MiException, Exception{
        try {
            List<Noticia> lista = ns.listarNoticias();
            modelo.addAttribute("elemento", lista);
            return "noticia_lista.html";
        } catch (Exception ex) {
            throw new Exception("Error");
            
            
        }finally{
            return "noticia_lista.html";
        }
        
    }
    @GetMapping("/eliminar/{id}")
    public String Eliminar(@PathVariable String id, Model modelo){
        try {
            ns.borrarNoticiaPorId(id);
            modelo.addAttribute("Exito", "Exito al eliminar");
        } catch (Exception ex) {
            modelo.addAttribute("Error", ex.getMessage());
        
        }
       return "redirect:/noticia/listar";
    }
}
    
