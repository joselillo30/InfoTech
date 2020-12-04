package com.example.infotech.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.infotech.R;
import com.example.infotech.clasespojo.Pedido;
import com.example.infotech.clasespojo.Productos;
import com.example.infotech.servicios.DownloadImageTask;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class AdaptadorPedidos extends RecyclerView.Adapter implements View.OnClickListener {

    ArrayList<Pedido> pedidos;
    Holder holder;
    View.OnClickListener listener;

    public AdaptadorPedidos(ArrayList<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.pedidos_recycler_layout,parent,false);
        holder = new Holder(v);
        v.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((Holder)holder).bind(pedidos.get(position));
    }

    @Override
    public int getItemCount() {
        return pedidos.size();
    }

    @Override
    public void onClick(View v) {
        if(listener!=null)listener.onClick(v);
    }

    public void setClickListener(View.OnClickListener listener){
        if(listener!=null)this.listener=listener;
    }

    class Holder extends RecyclerView.ViewHolder{

        TextView textoFechaRealizado,  textoFechaEntrega, textoEstadoPedido, textoNumPedido;

        public Holder(@NonNull View itemView) {
            super(itemView);
            textoFechaRealizado = itemView.findViewById(R.id.fecha_realizado);
            textoFechaEntrega = itemView.findViewById(R.id.entregaPrevista);
            textoEstadoPedido = itemView.findViewById(R.id.estadoPedido);
            textoNumPedido = itemView.findViewById(R.id.numeroPedido);
        }

        public void bind(Pedido pedidos){
            if(pedidos.getFechaPedido()!=null){
                textoFechaRealizado.setText("Realizado: "+pedidos.getFechaPedido().split(" ")[0].replace("-","/"));
            }
            if(pedidos.getFechaEntrega()!=null){
                textoFechaEntrega.setText("Entrega prevista: "+pedidos.getFechaEntrega().split(" ")[0].replace("-","/"));
            }
            textoEstadoPedido.setText("Estado: "+pedidos.getEstadoEntrega());
            textoNumPedido.setText("Nº de pedido: "+pedidos.getNumPedido());
        }
    }
}
