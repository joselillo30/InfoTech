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

public class DescripcionFragment extends Fragment {

    TextView descripcionTextView;
    String descripcion;

    public DescripcionFragment(String descripcion) {
        this.descripcion = descripcion;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.descripcion_layout,container,false);
        descripcionTextView = v.findViewById(R.id.descripcion_texto);
        descripcionTextView.setText(descripcion);
        return v;
    }
}
