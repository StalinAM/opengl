package ec.edu.uce.pa.geometrias;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.utilidades.Funciones;

public class CuadradoTextura {

    private FloatBuffer bufferVertices1, bufferColor, bufferTexturas;
    private ByteBuffer bufferIndice;
    private final static int componentesVertices = 2,componentesColores = 4;
    private  final static int byteFlotante = 4;

    public CuadradoTextura() {

        float [] vertices1 = { //vertices de geometria
                1.0f, 1.0f,  //0
                1.0f,-1.0f,   //1
                -1.0f,-1.0f,  //2
                -1.0f,1.0f    //3
        };

        /*float [] texturas = { //sentido de la imagen
                1,1,  //0
                1,0,   //1
                0,0,  //2
                0,1    //3
        };*/

        float [] texturas = { //como si la imagen diera la vuelta
                1,0,  //0
                1,1,   //1
                0,1,  //2
                0,0    //3
        };
        float [] colores = {
                1.0f, 0.0f, 1.0f, 1.0f,
                1.0f, 0.0f, 1.0f, 1.0f,
                1.0f, 0.0f, 1.0f, 1.0f,
                1.0f, 0.0f, 1.0f, 1.0f
        };

        byte [] indices = {
            0,1,2,
                0,2,3
        };

        bufferVertices1= Funciones.generarFloatBuffer(vertices1);
        bufferColor=Funciones.generarFloatBuffer(colores);
        bufferTexturas=Funciones.generarFloatBuffer(texturas);
        bufferIndice=Funciones.generarByteBuffer(indices);

    }

    public void dibujar(GL10 gl){
        gl.glFrontFace(gl.GL_CW);

        bufferVertices1.position(0);
        gl.glVertexPointer(componentesVertices, gl.GL_FLOAT, 0, bufferVertices1);
        gl.glEnableClientState(gl.GL_VERTEX_ARRAY);

        bufferColor.position(0);
        gl.glColorPointer(componentesColores, gl.GL_FLOAT, 0, bufferColor);
        gl.glEnableClientState(gl.GL_COLOR_ARRAY);

        gl.glTexCoordPointer(2,gl.GL_FLOAT,0,bufferTexturas);
        gl.glEnableClientState(gl.GL_TEXTURE_COORD_ARRAY);

        gl.glDrawElements(gl.GL_TRIANGLES, 6,gl.GL_UNSIGNED_BYTE, bufferIndice);

        gl.glFrontFace(gl.GL_CCW);
        gl.glDisableClientState(gl.GL_VERTEX_ARRAY);
        gl.glDisableClientState(gl.GL_COLOR_ARRAY);
        gl.glDisableClientState(gl.GL_TEXTURE_COORD_ARRAY);

    }
}
