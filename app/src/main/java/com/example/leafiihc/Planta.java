package com.example.leafiihc;

public class Planta {
    private String nombre;
    private String nombreCientifico;
    private String descripcion;
    private int imagenResourceId;
    private String imageUrl;
    private String familia;
    private String genero;
    private String riego;
    private String luz;
    private String temperatura;
    private String cuidados;
    private String toxicidad;
    private String crecimiento;
    private String propagacion;
    private String enfermedades;
    private String plagas;
    private String problemasConocidos;
    private String instruccionesCuidado;
    private int perenualId;

    // Constructor vacío
    public Planta() {
    }

    // Constructor con parámetros básicos
    public Planta(String nombre, String nombreCientifico, String descripcion, int imagenResourceId) {
        this.nombre = nombre;
        this.nombreCientifico = nombreCientifico;
        this.descripcion = descripcion;
        this.imagenResourceId = imagenResourceId;
    }

    // Constructor completo
    public Planta(String nombre, String nombreCientifico, String descripcion, int imagenResourceId,
                 String imageUrl, String familia, String genero, String riego, String luz,
                 String temperatura, String cuidados, String toxicidad, String crecimiento,
                 String propagacion, String enfermedades, String plagas, String problemasConocidos,
                 String instruccionesCuidado, int perenualId) {
        this.nombre = nombre;
        this.nombreCientifico = nombreCientifico;
        this.descripcion = descripcion;
        this.imagenResourceId = imagenResourceId;
        this.imageUrl = imageUrl;
        this.familia = familia;
        this.genero = genero;
        this.riego = riego;
        this.luz = luz;
        this.temperatura = temperatura;
        this.cuidados = cuidados;
        this.toxicidad = toxicidad;
        this.crecimiento = crecimiento;
        this.propagacion = propagacion;
        this.enfermedades = enfermedades;
        this.plagas = plagas;
        this.problemasConocidos = problemasConocidos;
        this.instruccionesCuidado = instruccionesCuidado;
        this.perenualId = perenualId;
    }

    // Getters y Setters
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

    public int getImagenResourceId() {
        return imagenResourceId;
    }

    public void setImagenResourceId(int imagenResourceId) {
        this.imagenResourceId = imagenResourceId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getFamilia() {
        return familia;
    }

    public void setFamilia(String familia) {
        this.familia = familia;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getRiego() {
        return riego;
    }

    public void setRiego(String riego) {
        this.riego = riego;
    }

    public String getLuz() {
        return luz;
    }

    public void setLuz(String luz) {
        this.luz = luz;
    }

    public String getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(String temperatura) {
        this.temperatura = temperatura;
    }

    public String getCuidados() {
        return cuidados;
    }

    public void setCuidados(String cuidados) {
        this.cuidados = cuidados;
    }

    public String getToxicidad() {
        return toxicidad;
    }

    public void setToxicidad(String toxicidad) {
        this.toxicidad = toxicidad;
    }

    public String getCrecimiento() {
        return crecimiento;
    }

    public void setCrecimiento(String crecimiento) {
        this.crecimiento = crecimiento;
    }

    public String getPropagacion() {
        return propagacion;
    }

    public void setPropagacion(String propagacion) {
        this.propagacion = propagacion;
    }

    public String getEnfermedades() {
        return enfermedades;
    }

    public void setEnfermedades(String enfermedades) {
        this.enfermedades = enfermedades;
    }

    public String getPlagas() {
        return plagas;
    }

    public void setPlagas(String plagas) {
        this.plagas = plagas;
    }

    public String getProblemasConocidos() {
        return problemasConocidos;
    }

    public void setProblemasConocidos(String problemasConocidos) {
        this.problemasConocidos = problemasConocidos;
    }

    public String getInstruccionesCuidado() {
        return instruccionesCuidado;
    }

    public void setInstruccionesCuidado(String instruccionesCuidado) {
        this.instruccionesCuidado = instruccionesCuidado;
    }

    public int getPerenualId() {
        return perenualId;
    }

    public void setPerenualId(int perenualId) {
        this.perenualId = perenualId;
    }
}