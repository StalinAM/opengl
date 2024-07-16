package ec.edu.uce.pa.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

import ec.edu.uce.pa.pae.universoEscalaTexturas.RenderSistemaConTexturas;


public class MoverMundoTexturas extends AppCompatActivity {

    private GLSurfaceView view;
    private GLSurfaceView.Renderer renderer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = new GLSurfaceView(this);
        view.setEGLContextClientVersion(1);
        renderer = new RenderSistemaConTexturas(this);
        view.setRenderer(renderer);
        setContentView(view);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_UP:
                if (RenderSistemaConTexturas.z <= -300) {
                    RenderSistemaConTexturas.z += 100;
                } else {
                    RenderSistemaConTexturas.z += 10;
                }
                Log.i(String.valueOf(RenderSistemaConTexturas.z),"xd3");
                break;
            case KeyEvent.KEYCODE_DPAD_DOWN:

                if (RenderSistemaConTexturas.z >= -200) {
                    RenderSistemaConTexturas.z -= 10;
                } else {
                    RenderSistemaConTexturas.z -= 350;
                }
                Log.i(String.valueOf(RenderSistemaConTexturas.z),"xd");

                break;
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                if (RenderSistemaConTexturas.x >= -600) {
                    RenderSistemaConTexturas.x -= 10;
                } else {
                    RenderSistemaConTexturas.x -= 300;
                }
                Log.i(String.valueOf(RenderSistemaConTexturas.x),"xd2");
                break;
            case KeyEvent.KEYCODE_DPAD_LEFT:
                if (RenderSistemaConTexturas.x <= -600) {
                    RenderSistemaConTexturas.x += 300;
                } else {
                    RenderSistemaConTexturas.x += 10;
                }
                Log.i(String.valueOf(RenderSistemaConTexturas.z),"xd4");
                break;
            case KeyEvent.KEYCODE_W:
                RenderSistemaConTexturas.y += 1;
                break;
            case KeyEvent.KEYCODE_S:
                RenderSistemaConTexturas.y -= 1;
                break;
        }
        view.requestRender();
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, PaginaDosVersionUno.class);
        startActivity(intent);
        finish();
    }

}