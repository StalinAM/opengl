package ec.edu.uce.pa.pae.figuras3D;

import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Cilindro {

    private FloatBuffer vertexBufferCilindro;
    private FloatBuffer colorBufferCilindro;

    private FloatBuffer vertexBufferTapaSuperior;
    private FloatBuffer colorBufferTapaSuperior;

    private FloatBuffer vertexBufferTapaInferior;
    private FloatBuffer colorBufferTapaInferior;



    private float[] verticesCilindro;
    private float[] colorsCiliendro;

    private float[] verticesTapaSuperior;

    private float[] coloresTapaSuperior;

    private float[] verticesTapaInferior;

    private float[] coloresTapaInferior;
    private final int COORDS_PER_VERTEX = 3;
    private final int COLORS_PER_VERTEX = 4;

    private static int puntosCiliendro = 36; // Número de divisiones alrededor del cilindro

    private static int puntosCirculos = puntosCiliendro+2;
    public Cilindro() {
        crearCilindro();
        vertexBufferCilindro = FuncionesFiguras.myBuffer(verticesCilindro);
        colorBufferCilindro = FuncionesFiguras.myBuffer(colorsCiliendro);

        verticesTapaSuperior = myArrayVertivesTapas(1.0f, puntosCirculos);
        coloresTapaSuperior = myArrayColoresTapas(puntosCirculos);
        vertexBufferTapaSuperior = FuncionesFiguras.myBuffer(verticesTapaSuperior);
        colorBufferTapaSuperior = FuncionesFiguras.myBuffer(coloresTapaSuperior);

        verticesTapaInferior = myArrayVertivesTapas(-1.0f, puntosCirculos);
        coloresTapaInferior = myArrayColoresTapas(puntosCirculos);
        vertexBufferTapaInferior = FuncionesFiguras.myBuffer(verticesTapaInferior);
        colorBufferTapaInferior = FuncionesFiguras.myBuffer(coloresTapaInferior);

    }

    private void crearCilindro() {
        int numVertices = (puntosCiliendro + 1) * 2;

        verticesCilindro = new float[numVertices * COORDS_PER_VERTEX];
        colorsCiliendro = new float[numVertices * COLORS_PER_VERTEX];

        int vertexOffset = 0;
        int colorOffset = 0;

        float angleIncrement = (float) (2.0 * Math.PI / puntosCiliendro);
        float currentAngle = 0.0f;

        // Generar los vértices y colores para el cilindro
        for (int i = 0; i < puntosCiliendro + 1; i++) {
            float x = (float) Math.cos(currentAngle);
            float y = 1.0f;
            float z = (float) -Math.sin(currentAngle);

            verticesCilindro[vertexOffset++] = x;
            verticesCilindro[vertexOffset++] = y;
            verticesCilindro[vertexOffset++] = z;

            colorsCiliendro[colorOffset++] = 1.0f; // R
            colorsCiliendro[colorOffset++] = 0.0f; // G
            colorsCiliendro[colorOffset++] = 0.0f; // B
            colorsCiliendro[colorOffset++] = 1.0f; // Alpha

            verticesCilindro[vertexOffset++] = x;
            verticesCilindro[vertexOffset++] = -y;
            verticesCilindro[vertexOffset++] = z;

            colorsCiliendro[colorOffset++] = 0.0f; // R
            colorsCiliendro[colorOffset++] = 0.0f; // G
            colorsCiliendro[colorOffset++] = 1.0f; // B
            colorsCiliendro[colorOffset++] = 1.0f; // Alpha

            currentAngle += angleIncrement;
        }
    }


    public static  float [] myArrayVertivesTapas(float alturaY, int numPuntos){

        float[] v = new float [numPuntos*3];
        v [0] = 0.0f;
        v [1] = alturaY;
        v [2] = 0.0f;
        double angulo = 0;
        for(int i=3; i<v.length; i += 3){
            v[i] = (float)(Math.sin(Math.toRadians(angulo)));//X
            v[i+1] = alturaY;//Y
            v[i+2] = (float)(Math.cos(Math.toRadians(angulo)));//Z
            angulo += 10; //sube de 10 en 10 los grados
        }
        v [v.length-3] = v [3];
        v [v.length-2] = alturaY;
        v [v.length-1] = v [5];
        return v;
    }

    public static  float [] myArrayColoresTapas(int numPuntos ){
        float[] co = new float [numPuntos*4];
        co [0] = 1.0f;
        co [1] = 0.0f;
        co [2] = 0.0f;
        co [3] = 1.0f;

        for (int i=4; i < co.length; i += 4){
            co[i] = 1.0f; //rojo
            co[i+1] = 0.7f; //verde
            co[i+2] = 0.0f; //azul
            co[i+3] = 1.0f; //alfa
        }
        return co;
    }

    public void dibujar(GL10 gl) {
        gl.glFrontFace(gl.GL_CW);
        //cilindro
        vertexBufferCilindro.position(0);
        gl.glVertexPointer(COORDS_PER_VERTEX, gl.GL_FLOAT, 0, vertexBufferCilindro);
        gl.glEnableClientState(gl.GL_VERTEX_ARRAY);

        colorBufferCilindro.position(0);
        gl.glColorPointer(COLORS_PER_VERTEX, gl.GL_FLOAT, 0, colorBufferCilindro);
        gl.glEnableClientState(gl.GL_COLOR_ARRAY);

        gl.glDrawArrays(gl.GL_TRIANGLE_STRIP, 0, verticesCilindro.length / COORDS_PER_VERTEX);

        //TapaSuperior
        vertexBufferTapaSuperior.position(0);
        gl.glVertexPointer(COORDS_PER_VERTEX, gl.GL_FLOAT, 0, vertexBufferTapaSuperior);
        gl.glEnableClientState(gl.GL_VERTEX_ARRAY);

        colorBufferTapaSuperior.position(0);
        gl.glColorPointer(COLORS_PER_VERTEX, gl.GL_FLOAT, 0, colorBufferTapaSuperior);
        gl.glEnableClientState(gl.GL_COLOR_ARRAY);

        gl.glDrawArrays(gl.GL_TRIANGLE_FAN,0, puntosCirculos);

        //TapaInferior
        vertexBufferTapaInferior.position(0);
        gl.glVertexPointer(COORDS_PER_VERTEX, gl.GL_FLOAT, 0, vertexBufferTapaInferior);
        gl.glEnableClientState(gl.GL_VERTEX_ARRAY);

        colorBufferTapaInferior.position(0);
        gl.glColorPointer(COLORS_PER_VERTEX, gl.GL_FLOAT, 0, colorBufferTapaInferior);
        gl.glEnableClientState(gl.GL_COLOR_ARRAY);

        gl.glDrawArrays(gl.GL_TRIANGLE_FAN,0, puntosCirculos);

        gl.glDisableClientState(gl.GL_VERTEX_ARRAY);
        gl.glDisableClientState(gl.GL_COLOR_ARRAY);
        gl.glDisableClientState(gl.GL_VERTEX_ARRAY);
        gl.glDisableClientState(gl.GL_COLOR_ARRAY);
        gl.glDisableClientState(gl.GL_VERTEX_ARRAY);
        gl.glDisableClientState(gl.GL_COLOR_ARRAY);
    }
}


