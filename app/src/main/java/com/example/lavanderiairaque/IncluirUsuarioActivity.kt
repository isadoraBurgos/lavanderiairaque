package com.example.lavanderiairaque

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class IncluirUsuarioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_incluir_usuario)
        val etNome     = findViewById<EditText>(R.id.etNomeUsuario)
        val etEmail    = findViewById<EditText>(R.id.etEmailUsuario)
        val etTelefone = findViewById<EditText>(R.id.etTelefoneUsuario)
        val etSenha    = findViewById<EditText>(R.id.etSenhaUsuario)
        findViewById<Button>(R.id.btnSalvarUsuario).setOnClickListener {
            RetrofitClient.apiService.incluirUsuario(etNome.text.toString(), etEmail.text.toString(), etTelefone.text.toString(), etSenha.text.toString())
                .enqueue(object : Callback<Void> {
                    override fun onResponse(call: Call<Void>, response: Response<Void>) { Toast.makeText(this@IncluirUsuarioActivity, "Usuário cadastrado!", Toast.LENGTH_SHORT).show(); finish() }
                    override fun onFailure(call: Call<Void>, t: Throwable) { Toast.makeText(this@IncluirUsuarioActivity, "Erro ao cadastrar", Toast.LENGTH_SHORT).show() }
                })
        }
    }
}
