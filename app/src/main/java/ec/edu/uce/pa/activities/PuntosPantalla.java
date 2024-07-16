package ec.edu.uce.pa.activities;

import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import ec.edu.uce.pa.renderes.RenderPunto;


public class PuntosPantalla extends AppCompatActivity {

    public static int getPuntos() {
        return puntos;
    }

    public static void setPuntos(int puntos) {
        PuntosPantalla.puntos = puntos;
    }

    public static int puntos;


    private GLSurfaceView view;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = new GLSurfaceView(this);
        view.setRenderer(new RenderPunto(getPuntos()));
        setContentView(view);
    }

    @Override
    public void onBackPressed() {
        setPuntos(0);
        super.onBackPressed();
        Intent intent = new Intent(this, PaginaUnoVersionUno.class);
        startActivity(intent);
    }

}

