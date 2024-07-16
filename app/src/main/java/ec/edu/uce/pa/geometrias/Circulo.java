package ec.edu.uce.pa.geometrias;

import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.utilidades.Funciones;

public class Circulo {

    private FloatBuffer bufferVertices;
    private FloatBuffer bufferColores;

    private final static int componentesVertices = 2;
    private final static int componentesColores = 4;

    private static int puntos=40;


    private float [] vertices;
    private float [] colores;
    public Circulo() {
        vertices = myArrayVertives();
        colores=myArrayColores();
        bufferVertices= Funciones.generarFloatBuffer(vertices);
        bufferColores= Funciones.generarFloatBuffer(colores);
    }

    public static  float [] myArrayVertives(){
        float[] v = new float [puntos*2];
        v [0] = 0.0f;
        v [1] = 0.0f;
        double angulo = 0;
        for(int i=2; i<v.length; i += 2){
            v[i] = (float)(Math.sin(Math.toRadians(angulo)));//X
            v[i+1] = (float)(Math.cos(Math.toRadians(angulo)));//Y
            angulo += 10; //sube de 10 en 10 los grados

        }
        v [v.length-2] = v [2];
        v [v.length-1] = v [3];
        return v;
    }

    public static  float [] myArrayColores(){
        float[] co = new float [puntos*4];
        co [0] = 1.0f;
        co [1] = 0.0f;
        co [2] = 0.0f;
        co [3] = 1.0f;

        for (int i=4; i < co.length; i += 4){
            co[i] = 1.0f; //rojo
            co[i+1] = 0.7f; //verde
            co[i+2] = 0.0f; //azul
            co[i+3] = 1.0f; //alfa
        }
        return co;
    }


    public void dibujar (GL10 gl){
        gl.glFrontFace(gl.GL_CW);

        bufferVertices.position(0);
        gl.glVertexPointer(componentesVertices, gl.GL_FLOAT, 0, bufferVertices);
        gl.glEnableClientState(gl.GL_VERTEX_ARRAY);

        bufferColores.position(0);
        gl.glColorPointer(componentesColores, gl.GL_FLOAT, 0, bufferColores);
        gl.glEnableClientState(gl.GL_COLOR_ARRAY);

        gl.glDrawArrays(gl.GL_TRIANGLE_FAN,0,puntos);// es ultimo valor pertenece a la cantidad de vertices que en este caso es la cantidad e valores

        gl.glFrontFace(gl.GL_CCW);
        gl.glDisableClientState(gl.GL_VERTEX_ARRAY);
        gl.glDisableClientState(gl.GL_COLOR_ARRAY);
    }
}
