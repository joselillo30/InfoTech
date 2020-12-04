package com.example.infotech.servicios;

import android.util.Patterns;

import com.example.infotech.clasespojo.Clientes;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//Clase estatica para validar la creacion de un usuario
public class ValidaCrearUsuario {
    //Comprueba si el correo electronico tiene el formato correcto
    public static boolean validaCorreoElectronico(String correoElectronico){
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(correoElectronico).matches();
    }
    //Comprueba si los correos coinciden
    public static boolean validaCorreoConfirmacion(String correoElectronico, String correoElectronicoConfirma){
        return correoElectronico.equals(correoElectronicoConfirma);
    }
    //Comprueba si el nombre de usuario tiene las longitudes premitidas
    public static boolean validaUsuario(String usuario){
        return usuario.length()>=6 && usuario.length()<=30;
    }
    //Comprueba si la contraseña tiene una letra mayuscula, una minuscula, un numero y un caracter especial
    public static boolean validaContraseña(String contraseña){
        Pattern pattern = Pattern.compile("(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,16}$");
        boolean b = pattern.matcher(contraseña).matches();
        return b;
    }
    //Comprueba si la longitud de la contraseña es la correcta
    public static boolean validaLongitudContraseña(String contraseña) {

        boolean b = contraseña.length()>=8 && contraseña.length()<=16;
        return b;
    }
    //Comprueba si las contraseñas coinciden
    public static boolean validaContraseñaCoincide(String contraseña, String contraseñaCoincide){
        return contraseña.equals(contraseñaCoincide);
    }
}
