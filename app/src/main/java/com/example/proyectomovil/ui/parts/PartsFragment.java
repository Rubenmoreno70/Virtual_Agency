package com.example.proyectomovil.ui.parts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.proyectomovil.R;


public class PartsFragment extends Fragment {

    private TextView textview_gallery;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_parts, container, false);

        textview_gallery = root.findViewById(R.id.tev_gallery);
        textview_gallery.setText("Hello Gallery");
        return root;
    }

}