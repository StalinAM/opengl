package ec.edu.uce.pa.pae.linternaPlano;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;



public class PlanoTextura {

    private FloatBuffer bufferVerticesBase;

    private FloatBuffer bufferTexturasBase;

    private ByteBuffer bufferIndiceBase;

    private final static int comPorVertices = 3;


    private float verticeBase[];


    private byte[] indiceBase;


    private float texturaBase[];

    public PlanoTextura() {

        verticeBase = new float[]{
                -1, 0, 1,
                1, 0, 1,
                1, 0, -1,
                -1, 0, -1,
        };

        texturaBase = new float[]{
                -1,-1,
                -1,0,
                0,0,
                0,-1
        };

        indiceBase = new byte[]{
                0,1,2,
                0,2,3
        };

        bufferVerticesBase = FuncionesLampara.myBuffer(verticeBase);

        bufferTexturasBase= FuncionesLampara.myBuffer(texturaBase);

        bufferIndiceBase = FuncionesLampara.myBufferInice(indiceBase);

    }


    public void dibujar(GL10 gl){

        gl.glFrontFace(gl.GL_CW);


        gl.glVertexPointer(comPorVertices, gl.GL_FLOAT, 0, bufferVerticesBase);
        gl.glEnableClientState(gl.GL_VERTEX_ARRAY);

        gl.glTexCoordPointer(2, gl.GL_FLOAT, 0, bufferTexturasBase);
        gl.glEnableClientState(gl.GL_TEXTURE_COORD_ARRAY);

        gl.glDrawElements(gl.GL_TRIANGLE_FAN, 6, gl.GL_UNSIGNED_BYTE, bufferIndiceBase);

        gl.glFrontFace(gl.GL_CW);
        gl.glDisableClientState(gl.GL_VERTEX_ARRAY);
        gl.glDisableClientState(gl.GL_TEXTURE_COORD_ARRAY);
    }
}
