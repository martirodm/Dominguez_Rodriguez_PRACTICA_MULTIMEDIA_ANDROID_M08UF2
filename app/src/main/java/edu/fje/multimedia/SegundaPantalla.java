package edu.fje.multimedia;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.snackbar.Snackbar;

public class SegundaPantalla extends AppCompatActivity {

    private CoordinatorLayout coordinatorLayout;
    private ImageButton cameraButton;
    private ImageButton galleryButton;
    private RadioGroup radioGroup;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout2);
        coordinatorLayout = findViewById(R.id.coordinator);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Obtener referencias a los botones de la cámara y la galería
        cameraButton = findViewById(R.id.imageButton2);
        galleryButton = findViewById(R.id.imageButton3);

        // Obtener referencia al RadioGroup
        radioGroup = findViewById(R.id.radioGroup);

        // Obtener referencia al botón
        button = findViewById(R.id.button);

        // Configurar animación para el botón
        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(button, "scaleX", 0f, 1f);
        scaleXAnimator.setDuration(1000);
        scaleXAnimator.setInterpolator(new AccelerateDecelerateInterpolator());

        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(button, "scaleY", 0f, 1f);
        scaleYAnimator.setDuration(1000);
        scaleYAnimator.setInterpolator(new AccelerateDecelerateInterpolator());

        scaleXAnimator.start();
        scaleYAnimator.start();

        // Agregar listener de clic al botón
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Código para abrir puzzle
                Intent intent = new Intent(SegundaPantalla.this, PuzzleFacil.class);
                startActivity(intent);
            }
        });

        // Agregar listener de clic al botón de la cámara
        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirCamara();
            }
        });

        // Agregar listener de clic al botón de la galería
        galleryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirGaleria();
            }
        });

        // Agregar listener para detectar cambios en la selección de RadioButton
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Desseleccionar todos los botones de opción si se selecciona uno
                RadioButton radioButtonFacil = findViewById(R.id.radioButtonFacil);
                RadioButton radioButtonIntermedio = findViewById(R.id.radioButtonIntermedio);
                RadioButton radioButtonDificil = findViewById(R.id.radioButtonDificil);

                if (checkedId == R.id.radioButtonFacil) {
                    radioButtonIntermedio.setChecked(false);
                    radioButtonDificil.setChecked(false);
                } else if (checkedId == R.id.radioButtonIntermedio) {
                    radioButtonFacil.setChecked(false);
                    radioButtonDificil.setChecked(false);
                } else if (checkedId == R.id.radioButtonDificil) {
                    radioButtonFacil.setChecked(false);
                    radioButtonIntermedio.setChecked(false);
                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.ajustos) {
            abrirAjustos();
            return true;
        }
        return false;
    }

    private void abrirCamara() {
        // Crear un intent para abrir la aplicación de la cámara
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivity(intent);
    }

    private void abrirGaleria() {
        // Crear un intent para abrir la galería de imágenes
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivity(intent);
    }
    private void abrirAjustos() {
        Snackbar.make(coordinatorLayout, "AJUSTOS", Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show();
    }
}


