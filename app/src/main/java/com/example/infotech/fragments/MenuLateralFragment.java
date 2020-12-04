package com.example.infotech.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.infotech.MainActivity;
import com.example.infotech.R;
import com.example.infotech.clasespojo.Categorias;
import com.example.infotech.clasespojo.ItemMenu;
import com.example.infotech.clasespojo.ListaCategorias;
import com.example.infotech.clasespojo.ListaProductos;
import com.example.infotech.clasespojo.MenuItems;
import com.example.infotech.clasespojo.Productos;

import java.util.ArrayList;

//Clase para asignar al drawer que genera y gestiona el menu de la aplicacion
public class MenuLateralFragment extends Fragment {

    ImageButton menuBackButton;
    ArrayList<ItemMenu> datosVisuales;
    ArrayList<ItemMenu> datosComponentes;
    ArrayList<ItemMenu> datosOrdenadores;
    ArrayList<ItemMenu> datosSmartphones;
    ArrayList<ItemMenu> datosRedes;
    ArrayList<ItemMenu> datosConsolas;
    String[] ruta = new String[2];
    ArrayList<Productos> productosFiltrados = new ArrayList<>();
    ArrayAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.menulateral_layout, container, false);
        init();
        menuBackButton = v.findViewById(R.id.menuBack);
        menuBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
                ((MainActivity)getActivity()).drawerLayout.closeDrawer(GravityCompat.START);
            }
        });
        final ListView lista = v.findViewById(R.id.pruebaListView);
        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, datosVisuales);
        adapter.setNotifyOnChange(true);
        lista.setAdapter(adapter);
        //Metodo que gestiona cuando haces click en algun elemento del menu
        //gracias un enumerados que implementan las clases MenuItem puedo controlar en que elemento he hecho click
        //y con este switch puedo decidir que hacer con cada elemento del menu
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ItemMenu i = (ItemMenu) parent.getItemAtPosition(position);
                switch (i.getId()) {
                    case Componentes:
                        ruta[0] = "Componentes";
                        datosVisuales.clear();
                        datosVisuales.addAll(datosComponentes);
                        adapter.notifyDataSetChanged();
                        break;
                    case PlacasBase:
                        ruta[1] = "Placas base";
                        generaDatos();
                        cambiaFragment();
                        reset();
                        ((MainActivity)getActivity()).drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case Procesadores:
                        ruta[1] = "Procesadores";
                        generaDatos();
                        cambiaFragment();
                        reset();
                        ((MainActivity)getActivity()).drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case DiscosDuros:
                        ruta[1] = "Discos duros";
                        generaDatos();
                        cambiaFragment();
                        reset();
                        ((MainActivity)getActivity()).drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case FuentesDeAlimentacion:
                        ruta[1] = "Fuentes de alimentacion";
                        generaDatos();
                        cambiaFragment();
                        reset();
                        ((MainActivity)getActivity()).drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case TarjetasGraficas:
                        ruta[1] = "Tajetas graficas";
                        generaDatos();
                        cambiaFragment();
                        reset();
                        ((MainActivity)getActivity()).drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case MemoriasRam:
                        ruta[1] = "Memorias ram";
                        generaDatos();
                        cambiaFragment();
                        reset();
                        ((MainActivity)getActivity()).drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case TarjetasDeSonido:
                        ruta[1] = "Tarjetas de sonido";
                        generaDatos();
                        cambiaFragment();
                        reset();
                        ((MainActivity)getActivity()).drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case LectoresDeDiscos:
                        ruta[1] = "Lectores de discos";
                        generaDatos();
                        cambiaFragment();
                        reset();
                        ((MainActivity)getActivity()).drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case Ordenadores:
                        ruta[0] = "Ordenadores";
                        datosVisuales.clear();
                        datosVisuales.addAll(datosOrdenadores);
                        adapter.notifyDataSetChanged();
                        break;
                    case Sobremesa:
                        ruta[1] = "Sobremesa";
                        generaDatos();
                        cambiaFragment();
                        reset();
                        ((MainActivity)getActivity()).drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case Portatiles:
                        ruta[1] = "Portatiles";
                        generaDatos();
                        cambiaFragment();
                        reset();
                        ((MainActivity)getActivity()).drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case AllInOne:
                        ruta[1] = "AllInOne";
                        generaDatos();
                        cambiaFragment();
                        reset();
                        ((MainActivity)getActivity()).drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case Gaming:
                        ruta[1] = "Sobremesa Gaming";
                        generaDatos();
                        cambiaFragment();
                        reset();
                        ((MainActivity)getActivity()).drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case Smartphones:
                        ruta[0] = "Smartphones";
                        ruta[1] = "Smartphones";
                        generaDatos();
                        cambiaFragment();
                        reset();
                        ((MainActivity)getActivity()).drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case Redes:
                        ruta[0] = "Redes";
                        datosVisuales.clear();
                        datosVisuales.addAll(datosRedes);
                        adapter.notifyDataSetChanged();
                        break;
                    case Routers:
                        ruta[1] = "Routers";
                        generaDatos();
                        cambiaFragment();
                        reset();
                        ((MainActivity)getActivity()).drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case TarjetasRed:
                        ruta[1] = "Tarjetas de red";
                        generaDatos();
                        cambiaFragment();
                        reset();
                        ((MainActivity)getActivity()).drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case CableRed:
                        ruta[1] = "Cables de red";
                        generaDatos();
                        cambiaFragment();
                        reset();
                        ((MainActivity)getActivity()).drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case Antenas:
                        ruta[1] = "Antenas wifi";
                        generaDatos();
                        cambiaFragment();
                        reset();
                        ((MainActivity)getActivity()).drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case AdaptadoresUsb:
                        ruta[1] = "Adaptadores USB";
                        generaDatos();
                        cambiaFragment();
                        reset();
                        ((MainActivity)getActivity()).drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case Consolas:
                        ruta[0] = "Consolas";
                        datosVisuales.clear();
                        datosVisuales.addAll(datosConsolas);
                        adapter.notifyDataSetChanged();
                        break;
                    case PlayStation:
                        ruta[1] = "PlayStation";
                        generaDatos();
                        cambiaFragment();
                        reset();
                        ((MainActivity)getActivity()).drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case Xbox:
                        ruta[1] = "Xbox";
                        generaDatos();
                        cambiaFragment();
                        reset();
                        ((MainActivity)getActivity()).drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case Nintendo:
                        ruta[1] = "Nintendo";
                        generaDatos();
                        cambiaFragment();
                        reset();
                        ((MainActivity)getActivity()).drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }
            }
        });
        return v;

    }

    //Metodo que sirve para generar el fragment de los productos del elemento del menu que has pulsado
    private void cambiaFragment() {
        FragmentManager fm = ((MainActivity)getActivity()).getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.contenedor,new ProductosMenuItemFragment(productosFiltrados,ruta));
        ft.addToBackStack("");
        ft.commit();

    }


    //Metodo que sirve para generar los productos segun que elemento has pulsado del menu
    private void generaDatos(){
        productosFiltrados.clear();
        for (Categorias cat :
                ListaCategorias.getListaCategorias()) {
            if(cat.getNomCategoria().equalsIgnoreCase(ruta[1])){
                for (Productos prod :
                        ListaProductos.getListaProductos()) {
                    if(prod.getIdCategoria()==cat.getIdCategoria()){
                        productosFiltrados.add(prod);
                    }
                }
            }
        }

    }

    //Metodo que sirve para generar todos los elementos del menu
    private void init() {
        datosVisuales = new ArrayList<>();
        datosVisuales.add(new ItemMenu(MenuItems.Componentes, "Componentes"));
        datosVisuales.add(new ItemMenu(MenuItems.Ordenadores, "Ordenadores"));
        datosVisuales.add(new ItemMenu(MenuItems.Smartphones, "Smartphones"));
        datosVisuales.add(new ItemMenu(MenuItems.Redes, "Redes"));
        datosComponentes = new ArrayList<>();
        datosComponentes.add(new ItemMenu(MenuItems.Procesadores, "Procesadores"));
        datosComponentes.add(new ItemMenu(MenuItems.PlacasBase, "Placas Base"));
        datosComponentes.add(new ItemMenu(MenuItems.MemoriasRam, "Memorias RAM"));
        datosComponentes.add(new ItemMenu(MenuItems.DiscosDuros, "Discos duros"));
        datosComponentes.add(new ItemMenu(MenuItems.LectoresDeDiscos, "Lectores de discos"));
        datosComponentes.add(new ItemMenu(MenuItems.FuentesDeAlimentacion, "Fuentes de alimentacion"));
        datosComponentes.add(new ItemMenu(MenuItems.TarjetasGraficas, "Tarjetas Graficas"));
        datosComponentes.add(new ItemMenu(MenuItems.TarjetasDeSonido, "Tarjetas de sonido"));
        datosOrdenadores = new ArrayList<>();
        datosOrdenadores.add(new ItemMenu(MenuItems.Sobremesa, "Sobremesa"));
        datosOrdenadores.add(new ItemMenu(MenuItems.Portatiles, "Portatiles"));
        datosOrdenadores.add(new ItemMenu(MenuItems.Gaming, "Gaming"));
        datosOrdenadores.add(new ItemMenu(MenuItems.AllInOne, "All In One"));
        datosRedes = new ArrayList<>();
        datosRedes.add(new ItemMenu(MenuItems.Routers, "Routers"));
        datosRedes.add(new ItemMenu(MenuItems.TarjetasRed, "Tarjetas de red"));
        datosRedes.add(new ItemMenu(MenuItems.CableRed, "Cables de red"));
        datosRedes.add(new ItemMenu(MenuItems.Antenas, "Antenas wifi"));
        datosRedes.add(new ItemMenu(MenuItems.AdaptadoresUsb, "Adaptadores USB"));
        datosConsolas = new ArrayList<>();
        datosConsolas.add(new ItemMenu(MenuItems.PlayStation,"PlayStation"));
        datosConsolas.add(new ItemMenu(MenuItems.Xbox,"Xbox"));
        datosConsolas.add(new ItemMenu(MenuItems.Nintendo,"Nintendo"));
    }

    //Metodo que sirve para volver a poner los elementos del menu como estaban cuando se cierre el menu
    private void reset(){
        datosVisuales.clear();
        datosVisuales.add(new ItemMenu(MenuItems.Componentes, "Componentes"));
        datosVisuales.add(new ItemMenu(MenuItems.Ordenadores, "Ordenadores"));
        datosVisuales.add(new ItemMenu(MenuItems.Smartphones, "Smartphones"));
        datosVisuales.add(new ItemMenu(MenuItems.Redes, "Redes"));
        datosVisuales.add(new ItemMenu(MenuItems.Consolas,"Consolas"));
        adapter.notifyDataSetChanged();
    }
}

