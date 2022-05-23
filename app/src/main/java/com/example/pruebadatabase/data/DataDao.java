package com.example.pruebadatabase.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

// !! REVISAR CONSULTAS BASES DE DATOS
// INTERFAZ DAO DONDE SE ESCRIBIRAN LAS CONSULTAS
@Dao
public interface DataDao {

    @Insert
    public long insert(DatoEntity dato); // Devuelve 1, si falla 0 | Inserta un DatoEntity

    @Query("DELETE FROM datos_tabla WHERE name = :mName")
    public void deleteName(String mName); // Elimina toda equivalencia de nombres que localice en la tabla

    @Query("SELECT * FROM datos_tabla")
    public List<DatoEntity> selectAll(); // Mostrar todos los datos de la tabla

    @Query("SELECT * FROM datos_tabla WHERE name = :mName")
    public List<DatoEntity> search(String mName); // Buscar en la tabla, nombre especifico

    @Query("DELETE FROM datos_tabla")
    public void deleteAll(); // Borrar el contenido de la tabla

    /*@Query("SELECT name FROM datos_tabla ORDER BY id")
    public List<DatoEntity> showName(); // Mostrar los nombres ordenados por el id
*/

}
