package ec.edu.uce.pa.pae.carrito;

import java.nio.FloatBuffer;
import java.util.Random;

import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.utilidades.Funciones;

public class Carrito {


    private FloatBuffer bufferVerticesRectanguloUno;
    private FloatBuffer bufferVerticesRectanguloDos;
    private FloatBuffer bufferColorRectanguloUno;
    private FloatBuffer bufferColorRectanguloDos;

    private FloatBuffer bufferVerticesCirculoUno;
    private FloatBuffer bufferVerticesCirculoDos;

    private FloatBuffer bufferColorCirculoUno;
    private FloatBuffer bufferColorCirculoDos;

    private final static int componentesVertices = 2, componentesColores = 4;


    private  int numeroPuntos;

    public float[] verticesRectanguloUno;
    public float[] verticesRectanguloDos;

    public float[] coloresRectanguloUno;
    public float[] coloresRectanguloDos;

    public float[] colorRectanguloUno = {0.0f,0.0f,1.0f,1.0f};
    public float[] colorRectanguloDos = {0.0f,1.0f,0.0f,1.0f};
    public float [] valoresUno ={-2.7f,0,-1,1};
    public float [] valoresDos ={valoresUno[1],1.5f,valoresUno[2],0.0f};

    public float[] verticesCirculoUno;
    public float[] verticesCirculoDos;
    public float[] colorRuedaUno;
    public float[] colorRuedaDos;

    public float[] colorRueda = {0.7f,0.5f,0.0f,1.0f};


    public Carrito(int numeroPuntos) {

        this.verticesRectanguloUno= verticesRectangulo(valoresUno);
        this.coloresRectanguloUno = generarColores(verticesRectanguloUno.length,colorRectanguloUno);
        bufferVerticesRectanguloUno=Funciones.generarFloatBuffer(verticesRectanguloUno);
        bufferColorRectanguloUno=Funciones.generarFloatBuffer(coloresRectanguloUno);

        this.verticesRectanguloDos= verticesRectangulo(valoresDos);
        this.coloresRectanguloDos = generarColores(verticesRectanguloDos.length,colorRectanguloDos);
        bufferVerticesRectanguloDos=Funciones.generarFloatBuffer(verticesRectanguloDos);
        bufferColorRectanguloDos=Funciones.generarFloatBuffer(coloresRectanguloDos);

        this.numeroPuntos=numeroPuntos;
        this.verticesCirculoUno = generarCirculo(-2,-1.5f,0.5f,numeroPuntos);
        this.verticesCirculoDos = generarCirculo(1,-1.5f,0.5f,numeroPuntos);

        this.colorRuedaUno = generarColores(numeroPuntos,colorRueda);
        this.colorRuedaDos= generarColores(numeroPuntos,colorRueda);

        bufferVerticesCirculoUno = Funciones.generarFloatBuffer(verticesCirculoUno);
        bufferVerticesCirculoDos = Funciones.generarFloatBuffer(verticesCirculoDos);

        bufferColorCirculoUno = Funciones.generarFloatBuffer(colorRuedaUno);
        bufferColorCirculoDos = Funciones.generarFloatBuffer(colorRuedaDos);
    }


    public  static  float[] verticesRectangulo (float [] valores){
        float x2=valores[0],x1=valores[1],y1=valores[2],y2=valores[3];
        float [] v = {
                //lADO 1
                x2,y2,
                x1,y2,

                //lADO 2
                x1,y2,
                x1,y1,

                //LADO 3
                x1,y1,
                x2,y1,

                //LADO 4
                x2,y1,
                x2,y2
        };
        return v;
    }

    public float[] generarColores(int numPuntos, float[]Color) {
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

    public void dibujar(GL10 gl) {

        gl.glFrontFace(gl.GL_CW);
        //Rectangulo 1
        /////////////////////////////////////////////////////////////////////////////////////
        bufferVerticesRectanguloUno.position(0);
        gl.glVertexPointer(componentesVertices, gl.GL_FLOAT, 0, bufferVerticesRectanguloUno);
        gl.glEnableClientState(gl.GL_VERTEX_ARRAY);

        bufferColorRectanguloUno.position(0);
        gl.glColorPointer(4,gl.GL_FLOAT,0,  bufferColorRectanguloUno);
        gl.glEnableClientState(gl.GL_COLOR_ARRAY);

        gl.glLineWidth(4);
        gl.glDrawArrays(gl.GL_LINES,0,verticesRectanguloUno.length/2);

        //Rectangulo 2
        /////////////////////////////////////////////////////////////////////////////////////
        bufferVerticesRectanguloDos.position(0);
        gl.glVertexPointer(componentesVertices, gl.GL_FLOAT, 0, bufferVerticesRectanguloDos);
        gl.glEnableClientState(gl.GL_VERTEX_ARRAY);

        bufferColorRectanguloDos.position(0);
        gl.glColorPointer(4,gl.GL_FLOAT,0,  bufferColorRectanguloDos);
        gl.glEnableClientState(gl.GL_COLOR_ARRAY);

        gl.glLineWidth(4);
        gl.glDrawArrays(gl.GL_LINES,0,verticesRectanguloDos.length/2);

        //Rueda 1

        /////////////////////////////////////////////////////////////////////////////////////
        bufferVerticesCirculoUno.position(0);
        gl.glVertexPointer(componentesVertices, gl.GL_FLOAT, 0, bufferVerticesCirculoUno);
        gl.glEnableClientState(gl.GL_VERTEX_ARRAY);

        bufferColorCirculoUno.position(0);
        gl.glEnableClientState(gl.GL_COLOR_ARRAY);
        gl.glColorPointer(4,gl.GL_FLOAT,0, bufferColorCirculoUno);

        gl.glPointSize(15);
        gl.glDrawArrays(gl.GL_POINTS, 0, numeroPuntos);

        //Rueda 2
        /////////////////////////////////////////////////////////////////////////////////////
        bufferVerticesCirculoDos.position(0);
        gl.glVertexPointer(componentesVertices, gl.GL_FLOAT, 0, bufferVerticesCirculoDos);
        gl.glEnableClientState(gl.GL_VERTEX_ARRAY);
        gl.glColorPointer(4,gl.GL_FLOAT,0, bufferColorCirculoDos);

        bufferColorCirculoDos.position(0);
        gl.glEnableClientState(gl.GL_COLOR_ARRAY);

        gl.glPointSize(15);
        gl.glDrawArrays(gl.GL_POINTS, 0, numeroPuntos);

        /////////////////////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////////////////////

        /*
        bufferColorCirculoUno.position(0);
        gl.glColorPointer(4,gl.GL_FLOAT,0, bufferColorCirculoUno);
        gl.glEnableClientState(gl.GL_COLOR_ARRAY);
*/



        /////////////////////////////////////////////////////////////////////////////////////


        gl.glFrontFace(gl.GL_CCW);

        gl.glDisableClientState(gl.GL_VERTEX_ARRAY);
        gl.glDisableClientState(gl.GL_COLOR_ARRAY);;

        gl.glDisableClientState(gl.GL_VERTEX_ARRAY);
        gl.glDisableClientState(gl.GL_COLOR_ARRAY);;

        gl.glDisableClientState(gl.GL_VERTEX_ARRAY);
        gl.glDisableClientState(gl.GL_VERTEX_ARRAY);
        gl.glDisableClientState(gl.GL_COLOR_ARRAY);;

    }
}
