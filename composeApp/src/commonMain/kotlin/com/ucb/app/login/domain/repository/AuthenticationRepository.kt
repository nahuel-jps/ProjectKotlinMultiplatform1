package com.ucb.app.login.domain.repository

import com.ucb.app.login.domain.model.LoginModel

// Una 'interface' es como un "contrato" o un "menú". Solo define QUÉ se debe hacer, pero no CÓMO.
// ¿Por qué esto va en la capa de Domain (Dominio) y su implementación en Data?
// Principio de Inversión de Dependencias: El Dominio NO PUEDE depender de cosas externas (bases de datos o internet).
// Por lo tanto, el Dominio crea este contrato y dice "No me importa CÓMO lo hagan, pero alguien tiene que proveerme esta función".
// Más tarde, la capa de Data (que sí sabe de internet o bases de datos) "firmará" este contrato y escribirá el código real.
// Si mañana cambias tu base de datos, el Dominio no se toca.
interface AuthenticationRepository {
    // La palabra 'suspend' es clave en Kotlin. Significa que esta función puede "pausarse/suspenderse".
    // Como hacer un login toma tiempo (ir al servidor, esperar respuesta), 'suspend' permite que el celular
    // haga este trabajo en segundo plano sin "congelar" la pantalla visual del usuario (Corrutinas de Kotlin).
    suspend fun login(model: LoginModel)
}