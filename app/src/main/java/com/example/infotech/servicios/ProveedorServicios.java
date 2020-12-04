package com.example.infotech.servicios;

import com.example.infotech.clasespojo.Categorias;
import com.example.infotech.clasespojo.Clientes;
import com.example.infotech.clasespojo.DetallesPedidos;
import com.example.infotech.clasespojo.Pedido;
import com.example.infotech.clasespojo.Productos;
import com.example.infotech.clasespojo.Transportistas;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ProveedorServicios {
    @GET("categorias")
    @Headers({"Accept: application/json","Content-Type: application/json"})
    Call<List<Categorias>> getCategorias();
    @GET("productos")
    @Headers({"Accept: application/json","Content-Type: application/json"})
    Call<List<Productos>> getProductos();
    @GET("productos/{id}")
    @Headers({"Accept: application/json","Content-Type: application/json"})
    Call<List<Productos>> getProducto(@Path("id") Integer id);
    @GET("clientes")
    @Headers({"Accept: application/json","Content-Type: application/json"})
    Call<List<Clientes>> getClientes();
    @GET("transportistas")
    @Headers({"Accept: application/json","Content-Type: application/json"})
    Call<List<Transportistas>> getTransportistas();
    @GET("transportistas/{id}")
    @Headers({"Accept: application/json","Content-Type: application/json"})
    Call<Transportistas> getTransportista(@Path("id")int idTransportista);
    @GET("pedidos")
    @Headers({"Accept: application/json","Content-Type: application/json"})
    Call<List<Pedido>> getPedidos();
    @GET("detallespedidos/IdPedido/")
    @Headers({"Accept: application/json","Content-Type: application/json"})
    Call<List<DetallesPedidos>> getDetallesPedido(@Query("desde") Integer desde,@Query("hasta") Integer hasta);
    @POST("clientes")
    @Headers({"Accept: application/json","Content-Type: application/json"})
    Call<RespuestaJson> postClientes(@Body Clientes clientes);
    @POST("pedidos")
    @Headers({"Accept: application/json","Content-Type: application/json"})
    Call<RespuestaJson> postPedido(@Body Pedido pedido);
    @POST("detallespedidos")
    @Headers({"Accept: application/json","Content-Type: application/json"})
    Call<RespuestaJson> postDetallesPedidos(@Body DetallesPedidos detallesPedidos);
    @PUT("clientes/{id}")
    @Headers({"Accept: application/json","Content-Type: application/json"})
    Call<RespuestaJson> putClientes(@Path("id")int idClientes,@Body Clientes clientes);
}
