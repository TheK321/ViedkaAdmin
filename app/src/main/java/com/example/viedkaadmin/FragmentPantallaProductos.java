package com.example.viedkaadmin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class FragmentPantallaProductos extends Fragment {

    private TableLayout tableLayout;
    private String[] encabezadoProductos = {"Articulo","Cantidad","Edad","Precio Unitario"};
    private ArrayList<String[]> filas = new ArrayList<>();
    ImageButton editar;




    public FragmentPantallaProductos() {

    }


    public static FragmentPantallaProductos newInstance(String param1, String param2) {
        FragmentPantallaProductos fragment = new FragmentPantallaProductos();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pantalla_productos, null, false);

        editar = view.findViewById(R.id.editarBoton);

        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_container,
                        new FragmentEditarProductos()).commit();
            }
        });

        return view;
    }


}