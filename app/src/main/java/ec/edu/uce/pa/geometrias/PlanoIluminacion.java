package ec.edu.uce.pa.geometrias;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.utilidades.Funciones;

public class PlanoIluminacion {

    private FloatBuffer bufferVertices1, bufferColor, bufferNormales;
    private ByteBuffer bufferIndice;
    private final static int componentesVertices = 3,componentesColores = 4;

    public PlanoIluminacion() {

        float [] vertices1 = {
                1.0f, -1.0f, -1.0f,  //0
                1.0f, -1.0f, 1.0f, //1
                -1.0f, -1.0f, 1.0f, //2
                -1.0f, -1.0f, -1.0f  //3

        };

        float [] normales = {
                0,1,0,
                0,1,0,
                0,1,0,
                0,1,0
        };

        float [] colores = {
                1.0f, 0.0f, 0.0f, 1.0f,
                0.0f, 0.0f, 1.0f, 1.0f,
                0.0f, 1.0f, 0.0f, 1.0f,
                1.0f, 0.0f, 1.0f, 1.0f
        };

        byte [] indices = {
            0,2,1,
                0,2,3
        };

        bufferVertices1 = Funciones.generarFloatBuffer(vertices1);
        bufferColor= Funciones.generarFloatBuffer(colores);
        bufferNormales = Funciones.generarFloatBuffer(normales);

        bufferIndice = Funciones.generarByteBuffer(indices);

/*
        bufferIndice = ByteBuffer.allocateDirect(indices.length*4);
        bufferIndice.order(ByteOrder.nativeOrder());
        bufferIndice.put(indices);
        bufferIndice.position(0);

 */
    }

    public void dibujar(GL10 gl){
        gl.glFrontFace(gl.GL_CW);

        bufferVertices1.position(0);
        gl.glVertexPointer(componentesVertices, gl.GL_FLOAT, 0, bufferVertices1);
        gl.glEnableClientState(gl.GL_VERTEX_ARRAY);

        bufferColor.position(0);
        gl.glColorPointer(componentesColores, gl.GL_FLOAT, 0, bufferColor);
        gl.glEnableClientState(gl.GL_COLOR_ARRAY);

        bufferNormales.position(0);
        gl.glNormalPointer(gl.GL_FLOAT, 0,bufferNormales);
        gl.glEnableClientState(gl.GL_NORMAL_ARRAY);

        gl.glDrawElements(gl.GL_TRIANGLES, 6,gl.GL_UNSIGNED_BYTE, bufferIndice);

        gl.glFrontFace(gl.GL_CCW);
        gl.glDisableClientState(gl.GL_VERTEX_ARRAY);
        gl.glDisableClientState(gl.GL_COLOR_ARRAY);
        gl.glDisableClientState(gl.GL_NORMAL_ARRAY);

    }
}
