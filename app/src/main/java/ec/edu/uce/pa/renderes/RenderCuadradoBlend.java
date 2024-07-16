package ec.edu.uce.pa.renderes;

import android.content.Context;
import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.R;
import ec.edu.uce.pa.geometrias.CuadradoBlend;
import ec.edu.uce.pa.geometrias.CuadradoTextura;
import ec.edu.uce.pa.utilidades.Funciones;

public class RenderCuadradoBlend implements GLSurfaceView.Renderer {
    private float vIncremento;
    private CuadradoTextura cuadradoTextura;
    private CuadradoBlend cuadrado1, cuadrado2, cuadrado3;

    private int[] arrayTexturas = new int[1];
    private Context context;

    public RenderCuadradoBlend(Context context) {
        this.context = context;
        cuadradoTextura = new CuadradoTextura();
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig eglConfig) {
        gl.glClearColor(0, 0, 0, 1);
        gl.glEnable(GL10.GL_DEPTH_TEST);
        arrayTexturas = Funciones.habilitarTexturas(gl, 2);
        Funciones.cargarImagenesTexturas(gl, context, R.drawable.foto1, 0, arrayTexturas);
        Funciones.cargarImagenesTexturas(gl, context, R.drawable.foto2, 1, arrayTexturas);

        cuadrado1 = new CuadradoBlend(new float[]{
                0.5f, 0.0f, 0.0f, 0.5f
        });

        cuadrado2 = new CuadradoBlend(new float[]{
                0.0f, 0.5f, 0.0f, 0.1f
        });

        cuadrado3 = new CuadradoBlend(new float[]{
                0.0f, 0.0f, 0.5f, 0.5f
        });

    }

    @Override
    public void onSurfaceChanged(GL10 gl, int ancho, int alto) {
        float aspectRatio = ((float) ancho / (float) alto);
        gl.glViewport(0, 0, ancho, alto);
        gl.glMatrixMode(gl.GL_PROJECTION);
        gl.glFrustumf(-aspectRatio, aspectRatio, -1, 1, 1, 30);
        gl.glTexEnvf(gl.GL_TEXTURE_ENV, gl.GL_TEXTURE_ENV_MODE, gl.GL_REPLACE);
    }

    @Override
    public void onDrawFrame(GL10 gl) {

        gl.glClear(gl.GL_COLOR_BUFFER_BIT | gl.GL_DEPTH_BUFFER_BIT);
        gl.glMatrixMode(gl.GL_MODELVIEW);
        gl.glLoadIdentity();

        gl.glDisable(gl.GL_DEPTH_TEST);
        gl.glEnable(gl.GL_BLEND);
        //gl.glBlendFunc(gl.GL_SRC_ALPHA, gl.GL_DST_ALPHA);
        //gl.glBlendFunc(gl.GL_ZERO, gl.GL_ONE);
        gl.glBlendFunc(gl.GL_SRC_ALPHA, gl.GL_ONE);

        gl.glColorMask(true, true, true, true);

        gl.glPushMatrix();
        {
            gl.glTranslatef((float) Math.cos(vIncremento), 0.0f, -4.0f);
            gl.glBindTexture(gl.GL_TEXTURE_2D, arrayTexturas[1]);
            cuadradoTextura.dibujar(gl);
        }
        gl.glPopMatrix();

        gl.glPushMatrix();
        {
            gl.glTranslatef(0.0f, (float) Math.cos(vIncremento), -4.0f);
            cuadrado2.dibujar(gl);
        }
        gl.glPopMatrix();

        gl.glPushMatrix();
        {
            gl.glTranslatef(0.0f, -(float) Math.sin(vIncremento), -4.0f);
            gl.glBindTexture(gl.GL_TEXTURE_2D, arrayTexturas[0]);
            cuadradoTextura.dibujar(gl);
        }

        gl.glPopMatrix();

        vIncremento += 0.05f;

    }
}

