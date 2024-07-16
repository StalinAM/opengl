package ec.edu.uce.pa.pae.universoEscalaMateriales;

import java.nio.FloatBuffer;
import java.util.Random;

import javax.microedition.khronos.opengles.GL10;


public class StarsSistemaMateriales {

    private FloatBuffer bufferVertices;

    private final static int comPorVertices = 3;
    private float[] vertices;
    public StarsSistemaMateriales(int numeroEstrellas, int dist) {
        vertices = new float[numeroEstrellas * 3];

        Random rand = new Random();
        for (int i = 0; i < vertices.length; i += comPorVertices) {
            vertices[i] = (rand.nextFloat() * 2 - 1)*dist;
            vertices[i + 1] = (rand.nextFloat() * 2 - 1)*dist;
            vertices[i + 2] = (rand.nextFloat() * 2 - 1)*dist;


        }

        bufferVertices = FuncionesMateriales.generarBuffer(vertices);

    }

    public void dibujar(GL10 gl) {
        gl.glFrontFace(gl.GL_CW);
        bufferVertices.position(0);


        gl.glVertexPointer(comPorVertices, gl.GL_FLOAT,0 ,bufferVertices);
        gl.glEnableClientState(gl.GL_VERTEX_ARRAY);

        gl.glPointSize(5);
        gl.glDrawArrays(gl.GL_POINTS, 0, vertices.length / 3);

        gl.glFrontFace(gl.GL_CCW);
        gl.glDisableClientState(gl.GL_VERTEX_ARRAY);

    }
}
