package ec.edu.uce.pa.pae.piramideTexturas;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;



public class TrinaguloTexturaPAE {

    private FloatBuffer bufferVerticesCara1;
    private FloatBuffer bufferVerticesCara2;
    private FloatBuffer bufferVerticesCara3;
    private FloatBuffer bufferVerticesCara4;
    private FloatBuffer bufferVerticesBase;
    private FloatBuffer bufferTexturasCara1;
    private FloatBuffer bufferTexturasCara2;
    private FloatBuffer bufferTexturasCara3;
    private FloatBuffer bufferTexturasCara4;
    private FloatBuffer bufferTexturasBase;


    private ByteBuffer bufferIndiceCara;
    private ByteBuffer bufferIndiceBase;

    private final static int comPorVertices = 3;

    private float verticeCara1[];

    private float verticeCara2[];

    private float verticeCara3[];
    private float verticeCara4[];
    private float verticeBase[];

    private byte[] indiceCara;
    private byte[] indiceBase;

    private float texturaCara1[];
    private float texturaCara2[];
    private float texturaCara3[];
    private float texturaCara4[];
    private float texturaBase[];

    public TrinaguloTexturaPAE() {
        verticeCara1 = new float[]{
                0, 1, 0,
                1, 0, 1,
                -1, 0, 1
        };

        texturaCara1 = new float[] {
                0.5f,0,
                0.75f, 1,
                0.25f, 1
        };

        verticeCara2 = new float[]{
                0,1,0,
                1,0,-1,
                1,0,1
        };

        texturaCara2 = new float[]{
                0.15f,0,
                0.60f, 1,
                0.0f, 1.0f

        };

        verticeCara3 = new float[]{
                0, 1, 0,
                -1, 0, -1,
                1, 0, -1
        };

        texturaCara3 = new float[]{
                0.66f,0,
                1.0f, 0.66f,
                0.33f, 0.66f

        };

        verticeCara4 = new float[]{
                0, 1, 0,
                -1, 0, 1,
                -1, 0, -1,
        };

        texturaCara4 = new float[]{
                0.66f,0,
                1, 1,
                0.33f, 1
        };

        indiceCara = new byte[]{
                0,1,2
        };

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
                0,2,3};

        bufferVerticesCara1= FuncionesPiramideTexturas.myBuffer(verticeCara1);
        bufferTexturasCara1= FuncionesPiramideTexturas.myBuffer(texturaCara1);

        bufferVerticesCara2= FuncionesPiramideTexturas.myBuffer(verticeCara2);
        bufferTexturasCara2= FuncionesPiramideTexturas.myBuffer(texturaCara2);

        bufferVerticesCara3= FuncionesPiramideTexturas.myBuffer(verticeCara3);
        bufferTexturasCara3= FuncionesPiramideTexturas.myBuffer(texturaCara3);

        bufferVerticesCara4= FuncionesPiramideTexturas.myBuffer(verticeCara4);
        bufferTexturasCara4= FuncionesPiramideTexturas.myBuffer(texturaCara4);

        bufferIndiceCara= FuncionesPiramideTexturas.myBufferInice(indiceCara);

        bufferVerticesBase = FuncionesPiramideTexturas.myBuffer(verticeBase);
        bufferIndiceBase = FuncionesPiramideTexturas.myBufferInice(indiceBase);
        bufferTexturasBase= FuncionesPiramideTexturas.myBuffer(texturaBase);

    }

    public void dribujar(GL10 gl, int caso){
        switch (caso){
            case 1:
                pasos(gl,bufferVerticesCara1,bufferTexturasCara1,bufferIndiceCara,3);
                break;
            case 2:
                pasos(gl,bufferVerticesCara2,bufferTexturasCara2,bufferIndiceCara,3);
                break;
            case 3:
                pasos(gl,bufferVerticesCara3,bufferTexturasCara3,bufferIndiceCara,3);
                break;
            case 4:
                pasos(gl,bufferVerticesCara4,bufferTexturasCara4,bufferIndiceCara,3);
                break;
            case 5:
                pasos(gl,bufferVerticesBase,bufferTexturasBase,bufferIndiceBase,6);
                break;

        }
    }
    public void pasos(GL10 gl, FloatBuffer vertices, FloatBuffer texturas, ByteBuffer indice, int indices){
        gl.glFrontFace(gl.GL_CW);
        bufferVerticesCara1.position(0);

        gl.glVertexPointer(comPorVertices, gl.GL_FLOAT, 0, vertices);
        gl.glEnableClientState(gl.GL_VERTEX_ARRAY);

        gl.glTexCoordPointer(2, gl.GL_FLOAT, 0, texturas);
        gl.glEnableClientState(gl.GL_TEXTURE_COORD_ARRAY);

        gl.glDrawElements(gl.GL_TRIANGLE_FAN, indices, gl.GL_UNSIGNED_BYTE, indice);

        gl.glFrontFace(gl.GL_CW);
        gl.glDisableClientState(gl.GL_VERTEX_ARRAY);
        gl.glDisableClientState(gl.GL_TEXTURE_COORD_ARRAY);
    }
}
