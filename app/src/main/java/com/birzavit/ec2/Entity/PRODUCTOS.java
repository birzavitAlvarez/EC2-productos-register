package com.birzavit.ec2.Entity;

public class PRODUCTOS {

    private int id_prod;
    private String nom_prod;
    private String categoria;
    private int stock;
    private double precio;

    public PRODUCTOS() {
    }

    public PRODUCTOS(int id_prod, String nom_prod, String categoria, int stock, double precio) {
        this.id_prod = id_prod;
        this.nom_prod = nom_prod;
        this.categoria = categoria;
        this.stock = stock;
        this.precio = precio;
    }

    public int getId_prod() {
        return id_prod;
    }

    public void setId_prod(int id_prod) {
        this.id_prod = id_prod;
    }

    public String getNom_prod() {
        return nom_prod;
    }

    public void setNom_prod(String nom_prod) {
        this.nom_prod = nom_prod;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
