package ec.edu.uce.pa.geometrias;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.utilidades.Funciones;

public class Cubo {
    private FloatBuffer bufferVertices;
    private FloatBuffer bufferColores,bufferColores1;

    private ByteBuffer bufferIndice1, bufferIndice2;

    private final static int byteFlotante =4;
    private final static int compPorVertice=3;
    private final static int compPorColores=4;
    public Cubo() {
        float[] vertices = {
                -1.0f, 1.0f, 1.0f, //0
                1.0f, 1.0f, 1.0f, //1
                1.0f, -1.0f, 1.0f,  //2
                -1.0f, -1.0f, 1.0f,  //3

                -1.0f, 1.0f, -1.0f, //4
                1.0f, 1.0f, -1.0f, //5
                1.0f, -1.0f, -1.0f,//6
                -1.0f, -1.0f, -1.0f, //7
        };
        float[] colores = {

                0.5f, 0.0f, 0.0f, 1.0f,
                0.0f, 0.5f, 0.0f, 1.0f,
                0.0f, 0.0f, 0.5f, 1.0f,
                0.0f, 0.5f, 0.0f, 1.0f,

                0.0f, 0.0f, 0.5f, 1.0f,
                0.0f, 0.5f, 0.0f, 1.0f,
                0.5f, 0.0f, 0.0f, 1.0f,
                0.0f, 0.5f, 0.0f, 1.0f

        };

        byte[] indices1 = {

                0, 1, 2,
                0, 2, 3,
                0, 3, 7,
                0, 7, 4,
                0, 4, 5,
                0, 5, 1

        };
        byte[] indices2 = {

                6, 7, 3,
                6, 3, 2,
                6, 2, 1,
                6, 1, 5,
                6, 5, 4,
                6, 4, 7

        };

        bufferVertices = Funciones.generarFloatBuffer(vertices);
        bufferColores = Funciones.generarFloatBuffer(colores);
        bufferIndice1 = Funciones.generarByteBuffer(indices1);
        bufferIndice2 = Funciones.generarByteBuffer(indices2);

    }
    public void dibujar (GL10 gl){
        gl.glFrontFace(gl.GL_CW);
        bufferVertices.position(0);
        gl.glVertexPointer(compPorVertice, gl.GL_FLOAT, 0, bufferVertices);
        gl.glEnableClientState(gl.GL_VERTEX_ARRAY);

        bufferColores.position(0);
        gl.glColorPointer(compPorColores, gl.GL_FLOAT,0,bufferColores);
        gl.glEnableClientState(gl.GL_COLOR_ARRAY);

        gl.glDrawElements(gl.GL_TRIANGLE_FAN,18,gl.GL_UNSIGNED_BYTE, bufferIndice1);
        gl.glDrawElements(gl.GL_TRIANGLE_FAN,18,gl.GL_UNSIGNED_BYTE, bufferIndice2);

        gl.glFrontFace(gl.GL_CW);
        gl.glDisableClientState(gl.GL_VERTEX_ARRAY);
        gl.glDisableClientState(gl.GL_COLOR_ARRAY);
    }
}
