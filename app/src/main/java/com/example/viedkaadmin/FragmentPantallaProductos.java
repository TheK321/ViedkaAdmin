package com.example.viedkaadmin;

import static android.view.Gravity.CENTER;
import static android.view.Gravity.CENTER_HORIZONTAL;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class FragmentPantallaProductos extends Fragment {

    Button cambiar;
    TableLayout tl;
    TableRow tr;
    TextView tv1;
    private String [] [] rawConsulta;
    private Producto [] listaProductos;
    private boolean color = false;

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

        cambiar = view.findViewById(R.id.editarProductosBoton);
        tl = (TableLayout) view.findViewById(R.id.tProductos);

        int conteo = ((MainActivity) getActivity()).Contar("Prenda");

        cambiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_container,
                        new FragmentEditarProductos()).addToBackStack("editarproductos").commit();
            }
        });

        TableRow.LayoutParams params = new TableRow.LayoutParams(150, TableRow.LayoutParams.WRAP_CONTENT);
        tr = new TableRow(getActivity());
        //String colorHeader="#84477F";
        //String colorHeader = "#db9600";
        int colorHeader=R.drawable.style_librocontable_h;
        agregaEncabezado(getActivity(), params, "ID", tr, colorHeader);
        agregaEncabezado(getActivity(), params, "Nombre", tr, colorHeader);
        agregaEncabezado(getActivity(), params, "Categoria", tr, colorHeader);
        agregaEncabezado(getActivity(), params, "Cantidad", tr, colorHeader);
        agregaEncabezado(getActivity(), params, "Precio compra", tr, colorHeader);
        agregaEncabezado(getActivity(), params, "Precio venta", tr, colorHeader);
        tl.addView(tr);
        //
        try {
            rawConsulta = ((MainActivity) getActivity()).Consultar("Prenda", 6, false, "");
            listaProductos = new Producto[rawConsulta[0].length];
            for (int i = 0; i < rawConsulta[0].length; i++) {
                for (int t = 0; t < conteo; t++) {

                    int num = Integer.parseInt(rawConsulta[i][t]);

                    listaProductos[i] = new Producto
                            (num, rawConsulta[1][t], rawConsulta[2][t],
                                    rawConsulta[3][t], rawConsulta[4][t], rawConsulta[5][t]);

                    String[] arregloProductos = new String[6];
                    arregloProductos[0] = listaProductos[i].getId()+"";
                    arregloProductos[1] = listaProductos[i].getNombre();
                    arregloProductos[2] = listaProductos[i].getCategoria();
                    arregloProductos[3] = listaProductos[i].getExistencias();
                    arregloProductos[4] = listaProductos[i].getPrecioC();
                    arregloProductos[5] = listaProductos[i].getPrecioV();
                    agregarFila(
                            listaProductos[i].getId(),
                            listaProductos[i].getNombre(),
                            listaProductos[i].getCategoria(),
                            listaProductos[i].getExistencias(),
                            listaProductos[i].getPrecioC(),
                            listaProductos[i].getPrecioV()
                    );

                }





            }
        }catch (Exception ex){
            System.out.println("ERROR AL CONSULTAR \n"+ex.toString());
        }

        return view;
    }

    private void agregaEncabezado(FragmentActivity fragmentActivity, TableRow.LayoutParams layoutParams, String string, TableRow tr, int color) {
        tv1 = new TextView(getActivity());
        tv1.setText(string);
        tv1.setTextColor(Color.WHITE);
        //tv1.setBackgroundColor(Color.parseColor(color));
        tv1.setBackgroundResource(color);
        tv1.setTextSize(20);
        tv1.setPadding(5, 5, 5, 5);
        tv1.setGravity(CENTER_HORIZONTAL);
        tr.addView(tv1);
        tv1.setLayoutParams(layoutParams);
    }

    public void agregarFila(int id, String name, String category, String count, String buy, String sell) {

        String idd = String.valueOf(id);
        tr = new TableRow(getActivity());
        tr.setTag(String.valueOf(id));


        TableRow.LayoutParams params = new TableRow.LayoutParams(80, ViewGroup.LayoutParams.MATCH_PARENT);
        agregaCelda(getActivity(), params, idd, tr, getColorFondo(color), CENTER_HORIZONTAL);
        params = new TableRow.LayoutParams(250, TableRow.LayoutParams.MATCH_PARENT);
        agregaCelda(getActivity(), params, name, tr, getColorFondo(color),CENTER);
        params = new TableRow.LayoutParams(80, ViewGroup.LayoutParams.MATCH_PARENT);
        agregaCelda(getActivity(), params, category, tr, getColorFondo(color),CENTER);
        agregaCelda(getActivity(), params, count, tr, getColorFondo(color),CENTER);
        agregaCelda(getActivity(), params, buy, tr, getColorFondo(color),CENTER);
        agregaCelda(getActivity(), params, sell, tr, getColorFondo(color),CENTER);

        tl.addView(tr);
        color = !color;

    }

    private void agregaCelda(FragmentActivity fragmentActivity, TableRow.LayoutParams layoutParams, String string, TableRow tr, int color, int gravedad) {
        tv1 = new TextView(fragmentActivity);
        tv1.setText(string);
        tv1.setTextColor(Color.BLACK);
        //tv1.setBackgroundColor(Color.parseColor(color));
        tv1.setBackgroundResource(color);
        tv1.setTextSize(20);
        tv1.setPadding(5, 5, 5, 5);
        tv1.setGravity(CENTER_HORIZONTAL);
        tr.addView(tv1);
        tv1.setLayoutParams(layoutParams);
    }

    private int getColorFondo(boolean color) {
        //String acolor = color ? "#ffe5ad" : "#fff2d6";
        return color ? R.drawable.style_librocontable_u : R.drawable.style_librocontable_d;
    }

}