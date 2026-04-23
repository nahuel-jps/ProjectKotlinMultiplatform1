package com.ucb.app.github.domain.repository

import com.ucb.app.github.domain.model.GithubModel

interface GithubRepository {
    // Al igual que antes, 'suspend' indica que esto tomará tiempo (probablemente irá a la API de Github en internet).
    suspend fun obtainAvatar(
        // Poner los parámetros con saltos de línea es solo un estilo visual de Kotlin para que sea más fácil de leer.
        avatar: String
    // ¡Aquí está la gran diferencia con el repository de Login!
    // Después del paréntesis vemos ': GithubModel'. 
    // Esto significa que esta función SÍ DEVUELVE ALGO. El contrato dice: 
    // "Dame un texto (el nombre del avatar), yo iré a buscarlo, y cuando termine, te DEVOLVERÉ una cajita llena tipo GithubModel".
    ): GithubModel
}