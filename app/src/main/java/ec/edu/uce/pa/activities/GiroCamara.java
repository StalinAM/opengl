package ec.edu.uce.pa.activities;

import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import ec.edu.uce.pa.pae.cuboMovimientoTeclado.RenderGiraCam;


public class GiroCamara extends AppCompatActivity {
    private GLSurfaceView view;
    private GLSurfaceView.Renderer renderer;
    public static float rotacionX = 0, rotacionY = 0, rotacionZ = 0;
    public static float iX = 0, iY = -2, iZ = -7, iYC = 2;
    public static float iRotacion = 0, iRotacionC = 0;
    public static boolean rotar = false, ejeCamara = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = new GLSurfaceView(this);
        view.setEGLContextClientVersion(1);
        view.setRenderer(new RenderGiraCam());
        setContentView(view);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, GirarCubo.class);
        startActivity(intent);
        finish();
    }
    int contador;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

            switch (keyCode) {
                case KeyEvent.KEYCODE_W:
                    iY += 0.1f;
                    iYC += 0.1f;
                    //Toast.makeText(this,"W arriba" , Toast.LENGTH_SHORT).show();
                    return true;
                case KeyEvent.KEYCODE_S:
                    iY -= 0.1f;
                    iYC -= 0.1f;
                    //Toast.makeText(this,"S abajo" , Toast.LENGTH_SHORT).show();
                    return true;
                case KeyEvent.KEYCODE_A:
                    iX -= 0.1f;
                    //Toast.makeText(this,"A izquierda" , Toast.LENGTH_SHORT).show();
                    return true;
                case KeyEvent.KEYCODE_D:
                    iX += 0.1f;
                    //Toast.makeText(this,"D derecha" , Toast.LENGTH_SHORT).show();
                    return true;
                case KeyEvent.KEYCODE_Q:
                    iZ -= 0.1f;
                    //Toast.makeText(this,"Q lejos" , Toast.LENGTH_SHORT).show();
                    return true;

                case KeyEvent.KEYCODE_E:
                    iZ += 0.1f;
                    //Toast.makeText(this,"E Cerca" , Toast.LENGTH_SHORT).show();
                    return true;

                case KeyEvent.KEYCODE_DPAD_UP:
                    ejeCamara = false;
                    rotar = true;
                    rotacionX = 1;
                    rotacionY = 0;
                    rotacionZ = 0;
                    iRotacion -= 2.5f;
                    iRotacionC -= 0.01f;
                    //Toast.makeText(this,"Rotar X" , Toast.LENGTH_SHORT).show();
                    return true;

                case KeyEvent.KEYCODE_DPAD_DOWN:
                    ejeCamara = false;
                    rotar = true;
                    rotacionX = 1;
                    rotacionY = 0;
                    rotacionZ = 0;
                    iRotacion += 2.5f;
                    iRotacionC += 0.01f;
                    //Toast.makeText(this,"Rotar X" , Toast.LENGTH_SHORT).show();
                    return true;

                case KeyEvent.KEYCODE_DPAD_LEFT:
                    ejeCamara = true;
                    rotar = true;
                    rotacionX = 0;
                    rotacionY = 1;
                    rotacionZ = 0;
                    iRotacion -= 2.5f;
                    iRotacionC += 0.01f;
                    //Toast.makeText(this,"Rotar Y" , Toast.LENGTH_SHORT).show();
                    return true;

                case KeyEvent.KEYCODE_DPAD_RIGHT:
                    ejeCamara = true;
                    rotar = true;
                    rotacionX = 0;
                    rotacionY = 1;
                    rotacionZ = 0;
                    iRotacion += 2.5f;
                    iRotacionC -= 0.01f;
                    //Toast.makeText(this,"Rotar Y " , Toast.LENGTH_SHORT).show();
                    return true;

                case KeyEvent.KEYCODE_J:
                    rotar = true;
                    rotacionX = 1;
                    rotacionY = 0;
                    rotacionZ = 1;
                    iRotacion += 2.5f;
                    return true;

                case KeyEvent.KEYCODE_K:
                    rotar = true;
                    rotacionX = 1;
                    rotacionY = 0;
                    rotacionZ = 1;
                    iRotacion -= 2.5f;
                    return true;

                case KeyEvent.KEYCODE_U:
                    rotar = true;
                    rotacionX = -1;
                    rotacionY = 0;
                    rotacionZ = 1;
                    iRotacion += 2.5f;
                    return true;

                case KeyEvent.KEYCODE_I:
                    rotar = true;
                    rotacionX = -1;
                    rotacionY = 0;
                    rotacionZ = 1;
                    iRotacion -= 2.5f;
                    return true;

                case KeyEvent.KEYCODE_BACK:
                    contador++;
                    rotacionX = 0;
                    rotacionY = 0;
                    rotacionZ = 0;
                    iX = 0;
                    iY = -2;
                    iZ = -7;
                    iYC = 2;
                    iRotacion = 0;
                    rotar = false;
                    iRotacionC = 0;
                    ejeCamara = false;

                Intent intent = new Intent(this, GirarCubo.class);
                startActivity(intent);
                finish();


                    return true;
        }
        view.requestRender();
        return super.onKeyDown(keyCode,event);
    }


}


