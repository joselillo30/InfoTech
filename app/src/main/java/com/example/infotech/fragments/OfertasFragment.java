package com.example.infotech.fragments;

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
import androidx.recyclerview.widget.RecyclerView;

import com.example.infotech.MainActivity;
import com.example.infotech.R;
import com.example.infotech.adaptadores.AdaptadorProductos;
import com.example.infotech.clasespojo.Productos;

import java.util.ArrayList;
import java.util.Collections;

public class OfertasFragment extends Fragment {

    ArrayList<Productos> productos = new ArrayList<>();
    ArrayList<Productos> productosFiltrados = new ArrayList<>();
    RecyclerView recyclerView;
    Spinner spinner;
    Button filtrarButton, aplicarFiltrosButton;
    EditText precioDesde, precioHasta;
    LinearLayout filtrosLayout;
    String[]orden;

    public OfertasFragment(ArrayList<Productos> productos) {
        this.productos.addAll(productos);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.ofertasfragment_layout,container,false);orden = getActivity().getResources().getStringArray(R.array.orden);
        recyclerView = v.findViewById(R.id.productosOferta_recycler);
        filtrosLayout = v.findViewById(R.id.filtroslinearlayout);
        aplicarFiltrosButton = v.findViewById(R.id.aplicarFiltros);
        precioDesde = v.findViewById(R.id.precioDesde);
        precioHasta = v.findViewById(R.id.precioHasta);
        Collections.sort(productos, (o1, o2) -> {
            int compare = o1.getNomProducto().compareTo(o2.getNomProducto());
            if(compare<0)
                return -1;
            else
                return 1;
        });
        quitaNoOfertas();
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

    //Metodo para quitar los productos que no tienen oferta
    private void quitaNoOfertas(){
        for (int i = productos.size()-1;i>=0;i--){
           if(productos.get(i).getDescuento()==0){
               productos.remove(productos.get(i));
           }
        }
    }
}
