package ec.edu.uce.pa.renderes;

import static javax.microedition.khronos.opengles.GL10.GL_LIGHT0;

import android.opengl.GLSurfaceView;

import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.geometrias.PlanoIluminacion;
import ec.edu.uce.pa.utilidades.Funciones;

public class RenderPlanoIluminacion implements GLSurfaceView.Renderer {
    private PlanoIluminacion plano;
    private static final int LUZ0 = GL_LIGHT0;
    private float vIncremento = 0.0f;

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        gl10.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        gl10.glEnable(gl10.GL_DEPTH_TEST);
        plano = new PlanoIluminacion();
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

        float[] posicion = {
                0.0f, -0.5f, -4.0f, 1.0f
        };

        float[] posicion2 = {
                0.0f, 0.0f, -4.0f, 1.0f
        };

        float[] color = {
                0.0f, 0.0f, 1.0f, 1.0f
        };

        float[] colorDifuso = {
                0.3f, 0.8f, 0.3f, 1.0f
        };

        float[] colorAmbiente = {
                0.2f, 0.2f, 0.2f, 1.0f
        };

        float[] colorMorado = {
                0.1f, 0.0f, 0.5f, 1.0f
        };

        float[] colorPVer = {
                0.1f, 1.0f, 0.0f, 1.0f
        };

        gl10.glEnable(LUZ0);

        gl10.glPushMatrix();
        {
            gl10.glTranslatef(0.0f, 0.0f, -4.0f);
            gl10.glRotatef(vIncremento, 1, 1, 0);
            gl10.glLightModelfv(gl10.GL_LIGHT_MODEL_AMBIENT, FloatBuffer.wrap(colorMorado));
            gl10.glLightfv(LUZ0, gl10.GL_POSITION, Funciones.generarFloatBuffer(posicion));
            gl10.glEnable(gl10.GL_LIGHTING);

            plano.dibujar(gl10);
            gl10.glPushMatrix();
            {
                gl10.glRotatef(90, 1, 0, 0);
                gl10.glLightModelfv(gl10.GL_LIGHT_MODEL_AMBIENT, FloatBuffer.wrap(colorAmbiente));
                gl10.glEnable(gl10.GL_LIGHTING);
                gl10.glLightfv(LUZ0, gl10.GL_POSITION, Funciones.generarFloatBuffer(posicion2));
                //gl10.glLightfv(LUZ0,gl10.GL_SPECULAR,Funciones.generarBuffer(colorPVer));
                gl10.glEnable(LUZ0);
                plano.dibujar(gl10);
            }
            gl10.glPopMatrix();
            gl10.glPushMatrix();
            {
                gl10.glRotatef(90, 0, 0, 1);
                gl10.glLightModelfv(gl10.GL_LIGHT_MODEL_AMBIENT, FloatBuffer.wrap(colorPVer));
                gl10.glEnable(gl10.GL_LIGHTING);
                gl10.glLightfv(LUZ0, gl10.GL_POSITION, Funciones.generarFloatBuffer(posicion2));
                //gl10.glLightfv(LUZ0,gl10.GL_SPECULAR,Funciones.generarBuffer(colorPVer));
                gl10.glEnable(LUZ0);
                plano.dibujar(gl10);
            }
            gl10.glPopMatrix();
        }
        gl10.glPopMatrix();

        vIncremento += 0.7f;
    }

}
