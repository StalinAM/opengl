package ec.edu.uce.pa.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import ec.edu.uce.pa.R;

public class PrincipalActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_principal);
        Button btnEjercicios=findViewById(R.id.btnEjercicios);
        btnEjercicios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(), EscogerVersion.class);
                startActivity(intent);
                finish();
            }
        });


        //Salir de  PrincipalActivity
        Button SalirDePrincipal=findViewById(R.id.btnSalirPrincipal);
        SalirDePrincipal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Toast.makeText(getApplicationContext(),"¡Hola de Nuevo!",Toast.LENGTH_SHORT).show();
    }
    //Salir de  PrincipalActivity
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Toast.makeText(getApplicationContext(),"¡Adiós!",Toast.LENGTH_SHORT).show();
        finish();
        System.exit(0);
    }






}