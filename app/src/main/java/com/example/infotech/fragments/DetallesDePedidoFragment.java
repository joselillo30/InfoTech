package com.example.infotech.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.infotech.MainActivity;
import com.example.infotech.R;
import com.example.infotech.adaptadores.AdaptadorProductosResumen;
import com.example.infotech.clasespojo.CarritoDeLaCompra;
import com.example.infotech.clasespojo.DetallesPedidos;
import com.example.infotech.clasespojo.Pedido;
import com.example.infotech.clasespojo.Productos;
import com.example.infotech.servicios.ProveedorServicios;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetallesDePedidoFragment extends Fragment {

    ArrayList<Productos> productosArrayList = new ArrayList<>();
    ArrayList<DetallesPedidos> detallesPedidosArrayList = new ArrayList<>();
    TextView nombreApellidos, telefono, fechaCompra, fechaEntrega, direccion, localidad, provincia, codigoPostal, pais, metodopago, precioTextView;
    RecyclerView recyclerView;
    AdaptadorProductosResumen adapter;
    Pedido pedido;
    Button volver;

    public DetallesDePedidoFragment(Pedido pedido) {
        this.pedido = pedido;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.detallespedido_layout,container,false);
        recyclerView = v.findViewById(R.id.productospedido_recycler);
        nombreApellidos = v.findViewById(R.id.nombreapellidosDetalles);
        telefono = v.findViewById(R.id.telefonoContacto);
        fechaCompra = v.findViewById(R.id.fechaCompra);
        fechaEntrega = v.findViewById(R.id.fechaEntregaDetalles);
        direccion = v.findViewById(R.id.direccionDetalles);
        localidad = v.findViewById(R.id.lcoalidadDetalles);
        provincia = v.findViewById(R.id.provinciaDetalles);
        codigoPostal = v.findViewById(R.id.codPostalDetalles);
        pais = v.findViewById(R.id.paisDetalles);
        metodopago = v.findViewById(R.id.metodopagoDetalles);
        precioTextView = v.findViewById(R.id.precioPedido);

        nombreApellidos.setText(pedido.getNomApellidosContacto());
        telefono.setText(pedido.getTelefonoContacto());

        fechaCompra.setText(pedido.getFechaPedido().split(" ")[0].replace("-","/"));
        fechaEntrega.setText(pedido.getFechaEntrega().split(" ")[0].replace("-","/"));

        direccion.setText(pedido.getDireccionEnvio());
        localidad.setText(pedido.getCiudadEnvio());
        provincia.setText(pedido.getProvinciaEnvio());
        codigoPostal.setText(pedido.getCodPostalEnvio());
        pais.setText(pedido.getPaisEnvio());
        metodopago.setText(pedido.getTipoPago());

        volver = v.findViewById(R.id.volverDetallesPedidoButton);
        adapter = new AdaptadorProductosResumen(productosArrayList);

        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).cambiaFragment();
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        getDetallesPedido();

        return v;
    }

    private void getDetallesPedido(){
        ProveedorServicios proveedorServicios = ((MainActivity)getActivity()).crearRetrofit();
        Call<List<DetallesPedidos>> call = proveedorServicios.getDetallesPedido(pedido.getIdPedido(),pedido.getIdPedido());
        call.enqueue(new Callback<List<DetallesPedidos>>() {
            @Override
            public void onResponse(Call<List<DetallesPedidos>> call, Response<List<DetallesPedidos>> response) {
                for (DetallesPedidos d :
                        response.body()) {
                    detallesPedidosArrayList.add(d);
                }
                llamaGetProductos();
            }

            @Override
            public void onFailure(Call<List<DetallesPedidos>> call, Throwable t) {

            }
        });
    }
    private void getProductos(Integer id){
        ProveedorServicios proveedorServicios = ((MainActivity)getActivity()).crearRetrofit();
        Call<List<Productos>> call = proveedorServicios.getProducto(id);
        call.enqueue(new Callback<List<Productos>>() {
            @Override
            public void onResponse(Call<List<Productos>> call, Response<List<Productos>> response) {
                for (Productos p :
                        response.body()) {
                    for (DetallesPedidos d:
                            detallesPedidosArrayList) {
                        if(p.getIdProducto()==d.getIdProducto())
                            p.setCantidad(d.getCantidad());
                    }
                        productosArrayList.add(p);
                }
                adapter.notifyDataSetChanged();
                setPrecio();

            }

            @Override
            public void onFailure(Call<List<Productos>> call, Throwable t) {
                Toast.makeText(getContext(),"",Toast.LENGTH_LONG);
            }
        });
    }

    private void setPrecio() {
        DecimalFormat format = new DecimalFormat("#.00");
        float precio=0;
        for (Productos p : productosArrayList) {
            precio += p.getPrecioUnitario()*p.getCantidad();
        }
        precioTextView.setText(format.format(precio)+"€");
    }

    private void llamaGetProductos(){
        for (DetallesPedidos d :
                detallesPedidosArrayList) {
            getProductos(d.getIdProducto());
        }
    }
}
