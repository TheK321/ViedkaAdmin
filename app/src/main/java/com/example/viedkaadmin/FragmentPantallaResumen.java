package com.example.viedkaadmin;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentPantallaResumen#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentPantallaResumen extends Fragment {


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String fecha1,fecha2;
    private MaterialAutoCompleteTextView fecha;
    private MaterialTextView txtventastotales, txtingresostotales;
    private String [] [] rawConsulta;
    private TextView[] titulos = new TextView[5], subtittulos = new TextView[5], textos = new TextView[5],titulosins = new TextView[5], subtittulosins = new TextView[5], textosins = new TextView[5];


    public FragmentPantallaResumen() {
        // Required empty public constructor
    }

    public static FragmentPantallaResumen newInstance(String param1, String param2) {
        FragmentPantallaResumen fragment = new FragmentPantallaResumen();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
           mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_pantalla_resumen, container, false);

        fecha = view.findViewById(R.id.textInputEditText_fecha);
        txtventastotales = view.findViewById(R.id.textViewventastotales);
        txtingresostotales = view.findViewById(R.id.textViewIngresostotales);
        for(int i=0;i<5;i++){
            titulos[i] =(TextView)view.findViewById(getResources().getIdentifier("titulomasvendido"+(i+1), "id", getActivity().getPackageName()));
            subtittulos[i] = (TextView)view.findViewById(getResources().getIdentifier("subtitulomasvendido"+(i+1), "id", getActivity().getPackageName()));
            textos[i] = (TextView)view.findViewById(getResources().getIdentifier("textomasvendido"+(i+1), "id", getActivity().getPackageName()));
            titulosins[i] =(TextView)view.findViewById(getResources().getIdentifier("textviewtituloingresadas"+(i+1), "id", getActivity().getPackageName()));
            subtittulosins[i] = (TextView)view.findViewById(getResources().getIdentifier("textviewsubtituloingresadas"+(i+1), "id", getActivity().getPackageName()));
            textosins[i] = (TextView)view.findViewById(getResources().getIdentifier("textviewtextoingresadas"+(i+1), "id", getActivity().getPackageName()));
        }

        Date date = new java.util.Date();
        long datetime = date.getTime();
        System.out.println(datetime);
        DateFormat simple = new SimpleDateFormat("yyyyMMdd");
        Date result = new Date(datetime);
        simple.setTimeZone(TimeZone.getTimeZone("America/Mexico_City"));
        System.out.println(simple.format(result));
        fecha1=fecha2=simple.format(result);
        llenarmasvendidos(view);

        fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MaterialDatePicker.Builder materialDateBuilder = MaterialDatePicker.Builder.dateRangePicker();
                materialDateBuilder.setTitleText("Rango");
                 MaterialDatePicker materialDatePicker = materialDateBuilder.build();
                materialDatePicker.show(getActivity().getSupportFragmentManager(), "MATERIAL_DATE_PICKER");
                materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
                    @Override
                    public void onPositiveButtonClick(Object selection) {
                        String cadenaS = materialDatePicker.getSelection().toString();
                        cadenaS = cadenaS.replaceAll("\\D+","");
                        System.out.println(cadenaS);
                        System.out.println(cadenaS.length());
                        String primerafecha=cadenaS.substring(0,10);
                        System.out.println("prim "+primerafecha);
                        String segundafecha=cadenaS.substring(13,23);
                        System.out.println("segn "+primerafecha);
                        String textofecha="";
                        DateFormat simple = new SimpleDateFormat("dd-MMM-yyyy");
                        Date result = new Date(Long.parseLong(primerafecha)*1000+(1000 * 60 * 60 * 24));
                        System.out.println("fecha1 "+result.toString());
                        simple.setTimeZone(TimeZone.getTimeZone("America/Mexico_City"));
                        textofecha+=simple.format(result)+" al ";
                        simple = new SimpleDateFormat("yyyyMMdd");
                        simple.setTimeZone(TimeZone.getTimeZone("America/Mexico_City"));
                        fecha1=simple.format(result);
                        simple = new SimpleDateFormat("dd-MMM-yyyy");
                        simple.setTimeZone(TimeZone.getTimeZone("America/Mexico_City"));
                        result = new Date(Long.parseLong(segundafecha)*1000+(1000 * 60 * 60 * 24));
                        System.out.println("fecha2 "+result.toString());
                        textofecha+=simple.format(result);
                        simple = new SimpleDateFormat("yyyyMMdd");
                        simple.setTimeZone(TimeZone.getTimeZone("America/Mexico_City"));
                        fecha2=simple.format(result);
                        fecha.setText(textofecha);

                        llenarmasvendidos(view);

                    }
                });


            }

        });

        ListView lv = view.findViewById(R.id.listviewgraficas);

        ArrayList<ChartItem> list = new ArrayList<>();

        // 30 items
        for (int i = 0; i < 10; i++) {
                list.add(new BarChartItem(generateDataBar(i + 1), getActivity().getApplicationContext()));
        }

        ChartDataAdapter cda = new ChartDataAdapter(getActivity().getApplicationContext(), list);
        lv.setAdapter(cda);

                // Inflate the layout for this fragment
        return view;
    }

    private void llenarmasvendidos(View view){
        //llenar mas vendidos
        try {
            String queryrawconsulta = "select concepto," +
                    " categoria," +
                    " preciouni," +
                    " SUM(cantidad) as totcant," +
                    " SUM(total) as totot," +
                    " idPrenda " +
                    "from Movimientos where " +
                    "fecha>="+fecha1+" and" +
                    " fecha<="+fecha2+"" +
                    " group by idPrenda" +
                    " order by totcant desc" +
                    " limit 5 ;";
            System.out.println(queryrawconsulta);
            rawConsulta = ((MainActivity) getActivity()).Consultarcustom(queryrawconsulta,6);
            for (int i = 0; i < 5; i++) {
                System.out.println("lenght de rawconsulta es "+rawConsulta[0].length);
                if(i<rawConsulta[0].length & rawConsulta[0].length!=0){
                    System.out.println("raw "+rawConsulta[0][i]+rawConsulta[1][i]);
                    String titulo=rawConsulta[0][i] + "/" + rawConsulta[1][i];
                    String subtitulo=rawConsulta[3][i]+" unidades vendidas";
                    String texto="Ingresos totales obtenidos por esta prenda $"+rawConsulta[4][i];
                    titulos[i].setText(titulo);
                    subtittulos[i].setText(subtitulo);
                    textos[i].setText(texto);
                } else {
                    titulos[i].setText("Sin datos");
                    subtittulos[i].setText("Sin datos");
                    textos[i].setText("Por favor seleccione otro periodo");
                }
            }
            llenartotvendidosarriba(view);

             queryrawconsulta = "select concepto," +
                    " categoria," +
                    " preciouni," +
                    " SUM(cantidad) as totcant," +
                    " SUM(total) as totot," +
                    " idPrenda " +
                    "from Movimientos where " +
                    "fecha>="+fecha1+" and" +
                    " fecha<="+fecha2+"" +
                    " group by idPrenda" +
                    " order by idPrenda desc" +
                    " limit 5 ;";
            System.out.println(queryrawconsulta);
            rawConsulta = ((MainActivity) getActivity()).Consultarcustom(queryrawconsulta,6);
            for (int i = 0; i < 5; i++) {
                System.out.println("lenght de rawconsulta es "+rawConsulta[0].length);
                if(i<rawConsulta[0].length & rawConsulta[0].length!=0){
                    System.out.println("raw "+rawConsulta[0][i]+rawConsulta[1][i]);
                    String titulo=rawConsulta[0][i] + "/" + rawConsulta[1][i];
                    String subtitulo=rawConsulta[3][i]+" unidades vendidas";
                    String texto="Ingresos totales obtenidos por esta prenda $"+rawConsulta[4][i];
                    titulosins[i].setText(titulo);
                    subtittulosins[i].setText(subtitulo);
                    textosins[i].setText(texto);
                } else {
                    titulosins[i].setText("Sin datos");
                    subtittulosins[i].setText("Sin datos");
                    textosins[i].setText("Por favor seleccione otro periodo");
                }
            }
        } catch (Exception ex){
            for (int i = 0; i < 5; i++) {
                titulos[i].setText("Sin datos");
                subtittulos[i].setText("Sin datos");
                textos[i].setText("Por favor seleccione otro periodo");
            }

            txtventastotales.setText("Sin ventas");
            txtingresostotales.setText("Sin ventas");
            System.out.println(ex.toString());
        }
    }

    private void llenartotvendidosarriba(View view){
        //llenar ventastotales arriba
        try {
            String querysel = "select SUM(cantidad) as totcant, SUM(total) as totot from Movimientos where fecha between "+fecha1+" and "+fecha2;
            rawConsulta = ((MainActivity) getActivity()).Consultarcustom(querysel,2);
            for (int i = 0; i < rawConsulta[1].length; i++) {
                System.out.println("raw "+rawConsulta[0][i]+rawConsulta[1][i]);
                String titulo=rawConsulta[0][i] + "/" + rawConsulta[1][i];
                txtventastotales.setText(rawConsulta[0][i]);
                txtingresostotales.setText(rawConsulta[1][i]);
            }
        } catch (Exception ex){
            System.out.println(ex.toString());
        }
    }


    private BarData generateDataBar(int cnt) {

        ArrayList<BarEntry> entries = new ArrayList<>();

        for (int i = 0; i < 12; i++) {
            entries.add(new BarEntry(i, (int) (Math.random() * 70) + 30));
        }

        BarDataSet d = new BarDataSet(entries, "New DataSet " + cnt);
        d.setColors(ColorTemplate.VORDIPLOM_COLORS);
        d.setHighLightAlpha(255);

        BarData cd = new BarData(d);
        cd.setBarWidth(0.9f);

        cd.setValueTextSize(14);
        return cd;
    }

    private class ChartDataAdapter extends ArrayAdapter<ChartItem> {

        ChartDataAdapter(Context context, List<ChartItem> objects) {
            super(context, 0, objects);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, @NonNull ViewGroup parent) {
            //noinspection ConstantConditions
            return getItem(position).getView(position, convertView, getContext());
        }

        @Override
        public int getItemViewType(int position) {
            // return the views type
            ChartItem ci = getItem(position);
            return ci != null ? ci.getItemType() : 0;
        }

        @Override
        public int getViewTypeCount() {
            return 3; // we have 3 different item-types
        }
    }





}

