package com.example.infotech.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.infotech.R;
import com.example.infotech.adaptadores.AdaptadorCategorias;
import com.example.infotech.clasespojo.Categorias;

import java.util.ArrayList;

public class VerTodosCategoriasFragment extends Fragment {

    ArrayList<Categorias> categorias;
    RecyclerView recyclerView;
    AdaptadorCategorias adapter;

    public VerTodosCategoriasFragment(ArrayList<Categorias> categorias) {
        this.categorias = categorias;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.vertodoscategorias_layout,container,false);
        recyclerView = v.findViewById(R.id.todasCategoriasRecycler);
        adapter = new AdaptadorCategorias(categorias);
        recyclerView.setAdapter(adapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),3);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter.setClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return v;
    }
}
