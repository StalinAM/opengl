package ec.edu.uce.pa.renderes;

import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.geometrias.Esfera;

public class RenderEsfera implements GLSurfaceView.Renderer {

    private float vIncremento = 1.0f;
    private Esfera esfera;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig eglConfig) {
        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        gl.glEnable(gl.GL_DEPTH_TEST);
        esfera = new Esfera(30,30,1.0f,1.0f);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int ancho, int alto) {
        float relacionAspecto = (float) ancho / (float) alto;
        gl.glViewport(0, 0, ancho, alto);
        gl.glMatrixMode(gl.GL_PROJECTION);
        gl.glFrustumf(-relacionAspecto, relacionAspecto, -1, 1, 1, 50);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClear(gl.GL_COLOR_BUFFER_BIT | gl.GL_DEPTH_BUFFER_BIT);
        gl.glMatrixMode(gl.GL_MODELVIEW);
        gl.glLoadIdentity();

        gl.glTranslatef(0.0f, 0.0f, -4.0f);
        gl.glRotatef(vIncremento, 0, 1, 0);
        esfera.dibujar(gl);

        vIncremento += 0.5;


    }
}