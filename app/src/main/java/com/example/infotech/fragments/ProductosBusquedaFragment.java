package com.example.infotech.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.infotech.MainActivity;
import com.example.infotech.R;
import com.example.infotech.adaptadores.AdaptadorProductos;
import com.example.infotech.clasespojo.Productos;

import java.util.ArrayList;

public class ProductosBusquedaFragment extends Fragment {

    AdaptadorProductos productosAdapter;
    RecyclerView recyclerViewProductos;
    ArrayList<Productos> productos;

    public ProductosBusquedaFragment(ArrayList<Productos> productos) {
        this.productos = productos;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.productosbusquedafragment_layout,container,false);
        productosAdapter = new AdaptadorProductos(productos);
        recyclerViewProductos = v.findViewById(R.id.recyclerBusqueda);
        recyclerViewProductos.setAdapter(productosAdapter);
        productosAdapter.setClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = recyclerViewProductos.getChildAdapterPosition(v);
                VistaProductoFragment vistaProductoFragment = new VistaProductoFragment(productos.get(position));
                FragmentManager fm = ((MainActivity)getActivity()).getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.contenedor,vistaProductoFragment).commit();
            }
        });
        recyclerViewProductos.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        return v;
    }

}
