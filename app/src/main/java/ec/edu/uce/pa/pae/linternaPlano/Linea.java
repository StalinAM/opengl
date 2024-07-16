package ec.edu.uce.pa.pae.linternaPlano;

import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;



public class Linea {
    private FloatBuffer bufferVertices;
    private FloatBuffer bufferColores;

    private final static int compPorVertice=3;
    private final static int compPorColores=4;

    public Linea(float [] color) {
        float[] vertices = {
                0.0f, -0.8f, 0.0f,
                0.25f, 0.0f, 0.0f
        };

        float[] colores = {
                color[0],  color[1],  color[2],  color[3],
                color[0],  color[1],  color[2],  color[3]
        };


        bufferVertices = FuncionesLampara.myBuffer(vertices);

        bufferColores=FuncionesLampara.myBuffer(colores);
    }

    public void dibujar (GL10 gl){
        gl.glFrontFace(gl.GL_CW);
        bufferVertices.position(0);
        gl.glVertexPointer(compPorVertice, gl.GL_FLOAT, 0, bufferVertices);
        gl.glEnableClientState(gl.GL_VERTEX_ARRAY);

        bufferColores.position(0);
        gl.glColorPointer(compPorColores, gl.GL_FLOAT,0,bufferColores);
        gl.glEnableClientState(gl.GL_COLOR_ARRAY);

        gl.glLineWidth(20);
        gl.glDrawArrays(gl.GL_LINES,0,2);

        gl.glFrontFace(gl.GL_CCW);
        gl.glDisableClientState(gl.GL_VERTEX_ARRAY);
        gl.glDisableClientState(gl.GL_COLOR_ARRAY);
    }
}
