package com.example.infotech.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.infotech.R;
import com.example.infotech.clasespojo.Clientes;

public class CambiarDatosPersonales extends Fragment {

    Clientes usuario;
    EditText nombreEditText, apellidosEditText, direccionEditText, ciudadEditText, provinciaEditText, paisEditText, codPostalEditText, telefonoEditText;
    Button aceptar;

    public CambiarDatosPersonales(Clientes usuario) {
        this.usuario = usuario;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.cambiardatospersonales_layout,container,false);
        nombreEditText = v.findViewById(R.id.nombreEditText);
        apellidosEditText = v.findViewById(R.id.apellidosEditText);
        direccionEditText = v.findViewById(R.id.direccionEditText);
        ciudadEditText = v.findViewById(R.id.ciudadEditText);
        provinciaEditText = v.findViewById(R.id.provinciaEditText);
        paisEditText = v.findViewById(R.id.paisEditText);
        codPostalEditText = v.findViewById(R.id.codigoPostalEditText);
        telefonoEditText = v.findViewById(R.id.telefonoEditText);
        aceptar = v.findViewById(R.id.aceptarButton);
        nombreEditText.setText(usuario.getNombre());
        apellidosEditText.setText(usuario.getApellidos());
        direccionEditText.setText(usuario.getDireccion1());
        ciudadEditText.setText(usuario.getCiudad());
        provinciaEditText.setText(usuario.getProvincia());
        paisEditText.setText(usuario.getPais());
        codPostalEditText.setText(usuario.getCodPostal());
        telefonoEditText.setText(usuario.getTelefono());
        aceptar.setOnClickListener(v1->{
           usuario.setNombre(nombreEditText.getText().toString());
           usuario.setApellidos(apellidosEditText.getText().toString());
           usuario.setDireccion1(direccionEditText.getText().toString());
           usuario.setCiudad(ciudadEditText.getText().toString());
           usuario.setProvincia(provinciaEditText.getText().toString());
           usuario.setPais(paisEditText.getText().toString());
           usuario.setCodPostal(codPostalEditText.getText().toString());
           usuario.setTelefono(telefonoEditText.getText().toString());
            FragmentManager fm = getActivity().getSupportFragmentManager();
            fm.beginTransaction().setCustomAnimations(android.R.animator.fade_in,android.R.animator.fade_out).replace(R.id.contenedor, new DireccionEnvioCarritoFragment(usuario)).commit();
        });
        return v;
    }
}
