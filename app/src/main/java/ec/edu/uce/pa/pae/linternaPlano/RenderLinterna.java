package ec.edu.uce.pa.pae.linternaPlano;

import static android.opengl.GLES10.GL_LIGHT0;

import android.content.Context;
import android.opengl.GLSurfaceView;

import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.R;


public class RenderLinterna implements GLSurfaceView.Renderer {
    //public static float x = 0.0f;
   // public static float y = 0.0f;
    public static float z = -3.0f;

    private final static int LUZ1 = GL_LIGHT0;
    float[] posicionLuz = {
            0.0f, 0.0f, -3.0f, 1.0f
    };
    float[] spotDir = {
            -1.0f, 0.0f, 0
    };

    private PlanoMaterial planoMaterial;
    private ConoBlend conoBlend;
    float[] coloriluminacion = {
            1.0f, 0.5f, 0.0f, 1.0f
    };
    float intencidadLuz = 0.5f;
    float[] colorLuz = {
            coloriluminacion[0], coloriluminacion[1], coloriluminacion[2], intencidadLuz
    };
    float intecidadFoco=0.45f;

    float [] colorFoco = {
            1.0f,1.0f,0.0f,intecidadFoco
    };


    private ConoLintarna conoFocoLampara, conoBaseLampara;
    float[] colorGris = {0.5f, 0.5f, 0.5f, 1.0f};

    private Linea linea;

    private PlanoTextura planoTextura;

    private Foco foco;


    private int[] arrayTecturas;

    private float[] materialBlanco = {1.0f, 1.0f, 1.0f, 1.0f};
    private float[] materialAmarillo = {1.0f, 1.0f, 0.0f, 1.0f};

    private Context context;
    private float vIncremento = 0.0f;

    public RenderLinterna(Context contexto) {

        this.context = contexto;


    }


    @Override
    //todo lo que quiero inizializar sin realizar cambios
    public void onSurfaceCreated(GL10 gl, EGLConfig eglConfig) {

        gl.glClearColor(0.23f, 0.24f, 0.25f, 1.0f);
        gl.glEnable(gl.GL_DEPTH_TEST);

        conoBlend = new ConoBlend(-1.3f, 0.46f, colorLuz);
        conoFocoLampara = new ConoLintarna(-0.5f, 0.45f, colorGris);
        conoBaseLampara = new ConoLintarna(0.5f, 0.7f, colorGris);
        planoMaterial = new PlanoMaterial();

        foco = new Foco(20, 20, 0.5f, 1.0f,colorFoco);

        linea = new Linea(colorGris);

        arrayTecturas = FuncionesLampara.habilitarTexturas(gl, 2);

        FuncionesLampara.cargarImagenesTextura(gl, context, 0, R.drawable.metal, arrayTecturas);
        FuncionesLampara.cargarImagenesTextura(gl, context, 1, R.drawable.piso, arrayTecturas);

        planoTextura = new PlanoTextura();


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
        gl.glTranslatef(0.0f, 0.0f, -3.0f);
        gl.glRotatef(0, 0, 1, 0);
        gl.glRotatef(0, 1, 0, 0);

        gl.glPushMatrix();
        {
            gl.glDisable(gl.GL_DEPTH_TEST);
            gl.glEnable(gl.GL_BLEND);
            gl.glBlendFunc(gl.GL_SRC_ALPHA, gl.GL_SRC_ALPHA);
            gl.glTranslatef(-1.2f, 0.0f, -0.55f);
            gl.glRotatef(90, 0, 0, 1);

            conoBlend.dibujar(gl);
        }
        gl.glPopMatrix();

        gl.glPushMatrix();
        {
            gl.glTranslatef(-0.10f, 0.0f, 0);
            float ef =0.30f;
            gl.glScalef(ef,ef,ef);
            foco.dibujar(gl);
        }
        gl.glPopMatrix();

        gl.glPushMatrix();
        {
            gl.glRotatef(90, 1, 0, 0);
            gl.glRotatef(-90, 0, 0, 1);

            gl.glLightfv(LUZ1, gl.GL_POSITION, FuncionesLampara.generarBuffer(posicionLuz));
            gl.glLightfv(LUZ1, gl.GL_DIFFUSE, FuncionesLampara.generarBuffer(coloriluminacion));
            gl.glLightfv(LUZ1, gl.GL_SPOT_DIRECTION, FloatBuffer.wrap(spotDir));
            gl.glLightf(LUZ1, gl.GL_SPOT_CUTOFF, 22);
            gl.glLightf(LUZ1, gl.GL_SPOT_EXPONENT, 1);
            gl.glEnable(gl.GL_LIGHTING);
            gl.glEnable(LUZ1);

            gl.glMaterialfv(gl.GL_FRONT_AND_BACK, gl.GL_AMBIENT, materialBlanco, 0);
            planoMaterial.dibujar(gl);
        }
        gl.glPopMatrix();

        gl.glDisable(gl.GL_BLEND);
        gl.glPushMatrix();
        {
            gl.glPushMatrix();
            {
                gl.glTranslatef(0, -1.0f, 0);
                gl.glBindTexture(gl.GL_TEXTURE_2D, arrayTecturas[1]);
                planoTextura.dibujar(gl);
            }
            gl.glPopMatrix();

            gl.glDisable(gl.GL_TEXTURE);
            gl.glDisable(gl.GL_LIGHTING);
            gl.glBindTexture(gl.GL_TEXTURE_2D, arrayTecturas[0]);

            gl.glPushMatrix();
            {
                gl.glTranslatef(-0.15f, 0.0f, -0.5f);
                gl.glRotatef(90, 0, 0, 1);

                conoFocoLampara.dibujar(gl);
            }
            gl.glPopMatrix();

            gl.glPushMatrix();
            {
                gl.glTranslatef(0, -1f, 0);
                float e = 0.5f;
                gl.glScalef(e, e, e);
                conoBaseLampara.dibujar(gl);
            }
            gl.glPopMatrix();

            gl.glPushMatrix();
            {
                linea.dibujar(gl);
            }
            gl.glPopMatrix();
        }
        gl.glPopMatrix();
    }


}
