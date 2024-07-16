package ec.edu.uce.pa.pae.carrito;

import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.pae.carrito.Carrito;


public class RenderCarrito implements GLSurfaceView.Renderer {
    private Carrito camion;
    private float vIncremento=0.1f;
    public static int numeroRender;

    private  int numero =0;
    public RenderCarrito() {

        this.numeroRender= numero;
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig eglConfig) {
        gl.glClearColor(0.5f,0.5f,0.5f,1.0f);
        camion =new Carrito(15);
    }


    @Override
    public void onSurfaceChanged(GL10 gl, int ancho, int alto) {
        float relacionAspecto = (float) ancho / (float) alto;
        gl.glViewport(0, 0, ancho, alto);
        gl.glMatrixMode(gl.GL_PROJECTION);
        gl.glFrustumf(-relacionAspecto, relacionAspecto, -1, 1, 1, 30);

    }

    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClear(gl.GL_COLOR_BUFFER_BIT);
        gl.glMatrixMode(gl.GL_MODELVIEW);
        gl.glLoadIdentity();

        gl.glTranslatef((float) Math.cos(vIncremento),0.0f,-6f);
        camion.dibujar(gl);

        vIncremento+= 0.1f;
    }
}
