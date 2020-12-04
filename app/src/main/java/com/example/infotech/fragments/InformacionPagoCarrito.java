package com.example.infotech.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.braintreepayments.cardform.view.CardForm;
import com.example.infotech.MainActivity;
import com.example.infotech.R;
import com.example.infotech.clasespojo.Clientes;
import com.example.infotech.clasespojo.Pedido;

public class InformacionPagoCarrito extends Fragment {

    Clientes usuario;
    CardForm tarjeta;
    Button aceptarButton, cancelarButton;
    Pedido pedido;


    public InformacionPagoCarrito(Clientes usuario, Pedido pedido) {
        this.usuario = usuario;
        this.pedido = pedido;
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
        aceptarButton = v.findViewById(R.id.aceptarInfoPagoButton);
        cancelarButton = v.findViewById(R.id.cancelarInfoPagoButton);
        aceptarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario.setNumTarjetaCredito(tarjeta.getCardNumber());
                usuario.setMesCadTarjetaCredito(Integer.parseInt(tarjeta.getExpirationMonth()));
                usuario.setYearCadTarjetaCredito(Integer.parseInt(tarjeta.getExpirationYear()));
                vuelve();
            }
        });
        cancelarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vuelve();
            }
        });
        return v;
    }

    private void vuelve(){
        FragmentManager fm = getActivity().getSupportFragmentManager();
        fm.beginTransaction().setCustomAnimations(android.R.animator.fade_in,android.R.animator.fade_out).replace(R.id.contenedor,new MetodoPagoFragment(usuario,pedido)).commit();
    }
}
