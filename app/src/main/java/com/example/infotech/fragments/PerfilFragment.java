package com.example.infotech.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.infotech.MainActivity;
import com.example.infotech.R;
import com.example.infotech.clasespojo.Clientes;
import com.example.infotech.servicios.ProveedorServicios;
import com.example.infotech.servicios.RespuestaJson;
import com.example.infotech.servicios.ValidaCrearUsuario;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilFragment extends Fragment {
    Clientes usuario;
    Button guardarContraseña, guardarNick, guardarDireccionEnvio, guardarDireccionFacturacion;
    TextView correoTextView, inicialTextView;
    EditText calleEnvioEd,portalEnvioEd,escaleraEnvioEd, pisoEnvioEd, puertaEnvioEd,calleFacturacionEd,portalFacturacionEd,escaleraFacturacionEd, pisoFacturacionEd, puertaFacturacionEd,contraseñaViejaEd, contraseñaNuevaEd,contraseñaNuevaConfirmaEd, nickEd;

    public PerfilFragment(Clientes usuario) {
        this.usuario = usuario;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.perfil_layout,container,false);
        nickEd = v.findViewById(R.id.nick);
        correoTextView = v.findViewById(R.id.correoElectronicoTextView);
        inicialTextView = v.findViewById(R.id.inicialCorreo);
        correoTextView.setText(usuario.getEmail());
        inicialTextView.setText(String.valueOf(usuario.getEmail().charAt(0)).toUpperCase());
        calleEnvioEd = v.findViewById(R.id.calleEnvioEd);
        portalEnvioEd = v.findViewById(R.id.portalEnvioEd);
        escaleraEnvioEd = v.findViewById(R.id.escaleraEnvioEd);
        pisoEnvioEd = v.findViewById(R.id.pisoEnvioEd);
        puertaEnvioEd = v.findViewById(R.id.puertaEnvioEd);
        calleFacturacionEd = v.findViewById(R.id.calleFacturacionPerfil);
        portalFacturacionEd = v.findViewById(R.id.portalFacturacionPerfil);
        escaleraFacturacionEd = v.findViewById(R.id.escaleraFacturacionPerfil);
        pisoFacturacionEd = v.findViewById(R.id.pisoFacturacionPerfil);
        puertaFacturacionEd = v.findViewById(R.id.puertaFacturacionPerfil);
        contraseñaNuevaEd = v.findViewById(R.id.nuevaContraseñaEd);
        contraseñaViejaEd = v.findViewById(R.id.contraseñaViejaEd);
        contraseñaNuevaConfirmaEd = v.findViewById(R.id.nuevaContraseñaConfirmarEd);
        guardarContraseña = v.findViewById(R.id.guardarContraseñaButton);
        guardarDireccionEnvio = v.findViewById(R.id.guardarDireccionEnvio);
        guardarDireccionFacturacion = v.findViewById(R.id.guardarDireccionFacturacion);
        guardarNick = v.findViewById(R.id.guardarNick);
        guardarNick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario.setUsuario(nickEd.getText().toString());
                ProveedorServicios proveedorServicios = ((MainActivity)getActivity()).crearRetrofit();
                Call<RespuestaJson> call = proveedorServicios.putClientes(usuario.getIdCliente(),usuario);
                call.enqueue(new Callback<RespuestaJson>() {
                    @Override
                    public void onResponse(Call<RespuestaJson> call, Response<RespuestaJson> response) {
                        RespuestaJson respuestaJson= response.body();
                        if(respuestaJson.respuesta>=200&&respuestaJson.respuesta<300)
                            Toast.makeText(getContext(),"Nick guardado con exito",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(getContext(),"No se ha podido guardar el nick. Vuelve a intentarlo mas tarde",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<RespuestaJson> call, Throwable t) {
                        Toast.makeText(getContext(),"No se ha podido guardar el nick. Vuelve a intentarlo mas tarde",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        guardarContraseña.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ValidaCrearUsuario.validaContraseñaCoincide(contraseñaNuevaEd.getText().toString(),contraseñaNuevaConfirmaEd.getText().toString())
                && ValidaCrearUsuario.validaContraseña(contraseñaNuevaEd.getText().toString())
                && !contraseñaNuevaEd.getText().toString().equals(contraseñaViejaEd.getText().toString())){
                    usuario.setContraseña(contraseñaNuevaEd.getText().toString());
                    ProveedorServicios retrofit = ((MainActivity)getActivity()).crearRetrofit();
                    Call<RespuestaJson> call = retrofit.putClientes(usuario.getIdCliente(),usuario);
                    call.enqueue(new Callback<RespuestaJson>() {
                        @Override
                        public void onResponse(Call<RespuestaJson> call, Response<RespuestaJson> response) {
                            RespuestaJson respuestaJson= response.body();
                            if(respuestaJson.respuesta>=200&&respuestaJson.respuesta<300)
                                Toast.makeText(getContext(),"Contraseña creada con exito",Toast.LENGTH_LONG).show();
                            else
                                Toast.makeText(getContext(),"No se ha podido crear la contraseña. Vuelve a intentarlo mas tarde",Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onFailure(Call<RespuestaJson> call, Throwable t) {
                            Toast.makeText(getContext(),"No se ha podido crear la contraseña. Vuelve a intentarlo mas tarde",Toast.LENGTH_LONG).show();
                        }
                    });
                }else{
                    Toast.makeText(getContext(),"Las contraseñas no coinciden",Toast.LENGTH_LONG).show();
                }

            }
        });
        guardarDireccionFacturacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String direccion = calleFacturacionEd.getText().toString()+", "+portalFacturacionEd.getText().toString()+", "+
                        (escaleraFacturacionEd.getText().toString().equals("")?", ":escaleraFacturacionEd.getText().toString()+", ")
                        +(pisoFacturacionEd.getText().toString().equals("")?", ":pisoFacturacionEd.getText().toString()+", ")
                        +(puertaFacturacionEd.getText().toString().equals("")?" ":puertaFacturacionEd.getText().toString());
                usuario.setDireccionFacturacion(direccion);
                ProveedorServicios proveedorServicios = ((MainActivity)getActivity()).crearRetrofit();
                Call<RespuestaJson> call = proveedorServicios.putClientes(usuario.getIdCliente(),usuario);
                call.enqueue(new Callback<RespuestaJson>() {
                    @Override
                    public void onResponse(Call<RespuestaJson> call, Response<RespuestaJson> response) {
                        RespuestaJson respuestaJson= response.body();
                        if(respuestaJson.respuesta>=200&&respuestaJson.respuesta<300)
                            Toast.makeText(getContext(),"Direccion de facturacion guardada con exito",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(getContext(),"No se ha podido guardar la direccion de facturacion. Vuelve a intentarlo mas tarde",Toast.LENGTH_LONG).show();
                    }
                    @Override
                    public void onFailure(Call<RespuestaJson> call, Throwable t) {
                        Toast.makeText(getContext(),"No se ha podido guardar la direccion de facturacion. Vuelve a intentarlo mas tarde",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        guardarDireccionEnvio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String direccion = calleEnvioEd.getText().toString()+", "+portalEnvioEd.getText().toString()+", "+
                        (escaleraEnvioEd.getText().toString().equals("")?", ":escaleraEnvioEd.getText().toString()+", ")
                        +(pisoEnvioEd.getText().toString().equals("")?", ":pisoEnvioEd.getText().toString()+", ")
                        +(puertaEnvioEd.getText().toString().equals("")?" ":puertaEnvioEd.getText().toString());
                usuario.setDireccion1(direccion);
                ProveedorServicios proveedorServicios = ((MainActivity)getActivity()).crearRetrofit();
                Call<RespuestaJson> call = proveedorServicios.putClientes(usuario.getIdCliente(),usuario);
                call.enqueue(new Callback<RespuestaJson>() {
                    @Override
                    public void onResponse(Call<RespuestaJson> call, Response<RespuestaJson> response) {
                        RespuestaJson respuestaJson= response.body();
                        if(respuestaJson.respuesta>=200&&respuestaJson.respuesta<300)
                            Toast.makeText(getContext(),"Direccion de envio guardada con exito",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(getContext(),"No se ha podido guardar la direccion de envio. Vuelve a intentarlo mas tarde",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<RespuestaJson> call, Throwable t) {
                        Toast.makeText(getContext(),"No se ha podido guardar la direccion de envio. Vuelve a intentarlo mas tarde",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        nickEd.setText(usuario.getUsuario());
        if(usuario.getDireccion1()!=null){
            String[] direccionEnvio = usuario.getDireccion1().split(",");
            calleEnvioEd.setText(direccionEnvio[0]);
            portalEnvioEd.setText(direccionEnvio[1]);
            escaleraEnvioEd.setText(direccionEnvio[2]);
            pisoEnvioEd.setText(direccionEnvio[3]);
            puertaEnvioEd.setText(direccionEnvio[4]);
        }
        if(usuario.getDireccionFacturacion()!=null){
            String[] direccionFacturacion = usuario.getDireccionFacturacion().split(",");
            calleFacturacionEd.setText(direccionFacturacion[0]);
            portalFacturacionEd.setText(direccionFacturacion[1]);
            escaleraFacturacionEd.setText(direccionFacturacion[2]);
            pisoFacturacionEd.setText(direccionFacturacion[3]);
            puertaFacturacionEd.setText(direccionFacturacion[4]);
        }
        return v;
    }
}
