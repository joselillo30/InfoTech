package com.example.infotech.clasespojo;

import androidx.annotation.NonNull;
//Elementos del menu lateral
public class ItemMenu {
    private MenuItems id;
    private String titulo;

    public ItemMenu(MenuItems id, String titulo) {
        this.id = id;
        this.titulo = titulo;
    }

    public MenuItems getId() {
        return id;
    }

    public void setId(MenuItems id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @NonNull
    @Override
    public String toString() {
        return titulo;
    }
}
