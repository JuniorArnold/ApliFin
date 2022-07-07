package com.upn.final_app.entidad;

public class Bien {
    private String id;
    private String nombre, codPat, estado;
    private int vida;

    public Bien(String id, String nombre, String codPat, String estado, int vida) {
        this.id = id;
        this.nombre = nombre;
        this.codPat = codPat;
        this.estado = estado;
        this.vida = vida;
    }

    public Bien(String nombre, String codPat, String estado, int vida) {
        this.nombre = nombre;
        this.codPat = codPat;
        this.estado = estado;
        this.vida = vida;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodPat() {
        return codPat;
    }

    public void setCodPat(String codPat) {
        this.codPat = codPat;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }
}
