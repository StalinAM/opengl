package ec.edu.uce.pa.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import ec.edu.uce.pa.R;


public class GirarCubo extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_mover_camara_mundo);


        Button btnMoverCarama = findViewById(R.id.btnMoverCarama);
        btnMoverCarama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), GiroCamara.class);
                startActivity(intent);
                finish();
            }
        });

        Button btnMoverMundo = findViewById(R.id.btnMoverMundo);
        btnMoverMundo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), GiroMundo.class);
                startActivity(intent);
                finish();
            }
        });

        //Regresar a RegresarAPaginaUnoVersionUno1
        Button regresarAPaginaUnoVersionUno2 = findViewById(R.id.btnRegresarAPaginaUnoVersionUno2);
        regresarAPaginaUnoVersionUno2.setOnClickListener(new View.OnClickListener() {
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

