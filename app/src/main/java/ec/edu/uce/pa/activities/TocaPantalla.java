package ec.edu.uce.pa.activities;

import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.MotionEvent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import ec.edu.uce.pa.renderes.RenderTocaPantalla;


public class TocaPantalla extends AppCompatActivity {

    private GLSurfaceView view;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = new GLSurfaceView(this);
        view.setRenderer(new RenderTocaPantalla());
        setContentView(view);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                RenderTocaPantalla.f1 = (float) Math.random();
                RenderTocaPantalla.f2 = (float) Math.random();
                RenderTocaPantalla.f3 = (float) Math.random();
                RenderTocaPantalla.f4 = (float) Math.random();
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, OpcionColor.class);
        startActivity(intent);
    }

}

