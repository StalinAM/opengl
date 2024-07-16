package ec.edu.uce.pa.pae.universoEscalaTexturas;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import javax.microedition.khronos.opengles.GL10;



public class AnilloSistemaTexturas {
    private final static int compPorColores = 4;
    private FloatBuffer vertexBuffer;
    private FloatBuffer bufferColores;
    private float[] vertices;
    private float[] colores;

    public AnilloSistemaTexturas(float radioInterno, float radioExterno, int numeroSegmentos) {
        vertices = new float[numeroSegmentos * 3 * 2];
        colores = new float[(numeroSegmentos + 1) * 2 * compPorColores];

        // Cálculo de los vértices del anillo
        for (int i = 0; i < numeroSegmentos; i++) {
            float theta = (float) (3.0f * Math.PI * i / numeroSegmentos);
            float x1 = (float) (radioInterno * Math.cos(theta));
            float y1 = (float) (radioInterno * Math.sin(theta));
            float x2 = (float) (radioExterno * Math.cos(theta));
            float y2 = (float) (radioExterno * Math.sin(theta));

            vertices[i * 6] = x1;
            vertices[i * 6 + 1] = y1;
            vertices[i * 6 + 2] = 0.0f;
            vertices[i * 6 + 3] = x2;
            vertices[i * 6 + 4] = y2;
            vertices[i * 6 + 5] = 0.0f;

            //PARA EL CAMBIO DE COLOR
            //PARA LA PARTE INTERNA
            int colorIndex = i * 2 * compPorColores;
            colores[colorIndex] = Material.materialSaturnoAnillo[0];
            colores[colorIndex + 1] = Material.materialSaturnoAnillo[1];
            colores[colorIndex + 2] = Material.materialSaturnoAnillo[2];
            colores[colorIndex + 3] = Material.materialSaturnoAnillo[3];  //ALPHA

            //PARA LA PARTE EXTERNA
            colores[colorIndex + 4] = Material.materialSaturnoAnillo[0];  //ROJO
            colores[colorIndex + 5] = Material.materialSaturnoAnillo[1];  //VERDE
            colores[colorIndex + 6] = Material.materialSaturnoAnillo[2];  //AZUL
            colores[colorIndex + 7] = Material.materialSaturnoAnillo[3];  //ALPHA
        }

        // Creación del buffer de vértices
        ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
        vbb.order(ByteOrder.nativeOrder());
        vertexBuffer = vbb.asFloatBuffer();
        vertexBuffer.put(vertices);
        vertexBuffer.position(0);

        // Creación del buffer de colores
        ByteBuffer cbb = ByteBuffer.allocateDirect(colores.length * 4);
        cbb.order(ByteOrder.nativeOrder());
        bufferColores = cbb.asFloatBuffer();
        bufferColores.put(colores);
        bufferColores.position(0);

    }

    public void dibujar(GL10 gl) {
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);

        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
        gl.glColorPointer(compPorColores, GL10.GL_FLOAT, 0, bufferColores);

        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, vertices.length / 3);

        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
    }
}
