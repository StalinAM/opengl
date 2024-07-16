package ec.edu.uce.pa.pae.cuboMovimientoTeclado;

import android.opengl.GLSurfaceView;
import android.opengl.GLU;


import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.activities.GiroCamara;


public class RenderGiraCam implements GLSurfaceView.Renderer {
    float x = 0;
    float y = 0;
    float z = 1.0f;
    float upY = 1.0f;
    private CaraCubo cara1;
    private CaraCubo cara2;
    private CaraCubo cara3;
    private CaraCubo cara4;
    private CaraCubo cara5;
    private CaraCubo cara6;

    private float In = 0.0f;


    private float radio = 0.5f;

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        gl10.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
        gl10.glEnable(gl10.GL_DEPTH_TEST);
        gl10.glEnable(gl10.GL_CULL_FACE);
        gl10.glCullFace(gl10.GL_BACK);

        cara1 = new CaraCubo(0.0f, 1.0f, 0.0f, 1.0f); //Verde
        cara2 = new CaraCubo(0.0f, 0.5f, 0.5f, 1.0f); //tuerqueza
        cara3 = new CaraCubo(1.0f, 0.0f, 0.0f, 1.0f); //rojo
        cara4 = new CaraCubo(0.1f, 0.0f, 0.1f, 1.0f);  //negro
        cara5 = new CaraCubo(0.0f, 0.0f, 1.0f, 1.0f); //azul
        cara6 = new CaraCubo(2.0f, 0.5f, 1.0f, 1.0f); //rosado
    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int ancho, int alto) {
        //Codigo para la matriz de proyecion
        float relacionAspecto = (float) ancho / (float) alto;
        gl10.glViewport(0, 0, ancho, alto); //lo normal es mantener en el ancho de la pantalla
        gl10.glMatrixMode(gl10.GL_PROJECTION);
        gl10.glFrustumf(-relacionAspecto, relacionAspecto, -1, 1, 1, 30);
        GLU.gluLookAt(gl10, 0.0f, 0.0f, 5.0f,
                0.0f, 0.0f, 0.0f,
                0.0f, 1.0f, 0.0f);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClear(gl.GL_COLOR_BUFFER_BIT | gl.GL_DEPTH_BUFFER_BIT);
        gl.glClear(gl.GL_COLOR_BUFFER_BIT);
        gl.glMatrixMode(gl.GL_MODELVIEW);
        gl.glLoadIdentity();

        gl.glTranslatef(0.0f, 0.0f, -0.5f);

        if (GiroCamara.ejeCamara) {
            if (GiroCamara.rotar) {
                GLU.gluLookAt(gl,
                        radio * (float) Math.sin(GiroCamara.iRotacionC), 0, radio * (float) Math.cos(GiroCamara.iRotacionC),// posicion punto del ojo
                        0, 0, 0,    //posicion punto de referencia
                        0, 1, 0           // direccion vector ascendente
                );
                cara1.dibujar(gl);
                gl.glRotatef(90, 0, 1, 0);
                cara2.dibujar(gl);

                gl.glRotatef(90, 0, 1, 0);
                cara3.dibujar(gl);

                gl.glRotatef(90, 0, 1, 0);
                cara4.dibujar(gl);

                gl.glRotatef(90, 1, 0, 0);
                cara5.dibujar(gl);

                gl.glRotatef(180, 1, 0, 0);
                cara6.dibujar(gl);
            } else {
                cara1.dibujar(gl);
                gl.glRotatef(90, 0, 1, 0);
                cara2.dibujar(gl);

                gl.glRotatef(90, 0, 1, 0);
                cara3.dibujar(gl);

                gl.glRotatef(90, 0, 1, 0);
                cara4.dibujar(gl);

                gl.glRotatef(90, 1, 0, 0);
                cara5.dibujar(gl);

                gl.glRotatef(180, 1, 0, 0);
                cara6.dibujar(gl);
            }
        } else {
            if (GiroCamara.rotar) {
                GLU.gluLookAt(gl,
                        0, radio * (float) Math.sin(GiroCamara.iRotacionC), radio * (float) Math.cos(GiroCamara.iRotacionC),// posicion punto del ojo
                        0, 0, 0,    //posicion punto de referencia
                        0, 1, 0           // direccion vector ascendente
                );
                cara1.dibujar(gl);
                gl.glRotatef(90, 0, 1, 0);
                cara2.dibujar(gl);

                gl.glRotatef(90, 0, 1, 0);
                cara3.dibujar(gl);

                gl.glRotatef(90, 0, 1, 0);
                cara4.dibujar(gl);

                gl.glRotatef(90, 1, 0, 0);
                cara5.dibujar(gl);

                gl.glRotatef(180, 1, 0, 0);
                cara6.dibujar(gl);
            } else {
                cara1.dibujar(gl);
                gl.glRotatef(90, 0, 1, 0);
                cara2.dibujar(gl);

                gl.glRotatef(90, 0, 1, 0);
                cara3.dibujar(gl);

                gl.glRotatef(90, 0, 1, 0);
                cara4.dibujar(gl);

                gl.glRotatef(90, 1, 0, 0);
                cara5.dibujar(gl);

                gl.glRotatef(180, 1, 0, 0);
                cara6.dibujar(gl);
            }
        }

    }
}