package com.example.pruebadatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ActivityDetalle extends AppCompatActivity {

    TextView textViewNombreDetalle, textViewEdadDetalle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        textViewNombreDetalle = findViewById(R.id.textViewNombreDetalle);
        textViewEdadDetalle = findViewById(R.id.textViewEdadDetalle);

        pasoDatos();
    }

    private void pasoDatos(){
        String nombre = "";
        String edad = "";

        Bundle extras = getIntent().getExtras();
        if (extras != null){
            nombre = extras.getString("nombre");
            edad = extras.getString("edad");
        }
        textViewNombreDetalle.setText("Nombre: " + nombre);
        textViewEdadDetalle.setText("Edad: "+ edad);
    }
}