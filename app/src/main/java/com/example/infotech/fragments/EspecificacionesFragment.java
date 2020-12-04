package com.example.infotech.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.infotech.R;

public class EspecificacionesFragment extends Fragment {

    TextView especificacionesTextView;
    String especificaciones;

    public EspecificacionesFragment(String especificaciones) {
        this.especificaciones = especificaciones;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.especificaciones_layout, container, false);
        especificacionesTextView = v.findViewById(R.id.especificaciones_texto);
        especificacionesTextView.setText(especificaciones);
        return v;
    }
}
