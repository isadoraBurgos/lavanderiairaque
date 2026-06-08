package com.example.lavanderiairaque

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class IncluirAdminActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_incluir_admin)
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationIcon(R.drawable.ic_back_arrow)
        toolbar.setNavigationOnClickListener { finish() }
        val etNome  = findViewById<EditText>(R.id.etNomeAdmin)
        val etEmail = findViewById<EditText>(R.id.etEmailAdmin)
        val etSenha = findViewById<EditText>(R.id.etSenhaAdmin)
        findViewById<Button>(R.id.btnSalvarAdmin).setOnClickListener {
            RetrofitClient.apiService.incluirAdmin(etNome.text.toString(), etEmail.text.toString(), etSenha.text.toString())
                .enqueue(object : Callback<Void> {
                    override fun onResponse(call: Call<Void>, response: Response<Void>) { Toast.makeText(this@IncluirAdminActivity, "Admin cadastrado!", Toast.LENGTH_SHORT).show(); finish() }
                    override fun onFailure(call: Call<Void>, t: Throwable) { Toast.makeText(this@IncluirAdminActivity, "Erro", Toast.LENGTH_SHORT).show() }
                })
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
