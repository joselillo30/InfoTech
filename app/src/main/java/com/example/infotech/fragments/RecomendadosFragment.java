package com.example.infotech.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.infotech.MainActivity;
import com.example.infotech.R;
import com.example.infotech.adaptadores.AdaptadorCategorias;
import com.example.infotech.adaptadores.AdaptadorProductos;
import com.example.infotech.clasespojo.CarritoDeLaCompra;
import com.example.infotech.clasespojo.Categorias;
import com.example.infotech.clasespojo.Productos;

import java.util.ArrayList;
import java.util.Random;

public class RecomendadosFragment extends Fragment {

    private Button verTodosCategorias, verTodosProductos, verTodosProductosRecomendados;
    private ArrayList<Categorias> categorias;
    private ArrayList<Productos> productos;
    private ArrayList<Productos> productosRandom;
    private RecyclerView recyclerCategorias;
    private AdaptadorCategorias adapterCategorias;
    private AdaptadorProductos adapterPoductos;
    private RecyclerView recyclerProductosRecomendados;
    private RecyclerView recyclerProductosParaTi;

    public RecomendadosFragment(ArrayList<Categorias> categorias, ArrayList<Productos> productos) {
        this.categorias = categorias;
        this.productos = productos;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.recomendadosfragment_layout,container,false);
        recyclerCategorias = v.findViewById(R.id.recyclerCategorias);
        adapterCategorias = new AdaptadorCategorias(categorias);
        recyclerCategorias.setAdapter(adapterCategorias);
        recyclerCategorias.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        recyclerProductosRecomendados = v.findViewById(R.id.recyclerProductosRecomendados);
        verTodosCategorias = v.findViewById(R.id.verTodosCategorias);
        verTodosProductos = v.findViewById(R.id.verTodosProductos);
        verTodosProductosRecomendados = v.findViewById(R.id.verTodosRecomendadosParaTi);
        productosRandom = getRandomElement();
        adapterPoductos = new AdaptadorProductos(productosRandom);
        adapterPoductos.setClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = recyclerProductosRecomendados.getChildAdapterPosition(v);
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.beginTransaction().setCustomAnimations(android.R.animator.fade_in,android.R.animator.fade_out).replace(R.id.contenedor,new VistaProductoFragment(productosRandom.get(position))).commit();
            }
        });
        verTodosCategorias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        verTodosProductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.beginTransaction().setCustomAnimations(android.R.animator.fade_in,android.R.animator.fade_out).replace(R.id.contenedor,new ProductosMenuItemFragment(productos,new String[]{"Productos","Todos"})).commit();
            }
        });
        verTodosProductosRecomendados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Productos> productosRandom = getRandomElement();;

                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.beginTransaction().setCustomAnimations(android.R.animator.fade_in,android.R.animator.fade_out).replace(R.id.contenedor,new ProductosMenuItemFragment(productosRandom,new String[]{"Productos","Recomendados para ti"})).commit();
            }
        });
        recyclerProductosRecomendados.setAdapter(adapterPoductos);
        recyclerProductosRecomendados.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        recyclerProductosParaTi = v.findViewById(R.id.recyclerProductosParaTi);
        recyclerProductosParaTi.setAdapter(adapterPoductos);
        recyclerProductosParaTi.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        return v;
    }

    //Metodo para elegir los productos que seran mostrados en la seccion de recomendados y recomendados para ti
    public ArrayList<Productos> getRandomElement()
    {
        Random rand = new Random();
        ArrayList<Productos> productosCopia = new ArrayList<>();
        productosCopia.addAll(productos);
        ArrayList<Productos> newList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int randomIndex = rand.nextInt(productosCopia.size());
            newList.add(productosCopia.get(randomIndex));
            productosCopia.remove(randomIndex);
        }
        return newList;
    }
}
