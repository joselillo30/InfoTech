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
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.infotech.MainActivity;
import com.example.infotech.R;
import com.example.infotech.adaptadores.AdaptadorProductos;
import com.example.infotech.adaptadores.AdaptadorProductosResumen;
import com.example.infotech.clasespojo.CarritoDeLaCompra;
import com.example.infotech.clasespojo.Clientes;
import com.example.infotech.clasespojo.DetallesPedidos;
import com.example.infotech.clasespojo.Pedido;
import com.example.infotech.clasespojo.Productos;
import com.example.infotech.clasespojo.Transportistas;
import com.example.infotech.servicios.ProveedorServicios;
import com.example.infotech.servicios.RespuestaJson;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResumenPedidoFragment extends Fragment {

    ProveedorServicios proveedorServicios;
    Integer idPedido;
    RecyclerView recycler;
    AdaptadorProductosResumen adapter;
    ArrayList<DetallesPedidos> detallesPedidosList = new ArrayList<>();
    TextView nombreApellidos, direccion, ciudadCodPostal, provinciaPais, nombreTransportista, precioResumenPedido, fechaEntrega;
    Clientes usuario;
    Pedido pedido;
    private Transportistas transportista;
    Button finalizarPedido;
    Calendar calendar;

    public ResumenPedidoFragment(Clientes usuario, Pedido pedido) {
        this.usuario = usuario;
        this.pedido = pedido;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.resumenpedido_layout,container,false);
        proveedorServicios = ((MainActivity)getActivity()).crearRetrofit();
        nombreApellidos = v.findViewById(R.id.nombreapellidosResumen);
        direccion = v.findViewById(R.id.direccionResumen);
        ciudadCodPostal = v.findViewById(R.id.codpostalCiudadResumen);
        provinciaPais = v.findViewById(R.id.provinciaPaisResumen);
        nombreTransportista = v.findViewById(R.id.nombreTransportista);
        precioResumenPedido = v.findViewById(R.id.precioResumenPedido);
        fechaEntrega = v.findViewById(R.id.fechaEntrega);
        recycler = v.findViewById(R.id.resumenpedido_recycler);
        finalizarPedido = v.findViewById(R.id.finalizarPedidoButton);
        finalizarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (Productos p :
                        CarritoDeLaCompra.getProductosCarritos()) {
                    DetallesPedidos d=new DetallesPedidos();

                    d.setCantidad(p.getCantidad());
                    d.setIdProducto(p.getIdProducto());
                    d.setPrecio(p.getPrecioUnitario());
                    detallesPedidosList.add(d);
                }
                pedido.setIdCliente(usuario.getIdCliente());
                pedido.setNumPedido(new Random().nextInt(999999)+10000);
                pedido.setEstadoEntrega("ALMACEN");
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                pedido.setFechaEntrega(simpleDateFormat.format(calendar.getTime()));
                pedido.setFechaPedido(simpleDateFormat.format(new Date()));
                pedido.setEntregado(false);
                if(pedido.getTipoPago().equalsIgnoreCase("efectivo")){
                    pedido.setPagado(false);
                }else{
                    pedido.setPagado(true);
                    pedido.setFechaPagado(simpleDateFormat.format(new Date()));
                }
                realizaPedido();


                ((MainActivity)getActivity()).cambiaFragment();
            }
        });
        adapter = new AdaptadorProductosResumen(CarritoDeLaCompra.getProductosCarritos());
        recycler.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recycler.setLayoutManager(layoutManager);
        recycler.addItemDecoration(new DividerItemDecoration(getContext(),
                layoutManager.getOrientation()));
        calendar =Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_YEAR,2);
        Date fechaEntregaDate = new Date(calendar.getTime().getTime());
        fechaEntrega.setText(new SimpleDateFormat("dd/MM/yyyy").format(fechaEntregaDate));
        nombreApellidos.setText(usuario.getNombre()+" "+usuario.getApellidos());
        direccion.setText(usuario.getDireccion1());
        ciudadCodPostal.setText(usuario.getCiudad()+", "+usuario.getCodPostal());
        provinciaPais.setText(usuario.getProvincia()+", "+usuario.getPais());
        DecimalFormat format = new DecimalFormat("#.00");
        if(pedido.getTipoPago().equalsIgnoreCase("efectivo")){
            float precioProv=0;
            for (Productos p :
                    CarritoDeLaCompra.getProductosCarritos()) {
                precioProv += p.getPrecioUnitario()*p.getCantidad();
            }
            precioResumenPedido.setText(format.format(precioProv+3.60)+"€");
        }else{
            float precioProv=0;
            for (Productos p :
                    CarritoDeLaCompra.getProductosCarritos()) {
                precioProv += p.getPrecioUnitario()*p.getCantidad();
            }
            precioResumenPedido.setText(format.format(precioProv));
        }

        getTransportista();
        return v;
    }

    private void getPedidos(){
        Call<List<Pedido>> call = proveedorServicios.getPedidos();
        call.enqueue(new Callback<List<Pedido>>() {
            @Override
            public void onResponse(Call<List<Pedido>> call, Response<List<Pedido>> response) {
                Pedido p = response.body().get(response.body().size()-1);
                idPedido = p.getIdPedido();
                for (DetallesPedidos d:
                        detallesPedidosList) {
                    d.setIdPedido(idPedido);
                    realizaDetallesPedido(d);
                }
            }

            @Override
            public void onFailure(Call<List<Pedido>> call, Throwable t) {

            }
        });
    }

    private void getTransportista(){
        Call<Transportistas> call = proveedorServicios.getTransportista(pedido.getIdTransportista());
        call.enqueue(new Callback<Transportistas>() {
            @Override
            public void onResponse(Call<Transportistas> call, Response<Transportistas> response) {
                transportista = response.body();
                nombreTransportista.setText(transportista.getNomEmpresa());
            }

            @Override
            public void onFailure(Call<Transportistas> call, Throwable t) {

            }
        });
    }

    private void realizaPedido(){
        Call<RespuestaJson> callPedido = proveedorServicios.postPedido(pedido);
        callPedido.enqueue(new Callback<RespuestaJson>() {
            @Override
            public void onResponse(Call<RespuestaJson> call, Response<RespuestaJson> response) {
                getPedidos();
                Toast.makeText(getContext(),"",Toast.LENGTH_LONG);
            }

            @Override
            public void onFailure(Call<RespuestaJson> call, Throwable t) {
                Toast.makeText(getContext(),"",Toast.LENGTH_LONG);
            }
        });
    }

    private void realizaDetallesPedido(DetallesPedidos detallesPedidos){
        Call<RespuestaJson> callDetallesPedido = proveedorServicios.postDetallesPedidos(detallesPedidos);
        callDetallesPedido.enqueue(new Callback<RespuestaJson>() {
            @Override
            public void onResponse(Call<RespuestaJson> call, Response<RespuestaJson> response) {
            }

            @Override
            public void onFailure(Call<RespuestaJson> call, Throwable t) {
            }
        });
    }

}
