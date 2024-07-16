package ec.edu.uce.pa.pae.universoEscalaTexturas;

import static javax.microedition.khronos.opengles.GL10.GL_LIGHT0;

import android.content.Context;
import android.opengl.GLSurfaceView;

import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.R;
import ec.edu.uce.pa.pae.universoEscalaMateriales.MaterialSistemaMateriales;


public class RenderSistemaConTexturas implements GLSurfaceView.Renderer {

    private float vIncremento = 0.0f;
    private float vIncremento2 = 0.0f;
    private float vIncremento3 = -5.0f;
    private AstroSistemaTexturas esfera;
    private AnilloSistemaTexturas anilloSaturno;
    private AnilloSistemaTexturas anilloUrano;
    private StarsSistemaTexturas estrellas;
    private float distancia = 0;
    private final static int LUZ0 = GL_LIGHT0;
    private Context context;
    public static float x = 0.5f;
    public static float y = 0.5f;
    public static float z = 0.5f;
    float[] Luzblanca = {0.1f, 0.1F, 0.1f, 1.0f};
    float[] colorAmbiental1 = {
            1.0f, 1.0f, 1.0f, 1.0f
    };

    public RenderSistemaConTexturas(Context contexto) {
        this.context = contexto;
        esfera = new AstroSistemaTexturas(50, 50, 1.0f, 1.0f);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig eglConfig) {

        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        gl.glEnable(gl.GL_DEPTH_TEST);
        gl.glDepthFunc(gl.GL_LESS);

        esfera.habilitarTexturas(gl, 22);
        esfera.cargarImagenesTextura(gl, context, 0, R.drawable.ceres);
        esfera.cargarImagenesTextura(gl, context, 1, R.drawable.makemake);
        esfera.cargarImagenesTextura(gl, context, 2, R.drawable.pluton);
        esfera.cargarImagenesTextura(gl, context, 3, R.drawable.europa);
        esfera.cargarImagenesTextura(gl, context, 4, R.drawable.luna);
        esfera.cargarImagenesTextura(gl, context, 5, R.drawable.callisto);
        esfera.cargarImagenesTextura(gl, context, 6, R.drawable.mercurio);
        esfera.cargarImagenesTextura(gl, context, 7, R.drawable.titan);
        esfera.cargarImagenesTextura(gl, context, 8, R.drawable.ganymade);
        esfera.cargarImagenesTextura(gl, context, 9, R.drawable.marte);
        esfera.cargarImagenesTextura(gl, context, 10, R.drawable.venus);
        esfera.cargarImagenesTextura(gl, context, 11, R.drawable.tierra);
        esfera.cargarImagenesTextura(gl, context, 12, R.drawable.kepler);
        esfera.cargarImagenesTextura(gl, context, 13, R.drawable.neptuno);
        esfera.cargarImagenesTextura(gl, context, 14, R.drawable.urano);
        esfera.cargarImagenesTextura(gl, context, 15, R.drawable.saturno);
        esfera.cargarImagenesTextura(gl, context, 16, R.drawable.jupiter);
        esfera.cargarImagenesTextura(gl, context, 17, R.drawable.sol);
        esfera.cargarImagenesTextura(gl, context, 18, R.drawable.siriusa);
        esfera.cargarImagenesTextura(gl, context, 19, R.drawable.elnath);
        esfera.cargarImagenesTextura(gl, context, 20, R.drawable.pollux);
        esfera.cargarImagenesTextura(gl, context, 21, R.drawable.acturus);
        anilloSaturno = new AnilloSistemaTexturas(1.8f, 1.5f, 36);
        anilloUrano = new AnilloSistemaTexturas(1.51f, 1.5f, 36);
        estrellas = new StarsSistemaTexturas(5000, 40000);
        gl.glEnable(gl.GL_LIGHTING);
        gl.glEnable(LUZ0);


    }


