package ec.edu.uce.pa.geometrias;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.utilidades.Funciones;

public class CuadradoBlend {

    private FloatBuffer bufferVertices;
    private ByteBuffer bufferIndice;
    private final static int componentesVertices = 2;
    private float [] color;


    public CuadradoBlend(float []color) {

        this.color=color;

        float [] vertices = {
                1.0f, -1.0f,
                -1.0f,-1.0f,
                -1.0f,1.0f,
                1.0f,1.0f
        };


        byte [] indices = {
            3,0,1,
                3,1,2
        };


        bufferVertices= Funciones.generarFloatBuffer(vertices);
        bufferIndice=Funciones.generarByteBuffer(indices);

    }

    public void dibujar(GL10 gl){
        gl.glFrontFace(gl.GL_CW);

        bufferVertices.position(0);
        gl.glVertexPointer(componentesVertices, gl.GL_FLOAT, 0, bufferVertices);
        gl.glEnableClientState(gl.GL_VERTEX_ARRAY);

        gl.glColor4f(color[0],color[1],color[2],color[3]);

        gl.glDrawElements(gl.GL_TRIANGLES, 6,gl.GL_UNSIGNED_BYTE, bufferIndice);

        gl.glFrontFace(gl.GL_CCW);
        gl.glDisableClientState(gl.GL_VERTEX_ARRAY);
        gl.glDisableClientState(gl.GL_COLOR_ARRAY);

    }
}
