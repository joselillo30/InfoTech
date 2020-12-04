package com.example.infotech.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.infotech.R;
import com.example.infotech.clasespojo.Productos;
import com.example.infotech.servicios.DownloadImageTask;

import java.util.ArrayList;

public class AdaptadorProductosCarrito extends RecyclerView.Adapter{
    ArrayList<Productos> productos;
    Holder holder;
    View.OnClickListener listenerBtImagen;

    public AdaptadorProductosCarrito(ArrayList<Productos> productos) {
        this.productos = productos;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.productoscarritorecycler,parent,false);
        holder = new Holder(v);
        holder.setClickBtnListener(v1 -> {
            if(listenerBtImagen!=null) listenerBtImagen.onClick(v1);
        });
        return holder;
    }

    public  void setClickBtImagen(View.OnClickListener listener){
        if(listener!=null) listenerBtImagen= listener;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((Holder)holder).bind(productos.get(position));
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }



    class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        View.OnClickListener listener;
        TextView textoPrecio, textoProducto, textoCantidad;
        ImageView imagenProducto;
        ImageButton imageButton;

        public Holder(@NonNull View itemView) {
            super(itemView);
            textoPrecio = itemView.findViewById(R.id.precioProductoCarrito);
            textoProducto = itemView.findViewById(R.id.tituloProductoCarrito);
            imagenProducto = itemView.findViewById(R.id.imagenProductoCarrito);
            textoCantidad = itemView.findViewById(R.id.cantidadTextView);
            imageButton = itemView.findViewById(R.id.eliminarProducto);
            imageButton.setOnClickListener(this);
        }

        public void bind(Productos productos){
            textoProducto.setText(productos.getNomProducto());
            textoPrecio.setText(productos.getPrecioUnitario()+"€");
            new DownloadImageTask(imagenProducto).execute(productos.getImgProducto());
            textoCantidad.setText("Cantidad: "+productos.getCantidad());
        }
        @Override
        public void onClick(View v) {
            if(listener!=null){
                this.listener.onClick(v);
            }
        }

        public void setClickBtnListener(View.OnClickListener listener){
            if(listener!=null){
                this.listener = listener;
            }
        }
    }
}
