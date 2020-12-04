package com.example.infotech.adaptadores;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.infotech.fragments.DescripcionFragment;
import com.example.infotech.fragments.EspecificacionesFragment;

public class DescEspePagerAdapter extends FragmentStatePagerAdapter {

    String descripcion, especificaciones;
    int numOfTabs;

    public DescEspePagerAdapter(FragmentManager fm, String descripcion, String especificaciones, int numOfTabs) {
        super(fm);
        this.descripcion = descripcion;
        this.especificaciones = especificaciones;
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                DescripcionFragment descripcionFragment = new DescripcionFragment(descripcion);
                return  descripcionFragment;
            case 1:
                EspecificacionesFragment especificacionesFragment = new EspecificacionesFragment(especificaciones);
                return  especificacionesFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
