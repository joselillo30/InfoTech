package com.example.infotech.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.infotech.MainActivity;
import com.example.infotech.R;
import com.example.infotech.adaptadores.AdaptadorPedidos;
import com.example.infotech.clasespojo.Clientes;
import com.example.infotech.clasespojo.Pedido;
import com.example.infotech.servicios.ProveedorServicios;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PedidosCuentaFragment extends Fragment {

    Clientes usuarios;
    RecyclerView recyclerView;
    ArrayList<Pedido> pedidos = new ArrayList<>();
    AdaptadorPedidos adaptadorPedidos;

    public PedidosCuentaFragment(Clientes usuarios) {
        this.usuarios = usuarios;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.pedidos_fragment_layout,container,false);
        recyclerView = v.findViewById(R.id.pedidos_recycler);
        adaptadorPedidos = new AdaptadorPedidos(pedidos);
        recyclerView.setAdapter(adaptadorPedidos);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        adaptadorPedidos.setClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = recyclerView.getChildLayoutPosition(v);
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.beginTransaction().setCustomAnimations(android.R.animator.fade_in,android.R.animator.fade_out).replace(R.id.contenedor,new DetallesDePedidoFragment(pedidos.get(position))).commit();
            }
        });
        getPedidos();

        return v;
    }

    private void getPedidos() {
        ProveedorServicios proveedorServicios = ((MainActivity)getActivity()).crearRetrofit();
        Call<List<Pedido>> call = proveedorServicios.getPedidos();
        call.enqueue(new Callback<List<Pedido>>() {
            @Override
            public void onResponse(Call<List<Pedido>> call, Response<List<Pedido>> response) {
                for (Pedido p :
                        response.body()) {
                    if(p.getIdCliente()==usuarios.getIdCliente())
                        pedidos.add(p);
                }
                adaptadorPedidos.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Pedido>> call, Throwable t) {
                Toast.makeText(getContext(),"No se han podido obtener los pedidos. Intentalo mas tarde",Toast.LENGTH_LONG).show();
            }
        });
    }
}
