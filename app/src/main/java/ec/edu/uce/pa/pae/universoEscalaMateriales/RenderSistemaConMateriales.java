package ec.edu.uce.pa.pae.universoEscalaMateriales;

import static javax.microedition.khronos.opengles.GL10.GL_LIGHT0;

import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;



public class RenderSistemaConMateriales implements GLSurfaceView.Renderer {

    private float vIncremento = 0.0f;
    private float vIncremento2 = 0.0f;
    private float vIncremento3 = -5.0f;
    private AstroSistemaMateriales esfera;
    private AnilloSistemaMateriales anillo;
    private StarsSistemaMateriales estrellas;
    private float distancia = 0;
    private final static int LUZ0 = GL_LIGHT0;


    public static float x = 0.5f;
    public static float y = 0.5f;
    public static float z = 0.5f;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig eglConfig) {

        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        gl.glEnable(gl.GL_DEPTH_TEST);
        gl.glDepthFunc(gl.GL_LESS);

        esfera = new AstroSistemaMateriales(50, 50, 1.0f, 1.0f);
        anillo = new AnilloSistemaMateriales(50, 50, 1.0f);
        estrellas= new StarsSistemaMateriales(5000,40000);
        gl.glEnable(gl.GL_LIGHTING);
        gl.glEnable(LUZ0);

    }


    @Override
    public void onSurfaceChanged(GL10 gl, int ancho, int alto) {
        float relacionAspecto = (float) ancho / (float) alto;
        gl.glViewport(0, 0, ancho, alto);
        gl.glMatrixMode(gl.GL_PROJECTION);
        gl.glFrustumf(-relacionAspecto, relacionAspecto, -1, 1, 1, 60000);
    }


    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClear(gl.GL_COLOR_BUFFER_BIT | gl.GL_DEPTH_BUFFER_BIT);
        gl.glMatrixMode(gl.GL_MODELVIEW);
        gl.glLoadIdentity();

        gl.glTranslatef(-4.0f, -3.0f, -9.0f);

        gl.glPushMatrix();{
            gl.glTranslatef(10,3,0);
            gl.glMaterialfv(gl.GL_FRONT_AND_BACK, gl.GL_EMISSION, MaterialSistemaMateriales.materialEstrella, 0);
            estrellas.dibujar(gl);
        }gl.glPopMatrix();

        gl.glTranslatef(x, -3, z);


        gl.glPushMatrix();
        {
            ubicarAstro(gl, 0, 1.0f, MaterialSistemaMateriales.materialCeres, vIncremento2);
        }
        gl.glPopMatrix();

        gl.glPushMatrix();
        {
            ubicarAstro(gl, 3, 1.50f, MaterialSistemaMateriales.materialMakeMake, vIncremento2);
        }
        gl.glPopMatrix();

        gl.glPushMatrix();
        {
            ubicarAstro(gl, 7, 2.0f, MaterialSistemaMateriales.materialPluton, vIncremento2);
        }
        gl.glPopMatrix();

        gl.glPushMatrix();
        {
            ubicarAstro(gl, 12, 2.5f, MaterialSistemaMateriales.materialEuropa, vIncremento2);
        }
        gl.glPopMatrix();

        gl.glPushMatrix();
        {
            ubicarAstro(gl, 18, 2.75f, MaterialSistemaMateriales.materialLuna, vIncremento2);
        }
        gl.glPopMatrix();

        gl.glPushMatrix();
        {
            ubicarAstro(gl, 25.5f, 3.8f, MaterialSistemaMateriales.materialCallisto, vIncremento2);
        }
        gl.glPopMatrix();

        gl.glPushMatrix();
        {
            ubicarAstro(gl, 34, 3.9f, MaterialSistemaMateriales.materialMercurio, vIncremento2);
        }
        gl.glPopMatrix();

        gl.glPushMatrix();
        {
            ubicarAstro(gl, 43, 4.0f, MaterialSistemaMateriales.materialTitan, vIncremento2);
        }
        gl.glPopMatrix();

        gl.glPushMatrix();
        {
            ubicarAstro(gl, 52, 4.2f, MaterialSistemaMateriales.materialGanymede, vIncremento2);
        }
        gl.glPopMatrix();

        gl.glPushMatrix();
        {
            ubicarAstro(gl, 62.5f, 5.5f, MaterialSistemaMateriales.materialMarte, vIncremento2);
        }
        gl.glPopMatrix();

        gl.glPushMatrix();
        {
            ubicarAstro(gl, 77, 8.0f, MaterialSistemaMateriales.materialVenus, vIncremento2);
        }
        gl.glPopMatrix();

        gl.glPushMatrix();
        {
            ubicarAstro(gl, 95, 8.3f, MaterialSistemaMateriales.materialTierra, vIncremento2);
        }
        gl.glPopMatrix();

        gl.glPushMatrix();
        {
            ubicarAstro(gl, 120, 15.0f, MaterialSistemaMateriales.materialKepler, vIncremento2);
        }
        gl.glPopMatrix();

        gl.glPushMatrix();
        {
            ubicarAstro(gl, 162, 25.00f, MaterialSistemaMateriales.materialNeptuno, vIncremento2);
        }
        gl.glPopMatrix();

        gl.glPushMatrix();
        {
            ubicarAstro(gl, 215, 25.5f, MaterialSistemaMateriales.materialUrano, vIncremento2);
        }
        gl.glPopMatrix();

        gl.glPushMatrix();
        {
            ubicarAstro(gl, 320, 70f, MaterialSistemaMateriales.materialSaturno, vIncremento2);
        }
        gl.glPopMatrix();

        gl.glPushMatrix();
        {
            ubicarAstro(gl, 480, 75, MaterialSistemaMateriales.materialJupiter, vIncremento2);
        }
        gl.glPopMatrix();

        gl.glPushMatrix();
        {
            ubicarAstro(gl, 1150, 550, MaterialSistemaMateriales.materialSol, vIncremento2);
        }
        gl.glPopMatrix();

        gl.glPushMatrix();
        {
            ubicarAstro(gl, 2700, 850f, MaterialSistemaMateriales.materialSiriusA, vIncremento2);
        }
        gl.glPopMatrix();

        gl.glPushMatrix();
        {
            ubicarAstro(gl, 5700, 2000, MaterialSistemaMateriales.materialElnath, vIncremento2);
        }
        gl.glPopMatrix();

        gl.glPushMatrix();
        {
            ubicarAstro(gl, 12000, 4000, MaterialSistemaMateriales.materialPollux, vIncremento2);
        }
        gl.glPopMatrix();

        gl.glPushMatrix();
        {
            ubicarAstro(gl, 25000, 8000, MaterialSistemaMateriales.materialArcturus, vIncremento2);
        }
        gl.glPopMatrix();

        vIncremento += 0.1f;
        vIncremento2 += 0.5f;
        vIncremento3 += 0.1f;

    }

    public void ubicarAstro(GL10 gl, float distancia, float escala, float[] material, float incremento) {
        gl.glMaterialfv(gl.GL_FRONT_AND_BACK, gl.GL_EMISSION, material, 0);
        gl.glTranslatef(distancia, 3.5f, 0);
        gl.glScalef(escala, escala, escala);
        gl.glRotatef(incremento, 0, 1, 0);
        esfera.dibujar(gl);
    }

}


