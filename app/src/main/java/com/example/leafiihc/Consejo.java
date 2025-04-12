package com.example.leafiihc;

public class Consejo {
    private String titulo;
    private String descripcion;
    private String categoria;
    private int iconoResId;

    public Consejo(String titulo, String descripcion, String categoria, int iconoResId) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.iconoResId = iconoResId;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public int getIconoResId() {
        return iconoResId;
    }
}