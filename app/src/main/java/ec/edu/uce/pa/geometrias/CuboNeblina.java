package ec.edu.uce.pa.geometrias;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class CuboNeblina {
    private FloatBuffer bufferVertices, bufferColores;
    private ByteBuffer bufferIndices;
    private final static int byteFlotante = 4, compVertices = 3, compColores=4;

    public CuboNeblina(){
        float [] vertices={
                -1.0f,1.0f,1.0f,   //0
                1.0f,1.0f,1.0f,   //1
                1.0f,-1.0f,1.0f,   //2
                -1.0f,-1.0f,1.0f,   //3

                -1.0f,1.0f,-1.0f,   //4
                1.0f,1.0f,-1.0f,   //5
                1.0f,-1.0f,-1.0f,   //6
                -1.0f,-1.0f,-1.0f   //7
        };

        byte[] indices = {
                0,1,2,
                0,2,3,

                0,4,5,
                0,5,1,

                0,4,7,
                0,7,3,

                6,2,3,
                6,3,7,

                6,2,1,
                6,1,5,

                6,7,4,
                6,4,5
        };

        ByteBuffer buffer = ByteBuffer.allocateDirect(vertices.length*byteFlotante);
        buffer.order(ByteOrder.nativeOrder());
        bufferVertices = buffer.asFloatBuffer();
        bufferVertices.put(vertices);

        bufferIndices = ByteBuffer.allocateDirect(indices.length*4);
        bufferIndices.order(ByteOrder.nativeOrder());
        bufferIndices.put(indices);


    }

    public void dibujarCubo(GL10 gl){
        gl.glFrontFace(gl.GL_CW);

        bufferVertices.position(0);
        gl.glVertexPointer(compVertices, gl.GL_FLOAT, 0, bufferVertices);
        gl.glEnableClientState(gl.GL_VERTEX_ARRAY);

        /*bufferIndices.position(0);
        gl.glColor4f(0.5f,0.5f,0.0f,1.0f);
        gl.glDrawElements(gl.GL_TRIANGLE_FAN, 2*3,gl.GL_UNSIGNED_BYTE, bufferIndices);*/

        bufferIndices.position(6);
        gl.glColor4f(0.5f,0.5f,0.0f,1.0f);
        gl.glDrawElements(gl.GL_TRIANGLE_FAN, 2*3,gl.GL_UNSIGNED_BYTE, bufferIndices);

        bufferIndices.position(12);
        gl.glColor4f(0.0f,0.5f,0.5f,1.0f);
        gl.glDrawElements(gl.GL_TRIANGLE_FAN, 2*3,gl.GL_UNSIGNED_BYTE, bufferIndices);

        bufferIndices.position(18);
        gl.glColor4f(0.5f,0.0f,0.5f,1.0f);
        gl.glDrawElements(gl.GL_TRIANGLE_FAN, 2*3,gl.GL_UNSIGNED_BYTE, bufferIndices);

        bufferIndices.position(24);
        gl.glColor4f(0.5f,0.2f,0.1f,1.0f);
        gl.glDrawElements(gl.GL_TRIANGLE_FAN, 2*3,gl.GL_UNSIGNED_BYTE, bufferIndices);

        bufferIndices.position(30);
        gl.glColor4f(0.0f,0.5f,0.0f,1.0f);
        gl.glDrawElements(gl.GL_TRIANGLE_FAN, 2*3,gl.GL_UNSIGNED_BYTE, bufferIndices);

        gl.glFrontFace(gl.GL_CCW);
        gl.glDisableClientState(gl.GL_VERTEX_ARRAY);
        gl.glDisableClientState(gl.GL_COLOR_ARRAY);

    }
}

