package ec.edu.uce.pa.geometrias;
import java.util.Random;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;


import ec.edu.uce.pa.utilidades.Funciones;

//import ec.edu.uce.pa.renderers.RenderPunto;

public class Punto {
    private FloatBuffer bufferVertices;
    private FloatBuffer bufferColor;
    private final static int componentesVertices = 2, componentesColores = 4;
    private final static int byteFlotante = 4; //no cambiara

    private  int numeroPuntos;
    public float[] vertices;

    public float[] colores;
    public Punto(int numeroPuntos) {

        this.numeroPuntos=numeroPuntos;
        this.vertices = generarCoordenadas(numeroPuntos,-2f,2f,-3f,3f);
        this.colores=generarColores(numeroPuntos);
        bufferVertices = Funciones.generarFloatBuffer(vertices);
        bufferColor = Funciones.generarFloatBuffer(colores);
    }

    public float[] generarCoordenadas(int numPuntos, float minX, float maxX, float minY, float maxY) {
        Random rand = new Random();
        float[] coordenadas = new float[numPuntos * 2];
        for (int i = 0; i < numPuntos; i++) {
            float x = rand.nextFloat() * (maxX - minX) + minX;
            float y = rand.nextFloat() * (maxY - minY) + minY;
            boolean valorx= rand.nextBoolean();
            boolean valory= rand.nextBoolean();
            float dis = minX - 1.9f;
            if(valorx&&valory){
                x+=0.5;
                y+=0.5;
            }
            else if (valory && maxY<dis) {
                y+=0.8;
            }
            else if (valorx&& maxX<dis ) {
                x+=0.9;
            }
            else if (!valory && maxY<dis) {
                y-=0.8;
            }
            else if (!valorx && maxX<dis) {
                x-=0.9;
            }

            else if (valorx==false && valorx==false) {
                x -= 0.5;
                y -= 0.5;
            }

            coordenadas[i * 2] = x;
            coordenadas[i * 2 + 1] = y;
        }
        return coordenadas;
    }

    public float[] generarColores(int numPuntos) {
        Random rand = new Random();
        float[] colores = new float[numPuntos * 4];
        for (int i = 0; i < numPuntos; i++) {
            float r = rand.nextFloat();
            float g = rand.nextFloat();
            float b = rand.nextFloat();
            float a = rand.nextFloat();

            if (r == 0.5f && g == 0.5 && b == 0.5){
                r=0.9f;
            }
            colores[i * 4] = r;
            colores[i * 4 + 1] = g;
            colores[i * 4 + 2] = b;
            colores[i * 4 + 3] = a;
        }
        return colores;
    }

    public static void llenarPosiciones(int numPuntos, float [] array, int inicio, int fin, float radio) {
        float incremento = 360/numPuntos;
        float angulo = incremento;
        for (int i = inicio; i < fin; i++) {
            float x = (float) (radio * Math.cos(Math.toRadians(angulo * i)));
            float y = (float) (radio * Math.sin(Math.toRadians(angulo * i)));
            array[i * 2] = x;
            array[i * 2 + 1] = y;
        }

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
