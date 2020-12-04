package com.example.infotech.clasespojo;

public class DetallesPedidos {

    private int IdDetallesPedidos;
    private int IdPedido;
    private int IdProducto;
    private float Precio;
    private int Cantidad;

    public DetallesPedidos() {
    }

    public DetallesPedidos(int idDetallesPedidos, int idPedido, int idProducto, float precio, int cantidad) {
        IdDetallesPedidos = idDetallesPedidos;
        IdPedido = idPedido;
        IdProducto = idProducto;
        Precio = precio;
        Cantidad = cantidad;
    }

    public int getIdDetallesPedidos() {
        return IdDetallesPedidos;
    }

    public void setIdDetallesPedidos(int idDetallesPedidos) {
        IdDetallesPedidos = idDetallesPedidos;
    }

    public int getIdPedido() {
        return IdPedido;
    }

    public void setIdPedido(int idPedido) {
        IdPedido = idPedido;
    }

    public int getIdProducto() {
        return IdProducto;
    }

    public void setIdProducto(int idProducto) {
        IdProducto = idProducto;
    }

    public float getPrecio() {
        return Precio;
    }

    public void setPrecio(float precio) {
        Precio = precio;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int cantidad) {
        Cantidad = cantidad;
    }
}
