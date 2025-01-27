package ec.edu.uce.pa.pae.universoEscalaMateriales;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public  class FuncionesMateriales {
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
}
