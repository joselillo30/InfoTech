package com.example.infotech.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.infotech.R;
import com.example.infotech.clasespojo.Categorias;
import com.example.infotech.servicios.DownloadImageTask;

import java.util.ArrayList;

public class AdaptadorCategorias extends RecyclerView.Adapter implements View.OnClickListener {

    ArrayList<Categorias> categorias;
    Holder holder;
    View.OnClickListener listener;

    public AdaptadorCategorias(ArrayList<Categorias> categorias) {
        this.categorias = categorias;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardviewcategoria_recycler,parent,false);
        holder = new Holder(v);
        v.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((Holder)holder).bind(categorias.get(position));
    }

    @Override
    public int getItemCount() {
        return categorias.size();
    }

    @Override
    public void onClick(View v) {
        if(listener!=null)listener.onClick(v);
    }
    public void setClickListener(View.OnClickListener listener){
        if(listener!=null)this.listener=listener;
    }

    public class Holder extends RecyclerView.ViewHolder {
        ImageView imagenCategoria;
        TextView txtCategoria;

        public Holder(@NonNull View itemView) {
            super(itemView);
            txtCategoria = itemView.findViewById(R.id.textoCategoria);
            imagenCategoria = itemView.findViewById(R.id.imagenCategoria);
        }

        public void bind(Categorias categorias){
            txtCategoria.setText(categorias.getNomCategoria());
            new DownloadImageTask((ImageView)imagenCategoria).execute(categorias.getImgCategoria());
        }
    }
}
