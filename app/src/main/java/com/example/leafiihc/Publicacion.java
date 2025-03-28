package com.example.leafiihc;

import java.util.Date;

public class Publicacion {
    private String usuario;
    private String titulo;
    private String contenido;
    private Date fecha;
    private int likes;
    private int comentarios;
    private String imagenUrl;

    public Publicacion(String usuario, String titulo, String contenido, Date fecha) {
        this.usuario = usuario;
        this.titulo = titulo;
        this.contenido = contenido;
        this.fecha = fecha;
        this.likes = 0;
        this.comentarios = 0;
        this.imagenUrl = null;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getComentarios() {
        return comentarios;
    }

    public void setComentarios(int comentarios) {
        this.comentarios = comentarios;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }
}