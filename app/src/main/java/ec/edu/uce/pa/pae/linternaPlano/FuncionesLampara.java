package ec.edu.uce.pa.pae.linternaPlano;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class FuncionesLampara {

    public static FloatBuffer generarBuffer(float [] arrayVertices){
        int byteFlotante=4;
        FloatBuffer result;
        ByteBuffer buffer = ByteBuffer.allocateDirect(arrayVertices.length*byteFlotante);
        buffer.order(ByteOrder.nativeOrder());
        result = buffer.asFloatBuffer();
        result.put(arrayVertices);
        result.position(0);
        return result;
    }


    public static FloatBuffer myBuffer(float[]array){
        FloatBuffer a ;
        ByteBuffer buffer = ByteBuffer.allocateDirect(array.length*4);
        buffer.order(ByteOrder.nativeOrder());
        a = buffer.asFloatBuffer();
        a.put(array);
        a.position(0);
        return a;
    }

    public static ByteBuffer myBufferInice(byte[]array){
        ByteBuffer a ;
        a = ByteBuffer.allocateDirect(array.length*4);
        a.order(ByteOrder.nativeOrder());
        a.put(array);
        a.position(0);
        return a;
    }


    public static int[] habilitarTexturas(GL10 gl, int numTexturas){
        int arrayTexture[] = new int[numTexturas];
        gl.glEnable(gl.GL_TEXTURE_2D);
        arrayTexture = new int[numTexturas];
        gl.glGenTextures(numTexturas,arrayTexture,0);
        return  arrayTexture;
    }

    public static void cargarImagenesTextura(GL10 gl, Context context, int indice, int  idImagen, int arrayTexture[]){

        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),idImagen);
        gl.glBindTexture(gl.GL_TEXTURE_2D,arrayTexture[indice]);
        GLUtils.texImage2D(gl.GL_TEXTURE_2D,0,bitmap,0);
        gl.glTexParameterf(gl.GL_TEXTURE_2D,gl.GL_TEXTURE_MAG_FILTER,gl.GL_LINEAR);
        gl.glTexParameterf(gl.GL_TEXTURE_2D,gl.GL_TEXTURE_MIN_FILTER,gl.GL_LINEAR);
        bitmap.recycle();
    }

}
