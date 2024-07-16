package ec.edu.uce.pa.geometrias;

import java.nio.FloatBuffer;
import java.util.Random;

import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.utilidades.Funciones;

//import ec.edu.uce.pa.renderers.RenderPunto;

public class CirculoPuntos {
    private FloatBuffer bufferVertices;
    private FloatBuffer bufferColor;
    private final static int componentesVertices = 2, componentesColores = 4;
    private final static int byteFlotante = 4; //no cambiara

    private  int numeroPuntos;
    public float[] vertices;

    public float[] colores;

    public CirculoPuntos(int numeroPuntos, float [] Color) {
        this.numeroPuntos=numeroPuntos;
        this.vertices = generarCirculo(0,0,0.5f,numeroPuntos);
        this.colores= generarColoresCirculo(numeroPuntos,Color);

        bufferVertices = Funciones.generarFloatBuffer(vertices);
        bufferColor = Funciones.generarFloatBuffer(colores);
    }

    public static float[] generarCirculo(float centerX, float centerY, float radius, int numPoints) {
        float[] circleCoords = new float[numPoints * 2];
        for (int i = 0; i < numPoints; i++) {
            double angle = 2 * Math.PI * i / numPoints;
            float x = centerX + radius * (float) Math.cos(angle);
            float y = centerY + radius * (float) Math.sin(angle);
            circleCoords[i * 2] = x;
            circleCoords[i * 2 + 1] = y;
        }
        return circleCoords;
    }



    public float[] generarColoresCirculo(int numPuntos, float[]Color) {
        Random rand = new Random();
        float[] colores = new float[numPuntos * 4];
        for (int i = 0; i < numPuntos; i++) {

            colores[i * 4] = Color[0];
            colores[i * 4 + 1] = Color[1];
            colores[i * 4 + 2] = Color[2];
            colores[i * 4 + 3] = Color[3];
        }
        return colores;
    }


    public void dibujar(GL10 gl) {

        gl.glFrontFace(gl.GL_CW);

        bufferVertices.position(0);
        gl.glVertexPointer(componentesVertices, gl.GL_FLOAT, 0, bufferVertices);
        gl.glEnableClientState(gl.GL_VERTEX_ARRAY);

        bufferColor.position(0);
        gl.glColorPointer(4,gl.GL_FLOAT,0,bufferColor);
        gl.glEnableClientState(gl.GL_COLOR_ARRAY);

        gl.glPointSize(25);
        gl.glDrawArrays(gl.GL_POINTS, 0, numeroPuntos);

        gl.glFrontFace(gl.GL_CCW);

        gl.glDisableClientState(gl.GL_VERTEX_ARRAY);
        gl.glDisableClientState(gl.GL_COLOR_ARRAY);;

    }
}
