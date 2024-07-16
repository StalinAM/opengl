package ec.edu.uce.pa.pae.universoEscalaMateriales;

import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;


public class AstroSistemaMateriales {

    private FloatBuffer bufferVertices;
    private FloatBuffer bufferNormales;
    private final static int comPorVertices=3;

    private int franjas, cortes;


    public AstroSistemaMateriales(int franjas, int cortes, float radio, float ejePolar) {
        this.franjas=franjas;
        this.cortes=cortes;

        float [] vertices ;

        float [] normales;

        int iVertice = 0;

        int iNormal=0;

        vertices = new float[3 * ((cortes * 2 + 2) * franjas)];

        normales = new float[3 * ((cortes * 2 + 2) * franjas)];

        int i, j;

        // Bucle para construir las franjas de la esfera
        // Latitudes
        for(i = 0; i < franjas; i++)  {
            //empieza en -90 grados (-1.57 radianes) incrementa hasta +90 grados  (o +1.57 radianes)
            //Phi   --> angulo de latitud /(cCortes)
            //Theta --> angulo de longitud(Franjas)

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

                vertices[iNormal+0] =  cosPhi0 * cosTheta;          //x
                vertices[iNormal+1] =  sinPhi0 ;    //y
                vertices[iNormal+2] =  cosPhi0 * sinTheta;        //z

                vertices[iNormal+3] = cosPhi1 * cosTheta;          //x'
                vertices[iNormal+4] =  sinPhi1 ;    //y'
                vertices[iNormal+5] =  cosPhi1 * sinTheta;

                iVertice += 2*3;
                iNormal +=2*3;
            }

            vertices[iVertice+0] = vertices[iVertice+3];
            vertices[iVertice+3] = vertices[iVertice-3];
            vertices[iVertice+1] = vertices[iVertice+4];
            vertices[iVertice+4] = vertices[iVertice-2];
            vertices[iVertice+2] = vertices[iVertice+5];
            vertices[iVertice+5] = vertices[iVertice-1];
        }

        bufferVertices= FuncionesMateriales.generarBuffer( vertices);
        bufferNormales=FuncionesMateriales.generarBuffer(normales);


    }
    public void dibujar(GL10 gl){
        gl.glFrontFace(gl.GL_CW);
        bufferVertices.position(0);

        gl.glVertexPointer(comPorVertices, gl.GL_FLOAT,0 ,bufferVertices);
        gl.glEnableClientState(gl.GL_VERTEX_ARRAY);

        gl.glDrawArrays(gl.GL_TRIANGLE_STRIP, 0, franjas*cortes*2);



        gl.glFrontFace(gl.GL_CCW);
        gl.glDisableClientState(gl.GL_VERTEX_ARRAY);
        gl.glDisableClientState(gl.GL_COLOR_ARRAY);
    }



}
