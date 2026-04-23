package com.ucb.app.movie.domain.model

// Repaso: data class sin valores por defecto (= "") como en GithubModel.
// Eso significa que para crear un MovieModel estás OBLIGADO a pasar los 3 datos.
// Sin llaves {} al final porque no tiene funciones internas extra: solo transporta datos.
// Los campos representan exactamente los datos de una película que la regla de negocio necesita:
// description (descripción), title (título), pathUrl (URL de la imagen/portada).
data class MovieModel(
    val description: String,
    val title: String,
    val pathUrl: String
)