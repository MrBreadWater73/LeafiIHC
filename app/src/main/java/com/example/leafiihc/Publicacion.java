package com.example.leafiihc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Publicacion {
    private String autor;
    private String titulo;
    private String contenido;
    private Date fecha;
    private int likes;
    private int comentarios;
    private boolean meGusta;
    private List<Comentario> listaComentarios;

    public Publicacion(String autor, String titulo, String contenido, Date fecha) {
        this.autor = autor;
        this.titulo = titulo;
        this.contenido = contenido;
        this.fecha = fecha;
        this.likes = 0;
        this.comentarios = 0;
        this.meGusta = false;
        this.listaComentarios = new ArrayList<>();
    }

    public String getAutor() {
        return autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public Date getFecha() {
        return fecha;
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

    public boolean isMeGusta() {
        return meGusta;
    }

    public void setMeGusta(boolean meGusta) {
        this.meGusta = meGusta;
    }

    public List<Comentario> getListaComentarios() {
        return listaComentarios;
    }

    public void agregarComentario(Comentario comentario) {
        listaComentarios.add(comentario);
        comentarios++;
    }

    public void toggleMeGusta() {
        meGusta = !meGusta;
        likes += meGusta ? 1 : -1;
    }
}