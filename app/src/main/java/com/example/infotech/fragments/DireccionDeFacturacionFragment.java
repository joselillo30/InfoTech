package com.example.infotech.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.infotech.MainActivity;
import com.example.infotech.R;
import com.example.infotech.clasespojo.Clientes;
import com.example.infotech.servicios.ProveedorServicios;
import com.example.infotech.servicios.RespuestaJson;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DireccionDeFacturacionFragment extends Fragment {

    Clientes usuario;
    TextInputEditText calleEditText, portalEditText, escaleraEditText, pisoEditText, puertaEditText, paisEditText, provinciaEditText, ciudadEditText, codPostalEditText;
    Button siguienteButton;
    View v;

    public DireccionDeFacturacionFragment(Clientes usuario) {
        this.usuario = usuario;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v  = inflater.inflate(R.layout.crearcuenta_direcciondefacturacion_layout,container,false);
        calleEditText = v.findViewById(R.id.callefacturacion_editText);
        portalEditText = v.findViewById(R.id.portalfacturacion_editText);
        escaleraEditText = v.findViewById(R.id.escalerafacturacion_editText);
        pisoEditText = v.findViewById(R.id.pisofacturacion_editText);
        puertaEditText = v.findViewById(R.id.puertafacturacion_editText);
        paisEditText = v.findViewById(R.id.paisfacturacion_editText);
        provinciaEditText = v.findViewById(R.id.provinciafacturacion_editText);
        ciudadEditText = v.findViewById(R.id.ciudadfacturacion_editText);
        codPostalEditText = v.findViewById(R.id.codPostalfacturacion_editText);
        siguienteButton = v.findViewById(R.id.siguientefacturacion_button);
        siguienteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v1) {
                if(calleEditText.getText().toString().equals("")||portalEditText.getText().toString().equals("")||paisEditText.getText().toString().equals("")||codPostalEditText.getText().toString().equals("")){
                    postUsuario(usuario);
                    ((MainActivity)getActivity()).cambiaFragment();
                }else{
                    String direccion = calleEditText.getText().toString()+", "+portalEditText.getText().toString()+", "+
                            (escaleraEditText.getText().toString().equals("")?", ":escaleraEditText.getText().toString()+", ")
                            +(pisoEditText.getText().toString().equals("")?", ":pisoEditText.getText().toString()+", ")
                            +(puertaEditText.getText().toString().equals("")?" ":puertaEditText.getText().toString());
                    usuario.setDireccionFacturacion(direccion);
                    usuario.setPaisFacturacion(paisEditText.getText().toString());
                    usuario.setCiudadFacturacion(ciudadEditText.getText().toString());
                    usuario.setProvinciaFacturacion(provinciaEditText.getText().toString());
                    usuario.setCodPostalFacturacion(codPostalEditText.getText().toString());
                    postUsuario(usuario);
                    ((MainActivity)getActivity()).cambiaFragment();
                }
            }
        });
        return v;
    }

    public void postUsuario(Clientes usuario){
        ProveedorServicios proveedorServicios = ((MainActivity)getActivity()).crearRetrofit();
        Call<RespuestaJson> call = proveedorServicios.postClientes(usuario);
        call.enqueue(new Callback<RespuestaJson>() {
            @Override
            public void onResponse(Call<RespuestaJson> call, Response<RespuestaJson> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getContext(),"Usuario creado correctamente",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<RespuestaJson> call, Throwable t) {
                Toast.makeText(getContext(),"No se ha podido crear el usuario",Toast.LENGTH_LONG).show();
            }
        });
    }
}
