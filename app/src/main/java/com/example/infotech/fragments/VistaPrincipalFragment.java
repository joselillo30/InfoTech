package com.example.infotech.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.infotech.MainActivity;
import com.example.infotech.adaptadores.PagerAdapter;
import com.example.infotech.R;
import com.example.infotech.clasespojo.Categorias;
import com.example.infotech.clasespojo.ListaCategorias;
import com.example.infotech.clasespojo.ListaProductos;
import com.example.infotech.clasespojo.Productos;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class VistaPrincipalFragment extends Fragment {


    TabLayout tabs;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.vistaprincipalfragment,container,false);


        tabs = v.findViewById(R.id.tabLayout);
        tabs.addTab(tabs.newTab().setText("RECOMENDADOS"));
        tabs.addTab(tabs.newTab().setText("OFERTAS"));
        ViewPager viewPager = v.findViewById(R.id.viewPager);
        PagerAdapter adapter = new PagerAdapter(((MainActivity)getContext()).getSupportFragmentManager(),tabs.getTabCount());
        adapter.setProductos(ListaProductos.getListaProductos());
        adapter.setCategorias(ListaCategorias.getListaCategorias());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));
        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return v;
    }


}
