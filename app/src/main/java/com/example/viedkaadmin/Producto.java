package com.example.viedkaadmin;

public class Producto {
    private int id;
    private String nombre;
    private String categoria;
    private String existencias;
    private String precioC;
    private String precioV;

    public Producto(int id, String nombre, String categoria, String existencias, String precioC, String precioV) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.existencias = existencias;
        this.precioC = precioC;
        this.precioV = precioV;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getExistencias() {
        return existencias;
    }

    public void setExistencias(String existencias) {
        this.existencias = existencias;
    }

    public String getPrecioC() {
        return precioC;
    }

    public void setPrecioC(String precioC) {
        this.precioC = precioC;
    }

    public String getPrecioV() {
        return precioV;
    }

    public void setPrecioV(String precioV) {
        this.precioV = precioV;
    }
}
