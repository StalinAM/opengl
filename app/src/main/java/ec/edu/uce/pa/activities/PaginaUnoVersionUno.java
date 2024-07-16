package ec.edu.uce.pa.activities;

import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import ec.edu.uce.pa.R;
import ec.edu.uce.pa.pae.carrito.RenderCarrito;
import ec.edu.uce.pa.renderes.RenderCasita;
import ec.edu.uce.pa.renderes.RenderCirculoPuntos;
import ec.edu.uce.pa.renderes.RenderCubo;
import ec.edu.uce.pa.renderes.RenderDeptTest;
import ec.edu.uce.pa.renderes.RenderPushPop;

public class PaginaUnoVersionUno extends AppCompatActivity {

    EditText etEnviar;
    Button btnEnviarNumero;
    private static int numeroPuntos;

    public static int getNumeroPuntos() {
        return numeroPuntos;
    }
    private boolean seguir,ingresaNumero;

    //Nueva Vista
    private  GLSurfaceView view;
    //Renderes
    private static GLSurfaceView.Renderer renderer;
    public static GLSurfaceView.Renderer getRenderer() {
        return renderer;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_pagina_uno_opengl_uno);

        etEnviar = findViewById(R.id.editTextNumber);
        btnEnviarNumero = findViewById(R.id.btnEnviarNumeroPuntos);

        btnEnviarNumero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    numeroPuntos = Integer.parseInt(etEnviar.getText().toString());
                    if (numeroPuntos > 0) {
                        seguir = true;
                        ingresaNumero=true;
                        Toast.makeText(getApplicationContext(), "Numero de Puntos: " + numeroPuntos, Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getApplicationContext(), "Numero Invalido", Toast.LENGTH_SHORT).show();
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Ingrese un número válido", Toast.LENGTH_SHORT).show();
                }
            }
        });

        view = new GLSurfaceView(this);
        view.setEGLContextClientVersion(1);

        renderer=null;
        Button btnDibujar = findViewById(R.id.btnDibujarPagina1V1);
        btnDibujar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int opcioneSel;
                RadioGroup rgOpciones = (RadioGroup) findViewById(R.id.rgOpciones);
                opcioneSel = rgOpciones.getCheckedRadioButtonId();
                if (opcioneSel > 0) {
                    if (opcioneSel == R.id.RBColorPantalla) {
                        Intent intent = new Intent(v.getContext(), OpcionColor.class);
                        startActivity(intent);
                        finish();
                    }
                    if (opcioneSel ==R.id.RBPuntosPantalla && seguir){
                        PuntosPantalla.setPuntos(numeroPuntos);
                        Intent intent = new Intent(v.getContext(), PuntosPantalla.class);
                        startActivity(intent);
                        finish();
                    }
                    if (opcioneSel ==R.id.RBlCasita)
                        renderer= new RenderCasita();

                    if (opcioneSel ==R.id.RBCirculoPuntos)
                        renderer= new RenderCirculoPuntos();

                    if (opcioneSel ==R.id.RBCarrito)
                        renderer= new RenderCarrito();

                    if (opcioneSel ==R.id.RBPushPop)
                        renderer= new RenderPushPop();

                    if (opcioneSel ==R.id.RBCubo)
                        renderer= new RenderCubo();

                    if (opcioneSel ==R.id.RBFuncionDeptTest)
                        renderer= new RenderDeptTest();

                    if (opcioneSel ==R.id.RBCuboMovimientoTeclado){
                        Intent intent = new Intent(v.getContext(), GirarCubo.class);
                        startActivity(intent);
                        finish();
                    }

                    else Toast.makeText(getApplicationContext(),"Escoge una opción",Toast.LENGTH_SHORT).show();

                }//Fin de opcioneSel > 0
                if (renderer!=null) {
                    RenderesParaVersionUnoPaginaUno.setRenderer(renderer);
                    Intent intent = new Intent(v.getContext(), RenderesParaVersionUnoPaginaUno.class);
                    startActivity(intent);
                    finish();
                }
                else  Toast.makeText(getApplicationContext(),"Escoge una opción",Toast.LENGTH_SHORT).show();
            }
        });

        //Regresar a VersionUnoIndice
        Button RegresarAVersionUnoIndice = findViewById(R.id.btnRegresarVersionUnoIndice1);
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
