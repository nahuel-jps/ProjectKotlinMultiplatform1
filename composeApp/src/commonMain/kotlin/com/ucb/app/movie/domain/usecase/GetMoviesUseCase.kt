package com.ucb.app.movie.domain.usecase

import com.ucb.app.movie.domain.model.MovieModel
import com.ucb.app.movie.domain.repository.MovieRepository

class GetMoviesUseCase(
    // Igual que siempre: el UseCase pide el contrato (interfaz) en su constructor, nunca la implementación real.
    val repository: MovieRepository
) {
    // DIFERENCIA clave vs GetAvatarUseCase: los paréntesis de invoke() están VACÍOS.
    // La regla de negocio para "obtener todas las películas" no necesita que el usuario le pase nada.
    // Solo ejecuta la acción y devuelve el resultado, sin necesitar datos de entrada.
    suspend fun invoke(): List<MovieModel> {
        // El UseCase no hace ninguna transformación extra aquí.
        // Es un caso simple: le pide la lista al repositorio y la devuelve tal cual hacia arriba (Presentación).
        return repository.getMovies()
    }
}