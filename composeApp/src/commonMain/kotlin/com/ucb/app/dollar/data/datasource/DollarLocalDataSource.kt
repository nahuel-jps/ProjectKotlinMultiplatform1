package com.ucb.app.dollar.data.datasource

import com.ucb.app.dollar.data.entity.DollarEntity


interface DollarLocalDataSource { // DollarLocalDataSource es una interfaz que se implementa en el repositorio, el repositorio es el que se encarga de acceder a la base de datos
    suspend fun save(entity: DollarEntity)
    suspend fun list() : List<DollarEntity>
}