package com.example.leafiihc;

public class Usuario {
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private String fotoPerfil;
    private boolean recibirNotificaciones;
    private boolean consejosDiarios;
    private boolean temaOscuro;

    public Usuario() {
        // Constructor vacío para inicialización por defecto
        this.nombre = "";
        this.apellido = "";
        this.email = "";
        this.telefono = "";
        this.fotoPerfil = "";
        this.recibirNotificaciones = true;
        this.consejosDiarios = true;
        this.temaOscuro = false;
    }

    public Usuario(String nombre, String apellido, String email, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.fotoPerfil = "";
        this.recibirNotificaciones = true;
        this.consejosDiarios = true;
        this.temaOscuro = false;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public boolean isRecibirNotificaciones() {
        return recibirNotificaciones;
    }

    public void setRecibirNotificaciones(boolean recibirNotificaciones) {
        this.recibirNotificaciones = recibirNotificaciones;
    }

    public boolean isConsejosDiarios() {
        return consejosDiarios;
    }

    public void setConsejosDiarios(boolean consejosDiarios) {
        this.consejosDiarios = consejosDiarios;
    }

    public boolean isTemaOscuro() {
        return temaOscuro;
    }

    public void setTemaOscuro(boolean temaOscuro) {
        this.temaOscuro = temaOscuro;
    }

    // Método para obtener el nombre completo
    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }
}