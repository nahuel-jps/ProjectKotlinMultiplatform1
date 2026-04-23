package com.ucb.app.movie.domain.repository

import com.ucb.app.movie.domain.model.MovieModel

interface MovieRepository {
    // *** CONCEPTO NUEVO Y MUY IMPORTANTE: List<MovieModel> ***
    // Antes, el repositorio de Github devolvía UN SOLO objeto: ': GithubModel'.
    // Aquí el contrato devuelve 'List<MovieModel>', es decir, una LISTA (colección) de muchos MovieModel.
    // 'List' es una clase de Kotlin que agrupa muchos objetos del mismo tipo en orden.
    // El '<MovieModel>' entre los '<>' se llama 'tipo genérico' y le dice a la lista: 
    // "No eres una lista de cualquier cosa, eres específicamente una lista de MovieModel".
    // Es como un estante con divisiones: el estante es la 'List' y cada división guarda un 'MovieModel'.
    // Nota: la función no recibe parámetros (paréntesis vacíos), solo va a buscar todas las películas.
    suspend fun getMovies(): List<MovieModel>
}