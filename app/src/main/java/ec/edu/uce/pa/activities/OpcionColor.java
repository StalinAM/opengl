package ec.edu.uce.pa.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import ec.edu.uce.pa.R;


public class OpcionColor extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_pintar_pantalla);


        Button btnPintarPantalla = findViewById(R.id.btnTocarPantalla);
        btnPintarPantalla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), TocaPantalla.class);
                startActivity(intent);
                finish();
            }
        });

        Button btnGirarPantalla = findViewById(R.id.btnGirarPantalla);
        btnGirarPantalla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), GiraPantalla.class);
                startActivity(intent);
                finish();
            }
        });

        //Regresar a RegresarAPaginaUnoVersionUno1
        Button regresarAPaginaUnoVersionUno1 = findViewById(R.id.btnRegresarAPaginaUnoVersionUno1);
        regresarAPaginaUnoVersionUno1 .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), PaginaUnoVersionUno.class);
                startActivity(intent);
                finish();
            }
        });


    }
    //Regresar a RegresarAPaginaUnoVersionUno1
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, PaginaUnoVersionUno.class);
        startActivity(intent);


    }

}

