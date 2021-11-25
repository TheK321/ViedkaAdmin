package com.example.viedkaadmin;

import static android.view.Gravity.CENTER_HORIZONTAL;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Trabajadores#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Trabajadores extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TableLayout tl;
    private TableRow tr;
    private TextView tv1;
    private TextInputEditText txtNombre;
    private ScrollView scrollView;
    private boolean color=false;
    private Trabajador[] listaTrabajadores;
    private String [] [] rawConsulta;

    public Trabajadores() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Trabajadores.
     */
    // TODO: Rename and change types and number of parameters
    public static Trabajadores newInstance(String param1, String param2) {
        Trabajadores fragment = new Trabajadores();
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
        View view = inflater.inflate(R.layout.fragment_trabajadores, container, false);
        tl = (TableLayout) view.findViewById(R.id.tablaMostrarTrabajadores);
        Button button =  view.findViewById(R.id.Button_agregar);
        txtNombre=view.findViewById(R.id.textInputEditText_nombreTrab);
        scrollView = view.findViewById(R.id.scrollView2);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(!isEmpty(txtNombre)){
                    agregarTrabajador();
                    scrollView.fullScroll(View.FOCUS_DOWN);
                } else {
                    Toast.makeText(getActivity(), "Algún campo está vacío", (short)1000).show();
                }
            }
        });

        TableRow.LayoutParams params = new TableRow.LayoutParams(150, TableRow.LayoutParams.WRAP_CONTENT);
        tr= new TableRow(getActivity());
        String colorHeader = "#db9600";
        agregaEncabezado(getActivity(), params, "ID", tr, colorHeader);
        agregaEncabezado(getActivity(), params, "Nombre", tr, colorHeader);
        tl.addView(tr);
        try {
            rawConsulta = ((MainActivity) getActivity()).Consultar("Trabajadores",2,false,"");
            listaTrabajadores= new Trabajador[rawConsulta[1].length];
            for (int i = 0; i <= rawConsulta[1].length; i++) {
                System.out.println(rawConsulta[0][i]+rawConsulta[1][i]);
                listaTrabajadores[i] = new Trabajador(Integer.parseInt(rawConsulta[0][i]), rawConsulta[1][i]);
                agregarFila(listaTrabajadores[i].getId(), listaTrabajadores[i].getNombre());
            }
        } catch (Exception ex){
            System.out.println(ex.toString());
        }


        return view;
    }

    public void agregarFila(int id, String nombre) {

        TableRow.LayoutParams params = new TableRow.LayoutParams(150, TableRow.LayoutParams.WRAP_CONTENT);

        tr = new TableRow(getActivity());
        agregaCelda(getActivity(), params, String.valueOf(id), tr, getColorFondo(color));
        agregaCelda(getActivity(), params, nombre, tr, getColorFondo(color));
        tl.addView(tr);

        color = !color;

    }
    public void agregarTrabajador() {
        String[] datos= new String[1];
        String[] columnas = new String[1];
        columnas[0]="NombreTrab";
        datos[0] = txtNombre.getText().toString();
        System.out.println(datos[0]+" "+columnas[0]);
        long trabajadores = ((MainActivity) getActivity()).Insertar(columnas, datos, "Trabajadores");
        System.out.println(trabajadores);
        if(trabajadores == -1){
            Toast.makeText(this.getContext(),"Error al insertar",(short)1000);
        } else {
            Toast.makeText(this.getContext(),String.valueOf(trabajadores),(short)1000);
            /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                getActivity().getSupportFragmentManager().beginTransaction().detach(this).commitNow();
                getActivity().getSupportFragmentManager().beginTransaction().attach(this).commitNow();
            } else {
                getActivity().getSupportFragmentManager().beginTransaction().detach(this).attach(this).commit();
            }*/
        }
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