    @Override
    public void onSurfaceChanged(GL10 gl, int ancho, int alto) {
        float relacionAspecto = (float) ancho / (float) alto;
        gl.glViewport(0, 0, ancho, alto);
        gl.glMatrixMode(gl.GL_PROJECTION);
        gl.glFrustumf(-relacionAspecto, relacionAspecto, -1, 1, 1, 60000);
        gl.glTexEnvf(gl.GL_TEXTURE_ENV, gl.GL_TEXTURE_ENV_MODE, gl.GL_REPLACE);
    }


    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClear(gl.GL_COLOR_BUFFER_BIT | gl.GL_DEPTH_BUFFER_BIT);
        gl.glMatrixMode(gl.GL_MODELVIEW);
        gl.glLoadIdentity();

        float[] posicion = {25000.0f, 3.5f, -16000.0f, 1.0f};
        gl.glLightfv(LUZ0, gl.GL_POSITION, FuncionesSistemaTexturas.generarBuffer(posicion));
        gl.glLightfv(LUZ0, gl.GL_AMBIENT, FuncionesSistemaTexturas.generarBuffer(colorAmbiental1));

        gl.glTranslatef(-4.0f, -3.0f, -9.0f);

        gl.glPushMatrix();{
            gl.glTranslatef(10,3,0);
            gl.glMaterialfv(gl.GL_FRONT_AND_BACK, gl.GL_EMISSION, MaterialSistemaMateriales.materialEstrella, 0);
            estrellas.dibujar(gl);
        }gl.glPopMatrix();


        gl.glTranslatef(x, -3, z);
        gl.glEnable(gl.GL_FOG);
        gl.glFogfv(gl.GL_FOG_COLOR, FloatBuffer.wrap(Luzblanca));
        gl.glFogf(gl.GL_FOG_MODE, gl.GL_LINEAR);
        gl.glFogf(gl.GL_FOG_START, -9.0F);
        gl.glFogf(gl.GL_FOG_END, 1700.0F);

        gl.glPushMatrix();
        {

            ubicarAstro(gl, 0, 1.0f, vIncremento2, 0);//Ceres

        }
        gl.glPopMatrix();

        gl.glPushMatrix();
        {
            ubicarAstro(gl, 3, 1.50f, vIncremento2, 1);//MakeMake
        }
        gl.glPopMatrix();

        gl.glPushMatrix();
        {
            ubicarAstro(gl, 7, 2.0f, vIncremento2, 2);//Pluton
        }
        gl.glPopMatrix();

        gl.glPushMatrix();
        {
            ubicarAstro(gl, 12, 2.5f, vIncremento2, 3);//Europa
        }
        gl.glPopMatrix();

        gl.glPushMatrix();
        {
            ubicarAstro(gl, 18, 2.75f, vIncremento2, 4);//Luna
        }
        gl.glPopMatrix();

        gl.glPushMatrix();
        {
            ubicarAstro(gl, 25.5f, 3.8f, vIncremento2, 5);//Callisto
        }
        gl.glPopMatrix();

        gl.glPushMatrix();
        {
            ubicarAstro(gl, 34, 3.9f, vIncremento2, 6);//Mercurio
        }
        gl.glPopMatrix();

        gl.glPushMatrix();
        {
            ubicarAstro(gl, 43, 4.0f, vIncremento2, 7);//Titan
        }
        gl.glPopMatrix();

        gl.glPushMatrix();
        {
            ubicarAstro(gl, 52, 4.2f, vIncremento2, 8);//Genymade
        }
        gl.glPopMatrix();

        gl.glPushMatrix();
        {
            ubicarAstro(gl, 62.5f, 5.5f, vIncremento2, 9);//Marte
        }
        gl.glPopMatrix();

        gl.glPushMatrix();
        {
            ubicarAstro(gl, 77, 8.0f, vIncremento2, 10);//Venus
        }
        gl.glPopMatrix();

        gl.glPushMatrix();
        {
            ubicarAstro(gl, 95, 8.3f, vIncremento2, 11);//Tierra
        }
        gl.glPopMatrix();

