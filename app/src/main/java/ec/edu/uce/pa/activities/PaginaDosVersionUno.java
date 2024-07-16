package ec.edu.uce.pa.activities;

import android.content.Context;
import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import ec.edu.uce.pa.R;
import ec.edu.uce.pa.pae.figuras3D.RenderFiguras;
import ec.edu.uce.pa.pae.linternaPlano.RenderLinterna;
import ec.edu.uce.pa.pae.piramideTexturas.RenderPiramideTextura;
import ec.edu.uce.pa.renderes.RenderCuadradoBlend;
import ec.edu.uce.pa.renderes.RenderCuadradoMipMap;
import ec.edu.uce.pa.renderes.RenderCuboNeblina;
import ec.edu.uce.pa.renderes.RenderEsfera;
import ec.edu.uce.pa.renderes.RenderPlanoIluminacion;
import ec.edu.uce.pa.renderes.RenderSpotlight;

public class PaginaDosVersionUno extends AppCompatActivity {

    //Nueva Vista
    private  GLSurfaceView view;
    //Renderes
    private static GLSurfaceView.Renderer renderer;
    public static GLSurfaceView.Renderer getRenderer() {
        return renderer;
    }
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_pagina_dos_opengl_uno);
        context = this.getApplicationContext();
        view = new GLSurfaceView(this);
        view.setEGLContextClientVersion(1);

        renderer=null;

        Button btnDibujar2 = findViewById(R.id.btnDibujarPagina2V1);
        btnDibujar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int opcioneSel;
                RadioGroup rgOpciones = (RadioGroup) findViewById(R.id.rgOpciones);
                opcioneSel = rgOpciones.getCheckedRadioButtonId();
                if (opcioneSel > 0) {

                    if (opcioneSel ==R.id.RBEsfera)
                        renderer= new RenderEsfera();

                    if (opcioneSel ==R.id.RBPlanosIluminacion)
                        renderer= new RenderPlanoIluminacion();

                    if (opcioneSel ==R.id.RBFiguras3D)
                        renderer= new RenderFiguras();

                    if (opcioneSel ==R.id.RBSpotLightAnimada)
                        renderer= new RenderSpotlight();

                    if (opcioneSel ==R.id.RBSpotLightAnimada)
                        renderer= new RenderSpotlight();

                    if (opcioneSel == R.id.RBUniversoEscalaMateriales) {
                        Intent intent = new Intent(v.getContext(), MoverMundoMateriales.class);
                        startActivity(intent);
                        finish();
                    }
                    if (opcioneSel ==R.id.RBUniversoEstalaTexturas){
                        Intent intent = new Intent(v.getContext(), MoverMundoTexturas.class);
                        startActivity(intent);
                        finish();
                    }

                    if (opcioneSel ==R.id.RBBlending)
                        renderer= new RenderCuadradoBlend(context);

                    if (opcioneSel ==R.id.RBPirámideTexturas)
                        renderer= new RenderPiramideTextura(context);

                    if (opcioneSel ==R.id.RBMipMap)
                        renderer= new RenderCuadradoMipMap(context);

                    if (opcioneSel ==R.id.RBNeblina)
                        renderer= new RenderCuboNeblina();

                    if (opcioneSel ==R.id.RBLinternaPlano)
                        renderer= new RenderLinterna(context);

                    else Toast.makeText(getApplicationContext(),"Escoge una opción",Toast.LENGTH_SHORT).show();

                }//Fin de opcioneSel > 0
                if (renderer!=null) {
                    RenderesParaVersionUnoPaginaDos.setRenderer(renderer);
                    Intent intent = new Intent(v.getContext(), RenderesParaVersionUnoPaginaDos.class);
                    startActivity(intent);
                    finish();
                }
                else  Toast.makeText(getApplicationContext(),"Escoge una opción",Toast.LENGTH_SHORT).show();
            }
        });

        //Regresar a VersionUnoIndice
        Button RegresarAVersionUnoIndice = findViewById(R.id.btnRegresarVersionUnoIndice2);
        RegresarAVersionUnoIndice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), VersionUnoIndice.class);
                startActivity(intent);
                finish();
            }
        });
    }

    //Regresar a VersionUnoIndice
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, VersionUnoIndice.class);
        startActivity(intent);
        finish();
    }
}
