package com.example.infotech.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.infotech.MainActivity;
import com.example.infotech.R;
import com.example.infotech.adaptadores.AdaptadorProductos;
import com.example.infotech.clasespojo.CarritoDeLaCompra;
import com.example.infotech.clasespojo.Productos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ProductosMenuItemFragment extends Fragment {
    ArrayList<Productos> productos;
    ArrayList<Productos> productosFiltrados = new ArrayList<>();
    RecyclerView recyclerView;
    Spinner spinner;
    String[]ruta;
    TextView tituloCategoria, tituloSubcategoria, cantidadProductos, tituloProductos;
    Button filtrarButton, aplicarFiltrosButton;
    EditText precioDesde, precioHasta;
    LinearLayout filtrosLayout;
    String[]orden;

    public ProductosMenuItemFragment(ArrayList<Productos> productos, String[] ruta) {
        this.productos = productos;
        this.ruta = ruta;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.productosmenu_layout,container,false);
        orden = getActivity().getResources().getStringArray(R.array.orden);
        recyclerView = v.findViewById(R.id.productos_menu_recycler);
        tituloCategoria = v.findViewById(R.id.titulocategoria);
        tituloSubcategoria = v.findViewById(R.id.titulosubcategoria);
        cantidadProductos = v.findViewById(R.id.cantidadproductos);
        cantidadProductos.setText(String.valueOf(productos.size()));
        tituloProductos = v.findViewById(R.id.tituloProductos);
        filtrosLayout = v.findViewById(R.id.filtroslinearlayout);
        aplicarFiltrosButton = v.findViewById(R.id.aplicarFiltros);
        precioDesde = v.findViewById(R.id.precioDesde);
        precioHasta = v.findViewById(R.id.precioHasta);
        tituloProductos.setText(ruta[1]);
        tituloCategoria.setText(ruta[0]);
        tituloSubcategoria.setText(ruta[1]);
        Collections.sort(productos, (o1, o2) -> {
            int compare = o1.getNomProducto().compareTo(o2.getNomProducto());
            if(compare<0)
                return -1;
            else
                return 1;
        });
        AdaptadorProductos adapter = new AdaptadorProductos(productos);
        filtrarButton = v.findViewById(R.id.filtrar_button);
        filtrarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filtrosLayout.setVisibility(View.VISIBLE);
            }
        });
        adapter.setClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = recyclerView.getChildAdapterPosition(v);
                VistaProductoFragment vistaProductoFragment = new VistaProductoFragment(productos.get(position));
                FragmentManager fm = ((MainActivity)getActivity()).getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.contenedor,vistaProductoFragment).commit();
            }
        });

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        spinner = v.findViewById(R.id.orden_spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        Collections.sort(productos, (o1, o2) -> {
                            int compare = o1.getNomProducto().compareTo(o2.getNomProducto());
                            if(compare<0)
                                return -1;
                            else
                                return 1;
                        });
                        adapter.notifyDataSetChanged();
                        break;
                    case 1:
                        Collections.sort(productos, (o1, o2) -> {
                            int compare = o1.getNomProducto().compareTo(o2.getNomProducto());
                            if(compare>0)
                                return -1;
                            else
                                return 1;
                        });
                        adapter.notifyDataSetChanged();
                        break;
                    case 2:
                        Collections.sort(productos, (o1, o2) -> {

                            if(o1.getPrecioUnitario()<o2.getPrecioUnitario())
                                return 1;
                            else
                                return -1;
                        });
                        adapter.notifyDataSetChanged();
                        break;
                    case 3:
                        Collections.sort(productos, (o1, o2) -> {
                            if(o1.getPrecioUnitario()>o2.getPrecioUnitario())
                                return 1;
                            else
                                return -1;
                        });
                        adapter.notifyDataSetChanged();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        aplicarFiltrosButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(precioDesde.getText().toString().equalsIgnoreCase("")&&precioHasta.getText().toString().equalsIgnoreCase("")){
                    adapter.setProductos(productos);
                    adapter.notifyDataSetChanged();
                }else{
                    productosFiltrados.clear();
                    for (Productos p:
                            productos) {
                        if(p.getPrecioUnitario()>Float.valueOf(precioDesde.getText().toString())&&p.getPrecioUnitario()<Float.valueOf(precioHasta.getText().toString())){
                            productosFiltrados.add(p);
                        }
                    }
                    adapter.setProductos(productosFiltrados);
                    adapter.notifyDataSetChanged();
                }
                filtrosLayout.setVisibility(View.GONE);
            }
        });
        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,orden);
        spinner.setAdapter(arrayAdapter);
        return v;
    }
}
