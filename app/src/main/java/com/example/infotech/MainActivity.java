package com.example.infotech;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.infotech.clasespojo.Categorias;
import com.example.infotech.clasespojo.Clientes;
import com.example.infotech.clasespojo.ListaCategorias;
import com.example.infotech.clasespojo.ListaProductos;
import com.example.infotech.clasespojo.Productos;
import com.example.infotech.fragments.CambiarInformacionPagoFragment;
import com.example.infotech.fragments.CargandoFragment;
import com.example.infotech.fragments.CarritoFragment;
import com.example.infotech.fragments.CrearCuentaUsuarioFragment;
import com.example.infotech.fragments.PedidosCuentaFragment;
import com.example.infotech.fragments.PerfilFragment;
import com.example.infotech.fragments.ProductosBusquedaFragment;
import com.example.infotech.fragments.VistaPrincipalFragment;
import com.example.infotech.servicios.ProveedorServicios;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    boolean doubleBackToExitPressedOnce = false;
    Menu menuOpciones;
    public static FragmentManager fm;
    public static FragmentTransaction ft;
    public MaterialSearchView searchView;
    public TextView textoCarrito;
    public ImageView carritoCompra;
    public Clientes cliente;
    public DrawerLayout drawerLayout;

    //Metodo que sirve para volver a la vista principal desde cualquier parte de la aplicacion y si pulsas dos veces salirse de la aplicacion
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Pulsa atras otra vez para salir", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
        cambiaFragment();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawer_layout);

        Toolbar toolbar = findViewById(R.id.mi_toolbar);
        toolbar.setNavigationIcon(R.drawable.baseline_menu_black_48dp);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("InfoTech");
        toolbar.setTitleTextColor(Color.BLACK);
        textoCarrito = findViewById(R.id.numeroCarrito);
        carritoCompra = findViewById(R.id.carritoCompra);

        searchView = findViewById(R.id.search_view);
        carritoCompra.setOnClickListener(v -> {
            ft = fm.beginTransaction();
            ft.replace(R.id.contenedor,new CarritoFragment());
            ft.addToBackStack("fragmentCarrito");
            ft.commit();
        });
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(query!=null && !query.isEmpty()){
                    ArrayList<Productos> productosEncontrados = new ArrayList<>();
                    for (Productos p :
                            ListaProductos.getListaProductos()) {
                        if(p.getNomProducto().toLowerCase().contains(query.toLowerCase()))
                            productosEncontrados.add(p);
                    }
                    ft = fm.beginTransaction();
                    ft.replace(R.id.contenedor,new ProductosBusquedaFragment(productosEncontrados));
                    ft.addToBackStack("fragmentBusqueda");
                    ft.commit();

                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        Fragment fragmentoCargando = new CargandoFragment();
        ft.replace(R.id.contenedor,fragmentoCargando);
        ft.commit();
        getCategorias();
        getProductos();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    public ProveedorServicios crearRetrofit(){
        String url = "http://pdam05b.iesdoctorbalmis.info/infotech/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(ProveedorServicios.class);
    }

    private void getCategorias(){
        ProveedorServicios proveedorServicios = crearRetrofit();
        Call<List<Categorias>> responseCall = proveedorServicios.getCategorias();
        responseCall.enqueue(new Callback<List<Categorias>>() {
            @Override
            public void onResponse(Call<List<Categorias>> call, Response<List<Categorias>> response) {
                List<Categorias> listCategorias = response.body();
                ListaCategorias.setListaCategorias(listCategorias);
            }

            @Override
            public void onFailure(Call<List<Categorias>> call, Throwable t) {
                Log.e("","",null);
            }
        });
    }

    private void getProductos(){
        final ProveedorServicios proveedorServicios = crearRetrofit();
        Call<List<Productos>> responseCall = proveedorServicios.getProductos();
        responseCall.enqueue(new Callback<List<Productos>>() {
            @Override
            public void onResponse(Call<List<Productos>> call, Response<List<Productos>> response) {
                List<Productos> listProductos = response.body();
                ListaProductos.setListaProductos(listProductos);
                cambiaFragment();
            }

            @Override
            public void onFailure(Call<List<Productos>> call, Throwable t) {

            }
        });
    }

    public void cambiaFragment(){
        ft = fm.beginTransaction();
        ft.setCustomAnimations(android.R.animator.fade_in,android.R.animator.fade_out);
        ft.replace(R.id.contenedor,new VistaPrincipalFragment());
        ft.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item,menu);
        menuOpciones = menu;
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
        getMenuInflater().inflate(R.menu.perfil_menuitem,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.cerrarSesion:
                cliente = null;
                menuOpciones.findItem(R.id.perfil).setVisible(true);
                menuOpciones.findItem(R.id.perfil2).setVisible(false);
                break;
            case R.id.accederPerfil:
                fm = getSupportFragmentManager();
                ft = fm.beginTransaction();
                ft.replace(R.id.contenedor,new PerfilFragment(cliente));
                ft.addToBackStack("perfil");
                ft.commit();
                break;
            case R.id.perfil:
                    EditText ed1,ed2;
                    Dialog dialogo;
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    LayoutInflater layoutInflater = getLayoutInflater();
                    final View v = layoutInflater.inflate(R.layout.dar_de_alta_layout,null);
                    builder.setView(v);
                    dialogo = builder.create();
                    dialogo.show();
                    Button botonEntrar = v.findViewById(R.id.entrar);
                    Button botonCrear = v.findViewById(R.id.crear);
                    ed1 = v.findViewById(R.id.nombre);
                    ed2 = v.findViewById(R.id.contraseña);
                    //Evento que controla el inicio de sesion en la aplicacion
                    botonEntrar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            final ProveedorServicios proveedorServicios = crearRetrofit();
                            Call<List<Clientes>> responseCall = proveedorServicios.getClientes();
                            responseCall.enqueue(new Callback<List<Clientes>>() {
                                @Override
                                public void onResponse(Call<List<Clientes>> call, Response<List<Clientes>> response) {
                                    List<Clientes> listClientes = response.body();
                                    for (Clientes c :
                                            listClientes) {
                                        if(ed1.getText().toString().equalsIgnoreCase(c.getUsuario())&&ed2.getText().toString().equals(c.getContraseña())){
                                            cliente = c;
                                            dialogo.dismiss();
                                            menuOpciones.findItem(R.id.perfil).setVisible(false);
                                            menuOpciones.findItem(R.id.perfil2).setVisible(true);
                                            break;
                                        }
                                    }
                                    if(cliente==null)
                                        Toast.makeText(MainActivity.this, "El nombre de usuario o la contraseña son incorrectos",Toast.LENGTH_LONG).show();
                                }

                                @Override
                                public void onFailure(Call<List<Clientes>> call, Throwable t) {
                                    Toast.makeText(MainActivity.this, "No se ha podido iniciar sesion",Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    });
                    //Evento que controla la creacion de un usuario
                    botonCrear.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            fm = getSupportFragmentManager();
                            ft = fm.beginTransaction();
                            ft.setCustomAnimations(android.R.animator.fade_in,android.R.animator.fade_out);
                            ft.replace(R.id.contenedor,new CrearCuentaUsuarioFragment());
                            ft.addToBackStack("fragmentoUsuarios");
                            ft.commit();
                            dialogo.dismiss();
                        }
                    });
                break;
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.accederPedidos:
                fm = getSupportFragmentManager();
                ft = fm.beginTransaction();
                ft.setCustomAnimations(android.R.animator.fade_in,android.R.animator.fade_out);
                ft.replace(R.id.contenedor,new PedidosCuentaFragment(cliente));
                ft.addToBackStack("fagmentoPedidos");
                ft.commit();
                return true;
            case R.id.accederPago:
                fm = getSupportFragmentManager();
                ft = fm.beginTransaction();
                ft.setCustomAnimations(android.R.animator.fade_in,android.R.animator.fade_out);
                ft.replace(R.id.contenedor,new CambiarInformacionPagoFragment(cliente));
                ft.addToBackStack("fagmentoPedidos");
                ft.commit();
                return true;

        }
        return true;
    }

}
