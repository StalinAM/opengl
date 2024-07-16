package ec.edu.uce.pa.renderes;

import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;



public class RenderTocaPantalla implements GLSurfaceView.Renderer {

    public static float f1,f2,f3,f4;

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        gl10.glClearColor(0.0f, 1.0f, 0.0f, 1.0f);

    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int ancho, int alto) {
        //Codigo para la matriz de proyecion
        float relacionAspecto = (float) ancho / (float) alto;
        gl10.glViewport(0, 0, ancho, alto);
        gl10.glMatrixMode(gl10.GL_PROJECTION);
        gl10.glFrustumf(-relacionAspecto, relacionAspecto, -1, 1, 1, 30);





    }

    @Override
    public void onDrawFrame(GL10 gl10) {
        gl10.glClear(gl10.GL_COLOR_BUFFER_BIT | gl10.GL_DEPTH_BUFFER_BIT);
        gl10.glClearColor(f1, f2, f3, f4);
    }
}