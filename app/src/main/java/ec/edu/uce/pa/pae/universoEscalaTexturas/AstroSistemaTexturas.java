package ec.edu.uce.pa.pae.universoEscalaTexturas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;



public class AstroSistemaTexturas {
    private FloatBuffer bufferVertices;
    private FloatBuffer bufferColores;
    private ByteBuffer bufferIndice;
    private FloatBuffer bufferNormales;

    private FloatBuffer bufferTexturas;

    private final static int compPorVertice=3;// esto altera el numero de vertices entre 2d y 3d
    private final static int compPorColores=4;
    private int franjas;
    private int cortes;

    private int[] arrayTexture;
    public AstroSistemaTexturas(int franjas, int cortes, float radio, float ejePolar) {
        this.franjas=franjas;
        this.cortes=cortes;

        float [] vertices ;
        float [] colores;
        float [] normales;
        float [] texturas;

        int iVertice = 0;
        int iColor = 0;
        int iNormal = 0;
        int iTextura = 0;

        vertices = new float[3 * ((cortes * 2 + 2) * franjas)];
        colores = new float[4 * ((cortes * 2 + 2) * franjas)];
        normales = new float[3 * ((cortes * 2 + 2) * franjas)];
        texturas = new float[2 * ((cortes * 2 + 2) * franjas)];

        int i, j;

        // Bucle para construir las franjas de la esfera
        // Latitudes
        for(i = 0; i < franjas; i++)  {
            //empieza en -90 grados (-1.57 radianes) incrementa hasta +90 grados  (o +1.57 radianes)
            //Phi   --> angulo de latitud ( cortes )
            //Theta --> angulo de longitud ( franjas )

            //Valor del angulo para el primer cìrculo
            float phi0 = (float)Math.PI * ((i + 0) * (1.0f/(franjas)) - 0.5f);
            float cosPhi0 = (float)Math.cos(phi0);
            float sinPhi0 = (float)Math.sin(phi0);

            //Valor del angulo para el segundo cìrculo
            float phi1 = (float)Math.PI * ((i + 1 ) * (1.0f/(franjas)) - 0.5f);
            float cosPhi1 = (float)Math.cos(phi1);
            float sinPhi1 = (float)Math.sin(phi1);

            float cosTheta, sinTheta;

            //Bucle para construir los cortes de la esfera
            //Longitudes
            for(j = 0; j < cortes; j++) {
                float theta = (float)(-2.0f * Math.PI * j * (1.0/(cortes -1)));
                cosTheta = (float)Math.cos(theta);
                sinTheta = (float)Math.sin(theta);

                // Dibujar la esfera en duplas, pares de puntos
                vertices[iVertice+0] = radio * cosPhi0 * cosTheta;          //x
                vertices[iVertice+1] = radio * (sinPhi0 * ejePolar);    //y
                vertices[iVertice+2] = radio * (cosPhi0 * sinTheta);        //z

                vertices[iVertice+3] = radio * cosPhi1 * cosTheta;          //x'
                vertices[iVertice+4] = radio * (sinPhi1 * ejePolar);    //y'
                vertices[iVertice+5] = radio * (cosPhi1 * sinTheta);        //z'

                normales[iNormal+0] =  cosPhi0 * cosTheta;          //x
                normales[iNormal+1] =  (sinPhi0);    //y
                normales[iNormal+2] =  (cosPhi0 * sinTheta);        //z

                normales[iNormal+3] =  cosPhi1 * cosTheta;          //x'
                normales[iNormal+4] =  (sinPhi1);    //y'
                normales[iNormal+5] =  (cosPhi1 * sinTheta);        //z'


                texturas[iTextura+0] = j * 1.0f/(cortes-1);          //s
                texturas[iTextura+1] = (i+0) * 1.0f/(franjas-1)*-1;    //t

                texturas[iTextura+2] = j * 1.0f/(cortes-1);        //s'
                texturas[iTextura+3] = (i+1) * 1.0f/(franjas-1)*-1;          //t'

                colores[iColor+0] = 1.0f;
                colores[iColor+1] = 0.5f;
                colores[iColor+2] = 0.25f;
                colores[iColor+3] = 1.0f;

                colores[iColor+4] = 0.25f;
                colores[iColor+5] = 0.5f;
                colores[iColor+6] = 1.0f;
                colores[iColor+7] = 1.0f;

                iColor += 2*4;
                iVertice += 2*3;
                iNormal += 2*3;
                iTextura += 2*2;
            }

            vertices[iVertice+0] = vertices[iVertice+3];
            vertices[iVertice+3] = vertices[iVertice-3];
            vertices[iVertice+1] = vertices[iVertice+4];
            vertices[iVertice+4] = vertices[iVertice-2];
            vertices[iVertice+2] = vertices[iVertice+5];
            vertices[iVertice+5] = vertices[iVertice-1];

        }
        bufferVertices = FuncionesSistemaTexturas.myFloatBuffer(vertices);
        bufferColores = FuncionesSistemaTexturas.myFloatBuffer(colores);
        bufferNormales = FuncionesSistemaTexturas.myFloatBuffer(normales);
        bufferTexturas = FuncionesSistemaTexturas.myFloatBuffer(texturas);
    }
    public void dibujar (GL10 gl,int indiceTextura){
        gl.glFrontFace(gl.GL_CW);
        gl.glVertexPointer(compPorVertice, gl.GL_FLOAT, 0, bufferVertices);
        gl.glColorPointer(compPorColores, gl.GL_FLOAT,0,bufferColores);
        gl.glNormalPointer(gl.GL_FLOAT, 0,bufferNormales);
        gl.glTexCoordPointer(2,gl.GL_FLOAT,0,bufferTexturas);

        gl.glEnableClientState(gl.GL_VERTEX_ARRAY);
        gl.glEnableClientState(gl.GL_COLOR_ARRAY);
        gl.glEnableClientState(gl.GL_NORMAL_ARRAY);
        gl.glEnableClientState(gl.GL_TEXTURE_COORD_ARRAY);

        gl.glBindTexture(gl.GL_TEXTURE_2D,arrayTexture[indiceTextura]);
        gl.glDrawArrays(gl.GL_TRIANGLE_STRIP,0,franjas*cortes*2);
        // 3 = cantidad de indices
        gl.glFrontFace(gl.GL_CCW);
        gl.glDisableClientState(gl.GL_VERTEX_ARRAY);
        gl.glDisableClientState(gl.GL_COLOR_ARRAY);
        gl.glDisableClientState(gl.GL_NORMAL_ARRAY);
        gl.glDisableClientState(gl.GL_TEXTURE_COORD_ARRAY);
    }
    public void habilitarTexturas(GL10 gl,int numTexturas){
        gl.glEnable(gl.GL_TEXTURE_2D);
        arrayTexture = new int[numTexturas];
        gl.glGenTextures(numTexturas,arrayTexture,0);
    }
    public void cargarImagenesTextura(GL10 gl, Context context,int indice, int  idImagen){

        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),idImagen);
        gl.glBindTexture(gl.GL_TEXTURE_2D,arrayTexture[indice]);
        GLUtils.texImage2D(gl.GL_TEXTURE_2D,0,bitmap,0);
        gl.glTexParameterf(gl.GL_TEXTURE_2D,gl.GL_TEXTURE_MAG_FILTER,gl.GL_LINEAR);
        gl.glTexParameterf(gl.GL_TEXTURE_2D,gl.GL_TEXTURE_MIN_FILTER,gl.GL_LINEAR);
        bitmap.recycle();
}
}
