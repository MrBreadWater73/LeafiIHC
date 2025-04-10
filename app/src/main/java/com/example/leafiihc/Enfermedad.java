package com.example.leafiihc;

public class Enfermedad {
    private String nombre;
    private String nombreCientifico;
    private String descripcion;
    private String solucion;
    private String plantasAfectadas;
    private int imagenResourceId;

    public Enfermedad() {
    }

    public Enfermedad(String nombre, String nombreCientifico, String descripcion, String solucion, String plantasAfectadas, int imagenResourceId) {
        this.nombre = nombre;
        this.nombreCientifico = nombreCientifico;
        this.descripcion = descripcion;
        this.solucion = solucion;
        this.plantasAfectadas = plantasAfectadas;
        this.imagenResourceId = imagenResourceId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreCientifico() {
        return nombreCientifico;
    }

    public void setNombreCientifico(String nombreCientifico) {
        this.nombreCientifico = nombreCientifico;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getSolucion() {
        return solucion;
    }

    public void setSolucion(String solucion) {
        this.solucion = solucion;
    }

    public String getPlantasAfectadas() {
        return plantasAfectadas;
    }

    public void setPlantasAfectadas(String plantasAfectadas) {
        this.plantasAfectadas = plantasAfectadas;
    }

    public int getImagenResourceId() {
        return imagenResourceId;
    }

    public void setImagenResourceId(int imagenResourceId) {
        this.imagenResourceId = imagenResourceId;
    }
} 