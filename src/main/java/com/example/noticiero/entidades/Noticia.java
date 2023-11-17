/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.noticiero.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.hibernate.annotations.GeneratorType;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Admin
 */
@Entity
public class Noticia {
    @Id
    @GeneratedValue(generator="uuid")
    @GenericGenerator(name="uuid", strategy="uuid2")
    private String id;
    private String titulo;
    private String cuerpo;

    public Noticia() {
    }

    public Noticia(String id, String titulo, String cuerpo) {
        this.id = id;
        this.titulo = titulo;
        this.cuerpo = cuerpo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    @Override
    public String toString() {
        return "Noticia{" + "id=" + id + ", titulo=" + titulo + ", cuerpo=" + cuerpo + '}';
    }
    
    
}
