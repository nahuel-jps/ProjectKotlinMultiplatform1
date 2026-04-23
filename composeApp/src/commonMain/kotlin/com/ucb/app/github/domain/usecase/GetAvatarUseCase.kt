package com.ucb.app.github.domain.usecase

import com.ucb.app.github.domain.model.GithubModel
import com.ucb.app.github.domain.repository.GithubRepository

class GetAvatarUseCase(
    // 1. Igual que en el Login: pedimos la interfaz del repositorio (el contrato de Github).
    val repository: GithubRepository
) {
    suspend fun invoke(
        model: GithubModel
    // 2. ¡Novedad en el UseCase! Ahora el invoke también devuelve el modelo (: GithubModel).
    ): GithubModel {
        // 3. Palabra clave 'return'. Lo que devuelva el repositorio, el UseCase lo devuelve hacia arriba.
        return repository.obtainAvatar(
            // 4. Dato curioso de lógica de negocio: el UseCase recibe un GithubModel entero (model),
            // pero el repositorio solo necesita un String (avatar). 
            // Así que el UseCase extrae solo el texto que necesita usando 'model.avatar' y se lo manda al repositorio.
            model.avatar
        )
    }
}