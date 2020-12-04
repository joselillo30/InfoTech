package com.example.infotech.clasespojo;

import java.util.Date;

public class Pedido {
    private int IdPedido;
    private int IdTransportista;
    private int IdCliente;
    private String NomApellidosContacto;
    private int NumPedido;
    private String DireccionEnvio;
    private String TelefonoContacto;
    private String CiudadEnvio;
    private String ProvinciaEnvio;
    private String PaisEnvio;
    private String CodPostalEnvio;
    private String TipoPago;
    private String FechaPedido;
    private String FechaEntrega;
    private String EstadoEntrega;
    private boolean Entregado;
    private boolean Pagado;
    private String FechaPagado;

    public Pedido() {
    }

    public Pedido(int idPedido, int idTransportista, int idCliente,String nomApellidosContacto, int numPedido, String direccionEnvio, String telefonoContacto, String ciudadEnvio, String provinciaEnvio, String paisEnvio, String codPostalEnvio, String tipoPago, String fechaPedido, String fechaEntrega, String estadoEntrega, boolean entregado, boolean pagado, String fechaPagado) {
        IdPedido = idPedido;
        IdTransportista = idTransportista;
        IdCliente = idCliente;
        NomApellidosContacto = nomApellidosContacto;
        NumPedido = numPedido;
        DireccionEnvio = direccionEnvio;
        TelefonoContacto = telefonoContacto;
        CiudadEnvio = ciudadEnvio;
        ProvinciaEnvio = provinciaEnvio;
        PaisEnvio = paisEnvio;
        CodPostalEnvio = codPostalEnvio;
        TipoPago = tipoPago;
        FechaPedido = fechaPedido;
        FechaEntrega = fechaEntrega;
        EstadoEntrega = estadoEntrega;
        Entregado = entregado;
        Pagado = pagado;
        FechaPagado = fechaPagado;
    }

    public String getNomApellidosContacto() {
        return NomApellidosContacto;
    }

    public void setNomApellidosContacto(String nomApellidosContacto) {
        NomApellidosContacto = nomApellidosContacto;
    }

    public int getIdPedido() {
        return IdPedido;
    }

    public void setIdPedido(int idPedido) {
        IdPedido = idPedido;
    }

    public int getIdTransportista() {
        return IdTransportista;
    }

    public void setIdTransportista(int idTransportista) {
        IdTransportista = idTransportista;
    }

    public int getIdCliente() {
        return IdCliente;
    }

    public void setIdCliente(int idCliente) {
        IdCliente = idCliente;
    }

    public int getNumPedido() {
        return NumPedido;
    }

    public void setNumPedido(int numPedido) {
        NumPedido = numPedido;
    }

    public String getDireccionEnvio() {
        return DireccionEnvio;
    }

    public void setDireccionEnvio(String direccionEnvio) {
        DireccionEnvio = direccionEnvio;
    }

    public String getTelefonoContacto() {
        return TelefonoContacto;
    }

    public void setTelefonoContacto(String telefonoContacto) {
        TelefonoContacto = telefonoContacto;
    }

    public String getCiudadEnvio() {
        return CiudadEnvio;
    }

    public void setCiudadEnvio(String ciudadEnvio) {
        CiudadEnvio = ciudadEnvio;
    }

    public String getProvinciaEnvio() {
        return ProvinciaEnvio;
    }

    public void setProvinciaEnvio(String provinciaEnvio) {
        ProvinciaEnvio = provinciaEnvio;
    }

    public String getPaisEnvio() {
        return PaisEnvio;
    }

    public void setPaisEnvio(String paisEnvio) {
        PaisEnvio = paisEnvio;
    }

    public String getCodPostalEnvio() {
        return CodPostalEnvio;
    }

    public void setCodPostalEnvio(String codPostalEnvio) {
        CodPostalEnvio = codPostalEnvio;
    }

    public String getTipoPago() {
        return TipoPago;
    }

    public void setTipoPago(String tipoPago) {
        TipoPago = tipoPago;
    }

    public String getFechaPedido() {
        return FechaPedido;
    }

    public void setFechaPedido(String fechaPedido) {
        FechaPedido = fechaPedido;
    }

    public String getFechaEntrega() {
        return FechaEntrega;
    }

    public void setFechaEntrega(String fechaEntrega) {
        FechaEntrega = fechaEntrega;
    }

    public String getEstadoEntrega() {
        return EstadoEntrega;
    }

    public void setEstadoEntrega(String estadoEntrega) {
        EstadoEntrega = estadoEntrega;
    }

    public boolean isEntregado() {
        return Entregado;
    }

    public void setEntregado(boolean entregado) {
        Entregado = entregado;
    }

    public boolean isPagado() {
        return Pagado;
    }

    public void setPagado(boolean pagado) {
        Pagado = pagado;
    }

    public String getFechaPagado() {
        return FechaPagado;
    }

    public void setFechaPagado(String fechaPagado) {
        FechaPagado = fechaPagado;
    }
}
