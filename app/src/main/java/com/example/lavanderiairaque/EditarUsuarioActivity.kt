package com.example.lavanderiairaque

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditarUsuarioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_usuario)
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationIcon(R.drawable.ic_back_arrow)
        toolbar.setNavigationOnClickListener { finish() }
        val id         = intent.getIntExtra("USUARIO_ID", 0)
        val etNome     = findViewById<EditText>(R.id.etNomeUsuario)
        val etEmail    = findViewById<EditText>(R.id.etEmailUsuario)
        val etTelefone = findViewById<EditText>(R.id.etTelefoneUsuario)
        etNome.setText(intent.getStringExtra("USUARIO_NOME"))
        etEmail.setText(intent.getStringExtra("USUARIO_EMAIL"))
        etTelefone.setText(intent.getStringExtra("USUARIO_TELEFONE"))
        findViewById<Button>(R.id.btnSalvarUsuario).setOnClickListener {
            RetrofitClient.apiService.editarUsuario(id, etNome.text.toString(), etEmail.text.toString(), etTelefone.text.toString())
                .enqueue(object : Callback<Void> {
                    override fun onResponse(call: Call<Void>, response: Response<Void>) { Toast.makeText(this@EditarUsuarioActivity, "Atualizado!", Toast.LENGTH_SHORT).show(); finish() }
                    override fun onFailure(call: Call<Void>, t: Throwable) { Toast.makeText(this@EditarUsuarioActivity, "Erro", Toast.LENGTH_SHORT).show() }
                })
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
