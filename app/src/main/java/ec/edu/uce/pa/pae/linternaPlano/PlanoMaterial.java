package ec.edu.uce.pa.pae.linternaPlano;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;



public class PlanoMaterial {
    private FloatBuffer bufferVertices;
    private FloatBuffer bufferColores;
    private FloatBuffer bufferNormales;
    private ByteBuffer bufferIndice;
    private final static int byteFlotante = 4;
    private final static int comPorVertices = 3;
    private final static int comPorColor = 4;
    public PlanoMaterial(){
        float[] vertices ={
                -1.0f, -1.0f,  1.0f, // Top-left
                1.0f, -1.0f,  1.0f, // Top-right
                1.0f, -1.0f, -1.0f, // Bottom-right
                -1.0f, -1.0f, -1.0f
        };

        float[] normales ={
                0,1,0,
                0,1,0,
                0,1,0,
                0,1,0

        };
        byte[] indices = {
                0,1,2,
                0,2,3,
        };

        float[] colores ={
                1.0f,0.0f,0.0f,1.0f,
                1.0f,0.0f,0.0f,1.0f,
                1.0f,0.0f,0.0f,1.0f,
                1.0f,0.0f,0.0f,1.0f


        };


        bufferVertices = FuncionesLampara.generarBuffer(vertices);

        bufferNormales = FuncionesLampara.generarBuffer(normales);

        bufferIndice = ByteBuffer.allocateDirect(indices.length);
        bufferIndice.order(ByteOrder.nativeOrder());
        bufferIndice.put(indices);
        bufferIndice.position(0);

    }
    public void dibujar(GL10 gl){
        gl.glFrontFace(gl.GL_CW);
        gl.glVertexPointer(comPorVertices,gl.GL_FLOAT,0,bufferVertices);
        gl.glEnableClientState(gl.GL_VERTEX_ARRAY);


       //bufferColores.position(0);
       // gl.glColorPointer(comPorColor,gl.GL_FLOAT,0,bufferColores);
        //gl.glEnableClientState(gl.GL_COLOR_ARRAY);


        bufferNormales.position(0);
        gl.glNormalPointer(gl.GL_FLOAT,0,bufferNormales);
        gl.glEnableClientState(gl.GL_NORMAL_ARRAY);

        gl.glLineWidth(12);
        gl.glDrawElements(gl.GL_TRIANGLES,6,gl.GL_UNSIGNED_BYTE, bufferIndice);


        gl.glFrontFace(gl.GL_CW);
        gl.glDisableClientState(gl.GL_VERTEX_ARRAY);
        gl.glDisableClientState(gl.GL_COLOR_ARRAY);
        gl.glDisableClientState(gl.GL_NORMAL_ARRAY);

    }
}
