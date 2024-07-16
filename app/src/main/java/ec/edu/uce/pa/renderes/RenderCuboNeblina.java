package ec.edu.uce.pa.renderes;

import android.opengl.GLSurfaceView;

import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.geometrias.CuboNeblina;
import ec.edu.uce.pa.geometrias.Esfera;

public class RenderCuboNeblina implements GLSurfaceView.Renderer {
    private float vIncremento=0.0f,vFog=0.0f,vDeltaNeblina=0.005f;

    private CuboNeblina cubo;
    private Esfera esfera;


    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig eglConfig) {
        gl.glClearColor(0.234f,0.247f,0.255f,1.0f);
        gl.glEnable(GL10.GL_DEPTH_TEST);
        cubo = new CuboNeblina();
        esfera = new Esfera(20,20,1,1);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int ancho, int alto) {
        float aspectRatio = ((float) ancho / (float) alto);
        gl.glViewport(0, 0, ancho, alto);//origen "x=0" y "y=0" por defecto alto y ancho de la pantalla, es practicamente la ventana de copordenas donde se va a dibujar
        gl.glMatrixMode(gl.GL_PROJECTION);
        gl.glFrustumf(-aspectRatio, aspectRatio, -1, 1, 1, 30);// es la mas usada

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

        float [] luzBlanca = {1, 1, 1, 1.0f};

        float [] luzVerde = {0.0f, 1.0f,0.0f, 1.0f};

        float [] luzRojo = {1.0f, 0.0f,0.0f, 1.0f};

        if (vFog > 5 || vFog <0){
            vDeltaNeblina *=-1;
        }
        vFog += vDeltaNeblina;

        gl.glFogfv(gl.GL_FOG_COLOR, FloatBuffer.wrap(luzBlanca));
        gl.glFogf(gl.GL_FOG_MODE,gl.GL_EXP);
        gl.glFogf(gl.GL_FOG_DENSITY,0.3f);
        gl.glFogf(gl.GL_FOG_START,1.0f);
        gl.glFogf(gl.GL_FOG_END,5.0f);
        gl.glEnable(gl.GL_FOG);


        gl.glTranslatef(0.0f, 0.0f, -4);
        //gl.glRotatef(vIncremento, 0.0f, 1, 0.0f);

        cubo.dibujarCubo(gl);
        esfera.dibujar(gl);

        vIncremento += 0.3f;
    }
}
//ejercicio del sistema solar textura, camara, neblina, iluminacion, lo que se quiera
//que envuelva a algun planeta