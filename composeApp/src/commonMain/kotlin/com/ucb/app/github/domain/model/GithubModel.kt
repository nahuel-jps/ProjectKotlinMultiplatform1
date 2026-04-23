package com.ucb.app.github.domain.model

// ¡Aquí hay dos conceptos nuevos muy importantes comparados con LoginModel!
// 1. Ausencia de llaves {}: Como vimos antes, si el modelo no tiene funciones internas, puedes omitir las llaves {}. Queda mucho más limpio.
// 2. Valores por defecto (Default Values): ¿Ves el '= ""' o '= 0'?
// Esto significa que si alguien crea un GithubModel() vacío y NO le pasa el email o la contraseña,
// Kotlin automáticamente llenará esos espacios con textos vacíos o ceros.
// En C++ esto también existe (parámetros por defecto), pero en Kotlin es súper útil para las 'data class'.
data class GithubModel(
    val name: String = "",
    val urlImage: String = "",
    val avatar: String = "",
    val company: String = "",
    val createdAt: String = "",
    // Aquí vemos un nuevo tipo de dato: Int (Entero).
    val followers: Int = 0,
    val bio: String = ""
)
