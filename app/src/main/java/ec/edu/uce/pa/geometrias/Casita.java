package ec.edu.uce.pa.geometrias;

import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.utilidades.Funciones;

public class Casita {

    private FloatBuffer bufferVertices;
    private FloatBuffer bufferColores;

    private final static int componentesVertices = 2;
    private final static int componentesColores = 4;

    private float[] vertices;
    private float[] colores;

    public Casita() {
        vertices = myArrayVertives();
        colores = myArrayColores(vertices.length);
        bufferVertices = Funciones.generarFloatBuffer(vertices);
        bufferColores = Funciones.generarFloatBuffer(colores);
    }

    public static float[] myArrayVertives() {

        float a = 0.3f;

        float[] v = {

                0.0f, 0.0f,   //0
                0.0f, 1.0f,   //1
                0.5f, 1.0f,   //2

                1.0f, a,      //3
                1.0f, -1.0f,  //4
                0.0f, -1.0f,  //5

                -1.0f, -1.0f, //6
                -1.0f, a,     //7
                -0.5f, 1.0f,  //8

                0.0f, 1.0f   //9

        };
        return v;
    }

    public static float[] myArrayColores(int puntos) {
        float R = 0.5f, G = 0.1f, B = 0.2f, A = 1.0f;
        float[] co = {
                R, G, B, A,
                R, G, B, A,
                R, G, B, A,

                R, G, B, A,
                R, G, B, A,
                R, G, B, A,

                R, G, B, A,
                R, G, B, A,
                R, G, B, A,

                R, G, B, A
        };
        return co;
    }

    public void dibujar(GL10 gl) {
        gl.glFrontFace(gl.GL_CW);

        bufferVertices.position(0);
        gl.glVertexPointer(componentesVertices, gl.GL_FLOAT, 0, bufferVertices);
        gl.glEnableClientState(gl.GL_VERTEX_ARRAY);

        bufferColores.position(0);
        gl.glColorPointer(componentesColores, gl.GL_FLOAT, 0, bufferColores);
        gl.glEnableClientState(gl.GL_COLOR_ARRAY);

        gl.glDrawArrays(gl.GL_TRIANGLE_FAN, 0, 10);

        gl.glFrontFace(gl.GL_CCW);
        gl.glDisableClientState(gl.GL_VERTEX_ARRAY);
        gl.glDisableClientState(gl.GL_COLOR_ARRAY);
    }
}
