package ec.edu.uce.pa.pae.cuboMovimientoTeclado;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class CaraCubo {

    private FloatBuffer bufferVertices1, bufferColor;
    private ByteBuffer bufferIndice;
    private final static int componentesVertices = 3,componentesColores = 4;
    private  final static int byteFlotante = 4;
    private  float [] verticesCara;
    private float [] colores;
    private byte [] indices;


    public CaraCubo(float a,float b, float c, float d) {

        this.verticesCara = arrayVertices();
        this.colores = arrayColores(a,b, c, d);
        this.indices= arrayIndices();

        ByteBuffer buffer = ByteBuffer.allocateDirect(verticesCara.length*byteFlotante);
        buffer.order(ByteOrder.nativeOrder());
        bufferVertices1 = buffer.asFloatBuffer();
        bufferVertices1.put(verticesCara);
        bufferVertices1.position(0);

        bufferIndice = ByteBuffer.allocateDirect(indices.length*4);
        bufferIndice.order(ByteOrder.nativeOrder());
        bufferIndice.put(indices);
        bufferIndice.position(0);

        buffer = ByteBuffer.allocateDirect(colores.length*byteFlotante);
        buffer.order(ByteOrder.nativeOrder());
        bufferColor= buffer.asFloatBuffer();
        bufferColor.put(colores);
        bufferColor.position(0);

    }

    public static float[] arrayColores(float a,float b, float c, float d){
        float [] arrayColores = {
                a, b, c, d,
                a, b, c, d,
                a, b, c, d,
                a, b, c, d
        };
        return arrayColores;
    }

    public static float[] arrayVertices(){
        float [] vertices = {
                -1.0f, 1.0f, 1.0f, //0
                1.0f, 1.0f, 1.0f, //1
                1.0f, -1.0f, 1.0f, //2
                -1.0f, -1.0f, 1.0f, //3
        };
        return vertices;
    }

    public static byte[] arrayIndices(){
        byte [] indices = {
                0,1,2, //T1
                0,2,3, //T2
        };
        return indices;
    }

    public void dibujar(GL10 gl){

        gl.glFrontFace(gl.GL_CW);

        bufferVertices1.position(0);
        gl.glVertexPointer(componentesVertices, gl.GL_FLOAT, 0, bufferVertices1);
        gl.glEnableClientState(gl.GL_VERTEX_ARRAY);

        bufferColor.position(0);
        gl.glColorPointer(componentesColores, gl.GL_FLOAT, 0, bufferColor);
        gl.glEnableClientState(gl.GL_COLOR_ARRAY);


        gl.glDrawElements(gl.GL_TRIANGLE_FAN, 6,gl.GL_UNSIGNED_BYTE, bufferIndice);
        gl.glFrontFace(gl.GL_CCW);

        gl.glDisableClientState(gl.GL_VERTEX_ARRAY);
        gl.glDisableClientState(gl.GL_COLOR_ARRAY);

    }
}
