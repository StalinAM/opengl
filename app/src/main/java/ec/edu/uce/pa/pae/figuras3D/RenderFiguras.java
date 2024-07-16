package ec.edu.uce.pa.pae.figuras3D;

import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;


public class RenderFiguras implements GLSurfaceView.Renderer {

    private Cilindro cilindro;
    private Piramide piramide;
    private Cono cono;
    float i = 0.0f;


    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        gl10.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        gl10.glEnable(GL10.GL_DEPTH_TEST);
        cilindro = new Cilindro();
        piramide = new Piramide();
        cono = new Cono();
    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int ancho, int alto) {
        float relacionaspecto = (float) ancho / (float) alto;
        gl10.glViewport(0, 0, ancho, alto);
        gl10.glMatrixMode(gl10.GL_PROJECTION);
        gl10.glFrustumf(-relacionaspecto, relacionaspecto, -1, 1, 1, 30);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | gl.GL_DEPTH_BUFFER_BIT);
        gl.glMatrixMode(GL10.GL_MODELVIEW);//2D
        gl.glLoadIdentity();

        gl.glTranslatef(0, 0, -3);

        gl.glPushMatrix(); //ciliendro
        {
            gl.glTranslatef(0.0f, 0.0f, 0.0f);
            gl.glRotatef(i, 1, 0, 1);
            gl.glScalef(0.5f, 0.5f, 0.5f);
            cilindro.dibujar(gl);
        }
        gl.glPopMatrix();

        gl.glPushMatrix();
        {

            gl.glTranslatef(0.0f, 1.7f, 0.0f);
            gl.glRotatef(i, 1, 0, 1);
            gl.glScalef(0.5f, 0.5f, 0.5f);
            cono.dibujar(gl);
        }
        gl.glPopMatrix();

        gl.glPushMatrix();
        {
            gl.glTranslatef(0.0f, -1.7f, 0.0f);
            gl.glRotatef(i, 1, 0, 1);
            gl.glScalef(0.7f, 0.7f, 0.7f);
            piramide.dibujar(gl);
        }

        gl.glPopMatrix();

        i += 4.0f;


    }


}
