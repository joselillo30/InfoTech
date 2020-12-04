package com.example.infotech.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.braintreepayments.cardform.view.CardForm;
import com.example.infotech.MainActivity;
import com.example.infotech.R;
import com.example.infotech.clasespojo.Clientes;
import com.example.infotech.clasespojo.Pedido;
import com.example.infotech.servicios.ProveedorServicios;
import com.example.infotech.servicios.RespuestaJson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CambiarInformacionPagoFragment extends Fragment {

    Clientes usuario;
    CardForm tarjeta;
    Button aceptarButton, cancelarButton;
    Pedido pedido;


    public CambiarInformacionPagoFragment(Clientes usuario) {
        this.usuario = usuario;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.informacionpago_carrito_layout, container, false);
        tarjeta = v.findViewById(R.id.cardform);
        tarjeta.cardRequired(true)
                .expirationRequired(true)
                .cvvRequired(true)
                .cardholderName(CardForm.FIELD_REQUIRED)
                .setup((MainActivity)getActivity());
        tarjeta.getCardholderNameEditText().setText(usuario.getNombre()+" "+usuario.getApellidos());
        tarjeta.getExpirationDateEditText().setText(usuario.getMesCadTarjetaCredito()+usuario.getYearCadTarjetaCredito()+"");
        tarjeta.getCvvEditText().setText("654");
        tarjeta.getCardEditText().setText(usuario.getNumTarjetaCredito());
        aceptarButton = v.findViewById(R.id.aceptarInfoPagoButton);
        cancelarButton = v.findViewById(R.id.cancelarInfoPagoButton);
        aceptarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario.setNumTarjetaCredito(tarjeta.getCardNumber());
                usuario.setMesCadTarjetaCredito(Integer.parseInt(tarjeta.getExpirationMonth()));
                usuario.setYearCadTarjetaCredito(Integer.parseInt(tarjeta.getExpirationYear()));
                putUsuario();
                ((MainActivity)getActivity()).cambiaFragment();
            }
        });
        cancelarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).cambiaFragment();
            }
        });
        return v;
    }

    private void putUsuario() {
        ProveedorServicios proveedorServicios = ((MainActivity)getActivity()).crearRetrofit();
        Call<RespuestaJson> call = proveedorServicios.putClientes(usuario.getIdCliente(),usuario);
        call.enqueue(new Callback<RespuestaJson>() {
            @Override
            public void onResponse(Call<RespuestaJson> call, Response<RespuestaJson> response) {
                Toast.makeText(getContext(),"Informacion de pago cambiada con exito",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<RespuestaJson> call, Throwable t) {
                Toast.makeText(getContext(),"No se ha podido cambiar la informacion de pago. Intentalo mas tarde",Toast.LENGTH_LONG).show();
            }
        });
    }
}
