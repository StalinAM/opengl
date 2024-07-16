package ec.edu.uce.pa.pae.universoEscalaTexturas;

public class CreacionPlanetaSistemaTexturas {
    public static float[] valores = {
            946,
            1430,
            2400,
            3121,
            3500,
            4820,
            4900,
            5149,
            5268,
            6800,
            12000,
            13000,
            30000,
            50000,
            51000,
            120000,
            140000,
            1400000,
            2400000,
            5800000,
            12200000,
            35300000
    };

    public static int factorEscala = 1200;
    public static float maximoValor = valores[valores.length-1];

    // Escalamos los valores
    public static float[] valoresEscalados = escalarValores(valores, factorEscala,maximoValor);

    public static float[] escalarValores(float[] valores, int factor, float maximoValor) {
        float[] valoresEscalados = new float[valores.length];
        for (int i = 0; i < valores.length; i++) {
            valoresEscalados[i] = (valores[i] * factor)/ maximoValor;
        }
        return valoresEscalados;
    }

}
