package com.example.infotech.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.infotech.MainActivity;
import com.example.infotech.R;
import com.example.infotech.clasespojo.Clientes;
import com.example.infotech.clasespojo.Pedido;
import com.example.infotech.clasespojo.Transportistas;
import com.example.infotech.servicios.ProveedorServicios;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DireccionEnvioCarritoFragment extends Fragment {

    Pedido pedido;
    LinearLayout infoCorreos,infoTransportista;
    Clientes usuario;
    TextView botonCambiar, nombreApellidos, direccion, ciudadCodPostal, provinciaPais;
    RadioButton envioDomicilio, envioCorreos;
    Button siguiente;
    Spinner transportistasSpinner;
    ArrayList<Transportistas> transportistas = new ArrayList<>();
    ArrayAdapter<Transportistas> adapter;
    EditText direccionCorreos,localidadCorreos,provinciaCorreos,paisCorreos,codPostalCorreos;

    public DireccionEnvioCarritoFragment(Clientes usuario) {
        this.usuario = usuario;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.direcciondeenvio_carrito_layout,container,false);
        nombreApellidos = v.findViewById(R.id.nombreapellidosCarrito);
        direccion = v.findViewById(R.id.direccionCarrito);
        ciudadCodPostal = v.findViewById(R.id.codpostalCiudadCarrito);
        provinciaPais = v.findViewById(R.id.provinciaPaisCarrito);
        botonCambiar = v.findViewById(R.id.cambiarButton);
        siguiente = v.findViewById(R.id.siguienteCarritoButton);
        envioDomicilio = v.findViewById(R.id.envioADomicilio);
        envioCorreos = v.findViewById(R.id.recogidaEnCorreos);
        infoCorreos = v.findViewById(R.id.informacionCorreosCarrito);
        direccionCorreos = v.findViewById(R.id.direccionCorreos);
        localidadCorreos = v.findViewById(R.id.localidadCorreos);
        provinciaCorreos = v.findViewById(R.id.provinciaCorreos);
        paisCorreos = v.findViewById(R.id.paisCorreos);
        codPostalCorreos = v.findViewById(R.id.codigoPostalCorreos);
        infoTransportista = v.findViewById(R.id.transportista);
        transportistasSpinner = v.findViewById(R.id.transportistasSpinner);
        adapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_item,transportistas);
        transportistasSpinner.setAdapter(adapter);
        getTransportistas();
        pedido = new Pedido();

        envioDomicilio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                infoTransportista.setVisibility(View.VISIBLE);
                infoCorreos.setVisibility(View.GONE);
            }
        });

        envioCorreos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                infoCorreos.setVisibility(View.VISIBLE);
                infoTransportista.setVisibility(View.GONE);
            }
        });

        nombreApellidos.setText(usuario.getNombre()+" "+usuario.getApellidos());
        direccion.setText(usuario.getDireccion1());
        ciudadCodPostal.setText(usuario.getCiudad()+", "+usuario.getCodPostal());
        provinciaPais.setText(usuario.getProvincia()+", "+usuario.getPais());
        botonCambiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.beginTransaction().setCustomAnimations(android.R.animator.fade_in,android.R.animator.fade_out).replace(R.id.contenedor, new CambiarDatosPersonales(usuario)).commit();
            }
        });

        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(envioDomicilio.isChecked()){
                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    pedido.setNomApellidosContacto(usuario.getNombre()+" "+usuario.getApellidos());
                    pedido.setDireccionEnvio(usuario.getDireccion1());
                    pedido.setCiudadEnvio(usuario.getCiudad());
                    pedido.setProvinciaEnvio(usuario.getProvincia());
                    pedido.setPaisEnvio(usuario.getPais());
                    pedido.setCodPostalEnvio(usuario.getCodPostal());
                    pedido.setIdTransportista(((Transportistas)transportistasSpinner.getSelectedItem()).getIdTransportista());
                    pedido.setTelefonoContacto(usuario.getTelefono());
                    fm.beginTransaction().setCustomAnimations(android.R.animator.fade_in,android.R.animator.fade_out).replace(R.id.contenedor, new MetodoPagoFragment(usuario, pedido)).commit();
                }else{
                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    pedido.setNomApellidosContacto(usuario.getNombre()+" "+usuario.getApellidos());
                    pedido.setDireccionEnvio(direccionCorreos.getText().toString());
                    pedido.setCiudadEnvio(localidadCorreos.getText().toString());
                    pedido.setProvinciaEnvio(provinciaCorreos.getText().toString());
                    pedido.setPaisEnvio(paisCorreos.getText().toString());
                    pedido.setCodPostalEnvio(codPostalCorreos.getText().toString());
                    pedido.setTelefonoContacto(usuario.getTelefono());
                    pedido.setIdTransportista(3);
                    fm.beginTransaction().setCustomAnimations(android.R.animator.fade_in,android.R.animator.fade_out).replace(R.id.contenedor, new MetodoPagoFragment(usuario, pedido)).commit();
                }
            }
        });
        return v;
    }

    private void getTransportistas() {
        ProveedorServicios proveedorServicios = ((MainActivity)getActivity()).crearRetrofit();
        Call<List<Transportistas>> call = proveedorServicios.getTransportistas();
        call.enqueue(new Callback<List<Transportistas>>() {
            @Override
            public void onResponse(Call<List<Transportistas>> call, Response<List<Transportistas>> response) {
                List<Transportistas> lista = response.body();
                for (Transportistas t :
                        lista) {
                    transportistas.add(t);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Transportistas>> call, Throwable t) {

            }
        });
    }
}