        gl.glPushMatrix();
        {
            ubicarAstro(gl, 120, 15.0f, vIncremento2, 12);//Kepler
        }
        gl.glPopMatrix();

        gl.glPushMatrix();
        {
            ubicarAstro(gl, 164, 25.00f, vIncremento2, 13);//Neptuno
        }
        gl.glPopMatrix();

        float distanciaUrano = 230;

        gl.glPushMatrix();
        {
            ubicarAstro(gl, distanciaUrano, 25.5f, vIncremento2, 14);//Urano
        }
        gl.glPopMatrix();

        gl.glPushMatrix();
        {
            gl.glTranslatef(distanciaUrano, 3.5f, 0);
            float e = 22.0f;
            gl.glRotatef(90, 1, 0, 1);
            gl.glScalef(e, e, e);
            anilloUrano.dibujar(gl);

        }
        gl.glPopMatrix();

        gl.glPushMatrix();
        {
            gl.glTranslatef(distanciaUrano, 3.5f, 0);
            float e = 24.0f;
            gl.glRotatef(90, 1, 0, 1);
            gl.glScalef(e, e, e);
            anilloUrano.dibujar(gl);

        }
        gl.glPopMatrix();

        gl.glPushMatrix();
        {
            gl.glTranslatef(distanciaUrano, 3.5f, 0);
            float e = 26.0f;
            gl.glRotatef(90, 1, 0, 1);
            gl.glScalef(e, e, e);
            anilloUrano.dibujar(gl);

        }
        gl.glPopMatrix();

        float distanciaSaturno = 330;

        gl.glPushMatrix();
        {
            ubicarAstro(gl, distanciaSaturno, 50f, vIncremento2, 15);//Saturno

        }
        gl.glPopMatrix();

        gl.glPushMatrix();
        {
            gl.glTranslatef(distanciaSaturno, 3.5f, 0);
            gl.glRotatef(90, 1, 0, 1);
            float e = 45.0f;
            gl.glScalef(e, e, e);
            anilloSaturno.dibujar(gl);
        }
        gl.glPopMatrix();

        gl.glPushMatrix();
        {
            ubicarAstro(gl, 480, 75, vIncremento2, 16);//Jupiter

        }
        gl.glPopMatrix();

        gl.glDisable(gl.GL_FOG);

        gl.glEnable(gl.GL_FOG);
        gl.glFogfv(gl.GL_FOG_COLOR, FloatBuffer.wrap(Luzblanca));
        gl.glFogf(gl.GL_FOG_MODE, gl.GL_LINEAR);
        gl.glFogf(gl.GL_FOG_START, 300.0F);
        gl.glFogf(gl.GL_FOG_END, 18000.0F);

        gl.glPushMatrix();
        {

            ubicarAstro(gl, 1150, 550, vIncremento2, 17);//Sol
        }
        gl.glPopMatrix();

        gl.glPushMatrix();
        {
            ubicarAstro(gl, 2700, 850f, vIncremento2, 18);//Sirius
        }
        gl.glPopMatrix();

        gl.glPushMatrix();
        {
            ubicarAstro(gl, 5700, 2000, vIncremento2, 19);//Elnath
        }
        gl.glPopMatrix();

        gl.glPushMatrix();
        {
            ubicarAstro(gl, 12000, 4000, vIncremento2, 20);//Pollux
        }
        gl.glPopMatrix();

        gl.glPushMatrix();
        {
            ubicarAstro(gl, 25000, 8000, vIncremento2, 21);//Arcturus
            gl.glDisable(gl.GL_FOG);
        }
        gl.glPopMatrix();

        gl.glDisable(gl.GL_FOG);

        vIncremento += 0.1f;
        vIncremento2 += 0.5f;
        vIncremento3 += 0.1f;

    }

    public void ubicarAstro(GL10 gl, float distancia, float escala, float incremento, int indice) {
        gl.glTranslatef(distancia, 3.5f, 0);
        gl.glScalef(escala, escala, escala);
        gl.glRotatef(incremento, 0, 1, 0);
        esfera.dibujar(gl, indice);
    }


}


