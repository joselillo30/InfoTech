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

public class AdaptadorProductosResumen extends RecyclerView.Adapter {
    ArrayList<Productos> productos;
    Holder holder;

    public AdaptadorProductosResumen(ArrayList<Productos> productos) {
        this.productos = productos;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.productosresumenrecycler,parent,false);
        holder = new Holder(v);
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

    class Holder extends RecyclerView.ViewHolder{

        TextView textoPrecio, textoProducto, textoCantidad;
        ImageView imagenProducto;

        public Holder(@NonNull View itemView) {
            super(itemView);
            textoPrecio = itemView.findViewById(R.id.precioProductoCarrito);
            textoProducto = itemView.findViewById(R.id.tituloProductoCarrito);
            imagenProducto = itemView.findViewById(R.id.imagenProductoCarrito);
            textoCantidad = itemView.findViewById(R.id.cantidadTextView);
        }

        public void bind(Productos productos){
            textoProducto.setText(productos.getNomProducto());
            textoPrecio.setText(productos.getPrecioUnitario()+"€");
            new DownloadImageTask(imagenProducto).execute(productos.getImgProducto());
            textoCantidad.setText("Cantidad: "+productos.getCantidad());
        }
    }
}
