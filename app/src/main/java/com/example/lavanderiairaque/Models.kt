package com.example.lavanderiairaque

data class Usuario(
    val USUARIO_ID: Int,
    val USUARIO_NOME: String,
    val USUARIO_EMAIL: String,
    val USUARIO_TELEFONE: String
)

data class Admin(
    val ADMIN_ID: Int,
    val ADMIN_NOME: String,
    val ADMIN_EMAIL: String
)

data class Pedido(
    val PEDIDO_ID: Int,
    val PEDIDO_DESCRICAO: String,
    val PEDIDO_STATUS: String,
    val PEDIDO_DATA: String,
    val USUARIO_ID: Int
)
