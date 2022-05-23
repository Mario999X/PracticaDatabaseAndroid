package com.example.pruebadatabase.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

// CLASE ENTIDAD

// 1. Generamos la entidad
@Entity(tableName = "datos_tabla")
public class DatoEntity {

    // 2. Generamos la key primaria
    @PrimaryKey(autoGenerate = true)
    public Integer id;
    public String name;
    public String age;

    // 3. Generamos get y set
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
