package com.example.viedkaadmin;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentPantallaAlmacen#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentPantallaAlmacen extends Fragment {

    private TableLayout tableLayout;
    private String[] encabezado = {"ID\n","Prenda\n","Tipo\n","Disponibles\n","Vendidos\n","Precio\nUnidad","Precio\nTotal"};
    private ArrayList<String[]> filas = new ArrayList<>();
    private int totaldis=0;
    private TextView txtvav;
    private String [] [] rawConsulta;
    private Producto [] listaProductos;


    public FragmentPantallaAlmacen() {

    }

    public static FragmentPantallaAlmacen newInstance(String param1, String param2) {
        FragmentPantallaAlmacen fragment = new FragmentPantallaAlmacen();

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
        View view = inflater.inflate(R.layout.fragment_pantalla_almacen, null, false);
        tableLayout = view.findViewById(R.id.tableLayoutAlmacen);
        ClassTablaAlmacen tablaAlmacen = new ClassTablaAlmacen(tableLayout, getContext());
        tablaAlmacen.agregarEncabezado(encabezado);

        tablaAlmacen.agregarDatos(obtenerDatos(),rawConsulta[1].length);
        txtvav = view.findViewById(R.id.textView_totalesav);
        txtvav.setText(String.valueOf(totaldis));
        //tablaAlmacen.fondoEncabezadoColor(Color.WHITE);
        //tablaAlmacen.fondoCeldasColor(Color.GREEN, Color.YELLOW);

        return view;
    }

    private ArrayList<String[]>obtenerDatos(){

        try {
            rawConsulta = ((MainActivity) getActivity()).Consultar("Prenda", 6, false, "");
            System.out.println("Tamaño de rawconsulta es "+rawConsulta[0].length);
            for (int i = 0; i < rawConsulta[1].length; i++) {
                System.out.println(rawConsulta[0][i]+rawConsulta[1][i]);
                String totalvendidos = ((MainActivity) getActivity()).ConsultarSuma("Movimientos", "Cantidad", true, ("idPrenda=\""+rawConsulta[0][i]+"\""),"idPrenda")[0];
                System.out.println(totalvendidos);
                int totvendidos = Integer.parseInt(totalvendidos) * Integer.parseInt(rawConsulta[5][i]);
                filas.add(new String[]{rawConsulta[0][i],
                        rawConsulta[1][i],
                        rawConsulta[2][i],
                        rawConsulta[3][i], totalvendidos,rawConsulta[5][i], String.valueOf(totvendidos)});
                totaldis+=Integer.parseInt(rawConsulta[3][i]);

            }
        } catch (Exception ex){
            System.out.println(ex.toString());
        }
        return filas;
    }
    public void insertarDatos(View view){
        String[] nuevoitem = new String[]{/*VALORES NUEVOS*/"","","","","","",""};
    }
    //
}

