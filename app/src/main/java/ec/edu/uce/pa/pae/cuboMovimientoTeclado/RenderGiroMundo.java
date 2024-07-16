package ec.edu.uce.pa.pae.cuboMovimientoTeclado;

import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.activities.GiroMundo;

import ec.edu.uce.pa.pae.cuboMovimientoTeclado.CaraCubo;

public class RenderGiroMundo implements GLSurfaceView.Renderer {

    private CaraCubo cara1;
    private CaraCubo cara2;
    private CaraCubo cara3;
    private CaraCubo cara4;
    private CaraCubo cara5;
    private CaraCubo cara6;

    private float In = 0.0f;

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        gl10.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
        gl10.glEnable(gl10.GL_DEPTH_TEST);
        gl10.glEnable(gl10.GL_CULL_FACE);
        gl10.glCullFace(gl10.GL_BACK);

        cara1 = new CaraCubo(0.0f, 1.0f, 0.0f, 1.0f); //naranja
        cara2 = new CaraCubo(0.0f, 0.5f, 0.5f, 1.0f); //tuerqueza
        cara3 = new CaraCubo(1.0f, 0.0f, 0.0f, 1.0f); //rojo
        cara4 = new CaraCubo(0.1f, 0.0f, 0.1f, 1.0f);  //negro
        cara5 = new CaraCubo(0.0f, 0.0f, 1.0f, 1.0f); //azul
        cara6 = new CaraCubo(2.0f, 0.5f, 1.0f, 1.0f); //rosado

    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int ancho, int alto) {

        float relacionAspecto = (float) ancho / (float) alto;
        gl10.glViewport(0, 0, ancho, alto); //lo normal es mantener en el ancho de la pantalla
        gl10.glMatrixMode(gl10.GL_PROJECTION);
        gl10.glFrustumf(-relacionAspecto, relacionAspecto, -1, 1, 1, 30);
    }

    @Override
    public void onDrawFrame(GL10 gl10) {
        gl10.glClear(gl10.GL_COLOR_BUFFER_BIT | gl10.GL_DEPTH_BUFFER_BIT);
        gl10.glClear(gl10.GL_COLOR_BUFFER_BIT);
        gl10.glMatrixMode(gl10.GL_MODELVIEW);

        gl10.glLoadIdentity();
        gl10.glTranslatef(0.0f, 0.0f, -6.0f);

        gl10.glRotatef(GiroMundo.getRotarX(), 1, 0, 0);
        gl10.glRotatef(GiroMundo.getRotarY(), 0, 1, 0);


        cara1.dibujar(gl10);
        gl10.glTranslatef(0.0f, 0.0f, 0.0f);

        gl10.glRotatef(90, 0, 1, 0);
        cara2.dibujar(gl10);

        gl10.glRotatef(90, 0, 1, 0);
        cara3.dibujar(gl10);

        gl10.glRotatef(90, 0, 1, 0);
        cara4.dibujar(gl10);

        gl10.glRotatef(90, 1, 0, 0);
        cara5.dibujar(gl10);

        gl10.glRotatef(180, 1, 0, 0);
        cara6.dibujar(gl10);
    }
}