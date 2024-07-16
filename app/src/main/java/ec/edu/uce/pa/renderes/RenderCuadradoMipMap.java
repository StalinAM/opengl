package ec.edu.uce.pa.renderes;



import android.content.Context;
import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.R;
import ec.edu.uce.pa.geometrias.CuadradoMipMap;


public class RenderCuadradoMipMap implements GLSurfaceView.Renderer {

    float rotacion = 0.0f;
    private CuadradoMipMap cuadradoMipMap;
    private int[] arrayTexturas;
    private Context context;

    public RenderCuadradoMipMap(Context contexto){
        this.context = contexto;
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        gl.glClearColor(0.3f,0.3f,0.3f,1); //0.3,0,0.3
        gl.glEnable(gl.GL_DEPTH_TEST);
        cuadradoMipMap = new CuadradoMipMap(
                new float[]{
                        1.0f,1.0f,0.0f,
                        1.0f,-1.0f,0.0f,
                        -1.0f,-1.0f,0.0f,
                        -1.0f,1.0f,0.0f

                },
                new byte[]{
                        0,1,2,
                        0,2,3
                },
                new float[]{
                        1.0f, 0.0f,
                        1.0f, 1.0f,
                        0.0f, 1.0f,
                        0.0f,0.0f
                },
                new float[]{
                        0.5f,0.5f,0.5f,0.5f
                },
                true

        );
        cuadradoMipMap.habilitarTexturas(gl,1);
        cuadradoMipMap.cargarTextura(gl,this.context,R.drawable.nivel0,0,0);
        cuadradoMipMap.cargarTextura(gl,this.context,R.drawable.nivel1,0,1);
        cuadradoMipMap.cargarTextura(gl,this.context,R.drawable.nivel2,0,2);
        cuadradoMipMap.cargarTextura(gl,this.context,R.drawable.nivel3,0,3);
        cuadradoMipMap.cargarTextura(gl,this.context,R.drawable.nivel4,0,4);
        cuadradoMipMap.cargarTextura(gl,this.context,R.drawable.nivel5,0,5);
        cuadradoMipMap.cargarTextura(gl,this.context,R.drawable.nivel6,0,6);
        cuadradoMipMap.cargarTextura(gl,this.context,R.drawable.nivel7,0,7);
        cuadradoMipMap.cargarTextura(gl,this.context,R.drawable.nivel8,0,8);
        cuadradoMipMap.cargarTextura(gl,this.context,R.drawable.nivel9,0,9);

        /*
        cuadradoMipMap.habilitarTexturas(gl,1);
        cuadradoMipMap.cargarTextura(gl,this.context, R.drawable.nivel0,0,0);
        cuadradoMipMap.cargarTextura(gl,this.context, R.drawable.nivel1,0,1);
        cuadradoMipMap.cargarTextura(gl,this.context, R.drawable.nivel2,0,2);
        cuadradoMipMap.cargarTextura(gl,this.context, R.drawable.nivel3,0,3);
        cuadradoMipMap.cargarTextura(gl,this.context, R.drawable.nivel4,0,4);
        cuadradoMipMap.cargarTextura(gl,this.context, R.drawable.nivel5,0,5);
        cuadradoMipMap.cargarTextura(gl,this.context, R.drawable.nivel6,0,6);
        cuadradoMipMap.cargarTextura(gl,this.context, R.drawable.nivel7,0,7);
        cuadradoMipMap.cargarTextura(gl,this.context, R.drawable.nivel8,0,8);

         */

    }
    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        float relacionAspecto = (float) width/(float)height;
        gl.glViewport(0,0,width,height);
        gl.glMatrixMode(gl.GL_PROJECTION);
        gl.glFrustumf(-relacionAspecto,relacionAspecto,-1,1,1,100);
        //gl.glTexEnvf(gl.GL_TEXTURE_ENV,gl.GL_TEXTURE_ENV_MODE,gl.GL_REPLACE);
    }

    private boolean validar = false;
    private float incremento = 0;
    private float incremento2 = -0.1f;
    float deltaZ=-2,vIntervalo=-0.1f,vIncremento;

    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClear(gl.GL_COLOR_BUFFER_BIT | gl.GL_DEPTH_BUFFER_BIT);
        gl.glMatrixMode(gl.GL_MODELVIEW);
        gl.glLoadIdentity();

        if(deltaZ < -50 || deltaZ > -2.0f)
            vIntervalo *=-1;

        deltaZ += vIntervalo;
        gl.glTranslatef(0.0f,0.0f,deltaZ);
            cuadradoMipMap.dibujar(gl);

        vIncremento +=1.5f;
    }
}