package com.example.infotech.clasespojo;

import androidx.annotation.NonNull;

public class Clientes {
    private int IdCliente;
    private String Nombre;
    private String Apellidos;
    private String Dni;
    private String Telefono;
    private String Email;
    private String Usuario;
    private String Contraseña;
    private String Ciudad;
    private String Provincia;
    private String Pais;
    private String CodPostal;
    private String Direccion1;
    private String DireccionFacturacion;
    private String TipoTarjetaCredito;
    private String NumTarjetaCredito;
    private int MesCadTarjetaCredito;
    private int YearCadTarjetaCredito;
    private String CiudadFacturacion;
    private String ProvinciaFacturacion;
    private String PaisFacturacion;
    private String CodPostalFacturacion;
    private String ImgPerfil;

    public Clientes() {
    }

    public Clientes(int idCliente, String nombre, String apellidos,String dni, String telefono, String email, String usuario, String contraseña, String ciudad, String provincia, String pais, String codPostal, String direccion1, String direccionFacturacion, String tipoTarjetaCredito, String numTarjetaCredito, int mesCadTarjetaCredito, int yearCadTarjetaCredito, String ciudadFacturacion, String provinciaFacturacion, String paisFacturacion, String codPostalFacturacion, String imgPerfil) {
        IdCliente = idCliente;
        Nombre = nombre;
        Apellidos = apellidos;
        Dni = dni;
        Telefono = telefono;
        Email = email;
        Usuario = usuario;
        Contraseña = contraseña;
        Ciudad = ciudad;
        Provincia = provincia;
        Pais = pais;
        CodPostal = codPostal;
        Direccion1 = direccion1;
        DireccionFacturacion = direccionFacturacion;
        TipoTarjetaCredito = tipoTarjetaCredito;
        NumTarjetaCredito = numTarjetaCredito;
        MesCadTarjetaCredito = mesCadTarjetaCredito;
        YearCadTarjetaCredito = yearCadTarjetaCredito;
        CiudadFacturacion = ciudadFacturacion;
        ProvinciaFacturacion = provinciaFacturacion;
        PaisFacturacion = paisFacturacion;
        CodPostalFacturacion = codPostalFacturacion;
        ImgPerfil = imgPerfil;
    }

    public String getDni() {
        return Dni;
    }

    public void setDni(String dni) {
        Dni = dni;
    }

    public int getIdCliente() {
        return IdCliente;
    }

    public void setIdCliente(int idCliente) {
        IdCliente = idCliente;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String apellidos) {
        Apellidos = apellidos;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String usuario) {
        Usuario = usuario;
    }

    public String getContraseña() {
        return Contraseña;
    }

    public void setContraseña(String contraseña) {
        Contraseña = contraseña;
    }

    public String getCiudad() {
        return Ciudad;
    }

    public void setCiudad(String ciudad) {
        Ciudad = ciudad;
    }

    public String getProvincia() {
        return Provincia;
    }

    public void setProvincia(String provincia) {
        Provincia = provincia;
    }

    public String getPais() {
        return Pais;
    }

    public void setPais(String pais) {
        Pais = pais;
    }

    public String getCodPostal() {
        return CodPostal;
    }

    public void setCodPostal(String codPostal) {
        CodPostal = codPostal;
    }

    public String getDireccion1() {
        return Direccion1;
    }

    public void setDireccion1(String direccion1) {
        Direccion1 = direccion1;
    }

    public String getDireccionFacturacion() {
        return DireccionFacturacion;
    }

    public void setDireccionFacturacion(String direccionFacturacion) {
        DireccionFacturacion = direccionFacturacion;
    }

    public String getTipoTarjetaCredito() {
        return TipoTarjetaCredito;
    }

    public void setTipoTarjetaCredito(String tipoTarjetaCredito) {
        TipoTarjetaCredito = tipoTarjetaCredito;
    }

    public String getNumTarjetaCredito() {
        return NumTarjetaCredito;
    }

    public void setNumTarjetaCredito(String numTarjetaCredito) {
        NumTarjetaCredito = numTarjetaCredito;
    }

    public int getMesCadTarjetaCredito() {
        return MesCadTarjetaCredito;
    }

    public void setMesCadTarjetaCredito(int mesCadTarjetaCredito) {
        MesCadTarjetaCredito = mesCadTarjetaCredito;
    }

    public int getYearCadTarjetaCredito() {
        return YearCadTarjetaCredito;
    }

    public void setYearCadTarjetaCredito(int yearCadTarjetaCredito) {
        YearCadTarjetaCredito = yearCadTarjetaCredito;
    }

    public String getCiudadFacturacion() {
        return CiudadFacturacion;
    }

    public void setCiudadFacturacion(String ciudadFacturacion) {
        CiudadFacturacion = ciudadFacturacion;
    }

    public String getProvinciaFacturacion() {
        return ProvinciaFacturacion;
    }

    public void setProvinciaFacturacion(String provinciaFacturacion) {
        ProvinciaFacturacion = provinciaFacturacion;
    }

    public String getPaisFacturacion() {
        return PaisFacturacion;
    }

    public void setPaisFacturacion(String paisFacturacion) {
        PaisFacturacion = paisFacturacion;
    }

    public String getCodPostalFacturacion() {
        return CodPostalFacturacion;
    }

    public void setCodPostalFacturacion(String codPostalFacturacion) {
        CodPostalFacturacion = codPostalFacturacion;
    }

    public String getImgPerfil() {
        return ImgPerfil;
    }

    public void setImgPerfil(String imgPerfil) {
        ImgPerfil = imgPerfil;
    }

    @NonNull
    @Override
    public Clientes clone(){
        return this;
    }
}
