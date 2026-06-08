package com.example.lavanderiairaque

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class IncluirPedidoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_incluir_pedido)
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationIcon(R.drawable.ic_back_arrow)
        toolbar.setNavigationOnClickListener { finish() }
        val etDesc  = findViewById<EditText>(R.id.etDescricaoPedido)
        val etStat  = findViewById<EditText>(R.id.etStatusPedido)
        val etUid   = findViewById<EditText>(R.id.etUsuarioIdPedido)
        findViewById<Button>(R.id.btnSalvarPedido).setOnClickListener {
            RetrofitClient.apiService.incluirPedido(etDesc.text.toString(), etStat.text.toString(), etUid.text.toString().toIntOrNull() ?: 0)
                .enqueue(object : Callback<Void> {
                    override fun onResponse(call: Call<Void>, response: Response<Void>) { Toast.makeText(this@IncluirPedidoActivity, "Pedido cadastrado!", Toast.LENGTH_SHORT).show(); finish() }
                    override fun onFailure(call: Call<Void>, t: Throwable) { Toast.makeText(this@IncluirPedidoActivity, "Erro", Toast.LENGTH_SHORT).show() }
                })
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
