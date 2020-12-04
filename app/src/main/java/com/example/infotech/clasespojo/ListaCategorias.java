package com.example.infotech.clasespojo;

import java.util.ArrayList;
import java.util.List;

public class ListaCategorias {
    private static ArrayList<Categorias> listaCategorias = new ArrayList<>();

    public static ArrayList<Categorias> getListaCategorias() {
        return listaCategorias;
    }

    public static void setListaCategorias(ArrayList<Categorias> listCategorias) {
        listaCategorias = listCategorias;
    }
    public static void setListaCategorias(List<Categorias> listCategorias) {
        for (Categorias c :
                listCategorias) {
            listaCategorias.add(c);
        }
    }
}
