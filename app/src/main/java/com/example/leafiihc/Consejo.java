package com.example.leafiihc;

public class Consejo {
    private String titulo;
    private String descripcion;
    private String categoria;
    private boolean favorito;

    public Consejo(String titulo, String descripcion, String categoria) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.favorito = false;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public boolean isFavorito() {
        return favorito;
    }

    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
    }
}