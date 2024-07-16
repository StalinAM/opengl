package ec.edu.uce.pa.pae.linternaPlano;


import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;



public class ConoBlend {

    private FloatBuffer bufferVerticesCono;
    private FloatBuffer bufferColoresCono;


    private final static int componentesVertices = 3;
    private final static int componentesColores = 4;

    private static int puntos=36;

    private float [] verticesCono;
    private float [] coloresCono;


    public ConoBlend(float altura, float radio, float [] color) {
        verticesCono = myArrayVertives(altura,radio);
        coloresCono =myArrayColores(color);
        bufferVerticesCono = FuncionesLampara.myBuffer(verticesCono);
        bufferColoresCono = FuncionesLampara.myBuffer(coloresCono);



    }

    public static  float [] myArrayVertives(float altura, float radio){

        float[] v = new float [puntos*3];
        v [0] = 0.0f;
        v [1] = altura;
        v [2] = 0.0f;
        double angulo = 0;
        for(int i=3; i<v.length; i += 3){
            v[i] = radio *(float)(Math.sin(Math.toRadians(angulo)));//X
            v[i+1] = 0.0f;//Y
            v[i+2] = radio*(float)(Math.cos(Math.toRadians(angulo)));//Z
            angulo += 10;

        }
        v [v.length-3] = v [3];
        v [v.length-2] = 0.0f;
        v [v.length-1] = v [5];
        return v;
    }

        public static  float [] myArrayColores(float [] colores){
        float[] co = new float [puntos*4];

        co [0] = colores[0];
        co [1] = colores[1];
        co [2] = colores[2];
        co [3] = colores[3];;

        for (int i=4; i < co.length; i += 4){
            co[i] = colores[0];
            co[i+1] = colores[1];
            co[i+2] = colores[2];
            co[i+3] = colores[3];
        }
        return co;
    }

    public void dibujar (GL10 gl){
        gl.glFrontFace(gl.GL_CW);

        bufferVerticesCono.position(0);
        gl.glVertexPointer(componentesVertices, gl.GL_FLOAT, 0, bufferVerticesCono);
        gl.glEnableClientState(gl.GL_VERTEX_ARRAY);

        bufferColoresCono.position(0);
        gl.glColorPointer(componentesColores, gl.GL_FLOAT, 0, bufferColoresCono);
        gl.glEnableClientState(gl.GL_COLOR_ARRAY);

        gl.glDrawArrays(gl.GL_TRIANGLE_FAN,0,puntos);

        gl.glFrontFace(gl.GL_CCW);
        gl.glDisableClientState(gl.GL_VERTEX_ARRAY);
        gl.glDisableClientState(gl.GL_COLOR_ARRAY);

    }
}
