package com.example.lavanderiairaque

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    // USUARIO
    @GET("usuarios.php")
    fun getUsuarios(): Call<List<Usuario>>

    @FormUrlEncoded
    @POST("incluir_usuario.php")
    fun incluirUsuario(
        @Field("USUARIO_NOME") nome: String,
        @Field("USUARIO_EMAIL") email: String,
        @Field("USUARIO_TELEFONE") telefone: String,
        @Field("USUARIO_SENHA") senha: String
    ): Call<Void>

    @FormUrlEncoded
    @POST("editar_usuario.php")
    fun editarUsuario(
        @Field("USUARIO_ID") id: Int,
        @Field("USUARIO_NOME") nome: String,
        @Field("USUARIO_EMAIL") email: String,
        @Field("USUARIO_TELEFONE") telefone: String
    ): Call<Void>

    @FormUrlEncoded
    @POST("deletar_usuario.php")
    fun deletarUsuario(@Field("USUARIO_ID") id: Int): Call<Void>

    // ADMIN
    @GET("admins.php")
    fun getAdmins(): Call<List<Admin>>

    @FormUrlEncoded
    @POST("incluir_admin.php")
    fun incluirAdmin(
        @Field("ADMIN_NOME") nome: String,
        @Field("ADMIN_EMAIL") email: String,
        @Field("ADMIN_SENHA") senha: String
    ): Call<Void>

    @FormUrlEncoded
    @POST("editar_admin.php")
    fun editarAdmin(
        @Field("ADMIN_ID") id: Int,
        @Field("ADMIN_NOME") nome: String,
        @Field("ADMIN_EMAIL") email: String
    ): Call<Void>

    @FormUrlEncoded
    @POST("deletar_admin.php")
    fun deletarAdmin(@Field("ADMIN_ID") id: Int): Call<Void>

    // PEDIDO
    @GET("pedidos.php")
    fun getPedidos(): Call<List<Pedido>>

    @FormUrlEncoded
    @POST("incluir_pedido.php")
    fun incluirPedido(
        @Field("PEDIDO_DESCRICAO") descricao: String,
        @Field("PEDIDO_STATUS") status: String,
        @Field("USUARIO_ID") usuarioId: Int
    ): Call<Void>

    @FormUrlEncoded
    @POST("editar_pedido.php")
    fun editarPedido(
        @Field("PEDIDO_ID") id: Int,
        @Field("PEDIDO_DESCRICAO") descricao: String,
        @Field("PEDIDO_STATUS") status: String
    ): Call<Void>

    @FormUrlEncoded
    @POST("deletar_pedido.php")
    fun deletarPedido(@Field("PEDIDO_ID") id: Int): Call<Void>
}
