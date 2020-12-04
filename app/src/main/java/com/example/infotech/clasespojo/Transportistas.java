package com.example.infotech.clasespojo;

import androidx.annotation.NonNull;

public class Transportistas {

    private int IdTransportista;
    private String NomEmpresa;
    private String Telefono;

    public Transportistas(int idTransportista, String nomEmpresa, String telefono) {
        IdTransportista = idTransportista;
        NomEmpresa = nomEmpresa;
        Telefono = telefono;
    }

    public int getIdTransportista() {
        return IdTransportista;
    }

    public void setIdTransportista(int idTransportista) {
        IdTransportista = idTransportista;
    }

    public String getNomEmpresa() {
        return NomEmpresa;
    }

    public void setNomEmpresa(String nomEmpresa) {
        NomEmpresa = nomEmpresa;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    @NonNull
    @Override
    public String toString() {
        return getNomEmpresa();
    }
}
