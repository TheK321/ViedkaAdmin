package com.example.viedkaadmin;

import static android.view.Gravity.CENTER_HORIZONTAL;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textfield.TextInputEditText;

public class FragmentPantallaAgregarReporte extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private TableLayout tl;
    private TableRow tr;
    private TextView tv1;
    private TextInputEditText txtArticulo, txtprecio, txtcantidad;
    private SwitchMaterial ingreso;
    private ScrollView scrollView;
    private AutoCompleteTextView nombre;
    private boolean color = false;
    private String [] [] rawConsulta;
    private String[] nombres;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentPantallaAgregarReporte() {

    }

    public static FragmentPantallaAgregarReporte newInstance(String param1, String param2) {
        FragmentPantallaAgregarReporte fragment = new FragmentPantallaAgregarReporte();
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

        View view = inflater.inflate(R.layout.fragment_pantalla_agregar_reporte, container, false);


        tl = (TableLayout) view.findViewById(R.id.tablaOperacionesReporte);
        txtArticulo = view.findViewById(R.id.textInputEditText_articulo);
        txtcantidad = view.findViewById(R.id.textInputEditText_cantidad);
        txtprecio = view.findViewById(R.id.textInputEditText_precio);
        ingreso = view.findViewById(R.id.switchMaterial_ingreso);
        Button button = view.findViewById(R.id.imageButton_agregar);
        Button verTrabs = view.findViewById(R.id.Button_trabajadores);
        scrollView = view.findViewById(R.id.scrollView2);
        nombre = view.findViewById(R.id.autoCompleteTextView_nombre);

        try {
            rawConsulta = ((MainActivity) getActivity()).Consultar("Trabajadores",2,false,"");
            nombres = new String[rawConsulta[1].length];
            for (int i = 0; i < rawConsulta[1].length; i++) {
                System.out.println(rawConsulta[0][i]+rawConsulta[1][i]);
                nombres[i] = rawConsulta[1][i];
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.select_dialog_item, nombres);
            nombre.setAdapter(adapter);
        } catch (Exception ex){
            System.out.println(ex.toString());
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isEmpty(txtArticulo) && !isEmpty(txtcantidad) & !isEmpty(txtprecio)) {
                    agregarFila();
                    scrollView.fullScroll(View.FOCUS_DOWN);
                } else {
                    Toast.makeText(getActivity(), "Algún campo está vacío", (short) 1000).show();
                }
            }
        });

        verTrabs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_container,
                        new FragmentTrabajadores()).commit();
            }
        });
        ingreso.setOnCheckedChangeListener((buttonView, isChecked) -> {
            ingreso.setText(ingreso.isChecked() ? "Ingreso" : "Egreso");
        });

        TableRow.LayoutParams params = new TableRow.LayoutParams(150, TableRow.LayoutParams.WRAP_CONTENT);
        tr = new TableRow(getActivity());
        //String colorHeader="#84477F";
        String colorHeader = "#db9600";
        agregaEncabezado(getActivity(), params, "Articulo", tr, colorHeader);
        agregaEncabezado(getActivity(), params, "Precio", tr, colorHeader);
        agregaEncabezado(getActivity(), params, "Cantidad", tr, colorHeader);
        agregaEncabezado(getActivity(), params, "Total", tr, colorHeader);
        agregaEncabezado(getActivity(), params, "Tipo", tr, colorHeader);
        tl.addView(tr);
        return view;
    }

    public void agregarFila() {

        TableRow.LayoutParams params = new TableRow.LayoutParams(150, TableRow.LayoutParams.WRAP_CONTENT);

        tr = new TableRow(getActivity());
        agregaCelda(getActivity(), params, txtArticulo.getText().toString(), tr, getColorFondo(color));
        agregaCelda(getActivity(), params, txtprecio.getText().toString(), tr, getColorFondo(color));
        agregaCelda(getActivity(), params, txtcantidad.getText().toString(), tr, getColorFondo(color));
        agregaCelda(getActivity(), params, String.valueOf((Integer.parseInt(txtcantidad.getText().toString()) * Integer.parseInt(txtprecio.getText().toString()))).toString(), tr, getColorFondo(color));
        agregaCelda(getActivity(), params, (ingreso.isChecked() ? "Ingreso" : "Egreso").toString(), tr, getColorFondo(color));

        tl.addView(tr);

        color = !color;

    }

    private String getColorFondo(boolean color) {
        String acolor = color ? "#ffe5ad" : "#fff2d6";
        return acolor;
    }

    private void agregaCelda(FragmentActivity fragmentActivity, TableRow.LayoutParams layoutParams, String string, TableRow tr, String color) {
        tv1 = new TextView(fragmentActivity);
        tv1.setText(string);
        tv1.setTextColor(Color.BLACK);
        tv1.setBackgroundColor(Color.parseColor(color));
        tv1.setTextSize(20);
        tv1.setPadding(5, 5, 5, 5);
        tv1.setGravity(CENTER_HORIZONTAL);
        tr.addView(tv1);
        tv1.setLayoutParams(layoutParams);
    }

    private void agregaEncabezado(FragmentActivity fragmentActivity, TableRow.LayoutParams layoutParams, String string, TableRow tr, String color) {
        tv1 = new TextView(getActivity());
        tv1.setText(string);
        tv1.setTextColor(Color.WHITE);
        tv1.setBackgroundColor(Color.parseColor(color));
        tv1.setTextSize(20);
        tv1.setPadding(5, 5, 5, 5);
        tv1.setGravity(CENTER_HORIZONTAL);
        tr.addView(tv1);
        tv1.setLayoutParams(layoutParams);
    }

    private boolean isEmpty(TextInputEditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }


}