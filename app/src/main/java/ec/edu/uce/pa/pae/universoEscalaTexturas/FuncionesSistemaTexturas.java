package ec.edu.uce.pa.pae.universoEscalaTexturas;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public  class FuncionesSistemaTexturas {
    public static FloatBuffer generarBuffer (float[] arrayVertices){
        int byteFlotante=4;
        FloatBuffer result;
        ByteBuffer buffer = ByteBuffer.allocateDirect(arrayVertices.length*byteFlotante);
        buffer.order(ByteOrder.nativeOrder());
        result= buffer.asFloatBuffer();
        result.put(arrayVertices);
        result.position(0);
        return result;

    }
    public static FloatBuffer myFloatBuffer(float[]array){
        FloatBuffer a ;
        ByteBuffer buffer = ByteBuffer.allocateDirect(array.length*4);
        buffer.order(ByteOrder.nativeOrder());
        a = buffer.asFloatBuffer();
        a.put(array);
        a.position(0);
        return a;
    }

    public static ByteBuffer myByteBufferInice(byte[]array){
        ByteBuffer a ;
        a = ByteBuffer.allocateDirect(array.length*4);
        a.order(ByteOrder.nativeOrder());
        a.put(array);
        a.position(0);
        return a;
    }
}
