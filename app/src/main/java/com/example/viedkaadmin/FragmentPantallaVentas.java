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

public class FragmentPantallaVentas extends Fragment {

    TableLayout tl;
    TableRow tr;
    TextView tv1;
    private String [] [] rawConsulta;
    private boolean color = false;


    public FragmentPantallaVentas() {

    }

    public static FragmentPantallaVentas newInstance(String param1, String param2) {
        FragmentPantallaVentas fragment = new FragmentPantallaVentas();

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
        View view = inflater.inflate(R.layout.fragment_pantalla_ventas, null, false);
        tl = (TableLayout) view.findViewById(R.id.tableLayoutVentas);


        TableRow.LayoutParams params = new TableRow.LayoutParams(170, TableRow.LayoutParams.WRAP_CONTENT);
        tr = new TableRow(getActivity());
        int colorHeader=R.drawable.style_librocontable_h;
        agregaEncabezado(getActivity(), params, "ID", tr, colorHeader);
        agregaEncabezado(getActivity(), params, "Articulo", tr, colorHeader);
        agregaEncabezado(getActivity(), params, "Categoria", tr, colorHeader);
        params = new TableRow.LayoutParams(250, TableRow.LayoutParams.WRAP_CONTENT);
        agregaEncabezado(getActivity(), params, "Cantidad Vendida", tr, colorHeader);
        params = new TableRow.LayoutParams(140, TableRow.LayoutParams.WRAP_CONTENT);
        agregaEncabezado(getActivity(), params, "Precio", tr, colorHeader);
        agregaEncabezado(getActivity(), params, "Total", tr, colorHeader);
        tl.addView(tr);

        try {
            rawConsulta = ((MainActivity) getActivity()).Consultar("Movimientos", 12, true, "Tipo='Ingreso'");
            Movimiento[] listaMovimientos = new Movimiento[rawConsulta[1].length];
            for (int i = 0; i < rawConsulta[1].length; i++) {
                System.out.println(rawConsulta[0][i]+rawConsulta[1][i]);
                listaMovimientos[i] = new Movimiento(
                        Integer.parseInt(rawConsulta[0][i]), //ID
                        rawConsulta[1][i], //CONCEPTO
                        rawConsulta[2][i], //CATEGORIA
                        rawConsulta[3][i], //PRECIO UNITARIO
                        rawConsulta[4][i], //CANTIDAD
                        rawConsulta[5][i], //TOTAL
                        rawConsulta[6][i], //TIPO
                        rawConsulta[7][i], //SALDO ANTERIOR
                        rawConsulta[8][i], //SALDO ACTUAL
                        rawConsulta[9][i], //FECHA
                        rawConsulta[10][i],//ID EMPLEADO
                        rawConsulta[11][i]);//ID PRENDA
                    agregarFila(
                            listaMovimientos[i].getIdMovimiento(),
                            listaMovimientos[i].getConcepto(),
                            listaMovimientos[i].getCategoria(),
                            listaMovimientos[i].getCantidad(),
                            listaMovimientos[i].getPrecioUni()
                    );
            }
        }catch (Exception ex){
            System.out.println("ERROR AL CONSULTAR \n"+ex.toString());
        }
        return view;
    }

    public void agregarFila(int id, String name, String category, String count, String price) {

        String idd = String.valueOf(id);
        tr = new TableRow(getActivity());
        tr.setTag(String.valueOf(id));

        int calc = Integer.parseInt(count) * Integer.parseInt(price);
        String calcs = calc+"";

        TableRow.LayoutParams params = new TableRow.LayoutParams(80, ViewGroup.LayoutParams.MATCH_PARENT);
        agregaCelda(getActivity(), params, idd, tr, getColorFondo(color), CENTER_HORIZONTAL);
        params = new TableRow.LayoutParams(250, TableRow.LayoutParams.MATCH_PARENT);
        agregaCelda(getActivity(), params, name, tr, getColorFondo(color),CENTER);
        params = new TableRow.LayoutParams(80, ViewGroup.LayoutParams.MATCH_PARENT);
        agregaCelda(getActivity(), params, category, tr, getColorFondo(color),CENTER);
        agregaCelda(getActivity(), params, count, tr, getColorFondo(color),CENTER);
        params = new TableRow.LayoutParams(80, ViewGroup.LayoutParams.MATCH_PARENT);
        agregaCelda(getActivity(), params, price, tr, getColorFondo(color),CENTER);
        agregaCelda(getActivity(), params, calcs, tr, getColorFondo(color),CENTER);

        tl.addView(tr);
        color = !color;

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

    private int getColorFondo(boolean color) {
        //String acolor = color ? "#ffe5ad" : "#fff2d6";
        return color ? R.drawable.style_librocontable_u : R.drawable.style_librocontable_d;
    }
}