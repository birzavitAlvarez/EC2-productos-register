package com.birzavit.ec2.DAO;

import com.birzavit.ec2.Entity.PRODUCTOS;

import java.util.ArrayList;
import java.util.List;

public class PRODUCTOS_DAO {

    public static String[] vProductos = {
            "SELECCIONE","COMPUTO","LINEA BLANCA","ABARROTES"
    };

    private static List<PRODUCTOS> lista = new ArrayList<>();
    //
    public PRODUCTOS BuscarProductos(int id) {
        for (PRODUCTOS productos : lista) {
            if (productos.getId_prod() == id) return productos;
        }
        return null;
    }

    public String GrabarProducto(PRODUCTOS productos) {
        if (BuscarProductos(productos.getId_prod()) == null) {
            lista.add(productos);
            return "El Producto: " + productos.getNom_prod() +" fue registrado correctamente";
        } else {
            return "Error, Código del Producto Duplicado";
        }
    }

    public List<PRODUCTOS> ListarProductos() {
        return lista;
    }

    public List<String> ListarProductosCategoriaStock(String nom_prod, int stock)
    {
        List<String> lista_prod = new ArrayList<String>();
        for (PRODUCTOS prod:  lista ) {
            if (prod.getCategoria().equals(nom_prod)  && prod.getStock() > stock) {
                lista_prod.add(
                        "ID: " + prod.getId_prod() + "\n" +
                                "Nombre: " + prod.getNom_prod() + "\n" +
                                "Categoria: " + prod.getCategoria() + "\n" +
                                "Stock: " + prod.getStock() + "\n" +
                                "Precio: " + prod.getPrecio());
            }
        }
        //
        return lista_prod;
    }

    public String EliminarProducto(int id) {
        PRODUCTOS productos = BuscarProductos(id);
        if (productos != null) {
            lista.remove(productos);
            return "Producto eliminado correctamente";
        } else {
            return "Error, código del producto no existe";
        }
    }

}
