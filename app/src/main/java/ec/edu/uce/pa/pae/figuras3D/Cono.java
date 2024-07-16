package ec.edu.uce.pa.pae.figuras3D;


import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Cono {

    private FloatBuffer bufferVerticesCono;
    private FloatBuffer bufferColoresCono;

    private FloatBuffer bufferVerticesTapa;
    private FloatBuffer bufferColoresTapa;

    private final static int componentesVertices = 3;
    private final static int componentesColores = 4;

    private static int puntos=42;

    private float [] verticesCono;
    private float [] coloresCono;

    private float [] verticesTapa;
    private float [] coloresTapa;
    public Cono() {
        verticesCono = myArrayVertives(2);
        coloresCono =myArrayColores();
        bufferVerticesCono = FuncionesFiguras.myBuffer(verticesCono);
        bufferColoresCono = FuncionesFiguras.myBuffer(coloresCono);

        verticesTapa=myArrayVertives();
        coloresTapa=myArrayColores();
        bufferVerticesTapa= FuncionesFiguras.myBuffer(verticesTapa);
        bufferColoresTapa= FuncionesFiguras.myBuffer(coloresTapa);

    }

    public static  float [] myArrayVertives(float altura){
        float[] v = new float [puntos*3];
        v [0] = 0.0f;
        v [1] = altura;
        v [2] = 0.0f;
        double angulo = 0;
        for(int i=3; i<v.length; i += 3){
            v[i] = (float)(Math.sin(Math.toRadians(angulo)));//X
            v[i+1] = 0.0f;//Y
            v[i+2] = (float)(Math.cos(Math.toRadians(angulo)));//Z
            angulo += 10; //sube de 10 en 10 los grados

        }
        v [v.length-3] = v [3];
        v [v.length-2] = 0.0f;
        v [v.length-1] = v [5];
        return v;
    }

    public static  float [] myArrayVertives(){
        return myArrayVertives(0.0f);
    }

    public static  float [] myArrayColores(){
        float[] co = new float [puntos*4];
        co [0] = 1.0f;
        co [1] = 0.6f;
        co [2] = 0.0f;
        co [3] = 1.0f;

        for (int i=4; i < co.length; i += 4){
            co[i] = 0.0f;
            co[i+1] = 0.6f;
            co[i+2] = 0.0f;
            co[i+3] = 1.0f;
        }
        return co;
    }

    public void dibujar (GL10 gl){
        gl.glFrontFace(gl.GL_CW);

        bufferVerticesCono.position(0);
        gl.glVertexPointer(componentesVertices, gl.GL_FLOAT, 0, bufferVerticesCono);
        gl.glEnableClientState(gl.GL_VERTEX_ARRAY);

        bufferColoresCono.position(0);
        gl.glColorPointer(componentesColores, gl.GL_FLOAT, 0, bufferColoresCono);
        gl.glEnableClientState(gl.GL_COLOR_ARRAY);

        gl.glDrawArrays(gl.GL_TRIANGLE_FAN,0,puntos);

        bufferVerticesTapa.position(0);
        gl.glVertexPointer(componentesVertices, gl.GL_FLOAT, 0, bufferVerticesTapa);
        gl.glEnableClientState(gl.GL_VERTEX_ARRAY);

        bufferColoresTapa.position(0);
        gl.glColorPointer(componentesColores, gl.GL_FLOAT, 0, bufferColoresTapa);
        gl.glEnableClientState(gl.GL_COLOR_ARRAY);

        gl.glDrawArrays(gl.GL_TRIANGLE_FAN,0,puntos);

        gl.glFrontFace(gl.GL_CCW);
        gl.glDisableClientState(gl.GL_VERTEX_ARRAY);
        gl.glDisableClientState(gl.GL_COLOR_ARRAY);

        gl.glDisableClientState(gl.GL_VERTEX_ARRAY);
        gl.glDisableClientState(gl.GL_COLOR_ARRAY);
    }
}
