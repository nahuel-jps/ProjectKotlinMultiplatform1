package com.ucb.app.login.domain.usecase

import com.ucb.app.login.domain.model.LoginModel
import com.ucb.app.login.domain.repository.AuthenticationRepository

// ¿Por qué se llama así?
// En Clean Architecture, un UseCase (Caso de Uso) representa UNA SOLA ACCIÓN específica que puede hacer el usuario.
// La regla de nombramiento es: Verbo + Sustantivo + "UseCase". (Ej: DoLoginUseCase, GetUserUseCase, ResetPasswordUseCase).
// Si tuvieras otro caso de uso, por ejemplo para registrarse, se llamaría RegisterUserUseCase.
class DoLoginUseCase(
    // ¡Aquí está la magia del Dominio! El UseCase pide la interfaz (el contrato) en su constructor.
    // Fíjate que pide la 'interface AuthenticationRepository', NO la implementación real. 
    // Como decíamos antes, este es el "Gerente" dando órdenes a ciegas a quien sea que firme el contrato.
    val repository: AuthenticationRepository
) {

    // Cada UseCase suele tener una sola función principal pública.
    // Muchos programadores la llaman 'invoke' (invocar) o 'execute' (ejecutar).
    // ¿Qué modelos utiliza? Depende de la acción. Para "DoLogin", necesita el LoginModel.
    // Si fuera "RecoverPasswordUseCase", quizás solo necesite un String (el email). Todo depende de la regla de negocio.
    suspend fun invoke(model: LoginModel) {
        // El gerente (Dominio) simplemente le pasa el modelo al repositorio (contrato) y le dice: "Haz tu trabajo".
        repository.login(model)
    }
}