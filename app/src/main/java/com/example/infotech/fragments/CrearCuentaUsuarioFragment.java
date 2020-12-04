package com.example.infotech.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.infotech.MainActivity;
import com.example.infotech.R;
import com.example.infotech.clasespojo.Clientes;
import com.example.infotech.servicios.ValidaCrearUsuario;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class CrearCuentaUsuarioFragment extends Fragment {
    Clientes usuario = new Clientes();
    boolean correcto;
    Button siguienteButton;
    TextInputLayout textInputLayoutCorreo, textInputLayoutCorreoConfirma,textInputLayoutUsuario, textInputLayoutContraseña;
    TextInputEditText textInputEditTextCorreo, textInputEditTextCorreoConfirma,textInputEditTextUsuario, textInputEditTextContraseña;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.crearcuenta_usuariofragment_layout,container,false);
        siguienteButton = v.findViewById(R.id.siguienteButton);
        textInputLayoutCorreo = v.findViewById(R.id.til_correoelectronico);
        textInputLayoutCorreoConfirma = v.findViewById(R.id.til_confirmacioncorreoelectronico);
        textInputEditTextCorreo = v.findViewById(R.id.correoElectronicoEditText);
        textInputEditTextCorreoConfirma = v.findViewById(R.id.confirmarCorreoEditText);
        textInputLayoutUsuario = v.findViewById(R.id.til_usuario);
        textInputEditTextUsuario = v.findViewById(R.id.usuarioEditText);
        textInputLayoutContraseña = v.findViewById(R.id.til_contraseña);
        textInputEditTextContraseña = v.findViewById(R.id.contraseñaEditText);
        siguienteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                correcto = true;
                if(!ValidaCrearUsuario.validaCorreoElectronico(textInputEditTextCorreo.getText().toString())){
                    textInputLayoutCorreo.setError("Formato del correo no valido");
                    correcto = false;
                }
                if(!ValidaCrearUsuario.validaCorreoConfirmacion(textInputEditTextCorreo.getText().toString(),textInputEditTextCorreoConfirma.getText().toString())){
                    textInputLayoutCorreoConfirma.setError("Los correos no coinciden");
                    correcto = false;
                }
                if(!ValidaCrearUsuario.validaUsuario(textInputEditTextUsuario.getText().toString())){
                    textInputLayoutUsuario.setError("No puede tener menos de 6 caracteres y mas de 30");
                    correcto = false;
                }
                if(!ValidaCrearUsuario.validaContraseña(textInputEditTextContraseña.getText().toString())){
                    textInputLayoutContraseña.setError("Debe tener al menos una letra mayuscula, una minuscula, un digito, caracter especial");
                    correcto = false;
                }
                if(!ValidaCrearUsuario.validaLongitudContraseña(textInputEditTextContraseña.getText().toString())){
                    textInputLayoutContraseña.setError("La contraseña debe tener entre 8 y 16 caracteres");
                    correcto = false;
                }
                if(correcto){
                    usuario.setUsuario(textInputEditTextUsuario.getText().toString());
                    usuario.setEmail(textInputEditTextCorreo.getText().toString());
                    usuario.setContraseña(textInputEditTextContraseña.getText().toString());
                    ((MainActivity)getActivity())
                            .getSupportFragmentManager()
                            .beginTransaction()
                            .setCustomAnimations(android.R.animator.fade_in,android.R.animator.fade_out)
                            .replace(R.id.contenedor,new CrearCuentaDireccionFragment(usuario))
                            .addToBackStack("direccionFragment")
                            .commit();
                }

            }
        });
        return v;
    }
}
