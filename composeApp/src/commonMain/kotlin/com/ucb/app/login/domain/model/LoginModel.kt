package com.ucb.app.login.domain.model

// Un "data class" en Kotlin es una clase especial diseñada EXCLUSIVAMENTE para guardar datos 
// (similar a un 'struct' en C++ o usar '@dataclass' en Python).
// La ventaja de poner la palabra "data" es que Kotlin automáticamente le crea métodos útiles por debajo 
// como "toString()" (para imprimirlo fácil) o "equals()" (para comparar si dos modelos son iguales).
data class LoginModel(
    // Los paréntesis () definen el "Constructor Primario". 
    // Al declarar aquí "val" (que significa valor constante/inmutable), Kotlin automáticamente 
    // crea las propiedades dentro de la clase y te obliga a pasarle el email y password al crear la clase. (solo valores que se utilizan en las reglas de negocio de este login correspondiente)
    val email: String,
    val password: String
) {
    // Estas llaves {} son el "Cuerpo de la clase".
    // A menudo se dejan vacías (y hasta se pueden borrar) si solo quieres guardar el email y password, 
    // pero si las dejas, próximamente aquí podrías agregar funciones (métodos) propias de este modelo.
    // Por ejemplo, aquí podrías tener en el futuro una función validadora:
    // fun isEmailValid(): Boolean { return email.contains("@") }
}