package com.example.infotech.clasespojo;

import java.util.ArrayList;
import java.util.List;

public class ListaProductos {
    private static ArrayList<Productos> listaProductos = new ArrayList<>();

    public static ArrayList<Productos> getListaProductos() {
        return listaProductos;
    }

    public static void setListaProductos(ArrayList<Productos> listaProductos) {
        ListaProductos.listaProductos = listaProductos;
    }
    public static void setListaProductos(List<Productos> listProductos) {
        for (Productos p :
                listProductos) {
            listaProductos.add(p);
        }
    }
}
