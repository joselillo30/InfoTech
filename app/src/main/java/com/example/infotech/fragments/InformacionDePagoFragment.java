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
import androidx.fragment.app.FragmentTransaction;


import com.braintreepayments.cardform.view.CardForm;
import com.example.infotech.MainActivity;
import com.example.infotech.R;
import com.example.infotech.clasespojo.Clientes;

public class InformacionDePagoFragment extends Fragment {

    Clientes usuario;
    Button saltarButton, siguienteButton;

    public InformacionDePagoFragment(Clientes usuario) {
        this.usuario = usuario;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.informacionpago_fragment_layout,container,false);
        CardForm cardForm = v.findViewById(R.id.cardform);
        cardForm.cardRequired(true)
                .expirationRequired(true)
                .cvvRequired(true)
                .cardholderName(CardForm.FIELD_REQUIRED)
                .setup((MainActivity)getActivity());
        saltarButton = v.findViewById(R.id.saltarPago_button);
        siguienteButton = v.findViewById(R.id.siguientePago_button);
        saltarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = ((MainActivity)getActivity()).getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.contenedor,new DireccionDeFacturacionFragment(usuario));
                ft.addToBackStack("informacion de pago");
                ft.commit();
            }
        });
        siguienteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario.setNumTarjetaCredito(cardForm.getCardNumber());
                usuario.setMesCadTarjetaCredito(Integer.parseInt(cardForm.getExpirationMonth()));
                usuario.setYearCadTarjetaCredito(Integer.parseInt(cardForm.getExpirationYear()));
                FragmentManager fm = ((MainActivity)getActivity()).getSupportFragmentManager();

                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.contenedor,new DireccionDeFacturacionFragment(usuario));
                ft.addToBackStack("informacion de pago");
                ft.commit();
            }
        });
        return v;
    }
}
