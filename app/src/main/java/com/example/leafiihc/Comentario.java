package com.example.leafiihc;

import java.util.Date;

public class Comentario {
    private String autor;
    private String contenido;
    private Date fecha;

    public Comentario(String autor, String contenido, Date fecha) {
        this.autor = autor;
        this.contenido = contenido;
        this.fecha = fecha;
    }

    public String getAutor() {
        return autor;
    }

    public String getContenido() {
        return contenido;
    }

    public Date getFecha() {
        return fecha;
    }
} 