package ec.edu.uce.pa.renderes;

import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.geometrias.Triangulo;

public class RenderPushPop implements GLSurfaceView.Renderer {
    private Triangulo triangulo;
    private float vIncremento1 = 0.0f;
    private float vIncremento2 = 0.0f;

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        gl10.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
        gl10.glEnable(gl10.GL_DEPTH_TEST); //prueba de profundidad (para saber que pixel ya esta dibujado)
        triangulo = new Triangulo();
    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int ancho, int alto) {
        float relacionAspecto = (float) ancho / (float) alto;
        gl10.glViewport(0, 0, ancho, alto);
        gl10.glMatrixMode(gl10.GL_PROJECTION);
        gl10.glFrustumf(-relacionAspecto, relacionAspecto, -1, 1, 1, 30);

    }

    @Override
    public void onDrawFrame(GL10 gl10) {
        gl10.glClear(gl10.GL_COLOR_BUFFER_BIT | gl10.GL_DEPTH_BUFFER_BIT);
        gl10.glMatrixMode(gl10.GL_MODELVIEW);
        gl10.glLoadIdentity();

        gl10.glTranslatef(0.0f, 0.0f, -6.0f);
        gl10.glRotatef(vIncremento1, 0, 0, 1);
        triangulo.dibujar(gl10);

        gl10.glPushMatrix();
        {
            gl10.glTranslatef(0.0f, -3.5f, -3.0f);
            gl10.glRotatef(vIncremento1, 0, 0, 1);
            gl10.glScalef(0.5f, 0.5f, 0.5f);
            triangulo.dibujar(gl10);
        }
        gl10.glPopMatrix();

        gl10.glPushMatrix();
        {
            gl10.glTranslatef(0.0f, +3.5f, -3.0f);
            gl10.glRotatef(vIncremento2, 0, 0, 1);
            gl10.glScalef(0.5f, 0.5f, 0.5f);
            triangulo.dibujar(gl10);

            gl10.glPushMatrix();
            {
                gl10.glTranslatef(0.0f, +1.9f, -1.0f);
                gl10.glRotatef(vIncremento2, 0, 0, 1);
                gl10.glScalef(0.3f, 0.3f, 0.3f);
                triangulo.dibujar(gl10);
            }
            gl10.glPopMatrix();

        }
        gl10.glPopMatrix();


        gl10.glPopMatrix();

        vIncremento1 -= 0.3f;
        vIncremento2 += 0.6f;


    }

}
