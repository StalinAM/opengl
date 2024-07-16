package ec.edu.uce.pa.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

import ec.edu.uce.pa.pae.universoEscalaMateriales.RenderSistemaConMateriales;


public class MoverMundoMateriales extends AppCompatActivity {

    private GLSurfaceView view;
    private GLSurfaceView.Renderer renderer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        view = new GLSurfaceView(this);
        view.setEGLContextClientVersion(1);
        renderer = new RenderSistemaConMateriales();
        view.setRenderer(renderer);
        setContentView(view);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_UP:
                if (RenderSistemaConMateriales.z <= -200) {
                    RenderSistemaConMateriales.z += 100;
                } else {
                    RenderSistemaConMateriales.z += 10;
                }
                Log.i(String.valueOf(RenderSistemaConMateriales.z),"xd3");
                break;
            case KeyEvent.KEYCODE_DPAD_DOWN:

                if (RenderSistemaConMateriales.z >= -200) {
                    RenderSistemaConMateriales.z -= 10;
                } else {
                    RenderSistemaConMateriales.z -= 350;
                }
                Log.i(String.valueOf(RenderSistemaConMateriales.z),"xd");

                break;
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                if (RenderSistemaConMateriales.x >= -600) {
                    RenderSistemaConMateriales.x -= 10;
                } else {
                    RenderSistemaConMateriales.x -= 300;
                }
                Log.i(String.valueOf(RenderSistemaConMateriales.x),"xd2");
                break;
            case KeyEvent.KEYCODE_DPAD_LEFT:
                if (RenderSistemaConMateriales.x <= -600) {
                    RenderSistemaConMateriales.x += 300;
                } else {
                    RenderSistemaConMateriales.x += 10;
                }
                Log.i(String.valueOf(RenderSistemaConMateriales.z),"xd4");
                break;
            case KeyEvent.KEYCODE_W:
                RenderSistemaConMateriales.y += 1;
                break;
            case KeyEvent.KEYCODE_S:
                RenderSistemaConMateriales.y -= 1;
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