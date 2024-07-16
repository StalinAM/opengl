package ec.edu.uce.pa.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import ec.edu.uce.pa.R;

public class VersionUnoIndice extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_escoger_pagina_version_uno);

        Button pagina1 = findViewById(R.id.btnPagina1V1);
        pagina1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), PaginaUnoVersionUno.class);
                    startActivity(intent);
                    finish();
            }
        });

        Button pagina2 = findViewById(R.id.btnPagina2V1);
        pagina2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), PaginaDosVersionUno.class);
                startActivity(intent);
                finish();
            }
        });

        //Refresar a EscogerVersion
        Button regresarAEscogerVersion=findViewById(R.id.btnRegresarAEscogerVersion);
        regresarAEscogerVersion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), EscogerVersion.class);
                startActivity(intent);
                finish();
            }
        });
    }

    //Refresar a EscogerVersion
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, EscogerVersion.class);
        startActivity(intent);
        finish();
    }
}
