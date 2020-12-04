package com.example.infotech.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.infotech.R;
import com.example.infotech.clasespojo.Clientes;
import com.example.infotech.clasespojo.Pedido;
import com.example.infotech.clasespojo.Transportistas;

public class MetodoPagoFragment extends Fragment {

    Pedido pedido;
    TextView infoTarjetaTextView;
    LinearLayout efectivoLayout, tarjetaLayout;
    RadioButton efectivoButton, tarjetaButton;
    Button siguienteButton;
    Clientes usuario;
    TextView cambiarButton;

    public MetodoPagoFragment(Clientes usuario,Pedido pedido) {
        this.usuario = usuario;
        this.pedido = pedido;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.metodopagocarrito_fragmentlayout, container, false);
        efectivoButton = v.findViewById(R.id.pagoEnEfectivo);
        tarjetaButton = v.findViewById(R.id.pagoConTarjeta);
        efectivoLayout = v.findViewById(R.id.layoutPagoEfectivo);
        tarjetaLayout = v.findViewById(R.id.layoutPagoTarjeta);
        cambiarButton = v.findViewById(R.id.cambiarTarjetaButton);
        infoTarjetaTextView = v.findViewById(R.id.infoTarjetaTextView);
        siguienteButton = v.findViewById(R.id.siguienteResumenPedidoButton);

        infoTarjetaTextView.setText("Tarjeta de credito acabada en \n ••••"+usuario.getNumTarjetaCredito().substring(12));
        efectivoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                efectivoLayout.setVisibility(View.VISIBLE);
                tarjetaLayout.setVisibility(View.GONE);

            }

        });

        tarjetaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                efectivoLayout.setVisibility(View.GONE);
                tarjetaLayout.setVisibility(View.VISIBLE);
            }
        });

        cambiarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.beginTransaction().setCustomAnimations(android.R.animator.fade_in,android.R.animator.fade_out).replace(R.id.contenedor,new InformacionPagoCarrito(usuario, pedido)).commit();
            }
        });

        siguienteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                if(efectivoButton.isChecked()){
                    pedido.setTipoPago("EFECTIVO");
                }else{
                    pedido.setTipoPago("TARJETA");
                }
                fm.beginTransaction().setCustomAnimations(android.R.animator.fade_in,android.R.animator.fade_out).replace(R.id.contenedor,new ResumenPedidoFragment(usuario,pedido)).commit();
            }
        });

        return v;
    }
}
