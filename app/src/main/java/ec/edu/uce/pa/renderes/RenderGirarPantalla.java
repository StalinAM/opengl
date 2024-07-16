package ec.edu.uce.pa.renderes;

import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;


public class RenderGirarPantalla implements GLSurfaceView.Renderer {

    public  float g1,g2;

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        gl10.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);

    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int ancho, int alto) {
        float relacionAspecto = (float) ancho / (float) alto;
        gl10.glViewport(0, 0, ancho, alto);
        gl10.glMatrixMode(gl10.GL_PROJECTION);
        gl10.glFrustumf(-relacionAspecto, relacionAspecto, -1, 1, 1, 30);
        if(alto>ancho){
            g2=1.0f;
            g1=0.0f;
        }
        else {
            g1=1.0f;
            g2=0.0f;
        }

    }

    @Override
    public void onDrawFrame(GL10 gl10) {
        gl10.glClear(gl10.GL_COLOR_BUFFER_BIT | gl10.GL_DEPTH_BUFFER_BIT);
        gl10.glClearColor(g1, g2, 0.0f, 1.0f);
    }
}