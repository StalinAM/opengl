package ec.edu.uce.pa.activities;

import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class RenderesParaVersionDos extends AppCompatActivity {

    private static GLSurfaceView.Renderer renderer;

    public static GLSurfaceView.Renderer getRenderer() {
        return renderer;
    }
    public static void setRenderer(GLSurfaceView.Renderer renderer) {
        RenderesParaVersionDos.renderer = renderer;
    }

    private GLSurfaceView view;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = new GLSurfaceView(this);
        view.setEGLContextClientVersion(2);
        view.setRenderer(renderer);
        setContentView(view);
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
        Intent intent = new Intent(this, VersionDos.class);
        startActivity(intent);
    }

}

