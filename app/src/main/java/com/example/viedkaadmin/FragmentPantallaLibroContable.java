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
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class FragmentPantallaLibroContable extends Fragment {


    private String[][] rawConsulta;
    TableLayout tl;
    TableRow tr;
    TextView tv1;
    private boolean color = false;
    int sumando = 0, restando = 0;

    public FragmentPantallaLibroContable() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static FragmentPantallaLibroContable newInstance(String param1, String param2) {
        FragmentPantallaLibroContable fragment = new FragmentPantallaLibroContable();
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
        View view = inflater.inflate(R.layout.fragment_pantalla_libro_contable, null, false);

        tl = (TableLayout) view.findViewById(R.id.tb_librocontable);

        TableRow.LayoutParams params = new TableRow.LayoutParams(170, TableRow.LayoutParams.WRAP_CONTENT);
        tr = new TableRow(getActivity());
        int colorHeader=R.drawable.style_librocontable_h;
        agregaEncabezado(getActivity(), params, "FECHA", tr, colorHeader);
        agregaEncabezado(getActivity(), params, "CONCEPTO", tr, colorHeader);
        agregaEncabezado(getActivity(), params, "MONTO", tr, colorHeader);
        tl.addView(tr);

        try {
            rawConsulta = ((MainActivity) getActivity()).Consultar("Movimientos", 12, true, "Tipo='Ingreso'");
            Contable[] listaContable = new Contable[rawConsulta[1].length];
            for (int i = 0; i < rawConsulta[1].length; i++) {
                listaContable[i] = new Contable(
                        rawConsulta[9][i], //FECHA
                        rawConsulta[1][i], //CONCEPTO
                        null,
                        rawConsulta[5][i], //ING/EGR
                        null);//TOTAL
                agregarFila(
                        listaContable[i].getFecha(),
                        listaContable[i].getConcepto(),
                        listaContable[i].getMonto(),
                        listaContable[i].getTotal(),
                        true
                );
                sumando = sumando + Integer.parseInt(listaContable[i].getMonto());
            }
            agregarPie("+$"+(sumando),0,0);
        }catch (Exception ex){
            System.out.println("ERROR AL CONSULTAR \n"+ex.toString());
        }

        try {
            rawConsulta = ((MainActivity) getActivity()).Consultar("Movimientos", 12, true, "Tipo='Egreso'");
            Contable[] listaContable = new Contable[rawConsulta[1].length];
            for (int i = 0; i < rawConsulta[1].length; i++) {
                listaContable[i] = new Contable(
                        rawConsulta[9][i], //FECHA
                        rawConsulta[1][i], //CONCEPTO
                        null,
                        rawConsulta[5][i], //ING/EGR
                        null);//TOTAL
                agregarFila(
                        listaContable[i].getFecha(),
                        listaContable[i].getConcepto(),
                        listaContable[i].getMonto(),
                        listaContable[i].getTotal(),
                        false
                );
                restando = restando + Integer.parseInt(listaContable[i].getMonto());
            }
            agregarPie("-$"+(restando),1,0);
            agregarPie("",5,5);
            agregarPie("$"+(sumando-restando),2,0);
        }catch (Exception ex){
            System.out.println("ERROR AL CONSULTAR \n"+ex.toString());
        }

        return view;
    }

    private void agregaEncabezado(FragmentActivity fragmentActivity, TableRow.LayoutParams layoutParams, String string, TableRow tr, int color) {
        tv1 = new TextView(getActivity());
        tv1.setText(string);
        tv1.setTextColor(Color.WHITE);
        tv1.setBackgroundResource(color);
        tv1.setTextSize(20);
        tv1.setPadding(5, 5, 5, 5);
        tv1.setGravity(CENTER_HORIZONTAL);
        tr.addView(tv1);
        tv1.setLayoutParams(layoutParams);
    }

    public void agregarFila(String fecha, String concepto, String monto, String total, Boolean value) {

        String fechas = String.valueOf(fecha);
        tr = new TableRow(getActivity());
        tr.setTag(String.valueOf(fecha));

        if(value==true) {

            TableRow.LayoutParams params = new TableRow.LayoutParams(80, ViewGroup.LayoutParams.MATCH_PARENT);
            agregaCelda(getActivity(), params, fechas, tr, getColorFondo(color), CENTER_HORIZONTAL);
            params = new TableRow.LayoutParams(250, TableRow.LayoutParams.MATCH_PARENT);
            agregaCelda(getActivity(), params, concepto, tr, getColorFondo(color), CENTER);
            params = new TableRow.LayoutParams(80, ViewGroup.LayoutParams.MATCH_PARENT);
            agregaCelda(getActivity(), params, "+"+monto, tr, R.drawable.style_green, CENTER);
            params = new TableRow.LayoutParams(80, ViewGroup.LayoutParams.MATCH_PARENT);

            tl.addView(tr);
            color = !color;
        } else {
            TableRow.LayoutParams params = new TableRow.LayoutParams(80, ViewGroup.LayoutParams.MATCH_PARENT);
            agregaCelda(getActivity(), params, fechas, tr, getColorFondo(color), CENTER_HORIZONTAL);
            params = new TableRow.LayoutParams(250, TableRow.LayoutParams.MATCH_PARENT);
            agregaCelda(getActivity(), params, concepto, tr, getColorFondo(color), CENTER);
            params = new TableRow.LayoutParams(80, ViewGroup.LayoutParams.MATCH_PARENT);
            agregaCelda(getActivity(), params, "-"+monto, tr, R.drawable.style_red, CENTER);
            params = new TableRow.LayoutParams(80, ViewGroup.LayoutParams.MATCH_PARENT);

            tl.addView(tr);
            color = !color;
        }


    }

    public void agregarPie(String TOTAL, int valor, int celda) {

        int colorHeader2=R.drawable.style_pie;
        TableRow.LayoutParams params = new TableRow.LayoutParams(80, ViewGroup.LayoutParams.MATCH_PARENT);


        switch (valor){
            case 0: colorHeader2=Color.parseColor("#D6FCCC");
                break;
            case 1: colorHeader2=Color.parseColor("#F8DCDC");
                break;
            case 2: colorHeader2=Color.parseColor("#FFC546");
                break;
            case 5: colorHeader2=0;
                break;
        }

        switch (celda){
            case 0:
                tr = new TableRow(getActivity());
                agregaCelda2(getActivity(), params, "SUBTOTAL", tr, colorHeader2, CENTER_HORIZONTAL);
                agregaCelda2(getActivity(), params, "", tr, colorHeader2, CENTER_HORIZONTAL);
                agregaCelda2(getActivity(), params, TOTAL, tr, colorHeader2, CENTER_HORIZONTAL);

                tl.addView(tr);
                color = !color;
                break;
            case 5:
                tr = new TableRow(getActivity());
                agregaCelda2(getActivity(), params, "", tr, colorHeader2, CENTER_HORIZONTAL);
                agregaCelda2(getActivity(), params, "", tr, colorHeader2, CENTER_HORIZONTAL);
                agregaCelda2(getActivity(), params, "", tr, colorHeader2, CENTER_HORIZONTAL);

                tl.addView(tr);
                color = !color;
                break;
        }
    }


    private void agregaCelda(FragmentActivity fragmentActivity, TableRow.LayoutParams layoutParams, String string, TableRow tr, int color, int gravedad) {
        tv1 = new TextView(fragmentActivity);
        tv1.setText(string);
        tv1.setTextColor(Color.BLACK);
        tv1.setBackgroundResource(color);
        tv1.setTextSize(20);
        tv1.setPadding(5, 5, 5, 5);
        tv1.setGravity(CENTER_HORIZONTAL);
        tr.addView(tv1);
        tv1.setLayoutParams(layoutParams);
    }

    private void agregaCelda2(FragmentActivity fragmentActivity, TableRow.LayoutParams layoutParams, String string, TableRow tr, int color, int celda) {
        tv1 = new TextView(fragmentActivity);
        tv1.setText(string);
        tv1.setTextColor(Color.BLACK);
        tv1.setBackgroundColor(color);
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