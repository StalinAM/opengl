package ec.edu.uce.pa.pae.linternaPlano;


import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;



public class ConoLintarna {

    private FloatBuffer bufferVerticesCono;
    private FloatBuffer bufferColoresCono;

    private FloatBuffer bufferTexturasCono;


    private final static int componentesVertices = 3;
    private final static int componentesColores = 4;

    private static int puntos=36;

    private float [] verticesCono;
    private float [] coloresCono;

    private float[] texturasCono;

    public ConoLintarna(float altura, float radio, float [] color) {
        verticesCono = myArrayVertives(altura,radio);
        coloresCono =myArrayColores(color);
        texturasCono = myArrayTexturas();
        bufferVerticesCono = FuncionesLampara.myBuffer(verticesCono);
        bufferColoresCono = FuncionesLampara.myBuffer(coloresCono);
        bufferTexturasCono = FuncionesLampara.myBuffer(texturasCono);



    }

    public static  float [] myArrayVertives(float altura,float radio){
        float[] v = new float [puntos*3];
        v [0] = 0.0f;
        v [1] = altura;
        v [2] = 0.0f;
        double angulo = 0;
        for(int i=3; i<v.length; i += 3){
            v[i] = radio *(float)(Math.sin(Math.toRadians(angulo)));//X
            v[i+1] = 0.0f;//Y
            v[i+2] = radio*(float)(Math.cos(Math.toRadians(angulo)));//Z
            angulo += 10; //sube de 10 en 10 los grados

        }
        v [v.length-3] = v [3];
        v [v.length-2] = 0.0f;
        v [v.length-1] = v [5];
        return v;
    }

    public static float[] myArrayTexturas() {
        float[] t = new float[puntos * 2]; // 2 componentes para las coordenadas de textura (U, V)

        // Coordenadas de textura para el vértice en la parte superior del cono
        t[0] = 0.5f; // U = 0.5 (centro horizontal de la textura)
        t[1] = 1.0f; // V = 1.0 (parte superior de la textura)

        // Coordenadas de textura para los vértices en el cuerpo del cono
        for (int i = 1; i < puntos - 1; i++) {
            float anguloNormalizado = (i - 1) / (float) (puntos - 2); // Normaliza el ángulo entre 0.0 y 1.0
            float u = anguloNormalizado * 2.0f * (float) Math.PI; // Mapea el ángulo al rango 0.0 a 2*PI (una vuelta completa)

            t[i * 2] = u / (2.0f * (float) Math.PI); // U varía de 0.0 a 1.0 a medida que avanzamos alrededor del cono
            t[i * 2 + 1] = 0.0f; // V = 0.0 (parte inferior de la textura)
        }

        // Coordenadas de textura para el vértice en la parte inferior del cono
        t[(puntos - 1) * 2] = 0.5f; // U = 0.5 (centro horizontal de la textura)
        t[(puntos - 1) * 2 + 1] = 0.0f; // V = 0.0 (parte inferior de la textura)

        return t;
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

        bufferTexturasCono.position(0); // Agrega esta línea para habilitar el buffer de texturas
        gl.glTexCoordPointer(2, gl.GL_FLOAT, 0, bufferTexturasCono); // Agrega esta línea para definir el puntero de coordenadas de textura
        gl.glEnableClientState(gl.GL_TEXTURE_COORD_ARRAY); // Agrega esta línea para habilitar el puntero de coordenadas de textura

        gl.glDrawArrays(gl.GL_TRIANGLE_FAN,0,puntos);

        gl.glFrontFace(gl.GL_CCW);
        gl.glDisableClientState(gl.GL_VERTEX_ARRAY);
        gl.glDisableClientState(gl.GL_COLOR_ARRAY);
        gl.glDisableClientState(gl.GL_TEXTURE_COORD_ARRAY);

    }
}
