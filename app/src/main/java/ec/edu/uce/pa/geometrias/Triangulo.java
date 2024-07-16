package ec.edu.uce.pa.geometrias;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Triangulo {

    private FloatBuffer bufferVertices1, bufferColor;
    private ByteBuffer bufferIndice;
    private final static int componentesVertices = 2,componentesColores = 4;
    private  final static int byteFlotante = 4;

    public Triangulo() {

        float [] vertices1 = {
                1.0f, -1.0f,
                -1.0f,-1.0f,
                -1.0f,1.0f,
                1.0f,1.0f
        };

        float [] colores = {
                1.0f, 0.0f, 1.0f, 1.0f,
                0.5f, 0.5f, 1.0f, 1.0f,
                0.0f, 0.0f, 0.5f, 1.0f,
                1.0f, 0.0f, 1.0f, 1.0f
        };

        byte [] indices = {
            3,0,1,
                3,1,2
        };


        ByteBuffer buffer = ByteBuffer.allocateDirect(vertices1.length*byteFlotante);
        buffer.order(ByteOrder.nativeOrder());
        bufferVertices1 = buffer.asFloatBuffer();
        bufferVertices1.put(vertices1);
        bufferVertices1.position(0);

        buffer = ByteBuffer.allocateDirect(colores.length*byteFlotante);
        buffer.order(ByteOrder.nativeOrder());
        bufferColor= buffer.asFloatBuffer();
        bufferColor.put(colores);
        bufferColor.position(0);

        bufferIndice = ByteBuffer.allocateDirect(indices.length*4);
        bufferIndice.order(ByteOrder.nativeOrder());
        bufferIndice.put(indices);
        bufferIndice.position(0);
    }

    public void dibujar(GL10 gl){
        gl.glFrontFace(gl.GL_CW);

        bufferVertices1.position(0);
        gl.glVertexPointer(componentesVertices, gl.GL_FLOAT, 0, bufferVertices1);
        gl.glEnableClientState(gl.GL_VERTEX_ARRAY);

        bufferColor.position(0);
        gl.glColorPointer(componentesColores, gl.GL_FLOAT, 0, bufferColor);
        gl.glEnableClientState(gl.GL_COLOR_ARRAY);

        gl.glDrawElements(gl.GL_TRIANGLES, 6,gl.GL_UNSIGNED_BYTE, bufferIndice);

        gl.glFrontFace(gl.GL_CCW);
        gl.glDisableClientState(gl.GL_VERTEX_ARRAY);
        gl.glDisableClientState(gl.GL_COLOR_ARRAY);

    }
}
