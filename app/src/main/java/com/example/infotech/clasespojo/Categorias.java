package com.example.infotech.clasespojo;

public class Categorias {
    private int IdCategoria;
    private String NomCategoria;
    private String DescripcionCat;
    private String ImgCategoria;

    public Categorias() {
    }

    public Categorias(int IdCategoria, String NomCategoria, String DescripcionCat, String ImgCategoria) {
        this.IdCategoria = IdCategoria;
        this.NomCategoria = NomCategoria;
        this.DescripcionCat = DescripcionCat;
        this.ImgCategoria = ImgCategoria;
    }

    public int getIdCategoria() {
        return IdCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.IdCategoria = idCategoria;
    }

    public String getNomCategoria() {
        return NomCategoria;
    }

    public void setNomCategoria(String nomCategoria) {
        this.NomCategoria = nomCategoria;
    }

    public String getDescripcionCat() {
        return DescripcionCat;
    }

    public void setDescripcionCat(String descripcionCat) {
        this.DescripcionCat = descripcionCat;
    }

    public String getImgCategoria() {
        return ImgCategoria;
    }

    public void setImgCategoria(String imgCategoria) {
        this.ImgCategoria = imgCategoria;
    }
}
