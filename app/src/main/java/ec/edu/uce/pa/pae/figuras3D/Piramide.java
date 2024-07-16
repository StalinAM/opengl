package ec.edu.uce.pa.pae.figuras3D;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Piramide {
    private FloatBuffer bufferVertices;
    private FloatBuffer bufferColores;

    private ByteBuffer bufferIndices;
    private final static int byteFlotante = 4;
    private final static int compPorVertice = 3;
    private final static int compPorColores = 4;

    public Piramide() {
        float[] vertices = {
                0.0f,  0.5f, 0.0f,   // Top vertex
                -0.5f, -0.5f, 0.5f,   // Bottom-left front vertex
                0.5f, -0.5f, 0.5f,   // Bottom-right front vertex
                0.5f, -0.5f, -0.5f,  // Bottom-right back vertex
                -0.5f, -0.5f, -0.5f   // Bottom-left back vertex
        };
        float[] colores = {
                // Front face
                1.0f, 0.0f, 0.0f, 1.0f, // Red
                0.0f, 1.0f, 0.0f, 1.0f, // Red
                0.0f, 0.0f, 1.0f, 1.0f, // Red
                1.0f, 0.0f, 0.0f, 1.0f, // Red
                0.0f, 1.0f, 0.0f, 1.0f, // Red

        };
        byte[] indices = {
                0, 1, 2,  // Front face
                0, 2, 3,  // Right face
                0, 3, 4,  // Back face
                0, 4, 1,  // Left face
                1, 2, 3,  // Bottom face (triangles to form a quad)
                3, 4, 1   // Bottom face (triangles to form a quad)
        };


        ByteBuffer buffer = ByteBuffer.allocateDirect(vertices.length * byteFlotante);
        buffer.order(ByteOrder.nativeOrder());
        bufferVertices = buffer.asFloatBuffer();
        bufferVertices.put(vertices);

        buffer = ByteBuffer.allocateDirect(colores.length * byteFlotante);
        buffer.order(ByteOrder.nativeOrder());
        bufferColores = buffer.asFloatBuffer();
        bufferColores.put(colores);

        bufferIndices = ByteBuffer.allocateDirect(indices.length * 4);
        bufferIndices.order(ByteOrder.nativeOrder());
        bufferIndices.put(indices);
        bufferIndices.position(0);

    }

    public void dibujar(GL10 gl) {
        gl.glFrontFace(gl.GL_CW);

        bufferVertices.position(0);
        gl.glVertexPointer(compPorVertice, gl.GL_FLOAT, 0, bufferVertices);
        gl.glEnableClientState(gl.GL_VERTEX_ARRAY);

        bufferColores.position(0);
        gl.glColorPointer(compPorColores, gl.GL_FLOAT, 0, bufferColores);
        gl.glEnableClientState(gl.GL_COLOR_ARRAY);

        gl.glDrawElements(gl.GL_TRIANGLES, 18, gl.GL_UNSIGNED_BYTE, bufferIndices);

        gl.glFrontFace(gl.GL_CCW);
        gl.glDisableClientState(gl.GL_VERTEX_ARRAY);
        gl.glDisableClientState(gl.GL_COLOR_ARRAY);
    }
}