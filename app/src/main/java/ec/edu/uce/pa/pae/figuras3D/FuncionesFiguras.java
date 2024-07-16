package ec.edu.uce.pa.pae.figuras3D;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class FuncionesFiguras {

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


}
