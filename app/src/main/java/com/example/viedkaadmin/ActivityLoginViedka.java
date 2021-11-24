package com.example.viedkaadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class ActivityLoginViedka extends AppCompatActivity {

    private Button btnLogin;
    private TextInputEditText txtContra;
    private String contraIngresada;
    private TextInputLayout inputContra;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_viedka);
        btnLogin = findViewById(R.id.btnLogin);
        txtContra = findViewById(R.id.textInputEditText_contra);
        inputContra = findViewById(R.id.textInputLayout_contra);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    contraIngresada= txtContra.getText().toString();
                    if(contraIngresada.equals("viedka")){
                        startActivity(new Intent(ActivityLoginViedka.this, MainActivity.class));
                        finish();
                    } else {
                        inputContra.setError("Contraseña incorrecta");

                    }
            }
        });

    }
}