package ec.edu.uce.pa.pae.piramideTexturas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLSurfaceView;
import android.opengl.GLUtils;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.R;


public class RenderPiramideTextura implements GLSurfaceView.Renderer {

    private float vIncremento = 5.0f;

    private TrinaguloTexturaPAE prueba;
    private int[] arrayTexturas = new int[4];
    private Context context;

    public RenderPiramideTextura(Context contexto) {
        this.context = contexto;
    }

    @Override
    //todo lo que quiero inizializar sin realizar cambios
    public void onSurfaceCreated(GL10 gl, EGLConfig eglConfig) {

        gl.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
        gl.glEnable(gl.GL_DEPTH_TEST);

        prueba = new TrinaguloTexturaPAE();

        gl.glEnable(gl.GL_TEXTURE_2D);
        gl.glGenTextures(4, arrayTexturas, 0);

    }

    @Override
    public void onSurfaceChanged(GL10 gl, int ancho, int alto) {
        float relacionAspecto = (float) ancho / (float) alto;
        gl.glViewport(0, 0, ancho, alto);
        gl.glMatrixMode(gl.GL_PROJECTION);
        gl.glFrustumf(-relacionAspecto, relacionAspecto, -1, 1, 1, 30);
        gl.glTexEnvf(gl.GL_TEXTURE_ENV, gl.GL_TEXTURE_ENV_MODE, gl.GL_REPLACE);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClear(gl.GL_COLOR_BUFFER_BIT | gl.GL_DEPTH_BUFFER_BIT);
        gl.glMatrixMode(gl.GL_MODELVIEW);
        gl.glLoadIdentity();

        gl.glPushMatrix();
        {
            gl.glTranslatef(0.0f, 0.0f, -3.0f);
            gl.glRotatef(-vIncremento,1,1,1);
            gl.glPushMatrix();
            {
                Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.cara1);
                gl.glBindTexture(gl.GL_TEXTURE_2D, arrayTexturas[0]);
                GLUtils.texImage2D(gl.GL_TEXTURE_2D, 0, bitmap, 0);
                gl.glTexParameterf(gl.GL_TEXTURE_2D, gl.GL_TEXTURE_MAG_FILTER, gl.GL_LINEAR);
                gl.glTexParameterf(gl.GL_TEXTURE_2D, gl.GL_TEXTURE_MIN_FILTER, gl.GL_LINEAR);
                bitmap.recycle();
                prueba.dribujar(gl,1);
            }
            gl.glPopMatrix();

            gl.glPushMatrix();
            {
                Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.cara2);
                gl.glBindTexture(gl.GL_TEXTURE_2D, arrayTexturas[0]);
                GLUtils.texImage2D(gl.GL_TEXTURE_2D, 0, bitmap, 0);
                gl.glTexParameterf(gl.GL_TEXTURE_2D, gl.GL_TEXTURE_MAG_FILTER, gl.GL_LINEAR);
                gl.glTexParameterf(gl.GL_TEXTURE_2D, gl.GL_TEXTURE_MIN_FILTER, gl.GL_LINEAR);
                bitmap.recycle();
                prueba.dribujar(gl,2);
            }
            gl.glPopMatrix();

            gl.glPushMatrix();
            {
                Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.cara3);
                gl.glBindTexture(gl.GL_TEXTURE_2D, arrayTexturas[0]);
                GLUtils.texImage2D(gl.GL_TEXTURE_2D, 0, bitmap, 0);
                gl.glTexParameterf(gl.GL_TEXTURE_2D, gl.GL_TEXTURE_MAG_FILTER, gl.GL_LINEAR);
                gl.glTexParameterf(gl.GL_TEXTURE_2D, gl.GL_TEXTURE_MIN_FILTER, gl.GL_LINEAR);
                bitmap.recycle();
                prueba.dribujar(gl,3);
            }
            gl.glPopMatrix();

            gl.glPushMatrix();
            {
                Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.cara4);
                gl.glBindTexture(gl.GL_TEXTURE_2D, arrayTexturas[0]);
                GLUtils.texImage2D(gl.GL_TEXTURE_2D, 0, bitmap, 0);
                gl.glTexParameterf(gl.GL_TEXTURE_2D, gl.GL_TEXTURE_MAG_FILTER, gl.GL_LINEAR);
                gl.glTexParameterf(gl.GL_TEXTURE_2D, gl.GL_TEXTURE_MIN_FILTER, gl.GL_LINEAR);
                bitmap.recycle();
                prueba.dribujar(gl,4);
            }
            gl.glPopMatrix();

            gl.glPushMatrix();
            {
                Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.cara5);
                gl.glBindTexture(gl.GL_TEXTURE_2D, arrayTexturas[0]);
                GLUtils.texImage2D(gl.GL_TEXTURE_2D, 0, bitmap, 0);
                gl.glTexParameterf(gl.GL_TEXTURE_2D, gl.GL_TEXTURE_MAG_FILTER, gl.GL_LINEAR);
                gl.glTexParameterf(gl.GL_TEXTURE_2D, gl.GL_TEXTURE_MIN_FILTER, gl.GL_LINEAR);
                bitmap.recycle();
                prueba.dribujar(gl,5);
            }
            gl.glPopMatrix();
        }
        gl.glPopMatrix();

        vIncremento += 1.5f;
    }

}
