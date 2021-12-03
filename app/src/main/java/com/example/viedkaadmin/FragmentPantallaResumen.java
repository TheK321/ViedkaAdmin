package com.example.viedkaadmin;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ListView;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputEditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    private MaterialAutoCompleteTextView fecha;

    public FragmentPantallaResumen() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentPantallaResumen.
     */
    // TODO: Rename and change types and number of parameters
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
                        result = new Date(Long.parseLong(segundafecha)*1000+(1000 * 60 * 60 * 24));
                        System.out.println("fecha2 "+result.toString());
                        textofecha+=simple.format(result);
                        fecha.setText(textofecha);
                    }
                });


            }

        });
                // Inflate the layout for this fragment
        return view;
    }


}