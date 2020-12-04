package com.example.infotech.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.infotech.MainActivity;
import com.example.infotech.R;
import com.example.infotech.clasespojo.Clientes;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class CrearCuentaDireccionFragment extends Fragment {

    Clientes usuario;
    Spinner spinner;
    String[]arrayPrefijos;
    TextInputEditText calleEditText, portalEditText, escaleraEditText, pisoEditText, puertaEditText, paisEditText, provinciaEditText, ciudadEditText, codPostalEditText, telefonoEditText;
    Button siguienteButton;

    public CrearCuentaDireccionFragment(Clientes usuario) {
        this.usuario = usuario;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.crearcuenta_direccionfragment_layout,container,false);
        calleEditText = v.findViewById(R.id.calle_editText);
        portalEditText = v.findViewById(R.id.portal_editText);
        escaleraEditText = v.findViewById(R.id.escalera_editText);
        pisoEditText = v.findViewById(R.id.piso_editText);
        puertaEditText = v.findViewById(R.id.puerta_editText);
        paisEditText = v.findViewById(R.id.pais_editText);
        provinciaEditText = v.findViewById(R.id.provincia_editText);
        ciudadEditText = v.findViewById(R.id.ciudad_editText);
        codPostalEditText = v.findViewById(R.id.codPostal_editText);
        telefonoEditText = v.findViewById(R.id.numeroTelefono_editText);
        spinner = v.findViewById(R.id.prefijos_spinner);
        siguienteButton = v.findViewById(R.id.siguienteDireccion_button);
        siguienteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v1) {
                if(calleEditText.getText().toString().equals("")||portalEditText.getText().toString().equals("")||paisEditText.getText().toString().equals("")||codPostalEditText.getText().toString().equals("")){
                    Snackbar.make(v,"Los campos calle, portal, pais y codigo postal no pueden estar vacios", Snackbar.LENGTH_LONG).show();
                }else{
                    String direccion = calleEditText.getText().toString()+", "+portalEditText.getText().toString()+", "+
                            (escaleraEditText.getText().toString().equals("")?", ":escaleraEditText.getText().toString()+", ")
                            +(pisoEditText.getText().toString().equals("")?", ":pisoEditText.getText().toString()+", ")
                            +(puertaEditText.getText().toString().equals("")?" ":puertaEditText.getText().toString());
                    usuario.setDireccion1(direccion);
                    usuario.setPais(paisEditText.getText().toString());
                    usuario.setCiudad(ciudadEditText.getText().toString());
                    usuario.setProvincia(provinciaEditText.getText().toString());
                    usuario.setCodPostal(codPostalEditText.getText().toString());
                    usuario.setTelefono(spinner.getSelectedItem().toString()+" "+telefonoEditText.getText().toString());
                    FragmentManager fm = ((MainActivity)getActivity()).getSupportFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.contenedor,new InformacionDePagoFragment(usuario));
                    ft.addToBackStack("informacion de pago");
                    ft.commit();
                }
            }
        });
        arrayPrefijos = getResources().getStringArray(R.array.prefijos);
        ArrayAdapter<String> array = new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_item,arrayPrefijos);
        spinner.setAdapter(array);
        return v;
    }

}