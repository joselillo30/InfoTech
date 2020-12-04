package com.example.infotech.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.example.infotech.MainActivity;
import com.example.infotech.R;
import com.example.infotech.adaptadores.DescEspePagerAdapter;
import com.example.infotech.clasespojo.CarritoDeLaCompra;
import com.example.infotech.clasespojo.Productos;
import com.example.infotech.servicios.DownloadImageTask;
import com.google.android.material.tabs.TabLayout;

import java.text.DecimalFormat;

public class VistaProductoFragment extends Fragment {
    TabLayout tabs;
    ViewPager mViewPager;
    CoordinatorLayout botonComprar;
    TextView tituloTextView, precioTextView, cantidadTextView, stockTextView, descuentoTextView, precioDescontadoTextView;
    ImageButton addCantidadButton, removeCantidadButton;
    RatingBar ratingBar;
    ImageView imagen;
    private Productos productos;

    public VistaProductoFragment(Productos productos) {
        this.productos = productos;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.vista_producto,container,false);
        mViewPager = v.findViewById(R.id.descEspeViewPager);
        descuentoTextView = v.findViewById(R.id.descuentoVistaProducto);
        precioDescontadoTextView = v.findViewById(R.id.precioDescontadoVistaProducto);
        tabs = v.findViewById(R.id.descespeTabLayout);
        tabs.addTab(tabs.newTab().setText("DESCRIPCION"));
        tabs.addTab(tabs.newTab().setText("ESPECIFICACIONES"));
        DescEspePagerAdapter pagerAdapter = new DescEspePagerAdapter(getActivity().getSupportFragmentManager(),productos.getDescProducto(),productos.getEspecificaciones(),tabs.getTabCount());
        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        mViewPager.setAdapter(pagerAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));
        tituloTextView = v.findViewById(R.id.tituloVistaProducto);
        precioTextView = v.findViewById(R.id.precioVistaProducto);
        ratingBar = v.findViewById(R.id.rating_producto);
        imagen = v.findViewById(R.id.imagenVistaProducto);
        botonComprar = v.findViewById(R.id.botonComprar);
        cantidadTextView = v.findViewById(R.id.cantidadTextView);
        addCantidadButton = v.findViewById(R.id.addCantidadButton);
        removeCantidadButton = v.findViewById(R.id.removeCantidadButton);
        stockTextView = v.findViewById(R.id.stockTextView);
        productos.setStock(10);
        addCantidadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.valueOf(cantidadTextView.getText().toString())<productos.getStock()){
                    cantidadTextView.setText(String.valueOf(Integer.valueOf(cantidadTextView.getText().toString())+1));
                }else{
                    Toast.makeText(getContext(),"La cantidad no puede superar el stock",Toast.LENGTH_LONG).show();
                }
            }
        });
        removeCantidadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.valueOf(cantidadTextView.getText().toString())>1){
                    cantidadTextView.setText(String.valueOf(Integer.valueOf(cantidadTextView.getText().toString())-1));
                }
            }
        });
        botonComprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(productos.getStock()>0){
                    if(CarritoDeLaCompra.compruebaxisteProducto(productos)){
                        for (Productos prod :
                                CarritoDeLaCompra.getProductosCarritos()) {
                           if(prod.equals(productos)){
                                prod.setCantidad(prod.getCantidad()+Integer.parseInt(cantidadTextView.getText().toString()));
                           }
                        }
                    }else{
                        productos.setCantidad(Integer.parseInt(cantidadTextView.getText().toString()));
                        CarritoDeLaCompra.addProducto(productos);
                        ((MainActivity)getActivity()).textoCarrito.setText(Integer.parseInt(((MainActivity)getActivity()).textoCarrito.getText().toString())+1+"");
                    }
                    FragmentManager fm =getActivity().getSupportFragmentManager();
                    fm.beginTransaction().setCustomAnimations(android.R.animator.fade_in,android.R.animator.fade_out).replace(R.id.contenedor,new CarritoFragment()).commit();
                }else{
                    Toast.makeText(getContext(),"No quedan mas productos de este tipo",Toast.LENGTH_LONG).show();
                }
            }
        });
        stockTextView.setText("Stock: "+productos.getStock());
        ratingBar.setRating(productos.getRanking());
        tituloTextView.setText(productos.getNomProducto());
        DecimalFormat format = new DecimalFormat("#.00");
        precioTextView.setText(format.format(productos.getPrecioUnitario())+"€");
        if(productos.getDescuento()!=0){
            descuentoTextView.setVisibility(View.VISIBLE);
            precioDescontadoTextView.setVisibility(View.VISIBLE);
            descuentoTextView.setText(productos.getDescuento()+"%");
            float descuento = (productos.getPrecioUnitario()*Integer.valueOf(productos.getDescuento()))/100;

            productos.setPrecioUnitario(productos.getPrecioUnitario()-descuento);
            precioDescontadoTextView.setText(format.format(productos.getPrecioUnitario())+"€");
        }
        new DownloadImageTask(imagen).execute(productos.getImgProducto());
        return v;
    }
}
