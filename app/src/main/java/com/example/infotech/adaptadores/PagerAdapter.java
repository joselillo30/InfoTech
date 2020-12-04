package com.example.infotech.adaptadores;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.infotech.clasespojo.Categorias;
import com.example.infotech.clasespojo.Productos;
import com.example.infotech.fragments.OfertasFragment;
import com.example.infotech.fragments.RecomendadosFragment;

import java.util.ArrayList;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int numOfTabs;
    private ArrayList<Categorias> categorias;
    private ArrayList<Productos> productos;

    public PagerAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    public void setCategorias(ArrayList<Categorias> categorias) {
        this.categorias = categorias;
    }


    public void setProductos(ArrayList<Productos> productos) {
        this.productos = productos;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                RecomendadosFragment recomendadosFragment = new RecomendadosFragment(categorias,productos );
                return recomendadosFragment;
            case 1:
                OfertasFragment ofertasFragment = new OfertasFragment(productos);
                return ofertasFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
