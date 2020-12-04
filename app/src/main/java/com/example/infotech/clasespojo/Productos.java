package com.example.infotech.clasespojo;

public class Productos {
    private int IdProducto;
    private String NomProducto;
    private String DescProducto;
    private String Especificaciones;
    private int IdCategoria;
    private float PrecioUnitario;
    private int Stock;
    private boolean Disponible;
    private String ImgProducto;
    private int Descuento;
    private int Cantidad;
    private int Ranking;

    public Productos() {
    }

    public Productos(int idProducto, String nomProducto, String descProducto, String especificaciones, int idCategoria, float precioUnitario, int stock, boolean disponible, String imgProducto, int ranking, int descuento) {
        this.IdProducto = idProducto;
        this.NomProducto = nomProducto;
        this.DescProducto = descProducto;
        this.Especificaciones = especificaciones;
        this.IdCategoria = idCategoria;
        this.PrecioUnitario = precioUnitario;
        this.Stock = stock;
        this.Disponible = disponible;
        this.ImgProducto = imgProducto;
        this.Ranking = ranking;
        this.Descuento = descuento;
    }

    public Productos(String nomProducto, String descProducto, String especificaciones, int idCategoria, float precioUnitario, int stock, boolean disponible, String imgProducto, int ranking) {
        this.NomProducto = nomProducto;
        this.DescProducto = descProducto;
        this.Especificaciones = especificaciones;
        this.IdCategoria = idCategoria;
        this.PrecioUnitario = precioUnitario;
        this.Stock = stock;
        this.Disponible = disponible;
        this.ImgProducto = imgProducto;
        this.Ranking = ranking;

    }

    public int getDescuento() {
        return Descuento;
    }

    public void setDescuento(int descuento) {
        Descuento = descuento;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int cantidad) {
        Cantidad = cantidad;
    }

    public int getIdProducto() {
        return IdProducto;
    }

    public void setIdProducto(int idProducto) {
        this.IdProducto = idProducto;
    }

    public String getNomProducto() {
        return NomProducto;
    }

    public void setNomProducto(String nomProducto) {
        this.NomProducto = nomProducto;
    }

    public String getDescProducto() {
        return DescProducto;
    }

    public void setDescProducto(String descProducto) {
        this.DescProducto = descProducto;
    }

    public String getEspecificaciones() {
        return Especificaciones;
    }

    public void setEspecificaciones(String especificaciones) {
        this.Especificaciones = especificaciones;
    }

    public int getIdCategoria() {
        return IdCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.IdCategoria = idCategoria;
    }

    public float getPrecioUnitario() {
        return PrecioUnitario;
    }

    public void setPrecioUnitario(float precioUnitario) {
        this.PrecioUnitario = precioUnitario;
    }

    public int getStock() {
        return Stock;
    }

    public void setStock(int stock) {
        this.Stock = stock;
    }

    public boolean isDisponible() {
        return Disponible;
    }

    public void setDisponible(boolean disponible) {
        this.Disponible = disponible;
    }

    public String getImgProducto() {
        return ImgProducto;
    }

    public void setImgProducto(String imgProducto) {
        this.ImgProducto = imgProducto;
    }

    public int getRanking() {
        return Ranking;
    }

    public void setRanking(int ranking) {
        this.Ranking = ranking;
    }
}
