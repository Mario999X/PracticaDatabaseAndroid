package com.example.pruebadatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pruebadatabase.data.DatoEntity;
import com.example.pruebadatabase.data.DatoRoomDatabase;

import java.util.ArrayList;
import java.util.List;

/*  ORDEN PROYECTO:

    1. CARGAR DEPENDENCIAS EN BUILD.GRADLE(MODULE):
        implementation 'androidx.room:room-runtime:2.3.0'
        annotationProcessor 'androidx.room:room-compiler:2.3.0'

    2. CREAR PAQUETE data, GENERAMOS CLASES ENTIDAD (DataEntity, Usuario, Coche...)
    * SI SE GENERAN DOS ENTIDADES (Alumnos - Notas), SE GENERAN DOS TABLAS = DOS CLASES

    3. SE CREA LA INTERFAZ DataDao (LoqueseaDao)
    SE USA PARA METODOS COMO INSERTAR, ELIMINAR, O REALIZAR CONSULTAS

    4. CREAR LA CLASE ABSTRACTA DatoRoomDatabase

    5. MOMENTO LAYOUT APP

    6. PROGRAMACION DE LA ACTIVIDAD Y DEMAS CLASES

        !! [Android Studio] MIRAR LA FUNCION "App Inspection" PARA LA BASE DE DATOS,
            SOLO FUNCIONA CON LA APP EN MOVIMIENTO
*/

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ClickListener clickListener;

    // 3. Variables y su conexion a la vista
    Button btnInsert, btnDelete, btnSearch, btnSelect, btnDeleteAll;
    EditText editTextNombre, editTextEdad, editTextNombreBorrar, editTextNombreBuscar;
    RecyclerView recyclerView;

    List<DatoEntity> datoEntityList = new ArrayList<>();
    AdapterRecylerView adapterView;
    LinearLayoutManager linearLayoutManager;

    // 1. Crear variable Database
    DatoRoomDatabase database;

    // 5. Generar entidad
    DatoEntity dato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 2. Instanciar base de datos
        database = DatoRoomDatabase.getInstance(this);

        // 3. Variables y su conexion a la vista
        enlazarComponentes();

        actualizarLista();

    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btnInsert:
                dato = new DatoEntity();

                dato.setName(editTextNombre.getText().toString());
                dato.setAge(editTextEdad.getText().toString());

                long resultado = database.dataDao().insert(dato);

                actualizarLista();
                Log.i("insert() = ", "" + resultado); // Comprobacion
                break;

            case R.id.btnSelect:
                actualizarLista();
                break;

            case R.id.btnDelete:
                database.dataDao().deleteName(editTextNombreBorrar.getText().toString());
                Toast.makeText(this, "Usuario Eliminado", Toast.LENGTH_SHORT).show();

                actualizarLista();
                break;

            case R.id.btnSearch:
                datoEntityList = database.dataDao().search(editTextNombreBuscar.getText().toString());
                Toast.makeText(this, "Búsqueda Realizada", Toast.LENGTH_SHORT).show();

                mostrarLista(datoEntityList);
                break;

            case R.id.btnDeleteAll:
                database.dataDao().deleteAll();
                Toast.makeText(this, "Contenido Eliminado", Toast.LENGTH_SHORT).show();

                actualizarLista();
        }


    }

    private void enlazarComponentes() {

        editTextNombre = findViewById(R.id.editTextNombre);
        editTextEdad = findViewById(R.id.editTextEdad);
        editTextNombreBorrar = findViewById(R.id.editTextBorrar);
        editTextNombreBuscar = findViewById(R.id.editTextBuscar);

        btnInsert = findViewById(R.id.btnInsert);
        btnDelete = findViewById(R.id.btnDelete);
        btnSearch = findViewById(R.id.btnSearch);
        btnSelect = findViewById(R.id.btnSelect);
        btnDeleteAll = findViewById(R.id.btnDeleteAll);

        recyclerView = findViewById(R.id.recyclerView);

        // 4. Implementar listener a botones y sus acciones
        btnInsert.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnSearch.setOnClickListener(this);
        btnSelect.setOnClickListener(this);
        btnDeleteAll.setOnClickListener(this);

    }

    // Metodo encargado de enlazar los distintos componentes al recyclerView y mostrarlo en la vista.
    // En este caso, se requiere para realizar la busqueda con el metodo "search" de DataDao.
    private void mostrarLista(List<DatoEntity> dl) {

        listenerLista();
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapterView = new AdapterRecylerView(datoEntityList, this, clickListener);
        recyclerView.setAdapter(adapterView);
    }

    // Metodo encargado de actualizar la lista, realizando una consulta a la base de datos.
    private void actualizarLista() {
        datoEntityList = database.dataDao().selectAll();
        mostrarLista(datoEntityList);

    }

    // Metodo encargado de preparar la accion al pulsar sobre un elemento de la lista
    private void listenerLista(){
        clickListener = posicion -> {
            Intent intent = new Intent(getApplicationContext(), ActivityDetalle.class);
            intent.putExtra("nombre", datoEntityList.get(posicion).getName());
            intent.putExtra("edad", datoEntityList.get(posicion).getAge().toString());
            startActivity(intent);
        };
    }
}