package com.example.viedkaadmin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.navigationrail.NavigationRailView;

public class FragmentPantallaVentas extends Fragment {
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
        return inflater.inflate(R.layout.fragment_pantalla_ventas, null, false);
    }
}