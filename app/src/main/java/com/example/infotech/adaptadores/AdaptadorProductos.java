package com.example.infotech.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.infotech.R;
import com.example.infotech.clasespojo.DetallesPedidos;
import com.example.infotech.clasespojo.Productos;
import com.example.infotech.servicios.DownloadImageTask;

import java.util.ArrayList;

public class AdaptadorProductos extends RecyclerView.Adapter implements View.OnClickListener {

    ArrayList<Productos> productos;
    Holder holder;
    View.OnClickListener listener;

    public AdaptadorProductos(ArrayList<Productos> productos) {
        this.productos = productos;
    }

    public ArrayList<Productos> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Productos> productos) {
        this.productos = productos;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardviewproducto_recycler,parent,false);
        holder = new Holder(v);
        v.setOnClickListener(this);
        return holder;
    }



    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((Holder)holder).bind(productos.get(position));
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    @Override
    public void onClick(View v) {
        if(listener!=null)listener.onClick(v);
    }

    public void setClickListener(View.OnClickListener listener){
        if(listener!=null)this.listener=listener;
    }

    class Holder extends RecyclerView.ViewHolder{

        TextView textoPrecio, textoProducto, descuentoProducto;
        ImageView imagenProducto;

        public Holder(@NonNull View itemView) {
            super(itemView);
            textoPrecio = itemView.findViewById(R.id.precioProducto);
            textoProducto = itemView.findViewById(R.id.textoProducto);
            imagenProducto = itemView.findViewById(R.id.imagenProducto);
            descuentoProducto = itemView.findViewById(R.id.descuentoProducto);
        }

        public void bind(Productos productos){
            textoProducto.setText(productos.getNomProducto());
            textoPrecio.setText(productos.getPrecioUnitario()+"€");
            if(productos.getDescuento()!=0){
                descuentoProducto.setText("-"+productos.getDescuento()+"%");
            }
            new DownloadImageTask(imagenProducto).execute(productos.getImgProducto());
        }
    }
}
