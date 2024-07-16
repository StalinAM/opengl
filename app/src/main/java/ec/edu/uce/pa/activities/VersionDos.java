package ec.edu.uce.pa.activities;

import android.content.Context;
import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import ec.edu.uce.pa.R;
import ec.edu.uce.pa.pae.figuras3D.RenderFiguras;
import ec.edu.uce.pa.pae.linternaPlano.RenderLinterna;
import ec.edu.uce.pa.pae.piramideTexturas.RenderPiramideTextura;
import ec.edu.uce.pa.renderes.RenderCuadradoBlend;
import ec.edu.uce.pa.renderes.RenderCuadradoMipMap;
import ec.edu.uce.pa.renderes.RenderCuboNeblina;
import ec.edu.uce.pa.renderes.RenderEsfera;
import ec.edu.uce.pa.renderes.RenderHexagono;
import ec.edu.uce.pa.renderes.RenderHexagonoProyFP;
import ec.edu.uce.pa.renderes.RenderHexagonoStride;
import ec.edu.uce.pa.renderes.RenderHexagonoTextura;
import ec.edu.uce.pa.renderes.RenderPlanoIluminacion;
import ec.edu.uce.pa.renderes.RenderSpotlight;

public class VersionDos extends AppCompatActivity {

    private GLSurfaceView view;
    private static GLSurfaceView.Renderer renderer;
    public static GLSurfaceView.Renderer getRenderer() {
        return renderer;
    }

    private Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_pagina_uno_opengl_dos);

        context = this.getApplicationContext();
        view = new GLSurfaceView(this);
        view.setEGLContextClientVersion(2);

        renderer=null;
        Button DibujarPagina1V2 = findViewById(R.id.btnDibujarPagina1V2);
        DibujarPagina1V2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int opcioneSel;
                RadioGroup rgOpciones = (RadioGroup) findViewById(R.id.rgOpciones);
                opcioneSel = rgOpciones.getCheckedRadioButtonId();
                if (opcioneSel > 0) {

                    if (opcioneSel ==R.id.RBHexagonoColorFijo)
                        renderer= new RenderHexagono(context);

                    if (opcioneSel ==R.id.RBHexagonoColorStride)
                        renderer= new RenderHexagonoStride(context);

                    if (opcioneSel ==R.id.RBlHexagonoProyeccion)
                        renderer= new RenderHexagonoProyFP(context);

                    if (opcioneSel ==R.id.RBHexagonoTextura)
                        renderer= new RenderHexagonoTextura(context);

                    else Toast.makeText(getApplicationContext(),"Escoge una opción",Toast.LENGTH_SHORT).show();

                }//Fin de opcioneSel > 0
                if (renderer!=null) {
                    RenderesParaVersionDos.setRenderer(renderer);
                    Intent intent = new Intent(v.getContext(), RenderesParaVersionDos.class);
                    startActivity(intent);
                    finish();
                }
                else  Toast.makeText(getApplicationContext(),"Escoge una opción",Toast.LENGTH_SHORT).show();
            }
        });

        //Regresar a VersionUnoIndice
        Button RegresarAVersion = findViewById(R.id.btnRegresarVersion);
        RegresarAVersion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), EscogerVersion.class);
                startActivity(intent);
                finish();
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, EscogerVersion.class);
        startActivity(intent);
        finish();
    }

}
