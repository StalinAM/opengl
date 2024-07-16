package ec.edu.uce.pa.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import ec.edu.uce.pa.R;

public class EscogerVersion extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_escoger_version_opengl);

        Button OpenglES1 = findViewById(R.id.btnOpenglES1);
        OpenglES1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), VersionUnoIndice.class);
                startActivity(intent);
                finish();
            }
        });


        Button OpenglES2 = findViewById(R.id.btnOpenglES2);
        OpenglES2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), VersionDos.class);
                startActivity(intent);
                finish();
            }
        });

        //Regresar a PrincipalActivity
        Button RegresarAPrincipal = findViewById(R.id.btnRegresarAPrincipal);
        RegresarAPrincipal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), PrincipalActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    //Regresar a PrincipalActivity
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, PrincipalActivity.class);
        startActivity(intent);
    }


}