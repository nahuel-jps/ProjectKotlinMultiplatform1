package com.ucb.app.dollar.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dollars") // Este paquete entity sirve para decirle a room que es una entidad, las entidades sirven para guardar datos porque son tablas en la base de datos
data class DollarEntity(
    @ColumnInfo(name = "dollar_official") // ColumnInfo sirve para decirle a room que es una columna de la tabla
    var dollarOfficial: String? = null,


    @ColumnInfo(name = "dollar_parallel")
    var dollarParallel: String? = null,


    @ColumnInfo(name = "timestamp")
    var timestamp: Long = 0) // timestamp sirve para guardar la fecha de creacion del registro
{
    @PrimaryKey(autoGenerate = true) // para decirle a room que es la llave primaria de la tabla que es el id que se autoincrementa
    @ColumnInfo(name = "id")
    var id: Int = 0
}