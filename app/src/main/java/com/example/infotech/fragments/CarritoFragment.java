package com.example.infotech.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.infotech.MainActivity;
import com.example.infotech.R;
import com.example.infotech.adaptadores.AdaptadorProductosCarrito;
import com.example.infotech.clasespojo.CarritoDeLaCompra;
import com.example.infotech.clasespojo.Productos;

import java.text.DecimalFormat;

public class CarritoFragment extends Fragment {

    Button volverButton, realizarPedidoButton;
    TextView precioTotalTextview;
    private RecyclerView lista;
    float precioTotal;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.carritofragment_layout,container,false);
        lista = v.findViewById(R.id.carritoRecyclerView);
        AdaptadorProductosCarrito adapter = new AdaptadorProductosCarrito(CarritoDeLaCompra.getProductosCarritos());
        lista.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        lista.setLayoutManager(layoutManager);
        lista.addItemDecoration(new DividerItemDecoration(getContext(),
                layoutManager.getOrientation()));
        calculaPrecioTotal();
        realizarPedidoButton = v.findViewById(R.id.realizarPedido);
        precioTotalTextview = v.findViewById(R.id.precioTotalCarrito);
        DecimalFormat format = new DecimalFormat("#.00");
        precioTotalTextview.setText(format.format(precioTotal)+"€");
        realizarPedidoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((MainActivity)getActivity()).cliente!=null){
                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.beginTransaction().setCustomAnimations(android.R.animator.fade_in,android.R.animator.fade_out).replace(R.id.contenedor,new DireccionEnvioCarritoFragment(((MainActivity)getActivity()).cliente)).commit();
                }else{
                    Toast.makeText(getContext(),"Para realizar un pedido debes iniciar sesion",Toast.LENGTH_LONG).show();
                }
            }
        });
        volverButton = v.findViewById(R.id.volverButton);
        volverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).cambiaFragment();
            }
        });
        adapter.setClickBtImagen(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View rootView = (View) v.getParent().getParent();
                int position = lista.getChildAdapterPosition(rootView);
                Productos p = CarritoDeLaCompra.getProductosCarritos().get(position);
                precioTotal -= p.getPrecioUnitario()*p.getCantidad();
                DecimalFormat format = new DecimalFormat("#.00");
                precioTotalTextview.setText(format.format(precioTotal)+"€");
                CarritoDeLaCompra.getProductosCarritos().remove(p);
                ((MainActivity)getActivity()).textoCarrito.setText(Integer.valueOf(((MainActivity)getActivity()).textoCarrito.getText().toString())-1+"");
                adapter.notifyDataSetChanged();
            }
        });
        return v;
    }
    private void calculaPrecioTotal(){
        for (Productos prod :
                CarritoDeLaCompra.getProductosCarritos()) {
            precioTotal+=prod.getPrecioUnitario()*prod.getCantidad();
        }
    }
}
