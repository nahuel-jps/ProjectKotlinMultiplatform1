package com.ucb.app.dollar.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ucb.app.dollar.data.entity.DollarEntity

@Dao // Un dao es una interfaz que contiene metodos para acceder a la base de datos, son el CRUD de la base de datos
interface DollarDao { // DollarDao es una interfaz que se implementa en el repositorio, el repositorio es el que se encarga de acceder a la base de datos
    @Query("SELECT * FROM dollars") // esto es un metodo get, READ
    suspend fun getList(): List<DollarEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE) // esto es un metodo insert, CREATE
    suspend fun insert(dollar: DollarEntity)

    @Query("DELETE FROM dollars") // esto es un metodo delete, DELETE
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.IGNORE) // esto es un metodo insert, CREATE
    suspend fun insertDollars(lists: List<DollarEntity>)
}
