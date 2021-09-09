/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLASES;

/**
 *
 * @author 21Ernesto00
 */
public class Prenda {
    
    private int idPrendas;
    private String nombre;
    private String modelo;
    private String talla;
    private Double precio;
    private int stock;
    private int fk_idCategoria;

    public int getIdPrendas() {
        return idPrendas;
    }

    public void setIdPrendas(int idPrendas) {
        this.idPrendas = idPrendas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getFk_idCategoria() {
        return fk_idCategoria;
    }

    public void setFk_idCategoria(int fk_idCategoria) {
        this.fk_idCategoria = fk_idCategoria;
    }
}
