/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.noticiero;

import com.example.noticiero.entidades.Noticia;
import com.example.noticiero.servicios.NoticiaServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Admin
 */
public class UsuarioServicioTest {
    @Test
    public void test1(){
        Noticia n1 = new Noticia("d09a7dd5-3d94-46ac-a1ed-d6a80fca1e39", "asdasd", "asdasdasd");
        NoticiaServicio ns = new NoticiaServicio();
        final Noticia rs = ns.buscarNoticiaPorId("d09a7dd5-3d94-46ac-a1ed-d6a80fca1e39");
        Assertions.assertEquals(n1, rs);
        
    }
}
