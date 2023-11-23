/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.noticiero.servicios;

import com.example.noticiero.entidades.Noticia;
import com.example.noticiero.excepciones.MiException;
import com.example.noticiero.repositorios.NoticiaRepositorio;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Admin
 */
@Service
public class NoticiaServicio {
    @Autowired
    private NoticiaRepositorio ns;
    @Transactional
    public void crearNoticia(@RequestParam(required=false)String id,  String titulo,
             String cuerpo) throws MiException{
     validar(titulo, cuerpo);
     Noticia n1 = new Noticia();
     n1.setTitulo(titulo);
     n1.setCuerpo(cuerpo);
     ns.save(n1);
     
    }
    public void modificaNoticia( String id,  String titulo, 
             String cuerpo  ) throws MiException{
        validar(titulo, cuerpo);
        Optional<Noticia> res = ns.findById(id);
        if(res.isPresent()){
            Noticia n1 = res.get();
            n1.setTitulo(titulo);
            n1.setCuerpo(cuerpo);
            ns.save(n1);
        }
    }
    public void borrarNoticiaPorId( String id){
        Optional<Noticia> res = ns.findById(id);
        if(res.isPresent()){
            Noticia n1 = res.get();
            ns.delete(n1);
        }
    }
    public Noticia buscarNoticiaPorId( String id){
        Optional<Noticia> res = ns.findById(id);
        if(res.isPresent()){
            Noticia n1 = res.get();
            return n1; 
        }else{
            Noticia n1 = res.orElseThrow();
            return n1;
        }
           
        
    }
    public void validar(String titulo, String cuerpo) throws MiException{
      
        if(titulo.isEmpty() || titulo == null){
            throw new MiException("El titulo no puede ser nulo");
        }
        if(cuerpo.isEmpty() || cuerpo == null){
            throw new MiException("El cuerpo no puede ser nulo");
        }
    }
    public List<Noticia> listarNoticias(){
        List<Noticia> lista = ns.findAll();
        return lista;
    }
}
