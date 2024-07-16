package ec.edu.uce.pa.geometrias;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.utilidades.Funciones;


public class CuadradoMipMap {
    private FloatBuffer bufferVertices,bufferTexturas;
    private ByteBuffer bufferIndices;
    private boolean utilizarMipMap;
    private final static int comPorVertice =3;
    private int nVertices,nIndices;
    private float[] color;
    public CuadradoMipMap(float[] vertices, byte[] indices,float[]texturas, float[] color,boolean utilizarMipMap){
        this.utilizarMipMap = utilizarMipMap;
        //VERTICES
        nVertices = vertices.length;
        this.color = color;
        //COLORES
        /*
        auxC = Funciones.coloresUnico(R,G,B,nVertices/comPorVertice);
        nColores = auxC.length;
         */

        //indices
        nIndices = indices.length;

        //Buffers
        bufferVertices = Funciones.generarFloatBuffer(vertices);
        //bufferColores = Funciones.generarFloatBuffer(auxC);
        bufferIndices = Funciones.generarByteBuffer(indices);
        bufferTexturas = Funciones.generarFloatBuffer(texturas);
        //bufferNormales = Funciones.generarFloatBuffer(normales);

    }

    public void dibujar(GL10 gl){
        gl.glFrontFace(gl.GL_CW);
        gl.glEnableClientState(gl.GL_VERTEX_ARRAY);
        //gl.glEnableClientState(gl.GL_COLOR_ARRAY);
        //gl.glEnableClientState(gl.GL_NORMAL_ARRAY);

        gl.glColor4f(color[0],color[1],color[2],color[3]);

        gl.glVertexPointer(comPorVertice,gl.GL_FLOAT,0,bufferVertices);

        gl.glTexCoordPointer(2,gl.GL_FLOAT,0,bufferTexturas);
        gl.glEnableClientState(gl.GL_TEXTURE_COORD_ARRAY);
        //COLORES
        //gl.glTexCoordPointer(2,gl.GL_FLOAT,0,bufferTexturas);
        //gl.glEnableClientState(gl.GL_TEXTURE_COORD_ARRAY);

        gl.glDrawElements(gl.GL_TRIANGLE_FAN,nIndices,gl.GL_UNSIGNED_BYTE,bufferIndices);
        //NORMALES
        /*
        bufferNormales.position(0);
        gl.glNormalPointer(gl.GL_FLOAT,0,bufferNormales);

         */
        gl.glFrontFace(gl.GL_CCW);
        //gl.glDisableClientState(gl.GL_NORMAL_ARRAY);
        gl.glDisableClientState(gl.GL_VERTEX_ARRAY);
        //gl.glDisableClientState(gl.GL_COLOR_ARRAY);
        gl.glDisableClientState(gl.GL_TEXTURE_COORD_ARRAY);
    }
    private  int[] arrayTexturas;

    public void habilitarTexturas(GL10 gl,int numTexturas){
        gl.glEnable(gl.GL_TEXTURE_2D);
        arrayTexturas = new int[numTexturas];
        gl.glGenTextures(numTexturas,arrayTexturas,0);
    }
    public void cargarTextura(GL10 gl, Context context, int id, int indice,int nivel){
        BitmapFactory.Options option = new BitmapFactory.Options();
        option.inScaled = false;

        Bitmap imagen = BitmapFactory.decodeResource(context.getResources(), id,option);
        gl.glBindTexture(gl.GL_TEXTURE_2D,arrayTexturas[indice]);
        GLUtils.texImage2D(gl.GL_TEXTURE_2D,nivel,imagen,0);


        //gl.glTexParameterf(gl.GL_TEXTURE_2D,gl.GL_TEXTURE_WRAP_S,gl.GL_LINEAR);
        //gl.glTexParameterf(gl.GL_TEXTURE_2D,gl.GL_TEXTURE_WRAP_T,gl.GL_LINEAR);


        if(utilizarMipMap){
            gl.glTexParameterf(gl.GL_TEXTURE_2D,gl.GL_TEXTURE_MAG_FILTER,gl.GL_NEAREST_MIPMAP_LINEAR);
            gl.glTexParameterf(gl.GL_TEXTURE_2D,gl.GL_TEXTURE_MIN_FILTER,gl.GL_LINEAR_MIPMAP_LINEAR);
        }else{

            gl.glTexParameterf(gl.GL_TEXTURE_2D,gl.GL_TEXTURE_WRAP_S,gl.GL_LINEAR);
            gl.glTexParameterf(gl.GL_TEXTURE_2D,gl.GL_TEXTURE_WRAP_T,gl.GL_NEAREST);
            /*
            gl.glTexParameterf(gl.GL_TEXTURE_2D,gl.GL_TEXTURE_MAG_FILTER,gl.GL_NEAREST);
            gl.glTexParameterf(gl.GL_TEXTURE_2D,gl.GL_TEXTURE_MIN_FILTER,gl.GL_LINEAR);
             */

        }

        gl.glTexParameterf(gl.GL_TEXTURE_2D,gl.GL_TEXTURE_WRAP_S,gl.GL_REPEAT);
        gl.glTexParameterf(gl.GL_TEXTURE_2D,gl.GL_TEXTURE_WRAP_T,gl.GL_REPEAT);

        imagen.recycle();
    }
}

/*
    public void cargarTextura(GL10 gl, int idArchivo, Context contexto, int indice) {
        Bitmap imagen = BitmapFactory.decodeResource(contexto.getResources(), idArchivo);
        gl.glBindTexture(gl.GL_TEXTURE_2D, arrTexturas[indice]);
        GLUtils.texImage2D(gl.GL_TEXTURE_2D, 0, imagen, 0);
        gl.glTexParameterf(gl.GL_TEXTURE_2D, gl.GL_TEXTURE_MIN_FILTER, gl.GL_LINEAR);
        gl.glTexParameterf(gl.GL_TEXTURE_2D, gl.GL_TEXTURE_MAG_FILTER, gl.GL_NEAREST);
        gl.glTexParameterf(gl.GL_TEXTURE_2D, gl.GL_TEXTURE_WRAP_S, gl.GL_REPEAT);
        gl.glTexParameterf(gl.GL_TEXTURE_2D, gl.GL_TEXTURE_WRAP_T, gl.GL_REPEAT);

        imagen.recycle();

    }

 */