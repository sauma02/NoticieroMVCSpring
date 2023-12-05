/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.noticiero.repositorios;

import com.example.noticiero.entidades.Noticia;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Admin
 */
@Repository
public interface NoticiaRepositorio extends JpaRepository<Noticia, String> {
//    @Query("SELECT n FROM noticia n WHERE n.titulo = :titulo;")
//    public Noticia buscarNoticiaPorTitulo(@Param("titulo") String titulo);
//    @Query("SELECT n FROM noticia n WHERE n.id=:id;")
//  public Noticia buscarNoticiaPorId(@Param("id") String id);
    
}
