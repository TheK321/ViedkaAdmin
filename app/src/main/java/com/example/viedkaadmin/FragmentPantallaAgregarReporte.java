package com.example.viedkaadmin;

import android.graphics.Color;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentPantallaAgregarReporte#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentPantallaAgregarReporte extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private TableLayout tl;
    private TableRow tr;
    private TextView tv1;
    private TextInputEditText txtArticulo,txtprecio,txtcantidad,txttotal;
    private boolean color=false;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentPantallaAgregarReporte() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentPantallaAgregarReporte.
     */
    // TODO: Rename and change types and number of parameters
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pantalla_agregar_reporte, container, false);
         tl = (TableLayout) view.findViewById(R.id.tablaOperacionesReporte);
         txtArticulo= view.findViewById(R.id.textInputEditText_articulo);
         txtcantidad=view.findViewById(R.id.textInputEditText_cantidad);
         txtprecio=view.findViewById(R.id.textInputEditText_precio);
         txttotal=view.findViewById(R.id.textInputEditText_total);
        ImageButton button =  view.findViewById(R.id.imageButton_agregar);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                agregarFila();
            }
        });

        TableRow.LayoutParams params = new TableRow.LayoutParams(150, TableRow.LayoutParams.WRAP_CONTENT);
        tr= new TableRow(getActivity());
        tv1 = new TextView(getActivity());
        tv1.setText("Artículo");
        tv1.setTextColor(Color.WHITE);
        tv1.setBackgroundColor( Color.parseColor("#84477F"));
        tv1.setTextSize(20);
        tv1.setPadding(5, 5, 5, 5);
        tr.addView(tv1);
        tv1.setLayoutParams(params);
        tv1 = new TextView(getActivity());
        tv1.setText("Precio");
        tv1.setTextColor(Color.WHITE);
        tv1.setBackgroundColor( Color.parseColor("#84477F"));
        tv1.setTextSize(20);
        tv1.setPadding(5, 5, 5, 5);
        tr.addView(tv1);
        tv1.setLayoutParams(params);
        tv1 = new TextView(getActivity());
        tv1.setText("Cantidad");
        tv1.setTextColor(Color.WHITE);
        tv1.setBackgroundColor( Color.parseColor("#84477F"));
        tv1.setTextSize(20);
        tv1.setPadding(5, 5, 5, 5);
        tr.addView(tv1);
        tv1.setLayoutParams(params);
        tv1 = new TextView(getActivity());
        tv1.setText("Total");
        tv1.setTextColor(Color.WHITE);
        tv1.setBackgroundColor( Color.parseColor("#84477F"));
        tv1.setTextSize(20);
        tv1.setPadding(5, 5, 5, 5);
        tr.addView(tv1);
        tv1.setLayoutParams(params);
        tl.addView(tr);
        return view;
    }

    public void agregarFila(){

        TableRow.LayoutParams params = new TableRow.LayoutParams(150, TableRow.LayoutParams.WRAP_CONTENT);

        tr= new TableRow(getActivity());
        tv1 = new TextView(getActivity());
        tv1.setText(txtArticulo.getText().toString());
        tv1.setTextColor(Color.BLACK);
        tv1.setBackgroundColor(Color.parseColor((color) ? "#deb7eb" : "#ffffff"));
        tv1.setTextSize(20);
        tv1.setPadding(5, 5, 5, 5);
        tr.addView(tv1);
        tv1.setLayoutParams(params);
        tv1 = new TextView(getActivity());
        tv1.setText(txtprecio.getText().toString());
        tv1.setTextColor(Color.BLACK);
        tv1.setBackgroundColor(Color.parseColor((color) ? "#deb7eb" : "#ffffff"));
        tv1.setTextSize(20);
        tv1.setPadding(5, 5, 5, 5);
        tr.addView(tv1);
        tv1.setLayoutParams(params);
        tv1 = new TextView(getActivity());
        tv1.setText(txtcantidad.getText().toString());
        tv1.setTextColor(Color.BLACK);
        tv1.setBackgroundColor(Color.parseColor((color) ? "#deb7eb" : "#ffffff"));
        tv1.setTextSize(20);
        tv1.setPadding(5, 5, 5, 5);
        tr.addView(tv1);
        tv1.setLayoutParams(params);
        tv1 = new TextView(getActivity());
        tv1.setText(String.valueOf((Integer.parseInt(txtcantidad.getText().toString())*Integer.parseInt(txtprecio.getText().toString()))));
        tv1.setTextColor(Color.BLACK);
        tv1.setBackgroundColor(Color.parseColor((color) ? "#deb7eb" : "#ffffff"));
        tv1.setTextSize(20);
        tv1.setPadding(5, 5, 5, 5);
        tr.addView(tv1);
        tv1.setLayoutParams(params);
        tl.addView(tr);
        color=!color;

    }



}