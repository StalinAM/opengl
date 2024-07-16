package ec.edu.uce.pa.activities;


import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import ec.edu.uce.pa.pae.cuboMovimientoTeclado.RenderGiroMundo;


public class GiroMundo extends AppCompatActivity {

    private GLSurfaceView view;

    private static float rotarX = 1.0f;
    private static float rotarY = 1.0f;

    public static float getRotarX() {
        return rotarX;
    }

    public static float getRotarY() {
        return rotarY;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = new GLSurfaceView(this);
        view.setEGLContextClientVersion(1);
        view.setRenderer(new RenderGiroMundo());
        setContentView(view);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_UP:
                rotarX+=10.0f;
                break;
            case KeyEvent.KEYCODE_DPAD_DOWN:
                rotarX-=10.0f;
                break;
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                rotarY+=10.0f;
                break;
            case KeyEvent.KEYCODE_DPAD_LEFT:
                rotarY-=10.0f;
                break;
        }
        view.requestRender();
        return super.onKeyDown(keyCode,event);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, GirarCubo.class);
        startActivity(intent);
        finish();
    }

}



