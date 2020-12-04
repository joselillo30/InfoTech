package com.example.infotech.clasespojo;

import java.util.ArrayList;

public class CarritoDeLaCompra {
    public static ArrayList<Productos> productosCarritos = new ArrayList<>();
    public static void addProducto(Productos productos){
        productosCarritos.add(productos);
    }
    public static void removeProducto(Productos productos){
        productosCarritos.remove(productos);
    }

    public static ArrayList<Productos> getProductosCarritos() {
        return productosCarritos;
    }

    public static void setProductosCarritos(ArrayList<Productos> productosCarritos) {
        CarritoDeLaCompra.productosCarritos = productosCarritos;
    }

    public static boolean compruebaxisteProducto(Productos producto){
        return productosCarritos.contains(producto);
    }

}
